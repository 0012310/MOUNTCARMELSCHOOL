package gct.example.com.mountcarmelschool.wise;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gct.example.com.mountcarmelschool.R;

/**
 * Created by GCT on 1/18/2018.
 */

public class IntellectAdapter extends RecyclerView.Adapter<IntellectAdapter.ViewHolder>{

    private List<WiseModel> listitems;
    private Activity activity;

    public IntellectAdapter(List<WiseModel> listitems, Activity activity) {
        this.listitems = listitems;
        this.activity = activity;
    }

    @Override
    public IntellectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.intelect_row_layout,parent,false);
        return new IntellectAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IntellectAdapter.ViewHolder holder, int position) {

        final WiseModel wiseModel = listitems.get(position);
        holder.name.setText(wiseModel.getTeacher_name());
        holder.remarks.setText(wiseModel.remarks);
        holder.dateofgiving.setText(wiseModel.getDate());



        // holder.remarks.setText(Html.fromHtml()wiseModel.getRemarks());
      //  holder.remarks.setText(Html.fromHtml("&ldquo; " + wiseModel.getRemarks() + " &rdquo;"));


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
