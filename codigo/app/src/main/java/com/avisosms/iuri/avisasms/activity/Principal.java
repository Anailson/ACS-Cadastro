package com.avisosms.iuri.avisasms.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.fragments.Agenda;
import com.avisosms.iuri.avisasms.fragments.Consultas;
import com.avisosms.iuri.avisasms.fragments.Medicos;
import com.avisosms.iuri.avisasms.suporte.BancoDeDados;
import com.avisosms.iuri.avisasms.suporte.Funcoes;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean valoresDefault = Funcoes.getPreferences(this).getBoolean("valoresDefault", false);


        //Pedir permssão para enviar SMS
        if (!(PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS))) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        //BancoDeDados.ConfigurarBanco(this);
        //  if (!valoresDefault) {

//        BancoDeDados.AdicionarTesteDados();
//        Funcoes.setPreference(this, "valoresDefault", true);

        //  }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int idNavTab = getIntent().getIntExtra("idNavTab", -1);

        /*if (idNavTab != -1)*/
        selecionarNavTab(idNavTab);

        //Instancia a tela inicial de consultas
       /* else
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Consultas()).commit();*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        selecionarNavTab(id);
        return true;

    }

    public void selecionarNavTab(int idNavTab) {

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        switch (idNavTab) {
            case R.id.nav_consulta_do_dia:

                setTitle(getString(R.string.consultas_do_dia));
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Consultas()).commit();
                Toast.makeText(Principal.this, "Consulta dia", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_agenda:

                setTitle(getString(R.string.agenda));
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Agenda()).commit();
                Toast.makeText(Principal.this, "Atualizar agenda", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_atendente:

                setTitle(getString(R.string.medicos));
                //  viewFlipper.setDisplayedChild(3);
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Medicos()).commit();

                break;
            case R.id.nav_share:

                setTitle(getString(R.string.compartilhar));

                break;
            case R.id.nav_send:

                setTitle(getString(R.string.enviar_por_email));

                break;
            case R.id.nav_configuracao:

                break;

            default:
                setTitle(getString(R.string.consultas_do_dia));
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Consultas()).commit();
                Toast.makeText(Principal.this, "Consulta dia", Toast.LENGTH_SHORT).show();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

}
