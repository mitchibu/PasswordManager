package jp.gr.java_conf.mitchibu.passwordmanager.ui;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

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
		setSupportActionBar(getBinding().toolBar);

		NavHostFragment host = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host);
		controller = NavHostFragment.findNavController(Objects.requireNonNull(host));
		NavigationUI.setupActionBarWithNavController(this, controller, getBinding().drawer);
		NavigationUI.setupWithNavController(getBinding().navigation, controller);
	}

	@Override
	public void onBackPressed() {
		if(getBinding().drawer.isDrawerOpen(GravityCompat.START)) {
			getBinding().drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return NavigationUI.onNavDestinationSelected(item, controller) || super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onSupportNavigateUp() {
		return NavigationUI.navigateUp(controller, getBinding().drawer) || super.onSupportNavigateUp();
	}
}
