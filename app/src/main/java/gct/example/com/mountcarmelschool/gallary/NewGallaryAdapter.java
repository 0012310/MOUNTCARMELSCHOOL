package gct.example.com.mountcarmelschool.gallary;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gct.example.com.mountcarmelschool.R;

/**
 * Created by GCT on 1/31/2018.
 */

public class NewGallaryAdapter extends BaseAdapter {

    Activity activity;
    List<NewGallaryModel> listitems;
    String monthname ;

    public NewGallaryAdapter(Activity activity, List<NewGallaryModel> listitems) {
        this.activity = activity;
        this.listitems = listitems;
    }

    @Override
    public int getCount() {
        return listitems.size();
    }

    @Override
    public Object getItem(int i) {
        return listitems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).
                    inflate(R.layout.new_gallary_row, viewGroup, false);
        }

        // get current item to be displayed
       // Item currentItem = (Item) getItem(position);

        final NewGallaryModel newGallaryModel =listitems.get(position);
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.textnew);
        ImageView textViewItemDescription = (ImageView) convertView.findViewById(R.id.image);

        textViewItemName.setText(newGallaryModel.getMonth());
        if(newGallaryModel.getImageURl()!= null) {
            Picasso.with(activity).load(newGallaryModel.getImageURl()).resize(120, 120).into(textViewItemDescription);
        }

        LinearLayout llayout= (LinearLayout)convertView.findViewById(R.id.llayout);
        llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent (activity,EventNewGallary.class);
                    intent.putExtra("api","http://infoes.in/sunil/mcsd/user/eventByMonth?month="+newGallaryModel.getMonth_no());

                intent.putExtra("Monthname",newGallaryModel.getMonth());

                    activity.startActivity(intent);
                 Log.d("abc",newGallaryModel.getMonth());


            }
        });


        return convertView;
    }
}
