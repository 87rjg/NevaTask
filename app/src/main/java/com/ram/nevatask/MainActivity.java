package com.ram.nevatask;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;

    private ArrayList<JsonData> dataArray;
    private ArrayList<JsonData> dataArray1 = new ArrayList<>();
    private Set<String> setData = new HashSet<String>();

    private InterfaceApi mInterfaceApi;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initMembers();

        loadData();


    }

    // Method to initialize data members //
    private void initMembers() {
        dataArray = new ArrayList<>();

        mProgress = new ProgressDialog(this);

        mRecyclerView = findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    //Method to load Api data //
    private void loadData() {

        Retrofit retrofit = ApiServices.getApi();

        mInterfaceApi = retrofit.create(InterfaceApi.class);

        mProgress.setMessage("Loading Data..");
        mProgress.show();

        /** RxJava Method to fetch API data */
        Observable<JsonResponse> observable = mInterfaceApi.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<JsonResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonResponse jsonResponse) {


                dataArray = jsonResponse.getData();

                /** filtering duplicate json array objects **/
                for(int i=0; i < dataArray.size(); i++){

                    String id = dataArray.get(i).getId();

                    if(!setData.contains(id)){
                        setData.add(id);

                        dataArray1.add(dataArray.get(i));
                    }
                }
                mAdapter = new RecyclerAdapter(dataArray1,MainActivity.this);   //calling custom adapter

                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mProgress.dismiss();
            }

            @Override
            public void onError(Throwable e) {

                Toast.makeText(MainActivity.this, "Something went wrong while retrieving data :(", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                mProgress.dismiss();
            }

            @Override
            public void onComplete() {

                Toast.makeText(MainActivity.this, "Data Successfully retrieved :)", Toast.LENGTH_SHORT).show();
                mProgress.dismiss();
            }
        });
    }


}
