package mdl.sinlov.permission.check;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class MDLPermissionUtils {

    public static final int CODE_MULTI_PERMISSION = 100;
    private boolean debug = true;
    private static final String TAG = MDLPermissionUtils.class.getSimpleName();
    private static MDLPermissionUtils instance;

    public String[] requestPermissions = {
            PermissionGroup.OrdinaryGroup.INTERNET,
            PermissionGroup.OrdinaryGroup.ACCESS_WIFI_STATE,
            PermissionGroup.OrdinaryGroup.CHANGE_NETWORK_STATE,
            PermissionGroup.OrdinaryGroup.KILL_BACKGROUND_PROCESSES,
            PermissionGroup.OrdinaryGroup.VIBRATE,
            PermissionGroup.OrdinaryGroup.ACCESS_NOTIFICATION_POLICY,
            PermissionGroup.StorageGroup.READ_EXTERNAL_STORAGE,
            PermissionGroup.StorageGroup.WRITE_EXTERNAL_STORAGE,
    };

    public static MDLPermissionUtils getInstance() {
        if (null == instance) {
            MDLPermissionUtils.instance = new MDLPermissionUtils();
        }
        return instance;
    }

    private void printDebugInfo(String msg) {
        if (debug) {
            Log.d(TAG, msg);
        }
    }

    /**
     * check permission by AndroidManifest, this way not let APP get permission
     *
     * @param ctx           {@link Context}
     * @param permissionKey {@link String} permission by {@link android.Manifest.permission}
     * @return has or not has
     */
    public boolean checkFromManifest(Context ctx, @NonNull String permissionKey) {
        int checkResult = ctx.checkCallingOrSelfPermission(permissionKey);
        return PackageManager.PERMISSION_GRANTED == checkResult;
    }

    /**
     * check permission by content
     *
     * @param ctx           {@link Context}
     * @param permissionKey {@link String} permission by {@link android.Manifest.permission}
     * @return has or not has
     */
    public boolean checkSelfPermission(Context ctx, @NonNull String permissionKey) {
        int hasPermission = ContextCompat.checkSelfPermission(ctx, permissionKey);
        return PackageManager.PERMISSION_GRANTED == hasPermission;
    }

    /**
     * use this just by multi-permission need, if ROM not allow, this will let user open setting
     *
     * @param activity        {@link Activity}
     * @param multiPermission {@link HashMap} key is permission name, value is requestCode,
     *                        if code is {@link PackageManager#PERMISSION_GRANTED} it will not granted.
     * @param permissionGrant {@link PermissionGrant}
     */
    public void requestMultiResult(Activity activity, HashMap<String, Integer> multiPermission, PermissionGrant permissionGrant) {
        if (activity == null) {
            new NullPointerException("you must set activity!").printStackTrace();
            return;
        }
        ArrayList<String> notGranted = new ArrayList<>();
        for (Map.Entry<String, Integer> permission : multiPermission.entrySet()) {
            if (permission.getValue() != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permission.getKey());
            }
        }
        if (notGranted.size() == 0) {
            printDebugInfo("all permission success");
            permissionGrant.onPermissionGranted(CODE_MULTI_PERMISSION, "all permission success");
        } else {
            openSettingActivity(activity, activity.getString(R.string.fast_dialog_open_permission_content));
        }

    }

    /**
     * Requests permission. if mobile SDK_INT < M(23) ActivityCompat.checkSelfPermission() must be PERMISSION_GRANTED
     * <br/>And if use close you permission, APP will crash by java.lang.RuntimeException: Unknown exception code: 1 msg null
     * <br/>So if SDK_INT < M, this method return fast than do you want at permissionGrant.onPermissionGranted(requestCode)
     *
     * @param activity  {@link Activity}
     * @param subscript request code, e.g. if you need request
     *                  {@link mdl.sinlov.permission.check.PermissionGroup.OrdinaryGroup#INTERNET},
     *                  use subscript of this in {@link MDLPermissionUtils#requestMultiResult(Activity, HashMap, PermissionGrant)}
     */
    public void requestBasePermission(final Activity activity, final int subscript, PermissionGrant permissionGrant) {
        if (activity == null) {
            return;
        }

        printDebugInfo("requestBasePermission requestCode:" + subscript);
        if (subscript < 0 || subscript >= requestPermissions.length) {
            Log.w(TAG, "requestBasePermission illegal requestCode:" + subscript);
            return;
        }

        final String requestPermission = requestPermissions[subscript];

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGrant.onPermissionGranted(subscript, requestPermission);
            return;
        }

        int checkSelfPermission = PackageManager.PERMISSION_DENIED;
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
        } catch (RuntimeException e) {
            permissionGrant.onPermissionGranted(checkSelfPermission, requestPermission);
            Log.e(TAG, "RuntimeException:" + e.getMessage());
            return;
        }

        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                printDebugInfo("requestBasePermission shouldShowRequestPermissionRationale");
                shouldShowRationale(activity, subscript, requestPermission);
            } else {
                printDebugInfo("requestCameraPermission else");
                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, subscript);
            }

        } else {
            printDebugInfo("ActivityCompat.checkSelfPermission ==== PackageManager.PERMISSION_GRANTED");
            permissionGrant.onPermissionGranted(subscript, requestPermissions[subscript]);
        }
    }

    private void shouldShowRationale(final Activity activity, final int requestCode, final String requestPermission) {
        String[] permissionsHint = activity.getResources().getStringArray(R.array.check_permissions);
        showMessageOKCancel(activity, "Rationale: " + permissionsHint[requestCode], new FastAlertDialog.OnDialogClickListener() {
            @Override
            public void onClick(FastAlertDialog fastAlertDialog) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{requestPermission},
                        requestCode);
                printDebugInfo("showMessageOKCancel requestPermissions:" + requestPermission);
            }
        });
    }

    private void showMessageOKCancel(final Activity activity, String message, FastAlertDialog.OnDialogClickListener confirmListener) {
        new FastAlertDialog(activity, FastAlertDialog.WARNING_TYPE)
                .setTitleText(activity.getString(R.string.fast_dialog_open_permission))
                .setContentText(message)
                .setConfirmClickListener(confirmListener)
                .showCancelButton(true)
                .setCancelClickListener(new FastAlertDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(FastAlertDialog fastAlertDialog) {
                        fastAlertDialog.dismiss();
                    }
                })
                .show();
    }

    private void openSettingActivity(final Activity activity, String message) {
        openPermissionDialog(activity, message);
    }

    public void openPermissionDialog(final Activity activity, String msg) {
        if (TextUtils.isEmpty(msg)) {
            new FastAlertDialog(activity, FastAlertDialog.WARNING_TYPE)
                    .setContentText(activity.getString(R.string.fast_dialog_open_permission))
                    .showCancelButton(true)
                    .setConfirmClickListener(new FastAlertDialog.OnDialogClickListener() {
                        @Override
                        public void onClick(FastAlertDialog fastAlertDialog) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                            intent.setData(uri);
                            activity.startActivity(intent);
                            fastAlertDialog.dismiss();
                        }
                    })
                    .setCancelClickListener(new FastAlertDialog.OnDialogClickListener() {
                        @Override
                        public void onClick(FastAlertDialog fastAlertDialog) {
                            fastAlertDialog.dismiss();
                        }
                    })
                    .show();
        } else {
            new FastAlertDialog(activity, FastAlertDialog.WARNING_TYPE)
                    .setTitleText(activity.getString(R.string.fast_dialog_open_permission))
                    .setContentText(msg)
                    .showCancelButton(true)
                    .setConfirmClickListener(new FastAlertDialog.OnDialogClickListener() {
                        @Override
                        public void onClick(FastAlertDialog fastAlertDialog) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                            intent.setData(uri);
                            activity.startActivity(intent);
                            fastAlertDialog.dismiss();
                        }
                    })
                    .setCancelClickListener(new FastAlertDialog.OnDialogClickListener() {
                        @Override
                        public void onClick(FastAlertDialog fastAlertDialog) {
                            fastAlertDialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    private MDLPermissionUtils() {
    }
}
