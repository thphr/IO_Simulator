package com.ur.iosimulator.impl;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ur.style.URColorPalette;
import com.ur.style.URSpacingSize;
import com.ur.style.components.URButtons;
import com.ur.style.components.URSpacing;
import com.ur.style.components.URTextFields;
import com.ur.style.components.URToggles;
import com.ur.urcap.api.contribution.toolbar.ToolbarContext;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarContribution;

public class IOToolbarNodeContribution implements SwingToolbarContribution {

	private RTDEConnection connection;
	
	private static final String IMAGEPATH_SELECTED = "/image/onButton.png";
	private static final String IMAGEPATH_DESELECTED = "/image/offButton.png";
	
	private HashMap<Integer, JButton> IO_to_buttons = new HashMap<Integer, JButton>();
	private HashMap<Integer, JToggleButton> IO_to_toggle = new HashMap<Integer, JToggleButton>();
	

	private URSpacing urSpacing = new URSpacing();
	private URButtons urButtons = new URButtons();
	private URToggles urToggles = new URToggles();
	private URTextFields urTextFields = new URTextFields();
	private URColorPalette urColorPalette = new URColorPalette();

	private JButton buttonInput_1 = urButtons.getSmallButtonEnabled("OFF", 100);
	private JButton buttonInput_2 = urButtons.getSmallButtonEnabled("OFF", 100);
	private JButton buttonInput_3 = urButtons.getSmallButtonEnabled("OFF", 100);
	private JButton buttonInput_4 = urButtons.getSmallButtonEnabled("OFF", 100);
	
	JToggleButton toggleButton_1 = urToggles.getSmallToggleSelected(80);
	JToggleButton toggleButton_2 = urToggles.getSmallToggleDeselected(80);
	JToggleButton toggleButton_3 = urToggles.getSmallToggleDeselected(80);
	JToggleButton toggleButton_4 = urToggles.getSmallToggleDeselected(80);
	
	public IOToolbarNodeContribution(ToolbarContext context) {
		connection = new RTDEConnection();
		
		
		
	}
	
	private void addToMaps() {
		IO_to_buttons.put(64, buttonInput_1);
		IO_to_buttons.put(65, buttonInput_2);
		IO_to_buttons.put(66, buttonInput_3);
		IO_to_buttons.put(67, buttonInput_4);
		
		IO_to_toggle.put(64, toggleButton_1);
		IO_to_toggle.put(65, toggleButton_2);
		IO_to_toggle.put(66, toggleButton_3);
		IO_to_toggle.put(67, toggleButton_4);
	}

	@Override
	public void buildUI(JPanel jPanel) {
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

		jPanel.add(createPairIOButtons(createTextField("Input 64: "),toggleButton_1, buttonInput_1));
		
		jPanel.add(urSpacing.createVerticalSpacing(URSpacingSize.XLARGE_VERTICAL_SPACING));
		
		jPanel.add(createPairIOButtons(createTextField("Input 65: "),toggleButton_2, buttonInput_2));
		
		jPanel.add(urSpacing.createVerticalSpacing(URSpacingSize.XLARGE_VERTICAL_SPACING));
		
		jPanel.add(createPairIOButtons(createTextField("Input 66: "),toggleButton_3, buttonInput_3));
		
		jPanel.add(urSpacing.createVerticalSpacing(URSpacingSize.XLARGE_VERTICAL_SPACING));
		
		jPanel.add(createPairIOButtons(createTextField("Input 67: "),toggleButton_4, buttonInput_4));
		
		this.setupToggleHandler();
		this.setupButtonHandler();
		this.addToMaps();

	}

	@Override
	public void openView() {
		
	}

	@Override
	public void closeView() {

	}
	
	private JTextField createTextField(String text) {
		JTextField textField = urTextFields.getTextFieldEnabled(100);
		textField.setText(text);
		return textField;
	}
	
	
	/**
	 * Creating a box with a pair og togglebutton and button.
	 * @param togglebutton
	 * @param button
	 * @return
	 */
	private Box createPairIOButtons(JTextField textfield ,JToggleButton togglebutton, JButton button) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		toggleButton_1.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		toggleButton_2.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		toggleButton_3.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		toggleButton_4.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		
		box.add(textfield);
		box.add(urSpacing.createHorizontalSpacing());
		box.add(togglebutton);
		box.add(urSpacing.createHorizontalSpacing());
		box.add(button);
		
		
		return box;
		
	}
	
	/**
	 * Add itemlistener to all four togglebuttons.
	 */
	private void setupToggleHandler() {
		
		toggleButton_1.addItemListener(togglelistener(toggleButton_1,64));
		
		toggleButton_2.addItemListener(togglelistener(toggleButton_2,65));
		
		toggleButton_3.addItemListener(togglelistener(toggleButton_3,66));
		
		toggleButton_4.addItemListener(togglelistener(toggleButton_4,67));

	}
	
	/**
	 * Creating an itemlistner for togglebutton.
	 * @param togglebutton
	 * @return
	 */
	private ItemListener togglelistener(final JToggleButton togglebutton, final int IONumber) {
		ItemListener itemListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				int state = e.getStateChange();
				
				if(state == e.SELECTED) {
					changeIOstate(IONumber, true);
					connection.sendInput(IONumber, true);
				}else {
					changeIOstate(IONumber, false);
					connection.sendInput(IONumber, false);
				}
				
			}
		};
		
		return itemListener;
	}

	/**
	 * A method for handling pressed buttons.
	 */
	private void setupButtonHandler() {
		
		this.createChangeListener(buttonInput_1, 64);
		this.createChangeListener(buttonInput_2, 65);
		this.createChangeListener(buttonInput_3, 66);
		this.createChangeListener(buttonInput_4, 67);
		
	}
	
	/**
	 * Method for creating changelistener for buttons.
	 * @param button
	 * @param IONumber
	 */
	private void createChangeListener(final JButton button, final int IONumber) {
		button.getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				ButtonModel model = (ButtonModel) e.getSource();

				if (model.isPressed()) {
					changeIOstate(IONumber, true);
					connection.sendInput(IONumber, true);
					
				}
				if (!model.isPressed()) {
					changeIOstate(IONumber, false);
					connection.sendInput(IONumber, false);
				}

			}
		});
	}

	private void changeIOstate(int IONumber, boolean state) {
		if (state) {
//			IO_to_buttons.get(IONumber).getModel().setPressed(true);
			IO_to_buttons.get(IONumber).setText("ON");
			IO_to_buttons.get(IONumber).setBackground(urColorPalette.GRAY_3);
			IO_to_toggle.get(IONumber).setIcon(createImageIcon(IMAGEPATH_SELECTED));
			
		} else {
			IO_to_buttons.get(IONumber).setText("OFF");
//			IO_to_buttons.get(IONumber).getModel().setPressed(false);
			IO_to_buttons.get(IONumber).setBackground(urColorPalette.WHITE);
			IO_to_toggle.get(IONumber).setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		}
	}

	
	private ImageIcon createImageIcon(String path) {
		ImageIcon icon = null;
		try {
			BufferedImage imgURL = ImageIO.read(getClass().getResourceAsStream(path));

			if (imgURL != null) {
				icon = new ImageIcon(imgURL);

			}
		} catch (IOException e) {
			System.out.println("NO IMAGE FOUND");
		}

		return icon;
	}

}
