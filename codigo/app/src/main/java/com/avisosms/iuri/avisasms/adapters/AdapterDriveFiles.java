package com.avisosms.iuri.avisasms.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.widget.DataBufferAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import io.realm.Realm;

/**
 * Created by iuri on 3/26/2016.
 */
public class AdapterDriveFiles extends DataBufferAdapter<Metadata> {

    Activity contexto;
    GoogleApiClient googleApiClient;
    Dialog dialogAnterior;
    ProgressDialog progressDialog;

    public AdapterDriveFiles(Activity context, GoogleApiClient googleApiClient, Dialog dialog) {
        super(context, R.layout.item_list_lei_lixeira);
        this.contexto = context;
        this.googleApiClient = googleApiClient;
        this.dialogAnterior = dialog;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Metadata metadata = getItem(position);

        ViewHolder holder = null;
        View row = convertView;

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.item_list_lei_lixeira, null);
        holder = new ViewHolder();


        holder.textDescricao = ((TextView) row.findViewById(R.id.textView_descricao));
        holder.textSubDescricao = ((TextView) row.findViewById(R.id.textView_subDescricao));
        holder.imgRemover = (ImageView) row.findViewById(R.id.imageView_remover);
        holder.imgRestaurar = (ImageView) row.findViewById(R.id.imageView_restaurar);

        holder.textDescricao.setText(metadata.getTitle());
        holder.textSubDescricao.setText(new SimpleDateFormat("dd-MM-yyyy 'às' HH:mm").format(metadata.getCreatedDate()));

        holder.imgRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(contexto)
                        .setMessage("Remover ponto de restauração:\n\n"
                                + "Descrição: " + getItem(position).getTitle() + "\n"
                                + "Data de criação: " + new SimpleDateFormat("dd-MM-yyyy 'às' HH:mm").format(getItem(position).getCreatedDate()) + "\n\n"
                                + "Esta operação não poderá ser desfeita.")
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Remover arquivo de Backup");
                alertDialog.setPositiveButton(R.string.deletar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Metadata metadata = getItem(position);
                        DriveResource driveResource = metadata.getDriveId().asDriveResource();

                        // If a DriveResource is a folder it will only be trashed if all of its children
                        // are also accessible to this app.
                        driveResource.delete(googleApiClient);

                        dialog.dismiss();
                        dialog.cancel();
                        dialogAnterior.dismiss();
                    }
                });
                alertDialog.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                }).setCancelable(false)
                        .create();

                alertDialog.show();
            }
        });

        holder.imgRestaurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(contexto)
                        .setMessage("Restaurar:\n\n"
                                + "Descrição: " + getItem(position).getTitle() + "\n"
                                + "Data de criação: " + new SimpleDateFormat("dd-MM-yyyy 'às' HH:mm").format(getItem(position).getCreatedDate()) + "\n\n"
                        )
                        .setIcon(android.R.drawable.ic_menu_rotate)
                        .setTitle("Restaurar esse Backup");
                alertDialog.setPositiveButton(R.string.restaurar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Metadata metadata = getItem(position);

                        progressDialog = new ProgressDialog(contexto);
                        progressDialog.setTitle("Carregando arquivo de Backup");
                        progressDialog.setMessage("Por favor aguarde...");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setCancelable(false);
                        progressDialog.setIndeterminate(true);
                        progressDialog.show();
                        progressDialog.setProgress(0);

                        DriveFile file = metadata.getDriveId().asDriveFile();

                        file.open(googleApiClient, DriveFile.MODE_READ_ONLY, listener).setResultCallback(driveContentsCallback);

/*try{
                        new DriveFileObterConteudoAsyncTaskDrive(contexto, googleApiClient).execute(metadata.getDriveId());
}catch (Exception e ){e.printStackTrace();}*/

                       /* Drive.DriveApi.getFile(googleApiClient, metadata.getDriveId().asDriveFile())
                                .openContents(googleApiClient(), DriveFile.MODE_READ_ONLY, listener)
                                .setResultCallback(contentsCallback);*/

                        dialog.dismiss();
                        dialog.cancel();
                        dialogAnterior.dismiss();
                        //

                    }
                });
                alertDialog.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                }).setCancelable(false)
                        .create();

                alertDialog.show();


            }
        });

        return row;
    }

    DriveFile.DownloadProgressListener listener = new DriveFile.DownloadProgressListener() {
        @Override
        public void onProgress(long bytesDownloaded, long bytesExpected) {
            // Update progress dialog with the latest progress.

            int progress = (int) (bytesDownloaded * 100 / bytesExpected);
            Log.d("Obtendo Backup", String.format("Loading progress: %d percent", progress));
            progressDialog.setProgress(progress);
        }
    };

    private ResultCallback<DriveApi.DriveContentsResult> driveContentsCallback =
            new ResultCallback<DriveApi.DriveContentsResult>() {
                @Override
                public void onResult(DriveApi.DriveContentsResult result) {
                    if (!result.getStatus().isSuccess())
                        Toast.makeText(contexto, "Erro ao efetuar backup.", Toast.LENGTH_SHORT).show();
                    else {

                        PrintWriter pw = null;
                        Realm realm = Realm.getDefaultInstance();
                        try {
                            pw = new PrintWriter(realm.getPath()); //erase Realm data content
                            pw.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(contexto, "Erro ao restaurar o backup. Tente outra vez", Toast.LENGTH_LONG).show();
                        }

                        DriveContents driveContents = result.getDriveContents();

                        copyInputStreamToFile(driveContents.getInputStream(), new File(realm.getPath()));

                        realm.close();

                        driveContents.discard(googleApiClient);

                        Toast.makeText(contexto, "Backup efetuado com sucesso. O "+ contexto.getString(R.string.app_name)+" precisa ser reaberto.", Toast.LENGTH_LONG).show();

                        contexto.finish();
                        /*Intent intent = new Intent(contexto.getApplicationContext(), Login.class);
                        contexto.startActivity(intent);*/

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                       /* Intent intent = new Intent(contexto.getApplicationContext(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        contexto.getApplicationContext().startActivity(intent);*/

                    }
                    progressDialog.dismiss();
                }
            };

    static class ViewHolder {
        TextView textDescricao;
        TextView textSubDescricao;
        ImageView imgRestaurar;
        ImageView imgRemover;
    }

    private void copyInputStreamToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) != -1) {
                out.write(buf, 0, read);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}