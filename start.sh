#!/usr/bin/env bash

set -e
set -x

PROFILE=$1
MODULE=$2
GC_LOG_BASE=$3
TIME=$(date +%Y-%m-%d-%H)

export JAVA_HOME=/opt/soft/openjdk1.8.0
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$JAVA_HOME/bin:$PATH

function stop_process() {
  pid=$(ps -ef | grep -E "$MODULE" | grep -v "start.sh" | grep -v "grep" | grep -v '.run.log' | awk '{print $2}')
  if [ -n "$pid" ]; then
    for i in $pid; do
      kill $i
    done
  fi

  for (( i = 0; i < 150; i++ )); do
  pid=$(ps -ef | grep -E "$MODULE" | grep -v "start.sh" | grep -v "grep" | grep -v '.run.log' | awk '{print $2}')
  if [ -n "$pid" ];then
      sleep 0.2
   else
      echo $i
      break
  fi
  done

}

function start_process() {
  if [ $PROFILE = "staging" ]; then
    args="$args -server
              -Xmx4G
              -Xms1G
              -Xmn600M
              -XX:MetaspaceSize=512M
              -XX:MaxMetaspaceSize=4G
              -Xss1M
              -XX:+DisableExplicitGC
              -XX:SurvivorRatio=3
              "
  else
    args="$args -server
              -Xmx8G
              -Xms8G
              -Xmn3G
              -XX:MetaspaceSize=512M
              -XX:MaxMetaspaceSize=4G
              -Xss1M
              -XX:+DisableExplicitGC
              -XX:SurvivorRatio=3
              "
  fi
  args="$args -XX:+UseConcMarkSweepGC
              -XX:+UseParNewGC"
  args="$args -XX:+CMSParallelRemarkEnabled
              -XX:+UseCMSCompactAtFullCollection
              -XX:CMSFullGCsBeforeCompaction=0
              -XX:+CMSClassUnloadingEnabled
              -XX:LargePageSizeInBytes=128M
              -XX:+UseFastAccessorMethods
              -XX:+UseCMSInitiatingOccupancyOnly
              -XX:CMSInitiatingOccupancyFraction=80
              -XX:SoftRefLRUPolicyMSPerMB=0
              "
  args="$args -XX:+PrintClassHistogram
              -XX:+PrintGCDetails
              -XX:+PrintGCTimeStamps
              -XX:+PrintGCDateStamps
              -XX:+PrintHeapAtGC
              -Xloggc:$GC_LOG_BASE/gc.log.$TIME
              "
#  if [ $PROFILE = "staging" ]; then
#    args="$args -Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=9908"
#  fi

  pid=$(ps -ef | grep -E "$MODULE" | grep -v "start.sh" | grep -v "grep" | grep -v '.run.log'| awk '{print $2}')
  if [ -n "$pid" ]; then
    for i in $pid; do
      kill -9 $i
    done
  fi
  /opt/soft/openjdk1.8.0/jre/bin/java $args -javaagent:/home/work/app/mitelemetry/agent/opentelemetry-javaagent-all.jar -Dspring.profiles.active=$PROFILE -Dio.netty.leakDetectionLevel=SIMPLE org.springframework.boot.loader.WarLauncher
}

function restart_process() {
  stop_process
  start_process
}

restart_process
