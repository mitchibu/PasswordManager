package jp.gr.java_conf.mitchibu.passwordmanager.di;

import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.gr.java_conf.mitchibu.passwordmanager.MyApplication;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.MyDatabase;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.util.MyViewModelFactory;

@Module
public class AppModule {
	@Singleton
	@Provides
	MyDatabase provideMyDatabase(MyApplication app) {
		return Room.inMemoryDatabaseBuilder(app, MyDatabase.class).build();
//		return Room.databaseBuilder(app,
//				MyDatabase.class,
//				"database-name").build();
	}

	@Provides
	MyViewModelFactory provideMyViewModelFactory(MyDatabase db) {
		return new MyViewModelFactory(db);
	}
}
