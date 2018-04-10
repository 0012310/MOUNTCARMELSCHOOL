package gct.example.com.mountcarmelschool.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.Birthday_Wishes;
import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.Reply;
import gct.example.com.mountcarmelschool.classes.BirtdayWishesForList;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.classes.BirtdayWishesForList;
import gct.example.com.mountcarmelschool.classes.StaffBdForList;

/**
 * Created by GCT on 1/6/2018.
 */

public class WishesListAdapter  extends RecyclerView.Adapter<WishesListAdapter.ViewHolder> {

    private ArrayList<BirtdayWishesForList> birtdayWishesForListArrayList = new ArrayList<>();
    private Context context;

    public WishesListAdapter(ArrayList<BirtdayWishesForList> birtdayWishesForListArrayList, Context context) {
        this.birtdayWishesForListArrayList = birtdayWishesForListArrayList;
        this.context = context;
    }

    @Override
    public WishesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_wishinglist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WishesListAdapter.ViewHolder holder, int position) {
        final BirtdayWishesForList birtdayWishesForList = birtdayWishesForListArrayList.get(position);

        holder.textview_wisherName.setText(birtdayWishesForList.getTeacher_name());
        holder.textview_wishingmsg.setText(birtdayWishesForList.getMessage());

        holder.replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(birtdayWishesForList.getStatus().equals("Yes")) {


                    Intent intent = new Intent(context, Reply.class);




                    intent.putExtra("staff_id", birtdayWishesForList.getStaff_id());

                    Log.d("id_staff", "" + birtdayWishesForList.getStaff_id());
                    LocalSharedPreferences.getUserEmail(context);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
           }else {
                  Toast.makeText(context, "you have sent thnx msg", Toast.LENGTH_SHORT).show();
           }


                LocalSharedPreferences.getReplyMessage(context);
                Toast.makeText(context,  LocalSharedPreferences.getReplyMessage(context)+"", Toast.LENGTH_SHORT).show();


            }
        });







            }


    @Override
    public int getItemCount() {
        return birtdayWishesForListArrayList.size();
    }

    public ArrayList<BirtdayWishesForList> getwishes() {
        return birtdayWishesForListArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //region Binding Controls

        TextView textview_wishingmsg,textview_wisherName;
        Button replyButton;

        //endregion

        public ViewHolder(View itemView) {
            super(itemView);
            textview_wishingmsg= (TextView) itemView.findViewById(R.id.textview_wishingmsg);
            textview_wisherName= (TextView) itemView.findViewById(R.id.textview_wisherName);
            replyButton= (Button) itemView.findViewById(R.id.replyButton);


        }
    }
}
