package jp.gr.java_conf.mitchibu.passwordmanager.ui.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.MyDatabase;

public class MyViewModelFactory implements ViewModelProvider.Factory {
	private final MyDatabase db;

	public MyViewModelFactory(MyDatabase db) {
		this.db = db;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		try {
			return modelClass.getConstructor(MyDatabase.class).newInstance(db);
		} catch(Exception e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
	}
}
