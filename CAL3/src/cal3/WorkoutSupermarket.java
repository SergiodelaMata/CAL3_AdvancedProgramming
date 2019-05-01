package cal3;


import cal3.Buyer;
import cal3.LoggerThread;
import cal3.Monitor;
import cal3.Server;
import cal3.Supermarket;
import static java.lang.Thread.sleep;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sergio
 */
public class WorkoutSupermarket extends Thread{
    private Buyer buyer;
    private Supermarket supermarket;
    private Monitor monitor;
    private Server server;
    private int counter = 1;
    private LoggerThread log;
    
    public WorkoutSupermarket(LoggerThread log, Monitor monitor, Supermarket supermarket)  
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
            //System.out.println("hi");
            if(!monitor.isStopThread())
            {
                //System.out.println("Entering " + counter);
                    try
                    {
                        sleep((long) (800*Math.random() + 200));
                        buyer = new Buyer("Buyer " + counter, monitor, supermarket, 100, log);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Ready");
                    }
                //buyer = new Buyer("Buyer " + counter, monitor, supermarket, 100, log);
                counter++;
            }
            else
            {
                monitor.waitResume();
            }
        }
    }
    
    
}
