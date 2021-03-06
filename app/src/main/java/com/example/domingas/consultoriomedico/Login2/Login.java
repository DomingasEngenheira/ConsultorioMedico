package com.example.domingas.consultoriomedico.Login2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.domingas.consultoriomedico.Cadastro;
import com.example.domingas.consultoriomedico.Marcar_Consulta;
import com.example.domingas.consultoriomedico.Modelo.Usuario;
import com.example.domingas.consultoriomedico.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private EditText editEmail, editSenha;
    private Button btnLogar, btnNovo;
    private TextView txtResetSenha;

        private FirebaseAuth auth;
        ListView listV_dados;

        private FirebaseDatabase firebaseDatabase;
        private DatabaseReference databaseReference;

        private List<Usuario> listUsuario = new ArrayList<Usuario>();
        private ArrayAdapter<Usuario> arrayAdapterUsuario;

        Usuario pessoaSelecionada;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            editEmail = (EditText) findViewById(R.id.editEmail);
            editSenha = (EditText) findViewById(R.id.editSenha);

            btnLogar = (Button) findViewById(R.id.btnLogar);

            // listV_dados = (ListView) findViewById(R.id.listV_dados);

            inicializarComponentes();
            eventoClicks();
        }


        private void eventoClicks() {
            btnNovo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), Cadastro.class);
                    startActivity(i);
                }
            });
            btnLogar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if (!editEmail.getText().toString().equals("") && !editSenha.getText().toString().equals("")) {
                    String email = editEmail.getText().toString().trim();
                    String senha = editSenha.getText().toString().trim();
                    //    String nome = edtNome.getText().toString().trim();
                    login(email, senha);
                }

            });
        }


        private void eventtoDatabase() {
            databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    listUsuario.clear();
                    for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                        Usuario p = objSnapshot.getValue(Usuario.class);
                        listUsuario.add(p);
                    }
                    arrayAdapterUsuario = new ArrayAdapter<Usuario>(Login.this,android.R.layout.simple_list_item_1,listUsuario);
                    listV_dados.setAdapter(arrayAdapterUsuario);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        private void login (String email, String senha){
            auth.signInWithEmailAndPassword(email,senha)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent i = new Intent(Login.this, Marcar_Consulta.class);
                                startActivity(i);
                            }else{
                                alert("email ou senha errados");
                            }
                        }

                    });
        }
        private void alert(String s)
        {
            Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
        }
        private void inicializarComponentes() {
            editEmail = (EditText) findViewById(R.id.editEmail);
            editSenha = (EditText) findViewById(R.id.editSenha);
            btnLogar = (Button) findViewById(R.id.btnLogar);
            btnNovo = (Button) findViewById(R.id.btnNovo);
            txtResetSenha = (TextView) findViewById(R.id.txtResertSenha);
        }

        @Override
        protected void onStart() {
            super.onStart();
            auth = Conexao.getFirebaseAuth();
        }




    }

