package com.avisosms.iuri.avisasms.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.adapters.AdapterAdicionarPacienteConsulta;
import com.avisosms.iuri.avisasms.adapters.AdapterListaDePacientes;
import com.avisosms.iuri.avisasms.dataHandler.PacienteHandler;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;
import com.avisosms.iuri.avisasms.suporte.Funcoes;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by iuri on 6/18/2016.
 */

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ConsultasListaFragmento extends FragmentPagerAdapter {

    static int tabQuantidade;

    public ConsultasListaFragmento(FragmentManager fm, int tabQuantidade) {
        super(fm);
        this.tabQuantidade = tabQuantidade;
    }


    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return tabQuantidade;//3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
            default:
                return "default";
        }
    }

    @Override
    public int getItemPosition(Object object) {

        return super.getItemPosition(object);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        Realm realm;
        AdapterListaDePacientes adapterListaDePacientes;
        AlphaInAnimationAdapter animAdapter;
        DynamicListView mDynamicListView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            realm = Realm.getDefaultInstance();

            final int numeroSecao = getArguments().getInt(ARG_SECTION_NUMBER);

            final Calendar calendar = Funcoes.dataHoje();

            RealmResults<Consulta> consultas = realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis()).findAll();

            final Consulta consulta = consultas.get(numeroSecao);

            final View rootView = inflater.inflate(R.layout.consulta_lista_de_pacientes, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(consulta.getMedico().getNome());//consulta.getMedico().getCorIndicativa() + "  " +  + " numSecao " + numeroSecao);

            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.fragment_tab_layout_indicadores);
            float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
            /*LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.height = (int) pixels;
            params.weight = (int) pixels;*/

            //static int id = 1;// getResources().getIdentifier("gameover", "drawable", getPackageName());
            for (int i = 0; i < tabQuantidade; i++) {
                ImageView imgView = new ImageView(getContext());
                //imgView.setId(0x7f0d0079);
                imgView.setImageResource(R.drawable.icon_clipboard);
                imgView.setLayoutParams(new LinearLayout.LayoutParams((int) pixels, (int) pixels));//new LinearLayout.LayoutParams(30, 30));

                if (i == numeroSecao) {
                    int color = getResources().getColor(R.color.colorAccent); //The color u want
                    imgView.setColorFilter(color);
                }

                linearLayout.addView(imgView);

            }

            ((View) rootView.findViewById(R.id.consulta_medico_cor)).setBackgroundColor(consulta.getMedico().getCorIndicativa());
            ///////List
            mDynamicListView = (DynamicListView) rootView.findViewById(R.id.fragment_lista_dynamicListView);


            adapterListaDePacientes = new AdapterListaDePacientes(rootView.getContext()
                    , consulta.getPacientes().where().greaterThan("ordem", 0).findAllSorted("ordem").sort("atendido", Sort.DESCENDING)
                    , consulta.getId(), consulta.getMedico().getId());

            animAdapter = new AlphaInAnimationAdapter(adapterListaDePacientes);
            animAdapter.setAbsListView(mDynamicListView);
            mDynamicListView.setAdapter(animAdapter);

            adapterListaDePacientes.notifyDataSetInvalidated();


            mDynamicListView.enableDragAndDrop();
            mDynamicListView.setDraggableManager(new TouchViewDraggableManager(R.id.list_row_draganddrop_touchview));
            /*mDynamicListView.setOnItemMovedListener(new MyOnItemMovedListener(adapterListaDePacientes, this));
            mDynamicListView.setOnItemLongClickListener(new MyOnItemLongClickListener(mDynamicListView));*/

            // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.principal_content_fab);
            // fab.setOnClickListener(new MyOnItemClickListener(mDynamicListView, adapterListaDePacientes));

            //adapterListaDePacientes.notifyDataSetInvalidated();
          /*  Toast.makeText(getContext(), " " + getArguments().getInt(ARG_SECTION_NUMBER), Toast.LENGTH_SHORT).show();*/

            Button btn_ordenar = (Button) rootView.findViewById(R.id.btn_ordenar);
            btn_ordenar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    adapterListaDePacientes = new AdapterListaDePacientes(rootView.getContext()
                            , consulta.getPacientes().where().greaterThan("ordem", 0).findAllSorted("ordem").sort("atendido", Sort.DESCENDING)
                            , consulta.getId(), consulta.getMedico().getId());

                    mDynamicListView.setAdapter(adapterListaDePacientes);
                    mDynamicListView.invalidate();
                    mDynamicListView.setSelection(mDynamicListView.getCount());
                    Toast.makeText(v.getContext(), "Color selected: #", Toast.LENGTH_SHORT).show();

                }
            });

            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fragment_list_fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    /*Intent i = new Intent(getActivity(), AdicionarPacienteAFila.class);
                    i.putExtra("dataSelecionada", calendar.getTimeInMillis());
                    startActivity(i);*/
                    // getActivity().finish();

                    RealmResults<Consulta> consultas = realm.where(Consulta.class).equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis()).findAll();

                    Consulta consulta = consultas.get(numeroSecao);

                    listarPacientesAddConsulta(consulta);

                    onStop();

                    /*Snackbar.make(view, "Add <> paciente, para o médico " + consulta.getMedico().getId(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/

                }
            });

            return rootView;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (realm != null)
                realm.close();
        }

        @Override
        public void onStart() {
            super.onStart();

            Toast.makeText(getContext(), "On Start", Toast.LENGTH_SHORT).show();
        }

        private class MyOnItemClickListener implements AdapterView.OnClickListener {

            private final DynamicListView mListView;
            ArrayAdapter adapter;

            MyOnItemClickListener(final DynamicListView listView, ArrayAdapter adapter) {
                mListView = listView;
                this.adapter = adapter;
            }

            @Override
            public void onClick(View v) {
                mListView.insert(mListView.getCount(), new Paciente("Item adicionado", "telefone", 1, 0));

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

               /* mToast = Toast.makeText(mActivity, mActivity.getString(R.string.moved, mAdapter.getItem(newPosition).getNome(), newPosition), Toast.LENGTH_SHORT);
                mToast.show();
*/

