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
        //1.����һ������
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

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
    }
}
