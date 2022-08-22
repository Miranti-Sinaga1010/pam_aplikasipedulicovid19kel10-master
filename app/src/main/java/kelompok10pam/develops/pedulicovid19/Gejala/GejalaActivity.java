package kelompok10pam.develops.pedulicovid19.Gejala;

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

public class GejalaActivity extends AppCompatActivity {
    private RecyclerView rvGejala;
    private FloatingActionButton fabAddGejala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);

        rvGejala = findViewById(R.id.rv_judul);
        fabAddGejala = findViewById(R.id.fab_add_gejala);

        LinearLayoutManager llm = new LinearLayoutManager(GejalaActivity.this);
        rvGejala.setLayoutManager(llm);

        fabAddGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GejalaActivity.this, AddGejalaActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        new GetAllGejala(GejalaActivity.this).execute();
    }

    public static class GetAllGejala extends AsyncTask<Void, Void, List<Gejala>> {
        private WeakReference<Context> c;

        public GetAllGejala(Context c) {
            this.c = new WeakReference<>(c);
        }

        @Override
        protected List<Gejala> doInBackground(Void... voids) {
            GejalaDatabase ud = GejalaDatabase.getAppDatabase(c.get());
            return ud.gejalaDao().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<Gejala> users) {
            super.onPostExecute(users);
            RecyclerView rv = ((Activity) c.get()).findViewById(R.id.rv_judul);

            GejalaAdapter ua = new GejalaAdapter(c.get(), users);
            rv.setAdapter(ua);
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

