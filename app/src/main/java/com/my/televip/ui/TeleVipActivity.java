package com.my.televip.ui;

import static com.my.televip.MainHook.lpparam;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar;
import com.my.televip.virtuals.BaseFragment;
import com.my.televip.virtuals.Cells.TextCheckCell;
import com.my.televip.virtuals.Theme;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;


public class TeleVipActivity {

    public View fragmentView;

    private int rowCount;
    private Context context;
    private int hideSeenUserRow;
    public RecyclerView listView;


    public static void init(){
        Class<?> dataSettings = XposedHelpers.findClassIfExists("org.telegram.ui.DataSettingsActivity", lpparam.classLoader);
        XposedHelpers.findAndHookMethod(
                dataSettings,
                AutomationResolver.resolve("DataSettingsActivity", "createView", AutomationResolver.ResolverType.Method), Context.class, new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(final MethodHookParam param) {
                        if (com.my.televip.ui.Theme.isSettings){
                            TeleVipActivity tele = new TeleVipActivity();
                            param.setResult(tele.createView(param));
                        }
                    }
                });
        XposedHelpers.findAndHookMethod(
                dataSettings,
                AutomationResolver.resolve("DataSettingsActivity", "onFragmentDestroy", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(final MethodHookParam param) {
                        if (com.my.televip.ui.Theme.isSettings){
                            com.my.televip.ui.Theme.isSettings = false;
                        }
                    }
                });

    }

    private void updateRow(){
        rowCount = 4;
        hideSeenUserRow = rowCount++;
    }

    public View createView(final XC_MethodHook.MethodHookParam param) {
        BaseFragment televip = new BaseFragment(param.thisObject);

        context = televip.getContext();
        updateRow();

        Language.init();

        ActionBar actionBar = televip.getActionBar();
        actionBar.setTitle(Language.GhostMode);


        fragmentView = new FrameLayout(context);
        fragmentView.setBackgroundColor(Theme.getColor(Theme.getKey_windowBackgroundGray()));
        FrameLayout frameLayout = (FrameLayout) fragmentView;


        listView = new RecyclerView(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(layoutManager);

        ListAdapter listAdapter = new ListAdapter();
        listView.setAdapter(listAdapter);

        listView.setBackgroundColor(Color.rgb(29,39,51));

// إعداد LayoutParams مع margin (المسافة حول الـ RecyclerView)
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 10, 10, 0); // هنا المسافة من كل جهة

        frameLayout.addView(listView,params);


        return fragmentView;
    }


    // ------------------- Adapter -------------------
    private class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public int getItemViewType(int position) {
            if (position == hideSeenUserRow) {
                return 0;
            }
            /*else if (position == mediaDownloadSectionRow || position == streamSectionRow || position == callsSectionRow || position == usageSectionRow || position == proxySectionRow || position == autoplayHeaderRow || position == saveToGallerySectionRow) {
                return 2;
            } else if (position == enableCacheStreamRow || position == enableStreamRow || position == enableAllStreamRow || position == enableMkvRow || position == autoplayGifsRow || position == autoplayVideoRow) {
                return 3;
            } else {
                return 1;
            }

             */
            return 0;
        }


    @Override
        public int getItemCount() {
            return rowCount;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;

            switch (viewType) {

                case 0:
                    TextCheckCell cell = new TextCheckCell(context);
                    cell.setBackgroundColor(Theme.getKey_windowBackgroundWhite());
                    return new CheckHolder(cell);

                default:
                    view = new TextView(context);
                    view.setLayoutParams(new RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    return new Holder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            int viewType = getItemViewType(position);
            switch (viewType) {

                case 0:
                    CheckHolder ch = (CheckHolder) holder;
                    ch.cell.setTextAndCheck(Language.HideSeenUser, FeatureManager.isHideSeenPrivate(), false);

                    break;
            }

            // Selector مثل Telegram
            //holder.itemView.setBackgroundResource(android.R.drawable.list_selector_background);
        }

        class CheckHolder extends RecyclerView.ViewHolder {
            TextCheckCell cell;

            public CheckHolder(TextCheckCell cell) {
                super((View) cell.getTextCell());
                this.cell = cell;
            }
        }

        public class Holder extends RecyclerView.ViewHolder {

            public Holder(View itemView) {
                super(itemView);
            }
        }

    }
}
