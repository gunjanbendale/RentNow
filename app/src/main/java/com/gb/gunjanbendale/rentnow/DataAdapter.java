package com.gb.gunjanbendale.rentnow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<MachineType> machineTypes;
    private Context context;

    public DataAdapter(Context context,ArrayList<MachineType> machineTypes) {
        this.context = context;
        this.machineTypes=machineTypes;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.machine_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.machinename.setText(machineTypes.get(i).getName());
        Picasso.with(context).load(machineTypes.get(i).getImageURL()).resize(120, 60).into(viewHolder.img_machine);
    }

    @Override
    public int getItemCount() {
        return machineTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView machinename;
        ImageView img_machine;
        public ViewHolder(View view) {
            super(view);

            machinename = (TextView)view.findViewById(R.id.machinetxt);
            img_machine = (ImageView)view.findViewById(R.id.machineimg);
        }
    }
}