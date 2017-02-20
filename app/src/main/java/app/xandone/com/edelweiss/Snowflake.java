package app.xandone.com.edelweiss;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.animation.LinearInterpolator;

/**
 * 雪花类
 * Created by xandone on 2017/2/20.
 */
public class Snowflake {
    private Context mContext;

    private Paint mPaint;
    private Path mHexagonPath_1;
    private Path mHexagonPath_2;
    private Path mHexagonPath_3;

    private int mOriginX, mOriginY;
    private int mLength;//六边形的长度
    private static final int LEN_TIMES = 8;
    private int mWidth, mHeight;

    private int mW, mH;

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

    private int transHeight_1;
    private int transHeight_2;

    public int mProportion;
    private int mAngle;

    private ValueAnimator mValueAnimator;
    private LinearInterpolator mLinearInterpolator;

    /**
     * 构造函数
     *
     * @param context
     * @param width   初始宽度
     * @param height  初始高度
     * @param originX 初始坐标
     * @param originY 初始Y坐标
     * @param w       父容器宽度
     * @param h       父容器高度
     */
    public Snowflake(Context context, int width, int height, int originX, int originY, int w, int h) {
        this.mContext = context;
        mWidth = width;
        mHeight = height;
        mOriginX = originX;
        mOriginY = originY;
        mW = w;
        mH = h;
        init();
    }

    public void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHexagonPath_1 = new Path();
        mHexagonPath_2 = new Path();
        mHexagonPath_3 = new Path();

        mProportion = Utils.dp2px(mContext, 300);

        mValueAnimator = ValueAnimator.ofInt(0, 360);
        mLinearInterpolator = new LinearInterpolator();

        mAngle = 0;

        mLength = mWidth / LEN_TIMES;

        mThornPadding_1 = Utils.dp2px(mContext, 1) * mWidth / mProportion;
        mThornPadding_2 = Utils.dp2px(mContext, 3) * mWidth / mProportion;
        mThornHeight_1 = Utils.dp2px(mContext, 10) * mWidth / mProportion;
        mThornHeight_2 = Utils.dp2px(mContext, 20) * mWidth / mProportion;

        mCornerThornTrunkPadding_1 = Utils.dp2px(mContext, 2) * mWidth / mProportion;
        mCornerThornTrunkPadding_2 = Utils.dp2px(mContext, 6) * mWidth / mProportion;
        mCornerThornTrunkHeight = (int) ((mWidth / 2 - mLength) * 0.6f);
        mCornerThornTrunkHeight_1 = (int) ((mWidth / 2 - mLength) * 0.7f);
        mCornerThornTrunkHeight_2 = (int) ((mWidth / 2 - mLength) * 0.9f);
        mCornerThornTrunkHeight_3 = mWidth / 2 - mLength;

        mCornerThornPadding_1 = Utils.dp2px(mContext, 1) * mWidth / mProportion;
        mCornerThornPadding_2 = Utils.dp2px(mContext, 6) * mWidth / mProportion;
        mCornerThornHeight_1 = (int) ((mWidth / 2 - mLength) * 0.3f);
        mCornerThornHeight_2 = (int) ((mWidth / 2 - mLength) * 0.4f);

        transHeight_1 = (int) ((mWidth / 2 - mLength) * 0.26f);
        transHeight_2 = (int) ((mWidth / 2 - mLength) * 0.4f);
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
            mPaint.setStrokeWidth(6);
            canvas.drawLine(x1, y1, x1 + mLength, y1, mPaint);
            mPaint.setStrokeWidth(2);
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
            drawCornerThorn(canvas, transHeight_1);
            drawCornerThorn(canvas, transHeight_2);
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


    /**
     * 雪花的旋转动画(属性)
     *
     * @param duration
     */
    public void snowRotateAnim(int duration) {
        if (null == mValueAnimator) {
            return;
        }
        mValueAnimator.setDuration(duration);
        mValueAnimator.setInterpolator(mLinearInterpolator);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAngle = (int) mValueAnimator.getAnimatedValue();
            }
        });
        mValueAnimator.start();
    }

    public void drawSnow(Canvas canvas, int x, int y) {
        canvas.save();
        canvas.translate(mOriginX, mOriginY);
        canvas.rotate(mAngle);
        drawHexagon(canvas);
        drawThorn(canvas);
        drawCornerThornTrunk(canvas);
        setOriginX(x);
        setOriginY(y);
        canvas.restore();
    }

    /**
     * 更新雪花的X坐标
     *
     * @param x
     */
    public void setOriginX(int x) {
        this.mOriginX += x;
        if (mOriginX > mW || mOriginX < -mWidth) {
            mOriginX = Utils.randomIntPositive(mW);
        }
    }

    /**
     * 更新雪花的Y坐标
     *
     * @param y
     */
    public void setOriginY(int y) {
        this.mOriginY += y;
        if (mOriginY > mH) {
            mOriginY = -mHeight;
        }
    }
}
