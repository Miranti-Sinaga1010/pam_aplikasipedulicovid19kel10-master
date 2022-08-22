package kelompok10pam.develops.pedulicovid19.Gejala;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GejalaDao {

    @Insert
    Long insert(Gejala u);

    @Query("SELECT * FROM `Gejala` ORDER BY `id` DESC")
    List<Gejala> getAllUsers();

    @Query("SELECT * FROM `Gejala` WHERE `id` =:id")
    Gejala getUser(int id);

    @Update
    void update(Gejala u);

    @Delete
    void delete(Gejala u);
}
