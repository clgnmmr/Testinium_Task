package utilities;

import org.apache.log4j.Logger;

public class Log {


    private static final Logger logger = Logger.getLogger(Log.class.getName());


    public static void startTestCase(String sTestCaseName){

        logger.info("******************         "+sTestCaseName+" Start         ******************");


    }
    public static void endTestCase(String sTestCaseName){
        logger.info("******************         "+sTestCaseName+" Finish         ******************");
    }


    public static void assertLog(String msg) {

        logger.info(msg);

    }


    public static void debug(String message)
    {
        logger.debug(message);

    }


    public static void error(String message) {
        logger.error(message);
    }

    public static void info(String message) {

        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);

    }


}