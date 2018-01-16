#!/bin/bash
watch -n 5 mv /ou/pre-readings/* /ou/readings/input/ >> /ou/fm.log &
java -jar /app.jar