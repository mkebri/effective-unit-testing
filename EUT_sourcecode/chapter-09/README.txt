1. OVERVIEW

This is a sample project for demonstrating a remote build. The project in question is JspTest (http://github.com/jsptest/jsptest). It's a multimodule Maven 2 project, which serves to prove that the remote build script provided works with more complicated builds just as well as with simpler builds.


2. SYSTEM REQUIREMENTS

What you need for running the remote build is a computer with an operating system capable of running Bash shell scripts. You also need to have an SSH client installed (both ssh and scp). On a Windows computer, your best bet is to install the Cygwin environment. You'll also need a Linux server but we'll get to that later on in section 4.


3. RUNNING THE BUILD LOCALLY

Being a standard Maven 2 project, you should be able to run "mvn clean test" and similar commands right from the project's root directory (this directory). Do that first just to be sure that things work locally:

  mvn clean test

You should see all of JspTest's submodules being built successfully.


4. PREPARING FOR REMOTE BUILDS

For running the build remotely we need a couple of things in place:

1) a Linux server for delegating the build to, 
2) access to that server using SSH and passwordless private key authentication

The project's root directory contains two shell scripts: "remote-setup.sh" and "build.sh". The former is used for preparing a remote server for our use as a remote build slave. The latter script is used as a frontend for Maven, which offloads the actual build work to the remote server if you've got one set up.

The first step is to acquire a server. I would suggest getting one from a cloud provider such as Amazon Web Services (http://aws.amazon.com) or Rackspace (http://www.rackspace.com). What you need is an Ubuntu server with some disk space and some 512-1024 gigabytes of memory. You also need to know the server's hostname and credentials for logging in with SSH. Having acquired such a server, run the remote-setup.sh script as follows:

  ./remote-setup.sh [the-hostname-of-your-server]

What the script does is it'll log in to the server, create a user account for you on the server (prompting you for the password), install the prerequisite infrastructure software like Java and Maven. Then it'll upload your private key for identity-based, passwordless SSH access so you won't need to keep typing in your password when running the build script.

Once you've got the server set up, it's time to try running the build with the "build.sh" wrapper.


5. RUNNING THE BUILD REMOTELY

The remoting magic is done with a small wrapper build script called "build.sh". Instead of invoking Maven directly, we'll call this build script. Let's try and see that it works locally first:

  ./build.sh clean test

This should give you the exact same output than if you'd have invoked "mvn clean test".

Now, let's try running the same build targets on the server. All we need to do is to pass an option that tells the script which server to use:

  ./build.sh -r [the-hostname-of-your-server] clean test

You should see the exact same output from Maven but this time the build script first syncs your local project directory to the server and invokes Maven on the server, feeding back the output to your console. When the build is done, the script downloads the build artifacts to your computer. Assuming you have a reasonably fast network connection and a beefy server set up the build should be plenty fast - and it doesn't burden your computer much at all.

There's one more thing, though. Since we don't want to keep typing out the server's hostname all the time, we should probably export it to an environment variable and have build.sh pick it up automatically from there:

  export REMOTE_BUILD_MACHINE=[the-hostname-of-your-server]
  ./build.sh clean test

In the presence of the REMOTE_BUILD_MACHINE environment variable build.sh assumes that we'll want to run our build on that server unless we explicitly pass in the "-l" or "--local" option to enforce a local build.

This is a simple infrastructure for running your build on a remote computer but it works like magic!
