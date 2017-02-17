package app.xandone.com.edelweiss;

import android.content.Context;

/**
 * Created by xandone on 2017/1/3.
 */
public class Utils {

    public static int dp2px(Context context, int values) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

}
