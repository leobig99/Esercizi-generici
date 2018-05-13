package com.example.andrea.mongo;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class InserisciDialogFragment extends DialogFragment {

    private OnItemInserted listener;

    public interface OnItemInserted {
        void onItemInserted(VideoGames videoGames);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inserisci_dialog, container);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = context instanceof MainActivity ? (MainActivity) context : null;
        try {
            listener = activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyCustomTheme);

        String titleText = "Inserisci VideoGames";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorAccent));

        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);

        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        alertDialog.setTitle(ssBuilder);
        alertDialog.setView(R.layout.fragment_inserisci_dialog);
        alertDialog.setPositiveButton("OK",
                (dialog, whichButton) -> {
                    String nome = ((EditText) getDialog().findViewById(R.id.inserisci_nome)).getText().toString();
                    String creatori = ((EditText) getDialog().findViewById(R.id.inserisci_creatori)).getText().toString();
                    String descrizione = ((EditText) getDialog().findViewById(R.id.inserisci_descrizione)).getText().toString();
                    if (nome.equals("") || creatori.equals("") || descrizione.equals("")) {
                        Toast.makeText(getContext(), "I campi non possono essere vuoti", Toast.LENGTH_SHORT).show();
                        dismiss();
                    } else {
                        if (listener != null)
                            listener.onItemInserted(new VideoGames(nome, creatori, descrizione));
                    }
                }
        );
        alertDialog.setNegativeButton("Cancel",
                (dialog, whichButton) -> dialog.dismiss()
        );
        return alertDialog.create();
    }


}
