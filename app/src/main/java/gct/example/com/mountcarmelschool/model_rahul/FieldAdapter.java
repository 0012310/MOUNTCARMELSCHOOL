package gct.example.com.mountcarmelschool.model_rahul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gct.example.com.mountcarmelschool.R;

import static me.zhanghai.android.materialprogressbar.R.id.image;

/**
 * Created by GCT on 12/21/2017.
 */

public class FieldAdapter  extends ArrayAdapter<Field> {

    String fieldURl;
    List list=new ArrayList();
    FieldHolder fHolder;

    public FieldAdapter(Context context, int resource,String fieldUrl) {
        super(context, resource);
        this.fieldURl = fieldUrl;
    }

    public void add(Field object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Field getItem(int position) {
        return (Field) list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FieldHolder fHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.event_pics, parent, false);
            fHolder = new FieldHolder();

            fHolder.name = (TextView) convertView.findViewById(R.id.grid_text);
            fHolder.image = (ImageView) convertView.findViewById(R.id.grid_image);


            convertView.setTag(fHolder);
        } else {
            fHolder = (FieldHolder) convertView.getTag();
        }

        Field f1 = (Field) this.getItem(position);

        final String cid=f1.getImage();
        final String PName=f1.getDescription();
        final String polNo=f1.getImgid();
        Picasso.with(getContext()).load(f1.getImage()).resize(200, 150).into(fHolder.image);

        convertView.setTag(fHolder);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL ACTIVITY
                 openDetailActivity(cid,PName,polNo);
            }
        });
        return convertView;
    }
    private void openDetailActivity(String cid, String PName, String polNo) {

        Intent intent =new Intent(getContext(),ImageShow.class);
        intent.putExtra("imageURL",cid);
        intent.putExtra("des",PName);
        intent.putExtra("FieldUrl",fieldURl);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }
    static class FieldHolder{
        TextView name;
        ImageView image;
    }
 }
