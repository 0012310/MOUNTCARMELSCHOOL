package gct.example.com.mountcarmelschool.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.classes.SAttandanceForList;
import gct.example.com.mountcarmelschool.classes.StaffBdForList;

import static gct.example.com.mountcarmelschool.R.id.parent;

/**
 * Created by GCT on 12/30/2017.
 */

public class SAttendanceAdapter extends RecyclerView.Adapter<SAttendanceAdapter.ViewHolder> {

    private ArrayList<String> selectedStrings = new ArrayList<>();
    boolean flag = false;

    private ArrayList<SAttandanceForList> sAttandanceForListArrayList = new ArrayList<>();
    private Context context;

    public SAttendanceAdapter(ArrayList<SAttandanceForList> sAttandanceForListArrayList, Context context) {
        this.sAttandanceForListArrayList = sAttandanceForListArrayList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_sattendance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SAttendanceAdapter.ViewHolder holder, int position) {
        final SAttandanceForList sAttandanceForList = sAttandanceForListArrayList.get(position);
        //holder.checkboxchild.setVisibility(View.INVISIBLE);
        holder.checkboxchild.setChecked(true);
        holder.textviewStudentname.setText(sAttandanceForList.getFirstname());
        holder.textviewStudentRollnumber.setText(sAttandanceForList.getAdm_no());
        holder.checkboxchild.setChecked(sAttandanceForListArrayList.get(position).isSelected());
        holder.checkboxchild.setTag(sAttandanceForListArrayList.get(position));
        holder.itemView.setTag(sAttandanceForListArrayList.get(position));
        holder.linearlayoutcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Toast.makeText(context, "Attendance Updated", Toast.LENGTH_SHORT).show();
            }
        });
        holder.checkboxchild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                SAttandanceForList contact = (SAttandanceForList) cb.getTag();
                contact.setSelected(cb.isChecked());
                sAttandanceForListArrayList.get(holder.getAdapterPosition()).setSelected(cb.isChecked());

                //Toast.makeText(v.getContext(), "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(), Toast.LENGTH_LONG).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    //holder.checkboxchild.setChecked(false);
                    holder.linearlayoutcard.setBackgroundColor(Color.RED);
                    //CheckBox cb = (CheckBox) view;
                    SAttandanceForList contact = (SAttandanceForList) holder.itemView.getTag();
                    contact.setSelected(false);
                    sAttandanceForListArrayList.get(holder.getAdapterPosition()).setSelected(false);
                    flag = true;
                }else if (flag) {
                    //holder.checkboxchild.setChecked(true);
                    holder.linearlayoutcard.setBackgroundColor(Color.TRANSPARENT);
                    SAttandanceForList contact = (SAttandanceForList) holder.itemView.getTag();
                    contact.setSelected(true);
                    sAttandanceForListArrayList.get(holder.getAdapterPosition()).setSelected(true);
                    flag = false;
                }
                //Toast.makeText(context, ""+sAttandanceForList.getFirstname(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sAttandanceForListArrayList.size();
    }

    // method to access in activity after updating selection
    public ArrayList<SAttandanceForList> getStudentist() {
        return sAttandanceForListArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView textviewStudentname;
        TextView textviewStudentRollnumber;
        CheckBox checkboxchild;
        LinearLayout linearlayoutcard;


        public ViewHolder(View itemView) {
            super(itemView);
            checkboxchild = (CheckBox) itemView.findViewById(R.id.checkboxchild);
            textviewStudentname = (TextView) itemView.findViewById(R.id.textviewStudentname);
            textviewStudentRollnumber = (TextView) itemView.findViewById(R.id.textviewStudentRollnumber);
            linearlayoutcard = (LinearLayout) itemView.findViewById(R.id.linearlayoutcard);

        }
    }
}
