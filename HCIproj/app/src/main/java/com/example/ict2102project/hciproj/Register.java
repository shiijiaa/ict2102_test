package com.example.ict2102project.hciproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.os.AsyncTask;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.IOException;
import java.lang.String;
import android.widget.Toast;

public class Register extends Login {
    EditText name, matric, email, contact, campus, password, cfmPassword;
    String Name, Matric, Contact, Campus, Password, Email, CfmPassword;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.register_name);
        matric = (EditText) findViewById(R.id.register_matric);
        email = (EditText) findViewById(R.id.register_email);
        contact = (EditText) findViewById(R.id.register_contact);
        campus = (EditText) findViewById(R.id.register_campus);
        password = (EditText) findViewById(R.id.register_password);
        cfmPassword = (EditText) findViewById(R.id.register_cfmPassword);

    }

    public void register_register(View v){
        Name = name.getText().toString();
        Matric = matric.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        Contact = contact.getText().toString();
        Campus = campus.getText().toString();
        CfmPassword = cfmPassword.getText().toString();


        BackGround b = new BackGround();
        b.execute(Name, Matric, Email, Contact, Campus, Password, CfmPassword );
    }

    public void register_back(View v){
        startActivity(new Intent(this,Login.class));
    }

    class BackGround extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String matric = params[1];
            String email = params[2];
            String contact = params[3];
            String campus = params[4];
            String password = params[5];
            String cfmPassword = params[6];
            String data="";
            int tmp;

            try {
                //use the laptop ip address of same wifi with phone if testing on phone
                URL url = new URL("http://10.0.2.2/Test/register.php");
                String urlParams = "name="+name+"&matric="+matric+"&email="+email+"&contact="+contact+"&campus="+campus+"&password="+password+"&cfmPassword="+cfmPassword;

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
            if(s.equals("")){
                s="Registered Successfully!";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_login);
        }
    }
}
