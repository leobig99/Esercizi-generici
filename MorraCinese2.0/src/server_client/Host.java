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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import morracinese2.pkg0.MorraCinese20;

/**
 *
 * @author Leonardo
 */
public class Host {

    ServerSocket client;
    Socket clientSocket;
    MorraCineseMultyPlayer m;

    public Host() throws IOException {
        this.client = new ServerSocket(9090);
        this.m = MorraCinese20.m;
    }

    public void Link() throws IOException {
        System.out.println("Aspetto che un client si colleghi");
        this.clientSocket = client.accept();
        System.out.println("Connessione effettuata");
        new GestoreMosseS(m, clientSocket).start();
        
    }

}





//serve per gestire le mosse del gioco
class GestoreMosseS extends Thread implements Observer{

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
    public GestoreMosseS(MorraCineseMultyPlayer morra, Socket s) {
        this.m = morra;
        this.clientSocket = s;
        mossa=false;
        mossaG1=0;
        m.setT(this);
    }
    
    
    /**
     * metodo utilizzato per inviare la mossa al socket client
     * @throws IOException 
     */
    public void invioMossa() throws IOException {
        PrintStream os = new PrintStream(clientSocket.getOutputStream());
        os.println(this.mossaG1);
        os.flush();
    }

    
    /**
     * metodod utilizzato per ricevere la mossa dal socket client
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
