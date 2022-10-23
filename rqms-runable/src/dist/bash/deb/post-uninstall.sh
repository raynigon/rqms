#!/bin/bash

RQMS_PLUGIN_DIRECTORY=/usr/share/rqms/plugins

if [ -z "$(ls -A $RQMS_PLUGIN_DIRECTORY)" ]; then
  rm -rf "$RQMS_PLUGIN_DIRECTORY"
fi