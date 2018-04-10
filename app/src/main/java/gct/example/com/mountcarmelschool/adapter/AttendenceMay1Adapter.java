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
import gct.example.com.mountcarmelschool.classes.AttendenceMayForList;

/**
 * Created by GCT on 12/7/2017.
 */

public class AttendenceMay1Adapter extends RecyclerView.Adapter<AttendenceMay1Adapter.ViewHolder> {

    private ArrayList<AttendenceMayForList> attendenceMayForListArrayList = new ArrayList<>();
    Context context;

    public AttendenceMay1Adapter(ArrayList<AttendenceMayForList> attendenceMayForListArrayList, Context context) {
        this.attendenceMayForListArrayList = attendenceMayForListArrayList;
        this.context = context;
    }

    @Override
    public AttendenceMay1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendenceMay1Adapter.ViewHolder holder, int position) {
        AttendenceMayForList attendenceMayForList = attendenceMayForListArrayList.get(position);
        holder.textviewdate.setText(attendenceMayForList.getAtt_date());
        if (attendenceMayForList.getPresent_status().equalsIgnoreCase("P")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2a843b"));

        } else if (attendenceMayForList.getPresent_status().equalsIgnoreCase("H")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2196F3"));
        } else {
            holder.textviewdate.setTextColor(Color.parseColor("#EF5350"));

        }
    }

    @Override
    public int getItemCount() {
        return attendenceMayForListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewdate;
        public ViewHolder(View itemView) {
            super(itemView);
            textviewdate = (TextView) itemView.findViewById(R.id.textviewdate);
        }
    }
}