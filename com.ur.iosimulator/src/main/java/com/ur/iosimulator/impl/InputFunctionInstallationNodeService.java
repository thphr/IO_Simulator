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
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {

	}

	@Override
	public String getTitle(Locale locale) {
		return "Input Simulator";
	}

	@Override
	public InputFunctionInstallationNodeView createView(ViewAPIProvider apiProvider) {
		return new InputFunctionInstallationNodeView(apiProvider);
	}

	@Override
	public InputFunctionInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			InputFunctionInstallationNodeView view, DataModel model, CreationContext context) {
		return new InputFunctionInstallationNodeContribution(apiProvider, view, model, context);
	}

}