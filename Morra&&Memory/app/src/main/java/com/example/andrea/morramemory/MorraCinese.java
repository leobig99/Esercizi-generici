package com.example.andrea.morramemory;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MorraCinese extends AppCompatActivity {

    public static final int CARTA=0;
    public static final int SASSO=1;
    public static final int FORBICE=2;

    private int mossaPC;
    private int mossaG;
    private int puntiG;
    private ImageView img;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morra_cinese);
        puntiG=0;
        mossaPC=0;
        mossaG=0;
    }

    private void mossaPC(){
        Random gen=new Random();
        mossaPC=gen.nextInt(3);
        setImg(R.id.mossaPC,mossaPC);
    }

    public void mossaGiocatore(View view){
        mossaPC();
        int id=view.getId();
        if(id==R.id.carta){
            mossaG=CARTA;
            setImg(R.id.mossaGiocatore,CARTA);
            controllo();
        }else if(id==R.id.sasso){
            mossaG=SASSO;
            setImg(R.id.mossaGiocatore,SASSO);
            controllo();
        }else if(id==R.id.forbici){
            mossaG=FORBICE;
            setImg(R.id.mossaGiocatore,FORBICE);
            controllo();
        }
    }

    private void controllo(){
        if(mossaG==mossaPC){
            text= (TextView) findViewById(R.id.messaggio);
            text.setTextColor(Color.GREEN);
            text.setText("HAI VINTO");
            text= (TextView) findViewById(R.id.punti);
            puntiG++;
           text.setText("Punti: "+puntiG);
        }else{
            text= (TextView) findViewById(R.id.messaggio);
            text.setTextColor(Color.RED);
            text.setText("HAI PERSO");
        }
    }

    private void setImg(int id,int m){
        img= (ImageView) findViewById(id);
        if(m==CARTA){
            img.setImageResource(R.drawable.carta);
        }else if(m==SASSO){
            img.setImageResource(R.drawable.sasso);
        }else if(m==FORBICE){
            img.setImageResource(R.drawable.forbici);
        }
    }

    public void reset(View view){
        mossaG=0;
        mossaPC=0;
        puntiG=0;
        text.setText("");
        img= (ImageView) findViewById(R.id.mossaPC);
        img.setImageResource(R.drawable.vuota);
        img= (ImageView) findViewById(R.id.mossaGiocatore);
        img.setImageResource(R.drawable.vuota);
    }

    public void Return(){
        Intent i=new Intent();
        i.putExtra("isOK","Sei tornato alla MainActivity");
        MorraCinese.this.setResult(RESULT_OK,i);
        MorraCinese.this.finish();
    }
}
