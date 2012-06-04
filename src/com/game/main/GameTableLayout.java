package com.game.main;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;



/**
 * TileView: a View-variant designed for handling arrays of "icons" or other
 * drawables.
 * 
 */
public class GameTableLayout extends TableLayout {

    /**
     * Parameters controlling the size of the tiles and their range within view.
     * Width/Height are in pixels, and Drawables will be scaled to fit to these
     * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
     */

    public static int mTileSize = 50;

    protected static int mXTileCount =10;
    protected static int mYTileCount =30;

    private static int mXOffset = 1;
    private static int mYOffset = 1;
    


    /**
     * A hash that maps integer handles specified by the subclasser to the
     * drawable that will be used for that reference
     */
    private Bitmap[][] mTileArray;  

    /**
     * A two-dimensional array of integers in which the number represents the
     * index of the tile that should be drawn at that locations
     */
    private int[][] mTileGrid;

    private final Paint mPaint = new Paint();
    private static final int RED_STAR = 1;
    Resources r;

	private ImageView imageView;



    public GameTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();

    }
    public void init(){
        r = this.getContext().getResources();
      
                    

           
    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //mXTileCount = (int) Math.floor(w / mTileSize);
        //mYTileCount = (int) Math.floor(h / mTileSize);
    	

        mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
        mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

       // mTileGrid = new int[mXTileCount][mYTileCount];
        //clearTiles();
    }

    /**
     * Function to set the specified Drawable as the tile for a particular
     * integer key.
     * 
     * @param key
     * @param tile
     */
    public void loadTile(int X, int Y,int image, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileSize, mTileSize);
        tile.draw(canvas);
        mTileArray[X][Y] = bitmap;
    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        for (int x = 0; x < mXTileCount; x += 1) {
            for (int y = 0; y < mYTileCount; y += 1) {
            	
            	canvas.drawBitmap(mTileArray[x][y], 
                        mXOffset + x * mTileSize,
                        mYOffset + y * mTileSize,
                        mPaint);

            }
        }*/
        imageView.draw(canvas);

    }
        

}