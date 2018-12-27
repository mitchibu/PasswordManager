package jp.gr.java_conf.mitchibu.passwordmanager.ui.base;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Objects;

import dagger.android.support.DaggerAppCompatActivity;

@SuppressLint("Registered")
public class DataBindingActivity<T extends ViewDataBinding> extends DaggerAppCompatActivity {
	private T binding;

	public final T getBinding() {
		return binding;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BindLayout layout = getClass().getAnnotation(BindLayout.class);
		binding = DataBindingUtil.setContentView(this, Objects.requireNonNull(layout).value());
	}
}
