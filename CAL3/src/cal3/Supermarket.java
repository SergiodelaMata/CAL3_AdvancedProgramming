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
 * @author Sergio, Manuel
 */
public class Supermarket {
    private Monitor monitor;
    private Semaphore semOutsideQueue, semButcherQueue, semFishmongerQueue, semCheckAreaQueue;
    private Queue outsideQueue, butcherQueue, fishmongerQueue, itemShelvesQueue;
    private CheckArea checkArea;
    private Cashier cashier1, cashier2;
    private Butcher butcher;
    private Fishmonger fishmonger;
    private Lock lock;
    private Counter insideBuyerCounter, butcherAttendingCounter, fishmongerAttendingCounter, cashierAttendingCounter;
    private LoggerThread log;
    
    public Supermarket(JTextField buyerButcher,JTextField buyerFishmonger,JTextField butcherQueue, JTextField fishmongerQueue, JTextField buyersShelves, JTextField buyerCashier1, JTextField buyerCashier2, JTextField checkAreaQueue, JTextField outsideQueueField,Monitor monitor)
    {
        this.monitor = monitor;
        this.butcherQueue = new Queue(butcherQueue, monitor);
        this.fishmongerQueue = new Queue(fishmongerQueue, monitor);
        this.outsideQueue = new Queue(outsideQueueField, monitor);
        this.itemShelvesQueue = new Queue(buyersShelves, monitor);
        this.checkArea = new CheckArea(monitor, checkAreaQueue);
        this.semOutsideQueue = new Semaphore(20, true);
        this.semButcherQueue = new Semaphore(1, true);
        this.semFishmongerQueue = new Semaphore(1, true);
        this.semCheckAreaQueue = new Semaphore(2, true);
        this.cashier1 = new Cashier(1, monitor, buyerCashier1);
        this.cashier2 = new Cashier(2, monitor, buyerCashier2);
        this.butcher = new Butcher(0, monitor, buyerButcher);
        this.fishmonger = new Fishmonger(3, monitor, buyerFishmonger);
        this.lock =  new ReentrantLock();
        this.insideBuyerCounter = new Counter();
        this.butcherAttendingCounter = new Counter();
        this.fishmongerAttendingCounter = new Counter();
        this.cashierAttendingCounter = new Counter();
        this.log = new LoggerThread();
    }
    
    public void shopping(String idBuyer)
    {
        int randNumber;
        boolean attended = false;
        enterSupermarket(idBuyer);
        lock.lock();
        try {
            randNumber = (int) (Math.random()*3);
        } finally {
            lock.unlock();
        }
        if(!monitor.isComplete())
        {
            switch (randNumber) {
                case 0:
                    //goButcherShop(idBuyer);
                    attended = buyButcher(idBuyer);
                    break;
                case 1:
                    //goFishmongerShop(idBuyer);
                    attended = buyFishmonger(idBuyer);
                    break;
                default:
                    goItemShelves(idBuyer);
                    attended = true;
                    break;
            }
        }
        if(attended)
        {
            payShopping(idBuyer);
        }    
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
            monitor.waitIfDifferentIdBuyerEnterSupermarket(idBuyer, outsideQueue, insideBuyerCounter);
            if(!monitor.isComplete())
            {
                semOutsideQueue.acquire();
                insideBuyerCounter.setCounter(insideBuyerCounter.getCounter()+1);
                //System.out.println("hola 2 " + idBuyer);
                if(monitor.isStopThread())
                {
                    monitor.waitResume();
                }
                outsideQueue.pop(idBuyer);
                log.log(idBuyer + " enters the supermarket");
                monitor.activeThread();
                //System.out.println("hola 3 " + idBuyer);
            }
            else
            {
                outsideQueue.pop(idBuyer);
            }
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
        log.log(idBuyer + " exits the supermarket");
        insideBuyerCounter.setCounter(insideBuyerCounter.getCounter()-1);//May need to be changed
        if(!monitor.isComplete())
        {
            semOutsideQueue.release();
        }
        monitor.activeThread();        
    }
    
