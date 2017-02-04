package mdl.sinlov.android.demo.permissioncheck;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdl.sinlov.android.demo.permissioncheck.ui.CheckPermissionActivity;
import mdl.sinlov.android.demo.permissioncheck.ui.FloatWindowPermissionActivity;
import mdl.sinlov.android.demo.permissioncheck.ui.RequestPermissionActivity;

public class MainActivity extends MDLTestActivity {


    @BindView(R.id.tv_main_act_result)
    TextView tvMainActResult;
    @BindView(R.id.btn_main_skip_check_permission)
    Button btnMainSkipCheckPermission;
    @BindView(R.id.btn_main_skip_request_permission)
    Button btnMainSkipRequestPermission;
    @BindView(R.id.btn_main_skip_float_window_permission)
    Button btnMainSkipFloatWindowPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvMainActResult.setText("This Demo for permission check");
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_main_skip_check_permission, R.id.btn_main_skip_request_permission, R.id.btn_main_skip_float_window_permission})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_skip_check_permission:
                skip2Activity(CheckPermissionActivity.class);
                break;
            case R.id.btn_main_skip_request_permission:
                skip2Activity(RequestPermissionActivity.class);
                break;
            case R.id.btn_main_skip_float_window_permission:
                skip2Activity(FloatWindowPermissionActivity.class);
                break;
        }
    }
}
