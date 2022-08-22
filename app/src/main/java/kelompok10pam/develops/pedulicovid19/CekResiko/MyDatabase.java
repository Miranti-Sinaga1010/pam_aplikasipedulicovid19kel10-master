package kelompok10pam.develops.pedulicovid19.CekResiko;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cekresiko.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public static final String DB_NAME = "app_db";
    public static final String TABLE_NAME_TODO = "todo";

    public abstract DaoAccess daoAccess();

}
