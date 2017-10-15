package site.yanhui.volley02;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import site.yanhui.volley02.ImageCache.BitmapCache;

/**
*create at 2017/10/15 by 16:31
*作者：Archer
*功能描述：
 * 1.ImageRequest的用法
 * 2.ImageLoader的用法
 * 3.NetworkImageView的用法
 *
 * 博客地址： http://blog.csdn.net/guolin_blog/article/details/17482165
 *
*/

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    private RequestQueue mQueue;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//             imageRequest();
//                imageLoader();

        NetworkImageView();
            }
        });


    }

    /*
    使用networkImageView
     */
    private void NetworkImageView() {

        //1.一定要记得初始化这个mQueue
        mQueue= Volley.newRequestQueue(MainActivity.this);
        //2.使用imageLoader
        ImageLoader imageLoader =new ImageLoader(mQueue,new BitmapCache());
        //3.加载过程中的默认图片
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        //4.出错以后加载的图片
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher_round);
        //5.传入图片地址和图片加载器
        networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                imageLoader);
    }

    /*
    使用imageLoader
     */
    private void imageLoader() {
        mQueue= Volley.newRequestQueue(MainActivity.this);
//        ImageLoader imageLoader =new ImageLoader(mQueue, new ImageLoader.ImageCache() {
//            @Override
//            public Bitmap getBitmap(String url) {
//                return null;
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//
//            }
//        });

        //加入一个10m的图片的缓存功能
        ImageLoader imageLoader =new ImageLoader(mQueue,new BitmapCache());

        /*
         * 第一个参数指定用于显示图片的ImageView控件，
         * 第二个参数指定加载图片的过程中显示的图片，
         * 第三个参数指定加载图片失败的情况下显示的图片。
         */
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher_round, R.mipmap.ic_launcher);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
    }


    private void imageRequest() {
        //1.创建一个队列
        mQueue = Volley.newRequestQueue(MainActivity.this);

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
        networkImageView = (NetworkImageView) findViewById(R.id.NetWorkImageView);
//        networkButton = (Button) findViewById(R.id.btn_network_view);
    }
}
