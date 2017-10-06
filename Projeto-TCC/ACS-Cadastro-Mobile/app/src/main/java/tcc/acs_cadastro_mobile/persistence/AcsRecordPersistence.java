package tcc.acs_cadastro_mobile.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmSchema;
import io.realm.Sort;
import io.realm.exceptions.RealmMigrationNeededException;
import tcc.acs_cadastro_mobile.models.AgentModel;

public class AcsRecordPersistence {

    public static final int OK = 0;
    public static final int INSERT = 1;
    public static final int UPDATE = 2;

    public static final int DEFAULT_INT = 0;
    public static final String DEFAULT_STR = "-";

    private static final String ACS_RECORD = "ACS_RECORD";
    private static final String DB_VERSION = "DB_VERSION";
    private static final String REMEMBER_LOGIN = "REMEMBER_LOGIN";

    private static long version = 0;

    public static void startDatabase(Context context) {
        try {
            init(context);
        } catch (RealmMigrationNeededException exception) {
            migrate(context);
        }
    }

    public static void startDatabase(Context context, AgentModel agent) {

        try {
            init(context);
            AgentPersistence.save(agent);

        } catch (RealmMigrationNeededException exception) {
            migrate(context);
        }
    }

    public static long getMinorValueIfBlank(Class<? extends RealmObject> cls, String field, long value) {

        if (value > AcsRecordPersistence.DEFAULT_INT) return value;
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<? extends RealmObject> query = realm.where(cls);

        RealmObject o = query.findAllSorted(field, Sort.ASCENDING).first(null);

        int data;
        if (o == null) {
            data = AcsRecordPersistence.DEFAULT_INT;
        } else {
            data = query.lessThanOrEqualTo(field, AcsRecordPersistence.DEFAULT_INT)
                    .findAll()
                    .size() * -1;
        }
        realm.close();
        return data;
    }

    private static void init(Context context) {
        version = getVersionDB(context);

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(version)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static void dropDatabase() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }

    private static void dropDatabase(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(DB_VERSION, Context.MODE_PRIVATE);
        Realm.deleteRealm(new RealmConfiguration.Builder().schemaVersion(0).build());
        preferences.edit().remove(DB_VERSION).putLong(DB_VERSION, 0).apply();
    }

    public static void migrate(Context context) {
        RealmConfiguration newConfig = new RealmConfiguration.Builder()
                .migration(migration())
                .schemaVersion(++version)
                .build();
        updateVersionDB(context, false, version);

        Realm.setDefaultConfiguration(newConfig);
    }

    private static RealmMigration migration() {

        return new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                Log.e("Before Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);

                switch ((int) oldVersion) {
                    case 0: migrationOne(realm.getSchema());
                }
                oldVersion++;
                Log.e("After Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);
            }
        };
    }

    private static void updateVersionDB(Context context, boolean delete, long version) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ACS_RECORD, Context.MODE_PRIVATE).edit();
        if (delete) {
            editor.remove(DB_VERSION);
        }
        editor.putLong(DB_VERSION, version).apply();
    }

    private static long getVersionDB(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ACS_RECORD, Context.MODE_PRIVATE);
        return preferences.getLong(DB_VERSION, 0);
    }

    public static boolean isRememberLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ACS_RECORD, Context.MODE_PRIVATE);
        return preferences.getBoolean(REMEMBER_LOGIN, false);
    }

    public static void setRememberLogin(Context context, boolean remember) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ACS_RECORD, Context.MODE_PRIVATE).edit();
        editor.putBoolean(REMEMBER_LOGIN, remember).apply();
    }

    private static void migrationOne(RealmSchema schema) {
        schema.get("RealmInt")
                .removeField("code")
                .addField("code", String.class);
    }

    public enum Status {
        OK(AcsRecordPersistence.OK), INSERT(AcsRecordPersistence.INSERT), UPDATE(AcsRecordPersistence.UPDATE);

        int status;

        Status(int status) {
            this.status = status;
        }
    }
}