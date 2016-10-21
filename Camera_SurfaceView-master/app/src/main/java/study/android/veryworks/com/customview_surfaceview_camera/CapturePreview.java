package study.android.veryworks.com.customview_surfaceview_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class CapturePreview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_preview);


        Intent intent = getIntent();
        String photoPath = intent.getStringExtra("strParamName");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        final Bitmap bmp = BitmapFactory.decodeFile(photoPath, options);

        Matrix matrix = new Matrix();
        matrix.preRotate(90);
        Bitmap adjustedBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
                bmp.getHeight(), matrix, true);

        ImageView img = (ImageView) findViewById(R.id.imageView1);
        img.setImageBitmap(adjustedBitmap);

    }
}
