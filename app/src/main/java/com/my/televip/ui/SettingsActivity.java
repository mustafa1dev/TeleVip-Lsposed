package com.my.televip.ui;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.televip.ClientChecker;
import com.my.televip.Drawable.ArrowDrawable;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.audio;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.androidx.ViewHolder;
import com.my.televip.virtuals.messenger.browser.Browser;
import com.my.televip.virtuals.ui.Cells.HeaderCell;
import com.my.televip.virtuals.ui.Cells.TextCheckCell;
import com.my.televip.virtuals.ui.Cells.TextInfoCell;
import com.my.televip.virtuals.ui.Cells.TextSettingsCell;
import com.my.televip.virtuals.ui.Components.RecyclerListView;
import com.my.televip.virtuals.ui.LaunchActivity;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XposedHelpers;

public class SettingsActivity {

    private static boolean isSettings;

    private static boolean isLongText = false;

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
    private static int removesContentSavingRow;
    private static int allowSavingvideosRow;

    private static int telegramPremiumRow;
    private static int fixTLErrorRow;

    private static int ConnectionsRow;
    private static int DownloadSpeedRow;

    private static int UiRow;
    private static int hidePinnedMessagesRow;
    private static int disableChannelSwipeBackRow;
    private static int disableProfileSwipeBackRow;

    private static final List<Integer> shadowRows = new ArrayList<>();

    private static int btnChannelRow;
    private static int btnRestartAppRow;


    private static int onlineInfoRow;

    public RecyclerListView listView;

    private void updateRow(){
        rowCount = 0;

        // GhostMode
        ghostModeRow = rowCount++;

        hideSeenRow = rowCount++;
        markReadAfterSendRow = rowCount++;
        hideTypingRow = rowCount++;
        hideStoryViewRow = rowCount++;
        hidePhoneRow = rowCount++;
        hideOnlineRow = rowCount++;

        onlineInfoRow = rowCount++;


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

        //UI
        UiRow = rowCount++;
        hidePinnedMessagesRow = rowCount++;
        disableChannelSwipeBackRow = rowCount++;
        disableProfileSwipeBackRow = rowCount++;

        shadowRows.add(rowCount++);

        // Other Features
        otherFeaturesRow = rowCount++;

        removesContentSavingRow = rowCount++;
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
        fragmentView.setBackgroundColor(Theme.getBackgroundGrayColor());
        try {

            ToolBar toolbar = new ToolBar(context);

            toolbar.setColorTitle(Theme.getTextToolBarColor());
            toolbar.setTextTitle(Translator.get(Keys.GhostMode));

            ArrowDrawable arrow = new ArrowDrawable();
            toolbar.setImageDrawable(arrow);
            toolbar.getImage().setOnClickListener(v -> hide());

            fragmentView.addView(toolbar);

            listView = new RecyclerListView(context);

            Object adapter = XposedHelpers.newInstance(
                    loadClass.getSettingsAdapter$ListAdapterClass(),
                    context);

            listView.setAdapter(adapter);

            listView.setBackgroundColor(Theme.getBackgroundWhiteOrBlueColor());

            listView.setVerticalScrollBarEnabled(false);
            listView.setLayoutManager(Bridge.getLayoutManager(context));

            LinearLayout.LayoutParams recyclerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1f
            );
            recyclerParams.setMargins(10, 10, 10, 0);

            fragmentView.addView(listView.getRecyclerListView(), recyclerParams);

        } catch (Exception e){
            Utils.log(e);
        }

