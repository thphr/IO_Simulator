package com.ur.iosimulator.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class InputFunctionInstallationNodeService
		implements SwingInstallationNodeService<InputFunctionInstallationNodeContribution, InputFunctionInstallationNodeView> {

	public InputFunctionInstallationNodeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Input Simulator";
	}

	@Override
	public InputFunctionInstallationNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new InputFunctionInstallationNodeView(apiProvider);
	}

	@Override
	public InputFunctionInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			InputFunctionInstallationNodeView view, DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new InputFunctionInstallationNodeContribution(apiProvider, view, model, context);
	}

}