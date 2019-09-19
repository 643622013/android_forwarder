package com.cfbx.framework.adapter;

import android.view.View;

/**
 * Created by 71033 on 2017/10/17.
 */

public interface onItemClickListener<T> {
    void onClick(View v, T data);

    boolean onLongClick(View v, T data);
}