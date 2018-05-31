# Java OBD API

An OBD-II API written in Java. Based on [obd-java-api](https://github.com/pires/obd-java-api).

## Quickstart

### Android
Just include it into the build.gradle file:

```
   dependencies {
     ...

     // Java OBD API
     implementation 'com.github.eltonvs:java-obd-api:0.0.1'
   }
```

### Maven
You only need to include it into your pom.xml file:

```
   <dependency>
     <groupId>com.github.eltonvs</groupId>
     <artifactId>java-obd-api</artifactId>
     <version>0.0.1</version>
   </dependency>
```

## Sample Usage

After pairing and establishing a Bluetooth connection with your OBD device.
```Java
// retrieve Bluetooth socket
socket = ...; // specific to the VM you're using (Java, Android, etc.)

// Group many obd commands into a single command ()
ObdCommandGroup obdCommands = new ObdCommandGroup();
obdCommands.add(new EchoOffCommand());
obdCommands.add(new LineFeedOffCommand());
obdCommands.add(new TimeoutCommand(timeout));
obdCommands.add(new SelectProtocolCommand(protocol));

// Run all commands at once
obdCommands.run(socket.getInputStream(), socket.getOutputStream());
```

## Contributing

We're open for contributions!

## Authors

* **[Elton Viana](https://github.com/eltonvs)** - *Initial work*
* **[Ivanovitch Silva](https://github.com/ivanovitchm)** - *Initial work*

See also the list of [contributors](https://github.com/eltonvs/java-obd-api/contributors) who participated in this project.

## License

This project is licensed under the Apache 2.0 - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* **Paulo Pires** - Creator of the [obd-java-api](https://github.com/pires/obd-java-api), on which this project was based.
* **[SmartMetropolis Project](http://smartmetropolis.imd.ufrn.br)** (Digital Metropolis Institute - UFRN, Brazil)