//            mToast = Toast.makeText(mActivity, originalPosition+ " to " + newPosition, Toast.LENGTH_SHORT);
//            mToast.show();
//
//            mToast = Toast.makeText(mActivity, mAdapter.getItem(newPosition).getNome() + " new Position ", Toast.LENGTH_SHORT);
//            mToast.show();

            }
        }

        //TODO Simlicar Dialog
        public void listarPacientesAddConsulta(final Consulta consulta) {// final AdapterListaLeis adapterListaDePacientes

            SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
            String dataStr = dataFormatada.format(consulta.getDataDoAtendimentoEmMilissegundo());

            final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(dataStr)
                    .setView(R.layout.paciente_consulta_list)
                    .create();

            dialog.show();

            dialog.setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.icon_paciente));


            final Medico medico = consulta.getMedico();

            ListView mListView = (ListView) dialog.findViewById(R.id.paciente_listview_add);

            ((TextView) dialog.findViewById(R.id.paciente_consulta_list_medico_nome)).setText(medico.getNome());
            ((View) dialog.findViewById(R.id.paciente_consulta_list_medico_cor)).setBackgroundColor(medico.getCorIndicativa());

            TextView txt = (TextView) dialog.findViewById(R.id.paciente_emptyText);
            txt.setText(R.string.nenhum_paciente_cadastrado);
            mListView.setEmptyView(txt);

            //Obtem todos os pacientes que ainda não possuem um ordem definida, ou seja, ainda não chegaram
            RealmResults<Paciente> pacientes = consulta.getPacientes().where().lessThanOrEqualTo("ordem", 0).findAll();

            final AdapterAdicionarPacienteConsulta arrayAdapter =
                    new AdapterAdicionarPacienteConsulta(getActivity(), R.id.paciente_listview_add, realm, pacientes, consulta.getId(), adapterListaDePacientes);
            mListView.setAdapter(arrayAdapter);


            //Button cancelar
            Button btnOk = (Button) dialog.findViewById(R.id.paciente_consulta_list_btn_ok);
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
            Button btnAdicionarPaciente = (Button) dialog.findViewById(R.id.paciente_consulta_list_btn_adicionar);

            btnAdicionarPaciente.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Date data = new Date();
                    data.setTime(consulta.getDataDoAtendimentoEmMilissegundo());

                    adicionarEditarPaciente(v.getContext(), arrayAdapter, medico, data, -1);

                }
            });

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Date data = new Date();
                    data.setTime(consulta.getDataDoAtendimentoEmMilissegundo());

                    Paciente p = (Paciente) parent.getItemAtPosition(position);
                    adicionarEditarPaciente(view.getContext(), arrayAdapter, medico, data, p.getId());

                }
            });

        }

        //TODO Simlicar Dialog Repetido

        /**
         * @param idPaciente = -1 novo, >-1 existente
         */
        public void adicionarEditarPaciente(final Context context,  AdapterAdicionarPacienteConsulta adapter,
                                            final Medico medico, final Date data, final long idPaciente) {


            final AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Adicionar paciente")
                    .setView(R.layout.paciente_add_editar_layout)
                    .create();

            dialog.show();

            dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));


            ImageButton btnExcluir = (ImageButton) dialog.findViewById(R.id.paciente_excluir);
            final EditText editNome = (EditText) dialog.findViewById(R.id.paciente_add_nome);
            final EditText editTelefone = (EditText) dialog.findViewById(R.id.paciente_add_telefone);
            final EditText editTelefone2 = (EditText) dialog.findViewById(R.id.paciente_add_telefone2);

            final CheckBox chkPago = (CheckBox) dialog.findViewById(R.id.paciente_add_chk_pago);
            if (idPaciente == -1) {

                dialog.setTitle("Adicionar paciente");
                btnExcluir.setVisibility(View.GONE);

            } else {

                dialog.setTitle("Editar/Excluir paciente");
                btnExcluir.setVisibility(View.VISIBLE);

               // Realm realm = Realm.getDefaultInstance();
                Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();
                //realm.close();

                editNome.setText(paciente.getNome());
                String[] telefones = paciente.getTelefone().split("/");
                try {
                    editTelefone.setText(telefones[0]);
                    editTelefone2.setText(telefones[1]);
                } catch (Exception e) {
                }//tratar excessão
                chkPago.setChecked(paciente.isPago());

            }

            Button btnSalvar = (Button) dialog.findViewById(R.id.paciente_add_btn_salvar);
            Button btnCancelar = (Button) dialog.findViewById(R.id.paciente_add_btn_cancelar);


            btnSalvar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //Realm realm = Realm.getDefaultInstance();
                    if (idPaciente == -1) {


                        Consulta consulta = realm.where(Consulta.class)
                                .equalTo("dataDoAtendimentoEmMilissegundo", data.getTime())
                                .equalTo("medico.id", medico.getId())
                                .findFirst();

                        Paciente paciente = new Paciente(editNome.getText().toString(), editTelefone.getText() + "/" + editTelefone2.getText(), 0, consulta.getId(), chkPago.isChecked());

                        new PacienteHandler().newPaciente(paciente, realm);

                        realm.beginTransaction();
                        consulta.getPacientes().add(paciente);
                        realm.commitTransaction();


                    } else {

                        Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();
                        realm.beginTransaction();
                        paciente.setNome(editNome.getText().toString());
                        paciente.setTelefone(editTelefone.getText() + "/" + editTelefone2.getText());
                        paciente.setPago(chkPago.isChecked());
                        realm.commitTransaction();

                        new PacienteHandler().editPaciente(paciente, realm);

                    }

                    //adapter.notifyDataSetChanged();

                    //realm.close();//debpois do notify

                    dialog.dismiss();

                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // Realm realm = Realm.getDefaultInstance();

                    Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();

                    realm.beginTransaction();
                    paciente.deleteFromRealm();
                    realm.commitTransaction();

                    //realm.close();

                    //adapter.notifyDataSetChanged();

                    dialog.dismiss();
                }
            });

            btnCancelar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

        }

    }

}
