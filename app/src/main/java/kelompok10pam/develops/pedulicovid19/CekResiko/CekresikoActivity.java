package kelompok10pam.develops.pedulicovid19.CekResiko;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kelompok10pam.develops.pedulicovid19.About;
import kelompok10pam.develops.pedulicovid19.Beranda;
import kelompok10pam.develops.pedulicovid19.R;
import kelompok10pam.develops.pedulicovid19.retrofit.LoginRetrofit;

public class CekresikoActivity  extends AppCompatActivity implements RecyclerViewAdapter.ClickListener, AdapterView.OnItemSelectedListener {

    MyDatabase myDatabase;
    RecyclerView recyclerView;
    Spinner spinner;
    RecyclerViewAdapter recyclerViewAdapter;
    FloatingActionButton floatingActionButton;
    private String[] categories = {
            "All",
            "Tinggi",
            "Sedang",
            "Rendah",

    };

    ArrayList<Cekresiko> todoArrayList = new ArrayList<>();
    ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(categories));

    public static final int NEW_TODO_REQUEST_CODE = 200;
    public static final int UPDATE_TODO_REQUEST_CODE = 300;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekresiko);

        initViews();
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, MyDatabase.DB_NAME).fallbackToDestructiveMigration().build();
        checkIfAppLaunchedFirstTime();
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CekresikoActivity.this, TodoNoteActivity.class), NEW_TODO_REQUEST_CODE);
            }
        });

    }

    private void initViews() {
        floatingActionButton = findViewById(R.id.fab);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void launchIntent(int id) {
        startActivityForResult(new Intent(CekresikoActivity.this, TodoNoteActivity.class).putExtra("id", id), UPDATE_TODO_REQUEST_CODE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (position == 0) {
            loadAllTodos();
        } else {
            String string = parent.getItemAtPosition(position).toString();
            loadFilteredTodos(string);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @SuppressLint("StaticFieldLeak")
    private void loadFilteredTodos(String category) {
        new AsyncTask<String, Void, List<Cekresiko>>() {
            @Override
            protected List<Cekresiko> doInBackground(String... params) {
                return myDatabase.daoAccess().fetchTodoListByCategory(params[0]);

            }

            @Override
            protected void onPostExecute(List<Cekresiko> todoList) {
                recyclerViewAdapter.updateTodoList(todoList);
            }
        }.execute(category);

    }


    @SuppressLint("StaticFieldLeak")
    private void fetchTodoByIdAndInsert(int id) {
        new AsyncTask<Integer, Void, Cekresiko>() {
            @Override
            protected Cekresiko doInBackground(Integer... params) {
                return myDatabase.daoAccess().fetchTodoListById(params[0]);

            }

            @Override
            protected void onPostExecute(Cekresiko todoList) {
                recyclerViewAdapter.addRow(todoList);
            }
        }.execute(id);

    }

    @SuppressLint("StaticFieldLeak")
    private void loadAllTodos() {
        new AsyncTask<String, Void, List<Cekresiko>>() {
            @Override
            protected List<Cekresiko> doInBackground(String... params) {
                return myDatabase.daoAccess().fetchAllTodos();
            }

            @Override
            protected void onPostExecute(List<Cekresiko> todoList) {
                recyclerViewAdapter.updateTodoList(todoList);
            }
        }.execute();
    }

    private void buildDummyTodos() {
        Cekresiko todo = new Cekresiko();
        todo.name = "POTENSI TERTULAR DILUAR RUMAH";
        todo.description ="saya Menggunakan transportasi umum";
        todo.category = "Tinggi";

        todoArrayList.add(todo);

        todo = new Cekresiko();
        todo.name ="POTENSI TERTULAR DI DALAM RUMAH";
        todo.description ="saya tidak mencuci tangan";
        todo.category = "Sedang";

        todoArrayList.add(todo);

        todo = new Cekresiko();
        todo.name ="DAYA TAHAN TUBUH (IMUNITAS)";
        todo.description = "saya jarang minum vitamin C dan E";
        todo.category = "Rendah";

        todoArrayList.add(todo);


        insertList(todoArrayList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //reset spinners
            spinner.setSelection(0);

            if (requestCode == NEW_TODO_REQUEST_CODE) {
                long id = data.getLongExtra("id", -1);
                Toast.makeText(getApplicationContext(), "Row inserted", Toast.LENGTH_SHORT).show();
                fetchTodoByIdAndInsert((int) id);

            } else if (requestCode == UPDATE_TODO_REQUEST_CODE) {

                boolean isDeleted = data.getBooleanExtra("isDeleted", false);
                int number = data.getIntExtra("number", -1);
                if (isDeleted) {
                    Toast.makeText(getApplicationContext(), number + " rows deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), number + " rows updated", Toast.LENGTH_SHORT).show();
                }

                loadAllTodos();

            }


        } else {
            Toast.makeText(getApplicationContext(), "No action done by user", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void insertList(List<Cekresiko> todoList) {
        new AsyncTask<List<Cekresiko>, Void, Void>() {
            @Override
            protected Void doInBackground(List<Cekresiko>... params) {
                myDatabase.daoAccess().insertTodoList(params[0]);

                return null;

            }

            @Override
            protected void onPostExecute(Void voids) {
                super.onPostExecute(voids);
            }
        }.execute(todoList);

    }

    private void checkIfAppLaunchedFirstTime() {
        final String PREFS_NAME = "SharedPrefs";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("firstTime", true)) {
            settings.edit().putBoolean("firstTime", false).apply();
            buildDummyTodos();
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




