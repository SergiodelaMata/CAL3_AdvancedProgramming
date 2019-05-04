/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Sergio
 */
public class Queue {
    private ArrayList<String> queue = new ArrayList<String>();
    private int numBuyers;
    private int totalBuyers;
    private JTextField text;
    private Monitor monitor;

    public Queue(JTextField text, Monitor monitor) {
        this.text = text;
        this.monitor = monitor;
        this.numBuyers = 0;
        this.totalBuyers = 0;
    }
    
    public synchronized void push(String id)//To add an element to a queue
    {
        //queue.add(lengthQueue(), id);
        queue.add(id);
        this.print();
    }
    public synchronized String pop(String id)//To eliminate an element of a queue
    {
        String s = queue.get(0);
        queue.remove(0);
        this.print();
        return s;
    }
    public synchronized void remove(String id)//To eliminate an element of a queue
    {
        for(int i = 0; i < queue.size(); i++)
        {
            if(queue.get(i).equals(id))
            {
                queue.remove(i);
            }
        }
        this.print();
    }
        
    public synchronized String top()// To eliminate an element of a queue
    {
        return queue.get(0);
    }
    
    public void print()//To show the result of a queue in an instant of time at their User Interface
    {
        String textVisitors = " ";
        for(int i = 0; i < queue.size(); i++)
        {
            textVisitors+= " " + queue.get(i);
        }
        text.setText(textVisitors);
    }
    
    public int lengthQueue()//To get the actual length of a queue
    {
        return queue.size();
    }

    public void cleanQueue() //Makes the buyers leave the queue
    {
        for(int i = 0; i < queue.size(); i++)
        {
            queue.remove(0);
        }
    }
    public JTextField getTextField()
    {
        return text;
    }
    
    public synchronized String getBuyerId(int number)
    {
        return queue.get(number);
    }
}
