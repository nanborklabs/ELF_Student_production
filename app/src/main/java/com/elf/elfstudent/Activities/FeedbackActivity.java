package com.elf.elfstudent.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elf.elfstudent.DataStorage.DataStore;
import com.elf.elfstudent.Network.AppRequestQueue;
import com.elf.elfstudent.Network.ErrorHandler;
import com.elf.elfstudent.R;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by nandhu on 31/10/16.
 *
 */

public class FeedbackActivity extends AppCompatActivity implements ErrorHandler.ErrorHandlerCallbacks {


    private static final String TAG = "Feedack";
    private static final String FEED_URL = "";
    @BindView(R.id.feed_edit)
    EditText mFeedback;

    @BindView(R.id.feed_button)
    Button mSubmitButton;
    String text = null;

    JSONObject mRequestObject = null;
    ErrorHandler errorHandler  = null;
    AppRequestQueue mRequestQueue = null;

    JsonArrayRequest mRequest   = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);

        mRequestQueue = AppRequestQueue.getInstance(this);
        errorHandler = new ErrorHandler(this);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = mFeedback.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    submitFeedback(text);
                }
            }
        });


    }

    private void submitFeedback(String text) {
        mRequestObject = new JSONObject();
        try{
            mRequestObject.put("Type","Student");
            mRequestObject.put("Feedback",text);
        }
        catch (Exception e ){
            Log.d(TAG, "submitFeedback: ");
        }

        mRequest = new JsonArrayRequest(Request.Method.POST, FEED_URL, mRequestObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        },errorHandler);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void TimeoutError() {
        Toast.makeText(getApplicationContext(),"Time out Error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void NetworkError() {

        Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ServerError() {
        Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();

    }
}