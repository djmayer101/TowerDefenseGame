package com.game.main;



import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MapUnitImageAdapter extends BaseAdapter {
    private Context mContext;
	private int columnSize;
	private int numSquares;

    public MapUnitImageAdapter(Context c,int columnSize,int numSquares) {
        mContext = c;
        this.columnSize = columnSize;
        this.numSquares = numSquares;
    }

    public int getCount() {
        return this.numSquares;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(this.columnSize, this.columnSize));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
           
        }

        //imageView.setImageResource(R.drawable.gs);

        return imageView;
    }

    // references to our images
   
}