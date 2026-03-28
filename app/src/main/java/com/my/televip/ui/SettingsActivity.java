package com.my.televip.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.my.televip.ClientChecker;
import com.my.televip.Drawable.ArrowDrawable;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.audio;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.ActiveTheme;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.androidx.ViewHolder;
import com.my.televip.virtuals.messenger.browser.Browser;
import com.my.televip.virtuals.ui.Cells.HeaderCell;
import com.my.televip.virtuals.ui.Cells.TextCheckCell;
import com.my.televip.virtuals.ui.Cells.TextSettingsCell;
import com.my.televip.virtuals.ui.Components.RecyclerListView;
import com.my.televip.virtuals.ui.LaunchActivity;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XposedHelpers;

public class SettingsActivity {

    private static boolean isSettings;

    private static int rowCount;
    private final Context context;

    private static int ghostModeRow;
    private static int storiesRow;
    private static int messagesRow;
    private static int mediaRow;
    private static int otherFeaturesRow;

    private static int hideSeenRow;
    private static int hideStoryViewRow;
    private static int hideOnlineRow;
    private static int hidePhoneRow;
    private static int hideTypingRow;
    private static int HideUpdateAppRow;

    private static int disableStoriesRow;
    private static int disableNumberRoundingRow;

    private static int showDeletedMessagesRow;
    private static int showMessageIDRow;
    private static int markReadAfterSendRow;
    private static int SaveEditsHistoryRow;

    private static int preventMediaRow;
    private static int unlockAllRestrictedRow;
    private static int allowSavingvideosRow;

    private static int telegramPremiumRow;
    private static int fixTLErrorRow;

    private static int ConnectionsRow;
    private static int DownloadSpeedRow;

    private static final List<Integer> shadowRows = new ArrayList<>();

    private static int btnChannelRow;
    private static int btnRestartAppRow;

    public RecyclerListView listView;

    private void updateRow(){
        rowCount = 0;

        // GhostMode
        ghostModeRow = rowCount++;

        hideSeenRow = rowCount++;
        markReadAfterSendRow = rowCount++;
        hideOnlineRow = rowCount++;
        hideTypingRow = rowCount++;
        hideStoryViewRow = rowCount++;
        hidePhoneRow = rowCount++;


        shadowRows.add(rowCount++);

        // Stories
        storiesRow = rowCount++;

        disableStoriesRow = rowCount++;


        shadowRows.add(rowCount++);

        // Messages
        messagesRow = rowCount++;

        showDeletedMessagesRow = rowCount++;
        if (!ClientChecker.check(ClientChecker.ClientType.NagramX)) {
            showMessageIDRow = rowCount++;
        }
        if (!ClientChecker.check(ClientChecker.ClientType.Teegra)) {
            SaveEditsHistoryRow = rowCount++;
        }

        shadowRows.add(rowCount++);

        //Connections
        ConnectionsRow = rowCount++;
        DownloadSpeedRow = rowCount++;

        shadowRows.add(rowCount++);

        // Media
        mediaRow = rowCount++;

        preventMediaRow = rowCount++;
        allowSavingvideosRow = rowCount++;


        shadowRows.add(rowCount++);

        // Other Features
        otherFeaturesRow = rowCount++;

        unlockAllRestrictedRow = rowCount++;
        telegramPremiumRow = rowCount++;
        if (!ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
            disableNumberRoundingRow = rowCount++;
            HideUpdateAppRow = rowCount++;
            fixTLErrorRow = rowCount++;
        }

        shadowRows.add(rowCount++);

        btnChannelRow = rowCount++;

        shadowRows.add(rowCount++);

        btnRestartAppRow = rowCount++;

        shadowRows.add(rowCount++);
    }

