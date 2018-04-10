package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.List;

import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;

/**
 * Created by GCT on 2/10/2018.
 */

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder>{
    private Context context;
    private List<MyData> list;
    private List<Integer> selectedIds = new ArrayList<>();

    public MyAdapter1(Context context,List<MyData> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.admNo.setText(list.get(position).getadm_no());


        String str =(list.get(position).getfirstname());
        String[] splitStr = str.split("\\s+");

        String name = splitStr[0];
        String s1 = name.substring(0, 1).toLowerCase();

       String nameCapitalized = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

        holder.title1.setText(nameCapitalized);


        int id = list.get(position).getId();

        if (selectedIds.contains(id)){
            //if item is selected then,set foreground color of FrameLayout.
            //holder.rootView1.setForeground(new ColorDrawable(ContextCompat.getColor(context, R.color.gray_transparent1)));

            String firstLetter = list.get(position).getfirstname().substring(0, 1);
            TextDrawable drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(60)  // width in px
                    .height(60) // height in px
                    .endConfig()
                    .buildRound(firstLetter, context.getResources().getColor(R.color.present_color));
            holder.imageViewShowText.setImageDrawable(drawable);
        }
        else {
            //else remove selected item color.
            //holder.rootView1.setForeground(new ColorDrawable(ContextCompat.getColor(context,android.R.color.transparent)));

            String firstLetter = list.get(position).getfirstname().substring(0, 1);
            TextDrawable drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(60)  // width in px
                    .height(60) // height in px
                    .endConfig()
                    .buildRound(firstLetter, Color.RED);
            holder.imageViewShowText.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public MyData getItem(int position){
        return list.get(position);
    }

    public void setSelectedIds(List<Integer> selectedIds) {
        this.selectedIds = selectedIds;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewShowText;
        TextView title1,admNo;
        FrameLayout rootView1;
        MyViewHolder(View itemView) {
            super(itemView);
            imageViewShowText = (ImageView) itemView.findViewById(R.id.imageViewShowText);
            title1 = (TextView) itemView.findViewById(R.id.titles1);
            admNo= (TextView) itemView.findViewById(R.id.admNo);
            rootView1 = (FrameLayout) itemView.findViewById(R.id.root_view1);
        }
    }
}

