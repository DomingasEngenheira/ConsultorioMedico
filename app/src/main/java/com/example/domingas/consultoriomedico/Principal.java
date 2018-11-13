package com.example.domingas.consultoriomedico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.domingas.consultoriomedico.Login2.Login;

public class Principal extends AppCompatActivity {
    ImageView imageView;
    Button btnConsulta, btnConsultaOnline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnConsulta = (Button) findViewById(R.id.btnConsulta);
        btnConsultaOnline  = (Button) findViewById(R.id.btnConsultaOnline);

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, Login.class);
                startActivity(i);
            }
        });

        btnConsultaOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent online = new Intent(Principal.this, Marcar_Consulta.class);
                startActivity(online);
            }
        });

       /* imageView = (ImageView) findViewById(R.id.imgAndroid);

        Glide.with(getApplicationContext())
                .load(R.drawable.android)
                .into(imageView);
    }*/
    }
}
