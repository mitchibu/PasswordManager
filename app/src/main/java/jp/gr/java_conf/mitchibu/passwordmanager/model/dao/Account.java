package jp.gr.java_conf.mitchibu.passwordmanager.model.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Account implements Parcelable {
	public static final Creator<Account> CREATOR = new Creator<Account>() {
		@Override
		public Account createFromParcel(Parcel in) {
			return new Account(in);
		}

		@Override
		public Account[] newArray(int size) {
			return new Account[size];
		}
	};

	@PrimaryKey(autoGenerate = true)
	public long id = 0;

	@ColumnInfo(name = "categoryId")
	public long categoryId = 0;

	@ColumnInfo(name = "title")
	public String title;

	@ColumnInfo(name = "name")
	public String name;

	@ColumnInfo(name = "password")
	public String password;

	@ColumnInfo(name = "comment")
	public String comment;

	public Account() {
	}

	private Account(Parcel in) {
		id = in.readLong();
		categoryId = in.readLong();
		title = in.readString();
		name = in.readString();
		password = in.readString();
		comment = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(categoryId);
		dest.writeString(title);
		dest.writeString(name);
		dest.writeString(password);
		dest.writeString(comment);
	}
}