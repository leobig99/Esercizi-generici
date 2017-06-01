package com.example.andrea.morramemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MORRA_CINESE = 1;
    public static final int MEMORY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MorraCinese(View view) {
        Intent i=new Intent(MainActivity.this, com.example.andrea.morramemory.MorraCinese.class);
        startActivityForResult(i,MORRA_CINESE);
    }

    public void Memory(View view) {
        Intent i=new Intent(MainActivity.this, com.example.andrea.morramemory.Memory.class);
        startActivityForResult(i,MEMORY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent Data) {
        if (requestCode == MORRA_CINESE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, Data.getStringExtra("isOK"), Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == MEMORY) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, Data.getStringExtra("isOK"), Toast.LENGTH_SHORT).show();
            }
        }

    }
}