package jp.gr.java_conf.mitchibu.passwordmanager.ui.outline;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.arch.paging.RxPagedListBuilder;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.Account;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.MyDatabase;

public class OutlineViewModel extends ViewModel {
	public final MutableLiveData<Boolean> isScrolling = new MutableLiveData<>();
	public final Flowable<PagedList<Account>> data;

	public OutlineViewModel(MyDatabase db) {
		new Thread() {
			public void run() {
				db.accountDao().deleteAll();
				Account[] accounts = new Account[10];
				for(int i = 0; i < accounts.length; ++ i) {
					accounts[i] = new Account();
					accounts[i].title = "title" + i;
					accounts[i].name = "name" + i;
					accounts[i].password = "password" + i;
					accounts[i].comment = "comment" + i;
				}
				db.accountDao().insert(accounts);
			}
		}.start();
		data = new RxPagedListBuilder<>(db.accountDao().getAll(), 50).buildFlowable(BackpressureStrategy.LATEST);
	}
}
