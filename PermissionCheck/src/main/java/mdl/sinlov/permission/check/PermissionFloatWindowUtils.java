package mdl.sinlov.permission.check;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * this utils of Float window which Android SDK above of M(23)
 * <pre>
 *     sinlov
 *
 *     /\__/\
 *    /`    '\
 *  ≈≈≈ 0  0 ≈≈≈ Hello world!
 *    \  --  /
 *   /        \
 *  /          \
 * |            |
 *  \  ||  ||  /
 *   \_oo__oo_/≡≡≡≡≡≡≡≡o
 *
 * </pre>
 * Created by sinlov on 17/1/20.
 */
public class PermissionFloatWindowUtils {

    public static final int REQUEST_OVERLAY_PERMISSION = 9088;
    private static PermissionFloatWindowUtils instance;

    /**
     * if your Android SDK version great than M {API 23} can be use {@link Settings#canDrawOverlays(Context)}
     *
     * @param act {@link Activity}
     * @return can show float window or not!
     */
    public boolean checkCanShowFloatWindow(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(act);
        } else {
            return true;
        }
    }

    /**
     * If your Android SDK verison less M{API 23}, this method will crash your APP!
     *
     * @param act {@link Activity}
     */
    public void grantShowFloatWindowPermission(Activity act) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            throw new RuntimeException("you Android sdk version is below M {API23}, not need grant this permission");
        } else {
            if (!Settings.canDrawOverlays(act)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + act.getPackageName()));
                act.startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
            }
        }
    }

    private void requestCapturePermission(Activity act) {
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager)
                act.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            act.startActivityForResult(
                    mediaProjectionManager.createScreenCaptureIntent(),
                    REQUEST_OVERLAY_PERMISSION);
        }
    }

    public static PermissionFloatWindowUtils getInstance() {
        if (null == instance) {
            instance = new PermissionFloatWindowUtils();
        }
        return instance;
    }

    private PermissionFloatWindowUtils() {
    }
}
