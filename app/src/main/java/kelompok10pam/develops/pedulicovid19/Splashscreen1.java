package kelompok10pam.develops.pedulicovid19;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splashscreen1 extends Fragment {

    private Button btn1;


    public Splashscreen1() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splashscreen1, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Aplikasi Peduli COVID19");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        btn1 = (Button) view.findViewById(R.id.button1);
        return view;


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Perintah button selanjutnya
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tampil) {
                Splashscreen2 sp2 = new Splashscreen2();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_content, sp2)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });
    }
}