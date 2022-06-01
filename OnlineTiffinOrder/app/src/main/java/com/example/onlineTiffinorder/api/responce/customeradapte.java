package com.example.onlineTiffinorder.api.responce;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineTiffinorder.R;

import java.util.List;

public class customeradapte extends RecyclerView.Adapter<customeradapte.ViewHolder>  {
    Context mcontext;
    private List<User> data;

    public customeradapte(Context mcontext, List<User> data) {
        this.mcontext = mcontext;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(mcontext).inflate(R.layout.layout_employee,null);

        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User d=data.get(position);
        holder.name.setText(d.getFname()+" "+d.getLname());
        holder.mobile.setText(d.getMobno());
        holder.id.setText(d.getId()+"");
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+91"+d.mobno;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                mcontext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView name,mobile,id;
        ImageButton call;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name_cmp_emp_cust);
            mobile=(TextView) itemView.findViewById(R.id.mobile_cmp_emp_cust);
            id=(TextView) itemView.findViewById(R.id.emp_cmp_list_id_cust);
            call=(ImageButton) itemView.findViewById(R.id.call_btn);


        }
    }
}
