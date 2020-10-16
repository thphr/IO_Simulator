package com.ur.iosimulator.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ur.style.URIcon;
import com.ur.style.URSpacingSize;
import com.ur.style.components.URButtons;
import com.ur.style.components.URSpacing;
import com.ur.style.components.URTextFields;
import com.ur.style.components.URToggles;
import com.ur.urcap.api.contribution.toolbar.ToolbarContext;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarContribution;

public class IOToolbarNodeContribution implements SwingToolbarContribution {

	private JLabel label = new JLabel();
	
	private static final String IMAGEPATH_SELECTED = "/image/onButton.png";
	private static final String IMAGEPATH_DESELECTED = "/image/offButton.png";
	

	private URSpacing urSpacing = new URSpacing();
	private URButtons urButtons = new URButtons();
	private URToggles urToggles = new URToggles();
	private URTextFields urTextFields = new URTextFields();

	private JButton buttonInput_1 = urButtons.getSmallButtonEnabled("OFF", 100);
	private JButton buttonInput_2 = urButtons.getSmallButtonEnabled("OFF", 100);
	private JButton buttonInput_3 = urButtons.getSmallButtonEnabled("OFF", 100);
	private JButton buttonInput_4 = urButtons.getSmallButtonEnabled("OFF", 100);
	
	JToggleButton toggleButton_1 = urToggles.getSmallToggleSelected(80);
	JToggleButton toggleButton_2 = urToggles.getSmallToggleDeselected(80);
	JToggleButton toggleButton_3 = urToggles.getSmallToggleDeselected(80);
	JToggleButton toggleButton_4 = urToggles.getSmallToggleDeselected(80);
	
	public IOToolbarNodeContribution(ToolbarContext context) {
		// TODO Auto-generated constructor stub
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
		
		this.selectedToggle();
		this.pressedButton();

	}

	@Override
	public void openView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub

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
	private void selectedToggle() {
		
		toggleButton_1.addItemListener(createItemlistener(toggleButton_1,1));
		
		toggleButton_2.addItemListener(createItemlistener(toggleButton_2,2));
		
		toggleButton_3.addItemListener(createItemlistener(toggleButton_3,3));
		
		toggleButton_4.addItemListener(createItemlistener(toggleButton_4,4));

	}
	
	/**
	 * Creating an itemlistner for togglebutton.
	 * @param togglebutton
	 * @return
	 */
	private ItemListener createItemlistener(final JToggleButton togglebutton, int IONumber) {
		ItemListener itemListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				int state = e.getStateChange();
				
				if(state == e.SELECTED) {
					System.out.println("SELECTED");
					togglebutton.setIcon(createImageIcon(IMAGEPATH_SELECTED));
				}else {
					System.out.println("DESELECTED");
					togglebutton.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
				}
				
			}
		};
		
		return itemListener;
	}

	/**
	 * A method for handling pressed buttons.
	 */
	private void pressedButton() {
		
		this.createChangeListener(buttonInput_1, 1);
		this.createChangeListener(buttonInput_2, 2);
		this.createChangeListener(buttonInput_3, 3);
		this.createChangeListener(buttonInput_4, 4);
		
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
					button.setText("ON");
				}
				if (!model.isPressed()) {
					button.setText("OFF");
				}

			}
		});
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
