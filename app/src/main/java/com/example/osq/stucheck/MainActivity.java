package com.example.osq.stucheck;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnadd,btndel,btnquery,btncheck;
    private TextView tvshowsno,tvshowname,tvshowclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("学生考勤系统");

        final DBHelper helper=new DBHelper(this);
        final Cursor c=helper.query();

        btnadd=(Button)findViewById(R.id.add);
        btndel=(Button)findViewById(R.id.del);
        btnquery=(Button)findViewById(R.id.query);
        btncheck=(Button)findViewById(R.id.check);
        tvshowsno=(TextView)findViewById(R.id.showsno);
        tvshowname=(TextView)findViewById(R.id.showname);
        tvshowclass=(TextView)findViewById(R.id.showclass);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,addActivity.class);
                startActivity(intent);
            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,delActivity.class);
                startActivity(i1);
            }
        });
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,searchActivity.class);
                startActivity(i2);
            }
        });
        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i3=new Intent(MainActivity.this,stucheckActivity.class);
                startActivity(i3);
            }
        });
    }
}