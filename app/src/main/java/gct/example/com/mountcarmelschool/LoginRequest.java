package gct.example.com.mountcarmelschool;

import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GCT on 8/30/2017.
 */

public class LoginRequest extends StringRequest {
      private static final String LOGIN_REQUEST_URL ="http://infoes.in/api/api/login";
      private Map<String,String> parms;

    public LoginRequest(String username, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST,LOGIN_REQUEST_URL, listener,null);
        parms = new HashMap<>();
        parms.put(username,username);
        parms.put(password,password);


    }
}
