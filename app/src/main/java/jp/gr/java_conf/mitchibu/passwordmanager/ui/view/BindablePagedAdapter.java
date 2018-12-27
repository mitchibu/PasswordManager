package jp.gr.java_conf.mitchibu.passwordmanager.ui.view;

import android.arch.paging.PagedListAdapter;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.ViewGroup;

public abstract class BindablePagedAdapter<T> extends PagedListAdapter<T, BindableViewHolder> {
	protected BindablePagedAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
		super(diffCallback);
	}

	@NonNull
	@Override
	public BindableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new BindableViewHolder(onCreateViewDataBinding(parent, viewType));
	}

	@Override
	public void onBindViewHolder(@NonNull BindableViewHolder holder, int position) {
	}

	public abstract ViewDataBinding onCreateViewDataBinding(@NonNull ViewGroup parent, int viewType);
}
