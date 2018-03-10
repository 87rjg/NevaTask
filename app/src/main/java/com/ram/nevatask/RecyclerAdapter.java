package com.ram.nevatask;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RAMJEE on 09-03-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;

    private ArrayList<JsonData> dataArray;


    public RecyclerAdapter(ArrayList<JsonData> dataArray, Context context) {

        this.context = context;
        this.dataArray = dataArray;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_layout,parent,false);

        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //String n = ;
        if(dataArray.get(position).getName() == null)
        {
            holder.name.setText("un-known");
        }else{
            holder.name.setText(dataArray.get(position).getName());
        }
        if(dataArray.get(position).getSkills() == null){
            holder.skills.setText("un-known");
        }else {
            holder.skills.setText(dataArray.get(position).getSkills());
        }

        String url = dataArray.get(position).getImage();

        if(url == "" || url == null){

            Picasso.with(context).load(R.drawable.avatar).into(holder.imageView);     //Used picasso library to load data into image view
        }else {
            Picasso.with(context).load(url).placeholder(R.drawable.avatar).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView;
        TextView name;
        TextView skills;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView =(CircleImageView) itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            skills = itemView.findViewById(R.id.skills);


            /** Custom font settings */
            Typeface latoRegularFont = Typeface.createFromAsset(context.getAssets(),"fonts/Lato-Regular.ttf");
            name.setTypeface(latoRegularFont);

            Typeface latoLightFont = Typeface.createFromAsset(context.getAssets(),"fonts/Lato-Light.ttf");
            skills.setTypeface(latoLightFont);


        }
    }
}
