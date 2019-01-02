package jp.gr.java_conf.mitchibu.passwordmanager.model.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Category implements Parcelable {
	public static final Creator<Category> CREATOR = new Creator<Category>() {
		@Override
		public Category createFromParcel(Parcel in) {
			return new Category(in);
		}

		@Override
		public Category[] newArray(int size) {
			return new Category[size];
		}
	};

	@PrimaryKey(autoGenerate = true)
	public long id = 0;

	@ColumnInfo(name = "name")
	public String name;

	public Category() {
	}

	private Category(Parcel in) {
		id = in.readLong();
		name = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
	}
}