package com.jgw.framework.implementation;

import java.util.List;

import com.jgw.framework.Input.TouchEvent;

import android.view.View.OnTouchListener;

public interface TouchHandler extends OnTouchListener {
	public boolean isTouchDown(int pointer);

	public int getTouchX(int pointer);
	
	public int getTouchY(int pointer);
	
	public List<TouchEvent> getTouchEvents();
}
