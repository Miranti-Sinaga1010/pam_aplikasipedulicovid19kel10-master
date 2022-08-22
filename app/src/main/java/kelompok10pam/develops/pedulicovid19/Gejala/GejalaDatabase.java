package kelompok10pam.develops.pedulicovid19.Gejala;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Gejala.class}, version = 1, exportSchema = false)
public abstract class GejalaDatabase extends RoomDatabase {
    public abstract GejalaDao gejalaDao();

    private static GejalaDatabase INSTANCE;

    public static GejalaDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GejalaDatabase.class, "gejala-database").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
