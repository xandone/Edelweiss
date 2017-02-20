package app.xandone.com.edelweiss;

import android.content.Context;

import java.util.Random;

/**
 * Created by xandone on 2017/1/3.
 */
public class Utils {
    private static Random random = new Random();

    public static int dp2px(Context context, int values) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

    public static int randomInt(int speed) {
        return random.nextInt(speed) - random.nextInt(speed);
    }

    public static int randomIntPositive(int speed) {
        return random.nextInt(speed);
    }

    public static int randomIntPositive(int max, int min) {
        return random.nextInt(max - min) + min;
    }

}
