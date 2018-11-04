package dupd.com.smartbag.Activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.Entities.TimeTableEntity;
import dupd.com.smartbag.R;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.MyViewHolder> {

    ArrayList<RFIDEntity> timeTableEntities = new ArrayList<RFIDEntity>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timefragment,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bookname.setText(timeTableEntities.get(position).getBookName());
    }

    @Override
    public int getItemCount() {
        return timeTableEntities.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView bookname;
        public MyViewHolder(View v) {
            super(v);
            bookname = v.findViewById(R.id.book_name);

        }
    }
}
