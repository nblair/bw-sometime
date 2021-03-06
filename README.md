## Bedework Sometime

[![Build Status](https://travis-ci.org/Bedework/bw-sometime.svg?branch=master)](https://travis-ci.org/Bedework/bw-sometime)

Bedework Sometime is an online calendar application that provides unique appointment scheduling capabilities. 

This project is a fork of the [Jasig Scheduling Assistant](https://wiki.jasig.org/display/SA/Home).

### Requirements

This application expects to integrate with your Enterprise Calendar. The default configuration targets a Bedework instance, but capabilities exist to integrate with other providers.

### Building

This application targets JDK 7 for build and runtime. 

This project uses Apache Maven; run 'mvn install' to perform the default build.

2 wars are generated by the project:

* sched-assist-war is the primary web application targeting Servlet 2.5 API compatible containers.
* sched-assist-portlet is an optional JSR-168 Portlet suitable for use in a Portlet 1.0 container.

### Running

The application is configured by default to connect to a Bedework Quickstart (3.10.x) instance running on the same host. 
Before you run Sometime, you must have at a minimum the ApacheDS LDAP directory provided by the quickstart running. In the Bedework Quickstart directory, execute the following command to start the directory:

> ./bw dirstart

You can find complete directions for starting up the Bedework Quickstart [on the Bedework wiki](https://wiki.jasig.org/display/BWK310/Running+Bedework).

To start Bedework Sometime, simply execute:

> mvn install jetty:run-war

The application will now be running at [http://localhost:8080/scheduling-assistant/](http://localhost:8080/scheduling-assistant/).

The *run-war* goal is required. The *run* goal will not work because it loads content from src/main/webapp only, which does not
contain the static assets provided by the Jasig Resource Server.

If you would like to connect a debugger, use the following command instead:

> mvn install jetty:run-forked

You can connect your debugger to port 18888.


