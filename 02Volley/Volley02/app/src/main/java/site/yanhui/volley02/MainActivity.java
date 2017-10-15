package site.yanhui.volley02;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             imageRequest();

            }
        });


    }

    private void imageRequest() {
        //1.创建一个队列
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        //2.new 一个ImageRequest对象
        /**
         * 可以看到，ImageRequest的构造函数接收六个参数，
         * 第一个参数就是图片的URL地址，这个没什么需要解释的。
         * 第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中。
         * 第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
         * 第五个参数用于指定图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小。
         * 第六个参数是图片请求失败的回调，这里我们当请求失败时在ImageView中显示一张默认图片。

         */
        ImageRequest imageRequest = new ImageRequest(
                "http://ogtmd8elu.bkt.clouddn.com/201710092031_811.png",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 700, 800, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });

        mQueue.add(imageRequest);
    }

    private void initUI() {
        imageView = (ImageView) findViewById(R.id.image123);
        button = (Button) findViewById(R.id.sendRequest);
    }
}
