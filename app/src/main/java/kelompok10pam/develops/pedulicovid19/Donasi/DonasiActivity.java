package kelompok10pam.develops.pedulicovid19.Donasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.List;

import kelompok10pam.develops.pedulicovid19.About;
import kelompok10pam.develops.pedulicovid19.Beranda;
import kelompok10pam.develops.pedulicovid19.Gejala.AddGejalaActivity;
import kelompok10pam.develops.pedulicovid19.Gejala.Gejala;
import kelompok10pam.develops.pedulicovid19.Gejala.GejalaAdapter;
import kelompok10pam.develops.pedulicovid19.Gejala.GejalaDatabase;
import kelompok10pam.develops.pedulicovid19.R;
import kelompok10pam.develops.pedulicovid19.retrofit.LoginRetrofit;

public class DonasiActivity extends AppCompatActivity {

    private EditText editnamapel,editnamabar,
            editjumlahbar;
    private Button btnproses;
    private Button btnhapus;
    private Button btnexit;
    private TextView txtnamapel;
    private TextView txtnamabar;
    private TextView txtjumlahbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi);

        editnamapel = (EditText)findViewById(R.id.nama_pelanggan);
        editnamabar = (EditText)findViewById(R.id.nama_barang);
        editjumlahbar = (EditText)findViewById(R.id.jml_barang);

        btnproses = (Button)findViewById(R.id.proses);
        btnhapus = (Button)findViewById(R.id.reset);
        btnexit = (Button)findViewById(R.id.exit);

        txtnamapel = (TextView)findViewById(R.id.nama_pelanggan);
        txtnamabar = (TextView)findViewById(R.id.nama_barang);
        txtjumlahbar = (TextView)findViewById(R.id.jml_barang);

        btnproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namapelanggan = editnamapel.getText().toString().trim();
                String namabarang = editnamabar.getText().toString().trim();
                String jumlahbarang = editjumlahbar.getText().toString().trim();

                double jb = Double.parseDouble(jumlahbarang);
                double total = (jb);


                btnhapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtnamapel.setText(" ");
                        txtnamabar.setText(" ");
                        txtjumlahbar.setText(" ");


                        Toast.makeText(getApplicationContext(), "Data sudah dihapus", Toast.LENGTH_LONG).show();
                    }
                });
                btnexit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveTaskToBack(true);
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_covid19, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.beranda) {
            startActivity(new Intent(this, Beranda.class));
        } else if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, About.class));
        }
        else if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(this, LoginRetrofit.class));
        }
        return true;
    }
}

