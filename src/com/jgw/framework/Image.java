package com.jgw.framework;

import com.jgw.framework.Graphics.ImageFormat;

public interface Image {
	public int getWidth();
	
	public int getHeight();
	
	public ImageFormat getFormat();
	
	public void dispose();
}
