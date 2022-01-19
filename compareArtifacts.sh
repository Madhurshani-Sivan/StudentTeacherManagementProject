#!/bin/sh
MAVEN=target/StudentTeacherManagement-0.0.1-SNAPSHOT.jar
GRADLE=build/libs/StudentTeacherManagement-0.0.1-SNAPSHOT.jar

ls -hl $MAVEN  | awk '{print $9, $5}'
ls -hl $GRADLE  | awk '{print $9, $5}'