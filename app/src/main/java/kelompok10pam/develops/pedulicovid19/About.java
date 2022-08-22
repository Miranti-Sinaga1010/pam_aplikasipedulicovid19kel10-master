package kelompok10pam.develops.pedulicovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import kelompok10pam.develops.pedulicovid19.retrofit.LoginRetrofit;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
