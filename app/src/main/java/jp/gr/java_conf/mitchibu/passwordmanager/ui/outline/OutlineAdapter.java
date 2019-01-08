package jp.gr.java_conf.mitchibu.passwordmanager.ui.outline;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Objects;

import jp.gr.java_conf.mitchibu.passwordmanager.databinding.OutlineItemBinding;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.Account;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.BindablePagedAdapter;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.BindableViewHolder;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.OnItemClickListener;

public class OutlineAdapter extends BindablePagedAdapter<Account> {
	private OnItemClickListener<Account> onItemClickListener = null;

	OutlineAdapter() {
		super(new DiffUtil.ItemCallback<Account>() {
			@Override
			public boolean areItemsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
				return oldItem.id == newItem.id;
			}

			@Override
			public boolean areContentsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
				return oldItem.equals(newItem);
			}
		});
		setHasStableIds(true);
	}

	void setOnItemClickListener(OnItemClickListener<Account> listener) {
		onItemClickListener = listener;
	}

	@Override
	public ViewDataBinding onCreateViewDataBinding(@NonNull ViewGroup parent, int viewType) {
		return OutlineItemBinding.inflate((LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE), parent, false);
	}

	@Override
	public void onBindViewHolder(@NonNull BindableViewHolder holder, int position) {
		ViewCompat.setTransitionName(holder.binding.getRoot(), "share" + position);
		Account account = getItem(position);
		if(holder.binding instanceof OutlineItemBinding) {
			((OutlineItemBinding)holder.binding).setAccount(account);
			((OutlineItemBinding)holder.binding).setListener(onItemClickListener);
		}
		holder.binding.executePendingBindings();
	}

	@Override
	public long getItemId(int position) {
		return Objects.requireNonNull(getItem(position)).id;
	}
}
