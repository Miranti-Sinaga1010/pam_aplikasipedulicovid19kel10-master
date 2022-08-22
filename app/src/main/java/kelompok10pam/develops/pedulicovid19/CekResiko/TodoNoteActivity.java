package kelompok10pam.develops.pedulicovid19.CekResiko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import kelompok10pam.develops.pedulicovid19.R;

public class TodoNoteActivity  extends AppCompatActivity {

    Spinner spinner;
    EditText inTitle, inDesc;
    Button btnDone, btnDelete;
    boolean isNewTodo = false;

    private String[] categories = {
            "Tinggi",
            "Sedang",
            "Rendah",

    };

    public ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(categories));
    MyDatabase myDatabase;

    Cekresiko updateTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = findViewById(R.id.spinner);
        inTitle = findViewById(R.id.inTitle);
        inDesc = findViewById(R.id.inDescription);
        btnDone = findViewById(R.id.btnDone);
        btnDelete = findViewById(R.id.btnDelete);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, MyDatabase.DB_NAME).build();

        int todo_id = getIntent().getIntExtra("id", -100);

        if (todo_id == -100)
            isNewTodo = true;

        if (!isNewTodo) {
            fetchTodoById(todo_id);
            btnDelete.setVisibility(View.VISIBLE);
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewTodo) {
                    Cekresiko  todo = new  Cekresiko();
                    todo.name = inTitle.getText().toString();
                    todo.description = inDesc.getText().toString();
                    todo.category = spinner.getSelectedItem().toString();

                    insertRow(todo);
                } else {

                    updateTodo.name = inTitle.getText().toString();
                    updateTodo.description = inDesc.getText().toString();
                    updateTodo.category = spinner.getSelectedItem().toString();

                    updateRow(updateTodo);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRow(updateTodo);
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchTodoById(final int todo_id) {
        new AsyncTask<Integer, Void, Cekresiko>() {
            @Override
            protected  Cekresiko  doInBackground(Integer... params) {

                return myDatabase.daoAccess().fetchTodoListById(params[0]);

            }

            @Override
            protected void onPostExecute( Cekresiko  todo) {
                super.onPostExecute(todo);
                inTitle.setText(todo.name);
                inDesc.setText(todo.description);
                spinner.setSelection(spinnerList.indexOf(todo.category));

                updateTodo = todo;
            }
        }.execute(todo_id);

    }

    @SuppressLint("StaticFieldLeak")
    private void insertRow( Cekresiko  todo) {
        new AsyncTask< Cekresiko, Void, Long>() {
            @Override
            protected Long doInBackground( Cekresiko... params) {
                return myDatabase.daoAccess().insertTodo(params[0]);
            }

            @Override
            protected void onPostExecute(Long id) {
                super.onPostExecute(id);

                Intent intent = getIntent();
                intent.putExtra("isNew", true).putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);

    }

    @SuppressLint("StaticFieldLeak")
    private void deleteRow( Cekresiko  todo) {
        new AsyncTask<Cekresiko, Void, Integer>() {
            @Override
            protected Integer doInBackground(Cekresiko... params) {
                return myDatabase.daoAccess().deleteTodo(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isDeleted", true).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);

    }


    @SuppressLint("StaticFieldLeak")
    private void updateRow(Cekresiko todo) {
        new AsyncTask<Cekresiko, Void, Integer>() {
            @Override
            protected Integer doInBackground(Cekresiko... params) {
                return myDatabase.daoAccess().updateTodo(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isNew", false).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);

    }

}
