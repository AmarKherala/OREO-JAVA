package amar.oreo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class amarlogger {
  private static final Logger logger = LoggerFactory.getLogger(amarlogger.class);
  
  public static void info(String msg){
    logger.info(msg);
  }
  public static void warn(String msg){
    logger.warn(msg);
  }
  public static void debug(String msg){
    logger.debug(msg);
  }
  public static void error(String msg){
    logger.error(msg);
  }
}