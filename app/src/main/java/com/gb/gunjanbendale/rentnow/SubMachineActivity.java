package com.gb.gunjanbendale.rentnow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubMachineActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HorizontalAdapter adapter;
    private List<MachineType> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_machine);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        String s=i.getStringExtra("id");

        data = filldatas(s);

        adapter= new HorizontalAdapter(data,getApplication());
        recyclerView=(RecyclerView) findViewById(R.id.recyclerViewSub);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                startActivity(new Intent(this,MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public List<MachineType> filldatas(String S){
        List<MachineType> data = new ArrayList<>();
        String s=S.toLowerCase();
        int sa=getResources().getIdentifier(s,"array",getPackageName());
        String[] array=getResources().getStringArray(sa);
        int i=0;
        while(i<array.length){
            String x=S.toLowerCase()+i;
            int r=getResources().getIdentifier(x,"drawable",getPackageName());
            data.add(new MachineType(array[i],r));
            i++;
        }
        return data;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            moveTaskToBack(true);

            finish();
            startActivity(new Intent(SubMachineActivity.this,MainActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
        public HorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.machine_card, parent, false);

            return new HorizontalAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final HorizontalAdapter.MyViewHolder holder, final int position) {

            holder.imageView.setImageResource(horizontalList.get(position).getThumbnail());
            holder.txtview.setText(horizontalList.get(position).getName());

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String list = horizontalList.get(position).getName().toString();
                    int img = horizontalList.get(position).getThumbnail();
                    Intent intent=new Intent(getApplicationContext(),RentalRequest.class);
                    intent.putExtra("id",list);
                    intent.putExtra("thumbnail",img);
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
