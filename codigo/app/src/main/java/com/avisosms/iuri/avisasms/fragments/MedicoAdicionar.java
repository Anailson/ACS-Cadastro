package com.avisosms.iuri.avisasms.fragments;

import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
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
import com.avisosms.iuri.avisasms.suporte.Dialogs;
import com.thebluealliance.spectrum.SpectrumDialog;

/**
 * Created by iuri on 7/11/2016.
 */
public class MedicoAdicionar extends AppCompatActivity {

    EditText medico_nome;
    EditText medico_especialidade;
    EditText medico_telefone;
    EditText medico_telefone2;
    EditText medico_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medico_add_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Adicionar Médico(a)");

        medico_nome = (EditText) findViewById(R.id.medico_nome);
        medico_nome.getText().toString();

        medico_especialidade = (EditText) findViewById(R.id.medico_especialidade);
        medico_especialidade.getText().toString();

        medico_telefone = (EditText) findViewById(R.id.medico_telefone);
        medico_telefone.getText().toString();

        medico_telefone2 = (EditText) findViewById(R.id.medico_telefone2);
        medico_telefone2.getText().toString();

        medico_email = (EditText) findViewById(R.id.medico_email);
        medico_email.getText().toString();

        ImageView icon_medico = (ImageView) findViewById(R.id.icon_medico);
        icon_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPicker("FFEA80FC");
            }
        });

        Button medico_salvar = (Button) findViewById(R.id.medico_salvar);
        medico_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        Button medico_cancelar =  (Button) findViewById(R.id.medico_cancelar);
        medico_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogs.confirmarSaida(MedicoAdicionar.this);
            }
        });

    }

    private void colorPicker(String color) {

        TypedArray cores = getResources().obtainTypedArray(R.array.cores);

        int idCor = cores.getResourceId(0, 0);

        for (int i = 0; i < cores.length(); i++) {
            if(Integer.toHexString((cores.getInt(i,0))).equalsIgnoreCase(color)){
                idCor = cores.getResourceId(i, 0);
                break;
            }
        }

        Toast.makeText(getApplication(), "Color selected: #" + idCor , Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplication(), "Color selected: #" + Integer.toHexString(color).toUpperCase() + " " + color , Toast.LENGTH_SHORT).show();
                            View medico_cor = (View) findViewById(R.id.medico_cor);
                            medico_cor.setBackgroundColor(color);
                        }
                    }
                }).build().show(getSupportFragmentManager(), "Cor");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
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
        } else {
            //Toast.makeText(this, "Perguntar se deseja salvar", Toast.LENGTH_SHORT).show();
            Dialogs.confirmarSaida(this);
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(){
        Toast.makeText(this, "Salvar no Realm", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //Toast.makeText(this, "Saida", Toast.LENGTH_SHORT).show();
            Dialogs.confirmarSaida(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
