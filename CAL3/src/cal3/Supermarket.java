/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Sergio
 */
public class Supermarket {
    Monitor monitor;
    Semaphore semOutsideQueue;
    Semaphore semButcherQueue;
    Semaphore semFishmongerQueue;
    Semaphore semCheckAreaQueue;
    
    public Supermarket(Monitor monitor)
    {
        this.monitor = monitor;
    }
}
