package cal3;


import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

public class LoggerThread extends Thread {
	
	private Logger logger;
	private FileHandler fh;
	private SimpleFormatter sf;
        private ArrayList<String> information;
        private int counter;
        private Lock lock;
        
        
	public LoggerThread()
        {
            try
            {
                this.logger = Logger.getLogger("MyLog");
                this.fh = new FileHandler("C:/Users/Sergio/Desktop/log.txt");
                logger.addHandler(fh);
                this.sf = new SimpleFormatter();
                fh.setFormatter(sf);
                information = new ArrayList<>();
                counter = 0;
                lock = new ReentrantLock();
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
                if(counter < information.size())
                {
                    //System.out.println(counter + " " + information.size());
                    try
                    {
                        lock.lock();
                        write(counter);
                        counter++;
                    }
                    finally
                    {
                        lock.unlock();
                    }
                    
                    System.out.println("Dentro " + counter);
                }
                System.out.println(counter);
            }
        }
        
        public synchronized void write(int counter)
        {
            //System.out.println("hola");
            logger.info(information.get(counter));
        }
        
        public synchronized void log (String s)
        {
            information.add(s);
            //System.out.println(information.size());
	}
        
        public synchronized int getCounter()
        {
            return counter;
        }
        
        public synchronized int length()
        {
            return information.size();
        }
        
        public synchronized String getData(int i)
        {
            return information.get(i-1);
        }

    /*private void Sleep(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
