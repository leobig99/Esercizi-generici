package com.example.andrea.mongo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by andrea on 25/04/2018.
 */

public class SearchDialogFragment extends DialogFragment {

    private SearchDialogListener listener;

    public interface SearchDialogListener {
        void onItemSearched(String tipologiaRicerca, String nome, String creatori);
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

        String titleText = "Search";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorAccent));
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        alertDialog.setTitle(ssBuilder);
        alertDialog.setView(R.layout.fragment_ricerca_dialog);
        alertDialog.setPositiveButton("OK",
                (dialog, whichButton) -> {
                    String nome = ((EditText) getDialog().findViewById(R.id.ricerca_nome)).getText().toString();
                    String creatori = ((EditText) getDialog().findViewById(R.id.ricerca_creatori)).getText().toString();
                    if (nome.equals("") && creatori.equals("")) {
                        listener.onItemSearched("vuoto", "", "");
                    } else if (!nome.equals("")) {
                        listener.onItemSearched("nome", nome, "");
                    } else if (!creatori.equals("")) {
                        listener.onItemSearched("creatori", "", creatori);
                    } else {
                        listener.onItemSearched("nome&&creatori", nome, creatori);
                    }
                }
        );
        alertDialog.setNegativeButton("Cancel",
                (dialog, whichButton) -> dialog.dismiss()
        );
        return alertDialog.create();
    }

}
