package jp.gr.java_conf.mitchibu.passwordmanager.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.MainActivity;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.account.AccountFragment;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.outline.OutlineFragment;

@Module
abstract class ActivityBuilderModule {
	@ContributesAndroidInjector
	abstract MainActivity contributeMainActivity();
	@ContributesAndroidInjector
	abstract OutlineFragment contributeOutlineFragment();
	@ContributesAndroidInjector
	abstract AccountFragment contributeAccountFragment();
}
