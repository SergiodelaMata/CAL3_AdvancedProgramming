package cal3;


import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerThread extends Thread {
	
	private Logger logger;
	private FileHandler fh;
	private SimpleFormatter sf;
        private ArrayList<String> information;
        private Counter counter;
        
	public LoggerThread()
        {
            try
            {
                this.logger = Logger.getLogger("MyLog");
                this.fh = new FileHandler("log.txt");
                logger.addHandler(fh);
                this.sf = new SimpleFormatter();
                fh.setFormatter(sf);
                information = new ArrayList<>();
                this.counter = new Counter();
                this.start();
            }
            catch (Exception e)
            {
            }
	}
	public void run()
        {
            while(true)
            {
                if(counter.getCounter() < information.size())
                {
                        write(counter.getCounter());
                        counter.setCounter(counter.getCounter()+1);
                }
            }
        }
        
        public synchronized void write(int counter)
        {
            logger.info(information.get(counter));
        }
        
        public synchronized void log (String s)
        {
            information.add(s);
	}
        
        public synchronized int getCounter()
        {
            return counter.getCounter();
        }
        
        public synchronized int length()
        {
            return information.size();
        }
        
        public synchronized String getData(int i)
        {
            return information.get(i-1);
        }
}
