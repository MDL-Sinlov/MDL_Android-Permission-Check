package mdl.sinlov.android.demo.permissioncheck.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdl.sinlov.android.demo.permissioncheck.MDLTestActivity;
import mdl.sinlov.android.demo.permissioncheck.R;
import mdl.sinlov.android.demo.permissioncheck.SingleClickListener;
import mdl.sinlov.android.demo.permissioncheck.UXUtils;
import mdl.sinlov.permission.check.MDLPermissionUtils;
import mdl.sinlov.permission.check.PermissionGroup;

public class CheckPermissionActivity extends MDLTestActivity {

    @BindView(R.id.tv_check_permission_act_result)
    TextView tvCheckPermissionActResult;
    @BindView(R.id.rb_check_permission_access_wifi_state)
    RadioButton rbCheckPermissionAccessWifiState;
    @BindView(R.id.rb_check_permission_use_fingerprint)
    RadioButton rbCheckPermissionUseFingerprint;
    @BindView(R.id.rb_check_permission_access_network_state)
    RadioButton rbCheckPermissionAccessNetworkState;
    @BindView(R.id.rb_check_permission_flashlight)
    RadioButton rbCheckPermissionFlashlight;
    @BindView(R.id.rb_check_permission_read_contacts)
    RadioButton rbCheckPermissionReadContacts;
    @BindView(R.id.rb_check_permission_write_external_storage)
    RadioButton rbCheckPermissionWriteExternalStorage;
    @BindView(R.id.rg_check_permission_act_choose_of_permission)
    RadioGroup rgCheckPermissionActChooseOfPermission;
    @BindView(R.id.btn_check_permission_check_from_manifest)
    Button btnCheckPermissionCheckFromManifest;
    @BindView(R.id.btn_check_permission_check_self_permission)
    Button btnCheckPermissionCheckSelfPermission;
    private MDLPermissionUtils mdlPermissionCheck;
    private String choose_permission;
    private SingleClickListener singleClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permission);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mdlPermissionCheck = MDLPermissionUtils.getInstance();
        rbCheckPermissionAccessNetworkState.setText(PermissionGroup.OrdinaryGroup.ACCESS_NETWORK_STATE);
        rbCheckPermissionAccessWifiState.setText(PermissionGroup.OrdinaryGroup.ACCESS_WIFI_STATE);
        rbCheckPermissionUseFingerprint.setText(PermissionGroup.OrdinaryGroup.USE_FINGERPRINT);
        rbCheckPermissionFlashlight.setText(PermissionGroup.OrdinaryGroup.FLASHLIGHT);
        rbCheckPermissionReadContacts.setText(PermissionGroup.ContactsGroup.READ_CONTACTS);
        rbCheckPermissionWriteExternalStorage.setText(PermissionGroup.StorageGroup.WRITE_EXTERNAL_STORAGE);
        rgCheckPermissionActChooseOfPermission.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectRadioGroup(checkedId);
            }
        });
        singleClickListener = new SingleClickListener(false) {
            @Override
            public boolean doSingleClick(View v) {
                showToast("Single Click test");
                return true;
            }
        };
        tvCheckPermissionActResult.setOnClickListener(singleClickListener);
    }

    private void selectRadioGroup(int checkedId) {
        RadioButton radioButton = (RadioButton) findViewById(checkedId);
        if (radioButton != null) {
            choose_permission = radioButton.getText().toString().trim();
        }
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_check_permission_check_from_manifest, R.id.btn_check_permission_check_self_permission})
    public void onClick(View view) {
        UXUtils.fastRequestShowProgressDialog(this);
        singleClickListener.canClick();
        String checkInfo;
        switch (view.getId()) {
            case R.id.btn_check_permission_check_from_manifest:
                if (TextUtils.isEmpty(choose_permission)) {
                    checkInfo = "You must choose permission";
                } else {
                    boolean has_permission = mdlPermissionCheck.checkFromManifest(getApplication(), choose_permission);
                    if (has_permission) {
                        checkInfo = "checkFromManifest\n [" + choose_permission + "] \nHas this permission!";
                    } else {
                        checkInfo = "checkFromManifest\n [" + choose_permission + "] \n" +
                                "Not has this permission!";
                    }
                }
                tvCheckPermissionActResult.setText(checkInfo);
                break;
            case R.id.btn_check_permission_check_self_permission:
                if (TextUtils.isEmpty(choose_permission)) {
                    checkInfo = "You must choose permission";
                } else {
                    boolean has_permission = mdlPermissionCheck.checkSelfPermission(getApplication(), choose_permission);
                    if (has_permission) {
                        checkInfo = "checkSelfPermission\n [" + choose_permission + "] \nHas this permission!";
                    } else {
                        checkInfo = "checkSelfPermission\n [" + choose_permission + "] \n" +
                                "Not has this permission!";
                    }
                }
                tvCheckPermissionActResult.setText(checkInfo);
                break;
        }
    }
}
