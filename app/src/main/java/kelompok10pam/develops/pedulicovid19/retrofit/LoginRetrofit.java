package kelompok10pam.develops.pedulicovid19.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kelompok10pam.develops.pedulicovid19.Beranda;
import kelompok10pam.develops.pedulicovid19.R;
import kelompok10pam.develops.pedulicovid19.TipsPencegahan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRetrofit extends AppCompatActivity {

    EditText mail,pass;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_retrofit);

        mail=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        button=findViewById(R.id.singup);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performlogin();

            }
        });

    }

    private  void performlogin()
    {
        String email=mail.getText().toString();
        String password=pass.getText().toString();
        Call<Users> call=ApiClient.getInstance().getapi().login(email,password);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(response.body().getResponse()) {
                    startActivity(new Intent(LoginRetrofit.this, Beranda.class));
                    Toast.makeText(LoginRetrofit.this, "success", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginRetrofit.this, "Maaf, anda belum terdaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoginRetrofit.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




    }




}
