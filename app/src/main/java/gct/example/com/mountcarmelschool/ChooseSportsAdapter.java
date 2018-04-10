package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ChooseSportsAdapter extends RecyclerView.Adapter<ChooseSportsAdapter.ViewHolder> {

    private List<Integer> selectedIds = new ArrayList<>();
    Context context;
    private List<ChooseSportsForList> list;


    public ChooseSportsAdapter(List<ChooseSportsForList> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public ChooseSportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_choose_sports_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getSp_id());
        int id = list.get(position).getSp_id();
        if (selectedIds.contains(id)) {
            holder.root_view.setForeground(new ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent)));
        } else {
            holder.root_view.setForeground(new ColorDrawable(ContextCompat.getColor(context, R.color.white)));
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public ChooseSportsForList getItem(int position) {
        return list.get(position);
    }

    public void setSelectedIds(List<Integer> selectedIds) {
        this.selectedIds = selectedIds;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /* @Bind(R.id.imageViewSportsImageChooseSports)
        ImageView imageViewSportsImageChooseSports;*/
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.root_view)
        FrameLayout root_view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
