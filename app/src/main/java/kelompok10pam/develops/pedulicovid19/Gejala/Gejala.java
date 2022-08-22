package kelompok10pam.develops.pedulicovid19.Gejala;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Gejala {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "judul")
    private String judul;

    @ColumnInfo(name = "gejala1")
    private String gejala1;

    @ColumnInfo(name = "gejala2")
    private String gejala2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGejala1() {
        return gejala1;
    }

    public void setGejala1(String gejala1) {
        this.gejala1 = gejala1;
    }

    public String getGejala2() {
        return gejala2;
    }

    public void setGejala2(String gejala2) {
        this.gejala2 = gejala2;
    }
}
