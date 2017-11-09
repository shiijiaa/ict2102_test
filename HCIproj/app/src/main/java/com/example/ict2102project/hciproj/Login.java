package com.example.ict2102project.hciproj;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Login extends AppCompatActivity {


    EditText matric, password;
    String Matric, Password;
    Context ctx=this;
    String NAME=null, PASSWORD=null, EMAIL=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        matric = (EditText) findViewById(R.id.login_matric);
        password = (EditText) findViewById(R.id.login_password);
    }

    public void main_register(View v){
        startActivity(new Intent(this,Register.class));
    }
    public void main_forgetPw(View v){
        startActivity(new Intent(this,ForgetPassword.class));
    }

    public void main_login(View v){
        Matric = matric.getText().toString();
        Password = password.getText().toString();

        BackGround b = new BackGround();
        b.execute(Matric, Password);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String matric = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://10.0.2.2/Test/login.php");
                String urlParams = "matric="+matric+"&password="+password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                Log.d("gf", is.toString());

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");
              //  EMAIL = user_data.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            Intent i = new Intent(ctx, HomeActivity.class);
            i.putExtra("name", NAME);
           // i.putExtra("password", PASSWORD);
           // i.putExtra("email", EMAIL);
            i.putExtra("err", err);
            startActivity(i);
            Log.d("MyTagGoesHere", "test");

        }
    }
}

