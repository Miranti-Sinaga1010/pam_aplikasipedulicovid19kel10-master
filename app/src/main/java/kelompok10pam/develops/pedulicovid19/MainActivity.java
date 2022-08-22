package kelompok10pam.develops.pedulicovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();


        FragmentTransaction transaction = fm.beginTransaction();

        Splashscreen1 s1 = new Splashscreen1();
        transaction.add(R.id.frame_content, s1);
        transaction.commit();


    }

}

