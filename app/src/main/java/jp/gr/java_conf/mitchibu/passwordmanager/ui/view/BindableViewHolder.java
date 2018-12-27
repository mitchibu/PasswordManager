package jp.gr.java_conf.mitchibu.passwordmanager.ui.view;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class BindableViewHolder extends RecyclerView.ViewHolder {
	public final ViewDataBinding binding;

	public BindableViewHolder(@NonNull ViewDataBinding binding) {
		super(binding.getRoot());
		this.binding = binding;
	}
}
