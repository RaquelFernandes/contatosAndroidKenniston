package br.iesb.contatos.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.iesb.contatos.Adapter.UsuarioAdapter;
import br.iesb.contatos.Helper.DatabaseHelper;
import br.iesb.contatos.Models.Usuario;
import br.iesb.contatos.R;

public class ListarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario);

        List<Usuario> listaPesquisaBanco = new ArrayList<>();
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM usuario";

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++){

            Usuario usuario = new Usuario();

            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setTelefone(c.getString(c.getColumnIndex("telefone")));
            usuario.setEmail(c.getString(c.getColumnIndex("email")));

            listaPesquisaBanco.add(usuario);
            c.moveToNext();
        }

        ListView lista = (ListView) findViewById(R.id.listaUsuario);
        UsuarioAdapter adaptador = new UsuarioAdapter(this, 0, listaPesquisaBanco);
        lista.setAdapter(adaptador);


    }
}
