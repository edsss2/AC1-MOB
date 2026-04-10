package com.example.ac1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meubanco.db";
    private static final int DATABASE_VERSION = 1;
    // Nome da tabela e colunas – Para o caso de tabela única
    private static final String TABLE_NAME = "contatos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_TELEFONE = "telefone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CATEGORIA = "categoria";
    private static final String COLUMN_CIDADE = "cidade";
    private static final String COLUMN_FAVORITO = "favorito";

    public BancoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME + " TEXT,"
                + COLUMN_TELEFONE + " TEXT,"
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_CATEGORIA + " TEXT,"
                + COLUMN_CIDADE + " TEXT,"
                + COLUMN_FAVORITO + " BIT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // ### CRUD ###
    public long inserirUsuario(Contato ctt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, ctt.nome);
        values.put(COLUMN_TELEFONE, ctt.telefone);
        values.put(COLUMN_EMAIL, ctt.email);
        values.put(COLUMN_CATEGORIA, ctt.categoria);
        values.put(COLUMN_CIDADE, ctt.cidade);
        values.put(COLUMN_FAVORITO, ctt.favorito);
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor listarUsuarios()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public int atualizarUsuario(int id, Contato ctt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, ctt.nome);
        values.put(COLUMN_TELEFONE, ctt.telefone);
        values.put(COLUMN_EMAIL, ctt.email);
        values.put(COLUMN_CATEGORIA, ctt.categoria);
        values.put(COLUMN_CIDADE, ctt.cidade);
        values.put(COLUMN_FAVORITO, ctt.favorito);
        return db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public int excluirUsuario(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}
