package app.xandone.com.edelweiss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SnowflakeView snowflakeView;
    private SnowflakeView snowflakeView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        snowflakeView = (SnowflakeView) findViewById(R.id.snowflakeView);
//        snowflakeView1 = (SnowflakeView) findViewById(R.id.snowflakeView1);
//
//        snowflakeView.snowAnim2(6000);
//        snowflakeView1.snowAnim2(1000);
    }
}
