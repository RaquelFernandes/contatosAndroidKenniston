package br.iesb.contatos.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.iesb.contatos.Models.Usuario;
import br.iesb.contatos.R;

/**
 * Created by 1514290042 on 10/03/2017.
 */

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private LayoutInflater mInflater;
    private List<Usuario> listaItens;


    public UsuarioAdapter(Context context, int resource, List<Usuario> itensLista) {
        super(context, resource, itensLista);

        mInflater = LayoutInflater.from(context);
        listaItens = itensLista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View formatoItemLista = mInflater.inflate(R.layout.celula_lista, null);
        Usuario usuario = listaItens.get(position);

        EditText txtUsuarioNome = (EditText) formatoItemLista.findViewById(R.id.txtUsuarioNome);
        txtUsuarioNome.setText(usuario.getNome());

        EditText txtUsuarioEmail = (EditText) formatoItemLista.findViewById(R.id.txtUsuarioEmail);
        txtUsuarioEmail.setText(usuario.getEmail());

        EditText txtUsuarioTelefone = (EditText) formatoItemLista.findViewById(R.id.txtUsuarioTelefone);
        txtUsuarioTelefone.setText(usuario.getTelefone());

        return formatoItemLista;

    }
}
