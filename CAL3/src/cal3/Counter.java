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
public class Counter {
    private int counter;

    public Counter() {
        counter = 0;
    }

    public synchronized int getCounter() {
        return counter;
    }

    public synchronized void setCounter(int counter) {
        this.counter = counter;
    }
    
    public synchronized void setCounter(int counter, JTextField text) {
        this.counter = counter;
        text.setText(Integer.toString(counter));
    }
    
    
}
