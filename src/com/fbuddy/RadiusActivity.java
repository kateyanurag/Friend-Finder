package com.fbuddy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fbuddy.DisplayMap;
import com.fbuddy.ChatActivity;

 



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RadiusActivity extends Activity {
	
	 private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds    
	    protected LocationManager locationManager;    
	    protected EditText txtradius; 
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts);
		final Button btnContacts = (Button) findViewById(R.id.btnContacts);
		final ListView lstContacts = (ListView) findViewById(R.id.lstContacts);
		txtradius = (EditText)findViewById(R.id.txtradius); 
		 final Dialog dlg = new Dialog(this);
		 dlg.setTitle("Services !!!");
	      dlg.setContentView(R.layout.custom_dialog);
		 final Button btnChat = (Button)dlg.findViewById(R.id.btnChat);
	     final Button btnLocate = (Button)dlg.findViewById(R.id.btnMap);
		 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         
	        locationManager.requestLocationUpdates(
	                LocationManager.GPS_PROVIDER,
	                MINIMUM_TIME_BETWEEN_UPDATES,
	               MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
	                new MyLocationListener()
	        );
		btnContacts.setOnClickListener(new OnClickListener() {

			public void onClick(View v) 	{
				int k=0,t,ft=0,ftt=0;
				String[] return_users=showCurrentLocation();
				String[] user_phno = new String[50];
				String[] arrStudents = new String[50];
				String[] final_users = new String[50];
				 
				for(k=0;k<return_users.length;k++)
				{
					user_phno=return_users[k].split(",");
					arrStudents[k]=user_phno[2];
					 
				}
			 
				
				String name="",temp1 = "";
				
				int i=1,j;
				Boolean return_contact=false;
				ArrayList<String> arrListContacts = new ArrayList<String>();

				// read the contacts
				Uri contacts = ContactsContract.Data.CONTENT_URI;
				char[] phno_temp;
				char[] abc = new char['1'];
				//String phno_converted = "";

				String projection[] = { ContactsContract.Data._ID,
						ContactsContract.Data.DISPLAY_NAME,
						ContactsContract.Data.DATA1 };
				Cursor managedCursor = managedQuery(contacts, projection, null,
						null, null);
				//int q;
				 
				//arrStudents = check_contacts();
				 
				
				
				startManagingCursor(managedCursor);
				if (managedCursor.moveToFirst()) {
					do {
						String str = "" ;
						String id = managedCursor.getString(managedCursor
								.getColumnIndex(ContactsContract.Data._ID));
						name = managedCursor.getString(managedCursor
								.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
						if(i==1)
						{
							i=0;
							temp1=name;
						}
						else
						{
							if(name.equals(temp1))
							{
								continue;
							}
							else
							{
								temp1=name;
							}
						}
						String phoneNumber = managedCursor.getString(managedCursor
								.getColumnIndex(ContactsContract.Data.DATA1)).toString();
						phno_temp = phoneNumber.toCharArray();
						//ttemp[0]='1';
						phoneNumber = "";
						for(i=0,j=0;i<phno_temp.length;i++)
						{
							if(phno_temp[i]!='-')
							{
								abc[j] = phno_temp[i];
								phoneNumber = phoneNumber + Character.toString(abc[j]);
								//Toast.makeText(getApplicationContext(), "qq " + phoneNumber , Toast.LENGTH_LONG).show();
								j++;
							}
						}
						 
						return_contact=compare_contacts(arrStudents,phoneNumber);
						if(return_contact==true)
						{
							arrListContacts.add(name +  " , " + phoneNumber);
						}
					     
											
						} while (managedCursor.moveToNext());
				}
				stopManagingCursor(managedCursor);
				managedCursor.close();

				// update the ListView
				lstContacts.setAdapter(new ArrayAdapter<String>(
						getApplicationContext(),
						android.R.layout.simple_list_item_1, arrListContacts));

			}

			
		});
		lstContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
			{
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
				
				  dlg.show();
				  btnLocate.setOnClickListener(new OnClickListener() {
					 
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dlg.cancel();
						Intent i = new Intent(RadiusActivity.this, DisplayMap.class);
						 startActivity(i);
						//Intent intent = new Intent(packageContext, cls)
						 
						
					}
				});
		        btnChat.setOnClickListener(new OnClickListener()
		        {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dlg.cancel();
						Intent i = new Intent(RadiusActivity.this, ChatActivity.class);
						 startActivity(i);
					}
				});
			}
		});
		
	}
	 protected String[] showCurrentLocation() {
	    	
	    	 
		 
	        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	 
		        if (location != null) {
	            String message = String.format(
	                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
	                    location.getLongitude(), location.getLatitude()
	            );
		        
	           // Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG).show();
		        }
	            String url = "http://10.0.2.2/fbuddy_demo/ttemp.php?";
	    		url = url + "lattitude=" + location.getLatitude() + "&";
	    		url = url + "longitude=" + location.getLongitude();
	    		String[] return_users=executeQuery(url);
	        
		        return return_users;
	 
		    }  
	 public String[] executeQuery(String url)//,Double radius)//,Location location)
	    {
		 String[] return_users=new String[50];
		 String[] final_return_users;
		 int k=0;
	    	//execute the URL, and get the result
			try
			{
		 		int i=0,flag=0;
				HttpClient http = new DefaultHttpClient();
				HttpGet http_get = new HttpGet(url);
				HttpResponse http_response = http.execute(http_get);
				HttpEntity http_entity = http_response.getEntity();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(http_entity.getContent()));
				String result = br.readLine();
				String final_result[] = result.split("EOR");
				Double latt,longt;
				Double radius = Double.parseDouble(txtradius.getText().toString());
				Double distance;
				for(i=0;i<final_result.length;i++)
				{
					flag=0;
			 
					Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				/* String message = String.format(
			                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
		                    location.getLongitude(), location.getLatitude()
		            );*/
					
				String a[]= final_result[i].split(",");
				latt=Double.parseDouble(a[4]);
				longt=Double.parseDouble(a[5]);
				Location loc = new Location("");
				loc.setLatitude(latt);
				loc.setLatitude(longt);
				distance = (double) location.distanceTo(loc);
				if(distance<=radius)
				{
					return_users[k]=final_result[i];
					k++;
				}
			 		
				}
				
			}
			catch (Exception ex)
			{
				Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();
			}
		 
			final_return_users=new String[k];
			 
			for(int i=0;i<k;i++)
			{
				final_return_users[i]=return_users[i];
			}
			
			//Toast.makeText(getApplicationContext(), "hello " +final_return_users.length, Toast.LENGTH_LONG).show();
			return final_return_users;
	    }
	 
	
	
	public Boolean compare_contacts(String arr[],String phno)
	{
		int i;
	  for(i=0;i<arr.length;i++)
		{
			if(phno.equalsIgnoreCase(arr[i]))
			{
				return true;
			}
			
		}
		
		return false;
		
	}
	
	 private class MyLocationListener implements LocationListener {
		 
	        public void onLocationChanged(Location location) {
		            String message = String.format(
		                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
	                    location.getLongitude(), location.getLatitude()
	            );
		            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	        }
		 
	        public void onStatusChanged(String s, int i, Bundle b) {
	            Toast.makeText(getApplicationContext(), "Provider status changed",
		                    Toast.LENGTH_LONG).show();
	        }
	 
		        public void onProviderDisabled(String s) {
	            Toast.makeText(getApplicationContext(),
	                    "Provider disabled by the user. GPS turned off",
	                    Toast.LENGTH_LONG).show();
	        }
		 
	        public void onProviderEnabled(String s) {
		            Toast.makeText(getApplicationContext(),
	                    "Provider enabled by the user. GPS turned on",
		                    Toast.LENGTH_LONG).show();

	}
	}
}