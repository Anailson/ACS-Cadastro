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
    private static final String DB_VERSION = "DB_VERSION";
    private static long version = 0;

    public static void startDatabase(Context context, AgentModel agent) {

        SharedPreferences preferences = context.getSharedPreferences(DB_VERSION, Context.MODE_PRIVATE);
        //delete(preferences);

        try {

            init(preferences, context);
            AgentPersistence.save(agent);

        } catch (RealmMigrationNeededException exception) {

            migrate(preferences);
        }
    }

    public static long getMinorValueIfBlank(Class<? extends RealmObject> cls, String field, long value) {

        if (value > AcsRecordPersistence.DEFAULT_INT) return value;

        RealmQuery<? extends RealmObject> query = Realm.getDefaultInstance().where(cls);

        RealmObject o = query.findAllSorted(field, Sort.ASCENDING)
                .first(null);

        if (o == null) return AcsRecordPersistence.DEFAULT_INT;
        else
            return query.lessThanOrEqualTo(field, AcsRecordPersistence.DEFAULT_INT)
                    .findAll()
                    .size() * -1;
    }

    private static void init(SharedPreferences preferences, Context context) {
        version = preferences.getLong(DB_VERSION, 0);

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(version)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private static void delete(SharedPreferences preferences) {

        Realm.deleteRealm(new RealmConfiguration.Builder().build());
        preferences.edit().remove(DB_VERSION).putLong(DB_VERSION, 0).apply();
    }

    private static void migrate(SharedPreferences preferences) {

        RealmConfiguration newConfig = new RealmConfiguration.Builder()
                .migration(migration())
                .schemaVersion(++version)
                .build();
        preferences.edit().putLong(DB_VERSION, version).apply();

        Realm.setDefaultConfiguration(newConfig);
    }

    private static RealmMigration migration() {

        return new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                Log.e("Before Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);

                switch ((int) oldVersion) {
                    case 0: versionOne(realm.getSchema()); break;
                }
                oldVersion++;
                Log.e("After Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);
            }
        };
    }

    private static void versionOne(RealmSchema schema){
        schema.get("CitizenModel")
                .addField("status", int.class);
    }

    public enum Status{
        OK(AcsRecordPersistence.OK), INSERT(AcsRecordPersistence.INSERT), UPDATE(AcsRecordPersistence.UPDATE);

        int status;
        Status(int status){
            this.status = status;
        }
    }
}