package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.persistence.AcsCadastroPersistence;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.closeDrawer(GravityCompat.START);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigation.setNavigationItemSelectedListener(this);

        AcsCadastroPersistence.startDatabase(this);
        /*TextView txtAgentName = (TextView) navigation.findViewById(R.id.txt_agent_name);
        TextView txtAgentSusNum = (TextView) navigation.findViewById(R.id.txt_agent_sus_num);
        TextView txtArea = (TextView) navigation.findViewById(R.id.txt_area);
        TextView txtEquip = (TextView) navigation.findViewById(R.id.txt_equip);

        txtAgentName.setText("Olivia Siqueira Campos");
        txtAgentSusNum.setText("846735257");
        txtArea.setText("120");
        txtEquip.setText("12");*/
        selectMenu(R.id.nav_citizen);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return id == R.id.menu_settings || super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return selectMenu(item.getItemId());
    }

    private boolean selectMenu(int id){

        Fragment fragment = null;
        switch (id){
            case R.id.nav_citizen: fragment = new CitizenListFragment(); break;
            case R.id.nav_residence: fragment = new ResidenceListFragment();break;
            case R.id.nav_accompany: fragment = new AccompanyFragment(); break;
            case R.id.nav_visit: fragment = new VisitFragment(); break;
            case R.id.nav_update_system: fragment = new UpdateSystemFragment(); break;
        }
        return replaceFragment(fragment) > 0;
    }

    private int replaceFragment(Fragment fragment){
        if(fragment == null)
            return -1;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }
}