package com.example.osq.stucheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class stucheckActivity  extends Activity implements View.OnClickListener{
    TextView textname,textclass,textnum;
    Button btnnext;
    RadioButton rbonduty,rblate,rbleave,rbtruancy,rbill;
    ImageView ivphoto;

    public void onCreate(Bundle savedInstanceState)
    {
        this.setTitle("课堂点名");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stucheck);
        final DBHelper helper=new DBHelper(this);
        final Cursor c=helper.query();
        textname=(TextView)findViewById(R.id.showname);
        textclass=(TextView)findViewById(R.id.showclass);
        textnum=(TextView)findViewById(R.id.showsno);
        btnnext=(Button)findViewById(R.id.nextsno);

        ivphoto=(ImageView)findViewById(R.id.photo);

        rbonduty=(RadioButton)findViewById(R.id.ondutybtn);
        rblate=(RadioButton)findViewById(R.id.latebtn);
        rbleave=(RadioButton)findViewById(R.id.leavebtn);
        rbtruancy=(RadioButton)findViewById(R.id.truancybtn);
        rbill=(RadioButton)findViewById(R.id.illbtn);

        //初始化显示
        if(c!=null&&c.getCount()>0)
            if(c.moveToNext()) {
                String sno = c.getString(1);
                textnum.setText(sno);
                String sname = c.getString(2);
                System.out.println(sname);
                textname.setText(sname);
                String sclass = c.getString(3);
                textclass.setText(sclass);
//                Bitmap pic= BitmapFactory.decodeByteArray(c.getBlob(9),0,c.getBlob(9).length);
//                ivphoto.setImageBitmap(pic);
            }
        rbonduty.setChecked(true);

        //提交当前学号的点名情况
        rbonduty.setOnClickListener(this);
        rblate.setOnClickListener(this);
        rbleave.setOnClickListener(this);
        rbtruancy.setOnClickListener(this);
        rbill.setOnClickListener(this);
        //跳转下一学号
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(c!=null&&c.getCount()>0)
                   if(c.moveToNext())
                   {
                       String sno=c.getString(1);
                       textnum.setText(sno);
                       String sname=c.getString(2);
                       System.out.println(sname);
                       textname.setText(sname);
                       String sclass=c.getString(3);
                       textclass.setText(sclass);
//                       Bitmap pic= BitmapFactory.decodeByteArray(c.getBlob(9),0,c.getBlob(9).length);
//                       ivphoto.setImageBitmap(pic);
                   }
                rbonduty.setChecked(true);
            }
        });
    }
    public void onClick(View view){
        final DBHelper helper=new DBHelper(this);
        final Cursor c=helper.query();
        final RadioButton btnview=(RadioButton)view;
        final String strbtn=(String)btnview.getText();
        final ContentValues values=new ContentValues();
        final AlertDialog.Builder builder=new AlertDialog.Builder(stucheckActivity.this);
        builder.setMessage("是否提交本次点名？").setPositiveButton("是",new DialogInterface.OnClickListener()
        {public void onClick(DialogInterface dialog,int which)
        {
            switch(strbtn){
                case "在勤":
                    String UPDATESQL1="UPDATE stumsgtb SET onduty=1 where sno=11";
                    helper.update(UPDATESQL1);
                    break;
                case "迟到":
                    String UPDATESQL2="UPDATE stumsgtb SET onduty=1 where sno=11";
                    helper.update(UPDATESQL2);
                    break;
                case "早退":
                    String UPDATESQL3="UPDATE stumsgtb SET onduty=1 where sno=11";
                    helper.update(UPDATESQL3);
                    break;
                case "旷课":
                    String UPDATESQL4="UPDATE stumsgtb SET onduty=1 where sno=11";
                    helper.update(UPDATESQL4);
                    break;
                case "病假":
                    String UPDATESQL5="UPDATE stumsgtb SET onduty=1 where sno=11";
                    helper.update(UPDATESQL5);
                    break;
            }

            Intent in=new Intent(stucheckActivity.this,stucheckActivity.class);
            startActivity(in);

        }
        }).setNegativeButton("否",new DialogInterface.OnClickListener()
        {public void onClick(DialogInterface dialog,int which)
        {
        }
        });
        AlertDialog AD=builder.create();
        AD.show();

    }
}






