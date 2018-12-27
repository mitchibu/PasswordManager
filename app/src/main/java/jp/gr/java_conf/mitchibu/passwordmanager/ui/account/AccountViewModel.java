package jp.gr.java_conf.mitchibu.passwordmanager.ui.account;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.Account;

public class AccountViewModel extends ViewModel {
	public final MutableLiveData<Account> account = new MutableLiveData<>();
}
