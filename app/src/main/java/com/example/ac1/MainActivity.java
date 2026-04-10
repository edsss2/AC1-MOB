package com.example.ac1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BancoHelper databaseHelper;
    ListView contatosListView;
    ArrayAdapter<Contato> adapter;
    ArrayList<Contato> listaContatos;
    ArrayList<Integer> listaIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try
        {
            contatosListView = findViewById(R.id.contatosListView);

            databaseHelper = new BancoHelper(this);

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });


            contatosListView.setOnItemClickListener((parent, view, position, id) -> {
                int userId = listaIds.get(position);
                Contato ctt = listaContatos.get(position);

                contatosListView.setOnItemLongClickListener((adapterView, view1, pos, l) -> {
                    int idUsuario = listaIds.get(pos);
                    int deletado = databaseHelper.excluirUsuario(idUsuario);
                    if (deletado > 0) {
                        Toast.makeText(this, "Usuário excluído!", Toast.LENGTH_SHORT).show();
                        carregarUsuarios();
                    }
                    return true;
                });
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarUsuarios() {
        Cursor cursor = databaseHelper.listarUsuarios();
        listaContatos = new ArrayList<>();
        listaIds = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Contato ctt = new Contato();
                ctt.id = cursor.getInt(0);
                ctt.nome = cursor.getString(1);
                ctt.email = cursor.getString(2);
                ctt.telefone = cursor.getString(3);
                ctt.categoria = cursor.getString(4);
                ctt.cidade = cursor.getString(5);

                listaContatos.add(ctt);
                listaIds.add(ctt.id);
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaContatos);
        contatosListView.setAdapter(adapter);
    }

    public void carregarActivityCadastroUsuario(View view)
    {
        Intent intent = new Intent(MainActivity.this, FormularioCtt.class);
        startActivity(intent);
    }

    private void carregarActivityAtualizarUsuario(View view, Contato ctt)
    {
        Intent intent = new Intent(MainActivity.this, FormularioCtt.class);
        startActivity(intent);

    }
}