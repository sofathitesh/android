package com.example.sofatsms2;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText user;
	EditText pass;
	Button login;
	protected static final String MY_ERROR_TAG = null;
	protected static final String TAG = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	user=(EditText) findViewById(R.id.username);
	pass=(EditText) findViewById(R.id.password);     
	login=(Button) findViewById(R.id.login);

	}
	public void senddata(){
		try {
			
		HttpClient httpclient=new DefaultHttpClient();
	    HttpPost httppost=new HttpPost("http://localhost/f.php");
	    List<NameValuePair> nameValuePair=new ArrayList<NameValuePair>();
	   nameValuePair.add(new BasicNameValuePair("name",user.toString()));
	   nameValuePair.add(new BasicNameValuePair("pass",pass.toString()));		
	   //	UrlEncodedFormEntity urlEncodedFormEntity=new UrlEncodedFormEntity(nameValuePair);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		user.setText(httpclient.execute(httppost).toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

		

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	}

