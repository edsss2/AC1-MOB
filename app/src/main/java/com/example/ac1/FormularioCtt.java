package com.example.ac1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class FormularioCtt extends AppCompatActivity {

    EditText nomeEdit, telefoneEdit, emailEdit, cidadeEdit;
    CheckBox favoritoCb;
    Spinner categoriaSpin;
    Button salvarBtn;
    BancoHelper dbHelper;

    private List<CheckBox> checkBoxList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_ctt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //LinearLayout checkBoxContainer = findViewById(R.id.checkBoxContainer);
        //Button btnCheck = findViewById(R.id.btnCheck);

        String[] opcoes = {"Familia", "Amigos", "Trabalho", "Outros"};

        for (String opcao : opcoes)
        {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(opcao);
            //checkBoxContainer.addView(checkBox);
            checkBoxList.add(checkBox);
        }

        try {
            nomeEdit = findViewById(R.id.nomeEdit);
            telefoneEdit = findViewById(R.id.telefoneEdit);
            emailEdit = findViewById(R.id.emailEdit);
            cidadeEdit = findViewById(R.id.cidadeEdit);
            categoriaSpin = findViewById(R.id.categoriaSpin);
            salvarBtn = findViewById(R.id.salvarBtn);

            dbHelper = new BancoHelper(this);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void receberCtt(View view, Contato ctt) {
        nomeEdit.setText(ctt.nome);
        telefoneEdit.setText(ctt.telefone);
        emailEdit.setText(ctt.telefone);
        cidadeEdit.setText(ctt.telefone);

        salvarBtn.setText("Atualizar");
        salvarBtn.setOnClickListener(v -> {
            Contato ctt2 = new Contato();

            ctt2.nome = nomeEdit.getText().toString();
            ctt2.telefone = telefoneEdit.getText().toString();
            ctt2.email = emailEdit.getText().toString();
            ctt2.cidade = cidadeEdit.getText().toString();

            if (!ctt2.nome.isEmpty() && !ctt2.email.isEmpty() && !ctt2.telefone.isEmpty()) {
                long resultado = dbHelper.atualizarUsuario(ctt2.id, ctt2);
                if (resultado != -1) {
                    Toast.makeText(this, "Usuário Atualizado!", Toast.LENGTH_SHORT).show();
                    nomeEdit.setText("");
                    telefoneEdit.setText("");
                    emailEdit.setText("");
                    cidadeEdit.setText("");

                } else {
                    Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void salvarCtt(View view) {
        Contato ctt = new Contato();
        ctt.nome = nomeEdit.getText().toString();
        ctt.telefone = telefoneEdit.getText().toString();
        ctt.email = emailEdit.getText().toString();
        ctt.cidade = cidadeEdit.getText().toString();

        if (!ctt.nome.isEmpty() && !ctt.email.isEmpty() && !ctt.telefone.isEmpty()) {
            long resultado = dbHelper.inserirUsuario(ctt);
            if (resultado != -1) {
                Toast.makeText(this, "Usuário salvo!", Toast.LENGTH_SHORT).show();
                nomeEdit.setText("");
                telefoneEdit.setText("");
                emailEdit.setText("");
                cidadeEdit.setText("");

            } else {
                Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void atualizarCtt(View view, Integer id) {
        Contato ctt = new Contato();
        ctt.nome = nomeEdit.getText().toString();
        ctt.telefone = telefoneEdit.getText().toString();
        ctt.email = emailEdit.getText().toString();
        ctt.cidade = cidadeEdit.getText().toString();

        if (!ctt.nome.isEmpty() && !ctt.email.isEmpty() && !ctt.telefone.isEmpty()) {
            long resultado = dbHelper.atualizarUsuario(id, ctt);
            if (resultado != -1) {
                Toast.makeText(this, "Usuário Atualizado!", Toast.LENGTH_SHORT).show();
                nomeEdit.setText("");
                telefoneEdit.setText("");
                emailEdit.setText("");
                cidadeEdit.setText("");

            } else {
                Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltar(View view)
    {
        finish();
    }
}