#!/bin/bash
# 
# This is a shell script for delegating a Maven build to be executed on a remote server.
# 

BUILD_COMMAND="mvn"
BUILD_MODE="unknown"
VERBOSITY="quiet"
REMOTE_BUILD_DIR="~/test/"
REMOTE_RSYNC_OPTIONS="-az --delete --cvs-exclude"
REMOTE_SSH_OPTIONS="-t"

function usage {
    echo "usage: $0 [options] [arguments for maven]"
    echo ""
    echo "Options:"
    echo "  -h/--help               Display this help."
    echo "  -v/--verbose            Display verbose output for rsync and ssh."
    echo "  -l/--local              Run the build locally."
    echo "  -r/--remote [hostname]  Run the build remotely on the specified machine."
    echo ""
}

while [ "$1" != "" ]; do
    case $1 in
        -l | --local )    BUILD_MODE="local"
                          REMOTE_BUILD_MACHINE=""
                          ;;
        -r | --remote )   shift
                          REMOTE_BUILD_MACHINE="$1"
                          ;;
        -v | --verbose )  VERBOSITY="verbose"
                          ;;
        -h | --help )     usage
                          exit 1
                          ;;
        * )               BUILD_COMMAND="$BUILD_COMMAND $1"
    esac
    shift
done

if [ "" == "$REMOTE_BUILD_MACHINE" ]; then
	BUILD_MODE="local"
else
	BUILD_MODE="remote"
fi

if [ "verbose" == "$VERBOSITY" ]; then
	REMOTE_RSYNC_OPTIONS="$REMOTE_RSYNC_OPTIONS --verbose --stats --human-readable"
	REMOTE_SSH_OPTIONS="$REMOTE_SSH_OPTIONS -v"
else
	REMOTE_RSYNC_OPTIONS="$REMOTE_RSYNC_OPTIONS --quiet"
	REMOTE_SSH_OPTIONS="$REMOTE_SSH_OPTIONS -q"
fi

if [ "remote" == "$BUILD_MODE" ]; then
	echo "Synchronizing workspace to $REMOTE_BUILD_MACHINE"
	rsync $REMOTE_RSYNC_OPTIONS --delete-excluded --exclude target/ ./ $REMOTE_BUILD_MACHINE:$REMOTE_BUILD_DIR
	
	echo "Running build remotely on $REMOTE_BUILD_MACHINE"
	ssh $REMOTE_SSH_OPTIONS $REMOTE_BUILD_MACHINE "cd $REMOTE_BUILD_DIR ; bash -ic '$BUILD_COMMAND'"
	
	echo "Copying remote build results"
	rsync $REMOTE_RSYNC_OPTIONS --include '**/target/**' --exclude '*.*' $REMOTE_BUILD_MACHINE:$REMOTE_BUILD_DIR ./
else
	echo "Running build locally on $HOSTNAME"
	$BUILD_COMMAND
fi
