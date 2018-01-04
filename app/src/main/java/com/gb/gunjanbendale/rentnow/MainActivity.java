package com.gb.gunjanbendale.rentnow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HorizontalAdapter adapter;
    private List<MachineType> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);

        data = filldata();

        adapter= new HorizontalAdapter(data,getApplication());

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }



    public List<MachineType> filldata(){
        List<MachineType> data = new ArrayList<>();
        data.add(new MachineType("Aerial Lift",R.drawable.aerial));
        data.add(new MachineType("Earth Moving Equipments",R.drawable.earthmov));
        data.add(new MachineType("Forklifts and Material Handling",R.drawable.forklift));
        data.add(new MachineType("Concrete and Masonry",R.drawable.concrete));
        data.add(new MachineType("Compaction ",R.drawable.roadroller));
        data.add(new MachineType("Power and HVAC",R.drawable.power));
        data.add(new MachineType("Small Power Tools",R.drawable.smallpow));
        data.add(new MachineType("Safety and Shoring",R.drawable.safety));
        data.add(new MachineType("Trailers and Containers",R.drawable.trailer));
        return data;
    }
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


    List<MachineType> horizontalList = Collections.emptyList();
    Context context;


    public HorizontalAdapter(List<MachineType> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtview;
        public MyViewHolder(View view) {
            super(view);
            imageView=(ImageView) view.findViewById(R.id.machineimg);
            txtview=(TextView) view.findViewById(R.id.machinetxt);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.machine_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.imageView.setImageResource(horizontalList.get(position).getThumbnail());
        holder.txtview.setText(horizontalList.get(position).getName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String list = horizontalList.get(position).getName().toString();
                Intent intent=new Intent(getApplicationContext(),SubMachineActivity.class);
                intent.putExtra("id",list.substring(0,list.indexOf(" ")));
                finish();
                startActivity(intent);

            }

        });

    }


    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}
}