    public void payShopping(String idBuyer)
    {
        checkArea.push(idBuyer);
        log.log(idBuyer + " waits at the checkout area");
        try
        {
            //sleep(2000);
            monitor.waitIfDifferentIdBuyerEnterCashier(idBuyer, checkArea.getPayQueue(), cashierAttendingCounter);
            semCheckAreaQueue.acquire();
            if(monitor.isStopThread())
            {
                monitor.waitResume();
            }
            checkArea.pop(idBuyer);
            cashierAttendingCounter.setCounter(cashierAttendingCounter.getCounter()+1);
            monitor.activeThread();
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
            if(monitor.isStopThread())
            {
                monitor.waitResume();
            }
            
            if(monitor.isCashierWait())
            {
                monitor.setCashierWait(false);
                
                if(checkArea.lengthCheckArea() == 0)
                {
                    monitor.activeThread();
                    monitor.setCashierWait(true);
                }
                else
                {
                    monitor.activeThread();
                }
                
            }
            else
            {
                if(checkArea.lengthCheckArea() == 0)
                {
                    monitor.setCashierWait(true);
                }
            }
            if(monitor.isStopThread())
            {
                monitor.waitResume();
            }
            if(cashier1.getIdBuyer().equals(idBuyer))
            {
                log.log("Cashier 1 charges " + idBuyer);
                sleep((long)(Math.random()*3000 + 2000));
                monitor.waitEndBuying(idBuyer, cashier1.getIdCashier());
                if(monitor.isStopThread())
                {
                    monitor.waitResume();
                }
                cashier1.printRemove();
                cashier1.setFree(true);
                cashier1.setIdBuyer("");
                //System.out.println("Cashier 1: " + cashier1.isFree());
            }
            else if(cashier2.getIdBuyer().equals(idBuyer))
            {
                log.log("Cashier 2 charges " + idBuyer);
                sleep((long)(Math.random()*3000 + 2000));
                monitor.waitEndBuying(idBuyer, cashier2.getIdCashier());
                if(monitor.isStopThread())
                {
                    monitor.waitResume();
                }
                cashier2.printRemove();
                cashier2.setFree(true);
                cashier2.setIdBuyer("");
                //System.out.println("Cashier 2: " + cashier2.isFree());
            }
            
        }
        catch(Exception e)
        {}
        cashierAttendingCounter.setCounter(cashierAttendingCounter.getCounter()-1);
        monitor.activeThread();
        //System.out.println(idBuyer + " finished buying");
        semCheckAreaQueue.release();
    }
    
    /**public void goButcherShop(String idBuyer)// Enter butcher´s
	{
		butcherQueue.push(idBuyer);
                log.log(idBuyer + " waits at the butcher shop");
		if (monitor.isStopThread()) {
			monitor.waitResume();
		}
		// System.out.println(idBuyer + " Butcher");
		butcherQueue.remove(idBuyer);
	}*/
    
