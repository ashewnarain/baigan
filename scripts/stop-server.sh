#!/bin/bash
kill -SIGINT $(lsof -t -i:8080)