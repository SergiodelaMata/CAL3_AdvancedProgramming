package cal3;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerThread extends Thread {
	
	private Logger logger;
	private FileHandler fh;
	private SimpleFormatter sf;
	public LoggerThread() {	
		try {
			this.logger = Logger.getLogger("MyLog");
			this.fh = new FileHandler("C:/Users/Manuel/Desktop/log.txt");
			logger.addHandler(fh);
			this.sf = new SimpleFormatter();
			fh.setFormatter(sf);
		} catch (Exception e) {
		}
	}
	
	public void log (String s) {
		logger.info(s);
	}
}
