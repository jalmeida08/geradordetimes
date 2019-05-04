package com.example.geradorTimes.helper;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geradorTimes.R;
import com.example.geradorTimes.model.Jogador;

public class JogadorHelper {

    private EditText campoNome;
    private boolean campoIsSelected;

    public JogadorHelper(Activity activity) {
        switch (activity.getComponentName().getShortClassName()){
            case  ".AdicionarJogadoresActivity":
                Toast.makeText(activity, "AdicionarJogadoresActivity", Toast.LENGTH_SHORT);
                campoNome = activity.findViewById(R.id.add_jogador_nome);
                break;
            case ".ListaJogadoresActivity":
//                Toast.makeText(activity, ".ListaJogadoresActivity", Toast.LENGTH_SHORT);
//                campoIsSelected = ((CheckBox) activity.findViewById(R.id.modelo_jogador_chk)).isChecked();
                break;
        }
    }

    public Jogador getJogador(){
        Jogador jogador = new Jogador();
        jogador.setNome(campoNome.getText().toString());
        jogador.setSelected(campoIsSelected);
        return jogador;
    }

    public boolean getChecked(){
        return campoIsSelected;
    }

    public void limparCapo(){
        campoNome.setText("");
   }

    public EditText getCampoNome() {
        return campoNome;
    }

    public void setCampoNome(EditText campoNome) {
        this.campoNome = campoNome;
    }
}