    public View createView() {
        updateRow();

        LinearLayout fragmentView = new LinearLayout(context);
        fragmentView.setOrientation(LinearLayout.VERTICAL);
        fragmentView.setBackgroundColor(Theme.getColor(Theme.getKey_windowBackgroundGray()));
        try {

            ToolBar toolbar = new ToolBar(context);

            toolbar.setColorTitle(Theme.getColor(Theme.getKey_actionBarDefaultTitle()));
            toolbar.setTextTitle(Translator.get(Keys.GHOST_MODE));

            ArrowDrawable arrow = new ArrowDrawable();
            toolbar.setImageDrawable(arrow);
            toolbar.getImage().setOnClickListener(v -> hide());

            fragmentView.addView(toolbar);

            listView = new RecyclerListView(context);

            Object adapter = XposedHelpers.newInstance(
                    loadClass.getSettingsAdapter$ListAdapterClass(),
                    context);

            listView.setAdapter(adapter);

            if (!ActiveTheme.getActiveTheme()) {
                listView.setBackgroundColor(Color.rgb(29, 39, 51));
            } else {
                listView.setBackgroundColor(Color.rgb(255, 255, 255));
            }

            listView.setVerticalScrollBarEnabled(false);
            listView.setLayoutManager(Bridge.getLayoutManager(context));

            LinearLayout.LayoutParams recyclerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1f
            );
            recyclerParams.setMargins(10, 10, 10, 0);

            fragmentView.addView(listView.getRecyclerListView(), recyclerParams);
            if (!FeatureManager.getBoolean(Keys.OFFLINE_VISIBILITY_INFO)) {
                listView.post(() -> showTargetForItem(hideOnlineRow));
            }

        } catch (Exception e){
            Utils.log(e);
        }

