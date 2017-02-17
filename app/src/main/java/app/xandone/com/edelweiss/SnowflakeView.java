package app.xandone.com.edelweiss;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xandone on 2017/2/17.
 */
public class SnowflakeView extends View {
    private Paint mPaint;
    private Path mHexagonPath_1;
    private Path mHexagonPath_2;
    private Path mHexagonPath_3;

    private int mDefaultSize;
    private Context mContext;

    private int mWidth, mHeight;
    private int mLength;//六边形的长度
    private static final int LEN_TIMES = 8;

    private int mThornPadding_1;
    private int mThornPadding_2;
    private int mThornHeight_1;
    private int mThornHeight_2;

    private int mCornerThornTrunkPadding_1;
    private int mCornerThornTrunkPadding_2;
    private int mCornerThornTrunkHeight;
    private int mCornerThornTrunkHeight_1;
    private int mCornerThornTrunkHeight_2;
    private int mCornerThornTrunkHeight_3;


    private int mCornerThornPadding_1;
    private int mCornerThornPadding_2;
    private int mCornerThornHeight_1;
    private int mCornerThornHeight_2;


    public SnowflakeView(Context context) {
        this(context, null);
    }

    public SnowflakeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnowflakeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHexagonPath_1 = new Path();
        mHexagonPath_2 = new Path();
        mHexagonPath_3 = new Path();

        mDefaultSize = Utils.dp2px(mContext, 300);

        mThornPadding_1 = Utils.dp2px(mContext, 1);
        mThornPadding_2 = Utils.dp2px(mContext, 3);
        mThornHeight_1 = Utils.dp2px(mContext, 10);
        mThornHeight_2 = Utils.dp2px(mContext, 20);

        mCornerThornTrunkPadding_1 = Utils.dp2px(mContext, 2);
        mCornerThornTrunkPadding_2 = Utils.dp2px(mContext, 6);
        mCornerThornTrunkHeight = Utils.dp2px(mContext, 60);
        mCornerThornTrunkHeight_1 = Utils.dp2px(mContext, 70);
        mCornerThornTrunkHeight_2 = Utils.dp2px(mContext, 90);
        mCornerThornTrunkHeight_3 = Utils.dp2px(mContext, 100);

        mCornerThornPadding_1 = Utils.dp2px(mContext, 1);
        mCornerThornPadding_2 = Utils.dp2px(mContext, 6);
        mCornerThornHeight_1 = Utils.dp2px(mContext, 30);
        mCornerThornHeight_2 = Utils.dp2px(mContext, 40);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        drawHexagon(canvas);
        drawThorn(canvas);
        drawCornerThornTrunk(canvas);
    }

    /**
     * 绘制六边形
     *
     * @param canvas
     */
    public void drawHexagon(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        int x1 = -mLength / 2;
        int y1 = -(int) (Math.sqrt(3) * mLength / 2);
        int x2 = 0;
        int y2 = y1;
        canvas.save();
        for (int i = 0; i < 6; i++) {
            mPaint.setStrokeWidth(30);
            canvas.drawLine(x1, y1, x1 + mLength, y1, mPaint);
            mPaint.setStrokeWidth(8);
            canvas.drawLine(x2, y2, 0, 0, mPaint);
            canvas.rotate(60, 0, 0);
        }
        canvas.restore();
    }

    /**
     * 绘制边刺
     *
     * @param canvas
     */
    public void drawThorn(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        int y = -(int) (Math.sqrt(3) * mLength / 2);
        mHexagonPath_1.moveTo(0 - mThornPadding_1, y);
        mHexagonPath_1.lineTo(0 - mThornPadding_1 - mThornPadding_2, y - mThornHeight_1);
        mHexagonPath_1.lineTo(0, y - mThornHeight_2);
        mHexagonPath_1.lineTo(0 + mThornPadding_1 + mThornPadding_2, y - mThornHeight_1);
        mHexagonPath_1.lineTo(0 + mThornPadding_1, y);
        mHexagonPath_1.close();
        canvas.save();
        for (int i = 0; i < 6; i++) {
            canvas.drawPath(mHexagonPath_1, mPaint);
            canvas.rotate(60, 0, 0);
        }
        canvas.restore();
    }

    /**
     * 绘制角刺的主干
     *
     * @param canvas
     */
    public void drawCornerThornTrunk(Canvas canvas) {
        int h1 = Utils.dp2px(mContext, 16);
        int h2 = Utils.dp2px(mContext, 30);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        int y = -(int) (Math.sqrt(3) * mLength / 2);
        mHexagonPath_3.moveTo(0 - mCornerThornTrunkPadding_1, y);
        mHexagonPath_3.lineTo(0 - mCornerThornTrunkPadding_1, y - mCornerThornTrunkHeight);
        mHexagonPath_3.lineTo(0 - mCornerThornTrunkPadding_1 - mCornerThornTrunkPadding_2, y - mCornerThornTrunkHeight_1);
        mHexagonPath_3.lineTo(0 - mCornerThornTrunkPadding_1 - mCornerThornTrunkPadding_2, y - mCornerThornTrunkHeight_2);
        mHexagonPath_3.lineTo(0, y - mCornerThornTrunkHeight_3);
        mHexagonPath_3.lineTo(0 + mCornerThornTrunkPadding_1 + mCornerThornTrunkPadding_2, y - mCornerThornTrunkHeight_2);
        mHexagonPath_3.lineTo(0 + mCornerThornTrunkPadding_1 + mCornerThornTrunkPadding_2, y - mCornerThornTrunkHeight_1);
        mHexagonPath_3.lineTo(0 + mCornerThornTrunkPadding_1, y - mCornerThornTrunkHeight);
        mHexagonPath_3.lineTo(0 + mCornerThornTrunkPadding_1, y);
        mHexagonPath_3.close();
        canvas.save();
        canvas.rotate(30, 0, 0);
        for (int i = 0; i < 6; i++) {
            canvas.drawPath(mHexagonPath_3, mPaint);
            drawCornerThorn(canvas, h1);
            drawCornerThorn(canvas, h2);
            canvas.rotate(60, 0, 0);
        }
        canvas.restore();
    }

    /**
     * 绘制角刺
     *
     * @param canvas
     * @param h
     */
    public void drawCornerThorn(Canvas canvas, int h) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        int y = -(int) (Math.sqrt(3) * mLength / 2);
        mHexagonPath_2.moveTo(0 - mCornerThornPadding_1, y);
        mHexagonPath_2.lineTo(0 - mCornerThornPadding_1 - mCornerThornPadding_2, y - mCornerThornHeight_1);
        mHexagonPath_2.lineTo(0, y - mCornerThornHeight_2);
        mHexagonPath_2.lineTo(0 + mCornerThornPadding_1 + mCornerThornPadding_2, y - mCornerThornHeight_1);
        mHexagonPath_2.moveTo(0 + mCornerThornPadding_1, y);
        mHexagonPath_2.close();
        canvas.save();
        canvas.translate(0, -h);
        canvas.rotate(-30, 0, y);
        canvas.drawPath(mHexagonPath_2, mPaint);
        canvas.rotate(60, 0, y);
        canvas.drawPath(mHexagonPath_2, mPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = measureDimension(widthMeasureSpec);
        int h = measureDimension(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    public int measureDimension(int measureSpec) {
        int size = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                size = Math.min(mDefaultSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                size = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                size = mDefaultSize;
                break;
            default:
                size = mDefaultSize;
                break;
        }
        return size;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mLength = mWidth / LEN_TIMES;
    }
}
