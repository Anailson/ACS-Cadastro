package com.avisosms.iuri.avisasms.suporte;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.activity.Principal;
import com.avisosms.iuri.avisasms.adapters.AdapterAdicionarPacienteAgendamento;
import com.avisosms.iuri.avisasms.adapters.AdapterAdicionarPacienteConsulta;
import com.avisosms.iuri.avisasms.adapters.AdapterDriveFiles;
import com.avisosms.iuri.avisasms.adapters.AdapterSelecionarMedico;
import com.avisosms.iuri.avisasms.compactcalendarview.CompactCalendarView;
import com.avisosms.iuri.avisasms.dataHandler.PacienteHandler;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.objetos.Medico;
import com.avisosms.iuri.avisasms.objetos.Paciente;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.query.SortOrder;
import com.google.android.gms.drive.query.SortableField;
import com.google.android.gms.drive.widget.DataBufferAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 7/15/2016.
 */
public class Dialogs {

    public static void confirmarSaida(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        //builder.setTitle("Your Title");

        builder.setMessage("Deseja sair sem salvar?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        activity.finish();

                        Intent i = new Intent(activity, Principal.class);
                        i.putExtra("idNavTab", R.id.nav_atendente);
                        activity.startActivity(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void medicoListSelecionar(final Context context, final CompactCalendarView calendar, Date data) {// final AdapterListaLeis adapter

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

        AdapterSelecionarMedico arrayAdapter = new AdapterSelecionarMedico(context, R.id.medico_listview_check, medicos, data.getTime());
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

    public static void listarPacientes(final Context context, final Medico medico, final Date data) {// final AdapterListaLeis adapter

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(data);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.paciente_agendamento_list)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));

        Realm realm = Realm.getDefaultInstance();
        Consulta consulta = realm.where(Consulta.class)
                .equalTo("dataDoAtendimentoEmMilissegundo", data.getTime())
                .equalTo("medico.id", medico.getId())
                .findFirst();

        Log.e("Log", "dataDoAtendimentoEmMilissegundo" + data.getTime() + " medico.id  " + medico.getId());

        ListView mListView = (ListView) dialog.findViewById(R.id.paciente_listview_add);


        ((TextView) dialog.findViewById(R.id.paciente_list_medico_nome)).setText(medico.getNome());
        ((View) dialog.findViewById(R.id.paciente_list_medico_cor)).setBackgroundColor(medico.getCorIndicativa());

        TextView txt = (TextView) dialog.findViewById(R.id.paciente_emptyText);
        txt.setText(R.string.nenhum_paciente_cadastrado);
        mListView.setEmptyView(txt);

        final AdapterAdicionarPacienteAgendamento arrayAdapter = new AdapterAdicionarPacienteAgendamento(context, R.id.paciente_listview_add, consulta.getPacientes());
        mListView.setAdapter(arrayAdapter);

        realm.close();

        //Button cancelar
        Button btnOk = (Button) dialog.findViewById(R.id.paciente_list_btn_ok);
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
        Button btnAdicionarPaciente = (Button) dialog.findViewById(R.id.paciente_list_btn_adicionar);

        btnAdicionarPaciente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                adicionarEditarPaciente(v.getContext(), arrayAdapter, medico, data, -1);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Paciente p = (Paciente) parent.getItemAtPosition(position);
                adicionarEditarPaciente(view.getContext(), arrayAdapter, medico, data, p.getId());

            }
        });

    }

    /**
     * @param idPaciente = -1 novo, >-1 existente
     */
    public static void adicionarEditarPaciente(final Context context, final ArrayAdapter adapter,
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

            Realm realm = Realm.getDefaultInstance();
            Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();
            realm.close();

            editNome.setText(paciente.getNome());
            String[] telefones = paciente.getTelefone().split("/");
            editTelefone.setText(telefones[0]);
            editTelefone2.setText(telefones[1]);
            chkPago.setChecked(paciente.isPago());

        }

        Button btnSalvar = (Button) dialog.findViewById(R.id.paciente_add_btn_salvar);
        Button btnCancelar = (Button) dialog.findViewById(R.id.paciente_add_btn_cancelar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();
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

                realm.close();

                adapter.notifyDataSetChanged();

                dialog.dismiss();

            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();

                Paciente paciente = realm.where(Paciente.class).equalTo("id", idPaciente).findFirst();

                realm.beginTransaction();
                paciente.deleteFromRealm();
                realm.commitTransaction();

                realm.close();

                adapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public static void listarPacientesAddConsulta(final Context context, final Consulta consulta) {// final AdapterListaLeis adapter

        SimpleDateFormat dataFormatada = new SimpleDateFormat("EEE',' dd 'de' MMM 'de' yyyy");
        String dataStr = dataFormatada.format(consulta.getDataDoAtendimentoEmMilissegundo());

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(dataStr)
                .setView(R.layout.paciente_consulta_list)
                .create();

        dialog.show();

        dialog.setIcon(ContextCompat.getDrawable(context, R.drawable.icon_paciente));

        final Medico medico = consulta.getMedico();

        ListView mListView = (ListView) dialog.findViewById(R.id.paciente_listview_add);

        ((TextView) dialog.findViewById(R.id.paciente_consulta_list_medico_nome)).setText(medico.getNome());
        ((View) dialog.findViewById(R.id.paciente_consulta_list_medico_cor)).setBackgroundColor(medico.getCorIndicativa());

        TextView txt = (TextView) dialog.findViewById(R.id.paciente_emptyText);
        txt.setText(R.string.nenhum_paciente_cadastrado);
        mListView.setEmptyView(txt);

        //Obtem todos os pacientes que ainda não possuem um ordem definida, ou seja, ainda não chegaram
        List<Paciente> pacientes = consulta.getPacientes().where().lessThanOrEqualTo("ordem", 0).findAll();

        final AdapterAdicionarPacienteConsulta arrayAdapter =
                new AdapterAdicionarPacienteConsulta(context, R.id.paciente_listview_add, pacientes, consulta.getId());
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

    public static void listarBackups(final Activity context, final GoogleApiClient googleApiClient) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Backups (Pontos de restauração)");
        progressDialog.setMessage("Carregando Backups...");
        progressDialog.show();

        final ListView mListView;
        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.backup_list);//carregando o layout do dialog do xml

        mListView = (ListView) dialog.findViewById(R.id.backup_listView);

        TextView txt = (TextView) dialog.findViewById(R.id.backup_emptyText);
        txt.setText(R.string.backup_nao_encontrado);
        mListView.setEmptyView(txt);

        SortOrder sortOrder = new SortOrder.Builder()
                .addSortDescending(SortableField.MODIFIED_DATE).build();

        final DataBufferAdapter<Metadata> arrayAdapter = new AdapterDriveFiles(context, googleApiClient, dialog);
        Query query = new Query.Builder()
                .addFilter(Filters.and(Filters.eq(SearchableField.MIME_TYPE, "text/plain")))
                .setSortOrder(sortOrder).build();

        Drive.DriveApi.query(googleApiClient, query)
                .setResultCallback(new ResultCallback<DriveApi.MetadataBufferResult>() {
                    @Override
                    public void onResult(DriveApi.MetadataBufferResult result) {
                        if (!result.getStatus().isSuccess()) {
                            Toast.makeText(context, "Ocorreu um problema ao tentar listar os arquivos.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        arrayAdapter.append(result.getMetadataBuffer());

                        if (arrayAdapter.getCount() == 0) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Nenhuma arquivo de backup encontrado.", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            return;
                        }

                        if (progressDialog != null) {
                            progressDialog.dismiss();
                            dialog.show();
                        } else {
                            dialog.show();
                        }
                    }
                });

        mListView.setAdapter(arrayAdapter);
    }

    public static void criarBackup(final Context context, final GoogleApiClient googleApiClient, final DriveId mFolderDriveId) {

        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.backup_criar);//carregando o layout do dialog do xml
        dialog.setTitle("Backup (Ponto de restauração)");

        final EditText editDescricao = (EditText) dialog
                .findViewById(R.id.backup_criar_descricao);

        ((Button) dialog.findViewById(R.id.backup_btn_criar))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ResultCallback<DriveApi.DriveContentsResult> driveContentsCallback = new
                                ResultCallback<DriveApi.DriveContentsResult>() {
                                    @Override
                                    public void onResult(DriveApi.DriveContentsResult result) {
                                        if (!result.getStatus().isSuccess()) {
                                            Toast.makeText(context, "Erro ao criar o arquivo.", Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                        final DriveContents driveContents = result.getDriveContents();

                                        final ProgressDialog progressDialog = new ProgressDialog(context);
                                        progressDialog.setTitle("Backup (Ponto de restauração)");
                                        progressDialog.setMessage("Criando arquivo de backup...");
                                        progressDialog.show();

                                        // Perform I/O off the UI thread.
                                        new Thread() {
                                            @Override
                                            public void run() {
                                                DriveFolder folder = mFolderDriveId.asDriveFolder();

                                                InputStream is = null;

                                                Realm realm = Realm.getDefaultInstance();
                                                try {
                                                    is = new FileInputStream(new File(realm.getPath()));
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                realm.close();

                                                OutputStream oos = driveContents.getOutputStream();
                                                if (oos != null) try {

                                                    byte[] buf = new byte[8192];
                                                    int c = 0;
                                                    while ((c = is.read(buf, 0, buf.length)) > 0) {
                                                        oos.write(buf, 0, c);
                                                        oos.flush();
                                                    }
                                                } catch (Exception e) {
                                                } finally {
                                                    try {
                                                        oos.close();
                                                    } catch (Exception ignore) {
                                                    }
                                                }

                                       /* Writer writer = new OutputStreamWriter(oos);
                                        try {
                                            writer.write("Hello World!");
                                            writer.close();
                                        } catch (IOException e) {
                                            Log.e("Criando arquivo", e.getMessage());
                                        }*/

                                                MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                                                        .setTitle(editDescricao.getText().toString())
                                                        .setMimeType("text/plain")
                                                        .setStarred(true).build();

                                                folder.createFile(googleApiClient, changeSet, driveContents)
                                                        .setResultCallback(new
                                                                                   ResultCallback<DriveFolder.DriveFileResult>() {
                                                                                       @Override
                                                                                       public void onResult(DriveFolder.DriveFileResult result) {
                                                                                           if (!result.getStatus().isSuccess()) {
                                                                                               Toast.makeText(context, "Erro ao criar o arquivo.", Toast.LENGTH_LONG).show();
                                                                                               return;
                                                                                           } else
                                                                                               Toast.makeText(context, "Backup criado.", Toast.LENGTH_LONG).show();
                                                                                           progressDialog.dismiss();
                                                                                       }
                                                                                   });
                                            }
                                        }.start();
                                    }
                                };


                        Drive.DriveApi.newDriveContents(googleApiClient)
                                .setResultCallback(driveContentsCallback);

                        dialog.dismiss();
                    }
                });
        ((Button) dialog.findViewById(R.id.backup_btn_cancelar))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

        dialog.show();

    }

}
