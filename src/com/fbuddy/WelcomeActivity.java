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
import android.widget.Toast;

public class WelcomeActivity extends Activity
{
	SharedPreferences preferences;
	 SharedPreferences.Editor editor;
	 private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	 private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	 String url;
	 public  LocationManager locationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.main);
		Thread t =new Thread()
 		 {
 			 public void run()
 			 {
 				 try
 				 {
 					 sleep(5000);
 				 }
 				 catch(Exception e)
 				 {
 					 Toast.makeText(getApplicationContext(), "Background Error", Toast.LENGTH_LONG).show();
 				 }
 				 finally
 				 {
 					 finish();
 				 }
 			 }
 		 };
 		 t.start();
		  
		  preferences = getSharedPreferences("SETTINGS", 0);
		  String phno = preferences.getString("PHNO", "4444");
	        editor = preferences.edit();
	        url = "http://10.0.2.2/practice/fbuddy_demo/check_login.php?";
			url = url + "txt_phno=" + phno + "&";
			 url = url + "flagbit=" + 1 + "&";
			 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			 
		     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MINIMUM_TIME_BETWEEN_UPDATES,
		      MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,new MyLocationListener());	
		     Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		     Double longitude=location.getLongitude(),lattitude=location.getLatitude();
			   url = url + "lattitude=" + lattitude + "&";
				url = url + "longitude=" + longitude;      	
		              String status = executeQuery(url);
		        /*   try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  */
		            /*  try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
		              if(status.equals("0"))
		              {
							Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
			            	 startActivity(intent);
			            	// finish();
		              }
		              else
		              {
		            	  Intent intent = new Intent(WelcomeActivity.this, RadiusActivity.class);
			            	 startActivity(intent);
			            	 finish();
		              }
		           
	}

	private String executeQuery(String url2) 
	{
		// TODO Auto-generated method stub
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
	 private class MyLocationListener implements LocationListener {
	   	 
	        public void onLocationChanged(Location location) 
	        {
		            String message = String.format(
		                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
	                    location.getLongitude(), location.getLatitude()
	            );
		           Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	        }
		 
	        public void onStatusChanged(String s, int i, Bundle b) 
	        {
	             Toast.makeText(getApplicationContext(), "Provider status changed",
		                   Toast.LENGTH_LONG).show();
	        }
	 
		        public void onProviderDisabled(String s) 
		        {
	            Toast.makeText(getApplicationContext(),"Provider disabled by the user. GPS turned off",
	                    Toast.LENGTH_LONG).show();
	        }
		 
	        public void onProviderEnabled(String s) 
	        {
		           Toast.makeText(getApplicationContext(),"Provider enabled by the user. GPS turned on",
		                    Toast.LENGTH_LONG).show();

	        }
	}

}
