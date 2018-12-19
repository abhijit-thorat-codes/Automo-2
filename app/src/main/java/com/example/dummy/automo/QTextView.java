package com.example.dummy.automo;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by DEV on 28-08-2016.
 */
public class QTextView extends android.support.v7.widget.AppCompatTextView {

    public QTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/linotte_semibold.otf"));
    }

}