        return fragmentView;
    }

    public SettingsActivity(Context context) {
        this.context = context;
        try {
            isSettings = true;

            if (!FeatureManager.getBoolean("DSA")) {
                AlertDialog alertDialog = new AlertDialog(context);

                alertDialog.setTitle(Translator.get(Keys.GhostMode));
                alertDialog.setMessage(Translator.get(Keys.JoinTeleVip));

                alertDialog.setPositiveButton(Translator.get(Keys.Join), AlertDialog.click(() -> {
                    Browser.openUrl("https://t.me/t_l0_e");
                    hide();
                }));

                alertDialog.setNegativeButton(Translator.get(Keys.Cancel), null);
                alertDialog.setNeutralButton(Translator.get(Keys.DontShowAgain), AlertDialog.click(() -> FeatureManager.putBoolean("DSA", true)));
                alertDialog.show();
            }
        } catch (Exception e){
            Utils.log(e);
        }
    }


    public static void init(){
        audio.init();
        try {

            HMethod.hookMethod(loadClass.getLaunchActivityClass(), "onBackPressed", new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (isSettings) {
                        hide();
                        addItem.settings = null;
                        param.setResult(null);
                    }
                }
            });

            HMethod.hookMethod(loadClass.getAndroidUtilitiesClass(), AutomationResolver.resolve("AndroidUtilities", "isTablet", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
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
        if (position == ghostModeRow || position == storiesRow || position == messagesRow || position == mediaRow || position == otherFeaturesRow || position == ConnectionsRow || position == UiRow) {
            return 0;
        } else if (position == hideSeenRow || position == hideStoryViewRow || position == hideOnlineRow ||
                position == hidePhoneRow || position == hideTypingRow || position == disableStoriesRow || disableNumberRoundingRow == position || position == showDeletedMessagesRow || position == preventMediaRow ||
                position == removesContentSavingRow || position == allowSavingvideosRow || position == telegramPremiumRow || position == HideUpdateAppRow ||
                position == SaveEditsHistoryRow || position == fixTLErrorRow || position == showMessageIDRow || position == DownloadSpeedRow || position == markReadAfterSendRow || position == hidePinnedMessagesRow || position == disableChannelSwipeBackRow|| position == disableProfileSwipeBackRow) {
            return 1;
        } else if (position == btnChannelRow || position == btnRestartAppRow) {
            return 2;
        } else if (shadowRows.contains(position)) {
            return 3;
        } else if (position == onlineInfoRow) {
            return 4;
        }
        return 0;
    }

    public static void onBindViewHolder(Object holder, int position, int viewType) {
        switch (viewType) {
            case 0:
                HeaderCellHolder headerCell = new HeaderCellHolder(holder);
                if (position == ghostModeRow) {
                    headerCell.cell.setText(Translator.get(Keys.GhostModeSettings));
                } else if (position == storiesRow) {
                    headerCell.cell.setText(Translator.get(Keys.StoriesSettings));
                } else if (position == messagesRow) {
                    headerCell.cell.setText(Translator.get(Keys.MessagesSettings));
                } else if (position == mediaRow) {
                    headerCell.cell.setText(Translator.get(Keys.MediaSettings));
                } else if (position == otherFeaturesRow) {
                    headerCell.cell.setText(Translator.get(Keys.OtherFeaturesSettings));
                } else if (position == ConnectionsRow) {
                    headerCell.cell.setText(Translator.get(Keys.ConnectionsSettings));
                } else if (position == UiRow) {
                    headerCell.cell.setText(Translator.get(Keys.UiSettings));
                }
                break;
            case 1:
                TextCheckCellHolder ch = new TextCheckCellHolder(holder);
                if (position == hideSeenRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.HideSeen), FeatureManager.isHideSeen(), false);
                } else if (position == hideStoryViewRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.HideStoryView), FeatureManager.isHideStoryRead(), false);
                } else if (position == hideOnlineRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.HideOnline), Translator.get(Keys.RestartRequired), FeatureManager.isHideOnline(), true, false);
                } else if (position == hidePhoneRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.HidePhone), Translator.get(Keys.RestartRequired), FeatureManager.isHidePhone(), true, false);
                } else if (position == hideTypingRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.HideTyping), FeatureManager.isHideTyping(), false);
                } else if (position == HideUpdateAppRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.HideUpdateApp), Translator.get(Keys.RestartRequired), FeatureManager.isHideUpdateApp(), true, false);
                } else if (position == disableStoriesRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.DisableStories), Translator.get(Keys.RestartRequired), FeatureManager.isDisableStories(), true, false);
                } else if (position == disableNumberRoundingRow) {
                    ch.cell.setTextAndValueAndCheck(Translator.get(Keys.DisableNumberRounding), "5.3K -> 5300", FeatureManager.isDisableNumberRounding(), true, false);
                } else if (position == showMessageIDRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.ShowMessageID), FeatureManager.isShowMessageID(), false);
                } else if (position == showDeletedMessagesRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.ShowDeletedMessages), FeatureManager.isShowDeleted(), false);
                } else if (position == SaveEditsHistoryRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.SaveEditsHistory), FeatureManager.isSaveEditsHistory(), false);
                } else if (position == preventMediaRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.PreventMedia), FeatureManager.isPreventMedia(), false);
                } else if (position == removesContentSavingRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.RemovesContentSaving), FeatureManager.isRemovesContentSaving(), false);
                } else if (position == allowSavingvideosRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.AllowSavingVideos), FeatureManager.isAllowSaveGallery(), false);
                } else if (position == telegramPremiumRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.TelegramPremium), FeatureManager.isTelePremium(), false);
                } else if (position == fixTLErrorRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.FixTLError), FeatureManager.isFixTLError(), false);
                } else if (position == DownloadSpeedRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.DownloadSpeed), FeatureManager.isDownloadSpeed(), false);
                } else if (position == markReadAfterSendRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.MarkReadAfterSend), FeatureManager.isMarkReadAfterSend(), false);
                } else if (position == hidePinnedMessagesRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.HidePinnedMessages), FeatureManager.isHidePinnedMessages(), false);
                } else if (position == disableChannelSwipeBackRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.DisableChannelSwipeBack), FeatureManager.isDisableChannelSwipeBack(), false);
                } else if (position == disableProfileSwipeBackRow) {
                    ch.cell.setTextAndCheck(Translator.get(Keys.DisableProfileSwipeBack), FeatureManager.isDisableProfileSwipeBack(), false);
                }

                ch.cell.getTextView().setLines(0);
                ch.cell.getTextView().setMaxLines(0);
                ch.cell.getTextView().setSingleLine(false);
                ch.cell.getTextView().setEllipsize(null);
                break;

            case 2:
                TextSettingsCellHolder settingsCell = new TextSettingsCellHolder(holder);
                if (position == btnChannelRow) {
                    settingsCell.cell.setText(Translator.get(Keys.DeveloperChannel), false);
                } else if (position == btnRestartAppRow) {
                    settingsCell.cell.setText(Translator.get(Keys.RestartApp), false);
                }
                settingsCell.cell.getTextView().setTextColor(Theme.getTextBlueColor());
                break;
            case 3:
                ShadowSectionCellHolder shadowSectionCell = new ShadowSectionCellHolder(holder);
                shadowSectionCell.cell.setBackgroundColor((Theme.getBackgroundGrayColor()));
                break;
            case 4:
                TextInfoCellHolder textInfoCell = new TextInfoCellHolder(holder);
                TextView textView = textInfoCell.text.getTextView();
                if (position == onlineInfoRow) {
                    textView.setMaxLines(2);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    textView.setText(Translator.get(Keys.OfflineVisibilityInfo));
                    if (textView.getMaxLines() == 2){
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
                } else if (pos == removesContentSavingRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_REMOVES_CONTENT_SAVING, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_REMOVES_CONTENT_SAVING);
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
                } else if (pos == hidePinnedMessagesRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_HIDE_PINNED_MESSAGES, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_HIDE_PINNED_MESSAGES);
                } else if (pos == disableChannelSwipeBackRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_DISABLE_CHANNEL_SWIPE_BACK, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_DISABLE_CHANNEL_SWIPE_BACK);
                } else if (pos == disableProfileSwipeBackRow) {
                    FeatureManager.putBoolean(FeatureManager.KEY_DISABLE_PROFILE_SWIPE_BACK, checked);
                    FeatureManager.readFeature(FeatureManager.KEY_DISABLE_PROFILE_SWIPE_BACK);
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
    public static class TextInfoCellHolder {
        TextInfoCell text;

        public TextInfoCellHolder(Object obj){
            text = (TextInfoCell) XposedHelpers.getObjectField(obj, "view");
        }
    }

}
