package com.my.televip.settings.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.televip.settings.controller.SettingsController;
import com.my.televip.settings.ui.SettingsAdapter;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;
import com.my.televip.virtuals.ui.Cells.ExpandableTextCheckCell;
import com.my.televip.virtuals.ui.Cells.HeaderCell;
import com.my.televip.virtuals.ui.Cells.ShadowSectionCell;
import com.my.televip.virtuals.ui.Cells.TextCheckCell;
import com.my.televip.virtuals.ui.Cells.TextInfoCell;
import com.my.televip.virtuals.ui.Cells.TextSettingsCell;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public Context MContext;
    SettingsController settingsController;

    public ListAdapter(Context context, SettingsController settingsController) {
        MContext = context;
        this.settingsController = settingsController;
    }

    @Override
    public int getItemViewType(int position) {
        return SettingsAdapter.getRow(position);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        SettingsAdapter.onBindViewHolder(holder, settingsController, position, viewType);
    }

    @Override
    public int getItemCount() {
        return SettingsAdapter.getRowCount();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {

            case 1:
                TextCheckCell textCheckCell = Bridge.createTextCheckCell(MContext);
                return new TextCheckCellHolder(textCheckCell.getView(), textCheckCell.textCell);
            case 2:
                TextSettingsCell textSettingsCell = Bridge.createTextSettingsCell(MContext);
                return new TextSettingsCellHolder(textSettingsCell.getView(), textSettingsCell.textSettingsCell);
            case 3:
                return new ShadowSectionCellHolder(new ShadowSectionCell(MContext).getView());
            case 4:
                TextInfoCell textInfoCell = Bridge.createTextInfoCell(MContext);
                return new TextInfoCellHolder(textInfoCell);
                case 5:
                ExpandableTextCheckCell expandableTextCheckCell = Bridge.createExpandableTextCheckCell(MContext);
                return new ExpandableTextCheckCellHolder(expandableTextCheckCell, expandableTextCheckCell);
            case 0:
            default:
                HeaderCell headerCell = Bridge.createHeaderCell(MContext);
                return new HeaderCellHolder(headerCell.getView(), headerCell.headerCell);
        }
    }


    static class TextCheckCellHolder extends RecyclerView.ViewHolder {

        public Object textCheckCell;

        public TextCheckCellHolder(View view, Object textCheckCell) {
            super(view);
            this.textCheckCell = textCheckCell;
        }
    }
    static class ExpandableTextCheckCellHolder extends RecyclerView.ViewHolder {

        public Object expandableTextCheckCell;

        public ExpandableTextCheckCellHolder(View view, Object expandableTextCheckCell) {
            super(view);
            this.expandableTextCheckCell = expandableTextCheckCell;
        }
    }

    static class HeaderCellHolder extends RecyclerView.ViewHolder {

        public Object headerCell;

        public HeaderCellHolder(View view, Object headerCell) {
            super(view);
            this.headerCell = headerCell;
        }
    }

    static class TextSettingsCellHolder extends RecyclerView.ViewHolder {

        public Object textSettingsCell;

        public TextSettingsCellHolder(View view, Object textSettingsCell) {
            super(view);
            this.textSettingsCell = textSettingsCell;
        }
    }

    static class ShadowSectionCellHolder extends RecyclerView.ViewHolder {

        public View view;

        public ShadowSectionCellHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    static class TextInfoCellHolder extends RecyclerView.ViewHolder {

        public View view;

        public TextInfoCellHolder(View view) {
            super(view);
            this.view = view;
        }

    }
}