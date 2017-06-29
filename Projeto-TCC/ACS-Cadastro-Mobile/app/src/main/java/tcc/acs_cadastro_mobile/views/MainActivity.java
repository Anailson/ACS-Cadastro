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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AgentModel agent = AcsRecordPersistence.startDatabase(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigation.getHeaderView(0);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.closeDrawer(GravityCompat.START);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigation.setNavigationItemSelectedListener(this);

        TextView txtAgentName = (TextView) headerView.findViewById(R.id.txt_agent_name);
        TextView txtAgentSusNum = (TextView) headerView.findViewById(R.id.txt_agent_sus_num);
        TextView txtArea = (TextView) headerView.findViewById(R.id.txt_area);
        TextView txtEquip = (TextView) headerView.findViewById(R.id.txt_equip);

        txtAgentName.setText(agent.getName());
        txtAgentSusNum.setText(String.valueOf(agent.getNumSus()));
        txtArea.setText(String.valueOf(agent.getArea()));
        txtEquip.setText(String.valueOf(agent.getEquip()));
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return selectMenu(item.getItemId());
    }

    private boolean selectMenu(int id){

        Fragment fragment = null;
        switch (id){
            case R.id.nav_citizen: fragment = new CitizenListFragment(); break;
            case R.id.nav_residence: fragment = new ResidenceListFragment();break;
            case R.id.nav_accompany: fragment = new AccompanyListFragment(); break;
            case R.id.nav_visit: fragment = new VisitListFragment(); break;
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