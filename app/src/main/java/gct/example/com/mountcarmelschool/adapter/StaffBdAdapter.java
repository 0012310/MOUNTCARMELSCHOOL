package gct.example.com.mountcarmelschool.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.Birthday_Wishes;
import gct.example.com.mountcarmelschool.Message_Wished;
import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.StaffBd;
import gct.example.com.mountcarmelschool.Swise;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.classes.SAttandanceForList;
import gct.example.com.mountcarmelschool.classes.StaffBdForList;

import static gct.example.com.mountcarmelschool.Main2Activity.img;

import static gct.example.com.mountcarmelschool.R.id.imageViewProfileStaffBday;
import static gct.example.com.mountcarmelschool.classes.LocalSharedPreferences.getWishMessage;


public class StaffBdAdapter extends RecyclerView.Adapter<StaffBdAdapter.ViewHolder> {

    private ArrayList<StaffBdForList> staffBdForListArrayList = new ArrayList<>();
    private Context context;


    public StaffBdAdapter(ArrayList<StaffBdForList> staffBdForListArrayList, Context context) {
        this.staffBdForListArrayList = staffBdForListArrayList;
        this.context = context;
    }

    @Override
    public StaffBdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custombirthday,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StaffBdAdapter.ViewHolder holder, int position) {
        final StaffBdForList staffBdForList = staffBdForListArrayList.get(position);
        Log.d("reply_",""+staffBdForList.getReply());
        //Log.d("reply_",""+new StaffBdForList().getReply());
        holder.textfirstnameofstaff.setText(staffBdForList.getStaff_fname());
        holder.textlasttnameofstaff.setText(staffBdForList.getStaff_lname());
     //   holder.textdateofbirth.setText(staffBdForList.getDob());

     //  holder.imageViewProfileStaffBday
      //  Picasso.with(context).load(staffBdForList.getImg()).resize(75, 75).into(holder.imageViewProfileStaffBday);


        Glide.with(context).load(staffBdForList.getImg())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.prof)
                .dontAnimate().into(holder.imageViewProfileStaffBday);





        holder.imgcake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(staffBdForList.getStatus().equals("Yes")){

                    Intent intent = new Intent(context, Birthday_Wishes.class);

                    intent.putExtra("dob",staffBdForList.getDob());

                    intent.putExtra("staff_id",staffBdForList.getStaff_id());

                    intent.putExtra("mess",staffBdForList.getMsg());


                    LocalSharedPreferences.getUserEmail(context);


                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                   else
                    {
                     Toast.makeText(context, "You have send Wishes All Ready   ", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(context, Message_Wished.class);

                        intent.putExtra("dob",staffBdForList.getDob());

                        intent.putExtra("message",staffBdForList.getMsg());

                        intent.putExtra("reply",staffBdForList.getReply());



                        Log.d("message",staffBdForList.getMsg());






                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                    }
            }
        });
    }

    @Override
    public int getItemCount() {
        return staffBdForListArrayList.size();
    }
    public ArrayList<StaffBdForList> getwishes() {
        return staffBdForListArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //region Binding Controls
        ImageView imgcake,imageViewProfileStaffBday;
         TextView textfirstnameofstaff;
         TextView textlasttnameofstaff;
         TextView textdateofbirth;

        //endregion

        public ViewHolder(View itemView) {
            super(itemView);
             textfirstnameofstaff = (TextView) itemView.findViewById(R.id.textfirstnameofstaff);
             textlasttnameofstaff = (TextView) itemView.findViewById(R.id.textlasttnameofstaff);
             textdateofbirth = (TextView) itemView.findViewById(R.id.textdateofbirth);
             imgcake= (ImageView) itemView.findViewById(R.id.imgcake);
             imageViewProfileStaffBday= (ImageView) itemView.findViewById(R.id.imageViewProfileStaffBday);
        }
    }
}
