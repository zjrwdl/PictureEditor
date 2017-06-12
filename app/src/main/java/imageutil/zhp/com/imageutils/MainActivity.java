package imageutil.zhp.com.imageutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private ImageView mImageSrc;
    private SeekBar mColorSeekbar,mSaturationSeekbar,mLightSeekbar;
    private float mHue = 0,mSaturation = 1f,mLight = 1f;
    private Bitmap mSrcBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        mImageSrc = (ImageView) this.findViewById(R.id.image_src);
        mColorSeekbar = (SeekBar) this.findViewById(R.id.color_seekbar);
        mSaturationSeekbar = (SeekBar) this.findViewById(R.id.saturation_seekbar);
        mLightSeekbar = (SeekBar) this.findViewById(R.id.light_seekbar);
        mColorSeekbar.setOnSeekBarChangeListener(this);
        mSaturationSeekbar.setOnSeekBarChangeListener(this);
        mLightSeekbar.setOnSeekBarChangeListener(this);

        mSrcBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.scene);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()){
            case R.id.color_seekbar:
                //-180 < hue < 180
                mHue = progress - 180;
                break;

            case R.id.saturation_seekbar:
                //0 < saturation < 2;
                mSaturation = progress * 1f/180;
                break;

            case R.id.light_seekbar:
                //0 < saturation < 2;
                mLight = progress * 1f/180;
                break;

        }

        Bitmap bitmap = ImageMatrixUtil.getMatrixChangeBitmap(mSrcBitmap,mHue,mSaturation,mLight);
        mImageSrc.setImageBitmap(bitmap);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
