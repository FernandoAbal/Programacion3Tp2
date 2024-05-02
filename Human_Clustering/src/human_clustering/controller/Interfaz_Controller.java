package human_clustering.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import human_clustering.model.ConjuntoVertices;
import human_clustering.model.ConnectedComponentsPrinter;
import human_clustering.model.Graph;
import human_clustering.model.Vertice;
import human_clustering.view.InterfaceAmountOfGroups_View;
import human_clustering.view.InterfaceMenu_View;
import human_clustering.view.InterfaceRegisterPeople_View;
import human_clustering.view.InterfaceSeeAllPeopleRegister;

public class Interfaz_Controller {
	
	private InterfaceMenu_View viewMenu;
	private InterfaceRegisterPeople_View viewInterfaceRegister;
	private InterfaceSeeAllPeopleRegister viewInterfaceSeeAllPeopleRegister;
	private InterfaceAmountOfGroups_View viewInterfaceAmountOfGroups;
	
	private Graph peopleData;
	private Graph grafoAGM;
	
	//Menu
	private JButton bottonRegisterPeople;
	private JButton bottonSeeAllPeopleRegister;
	private JButton bottonAmountOfGrups;
	private JButton bottonSeeAllGroups;
	private JButton bottonExit;
	
	
	//Register People
	
	private JButton buttonAddPerson;
	private JButton buttonBackToMenu;

	private int[] personFillfillDataRegister;
	
	private int cantGrupos;
	
	private JButton[] buttonsInterestSports;
	private JButton[] buttonsMusicInterest;
	private JButton[] buttonsEntertainmentNewsInterests;
	private JButton[] buttonsScienceInterests;
	
	
	private JLabel labelNameOfPerson;
	
	private JTextField textFieldOfThePerson;
	
	
	private JButton buttonBackToMenuSeeAllPeopleRegister;
	
	private List<Set<Vertice>> connectedComponents;
	
	private ConjuntoVertices conjuntoVertices;
	
	private int[] personsQualify;
	
