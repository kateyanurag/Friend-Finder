package com.fbuddy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fbuddy_contactsActivity extends Activity {
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final Button btnContacts = (Button) findViewById(R.id.btnContacts);
		final ListView lstContacts = (ListView) findViewById(R.id.lstContacts);
		 final Dialog dlg = new Dialog(this);
		 dlg.setTitle("Services !!!");
	      dlg.setContentView(R.layout.custom_dialog);
		 final Button btnChat = (Button)dlg.findViewById(R.id.btnChat);
	     final Button btnLocate = (Button)dlg.findViewById(R.id.btnMap);
	    
	     

		btnContacts.setOnClickListener(new OnClickListener() {

			public void onClick(View v) 	{
				// Create an Array List for the ListView
				String name="",temp1 = "";
				String arrStudents[];
				int i=1,j;
				Boolean return_contact=false;
				ArrayList<String> arrListContacts = new ArrayList<String>();

				// read the contacts
				Uri contacts = ContactsContract.Data.CONTENT_URI;
				char[] phno_temp;
				char[] abc = new char['1'];
				String projection[] = { ContactsContract.Data._ID,
						ContactsContract.Data.DISPLAY_NAME,
						ContactsContract.Data.DATA1 };
				Cursor managedCursor = managedQuery(contacts, projection, null,
						null, null);
				arrStudents = check_contacts(lstContacts);
				
				
				startManagingCursor(managedCursor);
				if (managedCursor.moveToFirst()) {
					do {
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
						//phoneNumber = ;
						//phoneNumber = phno_converted;
				//		Toast.makeText(getApplicationContext(), "aaa " + phoneNumber, Toast.LENGTH_LONG).show();
						return_contact=compare_contacts(arrStudents,phoneNumber);
						if(return_contact==true)
						{
							arrListContacts.add(phoneNumber);
						}
					    // create a String to add to the list
						
						//str = id + "," + name + "," + phoneNumber;
												
						// add the string to the list
						//check_contacts(lstContacts);
						//arrListContacts.add(str);
											
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
						Intent i = new Intent(Fbuddy_contactsActivity.this, DisplayMap.class);
						 startActivity(i);
						 finish();
						//Intent intent = new Intent(packageContext, cls)
						 
						
					}
				});
		        btnChat.setOnClickListener(new OnClickListener()
		        {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dlg.cancel();
						Intent i = new Intent(Fbuddy_contactsActivity.this, ChatActivity.class);
						 startActivity(i);
						 finish();
					}
				});
			}
		});
		
	}
	public String[] check_contacts(ListView lstContacts)
    {
    	//Load all the Student List at the start
		String arrStudents[] = null;
		String url = "http://10.0.2.2/practice/fbuddy_demo/contacts_select.php?";
		try
		{
			HttpClient http = new DefaultHttpClient();
			HttpGet http_get = new HttpGet(url);
			HttpResponse http_response = http.execute(http_get);
			HttpEntity http_entity = http_response.getEntity();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(http_entity.getContent()));
			String result = br.readLine();

			if (result.trim().equals("") == false)
			{
				//split the result and form an array list
				arrStudents = result.split("EOR");
				
				//lstContacts.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arrStudents));
			}
		}
		catch (Exception ex)
		{
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		return arrStudents;
    }
	
	public Boolean compare_contacts(String arr[],String phno)
	{
		int i;
	  for(i=0;i<arr.length;i++)
		{
			if(phno.equalsIgnoreCase(arr[i]))
			{
				//Toast.makeText(getApplicationContext(), "success aa " + arr[i],Toast.LENGTH_LONG).show();
				return true;
			}
			//Toast.makeText(getApplicationContext(), "success aa " + arr[i],Toast.LENGTH_LONG).show();
		}
		
		//for(i=0;i<arr.length;i++)
		//{
		//Toast.makeText(getApplicationContext(), "s aa " + arr[i] ,Toast.LENGTH_LONG).show();
		//}
		//Toast.makeText(getApplicationContext(), "fail aa " + phno ,Toast.LENGTH_LONG).show();
		return false;
		
	}
}