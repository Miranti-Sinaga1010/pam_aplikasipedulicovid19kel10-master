package kelompok10pam.develops.pedulicovid19.Gejala;

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

public class AddGejalaActivity extends AppCompatActivity {
    private EditText etGejala, etDetail;
    private Button btnAdd;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gejala);

        etGejala = findViewById(R.id.et_gejala);
        etDetail = findViewById(R.id.et_detail);

        btnAdd = findViewById(R.id.btn_add);

        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            btnAdd.setText("Update");
            new GetGejala(AddGejalaActivity.this, id).execute();
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Gejala = etGejala.getText().toString().trim();
                String Detail = etDetail.getText().toString().trim();



                if (getIntent().hasExtra("id")) {
                    if (0 != Gejala.length()) {
                        Gejala u = new Gejala();
                        u.setId(id);
                        u.setJudul(Gejala);
                        u.setGejala1(Detail);



                        new UpdateGejala(AddGejalaActivity.this, u).execute();
                    } else {
                        Toast.makeText(AddGejalaActivity.this, "Silahkan masukkan gejala.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (0 != Gejala.length() ) {
                        Gejala u = new Gejala();
                        u.setJudul(Gejala);
                        u.setGejala1(Detail);
                        new AddGejala(AddGejalaActivity.this, u).execute();
                    } else {
                        Toast.makeText(AddGejalaActivity.this, "Silahkan masukkan gejala.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    static class AddGejala extends AsyncTask<Void, Void, Void> {
        private Gejala u;
        private WeakReference<Context> c;

        public AddGejala(Context c, Gejala u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            GejalaDatabase ud = GejalaDatabase.getAppDatabase(c.get());
            ud.gejalaDao().insert(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "Gejala added successfully!", Toast.LENGTH_SHORT).show();
            ((Activity) c.get()).finish();
        }
    }

    static class UpdateGejala extends AsyncTask<Void, Void, Void> {
        private Gejala u;
        private WeakReference<Context> c;

        public UpdateGejala(Context c, Gejala u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            GejalaDatabase ud = GejalaDatabase.getAppDatabase(c.get());
            ud.gejalaDao().update(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "Gejala updated successfully!", Toast.LENGTH_SHORT).show();
            ((Activity) c.get()).finish();
        }
    }

    static class GetGejala extends AsyncTask<Void, Void, Gejala> {
        private WeakReference<Context> c;
        private int id;

        public GetGejala(Context c, int id) {
            this.c = new WeakReference<>(c);
            this.id = id;
        }

        @Override
        protected Gejala doInBackground(Void... voids) {
            GejalaDatabase ud = GejalaDatabase.getAppDatabase(c.get());
            Gejala u = ud.gejalaDao().getUser(id);
            return u;
        }

        @Override
        protected void onPostExecute(Gejala u) {
            super.onPostExecute(u);

            EditText etGejala = ((Activity) c.get()).findViewById(R.id.et_gejala);
            EditText etDetail = ((Activity) c.get()).findViewById(R.id.et_detail);

            etGejala.setText(u.getJudul());
            etDetail.setText(u.getGejala1());


        }
    }
}
