package com.my.televip.virtuals.ActionBar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.my.televip.Class.ClassNames;
import com.my.televip.utils.Utils;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Proxy;

import com.my.televip.reflect.XReflect;

public class AlertDialog {


    @FunctionalInterface
    public interface OnClick {
        void onClick();
    }

    public static Object click(OnClick lambda) {
        Class<?> listenerClass = ClassLoad.getClass(ClassNames.ALERT_DIALOG_BUTTON_CLICK);
        if (listenerClass != null) {
            return Proxy.newProxyInstance(
                    Utils.classLoader,
                    new Class[]{listenerClass},
                    (proxy, method, args) -> {
                        if (method.getName().equals(AutomationResolver.resolve("AlertDialog$OnButtonClickListener", "onClick", AutomationResolver.ResolverType.Method))) {
                            lambda.onClick();
                        }
                        return null;
                    }
            );
        } else {
            return (DialogInterface.OnClickListener) (dialog, which) -> lambda.onClick();
        }
    }

    Object alertDialog;

    public AlertDialog(Context context) {
        alertDialog = XReflect.newInstance(ClassLoad.getClass(ClassNames.ALERT_DIALOG_BUILDER), context);
    }

    public void setTitle(CharSequence title) {
        XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "setTitle", AutomationResolver.ResolverType.Method), title);
    }

    public void setView(View view) {
        XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "setView", AutomationResolver.ResolverType.Method), view);
    }

    public void setMessage(CharSequence message) {
        XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "setMessage", AutomationResolver.ResolverType.Method), message);
    }

    public void setPositiveButton(CharSequence text, Object obj) {
        XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "setPositiveButton", AutomationResolver.ResolverType.Method),
                text, obj
        );
    }

    public void setNegativeButton(CharSequence text, Object obj) {
        XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "setNegativeButton", AutomationResolver.ResolverType.Method),
                text, obj
        );
    }

    public void setNeutralButton(CharSequence text, Object obj) {
        XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "setNeutralButton", AutomationResolver.ResolverType.Method),
                text, obj
        );
    }

    public void show() {
        XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "show", AutomationResolver.ResolverType.Method));
    }

    public Dialog create() {
        return (Dialog) XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "create", AutomationResolver.ResolverType.Method));
    }

    public Runnable getDismissRunnable() {
        return (Runnable) XReflect.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog$Builder", "getDismissRunnable", AutomationResolver.ResolverType.Method));
    }

}
