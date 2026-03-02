package com.my.televip.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.my.televip.ClientChecker;
import com.my.televip.Drawable.ArrowDrawable;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.audio;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.AlertDialog;
import com.my.televip.virtuals.ActiveTheme;
import com.my.televip.virtuals.Cells.HeaderCell;
import com.my.televip.virtuals.Cells.ShadowSectionCell;
import com.my.televip.virtuals.Cells.TextCheckCell;
import com.my.televip.virtuals.Cells.TextSettingsCell;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.messenger.browser.Browser;
import com.my.televip.virtuals.ui.LaunchActivity;

import de.robv.android.xposed.XposedHelpers;

public class SettingsActivity {

    private static boolean isSettings;

    private int rowCount;
    private final Context context;
    private int ghostModeRow;

    private int hideSeenUserRow;
    private int hideSeenGroupsRow;
    private int hideStoryViewRow;
    private int hideOnlineRow;
    private int hidePhoneRow;
    private int hideTypingRow;
    private int HideUpdateAppRow;

    private int disableStoriesRow;
    private int disableNumberRoundingRow;

    private int showDeletedMessagesRow;
    private int SaveEditsHistoryRow;
    private int preventMediaRow;
    private int unlockAllRestrictedRow;
    private int allowSavingvideosRow;
    private int telegramPremiumRow;

    private int shadowRow;
    private int shadow2Row;
    private int shadow3Row;

    private int btnChannelRow;
    private int btnRestartAppRow;

    public RecyclerView listView;

    private void updateRow(){
        rowCount = 0;
        ghostModeRow = rowCount++;

        hideSeenUserRow = rowCount++;
        hideSeenGroupsRow = rowCount++;
        hideStoryViewRow = rowCount++;
        hideOnlineRow = rowCount++;
        hidePhoneRow = rowCount++;
        hideTypingRow = rowCount++;
        HideUpdateAppRow = rowCount++;

        disableStoriesRow = rowCount++;

        if (!ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
            disableNumberRoundingRow = rowCount++;
        }

        showDeletedMessagesRow = rowCount++;

        if (!ClientChecker.check(ClientChecker.ClientType.Teegra)) {
            SaveEditsHistoryRow = rowCount++;
        }

        preventMediaRow = rowCount++;
        unlockAllRestrictedRow = rowCount++;
        allowSavingvideosRow = rowCount++;
        telegramPremiumRow = rowCount++;

        shadowRow = rowCount++;

        btnChannelRow = rowCount++;

        shadow2Row = rowCount++;

        btnRestartAppRow = rowCount++;

        shadow3Row = rowCount++;
    }

    public View createView() {
        updateRow();

        LinearLayout fragmentView = new LinearLayout(context);
        fragmentView.setOrientation(LinearLayout.VERTICAL);
        fragmentView.setBackgroundColor(Theme.getColor(Theme.getKey_windowBackgroundGray()));


        ToolBar toolbar = new ToolBar(context);

        toolbar.setColorTitle(Theme.getColor(Theme.getKey_actionBarDefaultTitle()));
        toolbar.setTextTitle(Language.GhostMode);

        ArrowDrawable arrow = new ArrowDrawable();
        toolbar.setImageDrawable(arrow);
        toolbar.getImage().setOnClickListener(v -> hide());

        fragmentView.addView(toolbar);


        listView = new RecyclerView(context);
        listView.setLayoutManager(new LinearLayoutManager(context));
        listView.setAdapter(new ListAdapter());

        if (!ActiveTheme.getActiveTheme()) {
            listView.setBackgroundColor(Color.rgb(29, 39, 51));
        } else {
            listView.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        listView.setVerticalScrollBarEnabled(false);
        listView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));


