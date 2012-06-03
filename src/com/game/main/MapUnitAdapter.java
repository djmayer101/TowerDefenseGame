package com.game.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class MapUnitAdapter extends BaseAdapter {
    private Context mContext;
	private MapUnitView[] mMapUnitViewArray;
    // references to our images
    
    public MapUnitAdapter(Context c, MapUnitView[] mMapUnitViewArray) {
        mContext = c;
        this.mMapUnitViewArray = mMapUnitViewArray;
        //generate_mThumbIds();

       
    }


    public int getCount() {
        return mMapUnitViewArray.length;
    }

    public Object getItem(int position) {
        return mMapUnitViewArray[position];
    }
	public long getItemId(int position) {
		return position;
	}


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        /*MapUnitView mMapUnitView;
        if (convertView == null){
        	mMapUnitView = new MapUnitView(mContext,10,10,10);
        }
        else{
        	mMapUnitView = (MapUnitView) convertView;
        }
       // mMapUnitView = this.mMapUnitViewArray[position];
        */
        return new MapUnitView(mContext,10,10,10);
    }



}