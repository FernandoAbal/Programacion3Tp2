package human_clustering.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfaceAmountOfGroups_View {
private JFrame frame;
	
	private JLabel labelAmountOfGroups;

	private JButton buttonAddPerson;
	private JButton buttonBackToMenu;

	private JPanel panel;
	
	private InterfaceMenu_View view;

	private JTextField textFieldAmountOfGroups;
	

	
	public void InterfaceAmountOfGroups_View(JFrame view){
		
		frame = view;
		labelAmountOfGroups = new JLabel("Amount Of Groups:");
        labelAmountOfGroups.setForeground(new Color(255, 255, 255));
        labelAmountOfGroups.setBounds(300, 11, 220, 22);
        labelAmountOfGroups.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(labelAmountOfGroups);
		
        
		textFieldAmountOfGroups = new JTextField();
		textFieldAmountOfGroups.setBounds(602, 14, 223, 20);
		frame.getContentPane().add(textFieldAmountOfGroups);
		textFieldAmountOfGroups.setColumns(10);
        
        
		
		buttonAddPerson = new JButton("Set Amount");
		buttonAddPerson.setBounds(10, 30, 200, 45);
		buttonColor(buttonAddPerson);
		frame.getContentPane().add(buttonAddPerson);
		
		buttonBackToMenu = new JButton("Back to Menu");
		buttonBackToMenu.setBounds(10, 141, 200, 45);
		buttonColor(buttonBackToMenu);
		frame.getContentPane().add(buttonBackToMenu);
		
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 170, 0));
		panel.setBounds(0, 0, 238, 761);
		frame.getContentPane().add(panel);
		
		frame.repaint();	
	}
	
	public JButton buttonColor(JButton button) {
    	button.setBorderPainted(false);
    	button.setForeground(new Color(255, 255, 255));
    	button.setBackground(new Color(116, 0, 255));
    	return button;
    	
    }
	
	
	public void changeStateVisibleOrNotAmountOfGroups() {
		labelAmountOfGroups.setVisible(!labelAmountOfGroups.isVisible());
			
		textFieldAmountOfGroups.setVisible(!textFieldAmountOfGroups.isVisible());

		buttonAddPerson.setVisible(!buttonAddPerson.isVisible());
		buttonBackToMenu.setVisible(!buttonBackToMenu.isVisible());

		panel.setVisible(!panel.isVisible());
	}
	
	public JButton changeColorButtonBlack(JButton button) {
    	button.setBackground(new Color(255, 255, 255));
		return button;
    }
    
    public JButton changeColorButtonRestoreOriginalColor(JButton button) {
    	button.setBackground(new Color(140, 140, 140));
		return button;
    }

	public JLabel getLabelNameOfPerson() {
		return labelAmountOfGroups;
	}


	public JButton getButtonAddPerson() {
		return buttonAddPerson;
	}

	public JButton getButtonBackToMenu() {
		return buttonBackToMenu;
	}

	public JPanel getPanel() {
		return panel;
	}

	public InterfaceMenu_View getView() {
		return view;
	}

	public JTextField getTextFieldOfThePerson() {
		return textFieldAmountOfGroups;
	}

	
	
	

}
