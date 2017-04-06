package br.iesb.contatos.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.iesb.contatos.R;

public class NotificacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);

        Intent ti = getIntent();
        String url = ti.getExtras().getString("url");
        Log.d("sda", url);

        TextView txt = (TextView) findViewById(R.id.txtUrl);
        txt.setText(url);
    }


}
