package com.example.geradorTimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.geradorTimes.adapter.JogadorAdapter;
import com.example.geradorTimes.dao.JogadorDAO;
import com.example.geradorTimes.helper.JogadorHelper;
import com.example.geradorTimes.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class ListaJogadoresActivity extends AppCompatActivity {

    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private ListView lst;
    private JogadorDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jogadores);

        listarJogadores();
        checkBoxListJogador();
        registerForContextMenu(lst);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarJogadores();
    }

    private void listarJogadores() {
        dao = new JogadorDAO(this);

        jogadores = dao.listarJogadores();
        dao.close();
        lst = findViewById(R.id.lista_jogadores_list);
        JogadorAdapter adapter = new JogadorAdapter(this, R.layout.modelo_jogadores, jogadores);
        lst.setAdapter(adapter);
    }


    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Jogador jogador = (Jogador) lst.getItemAtPosition(info.position);
                dao = new JogadorDAO(ListaJogadoresActivity.this);
                dao.deletarJogador(jogador.getId());
                dao.close();
                listarJogadores();
                Toast.makeText(ListaJogadoresActivity.this, "Item excluído com sucesso", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

        public void checkBoxListJogador(){
            JogadorHelper jh = new JogadorHelper(this);
            Toast.makeText(this, String.valueOf(jh.getChecked()), Toast.LENGTH_SHORT).show();
        }
}
