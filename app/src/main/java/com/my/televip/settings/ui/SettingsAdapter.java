package com.my.televip.settings.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.my.televip.Configs.ConfigItem;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.audio;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.logging.Logger;
import com.my.televip.settings.controller.SettingsController;
import com.my.televip.utils.DialogUtils;
import com.my.televip.utils.Utils;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.androidx.ViewHolder;
import com.my.televip.virtuals.messenger.browser.Browser;
import com.my.televip.virtuals.ui.Cells.ExpandableTextCheckCell;
import com.my.televip.virtuals.ui.Cells.HeaderCell;
import com.my.televip.virtuals.ui.Cells.TextCheckCell;
import com.my.televip.virtuals.ui.Cells.TextInfoCell;
import com.my.televip.virtuals.ui.Cells.TextSettingsCell;

import de.robv.android.xposed.XposedHelpers;

public class SettingsAdapter {

    private static boolean isLongText = false;

    public static int getRow(int position) { return ConfigManager.getItems().get(position).getType(); }

    public static int getRowCount() {
        return ConfigManager.getItems().size();
    }

    public static void onBindViewHolder(Object holder, SettingsController settingsController, int position, int viewType) {
        try {
            ConfigItem item = ConfigManager.getItems().get(position);

            switch (viewType) {
                case ConfigItem.HEADER:
                    HeaderCellHolder headerCell = new HeaderCellHolder(holder);
                    headerCell.cell.setText(Translator.get(item.getKey()));
                    break;

                case ConfigItem.SWITCH:
                    TextCheckCellHolder textCheck = new TextCheckCellHolder(holder);
                    if (item.getValue() != null) {
                        textCheck.cell.setTextAndValueAndCheck(
                                Translator.get(item.getKey()),
                                item.getValue(),
                                item.isEnable(),
                                true,
                                false
                        );
                    } else if (item.isRestartRequired()) {
                        textCheck.cell.setTextAndValueAndCheck(
                                Translator.get(item.getKey()),
                                Translator.get(Keys.RestartRequired),
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
                case ConfigItem.EXPANDABLE_SWITCH:
                    ExpandableTextCheckCellHolder expandableTextCheck = new ExpandableTextCheckCellHolder(holder);
                    expandableTextCheck.cell.addChildren(item);

                    break;
                case ConfigItem.TEXT:
                    TextSettingsCellHolder settingsCell = new TextSettingsCellHolder(holder);
                    if (item.getKey().equals(Keys.Calendar)) {
                        String value = null;
                        switch (item.getCustomCalendar()) {
                            case 0:
                                value = Translator.get(Keys.Gregorian);
                                break;
                            case 1:
                                value = Translator.get(Keys.Hijri);
                                break;
                            case 2:
                                value = Translator.get(Keys.Persian);
                                break;
                        }
                        settingsCell.cell.setTextAndValue(Translator.get(item.getKey()), value, false, false);
                    } else {
                        settingsCell.cell.setText(Translator.get(item.getKey()), false);
                        settingsCell.cell.getTextView().setTextColor(Theme.getTextBlueColor());
                    }
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
                playAudio(settingsController.getContext());
                return true;
            });

            viewHolder.getItemView().setOnClickListener(v -> {

                if (viewType == ConfigItem.SWITCH) {
                    TextCheckCellHolder textCheck = new TextCheckCellHolder(holder);
                    boolean checked = !textCheck.cell.isChecked();
                    textCheck.cell.setChecked(checked);
                    item.setEnable(checked);
                    item.run();
                } else if (viewType == ConfigItem.TEXT) {
                    switch (item.getKey()) {
                        case Keys.DeveloperChannel:
                            Browser.openUrl(settingsController.getContext(), "https://t.me/t_l0_e");
                            settingsController.hide();
                            break;
                        case Keys.RestartApp:
                            Intent intent = settingsController.getContext()
                                    .getPackageManager()
                                    .getLaunchIntentForPackage(Utils.pkgName);

                            if (intent != null) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                settingsController.getContext().startActivity(intent);
                            }

                            ((Activity) settingsController.getContext()).finishAffinity();
                            android.os.Process.killProcess(android.os.Process.myPid());
                            break;
                        case Keys.Calendar:
                            Dialog dlg = DialogUtils.createSingleChoiceDialog((Activity) settingsController.getContext(), new String[]{
                                            Translator.get(Keys.Gregorian), Translator.get(Keys.Hijri), Translator.get(Keys.Persian)},
                                    Translator.get(Keys.Calendar), item.getCustomCalendar(), (dialog, which) -> {
                                        item.setCustomCalendar(which);
                                        item.run();
                                        if (settingsController.settingsActivity.listView.getAdapter() != null) {
                                            settingsController.settingsActivity.listView.getAdapter().notifyItemChanged(position);
                                        }
                                    });
                            dlg.show();
                            break;
                    }
                }
            });

        } catch (Throwable t){
            Logger.e(t);
        }

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
    public static class ExpandableTextCheckCellHolder {
        ExpandableTextCheckCell cell;

        public ExpandableTextCheckCellHolder(Object obj) {
            cell = (ExpandableTextCheckCell) XposedHelpers.getObjectField(obj, "expandableTextCheckCell");
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

    public static void playAudio(Context context) {
        if (audio.playing) {
            audio.stop();
        } else {
            audio.start();
            DialogUtils.showQuranAlert(context);
        }
    }

}
