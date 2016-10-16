package com.avisosms.iuri.avisasms.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.adapters.AdapterListaAgendaDeMedicos;
import com.avisosms.iuri.avisasms.compactcalendarview.CompactCalendarView;
import com.avisosms.iuri.avisasms.compactcalendarview.Event;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.suporte.Dialogs;
import com.avisosms.iuri.avisasms.suporte.Funcoes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 7/5/2016.
 */
public class Agenda extends Fragment {


    View view;
    //TODO Agenda
    private static final String TAG = "Agenda";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MM(MMMM) - yyyy", Locale.getDefault());
    private boolean shouldShow = false;
    Realm realm;
    private Date dataSelecionada;

    @Override
    public void onStart() {
        super.onStart();
        atualizarCalendario();

        Toast.makeText(getContext(), "OnStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getContext(), "onAttach", Toast.LENGTH_SHORT).show();
    }

    private void atualizarCalendario(){

        Calendar calendar = Funcoes.dataHoje();
        dataSelecionada = calendar.getTime();
        RealmResults<Consulta> consultas = realm.where(Consulta.class)
                // .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
                .findAll();
        for (Consulta consulta : consultas) {
            Medico medico = consulta.getMedico();
            compactCalendarView.addEvent(new Event(medico.getCorIndicativa(), consulta.getDataDoAtendimentoEmMilissegundo(), medico), true);
        }

        List<Event> bookingsFromMap = compactCalendarView.getEvents(calendar.getTime());
        if (bookingsFromMap != null) {
            Log.d(TAG, bookingsFromMap.toString());
            mutableBookings.clear();
            for (Event booking : bookingsFromMap) {
                mutableBookings.add((Medico) booking.getData());
            }
            adapter.notifyDataSetChanged();
        }

    }

    CompactCalendarView compactCalendarView;
    List<Medico> mutableBookings;
    AdapterListaAgendaDeMedicos adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.agenda, container, false);
        view.refreshDrawableState();

        mutableBookings = new ArrayList<Medico>();

        ListView listMedicosDoDia = (ListView) view.findViewById(R.id.agenda_list_agendamento_medicos);
        Button btnMostrarMesAnterior = (Button) view.findViewById(R.id.prev_button);
        Button btnMostraMesSeguinte = (Button) view.findViewById(R.id.next_button);

        // ImageButton btnAdicionarMedico = (ImageButton) view.findViewById(R.id.agenda_btn_add_medico);
        FloatingActionButton btnFloatAdicionarMedico = (FloatingActionButton) view.findViewById(R.id.agenda_floating_add_medico);

        adapter = new AdapterListaAgendaDeMedicos(view.getContext(), mutableBookings);
        listMedicosDoDia.setAdapter(adapter);

        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);

        compactCalendarView.setDayColumnNames(new String[]{"dom", "seg", "ter", "qua", "qui", "sex", "sab"});

        // below allows you to configure color for the current day in the month
        //  compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.cor_preta));
        // below allows you to configure colors for the current day the user has selected
        // compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.cor_vermelho_escuro));

        realm = Realm.getDefaultInstance();


        atualizarCalendario();
/*
        View rootView = inflater.inflate(R.layout.consulta_lista_de_pacientes, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(consulta.getMedico().getNome() + " numSecao " + numeroSecao);*/

       /* addEvents(compactCalendarView, -1);
        addEvents(compactCalendarView, Calendar.DECEMBER);
        addEvents(compactCalendarView, Calendar.AUGUST);
        addEvents(compactCalendarView, Calendar.MAY);
        addEvents(compactCalendarView, Calendar.MARCH);
        addEvents(compactCalendarView, Calendar.APRIL);
        addEvents(compactCalendarView, Calendar.JANUARY);
        addEvents(compactCalendarView, Calendar.JULY);
        addEvents(compactCalendarView, Calendar.SEPTEMBER);
        addEvents(compactCalendarView, Calendar.NOVEMBER);
        addEvents(compactCalendarView, Calendar.FEBRUARY);*/

        //compactCalendarView.invalidate();

        // below line will display Sunday as the first day of the week
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        //set initial title
        // actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        TextView titulo = (TextView) view.findViewById(R.id.agenda_meses);
        titulo.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Funcoes.dataHoje();
                compactCalendarView.setCurrentDate(c.getTime());

                List<Event> bookingsFromMap = compactCalendarView.getEvents(c.getTime());
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    mutableBookings.clear();
                    for (Event booking : bookingsFromMap) {
                        mutableBookings.add((Medico) booking.getData());
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });


        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                dataSelecionada = dateClicked;
                Log.d(TAG, "inside onclick " + dateClicked);
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    mutableBookings.clear();
                    for (Event booking : bookingsFromMap) {
                        mutableBookings.add((Medico) booking.getData());
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                ((TextView) view.findViewById(R.id.agenda_meses)).setText(dateFormatForMonth.format(firstDayOfNewMonth));
                //actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        btnMostrarMesAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showPreviousMonth();
            }
        });

        btnMostraMesSeguinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showNextMonth();
            }
        });

      /*  btnAdicionarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (shouldShow) {
                    compactCalendarView.showCalendar();
                } else {
                    compactCalendarView.hideCalendar();
                }
                shouldShow = !shouldShow;
            }
        });*/


        btnFloatAdicionarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialogs.medicoListSelecionar(v.getContext(), getActivity().getSupportFragmentManager(), Funcoes.dataBanco(dataSelecionada));
                //Toast.makeText(getContext(), "Depois Dialog", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
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
            return Arrays.asList(new Event(Color.argb(255, 169, 68, 255), timeInMillis, "Medico 1"));
        } else if (day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Medico 1"),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Medico 2"));
        } else if (day > 5 && day <= 10) {
            return Arrays.asList(
                    new Event(Color.argb(255, 10, 68, 56), timeInMillis, "Medico 1"),
                    new Event(Color.argb(50, 100, 68, 65), timeInMillis, "Medico 2"),
                    new Event(Color.argb(100, 70, 5, 56), timeInMillis, "Medico 3"),
                    new Event(Color.argb(6, 80, 68, 65), timeInMillis, "Medico 4"));
        } else {
            return Arrays.asList(
                    new Event(Color.argb(255, 255, 0, 65), timeInMillis, "Medico 1"),
                    new Event(Color.argb(255, 255, 255, 65), timeInMillis, "Medico 2"),
                    new Event(Color.argb(100, 0, 100, 65), timeInMillis, "Medico 3"));

        }
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    @Override
    public void onDestroy() {

        realm.close();

        super.onDestroy();
    }
}
