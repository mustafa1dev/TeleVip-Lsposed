package com.my.televip.Drawable;

import static android.view.View.LAYOUT_DIRECTION_RTL;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

import com.my.televip.virtuals.Theme;

public class ArrowDrawable extends Drawable {

    private final Paint paint;
    private final Path path;

    public ArrowDrawable() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Theme.getColor(Theme.getKey_actionBarDefaultIcon()));

        path = new Path();
        path.moveTo(20f, 11f);
        path.lineTo(7.8f, 11f);
        path.lineTo(13.4f, 5.4f);
        path.lineTo(12f, 4f);
        path.lineTo(4f, 12f);
        path.lineTo(12f, 20f);
        path.lineTo(13.4f, 18.6f);
        path.lineTo(7.8f, 13f);
        path.lineTo(20f, 13f);
        path.lineTo(20f, 11f);
        path.close();
    }

    @Override
    public void draw(Canvas canvas) {
        float scaleX = getBounds().width() / 24f;
        float scaleY = getBounds().height() / 24f;
        canvas.save();

        // دعم autoMirrored عند RTL
        if ((getLayoutDirection() == LAYOUT_DIRECTION_RTL)) {
            canvas.translate(getBounds().width(), 0);
            canvas.scale(-scaleX, scaleY);
        } else {
            canvas.scale(scaleX, scaleY);
        }

        canvas.drawPath(path, paint);
        canvas.restore();
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha() == 255 ? android.graphics.PixelFormat.OPAQUE : android.graphics.PixelFormat.TRANSLUCENT;
    }
}