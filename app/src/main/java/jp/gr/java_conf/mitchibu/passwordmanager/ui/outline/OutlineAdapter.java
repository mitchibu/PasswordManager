package jp.gr.java_conf.mitchibu.passwordmanager.ui.outline;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import jp.gr.java_conf.mitchibu.passwordmanager.databinding.OutlineItemBinding;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.AccountView;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.BindablePagedAdapter;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.BindableViewHolder;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.OnItemClickListener;

public class OutlineAdapter extends BindablePagedAdapter<AccountView> {
	private OnItemClickListener<AccountView> onItemClickListener = null;

	OutlineAdapter() {
		super(new DiffUtil.ItemCallback<AccountView>() {
			@Override
			public boolean areItemsTheSame(@NonNull AccountView oldItem, @NonNull AccountView newItem) {
				return oldItem.account.id == newItem.account.id;
			}

			@Override
			public boolean areContentsTheSame(@NonNull AccountView oldItem, @NonNull AccountView newItem) {
				return oldItem.equals(newItem);
			}
		});
	}

	void setOnItemClickListener(OnItemClickListener<AccountView> listener) {
		onItemClickListener = listener;
	}

	@Override
	public ViewDataBinding onCreateViewDataBinding(@NonNull ViewGroup parent, int viewType) {
		return OutlineItemBinding.inflate((LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE), parent, false);
	}

	@Override
	public void onBindViewHolder(@NonNull BindableViewHolder holder, int position) {
		ViewCompat.setTransitionName(holder.binding.getRoot(), "share" + position);
		AccountView account = getItem(position);
		if(holder.binding instanceof OutlineItemBinding) {
			((OutlineItemBinding)holder.binding).setAccount(account);
			((OutlineItemBinding)holder.binding).setListener(onItemClickListener);
		}
		holder.binding.executePendingBindings();
	}
}
