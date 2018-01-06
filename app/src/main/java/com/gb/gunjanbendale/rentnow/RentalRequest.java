package com.gb.gunjanbendale.rentnow;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class RentalRequest extends AppCompatActivity {
    MachineType machineType;
    CardView cardView;
    TextView product_info;
    ImageView imageView;
    EditText mobileno;
    DatePickerDialog datePickerDialog;
    String quantity,mobile,startdate,enddate,product,username;
    public static final String INTENT_PHONENUMBER = "phonenumber";
    public static final String INTENT_COUNTRY_CODE = "code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent mIntent = getIntent();
        String text = mIntent.getStringExtra("id");
        String img = mIntent.getStringExtra("thumbnail");
        machineType = new MachineType(text, "https://drive.google.com/drive/folders/1CbhQ7PHAh5DBl_iQ394uMmFbO8dpjGsP/"+"aerial.png");
        cardView = (CardView) findViewById(R.id.machine);
        product_info = (TextView) findViewById(R.id.machinetype);
        product_info.setText(text);
        imageView = (ImageView) findViewById(R.id.machineimg);
        Picasso.with(getApplicationContext()).load(img).into(imageView);
        datePickerDialog= new DatePickerDialog(this);
        final TextView Quantity=(TextView)findViewById(R.id.quantity);
        final EditText datestart=(EditText)findViewById(R.id.startdate);
        final EditText dateend=(EditText) findViewById(R.id.completion_date);
        mobileno=(EditText) findViewById(R.id.mobileno);
        final EditText Name_us=(EditText) findViewById(R.id.name);
        final Button plus = (Button) findViewById(R.id.add_button);
        Button minus = (Button) findViewById(R.id.minus_button);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(Quantity.getText().toString());
                int b = a + 1;
                Quantity.setText(Integer.toString(b));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(Quantity.getText().toString());
                if (a > 1) {
                    int b = a - 1;
                    Quantity.setText(Integer.toString(b));
                } else Quantity.setText(Quantity.getText());
            }
        });
        datestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(RentalRequest.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                datestart.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        dateend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(RentalRequest.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateend.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        Button submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fieldcheck(Quantity,mobileno,dateend,datestart,Name_us);
             /*quantity=Quantity.getText().toString().trim();
             mobile=mobileno.getText().toString().trim();
             startdate=datestart.getText().toString().trim();
             enddate=dateend.getText().toString().trim();
             product=product_info.getText().toString().trim();
             new SendRequest().execute();*/


            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                startActivity(new Intent(this,MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            moveTaskToBack(true);
            finish();
            startActivity(new Intent(RentalRequest.this,MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
    public void fieldcheck(TextView Quantity,TextView mobileno,TextView dateend,TextView datestart,TextView Name_us)
    {
        quantity=Quantity.getText().toString().trim();
        mobile=mobileno.getText().toString().trim();
        startdate=datestart.getText().toString().trim();
        enddate=dateend.getText().toString().trim();
        product=product_info.getText().toString().trim();
        username=Name_us.getText().toString().trim();

        if(startdate.isEmpty())
        {
            datestart.setError("Required Field");
            datestart.requestFocus();
            return;
        }
        if(enddate.isEmpty())
        {
            dateend.setError("Required Field");
            dateend.requestFocus();
            return;
        }
        if(mobile.isEmpty())
        {
            mobileno.setError("Mobile Number Required");
            mobileno.requestFocus();
            return;
        }
        if(mobile.length()!=10)
        {
            mobileno.setError("Enter valid Mobile number");
            mobileno.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            Name_us.setError("Name Required");
            Name_us.requestFocus();
            return;
        }
        new SendRequest().execute();
    }
    /* @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
             case android.R.id.home:
                 finish();
                 startActivity(new Intent(this,MainActivity.class));
                 return true;
         }
         return super.onOptionsItemSelected(item);
     }*/
    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
    public class SendRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){}
        protected String doInBackground(String... arg0) {
            Date currentTime = Calendar.getInstance().getTime();
            try{
                //Change your web app deployed URL or u can use this for attributes (name, country)
                URL url = new URL("https://script.google.com/macros/s/AKfycbwW1aZ8GFx9fqqdtcDSdV9yPzgUvNkoI3JtPLqblnotyRwphS--/exec");

                JSONObject postDataParams = new JSONObject();

                //int i;
                //for(i=1;i<=70;i++)


                //    String usn = Integer.toString(i);

                String id= "1BZGoRO53OVWDNbM0y1wKgdRRn0_WH5NU2Q2xAB89xb0";

                postDataParams.put("name",product);
                postDataParams.put("quantity",quantity);
                postDataParams.put("startdate",startdate);
                postDataParams.put("enddate",enddate);
                postDataParams.put("mobile",mobile);
                postDataParams.put("username",username);
                postDataParams.put("time",currentTime);
                postDataParams.put("id",id);

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + "Network Error"/*e.getMessage()*/);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent=new Intent(RentalRequest.this,VerificationActivity.class);
            intent.putExtra(INTENT_PHONENUMBER, mobileno.getText().toString().replaceAll("\\D", "").trim());
            intent.putExtra(INTENT_COUNTRY_CODE,"91");
            finish();
            startActivity(intent);
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    void gotoDialer(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:987654321"));
        startActivity(intent);
    }

}