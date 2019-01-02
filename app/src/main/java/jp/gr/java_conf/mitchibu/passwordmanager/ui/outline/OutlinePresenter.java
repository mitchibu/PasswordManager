package jp.gr.java_conf.mitchibu.passwordmanager.ui.outline;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.AccountView;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.view.OnItemClickListener;

public class OutlinePresenter {
	public final View.OnClickListener onClickListener = view -> showAccountFragment(view, null);
	final OnItemClickListener<AccountView> onItemClickListener = this::showAccountFragment;

	@BindingAdapter("onScrollListener")
	public static void onScrollListener(RecyclerView view, final OutlineViewModel model) {
		view.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
				model.isScrolling.setValue(newState != RecyclerView.SCROLL_STATE_IDLE);
			}
		});
	}

	private void showAccountFragment(View view, AccountView account) {
		OutlineFragmentDirections.ActionOutlineFragmentToAccountFragment directions = OutlineFragmentDirections.actionOutlineFragmentToAccountFragment();
		if(account != null) directions.setAccount(account);

		String shareName = ViewCompat.getTransitionName(view);
		ViewCompat.setTransitionName(view, shareName);
		if(shareName != null) {
			directions.setShare(shareName);
		}

		FragmentNavigator.Extras.Builder builder = new FragmentNavigator.Extras.Builder();
		if(shareName != null) builder.addSharedElement(view, shareName);
		Navigation.findNavController(view).navigate(directions, builder.build());
	}
}
