package com.game.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class MapUnitView extends View {
	private int mTileSize;
	

	public MapUnitView(Context context,int mTileSize) {
		super(context);
		this.mTileSize = mTileSize;
		//loadTile();
	}

	public void loadTile(Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileSize, mTileSize);
        tile.draw(canvas);
        
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(1, 1, 1, 1);
        imageView.layout(1, 1, 10, 10);


        imageView.setImageResource(1);

        //mTileArray[X][Y] = bitmap;
    }
}
