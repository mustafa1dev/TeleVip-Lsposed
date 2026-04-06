package com.my.televip.settings.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.my.televip.Configs.ConfigItem;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.Utils;
import com.my.televip.audio;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.settings.controller.SettingsController;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.androidx.ViewHolder;
import com.my.televip.virtuals.messenger.browser.Browser;
import com.my.televip.virtuals.ui.Cells.HeaderCell;
import com.my.televip.virtuals.ui.Cells.TextCheckCell;
import com.my.televip.virtuals.ui.Cells.TextInfoCell;
import com.my.televip.virtuals.ui.Cells.TextSettingsCell;

import java.util.List;

import de.robv.android.xposed.XposedHelpers;

public class SettingsAdapter {

    public static List<ConfigItem> items;

    private static boolean isLongText = false;


    public static int getRow(int position) { return items.get(position).getType(); }

    public static int getRowCount() {
        return items.size();
    }

    public static void onBindViewHolder(Context context, Object holder, SettingsController settingsController, int position, int viewType) {
        ConfigItem item = items.get(position);

        switch (viewType) {
            case ConfigItem.HEADER:
                HeaderCellHolder headerCell = new HeaderCellHolder(holder);
                headerCell.cell.setText(Translator.get(item.getKey()));
                break;

            case ConfigItem.SWITCH:
                TextCheckCellHolder textCheck = new TextCheckCellHolder(holder);
                if (item.getText() != null) {
                    textCheck.cell.setTextAndValueAndCheck(
                            Translator.get(item.getKey()),
                            item.getText(),
                            item.isEnable(),
                            true,
                            false
                    );
                } else {
                    textCheck.cell.setTextAndCheck(
                            Translator.get(item.getKey()),
                            item.isEnable(),
                            false
                    );
                }
                textCheck.cell.getTextView().setLines(0);
                textCheck.cell.getTextView().setMaxLines(0);
                textCheck.cell.getTextView().setSingleLine(false);
                textCheck.cell.getTextView().setEllipsize(null);
                break;
            case ConfigItem.BUTTON:
                TextSettingsCellHolder settingsCell = new TextSettingsCellHolder(holder);
                if (item.getKey().equals(Keys.DeveloperChannel)) {
                    settingsCell.cell.setText(Translator.get(Keys.DeveloperChannel), false);
                } else if (item.getKey().equals(Keys.RestartApp)) {
                    settingsCell.cell.setText(Translator.get(Keys.RestartApp), false);
                }
                settingsCell.cell.getTextView().setTextColor(Theme.getTextBlueColor());
                break;
            case ConfigItem.DIVIDER:
                ShadowSectionCellHolder shadowSectionCell = new ShadowSectionCellHolder(holder);
                shadowSectionCell.cell.setBackgroundColor((Theme.getBackgroundGrayColor()));
                break;
            case ConfigItem.INFO:
                TextInfoCellHolder textInfoCell = new TextInfoCellHolder(holder);
                TextView textView = textInfoCell.text.getTextView();
                if (item.getKey().equals(Keys.OfflineVisibilityInfo)) {
                    textView.setMaxLines(2);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    textView.setText(Translator.get(Keys.OfflineVisibilityInfo));
                    if (textView.getMaxLines() == 2) {
                        isLongText = false;
                    }
                    textView.setOnClickListener(v -> {
                        if (!isLongText) {
                            textView.setMaxLines(Integer.MAX_VALUE);
                            textView.setEllipsize(null);
                            isLongText = true;
                        } else {
                            textView.setMaxLines(2);
                            textView.setEllipsize(TextUtils.TruncateAt.END);
                            textView.setText(Translator.get(Keys.OfflineVisibilityInfo));
                            isLongText = false;
                        }
                    });
                }
                break;
        }
        ViewHolder viewHolder = new ViewHolder(holder);

        viewHolder.getItemView().setOnLongClickListener(v -> {

            if (audio.playing) {
                audio.stop();
            } else {
                audio.start();
            }

            return true;
        });

        viewHolder.getItemView().setOnClickListener(v -> {
            int pos = viewHolder.getAdapterPosition();
            ConfigItem featureItem = items.get(pos);

            if (viewType == ConfigItem.SWITCH) {
                TextCheckCellHolder textCheck = new TextCheckCellHolder(holder);
                boolean checked = !textCheck.cell.isChecked();
                textCheck.cell.setChecked(checked);
                featureItem.setEnable(checked);
                ConfigManager.load(context);
                featureItem.getRunnable().run();
            } else if (viewType == ConfigItem.BUTTON) {
                if (featureItem.getKey().equals(Keys.DeveloperChannel)) {
                    Browser.openUrl(context, "https://t.me/t_l0_e");
                    settingsController.hide();
                } else if (featureItem.getKey().equals(Keys.RestartApp)) {
                    Intent intent = context
                            .getPackageManager()
                            .getLaunchIntentForPackage(Utils.pkgName);

                    if (intent != null) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }

                    ((Activity)context).finishAffinity();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        });

    }

    public static class HeaderCellHolder {
        HeaderCell cell;

        public HeaderCellHolder(Object obj) {
            cell = new HeaderCell(XposedHelpers.getObjectField(obj, "headerCell"));
        }
    }

    public static class TextCheckCellHolder {
        TextCheckCell cell;

        public TextCheckCellHolder(Object obj) {
            cell = new TextCheckCell(XposedHelpers.getObjectField(obj, "textCheckCell"));
        }
    }

    public static class TextSettingsCellHolder {
        TextSettingsCell cell;

        public TextSettingsCellHolder(Object obj) {
            cell = new TextSettingsCell(XposedHelpers.getObjectField(obj, "textSettingsCell"));
        }
    }

    public static class ShadowSectionCellHolder {
        View cell;

        public ShadowSectionCellHolder(Object obj) {
            cell = (View) XposedHelpers.getObjectField(obj, "view");
        }
    }

    public static class TextInfoCellHolder {
        TextInfoCell text;

        public TextInfoCellHolder(Object obj) {
            text = (TextInfoCell) XposedHelpers.getObjectField(obj, "view");
        }
    }

}
