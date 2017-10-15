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
*作者：Archer
*功能描述： 使用volley
 * http://blog.csdn.net/guolin_blog/article/details/17482095
 * stringRequest 字符串请求
 * jsonRequest    json请求
 *
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //发送一个string请求
//        StringRequest();

        JsonRequest();
    }

    private void JsonRequest() {
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        //返回了一个json数据
        String Url = "https://api.thinkpage.cn/v3/weather/now.json?key=rot2enzrehaztkdk&location=guangzhou&language=zh-Hans&unit=c";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Tag", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 //打印错误
                Log.e("TAG", error.getMessage(), error);
            }
        });

        mQueue.add(jsonObjectRequest);
    }

    private void StringRequest() {
        //1.创建一个队列
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        //2.创建一个请求
        StringRequest stringRequest = new StringRequest("http://www.yanhui.site",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //打印返回值
                        Log.d("TAG", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //打印错误
                Log.e("TAG", error.getMessage(), error);
            }
        });
        //3.添加进队列
        mQueue.add(stringRequest);
    }
}
