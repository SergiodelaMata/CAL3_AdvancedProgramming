/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

/**
 *
 * @author Sergio
 */
public class Monitor {
    private boolean stopThread;
    private boolean stopButcher;
    private boolean stopFishmonger;
    private boolean stopCashier;
    private boolean endBuyingMeat;
    private boolean endBuyingFish;
    private boolean endBuying;
    
    
    public Monitor()
    {
        this.stopThread = false;
        this.stopButcher = false;
        this.stopFishmonger = false;
        this.stopCashier = false;
        this.endBuyingMeat = false;
        this.endBuyingFish = false;
        this.endBuying = false;
    }

    public synchronized void waitResume()//To make the threads wait until the resume Button is pressed
    {
        try
        {
            while(stopThread)
            {
                wait();
            }
        }
        catch(Exception e)
        {
        }
    }
    public synchronized void waitButcher()
    {
        while(stopButcher)
        {
            try
            {
                wait();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public synchronized void waitFishmonger()
    {
        while(stopFishmonger)
        {
            try
            {
                wait();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public synchronized void waitCashier()
    {
        while(stopCashier)
        {
            try
            {
                wait();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public synchronized void waitBuyingMeat()
    {
        while(endBuyingMeat)
        {
            try
            {
                wait();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public synchronized void waitBuyingFish()
    {
        while(endBuyingFish)
        {
            try
            {
                wait();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public synchronized void waitEndBuying()
    {
        while(endBuying)
        {
            try
            {
                wait();
            }
            catch(Exception e)
            {
                
            }
        }
    }
    
    public synchronized boolean isStopThread()//To know if the "Resume" Button has been activated
    {
        return stopThread;
    }
    
    public synchronized void setStopThread(boolean stopThread)//To set the value of the condition from the monitor
    {
        this.stopThread = stopThread;
    }

    public synchronized boolean isStopButcher() {
        return stopButcher;
    }

    public synchronized void setStopButcher(boolean stopButcher) {
        this.stopButcher = stopButcher;
    }

    public synchronized boolean isStopFishmonger() {
        return stopFishmonger;
    }

    public synchronized void setStopFishmonger(boolean stopFishmonger) {
        this.stopFishmonger = stopFishmonger;
    }

    public synchronized boolean isStopCashier() {
        return stopCashier;
    }

    public synchronized void setStopCashier(boolean stopCashier) {
        this.stopCashier = stopCashier;
    }

    public synchronized boolean isEndBuyingMeat() {
        return endBuyingMeat;
    }

    public synchronized void setEndBuyingMeat(boolean endBuyingMeat) {
        this.endBuyingMeat = endBuyingMeat;
    }

    public synchronized boolean isEndBuyingFish() {
        return endBuyingFish;
    }

    public synchronized void setEndBuyingFish(boolean endBuyingFish) {
        this.endBuyingFish = endBuyingFish;
    }

    public synchronized boolean isEndBuying() {
        return endBuying;
    }

    public synchronized void setEndBuying(boolean endBuying) {
        this.endBuying = endBuying;
    }
    
    public synchronized void activeThread()//To tell the threads inside the monitor to look if the "Resume" Button has been pressed
    {
        notifyAll();
    }
}
