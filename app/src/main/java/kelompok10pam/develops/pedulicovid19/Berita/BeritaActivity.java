package kelompok10pam.develops.pedulicovid19.Berita;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.List;

import kelompok10pam.develops.pedulicovid19.About;
import kelompok10pam.develops.pedulicovid19.Beranda;
import kelompok10pam.develops.pedulicovid19.R;
import kelompok10pam.develops.pedulicovid19.retrofit.LoginRetrofit;

public class BeritaActivity extends AppCompatActivity {
    private RecyclerView rvBerita;
    private FloatingActionButton fabAddBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        rvBerita = findViewById(R.id.rv_berita);
        fabAddBerita = findViewById(R.id.fab_add_berita);

        LinearLayoutManager llm = new LinearLayoutManager(BeritaActivity.this);
        rvBerita.setLayoutManager(llm);

        fabAddBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BeritaActivity.this, AddBeritaActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        new GetAllBerita(BeritaActivity.this).execute();
    }

    public static class GetAllBerita extends AsyncTask<Void, Void, List<Berita>> {
        private WeakReference<Context> c;

        public GetAllBerita(Context c) {
            this.c = new WeakReference<>(c);
        }

        @Override
        protected List<Berita> doInBackground(Void... voids) {
            BeritaDatabase ud = BeritaDatabase.getAppDatabase(c.get());
            return ud.BeritaDao().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<Berita> users) {
            super.onPostExecute(users);
            RecyclerView rv = ((Activity) c.get()).findViewById(R.id.rv_berita);

            BeritaAdapter a = new BeritaAdapter(c.get(), users);
            rv.setAdapter(a);
        }
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


