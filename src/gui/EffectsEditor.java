package gui;

import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;

import tables.ControllingProgram;
import tables.GemKind;
import tables.ItemKind;
import tables.RollableEffect;
import tables.Table;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.Color;

public class EffectsEditor {

	private static Table selectedTable;
	private static RollableEffect currentlyUsedEffect;
	
	private static JFrame frame;
	private static JTextField tName;
	private static JTextField tMin;
	private static JTextField tMax;
	private static EffectsEditor effectsEditor;
	private static JPanel panel;
	private static JLabel lblName;
	private static JLabel lblDescription;
	private static JLabel lblMinRoll;
	private static JLabel lblMaxRoll;
	private static JCheckBox checkBoxItem;
	private static JComboBox cBoxItem;
	private static JCheckBox checkBoxGem;
	private static JComboBox cBoxGem;
	private static JButton btnTables;
	private static JTextPane tDescription;
	private static JLabel lblGems;
	private static JCheckBox checkBoxTable;
	private static JComboBox cBoxTable;
	private static JButton btnGems;
	private static JLabel lblTables;
	private static JComboBox cRollableEffect;
	private static JButton btnClose;
	private static JButton btnSave;
	private static JTextPane tpGems;
	private static JTextPane tpTables;
	
	/**
	 * Launch the application.
	 */
	public static void startEdit(Table sTable) {
		selectedTable=sTable;
		if(effectsEditor==null){
			EventQueue.invokeLater(new Runnable() {
				
				public void run() {
					try {
						EffectsEditor window = new EffectsEditor();
						window.frame.setVisible(true);
						effectsEditor=window;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else{
			effectsEditor.frame.setVisible(true);
		}
	}

	/**
	 * Create the application.
	 */
	public EffectsEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 479, 319);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(0);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 473, 292);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblName = new JLabel("name");
		lblName.setBounds(10, 67, 46, 14);
		panel.add(lblName);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(172, 67, 77, 14);
		panel.add(lblDescription);
		
		lblMinRoll = new JLabel("min roll");
		lblMinRoll.setBounds(10, 98, 46, 14);
		panel.add(lblMinRoll);
		
		lblMaxRoll = new JLabel("max roll");
		lblMaxRoll.setBounds(10, 129, 46, 14);
		panel.add(lblMaxRoll);
		
		tName = new JTextField();
		tName.setBounds(76, 64, 86, 20);
		panel.add(tName);
		tName.setColumns(10);
		
		tMin = new JTextField();
		tMin.setBounds(132, 95, 30, 20);
		panel.add(tMin);
		tMin.setColumns(10);
		
		tMax = new JTextField();
		tMax.setBounds(132, 126, 30, 20);
		panel.add(tMax);
		tMax.setColumns(10);
		
		checkBoxItem = new JCheckBox("item required");
		checkBoxItem.setBounds(10, 150, 142, 23);
		panel.add(checkBoxItem);
		checkBoxItem.setHorizontalAlignment(SwingConstants.LEFT);
		
		cBoxItem = new JComboBox();
		cBoxItem.setBounds(10, 180, 142, 20);
		panel.add(cBoxItem);
		
		checkBoxGem = new JCheckBox("gems required");
		checkBoxGem.setBounds(161, 150, 142, 23);
		panel.add(checkBoxGem);
		
		cBoxGem = new JComboBox();
		cBoxGem.setBounds(162, 180, 112, 20);
		panel.add(cBoxGem);
		
		btnTables = new JButton("+");
		btnTables.setBounds(430, 180, 20, 20);
		panel.add(btnTables);
		btnTables.setMargin(new Insets(0,0,0,0));
		
		tDescription = new JTextPane();
		tDescription.setBounds(168, 84, 292, 59);
		panel.add(tDescription);
		
		lblGems = new JLabel("required Gems:");
		lblGems.setBounds(161, 211, 142, 14);
		panel.add(lblGems);
		
		checkBoxTable = new JCheckBox("uses Subtables");
		checkBoxTable.setBounds(310, 150, 140, 23);
		panel.add(checkBoxTable);
		
		cBoxTable = new JComboBox();
		cBoxTable.setBounds(312, 180, 112, 20);
		panel.add(cBoxTable);
		
		btnGems = new JButton("+");
		btnGems.setBounds(280, 180, 20, 20);
		panel.add(btnGems);
		btnGems.setMargin(new Insets(0,0,0,0));
		
		lblTables = new JLabel("subtables:");
		lblTables.setBounds(313, 211, 140, 14);
		panel.add(lblTables);
		
		cRollableEffect = new JComboBox();
		cRollableEffect.setBounds(10, 11, 444, 20);
		panel.add(cRollableEffect);
		
		btnClose = new JButton("close");
		btnClose.setBounds(368, 33, 86, 23);
		panel.add(btnClose);
		
		btnSave = new JButton("save");
		btnSave.setBounds(9, 33, 355, 23);
		panel.add(btnSave);
		
		tpGems = new JTextPane();
		tpGems.setBounds(161, 231, 142, 57);
		panel.add(tpGems);
		tpGems.setBackground(Color.WHITE);
		
		tpTables = new JTextPane();
		tpTables.setBounds(311, 231, 139, 57);
		panel.add(tpTables);
		tpTables.setBackground(Color.WHITE);
		
		for(ItemKind ik:ItemKind.itemKinds)
			cBoxItem.addItem(ik);
		for(GemKind gk:GemKind.gemKinds)
			cBoxGem.addItem(gk);
		for(Table t:Table.tables)
			cBoxTable.addItem(t);
		for(RollableEffect rEffect:selectedTable.getRollableEffects())
			cRollableEffect.addItem(rEffect);
		
		cRollableEffect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadEffect();
			}
		});
		
		checkBoxItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cBoxItem.setVisible(checkBoxItem.isSelected());
			}
		});
		
		btnGems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newGems;
				if(tpGems.getText().length()>1){
					newGems=tpGems.getText()+", "+cBoxGem.getSelectedItem();
				} else {
					newGems=cBoxGem.getSelectedItem().toString();
				}
				tpGems.setText(newGems);
			}
		});
		
		btnTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newTables;
				if(tpTables.getText().length()>1){
					newTables=tpTables.getText()+", "+cBoxTable.getSelectedItem();
				} else {
					newTables=cBoxTable.getSelectedItem().toString();
				}
				tpTables.setText(newTables);
			}
		});
		
		checkBoxGem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				cBoxGem.setVisible(checkBoxGem.isSelected());
				tpGems.setVisible(checkBoxGem.isSelected());
				btnGems.setVisible(checkBoxGem.isSelected());
				lblGems.setVisible(checkBoxGem.isSelected());
			}
		});
		
		checkBoxTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				cBoxTable.setVisible(checkBoxTable.isSelected());
				tpTables.setVisible(checkBoxTable.isSelected());
				btnTables.setVisible(checkBoxTable.isSelected());
				lblTables.setVisible(checkBoxTable.isSelected());
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
				effectsEditor=null;
				frame=null;
				MainWindow.updateTableDisplay();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentlyUsedEffect.setEffect(tName.getText(), tDescription.getText());
				ItemKind reqItem=null;
				GemKind[] reqGem=null;
				if(checkBoxItem.isSelected()){
					reqItem=(ItemKind) cBoxItem.getSelectedItem();
				}
				if(checkBoxGem.isSelected()){
					reqGem=readGems(tpGems.getText());
				}
				if(reqItem!=null || reqGem!=null){
					currentlyUsedEffect.setRequirements(reqItem, reqGem);
				}
				
				if(checkBoxTable.isSelected()){
					currentlyUsedEffect.setTable(readTables(tpTables.getText()));
				} else {
					currentlyUsedEffect.setTable(new ArrayList<Table>());
				}
				MainWindow.saveToFile(selectedTable);
			}
		});
	
		loadEffect();
	}
	
	public static void loadEffect(){
		currentlyUsedEffect=(RollableEffect) cRollableEffect.getSelectedItem();
		tName.setText(currentlyUsedEffect.toString());
		tMin.setText(""+currentlyUsedEffect.getMinRoll());
		tMax.setText(""+currentlyUsedEffect.getMaxRoll());
		
		if(currentlyUsedEffect.getRequiredItemKind()!=null){
			checkBoxItem.setSelected(true);
			cBoxItem.setSelectedItem(currentlyUsedEffect.getRequiredItemKind());
			cBoxItem.setVisible(true);
			System.out.println("requires Item");
		}
		else{
			checkBoxItem.setSelected(false);
			cBoxItem.setVisible(false);
			System.out.println("doesnt requires Item");
		}

		if(currentlyUsedEffect.getRequiredGemKind()!=null 
				&& currentlyUsedEffect.getRequiredGemKind().length>0
				&& currentlyUsedEffect.getRequiredGemKind()[0]!=null){
			checkBoxGem.setSelected(true);
			cBoxGem.setVisible(checkBoxGem.isSelected());
			tpGems.setVisible(checkBoxGem.isSelected());
			btnGems.setVisible(checkBoxGem.isSelected());
			lblGems.setVisible(checkBoxGem.isSelected());
			for(GemKind gem:currentlyUsedEffect.getRequiredGemKind()){
				String gems;
				if(gem!=currentlyUsedEffect.getRequiredGemKind()[0]){
					gems=tpGems.getText()+", "+gem;
				}else {
					gems=""+gem;
				}
				tpGems.setText(gems);
			}

		} else {
			checkBoxGem.setSelected(false);
			cBoxGem.setVisible(checkBoxGem.isSelected());
			tpGems.setVisible(checkBoxGem.isSelected());
			btnGems.setVisible(checkBoxGem.isSelected());
			lblGems.setVisible(checkBoxGem.isSelected());
		}
		
		if(currentlyUsedEffect.getTable()!=null 
				&& currentlyUsedEffect.getTable().isEmpty()!=true
				&& currentlyUsedEffect.getTable().get(0)!=null){
			checkBoxTable.setSelected(true);
			cBoxTable.setVisible(checkBoxTable.isSelected());
			tpTables.setVisible(checkBoxTable.isSelected());
			btnTables.setVisible(checkBoxTable.isSelected());
			lblTables.setVisible(checkBoxTable.isSelected());
			for(Table table:currentlyUsedEffect.getTable()){
				String tables;
				if(table!=currentlyUsedEffect.getTable().get(0)){
					tables=tpTables.getText()+", "+table.getName();
				} else {
					tables=table.getName();
				}
				tpTables.setText(tables);
			}
		} else {
			checkBoxTable.setSelected(false);
			cBoxTable.setVisible(checkBoxTable.isSelected());
			tpTables.setVisible(checkBoxTable.isSelected());
			btnTables.setVisible(checkBoxTable.isSelected());
			lblTables.setVisible(checkBoxTable.isSelected());
		}
		
		
		panel.updateUI();
	}
		
	private static GemKind[] readGems(String input){
		String[] gems=input.split(", ");
		GemKind[] alGems=new GemKind[gems.length];
		for(int i=0;i<gems.length;i++){
			alGems[i]=GemKind.getKindFromString(gems[i]);
		}
		
		return alGems;
	}
	
	private static ArrayList<Table> readTables(String input){
		ArrayList<Table> alTable=new ArrayList<Table>();
		String[] tables=input.split(", ");
		for(String table:tables){
			if(Table.findTableByName(table)==null){
				ControllingProgram.loadTables(table);
				if(Table.findTableByName(table)==null){
					MainWindow.saveToFile(new Table(table));
				}
			}
			alTable.add(Table.findTableByName(table));
			
		}
		return alTable;
	}
}
