/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author andrea
 */
public class memoryGioco {

    public boolean[] nascosta;
    private final int campo[];
    private final int nCoppie;
    private final int nTessere;
    private ArrayList<Integer> app;

    public memoryGioco(int nCoppie) {
        nTessere = nCoppie * 2;
        this.nascosta = new boolean[nTessere];
        Arrays.fill(nascosta, false);
        this.campo = new int[nTessere];
        this.nCoppie = nCoppie;
        app = new ArrayList<>();
    }

    public void inizializza() {
        Random gen = new Random();
        int n = 0;
        for (int i = 0; i < nTessere; i++) {
            n = gen.nextInt(nCoppie) + 1;
            if (contains(n) == 2) {
                while (contains(n) == 2)n = gen.nextInt(nCoppie) + 1;
            }
            app.add(n);
            campo[i] = n;
        }

    }

    public int[] getCampo() {
        return campo;
    }

    private int contains(int n) {
        int conta = 0;
        for (int i = 0; i < nTessere; i++) {
            if (campo[i] == n)conta++;
        }return conta;
    }

    public int getpos(int x){
        for (int i = 0; i < campo.length; i++) {
            if(campo[i]==x)return i;
        }
        return 0;
    }
    @Override
    public String toString() {
        String s = "";
        int x = 0;int app = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < nCoppie; j++) {
                s += campo[x] + " ";
                x++;
            }
            if (app == 1)return s;
            s += "\n";
            app++;
        }return s;
    }

}
