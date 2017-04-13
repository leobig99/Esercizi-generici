/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_client;

import ModelloMorraCinese.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import morracinese2.pkg0.MorraCinese20;

/**
 *
 * @author Leonardo
 */
public class client {
    protected String ip;
    protected Scanner t;
    protected Socket socket;
    protected MorraCineseMultyPlayer m;
    
    public client(){
        this.ip="127.0.0.1";
        this. t = new Scanner(System.in);
        this.m = MorraCinese20.m;
    }
    
    
    
    
    public void Link() throws IOException {
        //da convertire in grafica se si vuole
        System.out.println("digitare ip del server");
        ip=t.nextLine();
        //creo il nuovo socket per il collegamento al server
        //ip default "127.0.0.1"
        socket = new Socket(ip,9090);
        System.out.println("Connessione effettuata");
        new GestoreMosseC(m, socket).start();
    }
    
    
    
}



//serve per gestire le mosse del gioco
class GestoreMosseC extends Thread implements Observer{

    MorraCineseMultyPlayer m;
    Socket clientSocket;
    boolean mossa;
    int mossaG1;

    
    
    /**
     * 
     * costruttore del thread
     * 
     * @param morra
     * @param s 
     */
    public GestoreMosseC(MorraCineseMultyPlayer morra, Socket s) {
        this.m = morra;
        this.clientSocket = s;
        mossa=false;
        mossaG1=0;
        m.setT(this);
    }
    
    
    /**
     * metodo utilizzato per inviare la mossa al socket server
     * @throws IOException 
     */
    public void invioMossa() throws IOException {
        PrintStream os = new PrintStream(clientSocket.getOutputStream());
        os.println(mossaG1);
        os.flush();
    }

    
    /**
     * metodod utilizzato per ricevere la mossa dal socket server
     * @return
     * @throws IOException 
     */
    public int riceviMossa() throws IOException {
        BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return is.read();

    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {
        mossaG1=m.getMossaG1();
        mossa=true;
        
    }
    

    @Override
    public void run() {
        while (true) {
            if (mossa) {
                mossa=false;
                try {
                    this.invioMossa();
                    this.m.setMossaG2(riceviMossa());
                } catch (IOException ex) {
                }
            }

        }

    }

    

}

