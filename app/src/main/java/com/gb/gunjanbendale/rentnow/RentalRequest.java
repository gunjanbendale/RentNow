package com.gb.gunjanbendale.rentnow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import org.w3c.dom.Text;

public class RentalRequest extends AppCompatActivity {
    MachineType machineType;
    CardView cardView;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent mIntent = getIntent();
        String text = mIntent.getStringExtra("id");
        int img = mIntent.getIntExtra("thumbnail", R.drawable.ic_launcher_background);
        machineType = new MachineType(text, img);
        cardView = (CardView) findViewById(R.id.machine);
        textView = (TextView) findViewById(R.id.machinetype);
        textView.setText(text);
        imageView = (ImageView) findViewById(R.id.machineimg);
        imageView.setImageResource(img);

        final Button plus = (Button) findViewById(R.id.add_button);
        Button minus = (Button) findViewById(R.id.minus_button);
        final TextView quantity = (TextView) findViewById(R.id.quantity);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(quantity.getText().toString());
                int b = a + 1;
                quantity.setText(Integer.toString(b));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(quantity.getText().toString());
                if (a > 1) {
                    int b = a - 1;
                    quantity.setText(Integer.toString(b));
                } else quantity.setText(quantity.getText());
            }
        });
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

}