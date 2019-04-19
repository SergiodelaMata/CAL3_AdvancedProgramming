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
        this.start();
    }
    
    public void run()
    {
        int randNumber = (int) (Math.random()*3);
        supermarket.enterSupermarket(idBuyer);
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
        supermarket.payShopping(idBuyer);
        supermarket.exitSupermarket(idBuyer);
    }
    
}
