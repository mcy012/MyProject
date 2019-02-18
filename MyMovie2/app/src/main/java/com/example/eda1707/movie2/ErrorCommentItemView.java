package com.example.eda1707.movie2;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ErrorCommentItemView extends ConstraintLayout {
    Button refresh;

    public ErrorCommentItemView(Context context) {
        super(context);

        init(context);
    }

    public ErrorCommentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }


    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.error_comment_item_view, this, true);

        refresh = (Button) findViewById(R.id.refresh);
    }
}
