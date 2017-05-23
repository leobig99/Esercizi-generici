package provasingola.marcotramontini.com.bigetti170523;

import java.util.Arrays;
import java.util.Random;



public class Parola {

    protected String parola="";
    protected boolean[] viste;
    protected int tentativi=10;
    protected int  lunghezza=4;
    protected boolean vittoria=false;

    protected String [] s={"mela", "casa","ciao",};

    public Parola() {
        Random gen =new Random();
        parola=s[gen.nextInt(s.length-1)+1];
        viste=new boolean [parola.length()];
        Arrays.fill(viste,false);

    }

    public void controlla(String c, int pos){

        if(c.equals("")||c.length()>1)return;

        char lettera= c.charAt(0);
        if(parola.charAt(pos)==lettera && !viste[pos]){
            viste[pos]=true;
            lunghezza--;
        }else{
            tentativi--;
        }

    }

    public boolean finito(){
        if(lunghezza==0){
            vittoria=true;
        }
        if(tentativi<=0||lunghezza==0){
            return true;
        }else{
            return false;
        }
    }

    public int getTentativi(){
        return tentativi;
    }


    public String lettera(int pos){
        if(viste[pos])return parola.charAt(pos)+"";
        return  "*";

    }
}
