package com.example.osq.stucheck;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;


public class addActivity extends AppCompatActivity {
    private EditText ed1,ed2,ed3;
    private Button btnensure,btnexit,btnspic;

    protected void onCreate(Bundle savedInstanceState) {

        this.setTitle("添加学生信息");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.setTitle("添加");
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        btnensure=(Button)findViewById(R.id.but1);
        btnexit=(Button)findViewById(R.id.but3);
        btnspic=(Button)findViewById(R.id.spic);
        btnensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sno=ed1.getText().toString();
                String name=ed2.getText().toString();
                String banji=ed3.getText().toString();
                ContentValues values=new ContentValues();


                ByteArrayOutputStream os=new ByteArrayOutputStream();
                BitmapDrawable db=(BitmapDrawable) getDrawable(R.drawable.osq);
                Bitmap photo =db.getBitmap();
                photo.compress(Bitmap.CompressFormat.JPEG,50,os);
                values.put("photo",os.toByteArray());

//                //取出来
//                Bitmap pic=BitmapFactory.decodeByteArray(c.get);
//
                values.put("sno",sno);
                values.put("sname",name);
                values.put("sclass",banji);
                values.put("onduty",0);
                values.put("late",0);
                values.put("leave",0);
                values.put("truancy",0);
                values.put("ill",0);
                DBHelper helper=new DBHelper(getApplicationContext());
                helper.insert(values);
                final AlertDialog.Builder builder=new AlertDialog.Builder(addActivity.this);
                builder.setMessage("录入成功！是否跳转至查询界面？").setPositiveButton("是",new DialogInterface.OnClickListener()
                {public void onClick(DialogInterface dialog,int which)
                {
                    Intent in1=new Intent(addActivity.this,searchActivity.class);
                    startActivity(in1);
                }
                }).setNegativeButton("否",new DialogInterface.OnClickListener()
                {public void onClick(DialogInterface dialog,int which)
                {
                }
                });
                AlertDialog AD=builder.create();
                AD.show();
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(addActivity.this,MainActivity.class);
                startActivity(in);
            }
        });
        btnspic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnspic.setBackground();
            }
        });
    }
}