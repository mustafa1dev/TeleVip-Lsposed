package com.my.televip.virtuals.ui.Cells;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.my.televip.Configs.ConfigItem;
import com.my.televip.language.Translator;
import com.my.televip.settings.ui.SettingsAdapter;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;

import java.util.ArrayList;
import java.util.List;

public class ExpandableTextCheckCell extends LinearLayout {

    private final TextCheckCell textCheckCell;

    private final LinearLayout childContainer;
    private final List<ChildRow> children = new ArrayList<>();

    private boolean expanded = false;
    private boolean syncing = false;

    private int resIdRow;

    public ExpandableTextCheckCell(Context context) {
        super(context);
        setOrientation(VERTICAL);

        textCheckCell = Bridge.createTextCheckCell(context);

        addView(textCheckCell.getView(), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        childContainer = new LinearLayout(context);
        childContainer.setOrientation(VERTICAL);
        childContainer.setVisibility(GONE);

        textCheckCell.getView().setOnLongClickListener(v -> {
            SettingsAdapter.playAudio(context);
            return true;
        });
        addView(childContainer, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        textCheckCell.getView().setOnClickListener(v -> setExpanded(!expanded, true));
    }

    public void addChildren(ConfigItem item) {
        Context context = getContext();
        this.children.clear();
        childContainer.removeAllViews();
        List<ConfigItem> children = item.getChildren();
        for (ConfigItem child : children) {
            if (child == null) continue;
            if (child.getType() != ConfigItem.SWITCH) continue;

            TextCheckCell textCheckCell1 = new TextCheckCell(context);
            textCheckCell1.setTextAndCheck(Translator.get(child.getKey()), child.isEnable(), false);

            LinearLayout row = new LinearLayout(context);
            row.setOrientation(HORIZONTAL);
            row.setBackgroundResource(resIdRow);
            row.setPadding(dp(), 0, 0, 0);
            row.setOnLongClickListener(v -> {
                SettingsAdapter.playAudio(context);
                return true;
            });
            row.addView(textCheckCell1.getView(),
                    new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

            childContainer.addView(row,
                    new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            ChildRow childRow = new ChildRow(row, textCheckCell1);
            this.children.add(childRow);

            row.setOnClickListener(v -> {
                boolean newChecked = !textCheckCell1.isChecked();
                textCheckCell1.setChecked(newChecked);
                child.setEnable(newChecked);
                child.run();
                updateMainStateFromChildren(item);
            });

            updateMainStateFromChildren(item);
        }
    }

    public void setExpanded(boolean value, boolean animate) {
        if (expanded == value) return;
        expanded = value;

        if (!animate) {
            childContainer.setVisibility(expanded ? VISIBLE : GONE);
            return;
        }

        if (expanded) {
            childContainer.setVisibility(VISIBLE);
            childContainer.measure(
                    View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            int targetHeight = childContainer.getMeasuredHeight();
            animateHeight(0, targetHeight);
        } else {
            int startHeight = childContainer.getHeight();
            animateHeight(startHeight, 0);
        }
    }

    public void setBChildResource( int resid) {
        resIdRow = resid;
    }

    private void updateMainStateFromChildren(ConfigItem item) {
        if (syncing) return;
        boolean any = false;
        for (ChildRow c : children) {
            if (c.textCheckCell.isChecked()) { any = true; break; }
        }
        syncing = true;
        textCheckCell.setChecked(any);
        item.setEnable(any);
        syncing = false;
        int i = 0;
        for (ChildRow c : children) {
            if (c.textCheckCell.isChecked()) { i++; }
        }
        textCheckCell.setTextAndCheck(Translator.get(item.getKey()) + " " +i +"/" + children.size(), item.isEnable(),false);
    }

    private void animateHeight(int from, int to) {
        ValueAnimator animator = ValueAnimator.ofInt(from, to);
        animator.setDuration(220);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            ViewGroup.LayoutParams params = childContainer.getLayoutParams();
            params.height = value == 0 && to == 0 ? 0 : value;
            childContainer.setLayoutParams(params);
            if (value == 0 && to == 0) {
                childContainer.setVisibility(GONE);
                childContainer.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
        });
        animator.start();
    }

    private int dp() {
        float density = getResources().getDisplayMetrics().density;
        return (int) ((float) 24 * density);
    }

    public static class ChildRow {
        public final LinearLayout rootView;
        public final TextCheckCell textCheckCell;

        ChildRow(LinearLayout rootView, TextCheckCell textCheckCell) {
            this.rootView = rootView;
            this.textCheckCell = textCheckCell;
        }

    }

}