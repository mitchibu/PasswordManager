package jp.gr.java_conf.mitchibu.passwordmanager.ui.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class MyViewModelFactory<E> implements ViewModelProvider.Factory {
	private final E data;
	private final Class<E> clazz;

	public MyViewModelFactory(E data, Class<E> clazz) {
		this.data = data;
		this.clazz = clazz;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		try {
			return modelClass.getConstructor(clazz).newInstance(data);
		} catch(Exception e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
	}
}
