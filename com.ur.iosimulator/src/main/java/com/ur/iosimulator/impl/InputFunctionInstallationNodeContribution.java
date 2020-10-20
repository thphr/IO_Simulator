package com.ur.iosimulator.impl;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.InstallationAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.function.FunctionException;
import com.ur.urcap.api.domain.function.FunctionModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class InputFunctionInstallationNodeContribution implements InstallationNodeContribution {

	private InstallationAPI api;

	public InputFunctionInstallationNodeContribution(InstallationAPIProvider apiProvider,
			InputFunctionInstallationNodeView view, DataModel model, CreationContext context) {

		this.api = apiProvider.getInstallationAPI();
		
		this.addScriptFunctions();
	}
	
	
	private void addScriptFunctions() {
		addToFunctionModel(getMethodName("x"), "input");
		addToFunctionModel(getMethodName("64"));
		addToFunctionModel(getMethodName("65"));
		addToFunctionModel(getMethodName("66"));
		addToFunctionModel(getMethodName("67"));
		
	}

	private void addToFunctionModel(String name, String... argumentNames) {
		FunctionModel model = api.getFunctionModel();
		if (model.getFunction(name) == null) {
			try {
				model.addFunction(name, argumentNames);
			} catch (FunctionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private String getMethodName(String input_register) {
		String name = "get_simulated_input_"+input_register;
		return name;
	}
	

	@Override
	public void openView() {

	}

	@Override
	public void closeView() {

	}

	@Override
	public void generateScript(ScriptWriter writer) {
		writer.defineFunction("get_simulated_input_64");
		writer.appendLine("return read_input_boolean_register(64)");
		writer.end();
		
		writer.defineFunction("get_simulated_input_65");
		writer.appendLine("return read_input_boolean_register(65)");
		writer.end();
		
		writer.defineFunction("get_simulated_input_66");
		writer.appendLine("return read_input_boolean_register(66)");
		writer.end();
		
		writer.defineFunction("get_simulated_input_67");
		writer.appendLine("return read_input_boolean_register(67)");
		writer.end();
		
		writer.appendLine("def get_simulated_input_x(input):");
		writer.appendLine("return read_input_boolean_register(input)");
		writer.end();

	}

}