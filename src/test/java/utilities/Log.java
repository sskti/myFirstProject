package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log {
	
	static Logger logger = LogManager.getLogger(Log.class.getName());
	
	public static void info(String infoMsg) {
		logger.info(infoMsg);
	}
	
	public static void warn(String warnMsg) {
		logger.warn(warnMsg);
	} 
}
