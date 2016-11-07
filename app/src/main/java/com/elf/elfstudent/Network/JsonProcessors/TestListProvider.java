package com.elf.elfstudent.Network.JsonProcessors;

import android.util.Log;

import com.android.volley.Response;
import com.elf.elfstudent.model.AllTestModels;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandhu on 25/10/16.
 *
 */

public class TestListProvider implements Response.Listener<JSONArray> {
    private List<AllTestModels> mSocial = new ArrayList<>();
    private List<AllTestModels> mScience = new ArrayList<>();
    private List<AllTestModels> mMaths = new ArrayList<>();

    private TestProviderCallback mCallback = null;

    public TestListProvider(TestProviderCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onResponse(JSONArray response) {

        int count = response.length();
        String subId = null;


        JSONObject mobject = null;
        try {
            for (int i = 0; i < count; i++) {
                mobject = response.getJSONObject(i);


                subId = mobject.getString("SubjectId");

//                // TODO: 26/10/16 find subject iD
                if (subId.equals("1")){
                    //Science Subject
                    mScience.add(new AllTestModels(mobject.getString("TestId"),
                            mobject.getString("Description")
                            , mobject.getString("SubjectName"),
                            mobject.getString("SubjectId")));

                }
                 else if(subId.equals("2")){
                    mSocial.add(new AllTestModels(mobject.getString("TestId"),
                            mobject.getString("Description")
                            , mobject.getString("SubjectName"),
                            mobject.getString("SubjectId")));
                }
                else if(subId.equals("3")){

                    mMaths.add(new AllTestModels(mobject.getString("TestId"),
                            mobject.getString("Description")
                            , mobject.getString("SubjectName"),
                            mobject.getString("SubjectId")));              }



            }
            mCallback.setTestListData(mScience,mScience,mScience);

        } catch (Exception e) {
           mCallback.NoTestListData();
        }
    }

    public interface  TestProviderCallback{

       void setTestListData(List<AllTestModels> mScience,List<AllTestModels> mSocial,List<AllTestModels> maths);
        void NoTestListData();


    }
}
