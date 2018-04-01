package com.ray.test.testingexpandablelist2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyExpandablelistviewAdapter extends BaseExpandableListAdapter {
    private Context context  ;
    private String[] groups = {"家人","同学","同事"};
    private String[][] childs = { {"哥哥","姊姊"}, {"玖壹壹","兄弟本色","周湯豪","劉亦菲"}, {"賈伯斯","祖克伯",
            "馬雲"} };
    private int[][] childs_imgs = { { R.drawable.img1, R.drawable.img2 },
            { R.drawable.img3, R.drawable.img4 , R.drawable.img5, R.drawable.img6},
            { R.drawable.img7, R.drawable.img8 , R.drawable.img9}
    };

    //引入一个字段context，方便Activity实例化MyExpandablelistviewAdapter
    public MyExpandablelistviewAdapter(Context context) {
        this.context = context;
    }

    //获取一级菜单的分组数目，比如这里就是3组："我的好友","同学","同事"
    public int getGroupCount() {
        return groups.length;
    }

    //获取每个一节菜单中二级菜单的分组数目,比如"家人"中有2个条目("老爸","老妈")
    public int getChildrenCount(int groupPosition) {
        return childs[groupPosition].length;
    }
    //获取每个一级菜单子项对象
    public String getGroup(int groupPosition) {
        return groups[groupPosition];
    }
    //获取每个二级菜单子项对象
    public String getChild(int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];
    }

    //获取每个一级菜单子项对象Id
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    //获取每个二级菜单子项对象Id
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    /**
     * hasStableIds有关于MyExpandablelistviewAdapter适配器刷新顺序
     * getGroupId和getChildId两个方法获取对象Id，获取到的Id,ExpandableListView会根据这个Id确定位置显示内容
     * 然而Id是否有效稳定是由hasStableIds决定的，也就是说：这个方法就是判断item的id是否稳定，
     * 如果有自己的id也就是true，那就是稳定，否则不稳定，则根据item位置来确定id
     *
     */
    public boolean hasStableIds() {
        return true;
    }

    //渲染一级菜单
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if(convertView == null) {
            /**
             * LayoutInflater是一个抽象类，它的inflate方法可以把一个xml文件转化为View对象
             * 对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入
             * 刚刚说明了：LayoutInflater是一个抽象类,要获取LayoutInflater的实例;
             * 获得 LayoutInflater 实例的三种方式:
             *  1.LayoutInflater inflater = getLayoutInflater();  //调用Activity的getLayoutInflater()
             *
             *  2.LayoutInflater localinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             *
             *  3. LayoutInflater inflater = LayoutInflater.from(context);
             *  上面三种方法的本质是一样的
             */
            LayoutInflater minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = minflater.inflate(R.layout.group_item,null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.textview_group);
        tv.setText(groups[groupPosition]);
        tv.setTextSize(25);
        tv.setPadding(15, 5, 0, 0);
        return convertView;
    }
    //渲染二级菜单
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = minflater.inflate(R.layout.child_item,null);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageview_child);
        TextView tv = (TextView) convertView.findViewById(R.id.textview_child);

        iv.setImageResource(childs_imgs[groupPosition][childPosition]);
        //导入的包为：import android.view.ViewGroup.LayoutParams;
        android.view.ViewGroup.LayoutParams params = iv.getLayoutParams();
        params.width = 200;
        params.height = 200;
        iv.setLayoutParams(params);

        tv.setText(childs[groupPosition][childPosition]);
        //记得return convertView
        return convertView;
    }

    //true:让子项可选 ;     false:让子项不可选
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}