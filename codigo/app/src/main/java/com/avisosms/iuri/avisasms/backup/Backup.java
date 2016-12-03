package com.avisosms.iuri.avisasms.backup;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.activity.Principal;
import com.avisosms.iuri.avisasms.suporte.Dialogs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;


/**
 * Created by iuri on 3/24/2016.
 */
public class Backup extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "Backup";

    private static final String FOLDER_MIME_TYPE = "application/vnd.google-apps.folder";

    private DriveId folderDriveId = null;

    /**
     * Request code for auto Google Play Services error resolution.
     */
    protected static final int REQUEST_CODE_RESOLUTION = 1;

    private GoogleApiClient mGoogleApiClient;

    CustomPropertyKey customPropertyKey = new CustomPropertyKey(
            "SMS", CustomPropertyKey.PUBLIC);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_backup, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_backup_alterarConta:

                mGoogleApiClient.clearDefaultAccountAndReconnect();
                return true;
            case R.id.menu_backup_removerConta:
                if (mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.clearDefaultAccountAndReconnect();
                    Intent refresh = new Intent(Backup.this, Principal.class);
                    startActivity(refresh);
                    finish();
                }else{
                    Toast.makeText(Backup.this, "Nenhuma conta selecionada", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return false;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.backup);

        // ((TextView) findViewById(R.id.txt_backup_conta)).setText(account);

        ((Button) findViewById(R.id.btn_backup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialogs.criarBackup(Backup.this, getGoogleApiClient(), folderDriveId);

            }
        });

        ((Button) findViewById(R.id.btn_restaurarBackup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialogs.listarBackups(Backup.this, getGoogleApiClient());

            }
        });

    }


    /**
     * Called when activity gets visible. A connection to Drive services need to
     * be initiated as soon as the activity is visible. Registers
     * {@code ConnectionCallbacks} and {@code OnConnectionFailedListener} on the
     * activities itself.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    //.addScope(Drive.SCOPE_APPFOLDER) // required for App Folder sample
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        mGoogleApiClient.connect();

    }

    /**
     * Handles resolution callbacks.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESOLUTION && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();

            // account = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME); Nao agora
            showMessage("Conectado a pasta do " + getString(R.string.app_name));

        } else {

            Intent refresh = new Intent(Backup.this, Principal.class);
            startActivity(refresh);
            finish();

            showMessage("Não conectado.");

        }

    }

    /**
     * Called when {@code mGoogleApiClient} is connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "GoogleApiClient connected");

        Query query = new Query.Builder()
                .addFilter(Filters.eq(SearchableField.MIME_TYPE, FOLDER_MIME_TYPE))
                .addFilter(Filters.eq(customPropertyKey, getString(R.string.app_name)))
                .build();

        Drive.DriveApi.query(getGoogleApiClient(), query)
                .setResultCallback(metadataCallback);
    }

    final private ResultCallback<DriveApi.MetadataBufferResult> metadataCallback = new
            ResultCallback<DriveApi.MetadataBufferResult>() {
                @Override
                public void onResult(DriveApi.MetadataBufferResult result) {
                    if (!result.getStatus().isSuccess()) {
                        showMessage("Um problema ocorreu ao tentar acessar o Google Drive.");
                        return;
                    }
                    folderDriveId = null;

                    if (result.getMetadataBuffer().getCount() > 0) {
                        folderDriveId = result.getMetadataBuffer().get(0).getDriveId();

                    } else { //Criar pasta caso na exista ainda

                        MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                                .setTitle("Backup - " + getText(R.string.app_name)).setCustomProperty(customPropertyKey, getString(R.string.app_name)).build();
                        Drive.DriveApi.getRootFolder(getGoogleApiClient()).createFolder(
                                getGoogleApiClient(), changeSet).setResultCallback(callback);

                    }
                }
            };

    final ResultCallback<DriveFolder.DriveFolderResult> callback = new ResultCallback<DriveFolder.DriveFolderResult>() {
        @Override
        public void onResult(DriveFolder.DriveFolderResult result) {
            if (!result.getStatus().isSuccess()) {
                // showMessage("Error while trying to create the folder");
                return;
            }

            folderDriveId = result.getDriveFolder().getDriveId();

            Log.i(TAG, "Created a folder: " + result.getDriveFolder().toString());
        }
    };

    /**
     * Called when {@code mGoogleApiClient} is disconnected.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "GoogleApiClient connection suspended");
    }

    /**
     * Called when {@code mGoogleApiClient} is trying to connect but failed.
     * Handle {@code result.getResolution()} if there is a resolution is
     * available.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());
        if (!result.hasResolution()) {
            // show the localized error dialog.
            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
            return;
        }
        try {
            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
        } catch (IntentSender.SendIntentException e) {
            Log.e(TAG, "Exception while starting resolution activity", e);
        }
    }

    /**
     * Shows a toast message.
     */
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Getter for the {@code GoogleApiClient}.
     */
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    /**
     * Called when activity gets invisible. Connection to Drive service needs to
     * be disconnected as soon as an activity is invisible.
     */
    @Override
    protected void onPause() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent evento) {
        super.onKeyDown(KeyCode, evento);
        if (KeyCode == KeyEvent.KEYCODE_BACK) {

            Intent refresh = new Intent(Backup.this, Principal.class);
            startActivity(refresh);
            finish();
        }
        return true;
    }
}