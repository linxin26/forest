package co.solinx.forest.container.log4j;

import co.solinx.forest.container.IContainer;
import org.apache.log4j.*;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by LX on 2015/3/1.
 */
public class Log4jContainer implements IContainer {

    public static final String LOG4J_FILE = "dubbo.log4j.file";

    public static final String LOG4J_LEVEL = "dubbo.log4j.level";

    public static final String LOG4J_SUBDIRECTORY = "dubbo.log4j.subdirectory";

    public static final String DEFAULT_LOG4J_LEVEL = "ERROR";

    @Override
    public void start() {
        System.out.println("log4j start");

//        String file = ConfigUtils.getProperty(LOG4J_FILE);
//        if (file != null && file.length() > 0) {
//            String level = ConfigUtils.getProperty(LOG4J_LEVEL);
//            if (level == null || level.length() == 0) {
//                level = DEFAULT_LOG4J_LEVEL;
//            }

        Properties properties=new Properties();
        properties.setProperty("log4j.rootLogger", "INFO, application, Console");
        properties.setProperty("log4j.appender.application", "org.apache.log4j.DailyRollingFileAppender");
        properties.setProperty("log4j.appender.application.File", "log4j.file");
        properties.setProperty("log4j.appender.application.Append", "true");
        properties.setProperty("log4j.appender.application.DatePattern", "'.'yyyy-MM-dd");
        properties.setProperty("log4j.appender.application.layout", "org.apache.log4j.PatternLayout");
        properties.setProperty("log4j.appender.application.layout.ConversionPattern", "%d [%t] %-5p %C{6} (%F:%L) - %m%n");
        properties.setProperty("log4j.appender.application", "org.apache.log4j.DailyRollingFileAppender");

        properties.setProperty("log4j.appender.Console", "org.apache.log4j.ConsoleAppender");
        properties.setProperty("log4j.appender.Console.layout", "org.apache.log4j.PatternLayout");
        properties.setProperty("log4j.appender.Console.layout.ConversionPattern", "%d{ABSOLUTE} %-5p [%c{3}] %m%n ");
        PropertyConfigurator.configure(properties);
//        String subdirectory = ConfigUtils.getProperty(LOG4J_SUBDIRECTORY);
//        if (subdirectory != null && subdirectory.length() > 0) {
            Enumeration<Logger> ls = LogManager.getCurrentLoggers();
            while (ls.hasMoreElements()) {
                org.apache.log4j.Logger l = ls.nextElement();
                if (l != null) {
                    Enumeration<Appender> as = l.getAllAppenders();
                    while (as.hasMoreElements()) {
                        Appender a = as.nextElement();
                        if (a instanceof FileAppender) {
                            FileAppender fa = (FileAppender)a;
                            String f = fa.getFile();
                            if (f != null && f.length() > 0) {
                                int i = f.replace('\\', '/').lastIndexOf('/');
                                String path;
//                                if (i == -1) {
//                                    path = subdirectory;
//                                } else {
//                                    path = f.substring(0, i);
//                                    if (! path.endsWith(subdirectory)) {
//                                        path = path + "/" + subdirectory;
//                                    }
//                                    f = f.substring(i + 1);
//                                }
//                                fa.setFile(path + "/" + f);
//                                fa.activateOptions();
                            }
                        }
                    }
                }
//            }
        }

    }

    @Override
    public void stop() {

    }
}
