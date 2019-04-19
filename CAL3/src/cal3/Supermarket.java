/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author Sergio
 */
public class Supermarket {
    private Monitor monitor;
    private Semaphore semOutsideQueue, semButcherQueue, semFishmongerQueue, semCheckAreaQueue;
    private Queue outsideQueue, butcherQueue, fishmongerQueue;
    private CheckArea checkArea;
    
    public Supermarket(JTextField buyerButcher,JTextField buyerFishmonger,JTextField butcherQueue, JTextField fishmongerQueue, JTextField buyersShelves, JTextField buyerCashier1, JTextField buyerCashier2, JTextField checkAreaQueue, JTextField outsideQueueField,Monitor monitor)
    {
        this.monitor = monitor;
        this.butcherQueue = new Queue(butcherQueue, monitor);
        this.fishmongerQueue = new Queue(fishmongerQueue, monitor);
        this.outsideQueue = new Queue(outsideQueueField, monitor);
        this.checkArea = new CheckArea(monitor, checkAreaQueue);
        this.semOutsideQueue = new Semaphore(20);
        this.semButcherQueue = new Semaphore(1);
        this.semFishmongerQueue = new Semaphore(1);
        this.semCheckAreaQueue = new Semaphore(1);
    }
    
    public void enterSupermarket(String idBuyer)
    {
        outsideQueue.push(idBuyer);
        try
        {
            semOutsideQueue.acquire();
        }
        catch(Exception e)
        {}
        outsideQueue.pop(idBuyer);
        
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
    }
    
    public synchronized void exitSupermarket(String idBuyer)
    {
        
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        semOutsideQueue.release();
        
    }
    
    public void payShopping(String idBuyer)
    {
        checkArea.pop(idBuyer);
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
    }
    
    public void goButcherShop(String idBuyer)
    {
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        checkArea.push(idBuyer);
    }
    
    public void goFishShop(String idBuyer)
    {
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        checkArea.push(idBuyer);
    }
    
    public void goItemShelves(String idBuyer)
    {
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        checkArea.push(idBuyer);
    }
}
