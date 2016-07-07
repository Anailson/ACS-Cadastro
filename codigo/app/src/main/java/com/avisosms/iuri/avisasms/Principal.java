package com.avisosms.iuri.avisasms;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.adapters.AdapterListaDeMedicos;
import com.avisosms.iuri.avisasms.adapters.AdapterListaDePacientes;
import com.avisosms.iuri.avisasms.compactcalendarview.CompactCalendarView;
import com.avisosms.iuri.avisasms.compactcalendarview.Event;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;
import com.avisosms.iuri.avisasms.suporte.BancoDeDados;
import com.avisosms.iuri.avisasms.suporte.Funcoes;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

   // android.widget.ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean valoresDefault = Funcoes.getPreferences(this).getBoolean("valoresDefault", false);

        BancoDeDados.ConfigurarBanco(this);

        if (!valoresDefault) {
            BancoDeDados.AdicionarTesteDados();
            Funcoes.setPreference(this, "valoresDefault", true);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*TextView txt = (TextView) findViewById(R.id.textTeste);
        txt.setText("sadfasdfas");*/

        /*viewFlipper = (android.widget.ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.setDisplayedChild(0);

        ListaDePacientes();
        listarPacientesDoDia(3);
        medicos();
        agenda();*/

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

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_consulta_do_dia) {

            setTitle(getString(R.string.consultas_do_dia));
            fragmentManager.beginTransaction().replace(R.id.content_frame, new PrincipalConsultasDoDia()).commit();

            /*viewFlipper.setDisplayedChild(0);*/

            Toast.makeText(Principal.this, "Atualizar cosulta dia", Toast.LENGTH_SHORT).show();
            //ListaDePacientes();

        } else if (id == R.id.nav_agenda) {

            setTitle(getString(R.string.agenda));
          //  viewFlipper.setDisplayedChild(1);
            fragmentManager.beginTransaction().replace(R.id.content_frame, new PrincipalConsultasDoDia()).commit();
            Toast.makeText(Principal.this, "Atualizar agenda", Toast.LENGTH_SHORT).show();

            //listarPacientesDoDia(6);
            //startActivity(new Intent(this, Calendario.class));

        } else if (id == R.id.nav_lembretes) {

            setTitle(getString(R.string.lembretes));
          //  viewFlipper.setDisplayedChild(2);

            Toast.makeText(Principal.this, "atualizar lembrete", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this, CalendarioGoogleAPi.class));

        } else if (id == R.id.nav_atendente) {

            setTitle(getString(R.string.medicos));
          //  viewFlipper.setDisplayedChild(3);
            // startActivity(new Intent(this, Fragmento_tab.class));

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



//
//
//    //TODO lembretes
//    public void ListaDePacientes() {
//        final DynamicListView mDynamicListView = (DynamicListView) findViewById(R.id.DynamicListView);
//
//        /*android.view.View view = LayoutInflater.from(this).inflate(R.layout.activity_dynamiclistview_header, mDynamicListView, false);
//        /*O titulo esta dublicando a cada chamada
//        TextView titulo_lista_pacientes = (TextView) view.findViewById(R.id.titulo_lista_paciente);
//        titulo_lista_pacientes.setText(activity.getResources().getString(R.string.titulo_lista_pacientes, 5));
//
//        mDynamicListView.addHeaderView(view);*/
//
//        Toast.makeText(this, "Atualizado", Toast.LENGTH_SHORT).show();
//
//        List<Paciente> objects = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            objects.add(new Paciente(" Lembrete:", i + ":" + i * 0.3 + "  " + 1 + "/06/2016", i));
//        }
//
//
//        /* Setup the adapter */
//        ArrayAdapter<Paciente> adapter = new AdapterListaDePacientes(this, objects);
//           /*SimpleSwipeUndoAdapter simpleSwipeUndoAdapter = new SimpleSwipeUndoAdapter(adapter, this, new MyOnDismissCallback(adapter));
//            AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter(simpleSwipeUndoAdapter);
//            animAdapter.setAbsListView(mDynamicListView);
//            assert animAdapter.getViewAnimator() != null;
//            animAdapter.getViewAnimator().setInitialDelayMillis(300);*/
//
//        AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter(adapter);
//        animAdapter.setAbsListView(mDynamicListView);
//        mDynamicListView.setAdapter(animAdapter);
//
//
//        mDynamicListView.enableDragAndDrop();
//        mDynamicListView.setDraggableManager(new TouchViewDraggableManager(R.id.list_row_draganddrop_touchview));
//        mDynamicListView.setOnItemMovedListener(new MyOnItemMovedListener(adapter, this));
//        mDynamicListView.setOnItemLongClickListener(new MyOnItemLongClickListener(mDynamicListView));
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.principal_content_fab);
//        fab.setOnClickListener(new MyOnItemClickListener(mDynamicListView, adapter));
//
//        /*mDynamicListView.setOnItemLongClickListener(
//                new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(final AdapterView<?> parent, final android.view.View view,
//                                                   final int position, final long id) {
//                        mDynamicListView.startDragging(position - 1);
//                        return true;
//                    }
//                }
//        );*/
//
//      /*  mDynamicListView.enableSwipeToDismiss(
//                new OnDismissCallback() {
//                    @Override
//                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
//                        for (int position : reverseSortedPositions) {
//                            adapter.remove(position);
//                        }
//                    }
//                }
//        );
//
//        SimpleSwipeUndoAdapter swipeUndoAdapter = new SimpleSwipeUndoAdapter(adapter, activity,
//                new OnDismissCallback() {
//                    @Override
//                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
//                        for (int position : reverseSortedPositions) {
//                            adapter.remove(position);
//                        }
//                    }
//                }
//        );
//        swipeUndoAdapter.setAbsListView(mDynamicListView);
//        mDynamicListView.setAdapter(swipeUndoAdapter);
//        mDynamicListView.enableSimpleSwipeUndo();*/
//    }

    private class MyOnItemClickListener implements AdapterView.OnClickListener {

        private final DynamicListView mListView;
        ArrayAdapter adapter;

        MyOnItemClickListener(final DynamicListView listView, ArrayAdapter adapter) {
            mListView = listView;
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            mListView.insert(mListView.getCount(), new Paciente("Item adicionado", "telefone", 1));

            Toast.makeText(v.getContext(), "Adicionar Joption para add Paciente", Toast.LENGTH_SHORT).show();
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            mListView.post(new Runnable() {
                @Override
                public void run() {
                    // Select the last row so it will scroll into view...
                    mListView.setSelection(adapter.getCount() - 1);
                }
            });
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


    //TODO medicos
    void medicos() {

        ListView mListView = (ListView) findViewById(R.id.medicos_listview);
        List<Medico> objects = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            objects.add(new Medico(" Jonathas Martins", "Fonoaldiologo", "(79) 9 9670-2237"));
        }

        /* Setup the adapter */
        AdapterListaDeMedicos adapter = new AdapterListaDeMedicos(this, objects);
        mListView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.medico_layout_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(view.getContext());

                dialog.setContentView(R.layout.medico_add_layout);//carregando o layout do dialog do xml
                dialog.setTitle("Adicionar médico");

                dialog.show();
            }
        });


    }



    //TODO Agenda
    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MM(MMMM) - yyyy", Locale.getDefault());
    private boolean shouldShow = false;


    protected void agenda() {


        final List<String> mutableBookings = new ArrayList<>();

        final ListView bookingsListView = (ListView) findViewById(R.id.bookings_listview);
        final Button showPreviousMonthBut = (Button) findViewById(R.id.prev_button);
        final Button showNextMonthBut = (Button) findViewById(R.id.next_button);
        final Button slideCalendarBut = (Button) findViewById(R.id.slide_calendar);
        final Button showCalendarWithAnimationBut = (Button) findViewById(R.id.show_with_animation_calendar);

        final android.widget.ArrayAdapter adapter = new android.widget.ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mutableBookings);
        bookingsListView.setAdapter(adapter);
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        compactCalendarView.setDayColumnNames(new String[]{"dom", "seg", "ter", "qua", "qui", "sex", "sab"});

        // below allows you to configure color for the current day in the month
        //  compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.cor_preta));
        // below allows you to configure colors for the current day the user has selected
        // compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.cor_vermelho_escuro));

        addEvents(compactCalendarView, -1);
        addEvents(compactCalendarView, Calendar.DECEMBER);
        addEvents(compactCalendarView, Calendar.AUGUST);
        addEvents(compactCalendarView, Calendar.MAY);
        addEvents(compactCalendarView, Calendar.MARCH);
        addEvents(compactCalendarView, Calendar.APRIL);
        addEvents(compactCalendarView, Calendar.JANUARY);
        addEvents(compactCalendarView, Calendar.JULY);
        addEvents(compactCalendarView, Calendar.SEPTEMBER);
        addEvents(compactCalendarView, Calendar.NOVEMBER);
        addEvents(compactCalendarView, Calendar.FEBRUARY);

        compactCalendarView.invalidate();

        // below line will display Sunday as the first day of the week
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        //set initial title
        // actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        ((TextView) findViewById(R.id.agenda_meses)).setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "inside onclick " + dateClicked);
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    mutableBookings.clear();
                    for (Event booking : bookingsFromMap) {
                        mutableBookings.add((String) booking.getData());
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                ((TextView) findViewById(R.id.agenda_meses)).setText(dateFormatForMonth.format(firstDayOfNewMonth));
                //actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showPreviousMonth();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showNextMonth();
            }
        });

        slideCalendarBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldShow) {
                    compactCalendarView.showCalendar();
                } else {
                    compactCalendarView.hideCalendar();
                }
                shouldShow = !shouldShow;
            }
        });

        showCalendarWithAnimationBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldShow) {
                    compactCalendarView.showCalendarWithAnimation();
                } else {
                    compactCalendarView.hideCalendarWithAnimation();
                }
                shouldShow = !shouldShow;
            }
        });

    }

    private void addEvents(CompactCalendarView compactCalendarView, int month) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for (int i = 0; i < 20; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
            currentCalender.add(Calendar.DATE, i);
            setToMidnight(currentCalender);
            long timeInMillis = currentCalender.getTimeInMillis();

            List<Event> events = getEvents(timeInMillis, i);

            compactCalendarView.addEvents(events);
        }
    }

    private List<Event> getEvents(long timeInMillis, int day) {
        if (day < 2) {
            return Arrays.asList(new Event(Color.argb(255, 169, 68, 255), timeInMillis, "Event at " + new Date(timeInMillis)));
        } else if (day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)));
        } else if (day > 5 && day <= 10) {
            return Arrays.asList(
                    new Event(Color.argb(255, 10, 68, 56), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(50, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)),
                    new Event(Color.argb(100, 70, 5, 56), timeInMillis, "Event 3 at " + new Date(timeInMillis)),
                    new Event(Color.argb(6, 80, 68, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)));
        } else {
            return Arrays.asList(
                    new Event(Color.argb(255, 255, 0, 65), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 255, 255, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)),
                    new Event(Color.argb(100, 0, 100, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)));

        }
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
