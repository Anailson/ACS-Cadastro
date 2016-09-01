package com.avisosms.iuri.avisasms.fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.activity.Principal;
import com.avisosms.iuri.avisasms.dataHandler.MedicoHandler;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.suporte.Dialogs;
import com.thebluealliance.spectrum.SpectrumDialog;

import io.realm.Realm;

/**
 * Created by iuri on 7/11/2016.
 */
public class MedicoAdicionarEditar extends AppCompatActivity {

    EditText medico_nome;
    EditText medico_especialidade;
    EditText medico_telefone;
    EditText medico_telefone2;
    EditText medico_email;
    EditText medico_observacao;

    long idMedico;
    Medico medico = null;
    Realm realm;

    int corIndicativa = -16307805;
    private boolean editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medico_add_editar_layout);

        realm = Realm.getDefaultInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Adicionar Médico(a)");

        medico_nome = (EditText) findViewById(R.id.medico_nome);
        medico_especialidade = (EditText) findViewById(R.id.medico_especialidade);
        medico_telefone = (EditText) findViewById(R.id.medico_telefone);
        medico_telefone2 = (EditText) findViewById(R.id.medico_telefone2);
        medico_email = (EditText) findViewById(R.id.medico_email);
        medico_observacao = (EditText) findViewById(R.id.medico_observacao);

        ImageView icon_medico = (ImageView) findViewById(R.id.icon_medico);


        Button medico_salvar = (Button) findViewById(R.id.medico_salvar);
        medico_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        Button medico_cancelar = (Button) findViewById(R.id.medico_cancelar);
        medico_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogs.confirmarSaida(MedicoAdicionarEditar.this);
            }
        });

        editar = getIntent().getBooleanExtra("editar", false);
        idMedico = getIntent().getLongExtra("idMedico", -1);


        if (idMedico != -1) {
            if (editar) {
                setTitle("Editar Médico(a)");

            } else {
                setTitle("Visualizar Médico(a)");
                medico_nome.setKeyListener(null);
                medico_especialidade.setKeyListener(null);
                medico_telefone.setKeyListener(null);
                medico_telefone2.setKeyListener(null);
                medico_email.setKeyListener(null);
                medico_observacao.setKeyListener(null);
                medico_salvar.setVisibility(View.GONE);
                medico_cancelar.setVisibility(View.GONE);

            }

            medico = realm.where(Medico.class).equalTo("id", idMedico).findFirst();
            medico_nome.setText(medico.getNome());
            medico_especialidade.setText(medico.getEspecialidade() + "");
            String[] array = medico.getTelefone().split("/");

            medico_telefone.setText(array.length == 0 ? "" : array[0]);
            medico_telefone2.setText(array.length < 2 ? "" : array[1]);

            medico_email.setText(medico.getEmail());
            medico_observacao.setText(medico.getObservacao());


        }

        icon_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (medico != null)
                    colorPicker(medico.getCorIndicativa());
                else
                    colorPicker(14560768);
            }
        });

    }

    @Override
    public void onDestroy() {
        if (realm != null)
            realm.close();

        super.onDestroy();
    }

    private void colorPicker(int color) {

        TypedArray cores = getResources().obtainTypedArray(R.array.cores);

        int idCor = cores.getResourceId(0, 0);

     /*   for (int i = 0; i < cores.length(); i++) {
            if (Integer.toHexString((cores.getInt(i, 0))).equalsIgnoreCase(color)) {
                idCor = cores.getResourceId(i, 0);
                break;
            }
        }*/

        for (int i = 0; i < cores.length(); i++) {
            if ((cores.getInt(i, 0)) == color) {
                idCor = cores.getResourceId(i, 0);
                break;
            }
        }

        Toast.makeText(getApplication(), "Color selected: #" + idCor, Toast.LENGTH_SHORT).show();
        cores.recycle();
        //getResources().getColor(R.color.cor_roxo_claro)
        new SpectrumDialog.Builder(this)
                .setTitle("Selecione uma cor")
                .setColors(R.array.cores)
                .setSelectedColorRes(idCor)
                //.setSelectedColor( getResources().getColor(idCor))
                .setDismissOnColorSelected(false)
                //.setOutlineWidth(8)
                .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(boolean positiveResult, @ColorInt int color) {

                        if (positiveResult) {
                            Toast.makeText(getApplication(), "Color selected: #" + Integer.toHexString(color).toUpperCase() + " " + color, Toast.LENGTH_SHORT).show();
                            View medico_cor = (View) findViewById(R.id.medico_cor);
                            medico_cor.setBackgroundColor(color);
                            corIndicativa = color;
                        }

                    }
                }).build().show(getSupportFragmentManager(), "Cor");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (idMedico == -1) {
            getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        } else if (editar) {
            getMenuInflater().inflate(R.menu.menu_editar, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_visualizar, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_salvar) {
            salvar();
            return true;
        } else if (id == R.id.action_editar) {

            finish();

            Intent i = new Intent(this, MedicoAdicionarEditar.class);
            i.putExtra("editar", true);
            i.putExtra("idMedico", idMedico);
            startActivity(i);

            return true;
        } else {
            if (idMedico == -1 || editar) {
                Dialogs.confirmarSaida(this);
            } else {

                Intent i = new Intent(this, Principal.class);
                i.putExtra("idNavTab", R.id.nav_atendente);
                startActivity(i);

                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar() {

        if (medico_nome.getText().toString().length() < 5) {
            Toast.makeText(this, "Preencha o nome do médico.", Toast.LENGTH_SHORT).show();
            return;
        }

        Medico medico = new Medico(idMedico, medico_nome.getText().toString(), medico_especialidade.getText().toString(),
                medico_telefone.getText().toString() + "/" + medico_telefone2.getText().toString(), medico_email.getText().toString(),
                medico_observacao.getText().toString(), corIndicativa);

        if (idMedico == -1) {

            new MedicoHandler().add(realm, medico);

        } else if (editar) {

            new MedicoHandler().update(realm, medico);

        }

        Toast.makeText(this, "Salvo.", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Principal.class);
        i.putExtra("idNavTab", R.id.nav_atendente);
        startActivity(i);

        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (idMedico == -1) {
                Dialogs.confirmarSaida(this);
                return true;
            }

        }

        finish();

        Intent i = new Intent(this, Principal.class);
        i.putExtra("idNavTab", R.id.nav_atendente);
        this.startActivity(i);

        return super.onKeyDown(keyCode, event);

    }

}
