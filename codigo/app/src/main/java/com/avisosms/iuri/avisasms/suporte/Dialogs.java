package com.avisosms.iuri.avisasms.suporte;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.activity.Principal;
import com.avisosms.iuri.avisasms.adapters.AdapterDriveFiles;
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

import io.realm.Realm;

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

    /*Lista arquivos do Google Drive*/
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
