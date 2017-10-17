package site.yanhui.volley03.xml;

import android.support.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by Archer on 2017/10/15.
 * <p>
 * 功能描述：
 */

public class XMLRequest extends Request {

    public XMLRequest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }

    public XMLRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(Object response) {

    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
