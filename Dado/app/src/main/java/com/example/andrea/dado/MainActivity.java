package com.example.andrea.dado;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    float x1,x2,y1,y2;
    final static float MIN_DISTANCE = 150.0f;

    final static int LEFT=1;
    final static int RIGHT=2;
    final static int UP=3;
    final static int DOWN=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment welcome = new WelcomeFragment();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, welcome);
        ft.commit();
    }

    public void startRoll(){
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment,DadoFragment.newInstance(0,0));
        ft.commit();
    }

    public void startTutorial(){
        Fragment tutorial=new TutorialFragment();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        ft.replace(R.id.fragment, tutorial);
        ft.commit();
    }

    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY =y2-y1;
                int direzione=0;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        // Left to Right swipe action
                        direzione=this.RIGHT;
                    }
                    else {
                        // Right to left swipe action
                        direzione=this.LEFT;
                    }
                }
                if(Math.abs(deltaY) > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX)){
                    if (y2 > y1) {
                        // Up to down swipe action
                        direzione=this.DOWN;
                    }
                    else {
                        // Down to up swipe action
                        direzione=this.UP;
                    }
                }
                if(direzione!=0){
                    changeFragment(direzione);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void changeFragment(int direzione){
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(fm.findFragmentById(R.id.fragment) instanceof DadoFragment){
            ft.replace(R.id.fragment,DadoFragment.newInstance(((DadoFragment) fm.findFragmentById(R.id.fragment)).precFace,direzione));
        }else{
            switch (direzione){
                case UP:
                    ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_up);
                    break;
                case DOWN:
                    ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_down);
                    break;
                case LEFT:
                    ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_left);
                    break;
                case RIGHT:
                    ft.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
                    break;
            }
            ft.replace(R.id.fragment,DadoFragment.newInstance(0,direzione));
        }
        ft.commit();
    }
}
