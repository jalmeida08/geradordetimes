package com.example.geradorTimes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

       Jogador jogador = jogadores.get(position);

        if(jogador != null){
           TextView campoNome = v.findViewById(R.id.modelo_jogador_nome_jogador);
           campoNome.setText(jogador.toString());
        }
        return v;
    }
}
