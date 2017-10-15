package site.yanhui.eventdispatch01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create at 2017/9/30 by 14:53
 * 作者：Archer
 * 功能描述： 用于学习Android事件的分发机制
 * http://blog.csdn.net/guolin_blog/article/details/9097463
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv)
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        button1.setOnTouchListener(new View.OnTouchListener() {
            /**\
             * @param v 点击到的view，在这里的view是一个button
             * @param event 获取当前事件的
             * @return 返回true or false决定是否消费事件
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch execute, action " + event.getAction());
                return true;//如果返回了true，onclick方法就不会被执行，因为事件被消费了。
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch execute, action " + event.getAction());
                return false; //返回false的话，onclick还是可以得到执行的，在源码里面有提及
            }
        });

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch execute, action " + event.getAction());

                return false;
            }
        });

    }

    @OnClick(R.id.button1)
    public void onViewClicked() {
        Toast.makeText(this, "button", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.imageView)
    public void imageViewClick() {
        Toast.makeText(this, "imageviewOnclick", Toast.LENGTH_SHORT).show();
    }

    //textview的clickable属性默认为false，所以不会处罚点击事件。
    @OnClick(R.id.tv)
    public void tvClicked() {
        Log.d(TAG, "tvClicked: tv click");
    }
}
