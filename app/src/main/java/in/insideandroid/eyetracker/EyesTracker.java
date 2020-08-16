package in.insideandroid.eyetracker;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EyesTracker extends Tracker<Face> {
    private final float THRESHOLD = 0.75f;
    private Context context;

    public EyesTracker(Context context) {
        this.context = context;
    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
    //    SimpleDateFormat sdf = new SimpleDateFormat("ss", Locale.getDefault());
    //    String currentDateandTime = sdf.format(new Date());
        if (face.getIsLeftEyeOpenProbability() > THRESHOLD || face.getIsRightEyeOpenProbability() > THRESHOLD) {
            Log.i(TAG, "onUpdate: Open Eyes Detected");

            ((MainActivity)context).updateMainView(Condition.USER_EYES_OPEN);
        }
        else {
            Log.i(TAG, "onUpdate: Close Eyes Detected");

            ((MainActivity)context).updateMainView(Condition.USER_EYES_CLOSED);
        }
    }

    @Override
    public void onMissing(Detector.Detections<Face> detections) {
        super.onMissing(detections);
     //   SimpleDateFormat sdf = new SimpleDateFormat("ss", Locale.getDefault());
     //   String currentDateandTime = sdf.format(new Date());

        Log.i(TAG, "onUpdate: Face Not Detected!");


        ((MainActivity)context).updateMainView(Condition.FACE_NOT_FOUND);
    }

    @Override
    public void onDone() {
        super.onDone();
    }


}
