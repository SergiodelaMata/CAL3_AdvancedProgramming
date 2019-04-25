/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.start();
    }
    
    public void run()
    {
        //int randNumber;
        
        
        supermarket.shopping(idBuyer);
        
        //supermarket.enterSupermarket(idBuyer);
        
        /*try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Buyer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        /*randNumber = (int) (Math.random()*3);
        if(randNumber == 0)
        {
            supermarket.goButcherShop(idBuyer);
        }
        else if(randNumber == 1)
        {
            supermarket.goFishShop(idBuyer);
        }
        else
        {
            supermarket.goItemShelves(idBuyer);
        }
        try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Buyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        supermarket.payShopping(idBuyer);*/
        /*try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Buyer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        /*supermarket.exitSupermarket(idBuyer);*/
    }

    private void Sleep(double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
