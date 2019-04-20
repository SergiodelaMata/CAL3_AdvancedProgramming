/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import javax.swing.JTextField;

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
    private boolean butcherWait;
    private boolean fishmongerWait;
    private boolean cashierWait;
    private String [] buyerIdList = {"", ""};
    
    
    public Monitor()
    {
        this.stopThread = false;
        this.stopButcher = false;
        this.stopFishmonger = false;
        this.stopCashier = false;
        this.endBuyingMeat = false;
        this.endBuyingFish = false;
        this.endBuying = false;
        this.butcherWait = true;
        this.fishmongerWait = true;
        this.cashierWait = true;
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
    
    public synchronized void waitEndBuying(String idBuyer, int idCashier)
    {
        while(!endBuying && buyerIdList[idCashier] == idBuyer)
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
    
    
    
    public synchronized void waitIfDifferentId(String idBuyer, Queue queue, Counter counter)
    {
        try
        {
            
            while(!idBuyer.equals(topText(queue.getTextField())) || counter.getCounter() == 5)
            {
                wait();
            }
        }
        catch(Exception e)
        {
            
        }
        System.out.println(idBuyer);
    }
    
    public synchronized String topText(JTextField text)
    {
        String idBuyer  = "";
        char [] idBuyers = (text.getText()).toCharArray();
        //System.out.println(idBuyers);
        int i = 2;
        if(idBuyers.length == 0)
        {
            idBuyer = "-1";
        }
        else
        {
            while(idBuyers[i] != ' ' || idBuyers[i-1] == 'r')
            {
                //System.out.println(i + " " + idBuyers[i]);
                idBuyer += idBuyers[i];
                i++;
            }
        }
        //System.out.println(idBuyer);
        return idBuyer;
    }
    
    
    public synchronized void makeButcherWait()
    {
        while(butcherWait)
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
    
    public synchronized void makeFishmongerWait()
    {
        while(fishmongerWait)
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
    
    public synchronized void makeCashierWait()
    {
        while(cashierWait)
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

    public synchronized boolean isButcherWait() {
        return butcherWait;
    }

    public synchronized void setButcherWait(boolean butcherWait) {
        this.butcherWait = butcherWait;
    }

    public synchronized boolean isFishmongerWait() {
        return fishmongerWait;
    }

    public synchronized void setFishmongerWait(boolean fishmongerWait) {
        this.fishmongerWait = fishmongerWait;
    }

    public synchronized boolean isCashierWait() {
        return cashierWait;
    }

    public synchronized void setCashierWait(boolean cashierWait) {
        this.cashierWait = cashierWait;
    }
    
    public synchronized void setBuyerId(String buyerId, int id) {
        this.buyerIdList[id] = buyerId;
    }

    
    public synchronized void activeThread()//To tell the threads inside the monitor to look if the "Resume" Button has been pressed
    {
        notifyAll();
    }
}
