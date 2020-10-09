package com.ur.iosimulator.impl;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import com.ur.urcap.api.contribution.toolbar.ToolbarConfiguration;
import com.ur.urcap.api.contribution.toolbar.ToolbarContext;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarContribution;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarService;

public class IOToolbarNodeService implements SwingToolbarService {

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource("/image/I_O Icon.png"));
	}

	@Override
	public void configureContribution(ToolbarConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public SwingToolbarContribution createToolbar(ToolbarContext context) {
		// TODO Auto-generated method stub
		return new IOToolbarNodeContribution(context);
	}

}
