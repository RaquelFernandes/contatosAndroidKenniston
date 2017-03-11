package br.iesb.contatos.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import br.iesb.contatos.Helper.DatabaseHelper;
import br.iesb.contatos.R;

public class MainActivity extends AppCompatActivity {


    private GoogleApiClient client;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("testar", "onCreate, cheguei");
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_fechar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_salvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Cria só uma container para passar os dados
                Intent container = new Intent();

                //Pega os valores
                EditText nomeEdit = (EditText) findViewById(R.id.txtNome);
                EditText enderecoEdit = (EditText) findViewById(R.id.txtEndereco);
                EditText telefoneEdit = (EditText) findViewById(R.id.txtTelefone);
                EditText emailEdit = (EditText) findViewById(R.id.txtEmail);
                EditText senhaEdit = (EditText) findViewById(R.id.txtSenha);
                EditText confirmarSenhaEdit = (EditText) findViewById(R.id.txtConfirmarSenha);

                String nome = nomeEdit.getText().toString();
                String endereco = enderecoEdit.getText().toString();
                String telefone = telefoneEdit.getText().toString();
                String senha = senhaEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String confirmarSenha = confirmarSenhaEdit.getText().toString();

                //Passa qual a chave e o valor
                //A chave serve para saber recuperar o dado
                container.putExtra("nome", nome);
                container.putExtra("endereco", endereco);
                container.putExtra("telefone", telefone);
                container.putExtra("email", email);
                container.putExtra("senha", senha);
                container.putExtra("confirmarSenha", confirmarSenha);

                salvarUsuario(container);

                //O código que ele espera na outra tela (passa as constraint's)
                //Passa os dados
                setResult(RESULT_OK, container);

                //Mata a tela
                finish();
            }
        });

        Button btnLista = (Button) findViewById(R.id.btn_lista);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista = new Intent(MainActivity.this, ListarUsuario.class);
                startActivity(lista);
            }
        });

    }


    private void salvarUsuario(Intent intent){
        helper = new DatabaseHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("nome", intent.getStringExtra("nome"));
        content.put("endereco", intent.getStringExtra("endereco"));
        content.put("telefone", intent.getStringExtra("telefone"));
        content.put("email", intent.getStringExtra("email"));
        content.put("senha", intent.getStringExtra("senha"));

        long salvo = db.insert("usuario", null, content);
        if (salvo != -1) {
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
        }
    }

    private void listarUsuario(){
        helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM usuario", null);

        while (c.moveToNext()){
            Log.d("Nome", c.getString(c.getColumnIndex("nome")));
            Log.d("Endereco", c.getString(c.getColumnIndex("endereco")));
            Log.d("Telefone", c.getString(c.getColumnIndex("telefone")));
            Log.d("Email", c.getString(c.getColumnIndex("email")));
            Log.d("Senha", c.getString(c.getColumnIndex("senha")));
        }
    }

}
