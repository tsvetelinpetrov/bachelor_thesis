package com.tuvarna.mytu.util;

import com.tuvarna.mytu.R;

public class IconSelector {

    public static int getIconId(int id) {
        switch (id){
            case 1:
                return R.drawable.ic_wc;
            case 2:
                return R.drawable.ic_fastfood;
            default:
                return R.drawable.ic_android;
        }
    }
}
