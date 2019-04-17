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
public class CheckArea {
    Queue payQueue;

    public CheckArea(Monitor monitor, JTextField text) {
        this.payQueue = new Queue(text, monitor);
    }
    
    
    public int lengthCheckArea()
    {
        return payQueue.lengthQueue();
    }
    
}
