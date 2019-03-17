**Logging**

How to add a logger to your class and start logging

1. Add a reference<br>
`final Logger logger = LoggerFactory.getLogger(MyClass.class);`
<br>
_when adding imports make sure Logger and LoggerFactory are imported as follows_
<br>
    ```
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    ```
2. Logging
    ```
    logger.info("Something non critical happend, just wanted to inform you.");
    logger.warn("Something important happend, but everything is fine for now");
    logger.error("Something terrible happend, abort mission, send help.");
    ```
    There are 3 log levels, `info`,`warn` and `error`. Use accordingly.<br>
    You can also choose to log a stacktrace with an error message.<br>
    `logger.<loglevel>(String message, throwable error);`<br>
    e.g.
    ```
    try {
      // ...
    } catch (Exception e){
      logger.error("Well, that didn't work.", e);
    }
    ```
Logger configuration can be found at:<br>
`/src/main/resources/simplelogger.properties`<br>
For more information on configuration options:<br>
<a href="https://www.slf4j.org/apidocs/org/slf4j/impl/SimpleLogger.html">
    https://www.slf4j.org/apidocs/org/slf4j/impl/SimpleLogger.html
</a>
<br>
Logfile can be found at: `./log.txt`<br>

Logfile output example:<br>
```
2019.03.06 20:36:42 CET [INFO] Model.Movie.MovieImpl - e.g. Database connection established.
2019.03.06 20:36:42 CET [WARN] Model.Movie.MovieImpl - Uh, oh, something didn't go quite right, but everything is ok for now.
2019.03.06 20:36:42 CET [ERROR] Model.Movie.MovieImpl - SHIT, something went horribly wrong, abort mission, send help.
2019.03.06 20:36:42 CET [ERROR] Model.Movie.MovieImpl - example error message with attached runtime exception
java.lang.RuntimeException
	at LocalDirectory.Main.main(Main.java:13)
2019.03.06 20:36:42 CET [INFO] Model.Movie.MovieImpl - Another example info message
```