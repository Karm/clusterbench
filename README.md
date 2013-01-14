ClusterBench
============

ClusterBench is, or rather will be, a sample application to be deployed in a cluster of JBoss AS 7 and AS 5. Then it is easy to stress (curl, JMeter, whatnot) and monitor the perfrormance of the cluster and at the same time easily check correctness of replicated sessions.

Building
--------

It comes in 2 flavors for Java EE 5 and 6:

    $ mvn clean install -Pee6 # default
    $ mvn clean install -Pee5
    $ mvn clean install -Pee5,ee6 # build both

Output files:

    ./clusterbench-ee5-ear/target/clusterbench-ee5-web.war
    ./clusterbench-ee6-ear/target/clusterbench-ee6-web.war

Issues
------

Create them on GitHub:
[https://github.com/Karm/clusterbench/issues](https://github.com/Karm/clusterbench/issues)

Servlets
========

HttpSessionServlet
------------------
[/clusterbench/session](http://localhost:8080/clusterbench/session)

MemoryUsageServlet
------------------
[/clusterbench/memoryusage?milliseconds=10000&megabytes=500](http://localhost:8080/clusterbench/memoryusage?milliseconds=10000&megabytes=500)

AverageSystemLoadServlet
------------------------
[/clusterbench/averagesystemload?milliseconds=10000&threads=4](http://localhost:8080/clusterbench/averagesystemload?milliseconds=10000&threads=4)

BusyConnectorsLoadServlet
-------------------------
[/clusterbench/busyconnectorsload?milliseconds=10000](http://localhost:8080/clusterbench/busyconnectorsload?milliseconds=10000)

SendTrafficLoadServlet
----------------------
[/clusterbench/sendtrafficload?kilobytes=100](http://localhost:8080/clusterbench/sendtrafficload?kilobytes=100)

ReceiveTrafficLoadServlet
-------------------------
 Set ```data``` in POST method to ```/receivetrafficload```. See receiveTrafficLoadMetricManualTest in clusterbench-common.
