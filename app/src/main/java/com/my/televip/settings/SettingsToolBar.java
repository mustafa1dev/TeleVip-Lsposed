package com.my.televip.settings;

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

import com.my.televip.application.AndroidUtilities;
import com.my.televip.virtuals.Theme;

public class SettingsToolBar extends LinearLayout {

    private final Context context;
    private TextView title;
    private ImageView image;

    public SettingsToolBar(Context context){
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
                AndroidUtilities.dp(56) + statusBar));

        this.setBackgroundColor(Theme.getToolBarColor());

        this.setFitsSystemWindows(false);
        this.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL);

        /* padding مثل تيليجرام */
        this.setPadding(
                AndroidUtilities.dp(8),
                statusBar,   // أهم سطر
                AndroidUtilities.dp(16),
                0
        );

        /* ================= Back / Icon ================= */
        image = new ImageView(context);

        LinearLayout.LayoutParams iconParams =
                new LinearLayout.LayoutParams(
                        AndroidUtilities.dp(40),
                        AndroidUtilities.dp(40)
                );

        iconParams.gravity = Gravity.CENTER_VERTICAL;
        image.setLayoutParams(iconParams);

        /* Padding داخلي مثل تيليجرام */
        image.setPadding(
                AndroidUtilities.dp(8),
                AndroidUtilities.dp(8),
                AndroidUtilities.dp(8),
                AndroidUtilities.dp(8)
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

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        0,
                        LayoutParams.WRAP_CONTENT,
                        1f
                );

        titleParams.gravity = Gravity.CENTER_VERTICAL;
        titleParams.setMargins(AndroidUtilities.dp(8), 0, 0, 0);
        title.setLayoutParams(titleParams);

        /* ================= Add ================= */
        this.addView(image);
        this.addView(title);
    }

    public void setTextTitle(CharSequence title) {
        this.title.setText(title);
    }

    public void setColorTitle(int colorTitle) {
        this.title.setTextColor(colorTitle);
    }

    public void setImageDrawable(Drawable drawable){
        this.image.setImageDrawable(drawable);
    }
    public ImageView getImage(){
        return this.image;
    }
}
