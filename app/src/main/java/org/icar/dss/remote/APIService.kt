package com.primehealthcare.telehealth.remote
import com.google.gson.GsonBuilder
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.icar.dss.BuildConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.IOException
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

interface APIService {

    companion object Factory {

        const val BASE_URL = "https://telehealthnetworkwebapi.azurewebsites.net/api/"

        fun getInstance(): APIService {

            val certPinner = CertificatePinner.Builder()
        .add("telehealthnetworkwebapi.azurewebsites.net",
              "sha256/FllMKi0vHGZutOKupiVKHRJ2odJ6Lk6r73XvRscyrY0=")
        .build();


            val okHttpClientBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .certificatePinner(certPinner)  // Enable for SSL Pinning
//                .sslSocketFactory(getSslSocketFactory())  // Enable for SSL Pinning

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                okHttpClientBuilder.addInterceptor(logging)
            }
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClientBuilder.build())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()



            return retrofit.create(APIService::class.java)

        }






    }


//    @POST("login/UserLogin")
////    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun userLogin(@Body loginDataModel: LoginDataModel) : Call<LoginResponse>

//    @POST("Login/Login")
////    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun userLogin(@Body loginRespData: LoginDataModel) : Call<LoginResponse>

//    @POST("Login/CreatePassword")
//    fun createPassword(@Body loginRespData: LoginDataModel) : Call<LoginResponse>
//
//    @FormUrlEncoded
//    @POST("Login/ForgotPassword")
//    fun forgotPassword(@Field("Emailid")username:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/ForgotMPIN")
//    fun forgotMpin(@Field("UniqueID")uniqueID:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/VerifyForgotPasswordOTP")
//    fun forgotPasswordOTPVerification(@Field("EmailID")username:String, @Field("OTP")otp:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/VerifyForgotMPINOTP")
//    fun forgotMpinOTPVerification(@Field("UniqueID")uniqueID:String, @Field("OTP")otp:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/LoginwithMPIN")
//    fun userLoginWithMpin(@Field("UniqueID")uniqueID: String, @Field("MPIN")mpin: String) : Call<LoginResponse>
//
//    @FormUrlEncoded
//    @POST("Login/CreateForgotPassword")
//    fun resetPassword(@Field("EmailID") emailID:String, @Field("Password") password:String , @Field("OTP") otp:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/LogOut")
//    fun logout(@Header("THNtoken") token:String,@Field("UniqueID") uniqueID:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/AfterLoginChangePassword")
//    fun updatePassword(@Header("THNtoken") token:String,@Field("UniqueID") uniqueID:String,@Field("OldPassword") oldPassword:String, @Field("NewPassword") newPassword:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/UpdateMPIN")
//    fun updateMpin(@Header("THNtoken") token:String,@Field("UniqueID") uniqueID:String,@Field("NewMpin") newMpin:String, @Field("OldMpin") oldMpin:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/CreateMPIN")
//    fun createMPIN(@Header("THNtoken") token:String,  @Field("UniqueID") uniqueID:String, @Field("NewMpin") mpin:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/CreateForgotMPIN")
//    fun resetMPIN(@Header("THNtoken") token:String,  @Field("UniqueID") uniqueID:String,@Field("OTP") otp:String, @Field("NewMpin") mpin:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Patient/FetchPatientAppointments")
////    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun getPatientAppointments(@Header("THNtoken") token:String,  @Field("UniqueID") uniqueID:String) : Call<AppointmentsRespModel>
//
//    @FormUrlEncoded
//    @POST("login/GetOTP")
////    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun getAccVerifyOTP(@Field("EmailID") emailID: String,@Field("SSN") ssn:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/VerifyOTP")
////    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun verifyOTPForAccVerification(@Field("SSN") SSN:String, @Field("EmailID") emailID: String,@Field("OTP") OTP:String) : Call<GenericResponseModel>
//
//    @FormUrlEncoded
//    @POST("Login/Alerts")
////    fun userLogin(@Field("username")username:String, @Field("userpass")userpass:String) : Call<LoginResponse>
//    fun getAlerts(@Header("THNtoken") token:String,  @Field("UniqueID") uniqueID:String) : Call<AlertsResp>
}