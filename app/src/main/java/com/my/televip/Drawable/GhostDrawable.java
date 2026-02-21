package com.my.televip.Drawable;

import static android.view.View.LAYOUT_DIRECTION_RTL;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.my.televip.virtuals.Theme;

public class GhostDrawable extends Drawable {

    private final Paint paint;
    private final Path bodyPath;
    private final Path eyeLeft;
    private final Path eyeRight;
    private final Paint eyePaint;

    public GhostDrawable() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        // جسم الشبح
        bodyPath = new Path();
        bodyPath.moveTo(12f, 1.83f);
        bodyPath.cubicTo(6.94f, 1.83f, 2.84f, 5.96f, 2.84f, 11.06f);
        bodyPath.lineTo(2.84f, 20.82f);
        bodyPath.cubicTo(2.84f, 21.37f, 3.4f, 21.71f, 3.88f, 21.47f);
        bodyPath.lineTo(6.2f, 20.3f);
        bodyPath.cubicTo(7.77f, 19.51f, 9.67f, 19.97f, 10.73f, 21.38f);
        bodyPath.cubicTo(11.44f, 22.34f, 12.83f, 22.44f, 13.68f, 21.58f);
        bodyPath.lineTo(14.05f, 21.21f);
        bodyPath.cubicTo(15.37f, 19.88f, 17.43f, 19.63f, 19.05f, 20.6f);
        bodyPath.lineTo(20.35f, 21.39f);
        bodyPath.cubicTo(20.7f, 21.59f, 21.15f, 21.35f, 21.15f, 20.92f);
        bodyPath.lineTo(21.15f, 11.06f);
        bodyPath.cubicTo(21.15f, 5.96f, 17.05f, 1.83f, 12f, 1.83f);
        bodyPath.close();

        // العين اليمنى
        eyeRight = new Path();
        eyeRight.addCircle(15.5f, 11f, 1.5f, Path.Direction.CW);

        // العين اليسرى
        eyeLeft = new Path();
        eyeLeft.addCircle(8.5f, 11f, 1.5f, Path.Direction.CW);

        eyePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eyePaint.setStyle(Paint.Style.FILL);
        eyePaint.setColor(0xFF000000); // أسود (تقدر تغيّره)
    }

    @Override
    public void draw(Canvas canvas) {
        float scaleX = getBounds().width() / 24f;
        float scaleY = getBounds().height() / 24f;

        canvas.save();

        if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
            canvas.translate(getBounds().width(), 0);
            canvas.scale(-scaleX, scaleY);
        } else {
            canvas.scale(scaleX, scaleY);
        }

        // رسم الجسم
        canvas.drawPath(bodyPath, paint);

        // رسم العيون
        canvas.drawPath(eyeLeft, eyePaint);
        canvas.drawPath(eyeRight, eyePaint);

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
        return paint.getAlpha() == 255 ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }
}