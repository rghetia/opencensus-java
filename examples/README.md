# OpenCensus Examples

## To build the examples use

### Gradle
```
$ ./gradlew installDist
```

### Maven
```
$ mvn package appassembler:assemble
```

### Bazel
```
$ bazel build :all
```

## To run "TagContextExample" use

### Gradle
```
$ ./build/install/opencensus-examples/bin/TagContextExample
```

### Maven
```
$ ./target/appassembler/bin/TagContextExample
```

### Bazel
```
$ ./bazel-bin/TagContextExample
```

## To run "ZPagesTester"

### Gradle
```
$ ./build/install/opencensus-examples/bin/ZPagesTester
```

### Maven
```
$ ./target/appassembler/bin/ZPagesTester
```

### Bazel
```
$ ./bazel-bin/ZPagesTester
```

Available pages:
* For tracing page go to [localhost:8080/tracez][ZPagesTraceZLink]. 
* For tracing config page go to [localhost:8080/traceconfigz][ZPagesTraceConfigZLink].
* For RPC stats page go to [localhost:8080/rpcz][ZPagesRpcZLink].
* For stats and measures on all registered views go to [localhost:8080/statsz][ZPagesStatsZLink].

[ZPagesTraceZLink]: http://localhost:8080/tracez
[ZPagesTraceConfigZLink]: http://localhost:8080/traceconfigz
[ZPagesRpcZLink]: http://localhost:8080/rpcz
[ZPagesStatsZLink]: http://localhost:8080/statsz

## To run "QuickStart" example use

### Gradle
```
$ ./build/install/opencensus-examples/bin/QuickStart
```

### Maven
```
$ ./target/appassembler/bin/QuickStart
```

### Bazel
```
$ ./bazel-bin/QuickStart
```

## To run "gRPC Hello World" example use

Please note all the arguments are optional. If you do not specify these arguments, default values
will be used:

* host and serverPort will be "localhost:50051"
* user will be "world"
* cloudProjectId will be null (which means no stats/spans will be exported to Stackdriver)
* server zPagePort will be 3000
* client zPagePort will be 3001
* Prometheus port will be 9090


However, if you want to specify any of these arguements, please make sure they are in order.

### Gradle
```
$ ./build/install/opencensus-examples/bin/HelloWorldServer serverPort cloudProjectId zPagePort prometheusPort
$ ./build/install/opencensus-examples/bin/HelloWorldClient user host serverPort cloudProjectId zPagePort
```

### Maven
```
$ ./target/appassembler/bin/HelloWorldServer serverPort cloudProjectId zPagePort prometheusPort
$ ./target/appassembler/bin/HelloWorldClient user host serverPort cloudProjectId zPagePort
```

### Bazel
```
$ ./bazel-bin/HelloWorldServer serverPort cloudProjectId zPagePort prometheusPort
$ ./bazel-bin/HelloWorldClient user host serverPort cloudProjectId zPagePort
```

## To run "Repl" example

See the full tutorial on [OpenCensus website](https://opencensus.io/quickstart/java/metrics/).

First run:

### Gradle
```
$ ./build/install/opencensus-examples/bin/Repl
```

### Maven
```
$ ./target/appassembler/bin/Repl
```

### Bazel
```
$ ./bazel-bin/Repl
```

Then start the Prometheus process:
```
$ cd src/main/java/io/opencensus/examples/quickstart/
$ prometheus --config.file=prometheus.yaml
```

Stats will be shown on Prometheus UI on http://localhost:9090.

## To run "StackdriverQuickstart" use

See the full tutorial on [OpenCensus website](https://opencensus.io/guides/exporters/supported-exporters/java/stackdriver/).

### Gradle
```
$ ./build/install/opencensus-examples/bin/StackdriverQuickstart
```

### Maven
```
$ ./target/appassembler/bin/StackdriverQuickstart
```

### Bazel
```
$ ./bazel-bin/StackdriverQuickstart
```

## To run "Jetty HTTP Example" use

Client generates request every 15 seconds to the Server. Traces can be observed in the logs for
both the Client and the Server.

The Client and the Server application exports trace to Logger and Jaeger. They exports stats to Prometheus.

* host and serverPort is "localhost:8080"
* Prometheus port for scraping stats is 9090 for server and 9091 for client.


### Gradle
```
$ ./build/install/opencensus-examples/bin/HttpJettyServer
$ ./build/install/opencensus-examples/bin/HttpJettyClient
```

### Maven
```
$ ./target/appassembler/bin/HttpJettyServer
$ ./target/appassembler/bin/HttpJettyClient
```
