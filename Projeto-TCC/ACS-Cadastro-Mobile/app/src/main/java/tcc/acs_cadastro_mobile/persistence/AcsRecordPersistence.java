package tcc.acs_cadastro_mobile.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import tcc.acs_cadastro_mobile.models.AgentModel;

public class AcsRecordPersistence {

    public static final int DEFAULT_INT = 0;
    public static final String DEFAULT_STR = "-";
    private static final String DB_VERSION = "DB_VERSION";

    public static AgentModel startDatabase(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(DB_VERSION, Context.MODE_PRIVATE);
        long version = preferences.getLong(DB_VERSION, 0);

        Realm.init(context);
        //Realm.deleteRealm(new RealmConfiguration.Builder().build());
        //preferences.edit().putLong(DB_VERSION, 0).apply();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(version)
                .build();
        Realm.setDefaultConfiguration(config);

        AgentModel agent;

        try {
            Realm realm = Realm.getInstance(config);
            agent = realm.where(AgentModel.class).findAll().first(new AgentModel());

        } catch (RealmMigrationNeededException exception) {

            RealmConfiguration newConfig = new RealmConfiguration.Builder()
                    .migration(migration(version))
                    .schemaVersion(++version)
                    .build();
            preferences.edit().putLong(DB_VERSION, version).apply();

            Realm.setDefaultConfiguration(newConfig);
            Realm realm = Realm.getDefaultInstance();
            agent = realm.where(AgentModel.class).findAll().first(new AgentModel());
        }


        Log.e("RealmMigration", "version: " + version);
        return agent;
    }

    private static RealmMigration migration(final long version) {

        return new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                Log.e("Before Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);
                switch ((int)oldVersion){
                    case 0: versionOne(realm.getSchema()); break;
                    case 1: versionTwo(realm.getSchema()); break;
                    case 2: versionThree(realm.getSchema()); break;
                }
                oldVersion++;
                Log.e("After Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);
            }
        };
    }

    private static void versionOne(RealmSchema schema) {
        schema.get("NasfConductModel")
                .addField("observation", boolean.class);
        schema.get("ExamsModel")
                .addField("pic", String.class);
    }

    private static void versionTwo(RealmSchema schema) {

        schema.create("AnotherReasons")
                .addField("internment", boolean.class)
                .addField("controlEnvironments", boolean.class)
                .addField("collectiveActivities", boolean.class)
                .addField("guidance", boolean.class)
                .addField("others", boolean.class);

        schema.create("Following")
                .addField("pregnant", boolean.class)
                .addField("puerpera", boolean.class)
                .addField("newborn", boolean.class)
                .addField("child", boolean.class)
                .addField("malnutrition", boolean.class)
                .addField("rehabilitationDeficiency", boolean.class)
                .addField("hypertension", boolean.class)
                .addField("diabetes", boolean.class)
                .addField("asthma", boolean.class)
                .addField("copdEmphysema", boolean.class)
                .addField("cancer", boolean.class)
                .addField("chronicDiseases", boolean.class)
                .addField("leprosy", boolean.class)
                .addField("tuberculosis", boolean.class)
                .addField("respiratory", boolean.class)
                .addField("smoker", boolean.class)
                .addField("homeBedding", boolean.class)
                .addField("vulnerability", boolean.class)
                .addField("bolsaFamília", boolean.class)
                .addField("mentalHealth", boolean.class)
                .addField("alcohol", boolean.class)
                .addField("drugs", boolean.class);

        schema.create("ActiveSearch")
                .addField("appointment", boolean.class)
                .addField("exam", boolean.class)
                .addField("vaccine", boolean.class)
                .addField("conditions", boolean.class);

        schema.create("ReasonsVisitModel")
                .addRealmObjectField("active", schema.get("ActiveSearch"))
                .addRealmObjectField("following", schema.get("Following"))
                .addRealmObjectField("another", schema.get("AnotherReasons"));

        schema.create("RecordVisitModel")
                .addField("isShared", boolean.class)
                .addRealmObjectField("details", schema.get("RecordDetails"));

        schema.create("VisitModel")
                .addField("record", long.class)
                .addField("numSus", long.class)
                .addRealmObjectField("details", schema.get("RecordVisitModel"))
                .addRealmObjectField("reasons", schema.get("ReasonsVisitModel"))
                .addField("result", String.class);
    }

    private static void versionThree(RealmSchema schema) {
        schema.get("VisitModel").addPrimaryKey("record");
    }
}