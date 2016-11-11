package com.example.osq.stucheck;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class searchActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState)
    {
        this.setTitle("查询学生信息");
        super.onCreate(savedInstanceState);
        final DBHelper helper=new DBHelper(this);
        Cursor c=helper.query();

        String[] from = {"_id", "sno", "sname", "sclass","onduty","late","leave","truancy","ill"};
        int[] to = new int[]{R.id.textid,R.id.textsno, R.id.textsname, R.id.textclass,R.id.textonduty,R.id.textlate,R.id.textleave,R.id.texttruancy,R.id.textill};
        ListAdapter adapter=new SimpleCursorAdapter(this,R.layout.activity_del,c,from,to);
        ListView listView=getListView();
        listView.setAdapter(adapter);
        helper.close();
    }
}
