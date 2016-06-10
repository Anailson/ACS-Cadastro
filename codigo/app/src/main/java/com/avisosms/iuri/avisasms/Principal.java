package com.avisosms.iuri.avisasms;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.adapters.AdapterListaDePacientes;
import com.avisosms.iuri.avisasms.objetos.Paciente;
import com.avisosms.iuri.avisasms.suporte.ViewFlipperPaginas;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.widget.ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*TextView txt = (TextView) findViewById(R.id.textTeste);
        txt.setText("sadfasdfas");*/

        viewFlipper = (android.widget.ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.setDisplayedChild(0);
        ListaDePacientes();
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_agenda_do_dia) {

            viewFlipper.setDisplayedChild(0);

           ListaDePacientes();

        } else if (id == R.id.nav_agenda_da_semana) {

            viewFlipper.setDisplayedChild(1);

        } else if (id == R.id.nav_agenda) {

        } else if (id == R.id.nav_atendente) {

        } else if (id == R.id.nav_configuracao) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void ListaDePacientes() {
        final DynamicListView mDynamicListView = (DynamicListView) findViewById(R.id.DynamicListView);

        android.view.View view = LayoutInflater.from(this).inflate(R.layout.activity_dynamiclistview_header, mDynamicListView, false);
        /*O titulo esta dublicando a cada chamada
        TextView titulo_lista_pacientes = (TextView) view.findViewById(R.id.titulo_lista_paciente);
        titulo_lista_pacientes.setText(activity.getResources().getString(R.string.titulo_lista_pacientes, 5));*/

        mDynamicListView.addHeaderView(view);

        /* Setup the adapter */
        ArrayAdapter<Paciente> adapter = new AdapterListaDePacientes(this , null);
           /*SimpleSwipeUndoAdapter simpleSwipeUndoAdapter = new SimpleSwipeUndoAdapter(adapter, this, new MyOnDismissCallback(adapter));
            AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter(simpleSwipeUndoAdapter);
            animAdapter.setAbsListView(mDynamicListView);
            assert animAdapter.getViewAnimator() != null;
            animAdapter.getViewAnimator().setInitialDelayMillis(300);*/

        AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter(adapter);
        animAdapter.setAbsListView(mDynamicListView);
        mDynamicListView.setAdapter(animAdapter);


        mDynamicListView.enableDragAndDrop();
        mDynamicListView.setDraggableManager(new TouchViewDraggableManager(R.id.list_row_draganddrop_touchview));
        mDynamicListView.setOnItemMovedListener(new MyOnItemMovedListener(adapter, this));
        mDynamicListView.setOnItemLongClickListener(new MyOnItemLongClickListener(mDynamicListView));

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new MyOnItemClickListener(mDynamicListView));*/

        /*mDynamicListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(final AdapterView<?> parent, final android.view.View view,
                                                   final int position, final long id) {
                        mDynamicListView.startDragging(position - 1);
                        return true;
                    }
                }
        );*/

      /*  mDynamicListView.enableSwipeToDismiss(
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            adapter.remove(position);
                        }
                    }
                }
        );

        SimpleSwipeUndoAdapter swipeUndoAdapter = new SimpleSwipeUndoAdapter(adapter, activity,
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            adapter.remove(position);
                        }
                    }
                }
        );
        swipeUndoAdapter.setAbsListView(mDynamicListView);
        mDynamicListView.setAdapter(swipeUndoAdapter);
        mDynamicListView.enableSimpleSwipeUndo();*/
    }

    private class MyOnItemClickListener implements AdapterView.OnClickListener {

        private final DynamicListView mListView;

        MyOnItemClickListener(final DynamicListView listView) {
            mListView = listView;
        }

        @Override
        public void onClick(View v) {
            mListView.insert(mListView.getCount()-1, new Paciente("Item adicionado", "telefone"));

            Toast.makeText(v.getContext(), "Adicionar Joption para add Paciente", Toast.LENGTH_SHORT).show();
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }
    }

    private class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener {

        private final DynamicListView mListView;

        MyOnItemLongClickListener(final DynamicListView listView) {
            mListView = listView;
        }

        @Override
        public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
            if (mListView != null) {
                mListView.startDragging(position - mListView.getHeaderViewsCount());
            }
            return true;
        }
    }

    private class MyOnItemMovedListener implements OnItemMovedListener {

        private final ArrayAdapter<Paciente> mAdapter;

        private Toast mToast;
        private Activity mActivity;

        MyOnItemMovedListener(final ArrayAdapter<Paciente> adapter, Activity activity) {
            mAdapter = adapter;
            mActivity = activity;
        }

        @Override
        public void onItemMoved(final int originalPosition, final int newPosition) {
            if (mToast != null) {
                mToast.cancel();
            }

            mToast = Toast.makeText(mActivity, mActivity.getString(R.string.moved, mAdapter.getItem(newPosition).getNome(), newPosition), Toast.LENGTH_SHORT);
            mToast.show();

//            mToast = Toast.makeText(mActivity, originalPosition+ " to " + newPosition, Toast.LENGTH_SHORT);
//            mToast.show();
//
//            mToast = Toast.makeText(mActivity, mAdapter.getItem(newPosition).getNome() + " new Position ", Toast.LENGTH_SHORT);
//            mToast.show();

        }
    }
}
