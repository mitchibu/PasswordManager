package jp.gr.java_conf.mitchibu.passwordmanager.ui.outline;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public abstract class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
	private final Rect rect = new Rect();

	private Drawable background = null;
	private Drawable icon = null;

	public SwipeToDeleteCallback() {
		super(0, ItemTouchHelper.START|ItemTouchHelper.END);
	}

	@Override
	public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
		return false;
	}

	@Override
	public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
		drawBackground(viewHolder.itemView, c, (int)dX);
		drawIcon(viewHolder.itemView, c, (int)dX);
		super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
	}

	private void drawBackground(View itemView, Canvas canvas, int dX) {
		if(background == null) {
			background = new ColorDrawable(ResourcesCompat.getColor(itemView.getResources(), android.R.color.holo_red_dark, null));
		}

		itemView.getDrawingRect(rect);
		rect.offset(itemView.getLeft(), itemView.getTop());
		if(dX < 0) {
			rect.left = rect.right + dX;
		} else {
			rect.right = rect.left + dX;
		}
		background.setBounds(rect);
		background.draw(canvas);
	}

	private void drawIcon(View itemView, Canvas canvas, int dX) {
		if(icon == null) {
			icon = ResourcesCompat.getDrawable(itemView.getResources(), android.R.drawable.ic_menu_delete,  null);
		}

		if(icon == null) return;
		int w = icon.getIntrinsicWidth();
		int h = icon.getIntrinsicHeight();
		itemView.getDrawingRect(rect);
		rect.offset(itemView.getLeft(), itemView.getTop());
		rect.top = rect.centerY() - h / 2;
		rect.bottom = rect.top + h;
		if(dX < 0) {
			rect.left = rect.right - 20 - w;
			rect.right = rect.left + w;
		} else {
			rect.left = rect.left + 20;
			rect.right = rect.left + 20 + w;
		}
		icon.setBounds(rect);
		icon.draw(canvas);
	}
}
