package com.example.onlineTiffinorder.api.responce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineTiffinorder.R;

import java.util.List;

public class breakfastadapter  extends RecyclerView.Adapter<breakfastadapter.ViewHolder> {
    Context mcontext;
    private List<breakfast_get_set> data;

    public breakfastadapter(Context mcontext, List<breakfast_get_set> data) {
        this.mcontext = mcontext;
        this.data = data;
    }

    @Override
    public breakfastadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout_recycle,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        breakfast_get_set d=data.get(position);
        holder.name.setText(d.getName());
        holder.address.setText(d.getAddress());
        holder.mobile.setText(d.getMobno());
        holder.tid.setText("  Tiffin id:  "+d.getId()+"");
        String or="";
        if(d.getThepla().equals("1")){
            or=or+"thepla,";
        }
        if(d.getBatakapuva().equals("1")){
            or=or+"Bataka Puva,";
        }
        if(d.getAchar().equals("1")){
            or=or+"Achar,";
        }
        if(d.getTea().equals("1")){
            or=or+"Tea,";
        }
        holder.order.setText(or);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, mobile, address, tid, order;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.namecmp);
            mobile = (TextView) itemView.findViewById(R.id.mobilecmp);
            address = (TextView) itemView.findViewById(R.id.addresscmp);
            tid = (TextView) itemView.findViewById(R.id.orgcmpcid);
            order = (TextView) itemView.findViewById(R.id.order_view);


        }
    }
}