        LinearLayout.LayoutParams recyclerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
        );
        recyclerParams.setMargins(10,10,10,0);

        fragmentView.addView(listView, recyclerParams);

        return fragmentView;
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
        isSettings =false;
    }

    public SettingsActivity(Context context) {
        this.context =  context;
        Language.init();
        isSettings = true;
        if (!FeatureManager.getBoolean("DontShowAgain")) {
            AlertDialog alertDialog = new AlertDialog(context);

            alertDialog.setTitle(Language.GhostMode);
            alertDialog.setMessage(Language.JoinTeleVip);

            alertDialog.setPositiveButton(Language.Join, AlertDialog.click(() -> {
                Browser.openUrl("https://t.me/t_l0_e");
                hide();
            }));
            alertDialog.setNegativeButton(Language.Cancel, null);
            alertDialog.setNeutralButton(Language.DontShowAgain, AlertDialog.click(() -> FeatureManager.putBoolean("DontShowAgain", true)));
            alertDialog.show();
        }
    }

    public static void init(){
        audio.init();
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

        XposedHelpers.findAndHookMethod(loadClass.getAndroidUtilitiesClass(), AutomationResolver.resolve("AndroidUtilities","isTablet", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                if (isSettings) {
                    param.setResult(true);
                }
            }
        });
    }

    private class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public int getItemViewType(int position) {
            if (position == ghostModeRow) {
                return 0;
            } else if (position == hideSeenUserRow || position == hideSeenGroupsRow || position == hideStoryViewRow || position == hideOnlineRow ||
                    position == hidePhoneRow || position == hideTypingRow || position == disableStoriesRow || disableNumberRoundingRow == position || position == showDeletedMessagesRow || position == preventMediaRow ||
                    position == unlockAllRestrictedRow || position == allowSavingvideosRow || position == telegramPremiumRow || position == HideUpdateAppRow || position == SaveEditsHistoryRow) {
                return 1;
            } else if (position == btnChannelRow || position == btnRestartAppRow) {
                return 2;
            } else if (position == shadowRow || position == shadow2Row || position == shadow3Row) {
                return 3;
            }
            return 0;
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            int viewType = getItemViewType(position);
            switch (viewType) {

                case 0:
                    HeaderCellHolder headerCell = (HeaderCellHolder) holder;
                    if (position == ghostModeRow) {
                        headerCell.cell.setText(Language.GhostModeSettings);
                    }
                    break;
                case 1:
                    TextCheckCellHolder ch = (TextCheckCellHolder) holder;
                    if (position == hideSeenUserRow) {
                        ch.cell.setTextAndCheck(Language.HideSeenUser, FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN_PRIVATE), false);
                    } else if (position == hideSeenGroupsRow) {
                        ch.cell.setTextAndCheck(Language.HideSeenGroups, FeatureManager.getBoolean(FeatureManager.KEY_HIDE_SEEN_GROUP), false);
                    } else if (position == hideStoryViewRow) {
                        ch.cell.setTextAndCheck(Language.HideStoryView, FeatureManager.getBoolean(FeatureManager.KEY_HIDE_STORY_READ),false);
                    } else if (position == hideOnlineRow) {
                        ch.cell.setTextAndValueAndCheck(Language.HideOnline, Language.Restartrequired, FeatureManager.getBoolean(FeatureManager.KEY_HIDE_ONLINE), true, false);
                    } else if (position == hidePhoneRow) {
                        ch.cell.setTextAndValueAndCheck(Language.HidePhone, Language.Restartrequired, FeatureManager.getBoolean(FeatureManager.KEY_HIDE_PHONE), true, false);
                    } else if (position == hideTypingRow) {
                        ch.cell.setTextAndCheck(Language.HideTyping, FeatureManager.getBoolean(FeatureManager.KEY_HIDE_TYPING), false);
                    } else if (position == HideUpdateAppRow) {
                        ch.cell.setTextAndValueAndCheck(Language.HideUpdateApp,Language.Restartrequired , FeatureManager.getBoolean(FeatureManager.KEY_HIDE_UPDATE_APP),true, false);
                    } else if (position == disableStoriesRow) {
                        ch.cell.setTextAndValueAndCheck(Language.DisableStories, Language.Restartrequired, FeatureManager.getBoolean(FeatureManager.KEY_DISABLE_STORIES),true, false);
                    } else if (position == disableNumberRoundingRow) {
                        ch.cell.setTextAndValueAndCheck(Language.DisableNumberRounding, "5.3K -> 5300", FeatureManager.getBoolean(FeatureManager.KEY_Disable_Number_Rounding),true, false);
                    } else if (position == showDeletedMessagesRow) {
                        ch.cell.setTextAndCheck(Language.ShowDeletedMessages, FeatureManager.getBoolean(FeatureManager.KEY_SHOW_DELETED), false);
                    } else if (position == SaveEditsHistoryRow) {
                        ch.cell.setTextAndCheck(Language.SaveEditsHistory, FeatureManager.getBoolean(FeatureManager.KEY_Save_Edits_History), false);
                    } else if (position == preventMediaRow) {
                        ch.cell.setTextAndCheck(Language.PreventMedia, FeatureManager.getBoolean(FeatureManager.KEY_PREVENT_MEDIA), false);
                    } else if (position == unlockAllRestrictedRow) {
                        ch.cell.setTextAndCheck(Language.UnlockAllRestricted, FeatureManager.getBoolean(FeatureManager.KEY_UNLOCK_CHANNEL), false);
                    } else if (position == allowSavingvideosRow) {
                        ch.cell.setTextAndCheck(Language.AllowSavingvideos, FeatureManager.getBoolean(FeatureManager.KEY_ALLOW_SAVE_GALLERY), false);
                    } else if (position == telegramPremiumRow) {
                        ch.cell.setTextAndValueAndCheck(Language.TelegramPremium, Language.Restartrequired, FeatureManager.getBoolean(FeatureManager.KEY_TELE_PREMIUM),true, false);
                    }
                    ch.cell.getTextView().setLines(0);
                    ch.cell.getTextView().setMaxLines(0);
                    ch.cell.getTextView().setSingleLine(false);
                    ch.cell.getTextView().setEllipsize(null);
                    break;

                case 2:
                    TextSettingsCellHolder settingsCell = (TextSettingsCellHolder) holder;
                    if (position == btnChannelRow) {
                        settingsCell.cell.setText(Language.DeveloperChannel, false);
                    }
                    else if (position == btnRestartAppRow) {
                        settingsCell.cell.setText(Language.RestartApp, false);
                    }
                    settingsCell.cell.getTextView().setTextColor(Theme.getColor(Theme.getKey_switchTrackBlueChecked()));
                    break;
                case 3:
                    ShadowSectionCellHolder shadowSectionCell = (ShadowSectionCellHolder) holder;
                    shadowSectionCell.cell.getView().setBackgroundColor(Theme.getColor(Theme.getKey_windowBackgroundGray()));
                    break;
            }
            holder.itemView.setOnLongClickListener(v -> {

                if(audio.playing){
                    audio.stop();
                } else {
                    audio.start();
                }

                return true;
            });

            holder.itemView.setOnClickListener(v -> {
                int pos = holder.getAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return;

                if (viewType == 1) {

                    TextCheckCellHolder ch = (TextCheckCellHolder) holder;
                    boolean checked = !ch.cell.isChecked();
                    ch.cell.setChecked(checked);

                    if (pos == hideSeenUserRow) {
                        FeatureManager.putBoolean(FeatureManager.KEY_HIDE_SEEN_PRIVATE, checked);
                        FeatureManager.readFeature(FeatureManager.KEY_HIDE_SEEN_PRIVATE);
                    } else if (pos == hideSeenGroupsRow) {
                        FeatureManager.putBoolean(FeatureManager.KEY_HIDE_SEEN_GROUP, checked);
                        FeatureManager.readFeature(FeatureManager.KEY_HIDE_SEEN_GROUP);
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
                        FeatureManager.putBoolean(FeatureManager.KEY_Save_Edits_History, checked);
                        FeatureManager.readFeature(FeatureManager.KEY_Save_Edits_History);
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
                        FeatureManager.putBoolean(FeatureManager.KEY_Disable_Number_Rounding, checked);
                        FeatureManager.readFeature(FeatureManager.KEY_Disable_Number_Rounding);
                    }

                } else if (viewType == 2) {
                    if (pos == btnChannelRow){
                        Browser.openUrl("https://t.me/t_l0_e");
                        hide();
                    }
                    else if (pos == btnRestartAppRow) {

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

        @Override
        public int getItemCount() {
            return rowCount;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(
                    android.R.attr.selectableItemBackground,
                    outValue,
                    true
            );

            switch (viewType) {

                case 1:
                    TextCheckCell textCheckCell = new TextCheckCell(context);
                    textCheckCell.getView().setBackgroundColor(Theme.getKey_windowBackgroundWhite());

                    textCheckCell.getView().setBackgroundResource(outValue.resourceId);
                    textCheckCell.getView().setClickable(true);
                    textCheckCell.getView().setFocusable(true);
                    return new TextCheckCellHolder(textCheckCell);
                case 2:
                    TextSettingsCell textSettingsCell = new TextSettingsCell(context);
                    textSettingsCell.getView().setBackgroundColor(Theme.getKey_windowBackgroundWhite());
                    textSettingsCell.getView().setBackgroundResource(outValue.resourceId);
                    textSettingsCell.getView().setClickable(true);
                    textSettingsCell.getView().setFocusable(true);

                    return new TextSettingsCellHolder(textSettingsCell);
                case 3:
                    return new ShadowSectionCellHolder(new ShadowSectionCell(context));
                case 0:
                default:
                    HeaderCell header = new HeaderCell(context);
                    header.getView().setBackgroundColor(Theme.getKey_windowBackgroundWhite());
                    return new HeaderCellHolder(header);
            }
        }

        class TextCheckCellHolder extends RecyclerView.ViewHolder {
            TextCheckCell cell;

            public TextCheckCellHolder(TextCheckCell cell) {
                super(cell.getView());
                this.cell = cell;
            }
        }

        class HeaderCellHolder extends RecyclerView.ViewHolder {
            HeaderCell cell;

            public HeaderCellHolder(HeaderCell headerCell) {
                super (headerCell.getView());
                this.cell = headerCell;
            }
        }

        class TextSettingsCellHolder extends RecyclerView.ViewHolder {
            TextSettingsCell cell;

            public TextSettingsCellHolder(TextSettingsCell textSettingsCell) {
                super( textSettingsCell.getView());
                this.cell = textSettingsCell;
            }
        }
        class ShadowSectionCellHolder extends RecyclerView.ViewHolder {
            ShadowSectionCell cell;

            public ShadowSectionCellHolder(ShadowSectionCell shadowSectionCell) {
                super(shadowSectionCell.getView());
                this.cell = shadowSectionCell;
            }
        }

    }
}
