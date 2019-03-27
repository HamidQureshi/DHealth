package com.example.hamid.dhealth.Utils;

import android.content.Context;
import android.graphics.Bitmap;

public class ImageUtils {


    public static Bitmap scaleDownBitmap(Bitmap photo, Context context) {

        int newHeight = 100;

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));

        photo = Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }


}
