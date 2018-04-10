package gct.example.com.mountcarmelschool;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by GCT on 11/17/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>  {

    private List<Bean> listiems,movieList;
    private Activity activity;
    private List<Bean> arraylist;



    public CalendarAdapter(List<Bean> listitems, Activity activity) {
        this.listiems = listitems;
        this.activity= activity;
        this.movieList=listitems;
        this.arraylist=listitems;



    }


    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(CalendarAdapter.ViewHolder holder, int position) {

        final Bean listItem = listiems.get(position);
        holder.name.setText( listItem.getAtt_date());


        if (listItem.getStatus().equalsIgnoreCase("A"))
        {
            holder.name.setTextColor(Color.parseColor("#EF5350"));
        }else if(listItem.getStatus().equalsIgnoreCase("H")){
            holder.name.setTextColor(Color.parseColor("#2196F3"));
        }else{
            holder.name.setTextColor(Color.parseColor("#2a843b"));
        }
        String string =listItem.getAtt_date() ;
        String pattern = "yyyy-MM-dd";
        String dateOnly = "dd";
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(string);
            Calendar calendar = Calendar.getInstance();
            //  holder.calendar.setDate(calendar);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date); // Wed Mar 09 03:02:10 BOT 2011

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return listiems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView ccn;
        TextView HName;
        TextView HDist;
        LinearLayout linearlayout;
        // CalendarView calendar;

        public ViewHolder(View itemView) {
            super(itemView);


         //   name = (TextView) itemView.findViewById(R.id.date);

            //  calendar = (CalendarView)itemView.findViewById(R.id.cal);

            //linearlayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
        }
    }
}
