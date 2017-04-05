package pub.porco.multipermissions;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import java.util.Locale;

/**
 * Created by porco on 2017/3/30.
 */

public class DefaultConfig {

    public static final int RC_SETTINGS_SCREEN = 0311;  // a beauty's birthday
    private static final String[] settingRationale = {"缺少权限可能导致应用无法正常工作，请去设置界面打开权限。",
            "This app may not work correctly without the requested permissions. Open the app settings screen to modify app permissions."};
    private static final String[] settingTitle = {"权限请求", "Permissions Required"};
    private static final String[] settingPosBtn = {"设置", "Settings"};
    private static final String[] settingNegBtn = {"取消", "Cancel"};

    private static final String[] errorRationale = {"程序出错，缺少相关权限！", "This app is in error, because lack the requested permissions"};
    private static final String[] errorPosBtn = {"确定", "Confirm"};

    public static boolean switcher = false;

    public static AppSettingsDialog.Builder createSettingDialog(Activity activity) {
        AppSettingsDialog.Builder defaultSettingBuilder = new AppSettingsDialog.Builder(activity,getStringByLocale(settingRationale))
                .setTitle(getStringByLocale(settingTitle))
                .setPositiveButton(getStringByLocale(settingPosBtn))
                .setNegativeButton(getStringByLocale(settingNegBtn), null /* click listener */)
                .setRequestCode(RC_SETTINGS_SCREEN);
        return defaultSettingBuilder;
    }

    public static AlertDialog.Builder createErrorDialog(final Activity activity) {
        AlertDialog.Builder errorBuilder = new AlertDialog.Builder(activity)
                .setCancelable(false)
                .setMessage(getStringByLocale(errorRationale))
                .setPositiveButton(getStringByLocale(errorPosBtn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                });
        return  errorBuilder;
    }


    private static String getStringByLocale(String[] strings){
        if (Locale.getDefault().getLanguage().equals("zh")) {
            return getString(strings,0);
        } else {
            return getString(strings,1);
        }
    }

    private static String getString(String[] strings, int index){
        if(index >= strings.length)
            return "";
        else
            return strings[index];
    }
}
