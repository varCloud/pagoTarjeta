package com.bluecloude.pagotarjeta.Utilerias;

import android.content.Context;
import android.widget.Toast;

public class ToastManager {

    private static ToastManager mInstance = null;
    private Context context;
    private Toast currentToast;

    private ToastManager(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        mInstance = new ToastManager(context);
    }

    public static void toastShort(String message){
        if (mInstance.currentToast != null){
            mInstance.currentToast.cancel();
        }
        mInstance.currentToast = Toast.makeText(mInstance.context, message, Toast.LENGTH_SHORT);
        mInstance.currentToast.show();
    }

    public static void toastLong(String message){
        if (mInstance.currentToast != null){
            mInstance.currentToast.cancel();
        }
        mInstance.currentToast = Toast.makeText(mInstance.context, message, Toast.LENGTH_LONG);
        mInstance.currentToast.show();
    }
}
