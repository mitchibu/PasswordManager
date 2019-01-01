package jp.gr.java_conf.mitchibu.passwordmanager.ui.account;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.ChangeTransform;
import android.support.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import jp.gr.java_conf.mitchibu.passwordmanager.R;
import jp.gr.java_conf.mitchibu.passwordmanager.databinding.AccountFragmentBinding;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.BindLayout;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.DataBindingFragment;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.util.MyViewModelFactory;

@BindLayout(R.layout.account_fragment)
public class AccountFragment extends DataBindingFragment<AccountFragmentBinding> {
	@Inject
	MyViewModelFactory factory;

	public AccountFragment() {
		TransitionSet set = new TransitionSet();
		set.addTransition(new ChangeTransform());
		set.addTransition(new ChangeBounds());

		setEnterTransition(set);
		setExitTransition(set);
		setSharedElementEnterTransition(set);
		setSharedElementReturnTransition(set);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getBinding().setModel(ViewModelProviders.of(this, factory).get(AccountViewModel.class));
		getBinding().setPresenter(new AccountPresenter());

		Bundle args = getArguments();
		if(args != null) {
			AccountFragmentArgs a = AccountFragmentArgs.fromBundle(args);
			getBinding().getRoot().setTransitionName(a.getShare());
			getBinding().getModel().account.setValue(a.getAccount());
		}
	}
}
