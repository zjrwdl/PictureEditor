package imageutil.zhp.com.imageutils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by zhp on 2017/6/12.
 */

public class ImageMatrixUtil {

    public static Bitmap getMatrixChangeBitmap(Bitmap src,float hue,float saturation ,float light){
        Bitmap outBitmap = Bitmap.createBitmap(src.getWidth(),src.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2,hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lightMatrix = new ColorMatrix();
        lightMatrix.setScale(light,light,light,1);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.postConcat(hueMatrix);
        colorMatrix.postConcat(saturationMatrix);
        colorMatrix.postConcat(lightMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(src,0,0,paint);
        return outBitmap;
    }
}
