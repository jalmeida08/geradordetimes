package com.example.geradorTimes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.geradorTimes.R;
import com.example.geradorTimes.model.Jogador;
import com.example.geradorTimes.model.Time;

import java.util.List;

public class TimesAdapter extends ArrayAdapter<Time> {
    private List<Time> times;

    public TimesAdapter(Context context, int textViewResourceId, List<Time> times) {
        super(context, textViewResourceId, times);
        this.times = times;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            Context context = getContext();
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.modelo_time, null);
        }

        Time time = this.times.get(position);
        if(time != null){
            String timeConcat = "";
            int nmrItem = 0;
            for (Jogador jog: time.getJogador()) {
                timeConcat = timeConcat.concat((nmrItem+1) + " - " + jog.toString() + "\n");
                nmrItem++;
            }
            ((TextView) v.findViewById(R.id.listaTimes_nome_jogador)).setText(timeConcat);
            ((TextView) v.findViewById(R.id.listaTimes_nome_time)).setText("Time "+ time.getNomeTime());
        }
        return v;
    }
}
