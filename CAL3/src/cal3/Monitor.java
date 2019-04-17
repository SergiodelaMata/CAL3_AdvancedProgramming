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
    
    
    
    public Monitor()
    {
        this.stopThread = false;
        this.stopButcher = false;
        this.stopFishmonger = false;
        this.stopCashier = false;
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
    
    
    
    public synchronized boolean isStopThread()//To know if the "Resume" Button has been activated
    {
        return stopThread;
    }

    public boolean isStopButcher() {
        return stopButcher;
    }

    public void setStopButcher(boolean stopButcher) {
        this.stopButcher = stopButcher;
    }

    public boolean isStopFishmonger() {
        return stopFishmonger;
    }

    public void setStopFishmonger(boolean stopFishmonger) {
        this.stopFishmonger = stopFishmonger;
    }

    public boolean isStopCashier() {
        return stopCashier;
    }

    public void setStopCashier(boolean stopCashier) {
        this.stopCashier = stopCashier;
    }

    public synchronized void setStopThread(boolean stopThread)//To set the value of the condition from the monitor
    {
        this.stopThread = stopThread;
    }
    public synchronized void activeThread()//To tell the threads inside the monitor to look if the "Resume" Button has been pressed
    {
        notifyAll();
    }
}
