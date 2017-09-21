package com.example.lenovo.yuekao1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import view.xlistview.XListView;

import static android.R.attr.theme;
import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {

    private List<MyBean> list;
    private XListView xlv;
    private MyAdapter adapter;
    private Citydao dao;
    private String json;
    private String loupan_name;
    private String default_image;
    private int price;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlv = (XListView) findViewById(R.id.xlv);
        Button btn= (Button) findViewById(R.id.btn);
        Button btn2= (Button) findViewById(R.id.btn2);
        Button btn_sou= (Button) findViewById(R.id.btn_sou);
        final EditText ed_name= (EditText) findViewById(R.id.ed_name);
        dao=new Citydao(MainActivity.this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View view) {
                Collections.sort(list, new Comparator<MyBean>() {
                    @Override
                    public int compare(MyBean myBean, MyBean t1) {
                        int i = myBean.getPrice()-t1.getPrice();
                        if(i<0)
                        {
                            return -1;
                        }
                        return 0;
                    }
                });
                adapter.notifyDataSetChanged();

            }
        });
         btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<MyBean>() {
                    @Override
                    public int compare(MyBean myBean, MyBean t1) {
                        int i = myBean.getPrice() - t1.getPrice();
                        if(i>0)
                        {
                            return -1;
                        }
                        return 0;
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
        btn_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 name = ed_name.getText().toString();
                String query = dao.query();
                Toast.makeText(MainActivity.this, "==="+query, Toast.LENGTH_SHORT).show();
                list.clear();
                 Gson gson=new Gson();
                 JsonBean jsonBean = gson.fromJson(query,JsonBean.class);
                 JsonBean.ResultBean result1 = jsonBean.getResult();
                 List<JsonBean.ResultBean.RowsBean> rows = result1.getRows();
                for (int i = 0; i <rows.size() ; i++) {
                    JsonBean.ResultBean.RowsBean rowsBean = rows.get(i);
                    JsonBean.ResultBean.RowsBean.InfoBean info = rowsBean.getInfo();
                    default_image = info.getDefault_image();
                    price = info.getPrice();
                    loupan_name = info.getRegion_title();
                    if(name.equals(loupan_name))
                    {
                    list.add(new MyBean(loupan_name,default_image,price));
                    }
                }

                setDate();
            }
        });
        list = new ArrayList<MyBean>();
        RequestParams params=new RequestParams(Api.url);
        org.xutils.x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                dao.insert(result);
                Jiexi(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void Jiexi(String result) {
        Gson gson=new Gson();
        JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
        JsonBean.ResultBean result1 = jsonBean.getResult();
        List<JsonBean.ResultBean.RowsBean> rows = result1.getRows();
        for (int i = 0; i <rows.size() ; i++) {
            JsonBean.ResultBean.RowsBean rowsBean = rows.get(i);
            JsonBean.ResultBean.RowsBean.InfoBean info = rowsBean.getInfo();
            default_image = info.getDefault_image();
            price = info.getPrice();
            loupan_name = info.getRegion_title();
            list.add(new MyBean(loupan_name, default_image, price));
        }

        setDate();
    }
    private void setDate() {
        adapter = new MyAdapter(MainActivity.this,list);
        xlv.setAdapter(adapter);
              /*  xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(getContext(),Main2Activity.class);
                        intent.putExtra("tu", list.get(i-1).getPic());
                        startActivity(intent);
                    }
                });*/
    }
}
