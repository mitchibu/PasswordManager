package jp.gr.java_conf.mitchibu.passwordmanager.ui.account;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Objects;

import jp.gr.java_conf.mitchibu.passwordmanager.R;
import jp.gr.java_conf.mitchibu.passwordmanager.databinding.AccountFragmentBinding;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.BindLayout;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.DataBindingFragment;

@BindLayout(R.layout.account_fragment)
public class AccountFragment extends DataBindingFragment<AccountFragmentBinding> {
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getBinding().setModel(ViewModelProviders.of(this).get(AccountViewModel.class));
		getBinding().getModel().account.setValue(AccountFragmentArgs.fromBundle(Objects.requireNonNull(getArguments())).getAccount());
	}
}
