package com.example.dummy.automo;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Boopalan on 11-Sep-2016.
 */
public class QEditText extends android.support.v7.widget.AppCompatEditText{

    public QEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/linotte_semibold.otf"));
    }
}
