package mdl.sinlov.android.demo.permissioncheck.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdl.sinlov.android.demo.permissioncheck.MDLTestActivity;
import mdl.sinlov.android.demo.permissioncheck.R;
import mdl.sinlov.permission.check.MDLPermissionUtils;
import mdl.sinlov.permission.check.PermissionGrant;
import mdl.sinlov.permission.check.PermissionGroup;

public class RequestPermissionActivity extends MDLTestActivity {

    @BindView(R.id.tv_request_permission_act_result)
    TextView tvRequestPermissionActResult;
    @BindView(R.id.btn_request_permission_request)
    Button btnRequestPermissionRequest;
    @BindView(R.id.ck_request_permission_access_wifi_state)
    CheckBox ckRequestPermissionAccessWifiState;
    @BindView(R.id.ck_request_permission_use_fingerprint)
    CheckBox ckRequestPermissionUseFingerprint;
    @BindView(R.id.ck_request_permission_access_network_state)
    CheckBox ckRequestPermissionAccessNetworkState;
    @BindView(R.id.ck_request_permission_flashlight)
    CheckBox ckRequestPermissionFlashlight;
    @BindView(R.id.ck_request_permission_read_contacts)
    CheckBox ckRequestPermissionReadContacts;
    @BindView(R.id.ck_request_permission_write_external_storage)
    CheckBox ckRequestPermissionWriteExternalStorage;
    @BindView(R.id.rg_request_permission_act_choose_of_permission)
    RadioGroup rgRequestPermissionActChooseOfPermission;
    @BindView(R.id.btn_request_permission_base_request)
    Button btnRequestPermissionBaseRequest;
    private MDLPermissionUtils mdlPermissionCheck;
    private Set<String> permissionSet;
    private ArrayList<String> permissionArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mdlPermissionCheck = MDLPermissionUtils.getInstance();
        permissionSet = new HashSet<String>();
        permissionArray = new ArrayList<String>();
        ckRequestPermissionAccessNetworkState.setText(PermissionGroup.OrdinaryGroup.ACCESS_NETWORK_STATE);
        ckRequestPermissionAccessWifiState.setText(PermissionGroup.OrdinaryGroup.ACCESS_WIFI_STATE);
        ckRequestPermissionUseFingerprint.setText(PermissionGroup.OrdinaryGroup.USE_FINGERPRINT);
        ckRequestPermissionFlashlight.setText(PermissionGroup.OrdinaryGroup.FLASHLIGHT);
        ckRequestPermissionReadContacts.setText(PermissionGroup.ContactsGroup.READ_CONTACTS);
        ckRequestPermissionWriteExternalStorage.setText(PermissionGroup.StorageGroup.WRITE_EXTERNAL_STORAGE);
        CompoundButton.OnCheckedChangeListener watchPermission = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String newPermission = buttonView.getText().toString().trim();
                if (isChecked) {
                    permissionSet.add(newPermission);
                    permissionArray.add(newPermission);
                } else {
                    permissionSet.remove(newPermission);
                    permissionArray.remove(newPermission);
                }
            }
        };
        ckRequestPermissionAccessNetworkState.setOnCheckedChangeListener(watchPermission);
        ckRequestPermissionAccessWifiState.setOnCheckedChangeListener(watchPermission);
        ckRequestPermissionUseFingerprint.setOnCheckedChangeListener(watchPermission);
        ckRequestPermissionFlashlight.setOnCheckedChangeListener(watchPermission);
        ckRequestPermissionReadContacts.setOnCheckedChangeListener(watchPermission);
        ckRequestPermissionWriteExternalStorage.setOnCheckedChangeListener(watchPermission);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_request_permission_base_request, R.id.btn_request_permission_request})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request_permission_base_request:
                String[] requestPermissions = mdlPermissionCheck.requestPermissions;
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < requestPermissions.length; i++) {
                    mdlPermissionCheck.requestBasePermission(this, i, new PermissionGrant() {
                        @Override
                        public void onPermissionGranted(int requestCode, String permission) {
                            sb.append("requestBasePermission: ");
                            sb.append(permission);
                            sb.append("\n");
                            sb.append("requestCode: ");
                            sb.append(requestCode);
                            sb.append("\n=====\n");
                        }
                    });
                    SystemClock.sleep(1 * 1000);
                }
                tvRequestPermissionActResult.setText(sb.toString());
                break;
            case R.id.btn_request_permission_request:
                if (permissionArray.size() == 0) {
                    tvRequestPermissionActResult.setText("permission is empty, please check");
                } else {
                    String[] permissionArray = new String[permissionSet.size()];
                    permissionSet.toArray(permissionArray);
                    HashMap<String, Integer> tryPermission = new HashMap<String, Integer>();
                    for (int i = 0; i < permissionSet.size(); i++) {
                        tryPermission.put(permissionArray[i], i + 1);
                    }
                    mdlPermissionCheck.requestMultiResult(this, tryPermission, new PermissionGrant() {
                        @Override
                        public void onPermissionGranted(int requestCode, String permission) {

                        }
                    });
                }
                break;
        }
    }
}
