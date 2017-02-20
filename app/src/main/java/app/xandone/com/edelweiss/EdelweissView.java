package app.xandone.com.edelweiss;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的下雪View
 * Created by xandone on 2017/2/20.
 */
public class EdelweissView extends View {
    public static final int SPEED_X = 4;
    public static final int SPEED_Y = 10;
    public static final int SNOWFLAKE_W_MAX = 120;
    public static final int SNOWFLAKE_W_MIN = 40;
    public static final int SNOWFLAKE_ANIM_TIEM_MAX = 6000;
    public static final int SNOWFLAKE_ANIM_TIEM_MIN = 1000;

    public static final int ALL_SNOWFLAKE = 30;

    private int mWidth;
    private int mHeight;

    private Context mContext;
    private List<Snowflake> snowflakeList = new ArrayList<>();

    public EdelweissView(Context context) {
        this(context, null);
    }

    public EdelweissView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EdelweissView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < snowflakeList.size(); i++) {
            snowflakeList.get(i).drawSnow(canvas, Utils.randomInt(SPEED_X), Utils.randomIntPositive(SPEED_Y));
        }
        getHandler().postDelayed(runnable, 50);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        for (int i = 0; i < ALL_SNOWFLAKE; i++) {
            int s_w = Utils.randomIntPositive(SNOWFLAKE_W_MAX, SNOWFLAKE_W_MIN);
            int s_h = Utils.randomIntPositive(SNOWFLAKE_W_MAX, SNOWFLAKE_W_MIN);
            snowflakeList.add(new Snowflake(mContext, s_w, s_h, Utils.randomIntPositive(mWidth), Utils.randomIntPositive(mHeight) - s_w, mWidth, mHeight));
            snowflakeList.get(i).snowRotateAnim(Utils.randomIntPositive(SNOWFLAKE_ANIM_TIEM_MAX, SNOWFLAKE_ANIM_TIEM_MIN));
        }
    }
}
