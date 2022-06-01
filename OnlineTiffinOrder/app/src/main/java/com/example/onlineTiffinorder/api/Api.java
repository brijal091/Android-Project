package com.example.onlineTiffinorder.api;



import com.example.onlineTiffinorder.api.responce.User;
import com.example.onlineTiffinorder.api.responce.breakresponce;
import com.example.onlineTiffinorder.api.responce.dinnerresponce;
import com.example.onlineTiffinorder.api.responce.loginresponce;
import com.example.onlineTiffinorder.api.responce.lunchresponce;
import com.example.onlineTiffinorder.api.responce.userresponce;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    @FormUrlEncoded
    @POST("RegistrationControllerJson.php")
    Call<CommanResponse> createUser(

            @Field("adduser") String adduser,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("mobno") String mobno,
            @Field("email") String email,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("loginapi.php")
    Call<loginresponce> login(

            @Field("login") String login,
            @Field("mobno") String mobno,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("loginapi.php")
    Call<loginresponce> adminlogin(

            @Field("adminlogin") String adminlogin,
            @Field("mobno") String mobno,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("order.php")
    Call<CommanResponse> addbreakfast(

            @Field("addbreakfast") String addbreakfast,
            @Field("custid") String custid,
            @Field("thepla") String thepla,
            @Field("batakapuva") String batakapuva,
            @Field("achar") String achar,
            @Field("tea") String tea,
            @Field("name") String name,
            @Field("mobno") String mobno,
            @Field("qty") String qty,
            @Field("corona") String corona,
            @Field("stdate") String stdate,
            @Field("enddate") String enddate,
            @Field("address") String address,
            @Field("pincode") String pincode,
            @Field("spreq") String spreq

    );



    @FormUrlEncoded
    @POST("order.php")
    Call<CommanResponse> addlunch(

            @Field("addlunch") String addlunch,
            @Field("custid") String custid,
            @Field("rotii") String rotii,
            @Field("sabji") String sabji,
            @Field("rise") String rise,
            @Field("dal") String dal,
            @Field("salad") String salad,
            @Field("papad") String papad,
            @Field("name") String name,
            @Field("mobno") String mobno,
            @Field("qty") String qty,
            @Field("corona") String corona,
            @Field("stdate") String stdate,
            @Field("enddate") String enddate,
            @Field("address") String address,
            @Field("pincode") String pincode,
            @Field("spreq") String spreq

    );



    @FormUrlEncoded
    @POST("order.php")
    Call<CommanResponse> adddinner(

            @Field("adddinner") String adddinner,
            @Field("custid") String custid,
            @Field("rotii") String rotii,
            @Field("sabji") String sabji,
            @Field("mongdal") String mongdal,
            @Field("buttermilk") String buttermilk,
            @Field("salad") String salad,
            @Field("papad") String papad,
            @Field("name") String name,
            @Field("mobno") String mobno,
            @Field("qty") String qty,
            @Field("corona") String corona,
            @Field("stdate") String stdate,
            @Field("enddate") String enddate,
            @Field("address") String address,
            @Field("pincode") String pincode,
            @Field("spreq") String spreq

    );



    @FormUrlEncoded
    @POST("getorder.php")
    Call<dinnerresponce> getdinner(
            @Field("getdinner") String getdinner,
            @Field("condition") String condition
    );


    @FormUrlEncoded
    @POST("getorder.php")
    Call<lunchresponce> getlunch(
            @Field("getlunch") String getlunch,
            @Field("condition") String condition
    );


    @FormUrlEncoded
    @POST("getorder.php")
    Call<breakresponce> getbreakfast(
            @Field("getbreakfast") String getbreakfast,
            @Field("condition") String condition
    );

    @FormUrlEncoded
    @POST("getorder.php")
    Call<userresponce> getuser(
            @Field("getuser") String getuser
    );

}
