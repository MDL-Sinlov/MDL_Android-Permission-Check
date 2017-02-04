package mdl.sinlov.permission.check;

import android.Manifest;

/**
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
 * Created by sinlov on 17/1/18.
 */
public final class PermissionGroup {
    /**
     * at AndroidManifest.xml set can pass
     */
    public final class OrdinaryGroup {
        public static final String ACCESS_LOCATION_EXTRA_COMMANDS = Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS;
        public static final String ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE;
        public static final String ACCESS_NOTIFICATION_POLICY = Manifest.permission.ACCESS_NOTIFICATION_POLICY;
        public static final String ACCESS_WIFI_STATE = Manifest.permission.ACCESS_WIFI_STATE;
        public static final String BLUETOOTH = Manifest.permission.BLUETOOTH;
        public static final String BLUETOOTH_ADMIN = Manifest.permission.BLUETOOTH_ADMIN;
        public static final String BROADCAST_STICKY = Manifest.permission.BROADCAST_STICKY;
        public static final String CHANGE_NETWORK_STATE = Manifest.permission.CHANGE_NETWORK_STATE;
        public static final String CHANGE_WIFI_MULTICAST_STATE = Manifest.permission.CHANGE_WIFI_MULTICAST_STATE;
        public static final String CHANGE_WIFI_STATE = Manifest.permission.CHANGE_WIFI_STATE;
        public static final String DISABLE_KEYGUARD = Manifest.permission.DISABLE_KEYGUARD;
        public static final String EXPAND_STATUS_BAR = Manifest.permission.EXPAND_STATUS_BAR;
        public static final String FLASHLIGHT = Manifest.permission.FLASHLIGHT;
        public static final String GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
        public static final String GET_PACKAGE_SIZE = Manifest.permission.GET_PACKAGE_SIZE;
        public static final String INTERNET = Manifest.permission.INTERNET;
        public static final String KILL_BACKGROUND_PROCESSES = Manifest.permission.KILL_BACKGROUND_PROCESSES;
        public static final String MODIFY_AUDIO_SETTINGS = Manifest.permission.MODIFY_AUDIO_SETTINGS;
        public static final String NFC = Manifest.permission.NFC;
        public static final String READ_SYNC_SETTINGS = Manifest.permission.READ_SYNC_SETTINGS;
        public static final String READ_SYNC_STATS = Manifest.permission.READ_SYNC_STATS;
        public static final String RECEIVE_BOOT_COMPLETED = Manifest.permission.RECEIVE_BOOT_COMPLETED;
        public static final String REORDER_TASKS = Manifest.permission.REORDER_TASKS;
        public static final String REQUEST_INSTALL_PACKAGES = Manifest.permission.REQUEST_INSTALL_PACKAGES;
        public static final String SET_TIME_ZONE = Manifest.permission.SET_TIME_ZONE;
        public static final String SET_WALLPAPER = Manifest.permission.SET_WALLPAPER;
        public static final String SET_WALLPAPER_HINTS = Manifest.permission.SET_WALLPAPER_HINTS;
        public static final String TRANSMIT_IR = Manifest.permission.TRANSMIT_IR;
        public static final String USE_FINGERPRINT = Manifest.permission.USE_FINGERPRINT;
        public static final String VIBRATE = Manifest.permission.VIBRATE;
        public static final String WAKE_LOCK = Manifest.permission.WAKE_LOCK;
        public static final String WRITE_SYNC_SETTINGS = Manifest.permission.WRITE_SYNC_SETTINGS;
    }

    /**
     * VERSION after M(23) need grant and one is all group
     */
    public final class ContactsGroup {
        public static final String GROUP_NAME = Manifest.permission_group.CONTACTS;
        public static final String WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
        public static final String GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
        public static final String READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    }

    /**
     * VERSION after M(23) need auto grant and one is all group
     */
    public final class PhoneGroup {
        public static final String GROUP_NAME = Manifest.permission_group.PHONE;
        public static final String READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
        public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
        public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;
        public static final String WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
        public static final String USE_SIP = Manifest.permission.USE_SIP;
        public static final String PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;
    }

    /**
     * VERSION after M(23) need auto grant and one is all group
     */
    public final class CalendarGroup {
        public static final String GROUP_NAME = Manifest.permission_group.CALENDAR;
        public static final String CAMERA = Manifest.permission.CAMERA;
    }

    /**
     * VERSION after M(23) need auto grant and one is all group
     */
    public final class SensorsGroup {
        public static final String GROUP_NAME = Manifest.permission_group.SENSORS;
        public static final String BODY_SENSORS = Manifest.permission.BODY_SENSORS;
    }

    /**
     * VERSION after M(23) need grant and one is all group
     */
    public final class LocationGroup {
        public static final String GROUP_NAME = Manifest.permission_group.LOCATION;
        public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
        public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    }

    /**
     * VERSION after M(23) need grant and one is all group
     */
    public final class StorageGroup {
        public static final String GROUP_NAME = Manifest.permission_group.STORAGE;
        public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
        public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    }

    /**
     * VERSION after M(23) need grant and one is all group
     */
    public final class MicrophoneGroup {
        public static final String GROUP_NAME = Manifest.permission_group.MICROPHONE;
        public static final String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    }

    /**
     * VERSION after M(23) need grant and one is all group
     */
    public final class SMSGroup {
        public static final String GROUP_NAME = Manifest.permission_group.SMS;
        public static final String RECORD_AUDIO = Manifest.permission.READ_SMS;
        public static final String RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
        public static final String RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;
        public static final String RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
        public static final String SEND_SMS = Manifest.permission.SEND_SMS;
    }
}
