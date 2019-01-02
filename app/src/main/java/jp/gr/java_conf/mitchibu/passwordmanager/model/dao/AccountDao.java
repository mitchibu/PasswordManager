package jp.gr.java_conf.mitchibu.passwordmanager.model.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface AccountDao {
	@Query("select Account.*,Category.id as c_id,Category.name as c_name from Account left join Category on Account.categoryId=Category.id")
	DataSource.Factory<Integer, AccountView> getAll2();

	@Query("select * from Account")
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

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	long[] insert(Category... categories);
}
