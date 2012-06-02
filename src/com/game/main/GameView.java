package com.game.main;




import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


/**
 * TileView: a View-variant designed for handling arrays of "icons" or other
 * drawables.
 * 
 */
public class GameView extends View {

    /**
     * Parameters controlling the size of the tiles and their range within view.
     * Width/Height are in pixels, and Drawables will be scaled to fit to these
     * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
     */

    protected static int mTileSize = 30;

    protected static int mXTileCount =11;
    protected static int mYTileCount =11;

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
   // private int[][] mTileGrid;

    private final Paint mPaint = new Paint();
    private static final int RED_STAR = 1;

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTileSize = 10;
        mTileArray = new Bitmap[mXTileCount][mYTileCount];
        
        for(int i=0; i < mXTileCount; i++){
        	for( int j=0;j< mYTileCount;j++){
            	Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
            	bitmap.setPixel(0, 0, Color.GREEN);
            	mTileArray[i][j]=bitmap;
        	}
        }
        Resources r = this.getContext().getResources();
        loadTile(RED_STAR, r.getDrawable(R.drawable.redstar));
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTileArray = new Bitmap[mXTileCount][mYTileCount];
        
        for(int i=0; i < mXTileCount; i++){
        	for( int j=0;j< mYTileCount;j++){
            	Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
            	bitmap.setPixel(0, 0, Color.GREEN);
            	mTileArray[i][j]=bitmap;
        	}
        }
        Resources r = this.getContext().getResources();
        loadTile(RED_STAR, r.getDrawable(R.drawable.redstar));
    }



    /**
     * Rests the internal array of Bitmaps used for drawing tiles, and
     * sets the maximum index of tiles to be inserted
     * 
     * @param tilecount
     */

    public void resetTiles(int tilecount) {
        //mTileArray = new Bitmap[tilecount];
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //mXTileCount = (int) Math.floor(w / mTileSize);
        //mYTileCount = (int) Math.floor(h / mTileSize);

        mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
        mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

        //mTileGrid = new int[mXTileCount][mYTileCount];
        clearTiles();
    }

    /**
     * Function to set the specified Drawable as the tile for a particular
     * integer key.
     * 
     * @param key
     * @param tile
     */
    public void loadTile(int key, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileSize, mTileSize);
        tile.draw(canvas);

        mTileArray[5][5] = bitmap;
    }

    /**
     * Resets all tiles to 0 (empty)
     * 
     */
    public void clearTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
                setTile(0, x, y);
            }
        }
    }

    /**
     * Used to indicate that a particular tile (set with loadTile and referenced
     * by an integer) should be drawn at the given x/y coordinates during the
     * next invalidate/draw cycle.
     * 
     * @param tileindex
     * @param x
     * @param y
     */
    public void setTile(int tileindex, int x, int y) {
       // mTileGrid[x][y] = tileindex;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < mXTileCount; x += 1) {
            for (int y = 0; y < mYTileCount; y += 1) {
            	canvas.drawBitmap(mTileArray[x][y], 
                        mXOffset + x * mTileSize,
                        mYOffset + y * mTileSize,
                        mPaint);

            }
        }

    }
        

}