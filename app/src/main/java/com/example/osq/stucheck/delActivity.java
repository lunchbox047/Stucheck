package com.example.osq.stucheck;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class delActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        this.setTitle("删除学生信息");
        super.onCreate(savedInstanceState);
        final DBHelper helpter = new DBHelper(this);
        Cursor c = helpter.query();

        String[] from = {"_id", "sno", "sname", "sclass","onduty","late","leave","truancy","ill"};
        int[] to = new int[]{R.id.textid,R.id.textsno, R.id.textsname, R.id.textclass,R.id.textonduty,R.id.textlate,R.id.textleave,R.id.texttruancy,R.id.textill};
        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_del, c, from, to);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//提示对话框
        //设置Listview单击监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final long temp = arg3;
                builder.setMessage("真的要删除该记录吗？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        helpter.del((int) temp);
                        Intent tosearch=new Intent(delActivity.this,MainActivity.class);
                        startActivity(tosearch);
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog AD = builder.create();
                AD.show();
            }
        });
        helpter.close();
    }
}


