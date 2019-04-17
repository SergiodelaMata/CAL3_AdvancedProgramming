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
public class Buyer extends Thread{
    String idBuyer;
    Supermarket supermarket;
    Monitor monitor;

    public Buyer(String idBuyer, Monitor monitor, Supermarket supermarket) {
        this.idBuyer = idBuyer;
        this.monitor = monitor;
        this.supermarket = supermarket;
        this.run();
    }
    
    public void run()
    {
        
    }
    
}
