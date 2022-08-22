package kelompok10pam.develops.pedulicovid19.Berita;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BeritaDao {
    @Insert
    Long insert(Berita u);

    @Query("SELECT * FROM `Berita` ORDER BY `id` DESC")
    List<Berita> getAllUsers();

    @Query("SELECT * FROM `Berita` WHERE `id` =:id")
    Berita getUser(int id);

    @Update
    void update(Berita u);

    @Delete
    void delete(Berita u);
}
