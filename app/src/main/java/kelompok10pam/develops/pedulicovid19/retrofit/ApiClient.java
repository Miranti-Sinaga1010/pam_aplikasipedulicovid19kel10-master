package kelompok10pam.develops.pedulicovid19.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.42.2:8080/pedulicovid19(2)/Web Service/";
    private static Retrofit retrofit = null;
    private static ApiClient retrofitclient;

    public ApiClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

    }

    public static synchronized ApiClient getInstance() {
        if (retrofitclient == null) {
            retrofitclient = new ApiClient();
        }
        return retrofitclient;
    }

    public Api getapi() {
        return retrofit.create(Api.class);
    }
}
