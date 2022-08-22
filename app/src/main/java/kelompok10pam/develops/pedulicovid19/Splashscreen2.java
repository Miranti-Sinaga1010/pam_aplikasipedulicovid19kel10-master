package kelompok10pam.develops.pedulicovid19;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kelompok10pam.develops.pedulicovid19.retrofit.LoginRetrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splashscreen2 extends Fragment {
    private Button btn1;
    private Button btn2;

    public Splashscreen2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tampil = inflater.inflate(R.layout.fragment_splashscreen2, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Aplikasi Peduli COVID 19");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//perintah button
        btn1 = (Button) tampil.findViewById(R.id.button1);
        btn2 = (Button) tampil.findViewById(R.id.button2);

        setHasOptionsMenu(true);
        return tampil;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Perintah button selanjutnya
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginRetrofit.class);
                startActivity(intent);

            }
        });
        //Perintah button sebelumnya
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tampil) {
                Splashscreen1 sp1 = new Splashscreen1();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_content, sp1)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });
    }

    @Override
    //perintah button sebelum pada menu diatas
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);


    }
}
