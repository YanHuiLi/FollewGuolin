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
*���ߣ�Archer
*����������
 * 1.ImageRequest���÷�
 * 2.ImageLoader���÷�
 * 3.NetworkImageView���÷�
 *
 * ���͵�ַ�� http://blog.csdn.net/guolin_blog/article/details/17482165
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
    ʹ��networkImageView
     */
    private void NetworkImageView() {

        //1.һ��Ҫ�ǵó�ʼ�����mQueue
        mQueue= Volley.newRequestQueue(MainActivity.this);
        //2.ʹ��imageLoader
        ImageLoader imageLoader =new ImageLoader(mQueue,new BitmapCache());
        //3.���ع����е�Ĭ��ͼƬ
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        //4.�����Ժ���ص�ͼƬ
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher_round);
        //5.����ͼƬ��ַ��ͼƬ������
        networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                imageLoader);
    }

    /*
    ʹ��imageLoader
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

        //����һ��10m��ͼƬ�Ļ��湦��
        ImageLoader imageLoader =new ImageLoader(mQueue,new BitmapCache());

        /*
         * ��һ������ָ��������ʾͼƬ��ImageView�ؼ���
         * �ڶ�������ָ������ͼƬ�Ĺ�������ʾ��ͼƬ��
         * ����������ָ������ͼƬʧ�ܵ��������ʾ��ͼƬ��
         */
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher_round, R.mipmap.ic_launcher);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
    }


    private void imageRequest() {
        //1.����һ������
        mQueue = Volley.newRequestQueue(MainActivity.this);

        //2.new һ��ImageRequest����
        /**
         * ���Կ�����ImageRequest�Ĺ��캯����������������
         * ��һ����������ͼƬ��URL��ַ�����ûʲô��Ҫ���͵ġ�
         * �ڶ���������ͼƬ����ɹ��Ļص����������ǰѷ��ص�Bitmap�������õ�ImageView�С�
         * �������ĸ������ֱ�����ָ������ͼƬ���Ŀ�Ⱥ͸߶ȣ����ָ��������ͼƬ�Ŀ�Ȼ�߶ȴ�����������ֵ������ͼƬ����ѹ����ָ����0�Ļ��ͱ�ʾ����ͼƬ�ж�󣬶��������ѹ����
         * �������������ָ��ͼƬ����ɫ���ԣ�Bitmap.Config�µļ�������������������ʹ�ã�����ARGB_8888����չʾ��õ���ɫ���ԣ�ÿ��ͼƬ����ռ��4���ֽڵĴ�С����RGB_565���ʾÿ��ͼƬ����ռ��2���ֽڴ�С��
         * ������������ͼƬ����ʧ�ܵĻص����������ǵ�����ʧ��ʱ��ImageView����ʾһ��Ĭ��ͼƬ��

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
