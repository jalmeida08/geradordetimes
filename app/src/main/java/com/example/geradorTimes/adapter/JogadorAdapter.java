package com.example.geradorTimes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.geradorTimes.R;
import com.example.geradorTimes.model.Jogador;

import java.text.BreakIterator;
import java.util.List;
import java.util.zip.Inflater;

public class JogadorAdapter extends ArrayAdapter<Jogador>{

    private final List<Jogador> jogadores;
    private final Context context;

    public JogadorAdapter(Context context, int resource, List<Jogador> jogadores) {
        super(context, resource, jogadores);
        this.jogadores = jogadores;
        this.context = context;
    }



    @Override
    public Jogador getItem(int position) {
        return jogadores.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.modelo_jogadores, null);
        }

       final Jogador jogador = jogadores.get(position);

        if(jogador != null){
           TextView campoNome = v.findViewById(R.id.modelo_jogador_nome_jogador);
            final CheckBox campoChk = ((CheckBox) v.findViewById(R.id.modelo_jogador_chk));

            campoChk.setTag(jogador);

            campoNome.setText(jogador.toString());
            campoChk.setChecked(jogador.isSelected());

            campoChk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cehck = (CheckBox) v;
                    Jogador jogador = (Jogador) cehck.getTag();
                    jogador.setSelected(campoChk.isChecked());
                }
            });
        }
        return v;
    }
}
