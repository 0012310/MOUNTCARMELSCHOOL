package gct.example.com.mountcarmelschool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.classes.AttendenceAprilForList;
import gct.example.com.mountcarmelschool.classes.AttendenceMayForList;
import gct.example.com.mountcarmelschool.classes.AttendenceSeptemberForList;

/**
 * Created by GCT on 12/5/2017.
 */

public class AttendenceMayAdapter extends RecyclerView.Adapter<AttendenceMayAdapter.ViewHolder> {


    private ArrayList<AttendenceMayForList> attendenceMayForLists = new ArrayList<>();
    Context context;

    public AttendenceMayAdapter(ArrayList<AttendenceMayForList> attendenceMayForLists, Context context) {
        this.attendenceMayForLists = attendenceMayForLists;
        this.context = context;
    }

    @Override
    public AttendenceMayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendenceMayAdapter.ViewHolder holder, int position) {
        AttendenceMayForList attendenceMAyForList = attendenceMayForLists.get(position);
        holder.textviewdate.setText(attendenceMAyForList.getAtt_date());

    }

    @Override
    public int getItemCount() {
        return attendenceMayForLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewdate;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewdate = (TextView) itemView.findViewById(R.id.textviewdate);
        }
    }
}