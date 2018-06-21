#!/bin/bash -e
isExistApp=$(pgrep httpd)
if [[ -n $isExistApp ]]; then
service httpd stop

