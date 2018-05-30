package com.feevx.ravan.newssampleapp.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public final class DataBinder {

    private DataBinder() {
    }

    @BindingAdapter("picture")
    public static void setPicturel(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
