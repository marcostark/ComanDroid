package br.com.qgdostark.comandroid.interfaces;

import android.view.View;

/**
 * Created by stark on 26/06/17.
 */

public interface ClickRecyclerView {
    void onCustomClick(View view, int position, boolean isLongClick);
}
