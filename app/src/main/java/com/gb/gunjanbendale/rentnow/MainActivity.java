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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataAdapter adapter;
    private ArrayList<MachineType> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);

        data = filldata();

        adapter= new DataAdapter(getApplicationContext(),data);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }



    public ArrayList<MachineType> filldata(){
        ArrayList<MachineType> data = new ArrayList<>();
        String url="http://geekandsundry.com/wp-content/uploads/2016/11/JPEG-Promo-2-3.jpg";
        data.add(new MachineType("Aerial Lift",url));//+"aerial.png"));
        data.add(new MachineType("Earth Moving Equipments",url));//+"earthmov.jpg"));
        data.add(new MachineType("Forklifts and Material Handling",url));//+"forklift.png"));
        data.add(new MachineType("Concrete and Masonry",url));//+"concrete.jpg"));
        data.add(new MachineType("Compaction ",url));//+"roadroller.jpg"));
        data.add(new MachineType("Power and HVAC",url));//+"power.jpg"));
        data.add(new MachineType("Small Power Tools",url));//+"smallpow.jpg"));
        data.add(new MachineType("Safety and Shoring",url));//+"safety.jpg"));
        data.add(new MachineType("Trailers and Containers",url));//+"trailer.jpg"));
        return data;
    }

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
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {

            viewHolder.machinename.setText(machineTypes.get(i).getName());
            Picasso.with(context).load(machineTypes.get(i).getImageURL()).resize(120, 60).into(viewHolder.img_machine);
            viewHolder.img_machine.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = machineTypes.get(i).getName().toString();
                    Intent intent=new Intent(getApplicationContext(),SubMachineActivity.class);
                    intent.putExtra("id",list.substring(0,list.indexOf(" ")));
                    finish();
                    startActivity(intent);

                }
            });
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
}