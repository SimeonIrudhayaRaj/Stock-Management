package com.example.stock;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_st_display extends RecyclerView.Adapter<Adapter_st_display.ViewHolder> {
    private Context context;
    private   ArrayList<data_model> details;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_display, viewGroup, false);
        return new ViewHolder(view);
    }
    Adapter_st_display(Context context, ArrayList<data_model> de_tails)
    {
        this.details=de_tails;
        this.context=context;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.st.setText(details.get(i).getStock());
        viewHolder.quant.setText(details.get(i).getQuantity()+"");



    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView st,quant;
        private LinearLayout root;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            st = itemView.findViewById(R.id.st);
            quant = itemView.findViewById(R.id.quant);
            root = itemView.findViewById(R.id.parent);

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(details.get(0).getCol().equals("user"))
                    {
                        Intent i = new Intent(itemView.getContext(),Sell.class);
                        i.putExtra("name",st.getText().toString());
                        i.putExtra("quant",Integer.parseInt(quant.getText().toString()));
                        itemView.getContext().startActivity(i);
                    }
                    else
                    {
                        Intent i = new Intent(itemView.getContext(),Buy.class);
                        i.putExtra("name",st.getText().toString());
                        i.putExtra("quant",(quant.getText().toString()));
                        itemView.getContext().startActivity(i);
                    }
                }
            });
        }
    }



}
