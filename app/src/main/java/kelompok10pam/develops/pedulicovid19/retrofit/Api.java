package kelompok10pam.develops.pedulicovid19.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("Register.php")
    Call<ResponseBody> createuser(
            @Field("email")String email, @Field("password") String password);
    @FormUrlEncoded
    @POST("login.php")
    Call<Users> login(@Field("email") String email,@Field("password") String password);
}
