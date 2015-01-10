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
import android.content.Context;
//import android.content.Context;
//import android.os.AsyncTask;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends Activity 
{
	 ListView lstMsg ;
	 ArrayList<String> al ;
	 EditText txtChat;
	Button btnChat;
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        txtChat= (EditText)findViewById(R.id.txtChat);
       btnChat= (Button)findViewById(R.id.btnChat);
    //	OnClickListener btnStartListener;
	//	btnMsg.setOnClickListener(btnStartListener);
        try
        {
        	//private OnClickListener btnStartListener= new OnClickListener() {
		btnChat.setOnClickListener(new OnClickListener() {
				@Override		
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String msg = txtChat.getText().toString();
					  String []arr1 = msg.split(" ");
					int txtId=3;
					String url = "http://10.0.2.2/practice/fbuddy_demo/insert_msg.php?";
					url=url+"txtId="+txtId+"&";
					url=url+"txtMsg=";
					 for(int i=0;i<arr1.length;i++)
					    {
					    	url=url + arr1[i] + "%20";
					    }		
				    String res = executeQuery(url);
					Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
				}
			});
        
        	al = new ArrayList<String>();
        	lstMsg = (ListView)findViewById(R.id.lstMsg);
        CheckWallPosts chk = new CheckWallPosts();
           chk.execute(this);	
        }
        catch(Exception ex)
        {
        	Toast.makeText(this, "Error !!! :"+ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public class CheckWallPosts extends AsyncTask<Context, String, Void>{
   	 
    	
        	Context ctx;
    		@Override
    		protected Void doInBackground(Context... arg0) 
    		{
    			ctx = arg0[0];
    			try
    			{
    				while(true)
    				{
    					//check for the Web Request in the Background
    					String url = "http://10.0.2.2/practice/fbuddy_demo/async_demo/get_mbit_status.php?";
    					url=url+"txtId="+2;
    					
    					String res = executeQuery(url);
    					if(res.equals("1"))
    					{
    						//Now Select all Records and update the List View
    						url = "http://10.0.2.2/practice/fbuddy_demo/async_demo/select_all.php?";
    						url=url+"txtId="+2;
    						res = executeQuery(url);
    						String fields[] = res.split("EOR");
    						publishProgress(fields);
    						//publishProgress(res);
    					}
    					
    				}	
    			}
    			catch(Exception ex)
    			{
    				Toast.makeText(arg0[0], ex.getMessage(), Toast.LENGTH_LONG).show();	
    			}
    			return null;
    			//return null;
    		}
    		
    		@Override
    		protected void onProgressUpdate(String... values) {
    			// TODO Auto-generated method stub
    			super.onProgressUpdate(values);
    			try
    			{
    				String []val=values; 
    				for(int i=0;i<val.length;i++)
    				al.add(val[i]);
    				lstMsg.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, al));
    			}
    			catch(Exception ex)
    			{
    				Toast.makeText(ctx, ex.toString(), Toast.LENGTH_LONG).show();
    			}
    			
    		}
    		
    		public String executeQuery(String url)
    	    {
    	    	try {
    				HttpClient http = new DefaultHttpClient();
    				HttpGet http_get = new HttpGet(url);
    				HttpResponse http_resp = http.execute(http_get);
    				HttpEntity http_ent = http_resp.getEntity();
    				BufferedReader br = new BufferedReader(new InputStreamReader(http_ent.getContent()));
    				String str = br.readLine();
    				br.close();
    				http_ent = null;
    				http_resp = null;
    				http_get = null;
    				http = null;
    				
    				return str;
    			} catch (Exception e) {
    				// TODO: handle exception
    				return null;
    			}
    	    }


    }
    
    protected String executeQuery(String url) {
    	try {
			HttpClient http = new DefaultHttpClient();
			HttpGet http_get = new HttpGet(url);
			HttpResponse http_resp = http.execute(http_get);
			HttpEntity http_ent = http_resp.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(http_ent.getContent()));
			String str = br.readLine();
			br.close();
			http_ent = null;
			http_resp = null;
			http_get = null;
			http = null;
			
			return str;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	    }
