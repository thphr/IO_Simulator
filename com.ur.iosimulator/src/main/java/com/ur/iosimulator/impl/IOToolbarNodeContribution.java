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
import com.ur.style.components.URToggles;
import com.ur.urcap.api.contribution.toolbar.ToolbarContext;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarContribution;

public class IOToolbarNodeContribution implements SwingToolbarContribution {

	private JLabel label = new JLabel();
	
	private static final String IMAGEPATH_SELECTED = "/image/G5_radio_button_selected_enabled.png";
	private static final String IMAGEPATH_DESELECTED = "/image/G5_radio_button_deselected_enabled.png";

	private URSpacing urSpacing = new URSpacing();
	private URButtons urButtons = new URButtons();
	private URToggles urToggles = new URToggles();

	private JButton buttonIO_1 = urButtons.getSmallButtonEnabled("IO:1 OFF", 100);
	private JButton buttonIO_2 = urButtons.getSmallButtonEnabled("IO:2 OFF", 100);
	private JButton buttonIO_3 = urButtons.getSmallButtonEnabled("IO:3 OFF", 100);
	private JButton buttonIO_4 = urButtons.getSmallButtonEnabled("IO:4 OFF", 100);
	
	JToggleButton toggleButton_1 = urToggles.getSmallToggleSelected(50);
	JToggleButton toggleButton_2 = urToggles.getSmallToggleDeselected(50);
	JToggleButton toggleButton_3 = urToggles.getSmallToggleDeselected(50);
	JToggleButton toggleButton_4 = urToggles.getSmallToggleDeselected(50);
	
	public IOToolbarNodeContribution(ToolbarContext context) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildUI(JPanel jPanel) {
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

		jPanel.add(createPairIOButtons(toggleButton_1, buttonIO_1));
		
		jPanel.add(urSpacing.createVerticalSpacing(URSpacingSize.XLARGE_VERTICAL_SPACING));
		
		jPanel.add(createPairIOButtons(toggleButton_2, buttonIO_2));
		
		jPanel.add(urSpacing.createVerticalSpacing(URSpacingSize.XLARGE_VERTICAL_SPACING));
		
		jPanel.add(createPairIOButtons(toggleButton_3, buttonIO_3));
		
		jPanel.add(urSpacing.createVerticalSpacing(URSpacingSize.XLARGE_VERTICAL_SPACING));
		
		jPanel.add(createPairIOButtons(toggleButton_4, buttonIO_4));
		
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
	
	
	/**
	 * Creating a box with a pair og togglebutton and button.
	 * @param togglebutton
	 * @param button
	 * @return
	 */
	private Box createPairIOButtons(JToggleButton togglebutton, JButton button) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		toggleButton_1.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		toggleButton_2.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		toggleButton_3.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		toggleButton_4.setIcon(createImageIcon(IMAGEPATH_DESELECTED));
		
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
		
		this.createChangeListener(buttonIO_1, 1);
		this.createChangeListener(buttonIO_2, 2);
		this.createChangeListener(buttonIO_3, 3);
		this.createChangeListener(buttonIO_4, 4);
		
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
					button.setText("IO: "+ IONumber + " ON");
				}
				if (!model.isPressed()) {
					button.setText("IO: "+ IONumber + "  OFF");
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
