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
    ArrayList<Buyer> queue = new ArrayList<Buyer>();
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
    
    //longitud
    //modificar numero de clientes
    //modificar numero de clientes totales
    //
    
}
