package jp.gr.java_conf.mitchibu.passwordmanager.ui.outline;

import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.arch.paging.RxPagedListBuilder;

import androidx.navigation.Navigation;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.Account;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.MyDatabase;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.OnItemClickListener;

public class OutlineViewModel extends ViewModel {
	final OnItemClickListener<Account> onItemClickListener =
			(view, item) -> Navigation.findNavController(view).navigate(OutlineFragmentDirections.actionOutlineFragmentToAccountFragment(item));

	public Flowable<PagedList<Account>> data;

	public OutlineViewModel(MyDatabase db) {
		new Thread() {
			public void run() {
				db.accountDao().deleteAll();
				Account[] accounts = new Account[10];
				for(int i = 0; i < accounts.length; ++ i) {
					accounts[i] = new Account();
					accounts[i].title = "title" + i;
					accounts[i].name = "name" + i;
					accounts[i].comment = "comment" + i;
				}
				db.accountDao().insert(accounts);
			}
		}.start();
		data = new RxPagedListBuilder<>(db.accountDao().getAll(), 50).buildFlowable(BackpressureStrategy.LATEST);
	}
}
