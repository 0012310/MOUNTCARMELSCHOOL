package gct.example.com.mountcarmelschool.model_rahul;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gct.example.com.mountcarmelschool.R;

/**
 * Created by GCT on 12/17/2017.
 */

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder>  {

    private  List<PendingModel> listitems;
    private Activity activity;
    public PendingAdapter(List<PendingModel> listitems, FragmentActivity activity) {
        this.listitems=listitems;
        this.activity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_gallery,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final PendingModel listItem = listitems.get(position);
        holder.name.setText(listItem.getName());
        if(listItem.getEvent_image()!= null) {
            Picasso.with(activity).load(listItem.getEvent_image()).resize(300, 200).into(holder.image);
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetail(listItem.getId());
            }
        });

    }

    private void openDetail(String id) {
        Intent intent =new Intent(activity,Gridshow.class);
        intent.putExtra("imageURL",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);


        }
    }





}


