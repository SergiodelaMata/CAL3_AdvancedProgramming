/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Sergio
 */
public class Server extends Thread{
    Monitor monitor;
    public Server(Monitor monitor)
    {
        ///this.goya = goya;
        this.monitor = monitor;
        this.start();
    }
    
    public void run(){
        // TODO code application logic here
        ServerSocket server;
        Socket connection;
        DataInputStream input;
        DataOutputStream output;
        int numConnections = 0;
        
        try
        {
            server = new ServerSocket(5000);//To establish the server with a port
            System.out.println("--- Starting Server ---");
            while(true)
            {
                connection = server.accept();
                System.out.println("Connection number " + numConnections);
                
                input = new DataInputStream(connection.getInputStream());
                output = new DataOutputStream(connection.getOutputStream());
                
                String message = input.readUTF();
                System.out.println("Message " + message);
                if(message.equals("Operation: Stop Supermarket"))
                {
                    monitor.setStopThread(true);
                    output.writeUTF("OK");
                }
                else if(message.equals("Operation: Resume Supermarket"))
                {
                    monitor.setStopThread(false);
                    monitor.activeThread();
                    output.writeUTF("OK");
                }
                else if(message.equals("Operation: Stop Butcher"))
                {
                    monitor.setStopButcher(true);
                    output.writeUTF("OK");
                }
                else if(message.equals("Operation: Resume Butcher"))
                {
                    monitor.setStopButcher(false);
                    monitor.activeThread();
                    output.writeUTF("OK");
                }
                else if(message.equals("Operation: Stop Fishmonger"))
                {
                    monitor.setStopFishmonger(true);
                    output.writeUTF("OK");
                }
                else if(message.equals("Operation: Resume Fishmonger"))
                {
                    monitor.setStopButcher(false);
                    monitor.activeThread();
                    output.writeUTF("OK");
                }
                
                else//To advice if a message received wasn't expected to be received
                {
                    output.writeUTF("Error");
                }
                connection.close();
            }
        }
        catch(Exception e)
        {
            
        }
    }
}
