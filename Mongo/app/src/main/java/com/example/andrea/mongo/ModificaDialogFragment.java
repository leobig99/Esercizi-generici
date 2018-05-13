package com.example.andrea.mongo;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class ModificaDialogFragment extends DialogFragment {

    private static final String ARG_PARAM_NOME = "nome";
    private static final String ARG_PARAM_ID = "id";
    private static final String ARG_PARAM_DESC = "descrizione";
    private static final String ARG_PARAM_CREAT = "creatori";

    private String arg_nominativo;
    private String arg__creatori;
    private String arg_descrizione;
    private String id;

    private OnItemModified listener;

    public interface OnItemModified {
        void onItemModified(VideoGames videoGames, String id);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modifica_dialog, container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            arg_nominativo = getArguments().getString(ARG_PARAM_NOME);
            id = getArguments().getString(ARG_PARAM_ID);
            arg__creatori = getArguments().getString(ARG_PARAM_CREAT);
            arg_descrizione = getArguments().getString(ARG_PARAM_DESC);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), R.style.MyCustomTheme);

        String titleText = "Modifica " + arg_nominativo;

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.colorAccent));

        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);

        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        alertDialog.setTitle(ssBuilder);
        alertDialog.setView(R.layout.fragment_modifica_dialog);
        alertDialog.setPositiveButton("OK",
                (dialog, whichButton) -> {
                    String nome = ((EditText) getDialog().findViewById(R.id.modifica_nome)).getText().toString();
                    String creatori = ((EditText) getDialog().findViewById(R.id.modifica_creatori)).getText().toString();
                    String descrizione = ((EditText) getDialog().findViewById(R.id.modifica_descrizione)).getText().toString();
                    if (nome.equals("") || creatori.equals("") || descrizione.equals("")) {
                        Toast.makeText(getContext(), "I campi non possono essere vuoti", Toast.LENGTH_SHORT).show();
                        dismiss();
                    } else {
                        if (listener != null) {
                            listener.onItemModified(new VideoGames(nome, creatori, descrizione), id);
                        }
                        dismiss();
                    }
                }
        );
        alertDialog.setNegativeButton("Cancel",
                (dialog, whichButton) -> dialog.dismiss()
        );
        return alertDialog.create();
    }

}

