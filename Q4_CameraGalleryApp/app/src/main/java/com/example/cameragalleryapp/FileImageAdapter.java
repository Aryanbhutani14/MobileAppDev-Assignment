package com.example.cameragalleryapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;

public class FileImageAdapter extends BaseAdapter {

    Context context;
    ArrayList<File> files;

    // IMPORTANT CONSTRUCTOR
    public FileImageAdapter(Context context, ArrayList<File> files) {
        this.context = context;
        this.files = files;
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(context);

        imageView.setImageBitmap(
                BitmapFactory.decodeFile(files.get(position).getAbsolutePath())
        );

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(300,300));

        return imageView;
    }
}