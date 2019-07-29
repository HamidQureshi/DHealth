package com.example.hamid.dhealth.utils

import android.content.Context
import android.graphics.Bitmap

object ImageUtils {


    fun scaleDownBitmap(photo: Bitmap, context: Context): Bitmap {
        var photo = photo

        val newHeight = 100

        val densityMultiplier = context.resources.displayMetrics.density

        val h = (newHeight * densityMultiplier).toInt()
        val w = (h * photo.width / photo.height.toDouble()).toInt()

        photo = Bitmap.createScaledBitmap(photo, w, h, true)

        return photo
    }


}
