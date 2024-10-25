#!/bin/bash

find .  -name "*.class" -type f -delete

javac -cp po-uilib.jar:. `find hva -name "*.java"`

java -cp po-uilib.jar:. hva.app.App

wait

find .  -name "*.class" -type f -delete
