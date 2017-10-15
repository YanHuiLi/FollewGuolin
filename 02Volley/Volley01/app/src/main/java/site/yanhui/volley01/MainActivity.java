package site.yanhui.volley01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
/**
*create at 2017/10/15 by 15:10
*���ߣ�Archer
*���������� ʹ��volley
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * stringRequest �ַ�������
 * jsonRequest    json����
 *
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //����һ��string����
//        StringRequest();

        JsonRequest();
    }

    private void JsonRequest() {
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        //������һ��json����
        String Url = "https://api.thinkpage.cn/v3/weather/now.json?key=rot2enzrehaztkdk&location=guangzhou&language=zh-Hans&unit=c";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Tag", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 //��ӡ����
                Log.e("TAG", error.getMessage(), error);
            }
        });

        mQueue.add(jsonObjectRequest);
    }

    private void StringRequest() {
        //1.����һ������
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        //2.����һ������
        StringRequest stringRequest = new StringRequest("http://www.yanhui.site",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //��ӡ����ֵ
                        Log.d("TAG", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //��ӡ����
                Log.e("TAG", error.getMessage(), error);
            }
        });
        //3.��ӽ�����
        mQueue.add(stringRequest);
    }
}
