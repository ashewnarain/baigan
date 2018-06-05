#!/bin/bash
cd /tmp
ls -l
nohup java -jar target/smartbox-1.0-SNAPSHOT.jar server config.yml > /var/log/smartbox.out 2>&1