#!/bin/bash

HOSTNAME=$1
REMOTE_USERNAME=$USER
REMOTE_USERHOME=/home/$USERNAME

echo "Creating user $REMOTE_USERNAME at $HOSTNAME"
ssh $HOSTNAME <<EOF
  # CREATE USER
  useradd -d $REMOTE_USERHOME -m $REMOTE_USERNAME
  mkdir -p $REMOTE_USERHOME/.ssh
  touch $REMOTE_USERHOME/.ssh/authorized_keys
  chown -R $REMOTE_USERNAME $REMOTE_USERHOME
  chmod go-w $REMOTE_USERHOME $REMOTE_USERHOME/.ssh
  chmod 600 $REMOTE_USERHOME/.ssh/authorized_keys
EOF

echo "Installing Java and Maven at $HOSTNAME"
ssh $HOSTNAME <<EOF
  # INSTALL JAVA
  apt-get install -y openjdk-7-jdk

  # INSTALL MAVEN
  apt-get install wget
  cd /usr/local
  wget http://www.us.apache.org/dist/maven/binaries/apache-maven-3.0.4-bin.tar.gz
  tar xzf apache-maven-3.0.4-bin.tar.gz
  ln -s /usr/local/apache-maven-3.0.4/bin/mvn /usr/bin/mvn
EOF

if [[ -e "$HOME/.ssh/id_rsa.pub" ]]; then
  echo "Using private key $HOME/.ssh/id_rsa.pub"
else
  echo "No private key found. Generating a new key..."
  ssh-keygen -t rsa -N '' -f "$HOME/.ssh/id_rsa"
fi

echo "Uploading a public key for identity file-based login"
cat "$HOME/.ssh/id_rsa.pub" | ssh $HOSTNAME 'dd of=.ssh/authorized_keys oflag=append conv=notrunc'
