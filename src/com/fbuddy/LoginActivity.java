package com.fbuddy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
 
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
   
	 private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	 private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	 public  LocationManager locationManager;
	 SharedPreferences preferences;
	 SharedPreferences.Editor editor;
	 String url;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText txtName=(EditText)findViewById(R.id.txt_name);
	     final EditText txtPhno=(EditText)findViewById(R.id.txt_phno);
	     final Button btnLogin = (Button)findViewById(R.id.btn_login);
         preferences = getSharedPreferences("SETTINGS", 0);
      //  String name = preferences.getString("NAME", " ");
        String phno = preferences.getString("PHNO", "4444");
        Boolean fb= preferences.getBoolean("CHECK", false);
        editor = preferences.edit();
		//editor.putString("NAME", txtName.getText().toString());
		//editor.putString("PHNO", txtPhno.getText().toString());
		
        Toast.makeText(this, "Initially    "+phno + fb, Toast.LENGTH_LONG).show();
			 url = "http://10.0.2.2/practice/fbuddy_demo/check_login.php?";
				url = url + "txt_phno=" + phno + "&";
				 url = url + "flagbit=" + 1 + "&";
				locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			        locationManager.requestLocationUpdates(
			                LocationManager.GPS_PROVIDER,
			                MINIMUM_TIME_BETWEEN_UPDATES,
			               MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
			                new MyLocationListener()
			);
			       Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		            String url1 = "";
		           Double longitude=location.getLongitude(),lattitude=location.getLatitude();
		          
					url1 = url1 + "lattitude=" + lattitude + "&";
					url1 = url1 + "longitude=" + longitude;      
			              url = url + url1;	
			              String status = executeQuery(url);
			              if(status.equals("0"))
			              {
			            	  editor.putBoolean("CHECK", false);
								editor.commit();
			              }
			// intent
	
	
		if(fb==false)
		{
			
			Toast.makeText(this, phno + fb, Toast.LENGTH_LONG).show();       
		} 
		else
			Toast.makeText(this, phno + fb, Toast.LENGTH_LONG).show();       
        btnLogin.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub				
				 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			        locationManager.requestLocationUpdates(
			                LocationManager.GPS_PROVIDER,
			                MINIMUM_TIME_BETWEEN_UPDATES,
			               MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
			                new MyLocationListener()
			);			   
			        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		            String url1 = "";
		            Double longitude=location.getLongitude(),lattitude=location.getLatitude();
		            url = "http://10.0.2.2/practice/fbuddy_demo/insert_login.php?";
		            url = url + "txt_name=" + txtName.getText().toString() + "&";
					url = url + "txt_phno=" + txtPhno.getText().toString() + "&";
					 url = url + "flagbit=" + 1 + "&";
					url1 = url1 + "lattitude=" + lattitude + "&";
					url1 = url1 + "longitude=" + longitude;      
			        url = url + url1;		
			         executeQuery(url);      
					editor.putString("PHNO", txtPhno.getText().toString());
					editor.putBoolean("CHECK", true);
					editor.commit();
					Intent intent = new Intent(LoginActivity.this, Fbuddy_contactsActivity.class);
	            	 startActivity(intent);
	            	 finish();
			}
		});
    
    }
    public String executeQuery(String url)
    {
    	//execute the URL, and get the result
    	String result = null;
		try
		{
			HttpClient http = new DefaultHttpClient();
			HttpGet http_get = new HttpGet(url);
			HttpResponse http_response = http.execute(http_get);
			HttpEntity http_entity = http_response.getEntity();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(http_entity.getContent()));
			 result = br.readLine();

			Toast.makeText(getApplicationContext(), "Hello "+result, Toast.LENGTH_LONG).show();
		}
		catch (Exception ex)
		{
			Toast.makeText(getApplicationContext(),ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		return result;
    }
    public String showCurrentLocation() {
    	 
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
 
	        if (location != null) {
          /*  String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );*/
            String url1 = "";
            Double longitude=location.getLongitude(),lattitude=location.getLatitude();
           // url=url + "&";
			url1 = url1 + "lattitude=" + lattitude.toString() + "&";
			url1 = url1 + "longitude=" + longitude.toString();
			return url1;
			//executeQuery(url);
          // Toast.makeText(getApplicationContext(), "aa",Toast.LENGTH_LONG).show();
        }
			return null;
    }
	 
    private class MyLocationListener implements LocationListener {
   	 
        public void onLocationChanged(Location location) {
	            /*String message = String.format(
	                 //   "New Location \n Longitude: %1$s \n Latitude: %2$s",
                 //   location.getLongitude(), location.getLatitude()
            );*/
	          //  Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
	 
        public void onStatusChanged(String s, int i, Bundle b) {
             Toast.makeText(getApplicationContext(), "Provider status changed",
	                   Toast.LENGTH_LONG).show();
        }
 
	        public void onProviderDisabled(String s) {
            Toast.makeText(getApplicationContext(),"Provider disabled by the user. GPS turned off",
                    Toast.LENGTH_LONG).show();
        }
	 
        public void onProviderEnabled(String s) {
	           Toast.makeText(getApplicationContext(),"Provider enabled by the user. GPS turned on",
	                    Toast.LENGTH_LONG).show();

}
}
 
}