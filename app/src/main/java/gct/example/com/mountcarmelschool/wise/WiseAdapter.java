package gct.example.com.mountcarmelschool.wise;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import gct.example.com.mountcarmelschool.R;

import static gct.example.com.mountcarmelschool.R.id.dateofgiving;

/**
 * Created by GCT on 12/27/2017.
 */

public class WiseAdapter extends RecyclerView.Adapter<WiseAdapter.ViewHolder>{

    private List<WiseModel> listitems;
    private Activity activity;

    public WiseAdapter(List<WiseModel> listitems, Activity activity) {
        this.listitems = listitems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wise_row_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final WiseModel wiseModel = listitems.get(position);
        holder.name.setText(wiseModel.getTeacher_name());
        holder.remarks.setText(wiseModel.getRemarks());

        holder.dateofgiving.setText(wiseModel.getDate());


// holder.remarks.setText(Html.fromHtml("&ldquo; " + wiseModel.getRemarks() + " &rdquo;"));  >>?????>>>
// this is for getting response with "" commas
       // holder.remarks.setText(Html.fromHtml()wiseModel.getRemarks());


    }


    @Override
    public int getItemCount() {
        return listitems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,remarks,dateofgiving;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.t_name);
            remarks = (TextView) itemView.findViewById(R.id.remarks);
            dateofgiving= (TextView) itemView.findViewById(R.id.dateofgiving);


        }
    }
}
