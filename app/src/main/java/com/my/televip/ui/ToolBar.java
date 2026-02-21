package com.my.televip.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.televip.Drawable.ArrowDrawable;
import com.my.televip.MainHook;
import com.my.televip.virtuals.Theme;

public class ToolBar extends LinearLayout{

    private final Context context;
    private TextView title;
    private ImageView image;

    public ToolBar(Context context){
        super(context);
        this.context = context;
        createToolbar();
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        @SuppressLint({"InternalInsetResource", "DiscouragedApi"}) int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void createToolbar(){
        int statusBar = getStatusBarHeight(context);

        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dpToPx(56) + statusBar   // ارتفاع أكبر تلقائي
        ));

        this.setBackgroundColor(
                Theme.getColor(Theme.getKey_actionBarDefault())
        );

        this.setFitsSystemWindows(false);
        this.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL);

        /* padding مثل تيليجرام */
        this.setPadding(
                dpToPx(8),
                statusBar,   // أهم سطر
                dpToPx(16),
                0
        );

        /* ================= Back / Icon ================= */
        image = new ImageView(context);

        LinearLayout.LayoutParams iconParams =
                new LinearLayout.LayoutParams(
                        dpToPx(40),
                        dpToPx(40)
                );

        iconParams.gravity = Gravity.CENTER_VERTICAL;
        image.setLayoutParams(iconParams);

        /* Padding داخلي مثل تيليجرام */
        image.setPadding(
                dpToPx(8),
                dpToPx(8),
                dpToPx(8),
                dpToPx(8)
        );

        /* مهم */
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);

        /* Ripple تيليجرام */
        Drawable rippleDrawable = new android.graphics.drawable.RippleDrawable(
                ColorStateList.valueOf(0x20FFFFFF),
                null,
                null
        );
        image.setBackground(rippleDrawable);

        /* ================= Title ================= */
        title = new TextView(context);
        title.setTextSize(18);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setSingleLine(true);
        title.setEllipsize(TextUtils.TruncateAt.END);

        title.setTextColor(
                Theme.getColor(Theme.getKey_actionBarDefaultTitle())
        );

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        0,
                        LayoutParams.WRAP_CONTENT,
                        1f
                );

        titleParams.gravity = Gravity.CENTER_VERTICAL;
        titleParams.setMargins(dpToPx(8), 0, 0, 0);
        title.setLayoutParams(titleParams);

        /* ================= Add ================= */
        this.addView(image);
        this.addView(title);
    }

    private int dpToPx(int dp) {
        return (int) (dp * MainHook.launchActivity.getResources().getDisplayMetrics().density);
    }

    public void setTextTitle(CharSequence title) {
        this.title.setText(title);
    }

    public void setColorTitle(int colorTitle) {
        this.title.setTextColor(colorTitle);
    }

    public void setTextSizeTitle(float sizeTitle) {
        this.title.setTextSize(sizeTitle);
    }
    public void setImageDrawable(Drawable drawable){
        this.image.setImageDrawable(drawable);
    }
    public ImageView getImage(){
        return this.image;
    }
}
