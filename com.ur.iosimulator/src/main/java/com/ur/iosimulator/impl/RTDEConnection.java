package com.ur.iosimulator.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.o5h.skynet.ur.rtde.RTDEClient;
import com.github.o5h.skynet.ur.rtde.RTDEInputParam;
import com.github.o5h.skynet.ur.rtde.RTDEOutputHandler;

public class RTDEConnection {

	private RTDEClient client;
	private static final Logger LOG = LoggerFactory.getLogger(RTDEConnection.class);
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	private  int[] ids = { 0 };

	private HashMap<Integer, RTDEInputParam> input_registers = new HashMap<Integer, RTDEInputParam>();
	private HashMap<Integer,Boolean> inputData = new HashMap<Integer, Boolean>();
	private ArrayList<Integer> registerOrder = new ArrayList<Integer>();
	
	private boolean isConnected = false;

	public RTDEConnection() {
		createClient();
		
	}
	
	/**
	 * Updates/stores the new value of the input register based on 
	 * the input register number and calls sendData method.
	 * @param inputRegister [64..127]
	 * @param inputValue of the input register
	 */
	public void sendInput(int inputRegister, boolean inputValue) {
		
		if (connect()) {
			if(input_registers.containsKey(inputRegister)) {
				inputData.put(inputRegister, inputValue);
				
				sendData();
			}
		}

	}
	

	/**
	 * Initiate the input register and its param and value.
	 * Calls every time a new connection is made.
	 */
	private void addInputRegisters() {
		input_registers.clear();
		inputData.clear();
		registerOrder.clear();
	
		addRegister(64, RTDEInputParam.input_bit_register_64);
		addRegister(65, RTDEInputParam.input_bit_register_65);
		addRegister(66, RTDEInputParam.input_bit_register_66);
		addRegister(67, RTDEInputParam.input_bit_register_67);
		
	}
	/**
	 * Adding input registers.
	 * @param inputRegister [64...127]
	 * @param param [input_bit_register_X]
	 */
	private void addRegister(int inputRegister, RTDEInputParam param) {
		input_registers.put(inputRegister,param);
		inputData.put(inputRegister,false);
		registerOrder.add(inputRegister);
	}
	
	/**
	 * Ensures that you connects to the robot 
	 * and returns status for the connection.
	 * @return
	 */
	private boolean connect() {
		if (!isConnected) {
			if (client.connect("127.0.0.1", RTDEClient.PORT, 0)) {
				addInputRegisters();
				registerInputs();
				isConnected = true;
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
		 
	}
	
	/**
	 * Register the input registers to the robot.
	 */
	private void registerInputs() {
		if (client.connect("127.0.0.1", RTDEClient.PORT, 0)) {
			try {
				RTDEInputParam[] tmparr = new RTDEInputParam[input_registers.size()];
				int I = 0;
				for (RTDEInputParam rtdeInputParam : input_registers.values()) {
					tmparr[I] = rtdeInputParam;
					I++;
				}
				client.setupInputs(tmparr);
				countDownLatch.await();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}


	/**
	 * Saves the input register value in a new array
	 * which is then send to the robot.
	 */
	private void sendData() {
		Boolean[] currentValueState = new Boolean[inputData.size()];
		int index = 0;
		for (Integer currentRegister : registerOrder) {
			currentValueState[index] = inputData.get(currentRegister);
			index++;
		}
		try {
			client.sendData(ids[0], currentValueState);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a new RTDE client.
	 */
	private void createClient() {
		client = new RTDEClient(new RTDEOutputHandler() {
			@Override
			public void onSetupInputsResponse(int id, RTDEInputParam[] supported, RTDEInputParam[] unsupported) {
				LOG.info("INPUTS {} {} {}", id, supported, unsupported);
				ids[0] = id;
				countDownLatch.countDown();
			}

		});
	}

	
	/**
	 * Disconnects from the robot. 
	 * Called in the Contribution class, closeView method.
	 */
	public void disconnectClient() {
		client.disconnect();
	}
	

}
