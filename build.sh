#!/bin/bash

set -e
set -x

source /home/work/build_script/env/java/jdk_1.8.env

echo $1

MODULE_NAME="todolist"

compile_module() {
  mvn -U clean compile package -Dmaven.test.skip=true
  mkdir -p release
  cp -r deploy start.sh target/*.war release/
  cd release
  jar -xvf *.war
  rm *.war
}

compile_module
