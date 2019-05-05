package cal3;


import static java.lang.Thread.sleep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sergio
 */
public class OperationSupermarket extends Thread{
    private Buyer buyer;
    private Supermarket supermarket;
    private Monitor monitor;
    private Server server;
    private int counter = 1;
    private LoggerThread log;
    
    public OperationSupermarket(LoggerThread log, Monitor monitor, Supermarket supermarket)  
    {
        this.log = log;
        this.supermarket = supermarket;
        this.monitor = monitor;
        this.server = new Server(monitor);
        this.start();
    }
    public void run()
    {
        while(counter <= 100)
        {
            if(!monitor.isStopThread())
            {
                    try
                    {
                        sleep((long) (800*Math.random() + 200));
                        buyer = new Buyer("Buyer " + counter, monitor, supermarket, 100, log);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Ready");
                    }
                counter++;
            }
            else
            {
                monitor.waitResume();
            }
        }
    }
}
