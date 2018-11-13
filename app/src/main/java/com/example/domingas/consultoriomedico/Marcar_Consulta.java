package com.example.domingas.consultoriomedico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.domingas.consultoriomedico.Modelo.Consulta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Marcar_Consulta extends AppCompatActivity {
   CheckBox ckbDor, ckbDores, ckdCansaco, ckdColuna, ckdFebres;
    EditText edtNome, edtDuracao;
    //Button btnMarcar;
    ListView listV_Paciente;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Consulta> listPaciente = new ArrayList<Consulta>();
    private ArrayAdapter<Consulta> arrayAdapterPaciente;

    Consulta pacienteSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar__consulta);


        edtNome = (EditText) findViewById(R.id.edtPaciente);
            ckbDor = (CheckBox) findViewById(R.id.ckbDor);
            ckbDores = (CheckBox) findViewById(R.id.ckbDores);
            listV_Paciente = (ListView) findViewById(R.id.listV_dados);
            /*ckdCansaco = (CheckBox) findViewById(R.id.ckbCansaco);
            ckdColuna = (CheckBox) findViewById(R.id.ckdColuna);
            ckdFebres = (CheckBox) findViewById(R.id.ckbFebre);
            */

        inicializarFirebase();
         eventtoDatabase();


    }
    private void eventtoDatabase() {

            databaseReference.child("Paciente").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    listPaciente.clear();
                    for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                        Consulta consulta = objSnapshot.getValue(Consulta.class);
                        listPaciente.add(consulta);
                    }
                    arrayAdapterPaciente = new ArrayAdapter<Consulta>(Marcar_Consulta.this, android.R.layout.simple_list_item_1, listPaciente);
                    listV_Paciente.setAdapter(arrayAdapterPaciente);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        private void inicializarFirebase() {
            FirebaseApp.initializeApp(Marcar_Consulta.this);
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
            databaseReference = firebaseDatabase.getReference();

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menumain, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.menu_novo) {
                Consulta consulta = new Consulta();
                consulta.setUid(UUID.randomUUID().toString());
                consulta.setNome(edtNome.getText().toString());
                consulta.setDor(ckbDor.getText().toString());
                consulta.setDores(ckbDores.getText().toString());
                /*membro.getMorada(edtMorada.getText().toString());
                membro.getEstado(edtEstado.getText().toString());
                membro.getNatural(edtNatural.getText().toString());*/

                databaseReference.child("Paciente").child(consulta.getUid()).setValue(consulta);
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
                limparcampos();
            }else if(id == R.id.menu_actualizar){
                Consulta consulta = new Consulta();
                consulta.setUid(pacienteSelecionada.getUid());
                consulta.setNome(edtNome.getText().toString().trim());
                consulta.setDor(ckbDor.getText().toString().trim());
                consulta.setDores(ckbDores.getText().toString().trim());
                /*membro.getNatural(edtNatural.getText().toString().trim());*/
                databaseReference.child("Paciente").child(consulta.getUid()).setValue(consulta);
                limparcampos();
            }else if(id == R.id.menu_delete){
                Consulta consulta = new Consulta();
                consulta.setUid(pacienteSelecionada.getUid());
                databaseReference.child("Paciente").child(consulta.getUid()).removeValue();
                limparcampos();

            }

            return true;
        }

        private void limparcampos() {
           // edtEmail.setText("");
            edtNome.setText("");
            //edtSenha.setText("");
            //edtEstado.setText("");
            //edtMorada.setText("");
            //edtNatural.setText("");

        }
    }



