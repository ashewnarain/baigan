#!/bin/bash
cd /tmp
ls -l
nohup java -jar target/smartbox-1.0-SNAPSHOT.jar server config.yml > logs/smartbox.out 2>&1 &
