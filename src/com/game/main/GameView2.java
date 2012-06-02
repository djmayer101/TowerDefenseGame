package com.game.main;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.GridView;




/**
 * TileView: a View-variant designed for handling arrays of "icons" or other
 * drawables.
 * 
 */
public class GameView2 extends GridView {

    /**
     * Parameters controlling the size of the tiles and their range within view.
     * Width/Height are in pixels, and Drawables will be scaled to fit to these
     * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
     */

    protected static int mTileSize = 40;

    protected static int mXTileCount =30;
    protected static int mYTileCount =30;

    private static int mXOffset = 1;
    private static int mYOffset = 1;
    


    /**
     * A hash that maps integer handles specified by the subclasser to the
     * drawable that will be used for that reference
     */
    private Bitmap[][] mTileArray;  
    private MapUnitView[][] mMapUnitViewArray;

    /**
     * A two-dimensional array of integers in which the number represents the
     * index of the tile that should be drawn at that locations
     */
    private int[][] mTileGrid;

    private final Paint mPaint = new Paint();
    private static final int RED_STAR = 1;
    Resources r;

	private AttributeSet attrs;

    public GameView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.attrs = attrs;
        this.init();
    }

    public GameView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        this.init();

    }
    public void init(){
        r = this.getContext().getResources();
        mTileArray = new Bitmap[mXTileCount][mYTileCount];
        mMapUnitViewArray = new MapUnitView[mXTileCount][mYTileCount];
        
        for(int i=0; i < mXTileCount; i++){
			for( int j=0;j< mYTileCount;j++){
            	int mapUnitViewXOffset = mXOffset + i*mTileSize;
            	int mapUnitViewYOffset = mYOffset + j*mTileSize;
            	mMapUnitViewArray[i][j]=new MapUnitView(getContext(),attrs, mTileSize, mapUnitViewXOffset, mapUnitViewYOffset);
        	}
        }
        
        
    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mXTileCount = (int) Math.floor(w / mTileSize);
        mYTileCount = (int) Math.floor(h / mTileSize);
    	

        mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
        mYOffset = ((h - (mTileSize * mYTileCount)) / 2);
    }



    /**
     * Resets all tiles to 0 (empty)
     * 
     */
    public void clearTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
              //  setTile(0, x, y);
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
    public void setTile(int Xtileindex, int Ytileindex, int x, int y) {
        //mTileGrid[x][y] = Xtileindex;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < mXTileCount; x += 1) {
            for (int y = 0; y < mYTileCount; y += 1) {
            	mMapUnitViewArray[x][y].draw(canvas);
            	
            }
        }

    }
        

}