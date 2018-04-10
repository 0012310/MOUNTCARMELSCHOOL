package gct.example.com.mountcarmelschool.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.classes.AttendenceAprilForList;
import gct.example.com.mountcarmelschool.classes.AttendenceSeptemberForList;

/**
 * Created by GCT on 12/5/2017.
 */

public class AttendenceAprilAdapter extends RecyclerView.Adapter<AttendenceAprilAdapter.ViewHolder> {


    private ArrayList<AttendenceAprilForList> attendenceAprilForLists = new ArrayList<>();
    Context context;

    public AttendenceAprilAdapter(ArrayList<AttendenceAprilForList> attendenceAprilForListArrayList, Context context) {
        this.attendenceAprilForLists = attendenceAprilForListArrayList;
        this.context = context;
    }

    @Override
    public AttendenceAprilAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendenceAprilAdapter.ViewHolder holder, int position) {
        AttendenceAprilForList attendenceAprilForList = attendenceAprilForLists.get(position);
        holder.textviewdate.setText(attendenceAprilForList.getAtt_date());

        if (attendenceAprilForList.getPresent_status().equalsIgnoreCase("P")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2a843b"));

        } else if (attendenceAprilForList.getPresent_status().equalsIgnoreCase("H")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2196F3"));
        } else {
            holder.textviewdate.setTextColor(Color.parseColor("#EF5350"));

        }

    }

    @Override
    public int getItemCount() {
        return attendenceAprilForLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewdate;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewdate = (TextView) itemView.findViewById(R.id.textviewdate);
        }
    }
}
