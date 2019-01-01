package jp.gr.java_conf.mitchibu.passwordmanager.ui.account;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.RatingBar;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.Account;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.MyDatabase;

public class AccountViewModel extends ViewModel {
	public final MutableLiveData<Account> account = new MutableLiveData<>();
	public final MediatorLiveData<String> title = new MediatorLiveData<>();
	public final MediatorLiveData<String> name = new MediatorLiveData<>();
	public final MediatorLiveData<String> password = new MediatorLiveData<>();
	public final MediatorLiveData<String> comment = new MediatorLiveData<>();

	private final MyDatabase db;

	public AccountViewModel(MyDatabase db) {
		this.db = db;
		title.addSource(account, account -> title.setValue(account == null ? null : account.title));
		name.addSource(account, account -> name.setValue(account == null ? null : account.name));
		password.addSource(account, account -> password.setValue(account == null ? null : account.password));
		comment.addSource(account, account -> comment.setValue(account == null ? null : account.comment));
	}

	void save() {
		Account oldAccount = account.getValue();
		Account account = new Account();
		if(oldAccount != null) account.id = oldAccount.id;
		account.title = title.getValue();
		account.name = name.getValue();
		account.password = password.getValue();
		account.comment = comment.getValue();
		if(account.id == 0) {
			long[] id = db.accountDao().insert(account);
			android.util.Log.v("test", "insert");
			account.id = id[0];
		} else {
			db.accountDao().update(account);
			android.util.Log.v("test", "update");
		}
		this.account.setValue(account);
	}

	@BindingAdapter("strength")
	public static void strength(RatingBar view, String password) {
		int score = 0;
		if(!TextUtils.isEmpty(password)) {
			Zxcvbn zxcvbn = new Zxcvbn();
			Strength strength = zxcvbn.measure(password);
			score = strength.getScore() + 1;
		}
		view.setRating(score);
	}
}
