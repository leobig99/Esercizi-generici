/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Leonardo
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //creo il server
        ServerSocket client = new ServerSocket(9090);
        //collego il cliente al server
        System.out.println("Aspetto che un client si colleghi");
        Socket clientSocket = client.accept();
        System.out.println("collegato");
        Scanner t = new Scanner(System.in);
        while (true) {
            PrintStream os = new PrintStream(clientSocket.getOutputStream());
            System.out.println("Inserire testo da inviare");
            String invio =t.nextLine();
            os.println(invio);
            os.flush();
            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Persona: "+is.readLine());
        }

        //chiudo il cliente socket
        //clientSocket.close();
    }

}
