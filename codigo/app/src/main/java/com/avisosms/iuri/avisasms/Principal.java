package com.avisosms.iuri.avisasms;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.fragments.Calendario;
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

        BancoDeDados.ConfigurarBanco(this);

//        if (!valoresDefault) {
            BancoDeDados.AdicionarTesteDados();
            Funcoes.setPreference(this, "valoresDefault", true);

//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Instancia a tela inicial de consultas
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Consultas()).commit();

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

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Consultas()).commit();
        if (id == R.id.nav_consulta_do_dia) {

            setTitle(getString(R.string.consultas_do_dia));


            Toast.makeText(Principal.this, "Cosulta dia", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_agenda) {

            setTitle(getString(R.string.agenda));
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Calendario()).commit();
            Toast.makeText(Principal.this, "Atualizar agenda", Toast.LENGTH_SHORT).show();

        } /*else if (id == R.id.nav_lembretes) {

            setTitle(getString(R.string.lembretes));
            Toast.makeText(Principal.this, "atualizar lembrete", Toast.LENGTH_SHORT).show();

        } */else if (id == R.id.nav_atendente) {

            setTitle(getString(R.string.medicos));
          //  viewFlipper.setDisplayedChild(3);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Medicos()).commit();

        } else if (id == R.id.nav_configuracao) {

            setTitle(getString(R.string.configuracoes));

        } else if (id == R.id.nav_share) {

            setTitle(getString(R.string.compartilhar));

        } else if (id == R.id.nav_send) {

            setTitle(getString(R.string.enviar_por_email));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
