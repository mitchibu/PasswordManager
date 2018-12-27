package jp.gr.java_conf.mitchibu.passwordmanager.ui;

import android.os.Bundle;

import java.util.Objects;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import jp.gr.java_conf.mitchibu.passwordmanager.R;
import jp.gr.java_conf.mitchibu.passwordmanager.databinding.MainActivityBinding;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.BindLayout;
import jp.gr.java_conf.mitchibu.passwordmanager.ui.base.DataBindingActivity;

@BindLayout(R.layout.main_activity)
public class MainActivity extends DataBindingActivity<MainActivityBinding> {
	private NavController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		NavHostFragment host = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host);
		controller = NavHostFragment.findNavController(Objects.requireNonNull(host));
		NavigationUI.setupActionBarWithNavController(this, controller);
	}

	@Override
	public boolean onSupportNavigateUp() {
		return controller.navigateUp() || super.onSupportNavigateUp();
	}
}
