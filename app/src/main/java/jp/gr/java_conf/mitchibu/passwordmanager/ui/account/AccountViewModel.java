package jp.gr.java_conf.mitchibu.passwordmanager.ui.account;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.RatingBar;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.Account;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.AccountView;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.MyDatabase;

public class AccountViewModel extends ViewModel {
	public final MutableLiveData<AccountView> account = new MutableLiveData<>();
	public final MediatorLiveData<Long> categoryId = new MediatorLiveData<>();
	public final MediatorLiveData<String> title = new MediatorLiveData<>();
	public final MediatorLiveData<String> name = new MediatorLiveData<>();
	public final MediatorLiveData<String> password = new MediatorLiveData<>();
	public final MediatorLiveData<String> comment = new MediatorLiveData<>();
	public final MediatorLiveData<String> category = new MediatorLiveData<>();

	private final MyDatabase db;

	public AccountViewModel(MyDatabase db) {
		this.db = db;
		categoryId.addSource(account, account -> categoryId.setValue(account == null ? 0 : account.account.categoryId));
		title.addSource(account, account -> title.setValue(account == null ? null : account.account.title));
		name.addSource(account, account -> name.setValue(account == null ? null : account.account.name));
		password.addSource(account, account -> password.setValue(account == null ? null : account.account.password));
		comment.addSource(account, account -> comment.setValue(account == null ? null : account.account.comment));
		category.addSource(account, account -> category.setValue(account == null ? null : account.category.name));
	}

	void save() {
		Long category = categoryId.getValue();
		AccountView oldAccount = account.getValue();
		Account account = new Account();
		if(oldAccount != null) account.id = oldAccount.account.id;
		account.categoryId = category == null ? 0 : category;
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
	//	this.account.setValue(account);
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

	@BindingAdapter("test")
	public static void test(View view, long id) {
		android.util.Log.v("test", "id: " + id);
	}
}
