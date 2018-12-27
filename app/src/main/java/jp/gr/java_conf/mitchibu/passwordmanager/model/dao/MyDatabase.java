package jp.gr.java_conf.mitchibu.passwordmanager.model.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
	public abstract AccountDao accountDao();
}
