package jp.gr.java_conf.mitchibu.passwordmanager;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import jp.gr.java_conf.mitchibu.passwordmanager.di.DaggerAppComponent;

public class MyApplication extends DaggerApplication {
	@Override
	protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
		return DaggerAppComponent.builder().create(this);
	}
}
