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
public class Butcher extends Thread{
	private JTextField butcherText;
	private Semaphore sem;
    private Monitor butcherMonitor;
	private Queue butcherQueue = new Queue(butcherText, butcherMonitor);
    
	public Butcher(JTextField butcherText, Monitor butcherMonitor) {
		this.butcherText = butcherText;
		this.butcherMonitor = butcherMonitor;
	}
	
	public void enter(String s)//To make the thread enter the different queues
    {
        butcherQueue.push(s);
        try
        {
            sem.acquire();
        }
        catch(Exception e)
        {}
        butcherQueue.pop(s);
        //if(exhibitionState)
        //{
        butcherQueue.push(s);
        if(butcherMonitor.isStopThread())
        {
            butcherMonitor.waitResume();
        //}
        }
    }
	
	public void buyButcher(String s)//To be during a time in the Exhibition
    {
        try {
            Thread.sleep(1500+(int) (2500*Math.random()));
            if(butcherMonitor.isStopThread())
            {
                butcherMonitor.waitResume();
            }
        }
        catch (InterruptedException e)
        {}
    }
	
	 public void exitButcher(int v)//To make the thread go out of the Exhibition
	    {
	        if(true)//supermarketstate
	        {
	            sem.release();
	            
	        }
	        if(butcherMonitor.isStopThread())
	        {
	            butcherMonitor.waitResume();
	        }
	    }
	
	public void run() {
		
	}
}