    public boolean buyButcher(String idBuyer) {
        boolean attended = false;
        butcherQueue.push(idBuyer);
        log.log(idBuyer + " waits at the butcher shop");
        monitor.waitIfDifferentIdBuyerButcher(idBuyer, butcherQueue, butcherAttendingCounter);
        if(!monitor.isComplete())
        {
            try {
                semButcherQueue.acquire();
                if (monitor.isStopThread())
                {
                    monitor.waitResume();
                }
                butcherQueue.pop(idBuyer);
                butcherAttendingCounter.setCounter(butcherAttendingCounter.getCounter() + 1);
                monitor.activeThread();
                attended = true;
                if (butcher.isFree())
                {
                    butcher.setFree(false);
                    butcher.setIdBuyer(idBuyer);
                    butcher.printShow(idBuyer);
                }
                if (monitor.isButcherWait())
                {
                    monitor.setButcherWait(false);
                    monitor.activeThread();
                }
                if(monitor.isStopButcher())
                {
                    monitor.waitButcher();
                }
                if (monitor.isStopThread())
                {
                    monitor.waitResume();
                }
                if(monitor.isStopButcher())
                {
                    monitor.waitButcher();
                }
                if (butcher.getIdBuyer().equals(idBuyer))
                {
                    log.log("Butcher attends " + idBuyer);
                    sleep((long) (Math.random() * 2500 + 1500));
                    monitor.waitBuyingMeat();
                    if(monitor.isStopButcher())
                    {
                        monitor.waitButcher();
                    }
                    if(monitor.isStopThread())
                    {
                        monitor.waitResume();
                    }
                    if(monitor.isStopButcher())
                    {
                        monitor.waitButcher();
                    }
                    butcher.printRemove();
                    butcher.setFree(true);
                    butcher.setIdBuyer("");
                }
            } catch (Exception e)
            {}
            if (monitor.isStopThread())
            {
                monitor.waitResume();
            }
            butcherAttendingCounter.setCounter(butcherAttendingCounter.getCounter() - 1);
            monitor.activeThread();
            semButcherQueue.release();
        }
        else
        {
            butcherQueue.pop(idBuyer);
        }
        return attended;
    }
    
    /**public void goFishmongerShop(String idBuyer)// Enter fishmonger´s
    {
        fishmongerQueue.push(idBuyer);
        log.log(idBuyer + " waits at the fishmonger shop");
        if (monitor.isStopThread()) {
            monitor.waitResume();
        }
        fishmongerQueue.remove(idBuyer);
    }*/
    
    public boolean buyFishmonger(String idBuyer)
    {
        boolean attended = false;
        fishmongerQueue.push(idBuyer);
        log.log(idBuyer + " waits at the fishmonger shop");
        monitor.waitIfDifferentIdBuyerEnterFishmonger(idBuyer, fishmongerQueue, fishmongerAttendingCounter);
        if(!monitor.isComplete())
        {
            try {
                semFishmongerQueue.acquire();
                if (monitor.isStopThread()) {
                    monitor.waitResume();
                }
                fishmongerQueue.pop(idBuyer);
                fishmongerAttendingCounter.setCounter(fishmongerAttendingCounter.getCounter() + 1);
                monitor.activeThread();
                attended = true;
                if (fishmonger.isFree())
                {
                    fishmonger.setFree(false);
                    fishmonger.setIdBuyer(idBuyer);
                    fishmonger.printShow(idBuyer);
                }
                if (monitor.isFishmongerWait())
                {
                    monitor.setFishmongerWait(false);
                    monitor.activeThread();
                }
                if(monitor.isStopFishmonger())
                {
                    monitor.waitButcher();
                }
                if (monitor.isStopThread())
                {
                    monitor.waitResume();
                }
                if(monitor.isStopFishmonger())
                {
                    monitor.waitButcher();
                }
                if (fishmonger.getIdBuyer().equals(idBuyer))
                {
                    log.log("Fishmonger attends " + idBuyer);
                    sleep((long) (Math.random() * 2000 + 3000));
                    monitor.waitBuyingFish();
                    if(monitor.isStopFishmonger())
                    {
                        monitor.waitButcher();
                    }
                    if(monitor.isStopThread())
                    {
                        monitor.waitResume();
                    }
                    if(monitor.isStopFishmonger())
                    {
                        monitor.waitButcher();
                    }
                    fishmonger.printRemove();
                    fishmonger.setFree(true);
                    fishmonger.setIdBuyer("");
                }
            } catch (Exception e)
            {}
            if (monitor.isStopThread())
            {
                monitor.waitResume();
            }
            fishmongerAttendingCounter.setCounter(fishmongerAttendingCounter.getCounter() - 1);
            monitor.activeThread();
            semFishmongerQueue.release();
        }
        else
        {
            fishmongerQueue.pop(idBuyer);
        }
        return attended;
    }
    
    public void goItemShelves(String idBuyer)
    {
        itemShelvesQueue.push(idBuyer);
        try {
            log.log(idBuyer + " picks items from the item shelves");
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