	private String nombre;
	
	
	public Interfaz_Controller(InterfaceMenu_View viewMenu,InterfaceAmountOfGroups_View viewInterfaceAmountOfGroups, InterfaceRegisterPeople_View viewInterfaceRegister, InterfaceSeeAllPeopleRegister viewInterfaceSeeAllPeopleRegister, Graph peopleData) {
		this.viewMenu=viewMenu;
		this.viewInterfaceRegister = viewInterfaceRegister;
		this.viewInterfaceSeeAllPeopleRegister = viewInterfaceSeeAllPeopleRegister;
		this.viewInterfaceAmountOfGroups = viewInterfaceAmountOfGroups;
		this.peopleData = peopleData;
		conjuntoVertices = new ConjuntoVertices();
		attachListenersMenu();
	}
	public boolean crearGrafoAGM() {
		if (grafoAGM == null || conjuntoVertices.getVertices().size() <= 0) {
			throw new RuntimeException("Debe agregar vertices para poder crear el AGM");
		}
		if (grafoAGM != null) {
			grafoAGM.generarAGM(conjuntoVertices.getVertices());
			return true;
		}
		return false;
	}

	
	private void attachListenersMenu() {
		bottonRegisterPeople = viewMenu.getBottonRegisterPeople();
		bottonSeeAllPeopleRegister = viewMenu.getBottonSeeAllPeopleRegister();
		bottonAmountOfGrups= viewMenu.getBottonAmountOfGrups();
		bottonSeeAllGroups = viewMenu.getBottonSeeAllGroups();
		bottonExit =viewMenu.getBottonExit();
		
		bottonRegisterPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewInterfaceRegister.InterfaceRegisterPeople(viewMenu.getFrame());
				viewMenu.changeStateVisibleOrNotAllMenu();
				attachListenersRegisterPeople();
				
			}
		});
		
		
		bottonSeeAllPeopleRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!peopleData.vertices.isEmpty() ) {
					viewMenu.changeStateVisibleOrNotAllMenu();
//					viewInterfaceSeeAllPeopleRegister.InterfaceSeeAllPeopleRegisterVisual(viewMenu.getFrame(), peopleData.toSeeDataOfAllPeople());
					attachListenersSeeAllPeopleRegister();
				}
			}
		});
		
		
		bottonAmountOfGrups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!peopleData.vertices.isEmpty() ) {
					viewMenu.changeStateVisibleOrNotAllMenu();
					viewInterfaceAmountOfGroups.InterfaceAmountOfGroups_View(viewMenu.getFrame());
					attachListenersSeeAllPeopleRegister();
				}
			}
		});
		
		bottonSeeAllGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grafoAGM.generarAGM(conjuntoVertices.getVertices());
				grafoAGM.generarDosGrupos();

				
			}
		});
		
		bottonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
	}
	
	private void attachListenersRegisterPeople() {
		buttonBackToMenu = viewInterfaceRegister.getButtonBackToMenu();
		buttonAddPerson = viewInterfaceRegister.getButtonAddPerson();
		
		buttonsInterestSports = viewInterfaceRegister.getButtonsInterestSports();
		buttonsMusicInterest = viewInterfaceRegister.getButtonsMusicInterest();
		buttonsEntertainmentNewsInterests = viewInterfaceRegister.getButtonsEntertainmentNewsInterests();
		buttonsScienceInterests = viewInterfaceRegister.getButtonsScienceInterests();
		
		
		textFieldOfThePerson = viewInterfaceRegister.getTextFieldOfThePerson();
		
		
		personFillfillDataRegister = new int[4];
		
		buttonAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean personsQualifyFilled = true;
				for (int i = 0; i < 4; i++) {
					if (personsQualify[i] == 0) {
						personsQualifyFilled = false;
					}
				}
				if (personsQualifyFilled) {
					viewMenu.changeStateVisibleOrNotAllMenu();
					viewInterfaceRegister.changeStateVisibleOrNotAllRegistyerPeople();
					viewMenu.setTextinMainMenu();
						conjuntoVertices.crearVertice(nombre, personsQualify[0], personsQualify[1], personsQualify[2],
								personsQualify[3]);
						personsQualify = new int[4];
					
			
				}
			}
		});
					viewMenu.changeStateVisibleOrNotAllMenu();
					viewInterfaceRegister.changeStateVisibleOrNotAllRegistyerPeople();
					viewMenu.setTextinMainMenu();
			
		
		
		
		
		for (int row = 1; row<6; row++) {
			final int value = row;
			
			
			buttonsInterestSports[row].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personFillfillDataRegister[0] = value+1;
					for (int column = 1; column<6; column++) {
						viewMenu.changeColorButtonBlack(buttonsInterestSports[column]);
					}
					viewMenu.changeColorButtonRestoreOriginalColor(buttonsInterestSports[value]);
				}
			});
			
			buttonsMusicInterest[row].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personFillfillDataRegister[1] = value+1;
					for (int column = 1; column<6; column++) {
						viewMenu.changeColorButtonBlack(buttonsMusicInterest[column]);
					}
					viewMenu.changeColorButtonRestoreOriginalColor(buttonsMusicInterest[value]);
				}
			});
			
			buttonsEntertainmentNewsInterests[row].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personFillfillDataRegister[2] = value+1;
					for (int column = 1; column<6; column++) {
						buttonsEntertainmentNewsInterests[column].setBackground(new Color(255, 255, 255));
					}
					viewMenu.changeColorButtonRestoreOriginalColor(buttonsEntertainmentNewsInterests[value]);
				}
			});
			
			
			buttonsScienceInterests[row].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personFillfillDataRegister[3] = value+1;
					for (int column = 1; column<6; column++) {
						buttonsScienceInterests[column].setBackground(new Color(255, 255, 255));
					}
					viewMenu.changeColorButtonRestoreOriginalColor(buttonsScienceInterests[value]);
				}
			});
			
			
		}
		
		
		buttonBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMenu.changeStateVisibleOrNotAllMenu();
				viewInterfaceRegister.changeStateVisibleOrNotAllRegistyerPeople();
				viewMenu.getFrame().repaint();
			}
		});
	}
	
	
	private void attachListenersSeeAllPeopleRegister() {
		buttonBackToMenuSeeAllPeopleRegister = viewInterfaceSeeAllPeopleRegister.getButtonBackToMenu();
		
		buttonBackToMenuSeeAllPeopleRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMenu.changeStateVisibleOrNotAllMenu();
				viewInterfaceSeeAllPeopleRegister.changeStateVisibleOrNotAllSeeAllPeopleRegisterVisual();
				viewMenu.getFrame().repaint();
			}
		});
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	InterfaceRegisterPeople_View interfaceRegisterPeople = new InterfaceRegisterPeople_View();
            	InterfaceMenu_View viewMenu = new InterfaceMenu_View();
            	InterfaceSeeAllPeopleRegister  viewInterfaceSeeAllPeopleRegister = new InterfaceSeeAllPeopleRegister ();
            	InterfaceAmountOfGroups_View  viewInterfaceAmountOfGroups = new InterfaceAmountOfGroups_View();
            	
            	Graph peopleData = new Graph();
                new Interfaz_Controller(viewMenu, viewInterfaceAmountOfGroups ,interfaceRegisterPeople, viewInterfaceSeeAllPeopleRegister, peopleData);
            	
            }
        });

	}

}
