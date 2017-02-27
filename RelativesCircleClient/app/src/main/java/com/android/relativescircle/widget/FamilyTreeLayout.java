package com.android.relativescircle.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.android.relativescircle.R;
import com.android.relativescircle.model.Member;
import com.android.relativescircle.utils.MemberListManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fantao on 17-2-21.
 */

public class FamilyTreeLayout extends FrameLayout {
    private Context context;
    private MemberListManager memberListManager;
    private List<Member> memberList;
    private List<MemberCard> memberCards;
    private List<int[]> positions;
    private Paint paint;
    private int cardWidth;
    private int cardHeight;
    private int horizontalGap;
    private int verticalGap;
    private int totalWidth;
    private int totalHeight;
    private int unitWeightWidth;
    private float preDistance;
    private float distance;
    private float scale = 1.0f;

    public FamilyTreeLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FamilyTreeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FamilyTreeLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FamilyTreeLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        memberListManager = MemberListManager.getInstance();
        memberList = memberListManager.getMemberList();
        Resources r = context.getResources();
        paint = new Paint();
        paint.setAlpha(0xff);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(r.getColor(R.color.path_color));
        cardWidth = r.getDimensionPixelOffset(R.dimen.member_card_width);
        cardHeight = r.getDimensionPixelOffset(R.dimen.member_card_height);
        horizontalGap = r.getDimensionPixelOffset(R.dimen.tree_layout_horizontal_gap);
        verticalGap = r.getDimensionPixelOffset(R.dimen.tree_layout_vertical_gap);
        totalWidth = memberListManager.getTotalWeight() * (cardWidth + horizontalGap);
        totalHeight = memberListManager.getGenerationCount() * (cardHeight + verticalGap) + verticalGap;
        unitWeightWidth = cardWidth + horizontalGap;
        memberCards = new ArrayList<MemberCard>();
        for (Member m : memberList) {
            MemberCard mc = getMemberCard();
            mc.setName(m.getName());
            if (m.getPhoto() == null) {
                mc.setPhoto(r.getDrawable(m.getSex() == 0 ? R.drawable.boy : R.drawable.girl));
            } else {
                mc.setPhoto(m.getPhoto());
            }
            memberCards.add(mc);
            addView(mc);
        }
        calculatorPositions();
        setWillNotDraw(false);
    }

    private MemberCard getMemberCard() {
        View view = LayoutInflater.from(context).inflate(R.layout.member_card_view_item, this, false);
        return view == null ? null : (MemberCard) view;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() == 2) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) {
                    preDistance = getDistance(event);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 2) {
                    distance = getDistance(event);
                    float allDistance = (float) Math.sqrt(totalWidth * totalWidth + totalHeight * totalHeight);
                    scale += (distance - preDistance) / allDistance;
                    if (scale > 0.3f && scale < 1.0f) {
                        float[] c = getMidPoint(event);
//                        this.setPivotX(c[0]);
//                        this.setPivotY(c[1]);
//                        this.setScaleX(scale);
//                        this.setScaleY(scale);
                    } else {
                        if (scale > 1.0f) {
                            scale = 1.0f;
                        } else if (scale < 0.4f) {
                            scale = 0.4f;
                        }

                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                preDistance = 0;
                break;
        }
        return true;
    }
    private float[] getMidPoint(MotionEvent e) {
        float x = (e.getX(0) + e.getX(1)) / 2;
        float y = (e.getY(0) + e.getY(1)) / 2;
        return new float[]{x, y};
    }
    private float getDistance(MotionEvent event) {
        float x = event.getX(1) - event.getX(0);
        float y = event.getY(1) - event.getY(0);
        float distance = (float) Math.sqrt(x * x + y * y);
        return distance;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(totalWidth, totalHeight);
        Log.e("fantao", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 0; i < positions.size(); i++) {
            MemberCard mc = memberCards.get(i);
            int[] p = positions.get(i);
            mc.layout(p[0], p[1], p[0] + cardWidth, p[1] + cardHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        for (int i = 0; i < positions.size(); i++) {
            Member m = memberList.get(i);
            int[] p = positions.get(i);
            int index = memberListManager.getRelativeMemberIndex(m);
            int[] rp = index > -1 ? positions.get(index) : null;
            Member rm = index > -1 ? memberList.get(index) : null;
            path.reset();
            if (m.getSex() == 1) {
                if (m.isWife()) {
                    path.moveTo(p[0], p[1] + cardHeight / 2);
                    path.lineTo(p[0] - horizontalGap, p[1] + cardHeight / 2);
                } else {
                    path.moveTo(p[0] + cardWidth / 2, p[1]);
                    path.lineTo(p[0] + cardWidth / 2, p[1] - verticalGap / 2);
                    if (m.getPosition() == rm.getPosition()) {
                        path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight + verticalGap / 2);
                        if (m.getPosition() + m.getWeight() == rm.getPosition() + rm.getWeight()) {
                            path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight / 2);
                        }
                    } else if (m.getPosition() + m.getWeight() == rm.getPosition() + rm.getWeight()) {
                        path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight + verticalGap / 2);
                        path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight / 2);
                    }
                }
            } else {
                if (m.getGeneration() > 0) {
                    path.moveTo(p[0] + cardWidth / 2, p[1]);
                    path.lineTo(p[0] + cardWidth / 2, p[1] - verticalGap / 2);
                    if (m.getPosition() == rm.getPosition()) {
                        path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight + verticalGap / 2);
                        if (m.getPosition() + m.getWeight() == rm.getPosition() + rm.getWeight()) {
                            path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight / 2);
                        }
                    } else if (m.getPosition() + m.getWeight() == rm.getPosition() + rm.getWeight()) {
                        path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight + verticalGap / 2);
                        path.lineTo(rp[0] + cardWidth + horizontalGap / 2, rp[1] + cardHeight / 2);
                    }
                }
            }
            canvas.drawPath(path, paint);
        }
    }

    private void calculatorPositions() {
        positions = new ArrayList<int[]>();
        for (int i = 0; i < memberList.size(); i++) {
            Member m = memberList.get(i);
            int[] p = new int[2];
            if (m.isWife()) {
                p[0] = m.getRelativeMember().getPosition() * unitWeightWidth + m.getRelativeMember().getWeight() * unitWeightWidth / 2 + horizontalGap / 2;
            } else {
                if (i + 1 < memberList.size() && memberList.get(i + 1).getRelativeMember().equals(m)) {
                    p[0] = m.getPosition() * unitWeightWidth + m.getWeight() * unitWeightWidth / 2 - cardWidth - horizontalGap / 2;
                } else {
                    p[0] = m.getPosition() * unitWeightWidth + m.getWeight() * unitWeightWidth / 2 - cardWidth / 2;
                }
            }
            p[1] = m.getGeneration() * (cardHeight + verticalGap) + verticalGap;
            positions.add(p);
        }
    }
}
