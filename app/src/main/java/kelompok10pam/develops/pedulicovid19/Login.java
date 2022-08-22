package kelompok10pam.develops.pedulicovid19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences pref;
        SharedPreferences.Editor editor;
        final EditText username, password;
        final String USERNAME = "admin";
        final String PASSWORD = "admin";
        username = (EditText)  findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button btn = findViewById(R.id.login);
        final Button  btnReset = (Button)findViewById(R.id.reset);

        pref = getSharedPreferences("myPrefences", MODE_PRIVATE);
        editor = pref.edit();
        editor.putString(USERNAME, "admin");
        editor.putString(PASSWORD, "admin");
        editor.apply();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_ = username.getText().toString();
                String password_ = password.getText().toString();
                String savedUSERNAME = pref.getString(USERNAME, "-");
                String savedPASSWORD = pref.getString(PASSWORD, "-");
                if (username_.equals(" ") || password_.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "“Anda harus mengisi biodata terlebih dahulu", Toast.LENGTH_SHORT);

                } else if (username_.equals(savedUSERNAME) && password_.equals(savedPASSWORD)) {
                    Intent next = new Intent(getApplicationContext(), TipsPencegahan.class);
                    startActivity(next);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Data masih salah isi dulu", Toast.LENGTH_SHORT).show();
                }
            }


        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
                Toast.makeText(getApplicationContext(),"Data sudah direset", Toast.LENGTH_LONG).show();
            }
        });
    }
}
