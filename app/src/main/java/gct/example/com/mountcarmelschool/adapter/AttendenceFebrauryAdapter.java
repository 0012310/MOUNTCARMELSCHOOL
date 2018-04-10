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
import gct.example.com.mountcarmelschool.classes.AttentdanceFebrauryForList;

/**
 * Created by GCT on 2/1/2018.
 */

public class AttendenceFebrauryAdapter extends RecyclerView.Adapter<AttendenceFebrauryAdapter.ViewHolder> {


    private ArrayList<AttentdanceFebrauryForList> attendenceFebrauryForLists = new ArrayList<>();
    Context context;

    public AttendenceFebrauryAdapter(ArrayList<AttentdanceFebrauryForList> attendenceFebrauryForLists, Context context) {
        this.attendenceFebrauryForLists = attendenceFebrauryForLists;
        this.context = context;
    }

    @Override
    public AttendenceFebrauryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new AttendenceFebrauryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendenceFebrauryAdapter.ViewHolder holder, int position) {
        AttentdanceFebrauryForList AttendanceFebForList = attendenceFebrauryForLists.get(position);
        holder.textviewdate.setText(AttendanceFebForList.getAtt_date());
        if (AttendanceFebForList.getPresent_status().equalsIgnoreCase("P")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2a843b"));

        } else if (AttendanceFebForList.getPresent_status().equalsIgnoreCase("H")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2196F3"));
        } else {
            holder.textviewdate.setTextColor(Color.parseColor("#EF5350"));

        }
    }

    @Override
    public int getItemCount() {
        return attendenceFebrauryForLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewdate;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewdate= (TextView) itemView.findViewById(R.id.textviewdate);
        }
    }
}



