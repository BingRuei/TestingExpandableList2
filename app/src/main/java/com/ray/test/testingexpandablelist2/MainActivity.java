package com.ray.test.testingexpandablelist2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ExpandableListView expan_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expan_listview = (ExpandableListView) findViewById(R.id.expandablelv);
        expan_listview.setAdapter(new MyExpandablelistviewAdapter(this));
        expan_listview.setOnChildClickListener(new android.widget.ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(MainActivity.this, "你点击的是第" + (groupPosition + 1)
                        + "的菜单下的第" + (childPosition + 1) + "选项", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}