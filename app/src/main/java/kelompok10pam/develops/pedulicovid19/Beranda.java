package kelompok10pam.develops.pedulicovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import kelompok10pam.develops.pedulicovid19.Berita.BeritaActivity;
import kelompok10pam.develops.pedulicovid19.CekResiko.CekresikoActivity;
import kelompok10pam.develops.pedulicovid19.Chat.ChatActivity;
import kelompok10pam.develops.pedulicovid19.Donasi.DonasiActivity;
import kelompok10pam.develops.pedulicovid19.Gejala.GejalaActivity;
import kelompok10pam.develops.pedulicovid19.retrofit.LoginRetrofit;

public class Beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);


        CardView cekresiko = (CardView) findViewById(R.id.cekresiko);
        CardView gejalacovid = (CardView) findViewById(R.id.GejalaCovid);
        CardView zonamerah = (CardView) findViewById(R.id.ZonaMerah);
        CardView informasi = (CardView) findViewById(R.id.Informasi);
        CardView tips = (CardView) findViewById(R.id.TipsPencegahan);
        CardView statistik = (CardView) findViewById(R.id.Statistik);
        CardView berita = (CardView) findViewById(R.id.Berita);
        CardView chatbox = (CardView) findViewById(R.id.Chatbox);
        CardView donasi = (CardView) findViewById(R.id.DonasiPeduli);

        cekresiko.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
            Intent intent = new Intent(Beranda.this, CekresikoActivity.class);
            startActivity(intent);
       }
  });
       tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, TipsPencegahan.class);
                startActivity(intent);
            }
        });

       informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, InformasiCovid.class);
                startActivity(intent);
            }
        });
        statistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, Grafik.class);
                startActivity(intent);
            }
        });
        gejalacovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, GejalaActivity.class);
                startActivity(intent);
            }
        });
       berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, BeritaActivity.class);
                startActivity(intent);
            }
        });
        chatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, ChatActivity.class);
                startActivity(intent);
            }
        });
         zonamerah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, ZonaMerah.class);
                startActivity(intent);
            }
        });
        donasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, DonasiActivity.class);
                startActivity(intent);
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
        } else if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(this, LoginRetrofit.class));
        }
        return true;
    }
}

