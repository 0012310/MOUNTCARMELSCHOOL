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
import gct.example.com.mountcarmelschool.classes.AttendenceAprilForList;
import gct.example.com.mountcarmelschool.classes.AttendenceAuguestForList;
import gct.example.com.mountcarmelschool.classes.AttendenceOctoberlForList;
import gct.example.com.mountcarmelschool.classes.AttendenceSeptemberForList;

/**
 * Created by GCT on 12/5/2017.
 */

public class AttendenceSeptemberAdapter extends RecyclerView.Adapter<AttendenceSeptemberAdapter.ViewHolder> {

    private ArrayList<AttendenceSeptemberForList> attendenceSeptemberForLists = new ArrayList<>();
    Context context;

    public AttendenceSeptemberAdapter(ArrayList<AttendenceSeptemberForList> attendenceSeptemberForListArrayList, Context context) {
        this.attendenceSeptemberForLists = attendenceSeptemberForListArrayList;
        this.context = context;
    }

    @Override
    public AttendenceSeptemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendenceSeptemberAdapter.ViewHolder holder, int position) {
        AttendenceSeptemberForList attendenceSeptemberForList = attendenceSeptemberForLists.get(position);
        holder.textviewdate.setText(attendenceSeptemberForList.getAtt_date());
        if (attendenceSeptemberForList.getPresent_status().equalsIgnoreCase("P")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2a843b"));

        } else if (attendenceSeptemberForList.getPresent_status().equalsIgnoreCase("H")) {
            holder.textviewdate.setTextColor(Color.parseColor("#2196F3"));
        } else {
            holder.textviewdate.setTextColor(Color.parseColor("#EF5350"));

        }

    }

    @Override
    public int getItemCount() {
        return attendenceSeptemberForLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  textviewdate;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewdate= (TextView) itemView.findViewById(R.id. textviewdate);
        }
    }
}


