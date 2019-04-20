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
public class Cashier extends Thread{
    private Monitor monitor;
    private int id;
    private boolean free;
    private String idBuyer;
    private JTextField text;
    public Cashier(int id, Monitor monitor, JTextField text)
    {
        this.monitor = monitor; 
        this.id = id;
        this.free = true;
        this.idBuyer = "";
        this.text = text;
    }
    
    public void run()
    {
        while(true)
        {
            if(monitor.isStopCashier())
            {
                monitor.makeCashierWait();
            }
            makePayment();
            monitor.setEndBuying(false);
        }
    }
    
    public synchronized void makePayment()
    {
        try
        {
            monitor.setEndBuying(true);
            monitor.setBuyerId(idBuyer, id);
            monitor.activeThread();
        }
        catch(Exception e)
        {
            
        }
    }
    
    

    public int getIdCashier()
    {
        return id;
    }

    public synchronized boolean isFree()
    {
        return free;
    }

    public synchronized void setFree(boolean free)
    {
        this.free = free;
    }

    public String getIdBuyer() {
        return idBuyer;
    }

    public synchronized void setIdBuyer(String idBuyer)
    {
        this.idBuyer = idBuyer;
    }
    
    public synchronized void printShow(String idBuyer)
    {
        text.setText(idBuyer);
    }
    
    public synchronized void printRemove()
    {
        text.setText("");
    }   
    
}
