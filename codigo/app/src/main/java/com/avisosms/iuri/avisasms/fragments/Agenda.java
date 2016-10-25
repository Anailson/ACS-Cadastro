package com.avisosms.iuri.avisasms.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
import com.avisosms.iuri.avisasms.adapters.AdapterListaDeMedicosAddPaciente;
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
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MM (MMMM) - yyyy", Locale.getDefault());
    Realm realm;
    private Date dataSelecionada ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getContext(), "onAttach", Toast.LENGTH_SHORT).show();
    }

    private void atualizarCalendario() {

        RealmResults<Consulta> consultas = realm.where(Consulta.class)
                // .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
                .findAll();
        for (Consulta consulta : consultas) {
            Medico medico = consulta.getMedico();
            compactCalendarView.addEvent(new Event(medico.getCorIndicativa(), consulta.getDataDoAtendimentoEmMilissegundo(), medico), true);
        }

        List<Event> bookingsFromMap = compactCalendarView.getEvents(Funcoes.dataHoje().getTime());
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
    AdapterListaDeMedicosAddPaciente adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.agenda, container, false);
        //view.refreshDrawableState();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        toolbar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarParaHoje();
            }
        });

        realm = Realm.getDefaultInstance();

        mutableBookings = new ArrayList<Medico>();

        final ListView listMedicosDoDia = (ListView) view.findViewById(R.id.agenda_list_agendamento_medicos);
        Button btnMostrarMesAnterior = (Button) view.findViewById(R.id.prev_button);
        Button btnMostraMesSeguinte = (Button) view.findViewById(R.id.next_button);

        // ImageButton btnAdicionarMedico = (ImageButton) view.findViewById(R.id.agenda_btn_add_medico);
        FloatingActionButton btnFloatAdicionarMedico = (FloatingActionButton) view.findViewById(R.id.agenda_floating_add_medico);

        Calendar calendar = Funcoes.dataHoje();
        dataSelecionada = calendar.getTime();


        adapter = new AdapterListaDeMedicosAddPaciente(view.getContext(), mutableBookings, Funcoes.dataBanco(dataSelecionada));
        listMedicosDoDia.setAdapter(adapter);

        atualizarCalendario();

        compactCalendarView.setDayColumnNames(new String[]{"dom", "seg", "ter", "qua", "qui", "sex", "sab"});

        // below allows you to configure color for the current day in the month
        //  compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.cor_preta));
        // below allows you to configure colors for the current day the user has selected
        // compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.cor_vermelho_escuro));

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
                voltarParaHoje();
            }
        });

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                dataSelecionada = dateClicked;
                //Log.d(TAG, "inside onclick " + dateClicked);
                if (bookingsFromMap != null) {
                    //Log.d(TAG, bookingsFromMap.toString() + Funcoes.dataBanco(dataSelecionada).getTime() );
                    mutableBookings.clear();
                    for (Event booking : bookingsFromMap) {
                        mutableBookings.add((Medico) booking.getData());
                    }
                    adapter = new AdapterListaDeMedicosAddPaciente(view.getContext(), mutableBookings, Funcoes.dataBanco(dataSelecionada));
                    listMedicosDoDia.setAdapter(adapter);
                   // adapter.notifyDataSetChanged();
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


        btnFloatAdicionarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialogs.medicoListSelecionar(v.getContext(), compactCalendarView, Funcoes.dataBanco(dataSelecionada));
                //Toast.makeText(getContext(), "Depois Dialog", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private void voltarParaHoje() {
        Calendar c = Funcoes.dataHoje();
        compactCalendarView.setCurrentDate(c.getTime());

        ((TextView) view.findViewById(R.id.agenda_meses)).setText(dateFormatForMonth.format(dataSelecionada));

        List<Event> bookingsFromMap = compactCalendarView.getEvents(c.getTime());
        if (bookingsFromMap != null) {
            //Log.d(TAG, bookingsFromMap.toString());
            mutableBookings.clear();
            for (Event booking : bookingsFromMap) {
                mutableBookings.add((Medico) booking.getData());
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
