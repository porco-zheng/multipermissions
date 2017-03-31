package pub.porco.multipermissions;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by porco on 2017/3/29.
 */

public class MultiPermissions {

    private String requestRationale ;
    private int positiveButton = android.R.string.ok;
    private int negativeButton = android.R.string.cancel;
    private boolean isDefault = false;
    private int requestCode;

    private MultiPermissions(){
    }

    public static MultiPermissions get(){
        return new MultiPermissions();
    }

    public MultiPermissions configDefault(boolean isDefault){
        this.isDefault = isDefault;
        return this;
    }

    public MultiPermissions configRequestDialog(int positiveButton, int negativeButton){
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        return this;
    }

    public void needPermissions(@Nullable Object object,int requestCode, String rationale, @NonNull String... perms){
        this.requestRationale = rationale;
        this.requestCode = requestCode;
        DefaultConfig.switcher = isDefault;
        Activity activity = EasyPermissions.getActivity(object);
        if(activity == null)
            return;
        if(!EasyPermissions.hasPermissions(activity, perms)){
            EasyPermissions.requestPermissions(object,requestRationale,positiveButton,negativeButton,requestCode,perms);
        }else{
            EasyPermissions.runAnnotatedGrantMethods(object, requestCode);
        }
    }

}
