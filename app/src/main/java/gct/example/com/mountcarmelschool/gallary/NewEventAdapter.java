package gct.example.com.mountcarmelschool.gallary;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.model_rahul.Gridshow;
import gct.example.com.mountcarmelschool.model_rahul.PendingModel;

/**
 * Created by GCT on 2/1/2018.
 */

public class NewEventAdapter extends BaseAdapter{

    Activity activity;
    ArrayList<PendingModel> list;

    public NewEventAdapter( ArrayList<PendingModel> list,Activity activity) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.new_event_row, viewGroup, false);
        }

        // get current item to be displayed
        // Item currentItem = (Item) getItem(position);

        final PendingModel newGallaryModel =list.get(position);
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.textnewevent);
        ImageView textViewItemDescription = (ImageView) convertView.findViewById(R.id.imageevent);

        textViewItemName.setText(newGallaryModel.getName());
        if(newGallaryModel.getEvent_image()!= null) {
            Picasso.with(activity).load(newGallaryModel.getEvent_image()).resize(200, 100).into(textViewItemDescription);
        }

        LinearLayout llayout= (LinearLayout)convertView.findViewById(R.id.leventlayout);
        llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (activity,Gridshow.class);
                intent.putExtra("imageURL","http://infoes.in/sunil/mcsd/user/gallery?eventid="+newGallaryModel.getId());
                activity.startActivity(intent);

            }
        });
        return convertView;
    }
}
