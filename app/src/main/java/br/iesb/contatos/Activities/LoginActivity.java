package br.iesb.contatos.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import br.iesb.contatos.Helper.DatabaseHelper;
import br.iesb.contatos.R;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_cadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastrar = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(telaCadastrar);
                //Passa num código para poder saber qual Intent responder
                //startActivityForResult(telaCadastrar, 9999);
            }
        });

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginContainer = new Intent();

                EditText emailEdit = (EditText) findViewById(R.id.emailLogin);
                EditText senhaEdit = (EditText) findViewById(R.id.senhaLogin);

                String email = emailEdit.getText().toString();
                String senha = senhaEdit.getText().toString();

                boolean autorizado = confirmarUsuario(email, senha);
                if (autorizado){
                    Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                }

            }
        });
    }

    //Chama esse método automaticamente
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //Pega os valores pela chave
        String email = data.getExtras().getString("emailLogin");
        String senha = data.getExtras().getString("senhaLogin");

        EditText txtEmail = (EditText) findViewById(R.id.emailLogin);
        EditText txtSenha = (EditText) findViewById(R.id.senhaLogin);

        Log.d("senha",senha);
        Log.d("email",email);

        //Seta nos campos
        txtEmail.setText(email);
        txtSenha.setText(senha);

    }

    private boolean confirmarUsuario(String email, String senha){
        boolean log = false;

        try {
            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor c = db.rawQuery("SELECT email, senha FROM usuario WHERE email = '" + email+"'", null);

            if(c.moveToFirst()){
                if (c.getString(c.getColumnIndex("email")) != null || c.getString(c.getColumnIndex("email")) != ""){
                    if(c.getString(c.getColumnIndex("senha")) == senha){
                        Toast.makeText(this, "Bem-vindo", Toast.LENGTH_SHORT);
                        log = true;
                    }
                    Toast.makeText(this, "Dados incorretos", Toast.LENGTH_SHORT);

                }
                Toast.makeText(this, "Dados incorretos", Toast.LENGTH_SHORT);
            }

        }
        catch (Exception ex){
            Log.d("Erro", ex.getMessage());
        }

        return log;
    }
}
