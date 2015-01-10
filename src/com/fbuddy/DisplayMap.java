package com.fbuddy;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DisplayMap extends MapActivity
{
	private MapView mapView;
	double latitude,longitude,latitudeDb,longitudeDb;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locate);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		Button btnShowRoute = (Button)findViewById(R.id.btnShowRoute);
	    Drawable marker=getResources().getDrawable(R.drawable.ptr);
		marker.setBounds( (int) (-marker.getIntrinsicWidth()/2),-marker.getIntrinsicHeight(),(int) (marker.getIntrinsicWidth()/2),0);
		InterestingLocations funPlaces =new InterestingLocations(marker);
		mapView.getOverlays().add(funPlaces);
		GeoPoint pt = funPlaces.getCenterPt();
		int latSpan = funPlaces.getLatSpanE6();
		int lonSpan = funPlaces.getLonSpanE6();
		Log.v("Overlays", "Lat span is " + latSpan);
		Log.v("Overlays", "Lon span is " + lonSpan);
		MapController mc = mapView.getController();
		mc.setCenter(pt);
		mc.animateTo(pt);
		mc.zoomToSpan((int)(latSpan*1.5), (int)(lonSpan*1.5));
		btnShowRoute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr="+ latitude + "," + longitude+ "&daddr="+latitudeDb+","+longitudeDb+""));
				 intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
				startActivity(intent);			
			}
		});
	}
	@Override
	protected boolean isLocationDisplayed()
	{
		return false;
	}
	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}
	class InterestingLocations extends ItemizedOverlay<OverlayItem>
	{
		private ArrayList<OverlayItem> locations =new ArrayList<OverlayItem>();
		private GeoPoint center = null;
		public InterestingLocations(Drawable marker)
		{
			super(marker);
			// create locations of interest
			//nagpur: 21.125498,79.101563
			//Pune: 18.542117,73.828125
			latitude=21.125498;
			longitude=79.101563;
			latitudeDb=21.142117;
			longitudeDb=79.128125;
			GeoPoint disneyMagicKingdom =new GeoPoint((int)(21.125498*1E6),(int)(79.101563*1E6));
			GeoPoint disneySevenLagoon =new GeoPoint((int)(21.142117*1E6),(int)(79.128125*1E6));
			locations.add(new OverlayItem(disneyMagicKingdom ,"Magic Kingdom", "Magic Kingdom"));
			locations.add(new OverlayItem(disneySevenLagoon ,"Seven Seas Lagoon", "Seven Seas Lagoon"));
			populate();
		}
			// We added this method to find the middle point of the cluster
			// Start each edge on its opposite side and move across with
			// each point. The top of the world is +90, the bottom -90,
			// the west edge is -180, the east +180
			public GeoPoint getCenterPt() 
			{
				if(center == null)
			 {
				int northEdge = -90000000; // i.e., -90E6 microdegrees
				int southEdge = 90000000;
				int eastEdge = -180000000;
				int westEdge = 180000000;
				Iterator<OverlayItem> iter = locations.iterator();
				while(iter.hasNext())
				{
					GeoPoint pt = iter.next().getPoint();
					if(pt.getLatitudeE6() > northEdge)
						northEdge = pt.getLatitudeE6();
					if(pt.getLatitudeE6() < southEdge)
						southEdge = pt.getLatitudeE6();
					if(pt.getLongitudeE6() > eastEdge)
						eastEdge = pt.getLongitudeE6();
					if(pt.getLongitudeE6() < westEdge)
						westEdge = pt.getLongitudeE6();
				}
				center = new GeoPoint((int)((northEdge +southEdge)/2),(int)((westEdge + eastEdge)/2));
			}
			return center;
		  }
			@Override
			public void draw(Canvas canvas, MapView mapView, boolean shadow)
			{
			// Hide the shadow by setting shadow to false
				super.draw(canvas, mapView, shadow);
			}
			@Override
			protected OverlayItem createItem(int i)
			{
				return locations.get(i);
			}
			@Override
			public int size() 
			{
				return locations.size();
			}
		}
	}
