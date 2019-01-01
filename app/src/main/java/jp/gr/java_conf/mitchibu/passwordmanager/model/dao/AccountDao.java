package jp.gr.java_conf.mitchibu.passwordmanager.model.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface AccountDao {
	@Query("SELECT * FROM Account")
	DataSource.Factory<Integer, Account> getAll();

	@Query("SELECT * FROM Account where id=:id")
	DataSource.Factory<Integer, Account> get(long id);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	long[] insert(Account... accounts);

	@Update
	int update(Account account);

//	@Insert
//	Completable insertAll(Account... users);

	@Query("delete from Account")
	void deleteAll();
}
