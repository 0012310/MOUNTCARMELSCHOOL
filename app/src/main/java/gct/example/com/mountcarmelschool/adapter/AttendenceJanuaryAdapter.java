package gct.example.com.mountcarmelschool.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.R;

import gct.example.com.mountcarmelschool.classes.AttendanceJanuaryForList;

/**
 * Created by GCT on 1/3/2018.
 */

public class AttendenceJanuaryAdapter extends RecyclerView.Adapter<AttendenceJanuaryAdapter.ViewHolder> {


    private ArrayList<AttendanceJanuaryForList> attendenceJanuaryForLists = new ArrayList<>();
    Context context;

    public AttendenceJanuaryAdapter(ArrayList<AttendanceJanuaryForList> attendenceJanuaryForLists, Context context) {
        this.attendenceJanuaryForLists = attendenceJanuaryForLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new AttendenceJanuaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AttendanceJanuaryForList AttendanceJanForList = attendenceJanuaryForLists.get(position);
        holder.textviewdate.setText(AttendanceJanForList.getAtt_date());
        if (AttendanceJanForList.getPresent_status().equalsIgnoreCase("P")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2a843b"));

        } else if (AttendanceJanForList.getPresent_status().equalsIgnoreCase("H")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2196F3"));
        } else {
            holder.textviewdate.setTextColor(Color.parseColor("#EF5350"));

        }
    }

    @Override
    public int getItemCount() {
        return attendenceJanuaryForLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewdate;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewdate= (TextView) itemView.findViewById(R.id.textviewdate);
        }
    }
}



