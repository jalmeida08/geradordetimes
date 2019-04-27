package com.example.geradorTimes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.geradorTimes.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorDAO extends SQLiteOpenHelper {

    public JogadorDAO(Context context) {
        super(context, "Aluno", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE jogador (id INTEGER PRIMARY KEY, nome VARCHAR NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Jogador";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertJogador(Jogador jogador){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", jogador.getNome());
        db.insert("Jogador", null, values);
    }

    public List<Jogador> listarJogadores(){
        String sql = "SELECT * FROM Jogador";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c  = db.rawQuery(sql, null);
        List<Jogador> listJogadores = new ArrayList<Jogador>();
        while (c.moveToNext()){
            Jogador jogador = new Jogador();
            jogador.setNome(c.getString(c.getColumnIndex("nome")));
            jogador.setId(c.getLong(c.getColumnIndex("id")));
            listJogadores.add(jogador);
        }
        c.close();
        return listJogadores;
    }


    public void deletarJogador(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(id)};
        db.delete("Jogador", "id = ?", params);
    }
}
