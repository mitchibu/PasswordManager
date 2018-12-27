package jp.gr.java_conf.mitchibu.passwordmanager.ui.view;

import android.view.View;

public interface OnItemClickListener<T> {
	void onItemClick(View view, T item);
}
