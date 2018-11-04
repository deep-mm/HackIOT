package dupd.com.smartbag.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dupd.com.smartbag.Entities.RFIDEntity;
import dupd.com.smartbag.R;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.MyViewHolder>{



    List<RFIDEntity> rfid = new ArrayList<RFIDEntity>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.bagfragment,parent,false);
        return new MyViewHolder(view);
    }
    BagAdapter(List<RFIDEntity>rfid)
    {
        this.rfid = rfid;
        System.out.println("rfid3"+rfid);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bookname.setText(rfid.get(position).getBookName().toString());
       holder.inbag.setText(rfid.get(position).getInBag().toString());
    }



    @Override
    public int getItemCount() {
        return rfid.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView bookname,inbag;
        public MyViewHolder(View v) {
            super(v);
            bookname = v.findViewById(R.id.bookname);
            inbag = v.findViewById(R.id.inbag);
        }
    }



}
