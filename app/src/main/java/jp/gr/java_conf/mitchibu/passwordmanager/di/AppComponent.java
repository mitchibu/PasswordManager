package jp.gr.java_conf.mitchibu.passwordmanager.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import jp.gr.java_conf.mitchibu.passwordmanager.MyApplication;

@Singleton
@Component(modules = {
		AndroidSupportInjectionModule.class,
		AppModule.class,
		ActivityBuilderModule.class})
interface AppComponent extends AndroidInjector<MyApplication> {
	@Component.Builder
	abstract class Builder extends AndroidInjector.Builder<MyApplication> {
	}
}
