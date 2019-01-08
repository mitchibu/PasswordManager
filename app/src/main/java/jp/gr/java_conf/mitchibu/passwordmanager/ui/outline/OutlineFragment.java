package jp.gr.java_conf.mitchibu.passwordmanager.ui.outline;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import jp.gr.java_conf.mitchibu.passwordmanager.R;
import jp.gr.java_conf.mitchibu.passwordmanager.databinding.OutlineFragmentBinding;
import jp.gr.java_conf.mitchibu.passwordmanager.model.dao.MyDatabase;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.BindLayout;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.DataBindingFragment;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.util.MyViewModelFactory;

@BindLayout(R.layout.outline_fragment)
public class OutlineFragment extends DataBindingFragment<OutlineFragmentBinding> {
	@Inject
	MyViewModelFactory<MyDatabase> factory;

	private final CompositeDisposable disposables = new CompositeDisposable();
	private final OutlineAdapter adapter = new OutlineAdapter();

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		postponeEnterTransition();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getBinding().setModel(ViewModelProviders.of(this, factory).get(OutlineViewModel.class));
		getBinding().setPresenter(new OutlinePresenter());

		getBinding().list.setAdapter(adapter);
		getBinding().list.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> startPostponedEnterTransition());
		adapter.setOnItemClickListener(getBinding().getPresenter().onItemClickListener);

		ItemTouchHelper helper = new ItemTouchHelper(new SwipeToDeleteCallback() {
			@Override
			public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction) {
				android.util.Log.v("test", "onSwiped: " + direction);
				getBinding().getModel().delete(holder.getItemId());
			}
		});
		helper.attachToRecyclerView(getBinding().list);
	}

	@Override
	public void onStart() {
		super.onStart();
		disposables.add(getBinding().getModel().data
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(adapter::submitList, Throwable::printStackTrace));
	}

	@Override
	public void onStop() {
		super.onStop();
		disposables.clear();
	}
}
