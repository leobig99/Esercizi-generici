package com.example.andrea.morramemory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Memory extends AppCompatActivity {

    private int tessereTotGir;
    private int campo[];
    private int girate;
    private ImageButton img;
    private int prec;
    private int corrId;
    private int precId;
    public static final int RE=0;
    public static final int MATTA=1;
    public static final int DUE=2;
    public static final int ASSO=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        campo=new int[8];
        Arrays.fill(campo,-1);
        inizializza();
        girate=0;
        prec=0;
        precId=0;
        corrId=0;
        tessereTotGir=0;
    }

    public void mossa(View view){
        corrId=view.getId();
        if(girate==1){
            int val=campo[getImageValue(corrId)];
            showImage(corrId,val);
            if(campo[val]==campo[prec]){
                img.setEnabled(false);
                img= (ImageButton) findViewById(precId);
                img.setEnabled(false);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        img.setImageResource(R.drawable.vuota2);
                        img= (ImageButton) findViewById(corrId);
                        img.setImageResource(R.drawable.vuota2);
                        girate=0;
                        tessereTotGir++;
                        controlloVittoria();
                    }
                }, 2000);
            }else{
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        img= (ImageButton) findViewById(precId);
                        img.setEnabled(true);
                        img= (ImageButton) findViewById(corrId);
                        img.setImageResource(R.drawable.backleggenda);
                        img= (ImageButton) findViewById(precId);
                        img.setImageResource(R.drawable.backleggenda);
                        girate=0;
                    }
                }, 2000);
            }
        }else if(girate==0){
            girate++;
            showImage(corrId,getImageValue(corrId));
            prec=campo[getImageValue(corrId)];
            precId=corrId;
            img= (ImageButton) findViewById(corrId);
            img.setEnabled(false);
        }
    }

    private int getImageValue(int corrId){
        switch (corrId){
            case R.id.f1:return 0;
            case R.id.f2:return 1;
            case R.id.f3:return 2;
            case R.id.f4:return 3;
            case R.id.f5:return 4;
            case R.id.f6:return 5;
            case R.id.f7:return 6;
            case R.id.f8:return 7;
        }
        return -1;
    }

    private void controlloVittoria(){
        if(tessereTotGir==4)finish();
    }


    private void showImage(int id,int m){
        img= (ImageButton) findViewById(id);
        if(m==ASSO){
            img.setImageResource(R.drawable.asso);
        }else if(m==DUE){
            img.setImageResource(R.drawable.duepicche);
        }else if(m==RE){
            img.setImageResource(R.drawable.refiori);
        }
        else if(m==MATTA){
            img.setImageResource(R.drawable.matta);
        }
    }

    private void inizializza(){
        Random gen=new Random();
        int n=0;
        for (int i=0;i<campo.length;i++){
            n=gen.nextInt(4);
            if(contains(n)==2){
                while(contains(n)==2)n=gen.nextInt(4);
            }
            campo[i]=n;
        }
    }

    private int contains(int n){
        int conta = 0;
        for (int i = 0; i < campo.length; i++) {
            if (campo[i] == n)conta++;
        }return conta;

    }

    private void Return(){
        Intent i=new Intent();
        i.putExtra("isOK","Hai Vinto");
        Memory.this.setResult(RESULT_OK,i);
        Memory.this.finish();
    }
}
