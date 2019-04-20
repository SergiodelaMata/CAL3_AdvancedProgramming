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
public class Butcher extends Thread{
    private int id;
    private Monitor monitor;
    private boolean free;
    private String idBuyer;
    private JTextField text;
    
    public Butcher(int id, Monitor monitor, JTextField text)
    {
        this.id = id;
        this.monitor = monitor;
        this.free = true;
        this.idBuyer = "";
        this.text = text;
    }
    
    public void run()
    {
        while(true)
        {
            if(monitor.isStopButcher())
            {
                monitor.makeButcherWait();
            }
            buyButcher();
            monitor.setEndBuyingMeat(false);
        }
    }
    
    public synchronized void buyButcher()
    {
        try
        {
            monitor.setEndBuyingMeat(true);
            monitor.setBuyerId(idBuyer, id);
            monitor.activeThread();
        }
        catch(Exception e)
        {
        }
    }
    
    public int getIdButcher()
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
