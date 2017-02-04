package mdl.sinlov.android.demo.permissioncheck.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdl.sinlov.android.demo.permissioncheck.MDLTestActivity;
import mdl.sinlov.android.demo.permissioncheck.R;
import mdl.sinlov.android.demo.permissioncheck.UXUtils;
import mdl.sinlov.permission.check.PermissionFloatWindowUtils;

public class FloatWindowPermissionActivity extends MDLTestActivity {

    @BindView(R.id.tv_float_window_permission_act_result)
    TextView tvFloatWindowPermissionActResult;
    @BindView(R.id.btn_float_window_permission_check_permission)
    Button btnFloatWindowPermissionCheckPermission;
    @BindView(R.id.btn_float_window_permission_check_grant)
    Button btnFloatWindowPermissionCheckGrant;
    private PermissionFloatWindowUtils permissionFloatWindowUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window_permission);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        permissionFloatWindowUtils = PermissionFloatWindowUtils.getInstance();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_float_window_permission_check_permission, R.id.btn_float_window_permission_check_grant})
    public void onClick(View view) {
        if (UXUtils.fastRequestShowProgressDialog(this)) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_float_window_permission_check_permission:
                boolean checkResult = permissionFloatWindowUtils.checkCanShowFloatWindow(this);
                if (checkResult) {
                    tvFloatWindowPermissionActResult.setText("This app can show Float Window");
                } else {
                    tvFloatWindowPermissionActResult.setText("Warning this app can not show Float Window");
                }
                break;
            case R.id.btn_float_window_permission_check_grant:
                try {
                    permissionFloatWindowUtils.grantShowFloatWindowPermission(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
