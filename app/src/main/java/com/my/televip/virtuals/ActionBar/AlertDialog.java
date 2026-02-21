package com.my.televip.virtuals.ActionBar;

import static com.my.televip.MainHook.lpparam;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Proxy;

import de.robv.android.xposed.XposedHelpers;

public class AlertDialog {

    
        @FunctionalInterface
        public interface OnClick {
            void onClick();
        }

        // دالة تولد Proxy قصير جدًا
        public static Object click(OnClick lambda) {
            Class<?> listenerClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"),
                    lpparam.classLoader
            );
            if (listenerClass != null) {
                return Proxy.newProxyInstance(
                        lpparam.classLoader,
                        new Class[]{listenerClass},
                        (proxy, method, args) -> {
                            // يفترض اسم الدالة onClick
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
        if (loadClass.getAlertDialogBuilderClass() != null) {
            alertDialog = XposedHelpers.newInstance(loadClass.getAlertDialogBuilderClass(), context);
        }
    }

    public void setTitle(CharSequence title){
        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setTitle", AutomationResolver.ResolverType.Method), title);
    }

    public void setView(View view){
        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setView", AutomationResolver.ResolverType.Method), view);
    }

    public void setMessage(CharSequence message){
        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setMessage", AutomationResolver.ResolverType.Method), message);
    }

    public void setPositiveButton(CharSequence text, Object obj){
        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setPositiveButton", AutomationResolver.ResolverType.Method),
                text, obj
        );
    }

    public void setNegativeButton(CharSequence text, Object obj){
        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setNegativeButton", AutomationResolver.ResolverType.Method),
                text, obj
        );
    }

    public void setNeutralButton(CharSequence text, Object obj){
        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setNeutralButton", AutomationResolver.ResolverType.Method),
                text, obj
        );
    }

    public void show(){
        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "show", AutomationResolver.ResolverType.Method));
    }

}
