/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllo;

/**
 *
 * @author andrea.zoccarato
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("---------------------Starting Database Creation--------------------");
        CreazioneDB crDB = new CreazioneDB();
        crDB.creaDB();
        System.out.println("---------------------Database Creation Complete---------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("---------------------Seeding Proccess Begin-------------------------");
        Seed s = new Seed();
        s.popolamentoAutomatico();
        System.out.println("---------------------Seeding Proccess Complete----------------------");
    }
}
