package jp.gr.java_conf.mitchibu.passwordmanager.model.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface AccountDao {
	@Query("select * from Account where deletedAt is null")
	DataSource.Factory<Integer, Account> getAll();

	@Query("select * from Account where id=:id")
	DataSource.Factory<Integer, Account> get(long id);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	long[] insert(Account... accounts);

	@Update
	int update(Account account);

//	@Insert
//	Completable insertAll(Account... users);

	@Query("delete from Account")
	void deleteAll();

	@Query("update Account set updatedAt = :timestamp, deletedAt = :timestamp where id = :id")
	void deleteById(long id, long timestamp);

	@Query("delete from Account where id = :id and deletedAt is not null")
	void deleteById(long id);

	@Query("update Account set updatedAt = :timestamp, deletedAt = null where id = :id")
	void cancelDeleteById(long id, long timestamp);
}
