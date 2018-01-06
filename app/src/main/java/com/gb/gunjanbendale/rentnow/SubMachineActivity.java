package com.gb.gunjanbendale.rentnow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubMachineActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataAdapter adapter;
    String URL = "http://geekandsundry.com/wp-content/uploads/2016/11/JPEG-Promo-2-3.jpg";
    ProgressDialog mProgressDialog;

    private ArrayList<MachineType> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_machine);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        String s=i.getStringExtra("id");
        data = filldatas(s);
        adapter= new DataAdapter(getApplicationContext(),data);
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

    public ArrayList<MachineType> filldatas(String S){
        ArrayList<MachineType> data = new ArrayList<>();
        String s=S.toLowerCase();
        int sa=getResources().getIdentifier(s,"array",getPackageName());
        String[] array=getResources().getStringArray(sa);

        int i=0;
        while(i<array.length){
            String x=S.toLowerCase()+i;
            String r="https://drive.google.com/open?id=1CbhQ7PHAh5DBl_iQ394uMmFbO8dpjGsP/" + S.toLowerCase() + i + ".png";
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
                    String img = machineTypes.get(i).getImageURL();
                    Intent intent=new Intent(getApplicationContext(),RentalRequest.class);
                    intent.putExtra("id",list);
                    intent.putExtra("thumbnail",img);
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
