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
public class RevisorStateCheckArea extends Thread{
    private Monitor monitor;
    private Supermarket supermarket;
    private Counter counterTime;
    private Counter counterSize;
    private String lastFirstClientAtCheckArea;

    public RevisorStateCheckArea(Monitor monitor, Supermarket supermarket) {
        this.monitor = monitor;
        this.supermarket = supermarket;
        this.counterTime = new Counter();
        this.counterSize = new Counter();
        this.lastFirstClientAtCheckArea = "";
    }
    
    public void run()
    {
        while(true)
        {
            if(monitor.isStopThread())
            {
                monitor.waitResume();
            }
            if(supermarket.getCheckArea().lengthCheckArea() != 0 && counterSize.getCounter() == supermarket.getCheckArea().lengthCheckArea() && lastFirstClientAtCheckArea.equals(supermarket.getCheckArea().getPayQueue().getBuyerId(0)))// There is at least a buyer at the CheckArea
            {
                counterTime.setCounter(counterTime.getCounter()+1);
            }
            else if(!lastFirstClientAtCheckArea.equals(supermarket.getCheckArea().getPayQueue().getBuyerId(0)))
            {
                counterTime.setCounter(0);
                lastFirstClientAtCheckArea = supermarket.getCheckArea().getPayQueue().getBuyerId(0);
                counterSize.setCounter(supermarket.getCheckArea().lengthCheckArea());
            }
            if(monitor.isStopThread())
            {
                monitor.waitResume();
            }
            if(counterTime.getCounter() >= 10)
            {
                monitor.activeThread();
                counterTime.setCounter(0);
            }
        }
    }
    
    
}
