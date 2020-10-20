package com.ur.iosimulator.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarService;

/**
 * Hello world activator for the OSGi bundle URCAPS contribution
 *
 */
public class Activator implements BundleActivator {
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		bundleContext.registerService(SwingToolbarService.class, new IOToolbarNodeService(), null);
		bundleContext.registerService(SwingInstallationNodeService.class, new InputFunctionInstallationNodeService(), null);
		System.out.println("Welcome to IO Simulator");
		
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Goodbye from IO Simulator");
	}
}

