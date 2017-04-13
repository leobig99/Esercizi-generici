/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;



/**
 *
 * @author Leonardo
 */
public class Client {
    public static void main(String[] args) throws IOException {
         Scanner t = new Scanner(System.in);
         String ip=t.nextLine();
        //creo il nuovo socket per il collegamento al server
                 //ip default "127.0.0.1"
        Socket socket = new Socket(ip,9090);
        System.out.println("collegato");
        
       
         while (true) {
             //creo l'oggeto per comunicare al clint o al socket
            PrintStream os = new PrintStream(socket.getOutputStream());
            
            //creo l'oggeto per leggiere ciò che mi ha inviato il socket
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Persona: "+is.readLine());
            System.out.println("Inserire testo da inviare");
            String invio=t.nextLine();
            os.println(invio);
            os.flush();
         }
    }
    
}
