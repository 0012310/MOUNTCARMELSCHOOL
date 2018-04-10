package gct.example.com.mountcarmelschool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.classes.CustomForList;

/**
 * Created by AMIT on 9/23/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<CustomForList> customForLists = new ArrayList<>();
    private Context context;
    private int res;

    public CustomAdapter(ArrayList<CustomForList> customForLists, Context context, int res) {
        this.customForLists = customForLists;
        this.context = context;
        this.res = res;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(res, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        int num = position+1;

        CustomForList customForList = customForLists.get(position);
        holder.textViewNameCustomSibling.setText(customForList.getSname());
        holder.textViewAdmissionNoCustomSibling.setText(customForList.getAdm_no());
        holder.textViewBranchCustomSibling.setText(customForList.getSbranch());

        holder.textViewNumberCustomSibling.setText(""+num);
    }

    @Override
    public int getItemCount() {
        return customForLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNumberCustomSibling,
                textViewNameCustomSibling,
                textViewAdmissionNoCustomSibling,
                textViewBranchCustomSibling;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNumberCustomSibling = (TextView) itemView.findViewById(R.id.textViewNumberCustomSibling);
            textViewBranchCustomSibling = (TextView) itemView.findViewById(R.id.textViewBranchCustomSibling);
            textViewNameCustomSibling = (TextView) itemView.findViewById(R.id.textViewNameCustomSibling);
            textViewAdmissionNoCustomSibling = (TextView) itemView.findViewById(R.id.textViewAdmissionNoCustomSibling);
        }
    }
}
