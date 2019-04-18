/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Sergio
 */
public class Queue {
	ArrayList<String> queue = new ArrayList<String>();
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

	public synchronized void push(String id)// To add an element to a queue
	{
		queue.add(id);
		this.print();
	}

	public synchronized String pop()// To eliminate an element of a queue
	{
		String s = queue.get(0);
		queue.remove(0);

		return s;
	}
	
	public synchronized String top()// To eliminate an element of a queue
	{
		return queue.get(0);
	}

	public void print()// To show the result of a queue in an instant of time at their User Interface
	{
		String textVisitors = " ";
		for (int i = 0; i < queue.size(); i++) {
			textVisitors = textVisitors + " " + queue.get(i);
		}
		text.setText(textVisitors);
	}

	public int lengthQueue()// To get the actual length of a queue
	{
		return queue.size();
	}

	public void cleanQueue() // Makes the buyers leave the queue
	{
		for (int i = 0; i < queue.size(); i++) {
			queue.remove(0);
		}
	}

}
