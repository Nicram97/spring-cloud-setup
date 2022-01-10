<h1>Spring-cloud-setup</h1>
<p>This app is Spring-boot based exercise of simple cloud app setup with different services
<b>default port 8080.</b>
</p>

<h3>How-To</h3>

<h4>Run the app</h4>
<p>
To run the app with most basic functionalities You just need to run the app and eventually set proper variables in application.yml in resources folder considering subservice (<b>Spring-cloud-subservice</b>)
Default behaviour is to run the app using SQLite database.
If You want to use other functionalities and connect app to other services like influx, postgres Db and sending logs to logstash
You need to deploy those app or run them on localhost (I've used docker-compose for those services) and then uncomment
desired sections to use the code.
<b>IMPORTANT:</b> To use influx You also need to uncomment and rebuild gradle, line connected to micrometer-influx (line 64 in build.gradle)
"//    implementation group: 'io.micrometer', name: 'micrometer-registry-influx', version: '1.8.1'"
To properly set logstash, take a look into logstash.conf and set proper port etc.
</p>

<h4>Run contract tests</h4>
<p>To run tests trigger build or just tests but remember to properly set values connected with mocked service + contract stub generated through subservice.</p>

<h4>Deploy app to kubernetes</h4>
<p>
To create deployment and service You can use spring-deployment/service.yaml with command "kubectl apply (-f FILENAME | -k DIRECTORY)"
If You want deployment/service to work remember to use proper name whe creating and pushing app docker build and to properly set Prometheus port
to properly scrap metrics (default prometheus port is 8080 when deployed through helm-chart).
</p>