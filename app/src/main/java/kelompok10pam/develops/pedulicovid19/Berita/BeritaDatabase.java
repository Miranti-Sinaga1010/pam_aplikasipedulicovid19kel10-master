package kelompok10pam.develops.pedulicovid19.Berita;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Berita.class}, version = 1, exportSchema = false)
public abstract class BeritaDatabase extends RoomDatabase {
    public abstract BeritaDao BeritaDao();

    private static BeritaDatabase INSTANCE;

    public static BeritaDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BeritaDatabase.class, "berita-database").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
