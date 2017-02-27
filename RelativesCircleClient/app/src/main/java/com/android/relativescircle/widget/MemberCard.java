package com.android.relativescircle.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.relativescircle.R;

/**
 * Created by fantao on 17-2-24.
 */

public class MemberCard extends CardView {
    private Drawable photo;
    private String name;
    private String relativeShip;
    private ImageView photoView;
    private TextView nameView;
    private TextView relativeShipView;

    public MemberCard(Context context) {
        super(context);
    }

    public MemberCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MemberCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
        if (photoView != null) {
            photoView.setImageDrawable(photo);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (nameView != null) {
            nameView.setText(name);
        }
    }

    public String getRelativeShip() {
        return relativeShip;
    }

    public void setRelativeShip(String relativeShip) {
        this.relativeShip = relativeShip;
        if (relativeShipView != null) {
            relativeShipView.setText(relativeShip);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        photoView = (ImageView) findViewById(R.id.member_photo);
        nameView = (TextView) findViewById(R.id.member_name);
    }
}
