package com.avisosms.iuri.avisasms.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.adapters.AdapterListaDeMedicosAddPaciente;
import com.avisosms.iuri.avisasms.adapters.AdapterSelecionarMedico;
import com.avisosms.iuri.avisasms.compactcalendarview.CompactCalendarView;
import com.avisosms.iuri.avisasms.compactcalendarview.Event;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.suporte.Funcoes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private Date dataSelecionada;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void atualizarCalendario() {

        RealmResults<Consulta> consultas = realm.where(Consulta.class)
                // .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
                .findAll();

        //realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", dataSelecionada.getTime()).findAll();

        List<Medico> medicos = new ArrayList<Medico>();
        Medico medico;
        for (Consulta consulta : consultas) {
            medico = consulta.getMedico();
            long data = consulta.getDataDoAtendimentoEmMilissegundo();
            if (Funcoes.dataHoje().getTimeInMillis() == data) {
                medicos.add(medico);
            }

            compactCalendarView.addEvent(new Event(medico.getCorIndicativa(), data, medico), true);

        }
        listaDeMedicos = medicos;

        /*List<Event> bookingsFromMap = compactCalendarView.getEvents(Funcoes.dataHoje().getTime());
        if (bookingsFromMap != null) {
            Log.d(TAG, bookingsFromMap.toString());
            listaDeMedicos.clear();
            for (Event booking : bookingsFromMap) {
                listaDeMedicos.add((Medico) booking.getData());
            }
            adapterListaDeMedicos.notifyDataSetChanged();
        }*/

    }


    private static CompactCalendarView compactCalendarView;
    private List<Medico> listaDeMedicos;
    private static AdapterListaDeMedicosAddPaciente adapterListaDeMedicos;
    private static ListView listMedicosDoDia;


    //Otimizar para nao utilizar metodo estatico
    public static void atualizarListaMedicoDoDia(Context context, Realm realm, Date data) {


        RealmResults<Consulta> consultas = realm.where(Consulta.class)
                // .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
                .findAll();

        //realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", dataSelecionada.getTime()).findAll();
        compactCalendarView.removeAllEvents();
        List<Medico> medicos = new ArrayList<Medico>();
        Medico medico;
        for (Consulta consulta : consultas) {
            medico = consulta.getMedico();
            long data2 = consulta.getDataDoAtendimentoEmMilissegundo();
            if (Funcoes.dataHoje().getTimeInMillis() == data2) {
                medicos.add(medico);
            }

            compactCalendarView.addEvent(new Event(medico.getCorIndicativa(), data2, medico), true);

        }
      /*
        RealmResults<Consulta> consultas = realm.where(Consulta.class)
                .equalTo("dataDoAtendimentoEmMilissegundo", data.getTime())
                .findAll();
        List<Medico> medicos = new ArrayList<Medico>();
        Medico medico;
        for (Consulta consulta : consultas) {
            medico = consulta.getMedico();
            medicos.add(medico);
        }*/
        adapterListaDeMedicos = new AdapterListaDeMedicosAddPaciente(context, realm, medicos, data);
        listMedicosDoDia.setAdapter(adapterListaDeMedicos);


    }

    @Override
    public void onStart() {
        super.onStart();

        Toast.makeText(getContext(), "Start", Toast.LENGTH_SHORT).show();
    }

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

        listaDeMedicos = new ArrayList<Medico>();

        listMedicosDoDia = (ListView) view.findViewById(R.id.agenda_list_agendamento_medicos);
        Button btnMostrarMesAnterior = (Button) view.findViewById(R.id.prev_button);
        Button btnMostraMesSeguinte = (Button) view.findViewById(R.id.next_button);

        FloatingActionButton btnFloatAdicionarMedico = (FloatingActionButton) view.findViewById(R.id.agenda_floating_add_medico);

        btnFloatAdicionarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*long data = Funcoes.dataBanco(dataSelecionada).getTime();
                Intent i = new Intent(getActivity(), AgendaAdicionarEditarMedico.class);
                i.putExtra("dataSelecionada", data);
                startActivity(i);*/
                // getActivity().finish();

                medicoListSelecionar(v.getContext(), compactCalendarView, Funcoes.dataBanco(dataSelecionada));
                //Toast.makeText(getContext(), "Depois Dialog", Toast.LENGTH_SHORT).show();

            }
        });


        //Atualizar Calendrio
        atualizarCalendario();

        Calendar calendar = Funcoes.dataHoje();
        dataSelecionada = calendar.getTime();


        adapterListaDeMedicos = new AdapterListaDeMedicosAddPaciente(view.getContext(), realm, listaDeMedicos, Funcoes.dataBanco(dataSelecionada));
        listMedicosDoDia.setAdapter(adapterListaDeMedicos);


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
                    listaDeMedicos.clear();
                    for (Event booking : bookingsFromMap) {
                        listaDeMedicos.add((Medico) booking.getData());
                    }
                    adapterListaDeMedicos = new AdapterListaDeMedicosAddPaciente(view.getContext(), realm, listaDeMedicos, Funcoes.dataBanco(dataSelecionada));
                    listMedicosDoDia.setAdapter(adapterListaDeMedicos);
                    // adapterListaDePacientes.notifyDataSetChanged();
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


        return view;
    }

    private void voltarParaHoje() {
        Calendar c = Funcoes.dataHoje();
        compactCalendarView.setCurrentDate(c.getTime());

        ((TextView) view.findViewById(R.id.agenda_meses)).setText(dateFormatForMonth.format(dataSelecionada));

        List<Event> bookingsFromMap = compactCalendarView.getEvents(c.getTime());
        if (bookingsFromMap != null) {
            //Log.d(TAG, bookingsFromMap.toString());
            listaDeMedicos.clear();
            for (Event booking : bookingsFromMap) {
                listaDeMedicos.add((Medico) booking.getData());
            }
            adapterListaDeMedicos.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    //TODO simplificar
    public void medicoListSelecionar(final Context context, final CompactCalendarView calendar, Date data) {// final AdapterListaLeis adapterListaDePacientes

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(data);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.medico_list_check)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_medico));

        ListView mListView = (ListView) dialog.findViewById(R.id.medico_listview_check);

        TextView txt = (TextView) dialog.findViewById(R.id.medico_emptyText);
        txt.setText(R.string.nenhum_medico_cadastrado);
        mListView.setEmptyView(txt);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Medico> medicos = realm.where(Medico.class).findAll();
        realm.close();

        AdapterSelecionarMedico arrayAdapter = new AdapterSelecionarMedico(context, R.id.medico_listview_check, medicos, data.getTime(), adapterListaDeMedicos);
        mListView.setAdapter(arrayAdapter);

        //Button cancelar
        Button btnOk = (Button) dialog.findViewById(R.id.categoria_list_btn_cancelar);
        assert btnOk != null;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //android.support.v4.app.FragmentManager fragmentManager = fragmentManager;
                //  fragmentManager.beginTransaction().replace(R.id.content_frame, new Agenda()).commit();

            }
        });

        //Button para adicionar um medico para o dia
        Button btnAdicionarMedico = (Button) dialog.findViewById(R.id.categoria_list_btn_adicionar);

        btnAdicionarMedico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //fragmentManager.beginTransaction().replace(R.id.content_frame, new Agenda()).commit();
            }
        });

    }
}
