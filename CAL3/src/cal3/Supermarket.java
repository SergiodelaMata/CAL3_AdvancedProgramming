/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author Sergio
 */
public class Supermarket {
    Monitor monitor;
    Semaphore semOutsideQueue;
    Semaphore semButcherQueue;
    Semaphore semFishmongerQueue;
    Semaphore semCheckAreaQueue;
    Queue outsideQueue, butcherQueue, fishmongerQueue;
    CheckArea checkArea;
    
    public Supermarket(JTextField buyerButcher,JTextField buyerFishmonger,JTextField butcherQueue, JTextField fishmongerQueue, JTextField buyersShelves, JTextField buyerCashier1, JTextField buyerCashier2, JTextField checkAreaQueue, JTextField outsideQueue,Monitor monitor)
    {
        this.monitor = monitor;
        this.butcherQueue = new Queue(butcherQueue, monitor);
        this.fishmongerQueue = new Queue(fishmongerQueue, monitor);
        this.outsideQueue = new Queue(outsideQueue, monitor);
        this.checkArea = new CheckArea(monitor, checkAreaQueue);
        this.semOutsideQueue = new Semaphore(20, true);
        this.semButcherQueue = new Semaphore(1, true);
        this.semFishmongerQueue = new Semaphore(1, true);
        this.semCheckAreaQueue = new Semaphore(1, true);
    }
    
    public void enterSupermaret(String idBuyer)
    {
        
        
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
    }
    
    public void exitSupermarket(String idBuyer)
    {
        
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
    }
    
    public void payShopping(String idBuyer)
    {
        
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
    }
    
    public void goFishShop(String idBuyer)
    {
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
    }
    
    public void goItemShelves(String idBuyer)
    {
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
    }
}
