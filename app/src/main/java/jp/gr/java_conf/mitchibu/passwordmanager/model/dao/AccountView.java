package jp.gr.java_conf.mitchibu.passwordmanager.model.dao;

import android.arch.persistence.room.Embedded;
import android.os.Parcel;
import android.os.Parcelable;

public class AccountView implements Parcelable {
	public static final Creator<AccountView> CREATOR = new Creator<AccountView>() {
		@Override
		public AccountView createFromParcel(Parcel in) {
			return new AccountView(in);
		}

		@Override
		public AccountView[] newArray(int size) {
			return new AccountView[size];
		}
	};

	@Embedded
	public Account account;
	@Embedded(prefix = "c_")
	public Category category;

	public AccountView() {
	}

	private AccountView(Parcel in) {
		account = in.readParcelable(Account.class.getClassLoader());
		category = in.readParcelable(Category.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(account, flags);
		dest.writeParcelable(category, flags);
	}
}
