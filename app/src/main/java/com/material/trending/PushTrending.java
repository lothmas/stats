package com.material.trending;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.material.components.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PushTrending {


   void pushTrending(){
       //URL of the request we are sending

       String url = "http://localhost:8090/trending";

/*
 JsonObjectRequest takes in five paramaters
 Request Type - This specifies the type of the request eg: GET,POST
 URL          - This String param specifies the Request URL
 JSONObject   - This parameter takes in the POST parameters.Sending this parameters
                makes this a POST request
 Listener     -This parameter takes in a implementation of Response.Listener()
                interface which is invoked if the request is successful
 Listener     -This parameter takes in a implementation of Error.Listener()
                interface which is invoked if any error is encountered while processing
                the request

 */

       JSONObject postparams=new JSONObject();
       try {
           postparams.put("memberID","london");

       } catch (JSONException e) {
           e.printStackTrace();
       }


       JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
               url, postparams,
               new Response.Listener() {
                   @Override
                   public void onResponse(Object response) {


                   }

               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                       //Failure Callback

                   }
               });



    }
}
