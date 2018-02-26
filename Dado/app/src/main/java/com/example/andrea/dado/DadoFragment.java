package com.example.andrea.dado;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class DadoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public int precFace;
    public int direction;

    public static DadoFragment newInstance(int index,int direction) {
        DadoFragment fragment = new DadoFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putInt("direction", direction);
        fragment.setArguments(args);
        return fragment;
    }

    public static DadoFragment nextInstance(int currentFace,int direction,DadoFragment currentFragment){
        Bundle args=new Bundle();
        args.putInt("index",currentFragment.getArguments().getInt("index"));
        args.putInt("direction",currentFragment.getArguments().getInt("direction"));
        currentFragment.setArguments(args);
        return newInstance(currentFace,direction);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            precFace=getArguments().getInt("index");
            direction=getArguments().getInt("direction");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_dado, container, false);
        ImageView img= (ImageView) view.findViewById(R.id.dado_fragment_image);

        int face=getRandomFace(precFace);

        switch (face){
            case 1:
                img.setImageResource(R.drawable.die_face_1);
                break;
            case 2:
                img.setImageResource(R.drawable.die_face_2);
                break;
            case 3:
                img.setImageResource(R.drawable.die_face_3);
                break;
            case 4:
                img.setImageResource(R.drawable.die_face_4);
                break;
            case 5:
                img.setImageResource(R.drawable.die_face_5);
                break;
            case 6:
                img.setImageResource(R.drawable.die_face_6);
                break;
        }

        return view;
    }

    public int getRandomFace(int currentFace){
        Random gen=new Random();
        int newFace=0;
        while(newFace==currentFace){
            newFace=gen.nextInt(6)+1;
        }
        return newFace;
    }

}
