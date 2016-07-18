package com.tellh.androidlibraryarchitecturedemo.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tellh.androidlibraryarchitecturedemo.R;

import java.util.ArrayList;
import java.util.List;

public class DataBindingRVActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_rv);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Book> books = new ArrayList<>();
        books.add(new Book("瓦尔登湖", "梭罗", "中国三峡出版社",
                "http://o762c73os.bkt.clouddn.com/book_walden.png"));
        books.add(new Book("楚辞", "屈原", "中国画报出版社",
                "http://o762c73os.bkt.clouddn.com/book_song_chu.png"));
        books.add(new Book("西方哲学史", "梯利", "光明日报出版社",
                "http://o762c73os.bkt.clouddn.com/book_west.png"));
        books.add(new Book("泰和宜山会语", "马一浮", "辽宁教育出版社",
                "http://o762c73os.bkt.clouddn.com/book_taihe_talk.png"));
        books.add(new Book("易中天中华史", "易中天", "浙江文艺出版社",
                "http://o762c73os.bkt.clouddn.com/book_yizhongtian.png"));
        books.add(new Book("有味", "汪涵", "广西师范大学出版社",
                "http://o762c73os.bkt.clouddn.com/book_youwei.png"));
        books.add(new Book("风中的纸屑", "周国平", "浙江文艺出版社",
                "http://o762c73os.bkt.clouddn.com/book_song_chu.png"));
        books.add(new Book("瓦尔登湖", "梭罗", "中国三峡出版社",
                "http://o762c73os.bkt.clouddn.com/book_walden.png"));
        books.add(new Book("楚辞", "屈原", "中国画报出版社",
                "https://raw.githubusercontent.com/TellH/AndroidLibraryArchitectureDemo/master/raw/book_song_chu.png"));
        books.add(new Book("西方哲学史", "梯利", "光明日报出版社",
                "http://o762c73os.bkt.clouddn.com/book_west.png"));
        books.add(new Book("泰和宜山会语", "马一浮", "辽宁教育出版社",
                "http://o762c73os.bkt.clouddn.com/book_taihe_talk.png"));
        books.add(new Book("易中天中华史", "易中天", "浙江文艺出版社",
                "http://o762c73os.bkt.clouddn.com/book_yizhongtian.png"));
        books.add(new Book("有味", "汪涵", "广西师范大学出版社",
                "http://o762c73os.bkt.clouddn.com/book_youwei.png"));
        books.add(new Book("风中的纸屑", "周国平", "浙江文艺出版社",
                "http://o762c73os.bkt.clouddn.com/book_song_chu.png"));
        books.add(new Book("MacBook", "Steve Job", "Apple Inc",
                "http://o762c73os.bkt.clouddn.com/MacBook.jpg"));
        books.add(new Book("Naruto", "Naruto", "Naruto",
                "https://raw.githubusercontent.com/TellH/AndroidLibraryArchitectureDemo/master/raw/Naruto.gif"));
        rv.setAdapter(new CommonAdapter(books) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_rv_book;
            }

        });
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        //使用网络要记得加网络权限
        Log.d("DataBindingRVActivity", "loadImage() called with: " + "view = [" + view + "], url = [" + url + "], error = [" + "]");
        if (!TextUtils.isEmpty(url))
            Glide.with(view.getContext())
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(error)
                    .centerCrop()
//                    .fitCenter()
                    .into(view);
    }

}