        return fragmentView;
    }

    private void showTargetForItem(int position) {
        try {
        listView.scrollToPosition(position);

        listView.post(() -> {
            ViewHolder holder =
                    listView.findViewHolderForAdapterPosition(position);

            if (holder == null) return;

            View target = holder.getItemView();

            if (target != null) {
                showTap(target);
            }
        });
        } catch (Exception e){
            Utils.log(e);
        }
    }

    private void showTap(View view) {
        try {
            int outerColor;
            int textColor;
            int descColor;
            int dimColor;


            if (ActiveTheme.getActiveTheme()) {
                // ☀️ Light Mode
                outerColor = 0xFFF2F2F2;
                textColor = 0xFF111111;
                descColor = 0xFF444455;
                dimColor = 0x66000000;
            } else {
                // 🌙 Dark Mode
                outerColor = 0xFF1F2A38;
                textColor = 0xFFFFFFFF;
                descColor = 0xFFB0C4DE;
                dimColor = 0xAA000000;
            }
            TapTargetView.showFor(
                    MainHook.launchActivity,
                    TapTarget.forView(view, Translator.get(Keys.HIDE_ONLINE), Translator.get(Keys.OFFLINE_VISIBILITY_INFO))
                            .outerCircleColorInt(outerColor)
                            .targetCircleColorInt(0xFFFFFFFF)
                            .titleTextColorInt(textColor)
                            .descriptionTextColorInt(descColor)
                            .dimColorInt(dimColor)
                            .drawShadow(true)
                            .cancelable(false)
                            .titleTextSize(16)
                            .descriptionTextSize(14)
                            .transparentTarget(true),
                    new TapTargetView.Listener() {
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            FeatureManager.putBoolean(Keys.OFFLINE_VISIBILITY_INFO, true);
                            super.onTargetClick(view);
                        }
                    }
            );
        } catch (Exception e){
            Utils.log(e);
        }
    }

    public SettingsActivity(Context context) {
        this.context = context;
        try {
            isSettings = true;

            if (!FeatureManager.getBoolean("DontShowAgain")) {
                AlertDialog alertDialog = new AlertDialog(context);

                alertDialog.setTitle(Translator.get(Keys.GHOST_MODE));
                alertDialog.setMessage(Translator.get(Keys.JOIN_TELEVIP));

                alertDialog.setPositiveButton(Translator.get(Keys.JOIN), AlertDialog.click(() -> {
                    Browser.openUrl("https://t.me/t_l0_e");
                    hide();
                }));

                alertDialog.setNegativeButton(Translator.get(Keys.CANCEL), null);
                alertDialog.setNeutralButton(Translator.get(Keys.DONT_SHOW_AGAIN), AlertDialog.click(() -> FeatureManager.putBoolean("DontShowAgain", true)));
                alertDialog.show();
            }
        } catch (Exception e){
            Utils.log(e);
        }
    }


    public static void init(){
        audio.init();
        try {

            XposedHelpers.findAndHookMethod(loadClass.getLaunchActivityClass(), "onBackPressed", new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (isSettings) {
                        hide();
                        addItem.settings = null;
                        param.setResult(null);
                    }
                }
            });

            XposedHelpers.findAndHookMethod(loadClass.getAndroidUtilitiesClass(), AutomationResolver.resolve("AndroidUtilities", "isTablet", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (isSettings) {
                        param.setResult(true);
                    }
                }
            });
        } catch (Exception e){
            Utils.log(e);
        }
    }


    public static void hide() {
        LaunchActivity launchActivity = new LaunchActivity(MainHook.launchActivity);
        for (int i = 0; i < launchActivity.frameLayout.getChildCount(); i++) {
            View child = launchActivity.frameLayout.getChildAt(i);
            child.setVisibility(child == addItem.settings ? View.GONE : View.VISIBLE);
        }

        if (addItem.settings != null && addItem.settings.getParent() != null) {
            launchActivity.frameLayout.removeView(addItem.settings);
        }
        isSettings = false;
    }

    public static int getRowCount(){
        return rowCount;
    }

    public static int getRow(int position){
        if (position == ghostModeRow || position == storiesRow || position == messagesRow || position == mediaRow || position == otherFeaturesRow || position == ConnectionsRow) {
            return 0;
        } else if (position == hideSeenRow || position == hideStoryViewRow || position == hideOnlineRow ||
                position == hidePhoneRow || position == hideTypingRow || position == disableStoriesRow || disableNumberRoundingRow == position || position == showDeletedMessagesRow || position == preventMediaRow ||
                position == unlockAllRestrictedRow || position == allowSavingvideosRow || position == telegramPremiumRow || position == HideUpdateAppRow ||
                position == SaveEditsHistoryRow || position == fixTLErrorRow || position == showMessageIDRow || position == DownloadSpeedRow || position == markReadAfterSendRow) {
            return 1;
        } else if (position == btnChannelRow || position == btnRestartAppRow) {
            return 2;
        } else if (shadowRows.contains(position)) {
            return 3;
        }
        return 0;
    }

    public static void onBindViewHolder(Object holder, int position, int viewType) {
        switch (viewType) {
            case 0:
                HeaderCellHolder headerCell = new HeaderCellHolder(holder);
                if (position == ghostModeRow) {
                    headerCell.cell.setText(Translator.get(Keys.GHOST_MODE_SETTINGS));
                } else if (position == storiesRow) {
                    headerCell.cell.setText(Translator.get(Keys.STORIES_SETTINGS));
                } else if (position == messagesRow) {
                    headerCell.cell.setText(Translator.get(Keys.MESSAGES_SETTINGS));
                } else if (position == mediaRow) {
                    headerCell.cell.setText(Translator.get(Keys.MEDIA_SETTINGS));
                } else if (position == otherFeaturesRow) {
                    headerCell.cell.setText(Translator.get(Keys.OTHER_FEATURES_SETTINGS));
                } else if (position == ConnectionsRow) {
                    headerCell.cell.setText(Translator.get(Keys.CONNECTIONS_SETTINGS));
                }
                break;
            case 1:
                TextCheckCellHolder ch = new TextCheckCellHolder(holder);
                if (position == hideSeenRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.HIDE_SEEN), FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN), false);
                } else if (position == hideStoryViewRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.HIDE_STORY_VIEW), FeatureManager.getBoolean(FeatureManager.KEY_HIDE_STORY_READ), false);
                } else if (position == hideOnlineRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.HIDE_ONLINE), Translator.get(Keys.RESTART_REQUIRED), FeatureManager.getBoolean(FeatureManager.KEY_HIDE_ONLINE), true, false);
                } else if (position == hidePhoneRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.HIDE_PHONE), Translator.get(Keys.RESTART_REQUIRED), FeatureManager.getBoolean(FeatureManager.KEY_HIDE_PHONE), true, false);
                } else if (position == hideTypingRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.HIDE_TYPING), FeatureManager.getBoolean(FeatureManager.KEY_HIDE_TYPING), false);
                } else if (position == HideUpdateAppRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.HIDE_UPDATE_APP), Translator.get(Keys.RESTART_REQUIRED), FeatureManager.getBoolean(FeatureManager.KEY_HIDE_UPDATE_APP), true, false);
                } else if (position == disableStoriesRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.DISABLE_STORIES), Translator.get(Keys.RESTART_REQUIRED), FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_STORIES), true, false);
                } else if (position == disableNumberRoundingRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.DISABLE_NUMBER_ROUNDING), "5.3K -> 5300", FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_NUMBER_ROUNDING), true, false);
                } else if (position == showMessageIDRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.SHOW_MESSAGE_ID), FeatureManager.getBoolean(FeatureManager.KEY_SHOW_MESSAGE_ID), false);
                } else if (position == showDeletedMessagesRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.SHOW_DELETED_MESSAGES), FeatureManager.getBoolean(FeatureManager.KEY_SHOW_DELETED), false);
                } else if (position == SaveEditsHistoryRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.SAVE_EDITS_HISTORY), FeatureManager.getBoolean(FeatureManager.KEY_SAVE_EDITS_HISTORY), false);
                } else if (position == preventMediaRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.PREVENT_MEDIA), FeatureManager.getBoolean(FeatureManager.KEY_PREVENT_MEDIA), false);
                } else if (position == unlockAllRestrictedRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.UNLOCK_ALL_RESTRICTED), FeatureManager.getBoolean(FeatureManager.KEY_UNLOCK_CHANNEL), false);
                } else if (position == allowSavingvideosRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.ALLOW_SAVING_VIDEOS), FeatureManager.getBoolean(FeatureManager.KEY_ALLOW_SAVE_GALLERY), false);
                } else if (position == telegramPremiumRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.TELEGRAM_PREMIUM), FeatureManager.getBoolean(FeatureManager.KEY_TELE_PREMIUM), false);
                } else if (position == fixTLErrorRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.FIX_TL_ERROR), FeatureManager.getBoolean(FeatureManager.KEY_FIX_TL_ERROR), false);
                } else if (position == DownloadSpeedRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.DOWNLOAD_SPEED), FeatureManager.getBoolean(FeatureManager.KEY_DOWNLOAD_SPEED), false);
                } else if (position == markReadAfterSendRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.MARK_READ_AFTER_SEND), FeatureManager.getBoolean(FeatureManager.KEY_MARK_READ_AFTER_SEND), false);
                }
                ch.cell.getTextView().setLines(0);
                ch.cell.getTextView().setMaxLines(0);
                ch.cell.getTextView().setSingleLine(false);
                ch.cell.getTextView().setEllipsize(null);
                break;

            case 2:
                TextSettingsCellHolder settingsCell = new TextSettingsCellHolder(holder);
                if (position == btnChannelRow) {
                    settingsCell.cell.setText(Translator.get(Keys.DEVELOPER_CHANNEL), false);
                } else if (position == btnRestartAppRow) {
                    settingsCell.cell.setText(Translator.get(Keys.RESTART_APP), false);
                }
                settingsCell.cell.getTextView().setTextColor(Theme.getColor(Theme.getKey_switchTrackBlueChecked()));
                break;
            case 3:
                ShadowSectionCellHolder shadowSectionCell = new ShadowSectionCellHolder(holder);
                shadowSectionCell.cell.setBackgroundColor(Theme.getColor(Theme.getKey_windowBackgroundGray()));
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

            if (viewType == 1) {

                TextCheckCellHolder ch = new TextCheckCellHolder(holder);
                boolean checked = !ch.cell.isChecked();
                ch.cell.setChecked(checked);

                if (pos == hideSeenRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_HIDE_SEEN, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_HIDE_SEEN);
                } else if (pos == hideStoryViewRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_HIDE_STORY_READ, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_HIDE_STORY_READ);
                } else if (pos == hideOnlineRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_HIDE_ONLINE, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_HIDE_ONLINE);
                } else if (pos == hidePhoneRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_HIDE_PHONE, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_HIDE_PHONE);
                } else if (pos == hideTypingRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_HIDE_TYPING, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_HIDE_TYPING);
                } else if (pos == HideUpdateAppRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_HIDE_UPDATE_APP, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_HIDE_UPDATE_APP);
                } else if (pos == disableStoriesRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_DISABLE_STORIES, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_DISABLE_STORIES);
                } else if (pos == showDeletedMessagesRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_SHOW_DELETED, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_SHOW_DELETED);
                } else if (pos == SaveEditsHistoryRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_SAVE_EDITS_HISTORY, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_SAVE_EDITS_HISTORY);
                } else if (pos == preventMediaRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_PREVENT_MEDIA, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_PREVENT_MEDIA);
                } else if (pos == unlockAllRestrictedRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_UNLOCK_CHANNEL, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_UNLOCK_CHANNEL);
                } else if (pos == allowSavingvideosRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_ALLOW_SAVE_GALLERY, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_ALLOW_SAVE_GALLERY);
                } else if (pos == telegramPremiumRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_TELE_PREMIUM, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_TELE_PREMIUM);
                } else if (pos == disableNumberRoundingRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_DISABLE_NUMBER_ROUNDING, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_DISABLE_NUMBER_ROUNDING);
                } else if (pos == fixTLErrorRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_FIX_TL_ERROR, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_FIX_TL_ERROR);
                } else if (pos == showMessageIDRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_SHOW_MESSAGE_ID, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_SHOW_MESSAGE_ID);
                } else if (pos == DownloadSpeedRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_DOWNLOAD_SPEED, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_DOWNLOAD_SPEED);
                } else if (pos == markReadAfterSendRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_MARK_READ_AFTER_SEND, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_MARK_READ_AFTER_SEND);
                }

            } else if (viewType == 2) {
                if (pos == btnChannelRow) {
                    Browser.openUrl("https://t.me/t_l0_e");
                    hide();
                } else if (pos == btnRestartAppRow) {

                    Intent intent = MainHook.launchActivity
                            .getPackageManager()
                            .getLaunchIntentForPackage(Utils.pkgName);

                    if (intent != null) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        MainHook.launchActivity.startActivity(intent);
                    }

                    MainHook.launchActivity.finishAffinity();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }

        });

    }

    public static class HeaderCellHolder {
        HeaderCell cell;

        public HeaderCellHolder(Object obj){
            cell = new HeaderCell(XposedHelpers.getObjectField(obj, "headerCell"));
        }
    }

    public static class TextCheckCellHolder {
        TextCheckCell cell;

        public TextCheckCellHolder(Object obj){
            cell = new TextCheckCell(XposedHelpers.getObjectField(obj, "textCheckCell"));
        }
    }

    public static class TextSettingsCellHolder {
        TextSettingsCell cell;

        public TextSettingsCellHolder(Object obj){
            cell = new TextSettingsCell(XposedHelpers.getObjectField(obj, "textSettingsCell"));
        }
    }

    public static class ShadowSectionCellHolder {
        View cell;

        public ShadowSectionCellHolder(Object obj){
            cell = (View) XposedHelpers.getObjectField(obj, "view");
        }
    }

}
