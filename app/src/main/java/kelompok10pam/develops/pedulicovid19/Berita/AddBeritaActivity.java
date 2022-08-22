package kelompok10pam.develops.pedulicovid19.Berita;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import kelompok10pam.develops.pedulicovid19.R;

public class AddBeritaActivity extends AppCompatActivity {
    private EditText etBerita, etIsiBerita;
    private Button btnAdd;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_berita);

        etBerita = findViewById(R.id.et_berita);
        etIsiBerita = findViewById(R.id.et_isiberita);

        btnAdd = findViewById(R.id.btn_add);

        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            btnAdd.setText("Update");
            new GetBerita(AddBeritaActivity.this, id).execute();
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Berita = etBerita.getText().toString().trim();
                String IsiBerita = etIsiBerita.getText().toString().trim();



                if (getIntent().hasExtra("id")) {
                    if (0 != Berita.length()) {
                        Berita u = new Berita();
                        u.setId(id);
                        u.setJudul(Berita);
                        u.setIsi(IsiBerita);



                        new UpdateBerita(AddBeritaActivity.this, u).execute();
                    } else {
                        Toast.makeText(AddBeritaActivity.this, "Silahkan masukkan judul berita.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (0 != Berita.length() ) {
                        Berita u = new Berita();
                        u.setJudul(Berita);
                        u.setIsi(IsiBerita);
                        new AddBerita(AddBeritaActivity.this, u).execute();
                    } else {
                        Toast.makeText(AddBeritaActivity.this, "Silahkan masukkan judul berita.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    static class AddBerita extends AsyncTask<Void, Void, Void> {
        private Berita u;
        private WeakReference<Context> c;

        public AddBerita(Context c, Berita u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            BeritaDatabase ud = BeritaDatabase.getAppDatabase(c.get());
            ud.BeritaDao().insert(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "News added successfully!", Toast.LENGTH_SHORT).show();
            ((Activity) c.get()).finish();
        }
    }

    static class UpdateBerita extends AsyncTask<Void, Void, Void> {
        private Berita u;
        private WeakReference<Context> c;

        public UpdateBerita(Context c, Berita u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            BeritaDatabase ud = BeritaDatabase.getAppDatabase(c.get());
            ud.BeritaDao().update(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "News updated successfully!", Toast.LENGTH_SHORT).show();
            ((Activity) c.get()).finish();
        }
    }

    static class GetBerita extends AsyncTask<Void, Void, Berita> {
        private WeakReference<Context> c;
        private int id;

        public GetBerita(Context c, int id) {
            this.c = new WeakReference<>(c);
            this.id = id;
        }

        @Override
        protected Berita doInBackground(Void... voids) {
            BeritaDatabase ud = BeritaDatabase.getAppDatabase(c.get());
            Berita u = ud.BeritaDao().getUser(id);
            return u;
        }

        @Override
        protected void onPostExecute(Berita u) {
            super.onPostExecute(u);

            EditText etBerita = ((Activity) c.get()).findViewById(R.id.et_berita);
            EditText etIsiBerita = ((Activity) c.get()).findViewById(R.id.et_isiberita);

            etBerita.setText(u.getJudul());
            etIsiBerita.setText(u.getIsi());


        }
    }
}
