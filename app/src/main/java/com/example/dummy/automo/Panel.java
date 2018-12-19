package com.example.dummy.automo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.webkit.WebViewClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Panel extends AppCompatActivity
{


    Button get,send;
    Spinner dep;
    QEditText book;
    QTextView rfid;
    WebView loadUrl;
    String sendString,printString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        get = findViewById(R.id.getBook);
        book = findViewById(R.id.book);
        loadUrl=findViewById(R.id.response);
        send = findViewById(R.id.sendCode);
        rfid = findViewById(R.id.code);
        // dep = findViewById(R.id.department);
        // loadUrl.setVisibility(INVISIBLE);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Department");
        categories.add("Computer");
        categories.add("Civil");
        categories.add("M.B.A");

        categories.add("Electronics");
        categories.add("M.E Computer");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dep.setAdapter(dataAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Uri uri=Uri.parse("http://192.168.4.1/barcodeArgs?Barcode=A252220");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
                //HttpPost post=new HttpPost(String.format("http://192.168.4.1/barcodeArgs?Barcode=A422252"));
                HttpClient httpClient=new DefaultHttpClient();
                HttpPost httpPost=new HttpPost("http://192.168.4.1/barcodeArgs?Barcode=A422252");
                try {
                    HttpResponse httpResponse=httpClient.execute(httpPost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/
                loadUrl.setWebViewClient(new WebViewClient());
                //editString(rfid.getText().toString());
                loadUrl.loadUrl("http://192.168.43.112/"+sendString);
                //loadUrl.loadUrl("http://google.co.in");

            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book.getText().toString().equals(""))
                    book.setError("Can't be empty!");
              /*  else if (dep.getSelectedItemId() == 0)
                    Toast.makeText(Panel.this, "Select Department", Toast.LENGTH_SHORT).show();
                */else {
                    FetchData fetch = new FetchData(Panel.this);
                    fetch.execute(book.getText().toString());

                }

            }

        });

    }

    private void editString(String s)
    {
        s=""+s.charAt(0)+s.charAt(1)+" "+s.charAt(2)+s.charAt(3)+" "+s.charAt(4)+s.charAt(5)+" "+s.charAt(6)+s.charAt(7);

    }

    public class FetchData extends AsyncTask<String, Void, String> {

        public FetchData(Context context) {
            Context ctx = context;
        }

        String result = "";

        @Override
        protected String doInBackground(String... params) {
            System.out.println("do in background");

            // String fetch_url = "http://192.168.43.46/getCode.php";
            String fetch_url = "http://yuvavarta.com/getCode.php";
            try {
                String bookName = params[0];
                URL url = new URL(fetch_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "Utf-8"));
                String post_data = URLEncoder.encode("bookName", "Utf-8") + "=" + URLEncoder.encode(bookName, "Utf-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);

            /*String printString="";

            printString += result.substring(0,2);
            printString += " ";
            printString += result.substring(2,4);
            printString += " ";
            printString += result.substring(4,6);
            printString += " ";
            printString += result.substring(6);
            System.out.println(printString);
            */
            sendString=result;
            rfid.setText(result);


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


}