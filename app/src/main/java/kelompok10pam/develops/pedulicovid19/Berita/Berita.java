package kelompok10pam.develops.pedulicovid19.Berita;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Berita {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Judul")
    private String judul;

    @ColumnInfo(name = "Isi")
    private String Isi;

    @ColumnInfo(name = "Gambar")
    private String Gambar;

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

    public String getIsi() {
        return Isi;
    }

    public void setIsi(String isi) {
        Isi = isi;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }
}
