package com.example.lenovo.yuekao1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/9/21.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<MyBean> list;

    public MyAdapter(Context context, List<MyBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context,R.layout.item,null);
        TextView tv_name= view.findViewById(R.id.tv_name);
        TextView tv_price= view.findViewById(R.id.tv_price);
        ImageView iv= view.findViewById(R.id.iv);
        tv_name.setText(list.get(i).getName());
        tv_price.setText(list.get(i).getPrice()+"");
        ImageLoader.getInstance().displayImage(list.get(i).getPic(),iv);

        return view;
    }
}
