/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class Buyer extends Thread{
    private String idBuyer;
    private Supermarket supermarket;
    private Monitor monitor;
    private int maximumClientNumber;
    private LoggerThread log;

    public Buyer(String idBuyer, Monitor monitor, Supermarket supermarket, int maximumClientNumber, LoggerThread log) {
        this.idBuyer = idBuyer;
        this.monitor = monitor;
        this.supermarket = supermarket;
        this.maximumClientNumber = maximumClientNumber;
        this.log = log;
        this.start();
    }
    
    public void run()
    {
        //int randNumber;
        int randNumber;
        long enterTime = 0;
        boolean attended = false;
        long initialTime = System.currentTimeMillis();
        supermarket.enterOutsideQueue(idBuyer);
        if(!monitor.isComplete())
        {
            enterTime = System.currentTimeMillis();
            supermarket.enterSupermarket(idBuyer);
            randNumber = (int) (Math.random()*3);
            if(!monitor.isComplete())
            {
                switch (randNumber) {
                    case 0:
                        //goButcherShop(idBuyer);
                        attended = supermarket.buyButcher(idBuyer);
                        break;
                    case 1:
                        //goFishmongerShop(idBuyer);
                        attended = supermarket.buyFishmonger(idBuyer);
                        break;
                    default:
                        supermarket.goItemShelves(idBuyer);
                        attended = true;
                        break;
                }
            }
        }
        else
        {
            supermarket.goOutOutsideQueue(idBuyer);
        }
        if(attended)
        {
            supermarket.payShopping(idBuyer);
        }    
        supermarket.exitSupermarket(idBuyer);
        
        //supermarket.shopping(idBuyer);
        long finalTime = System.currentTimeMillis();
        if(enterTime != 0)
        {
            supermarket.addEnterAndLeaveTime(finalTime - enterTime);
        }
        supermarket.addWaitOutsideQueueAndLeaveTime(finalTime - initialTime);
        //System.out.println(supermarket.getCounterBuyerExit().getCounter() + " " + maximumClientNumber);
        if(supermarket.getCounterBuyerExit().getCounter() >= maximumClientNumber)
        {
            log.log("");
            log.log("Butcher shop average service time: " + supermarket.counterAverageServiceTime(supermarket.getTimeServiceButcherList()) + "ms");
            log.log("Fish shop average service time: " + supermarket.counterAverageServiceTime(supermarket.getTimeServiceFishmongerList()) + "ms");
            log.log("Accumulation times at the butcher shop: " + supermarket.getAccumulationTimesButcher().getCounter());
            log.log("Accumulation times at the fish shop: " + supermarket.getAccumulationTimesFishmonger().getCounter());
            log.log("Total number of people who have entered the supermarket: " + supermarket.getCounterBuyerEnter().getCounter());
            log.log("Total number of people who have left the supermarket: " + supermarket.getCounterBuyerExit().getCounter());
            log.log("Average time from a buyer since he/she arrives at the outside queue until he/she leaves the supermarket: " + supermarket.counterAverageServiceTime(supermarket.getTimeWaitOutsideQueueAndLeaveList()) + "ms");
            log.log("Average time from a buyer since he/she enters the supermarket until he/she leaves the supermarket: " + supermarket.counterAverageServiceTime(supermarket.getTimeEnterAndLeaveList()) + "ms");
            /*while(log.getCounter() < log.length())
            {
                System.out.println(log.getCounter() + " de " + log.length());
                //System.out.print(log.getData(log.getCounter()));
            }*/
        }
        
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
}
