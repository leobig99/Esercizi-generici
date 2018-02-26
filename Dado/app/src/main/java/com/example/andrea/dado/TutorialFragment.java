package com.example.andrea.dado;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TutorialFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tutorial, container, false);

        GifImageView gifImageView = (GifImageView) view.findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.swipe);

        Button button = (Button) view.findViewById(R.id.button_tutorial);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startRoll();
            }
        });
        return view;
    }

}
