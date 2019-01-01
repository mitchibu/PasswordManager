package jp.gr.java_conf.mitchibu.passwordmanager.ui.account;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.view.View;

import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.Account;

public class AccountPresenter {
	@BindingAdapter("onSave")
	public static void onSave(View view, final AccountViewModel model) {
		view.setOnClickListener(v -> model.save());
	}
}
