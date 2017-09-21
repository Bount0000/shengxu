package com.example.lenovo.yuekao1;

import android.support.annotation.NonNull;

/**
 * Created by lenovo on 2017/9/21.
 */

public class MyBean {
    private String name;
     private String pic;
     private int price;

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MyBean(String name, String pic, int price) {

        this.name = name;
        this.pic = pic;
        this.price = price;
    }

}
