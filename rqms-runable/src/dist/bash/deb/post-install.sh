#!/bin/bash

RQMS_USERNAME=rqms
RQMS_SERVICE_NAME=rqms

# Create Directories
mkdir -p /usr/share/rqms/plugins

# Create User
adduser --system $RQMS_USERNAME
chown -R $RQMS_USERNAME /usr/share/rqms/

# Register and Start Service
systemctl start $RQMS_SERVICE_NAME
systemctl enable $RQMS_SERVICE_NAME