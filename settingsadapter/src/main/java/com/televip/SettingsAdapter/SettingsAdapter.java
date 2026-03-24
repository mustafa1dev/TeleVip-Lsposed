package com.televip.SettingsAdapter;

import static com.televip.SettingsAdapter.Bridge.getRow;
import static com.televip.SettingsAdapter.Bridge.getRowCount;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingsAdapter {

    public static LinearLayoutManager getLayoutManager(Context context){
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    public static class RecyclerListView extends RecyclerView {

        public RecyclerListView(@NonNull Context context) {
            super(context);
        }
    }


        public static class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public Context MContext;

        public ListAdapter(Context context) {
            MContext = context;
        }

        @Override
        public int getItemViewType(int position) {
            return getRow(position);
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            int viewType = getItemViewType(position);
            Bridge.onBindViewHolder(holder, position, viewType);
        }

        @Override
        public int getItemCount() {
            return getRowCount();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            switch (viewType) {

                case 1:
                    return new TextCheckCellHolder(new FrameLayout(MContext), null);
                case 2:
                    return new TextSettingsCellHolder(new FrameLayout(MContext), null);
                case 3:
                    return new ShadowSectionCellHolder(new FrameLayout(MContext));
                case 0:
                default:
                    return new HeaderCellHolder(new FrameLayout(MContext), null);
            }
        }
    }


    static class TextCheckCellHolder extends RecyclerView.ViewHolder {

        public Object textCheckCell;

        public TextCheckCellHolder(View view, Object  textCheckCell) {
            super(view);
            this.textCheckCell = textCheckCell;
        }
    }

    static class HeaderCellHolder extends RecyclerView.ViewHolder {

        public Object headerCell;

        public HeaderCellHolder(View view, Object headerCell) {
            super (view);
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

}
