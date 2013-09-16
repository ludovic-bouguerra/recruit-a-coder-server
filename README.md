Recruit a coder plateform : 
===========================

[![Build Status](https://travis-ci.org/ludovic-bouguerra/ecodigo-server.png?branch=master)](https://travis-ci.org/ludovic-bouguerra/ecodigo-server)

Recruit a coder is a web platform to automate developper recruitement process. Recruitement services can purpose technical tests to developper to know if he known languages basics.

More information at : www.recruitacoder.com

This application is the server part which compile and run the code and compare the output result with the expected result. To run this application need to be connected to a message broker like ActiveMQ or the Glassfish embedded message broker.

Actually the software can compile and compare Java Code but you can easily add other languages like PHP or C. (Documentation coming soon).

You can find the client part in another repository. https://github.com/ludovic-bouguerra/ecodigo-server/


Architecture
=============
Comming soon.

Launching a test.
==================

Installing the sofware
======================
```
mvn install
```

The application will be available on the target dir.

You have to configure the application with the config.ini file following these instructions :
Rename in directory config.ini.sample to config.ini and modify settings.
```
; E-codigo configuration
; (c) 2013 Ludovic Bouguerra
ecodigo.java.home=/usr/bin/
ecodigo.messenging.url=tcp://localhost:61616
ecodigo.messenging.queuename=client.messages
```

After you can run the application launching the following command :
```
java -jar NAME 
```


To run this application in a production mode you have to put it on a service.
