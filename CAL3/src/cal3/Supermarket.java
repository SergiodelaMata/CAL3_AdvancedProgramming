/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Sergio
 */
public class Supermarket {
    private Monitor monitor;
    private Semaphore semOutsideQueue, semButcherQueue, semFishmongerQueue, semCheckAreaQueue;
    private Queue outsideQueue, butcherQueue, fishmongerQueue, itemShelvesQueue;
    private CheckArea checkArea;
    private Cashier cashier1, cashier2;
    private Lock lock;
    private Counter insideBuyerCounter;
    
    public Supermarket(JTextField buyerButcher,JTextField buyerFishmonger,JTextField butcherQueue, JTextField fishmongerQueue, JTextField buyersShelves, JTextField buyerCashier1, JTextField buyerCashier2, JTextField checkAreaQueue, JTextField outsideQueueField,Monitor monitor)
    {
        this.monitor = monitor;
        this.butcherQueue = new Queue(butcherQueue, monitor);
        this.fishmongerQueue = new Queue(fishmongerQueue, monitor);
        this.outsideQueue = new Queue(outsideQueueField, monitor);
        this.itemShelvesQueue = new Queue(buyersShelves, monitor);
        this.checkArea = new CheckArea(monitor, checkAreaQueue);
        this.semOutsideQueue = new Semaphore(5, true);
        this.semButcherQueue = new Semaphore(1, true);
        this.semFishmongerQueue = new Semaphore(1, true);
        this.semCheckAreaQueue = new Semaphore(2, true);
        this.cashier1 = new Cashier(1, monitor, buyerCashier1);
        this.cashier2 = new Cashier(2, monitor, buyerCashier2);
        this.lock =  new ReentrantLock();
        this.insideBuyerCounter = new Counter();
    }
    
    public void shopping(String idBuyer)
    {
        int randNumber;
        enterSupermarket(idBuyer);
        lock.lock();
        try {
            randNumber = (int) (Math.random()*3);
        } finally {
            lock.unlock();
        }
        if(randNumber == 0)
        {
            goButcherShop(idBuyer);
        }
        else if (randNumber == 1)
        {
            goFishShop(idBuyer);
        }
        else
        {
            goItemShelves(idBuyer);
        }
        payShopping(idBuyer);
        exitSupermarket(idBuyer);
        
    }
    public int selectOperation()
    {
        return (int) (Math.random()*3);
    }
    
    
    public void enterSupermarket(String idBuyer)
    {
        outsideQueue.push(idBuyer);                
        try
        {
            
            //System.out.println("hola 1");
            monitor.waitIfDifferentId(idBuyer, outsideQueue, insideBuyerCounter);
            semOutsideQueue.acquire();
            insideBuyerCounter.setCounter(insideBuyerCounter.getCounter()+1);
            //System.out.println("hola 2 " + idBuyer);
            outsideQueue.pop(idBuyer);
            monitor.activeThread();
            //System.out.println("hola 3 " + idBuyer);
        }
        catch(Exception e)
        {}
        
        //System.out.println(idBuyer);
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
        //insideBuyerCounter.setCounter(insideBuyerCounter.getCounter()-1);
        //semOutsideQueue.release();
        
    }
    
    public void payShopping(String idBuyer)
    {
        checkArea.push(idBuyer);
        //int idCashier;
        //System.out.println("Ready");
        //checkArea.pop(idBuyer);
        try
        {
            //sleep(2000);
            semCheckAreaQueue.acquire();
            checkArea.pop(idBuyer);
            /*if(cashier1.isFree() && cashier2.isFree())
            {
                cashier1.setFree(false);
                cashier1.setIdBuyer(idBuyer);
                cashier1.printShow(idBuyer);
            }*/
            if(cashier1.isFree())
            {
                cashier1.setFree(false);
                cashier1.setIdBuyer(idBuyer);
                cashier1.printShow(idBuyer);
            }
            else if(cashier2.isFree())
            {
                cashier2.setFree(false);
                cashier2.setIdBuyer(idBuyer);
                cashier2.printShow(idBuyer);
            }
            
            if(monitor.isCashierWait())
            {
                monitor.setCashierWait(false);
                
                if(checkArea.lengthCheckArea() == 0)
                {
                    monitor.notify();
                    monitor.setCashierWait(true);
                }
                else
                {
                    monitor.notifyAll();
                }
                
            }
            else
            {
                if(checkArea.lengthCheckArea() == 0 || checkArea.lengthCheckArea() == 1)
                {
                    monitor.setCashierWait(true);
                }
            }
            
            if(cashier1.getIdBuyer().equals(idBuyer))
            {
                monitor.waitEndBuying(idBuyer, 0);
                cashier1.printRemove();
                cashier1.setFree(true);
                cashier1.setIdBuyer("");
            }
            else if(cashier2.getIdBuyer().equals(idBuyer))
            {
                monitor.waitEndBuying(idBuyer, 1);
                cashier2.printRemove();
                cashier2.setFree(true);
                cashier2.setIdBuyer("");
            }
        }
        catch(Exception e)
        {}
        
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        
        //semCheckAreaQueue.release();
        //semOutsideQueue.release();
    }
    
    public void goButcherShop(String idBuyer)
    {
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        //System.out.println(idBuyer + " Butcher");
    }
    
    public void goFishShop(String idBuyer)
    {
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        //System.out.println(idBuyer + " Fishmonger");
    }
    
    public void goItemShelves(String idBuyer)
    {
        itemShelvesQueue.push(idBuyer);
        try {
            sleep((long)(Math.random()*11000 + 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Supermarket.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(monitor.isStopThread())
        {
            monitor.waitResume();
        }
        itemShelvesQueue.remove(idBuyer);
        //System.out.println(idBuyer + " Items");
    }
}
