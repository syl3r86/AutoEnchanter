package gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import tables.*;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.*;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import java.io.File;
import java.io.PrintWriter;

public class MainWindow {

	private static JFrame frame;
	
	private static List<JPanel> panelList=new ArrayList<JPanel>();
	private static List <JLabel> dynamicLabels=new ArrayList<JLabel>();
			
	private static JPanel pEnchant;
	private static ImagePanel pMainMenu;
	private static JPanel pTableManager;
	private static JTextField tNewMin;
	private static JTextField tNewMax;
	private static JTextField tNewName;
	private static JLabel lblTablename;
	private static JPanel pTableSettings;
	private static JComboBox cTablePicker;
	private static JScrollPane pTableEffects;
	private static JTextField tableName;
	private static JCheckBox checkBoxItem;
	private static JTextPane tpTableGems;
	private static JComboBox cBoxGem;
	private static JComboBox cBoxItem;
	private static JCheckBox checkBoxGem;
	private static JButton btnAddGem;
	private static JButton btnSaveTable;
	private static JButton btnMainMenu;
	private static JButton btnMainMenu_1;
	private JButton btnNewButton_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
					window.frame.setTitle("Enchant-o-tron 9001");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 254, 329);
		//frame.setBounds(100, 100, 254, 329);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		
		pEnchant = new JPanel();
		frame.getContentPane().add(pEnchant, "name_4572697588932255");
		pEnchant.setLayout(null);
		panelList.add(pEnchant);
		
		pMainMenu = new ImagePanel();
		frame.getContentPane().add(pMainMenu, "name_4574201169741744");
		pMainMenu.setLayout(null);
		panelList.add(pMainMenu);
		
		pTableManager = new JPanel();
		frame.getContentPane().add(pTableManager, "name_4574653329916111");
		pTableManager.setLayout(null);
		panelList.add(pTableManager);
		

		buildMainMenu();
		buildEnchantGui();
		buildTableManager();

		
		showPanels(pMainMenu);
		try{
			Image img=ImageIO.read(new File("enchanting.jpg"));
			//pMainMenu.
		} catch (Exception e){
			
		}
	}
	
	private static void showPanels(JPanel panel){
		
		for(JPanel jP:panelList){
			if(panel==jP){
				jP.setVisible(true);
			}
			else{
				jP.setVisible(false);
			}
		}
	}
	
	private static void buildEnchantGui(){
		showPanels(pEnchant);
		
		JLabel lblItemtype = new JLabel("Itemtype");
		lblItemtype.setBounds(10, 11, 109, 14);
		pEnchant.add(lblItemtype);
		lblItemtype.setHorizontalAlignment(SwingConstants.LEFT);
		
		JComboBox cBoxItem = new JComboBox();
		cBoxItem.setBounds(10, 30, 109, 20);
		pEnchant.add(cBoxItem);
		
		JLabel lblGemtype = new JLabel("GemType");
		lblGemtype.setBounds(135, 11, 109, 14);
		pEnchant.add(lblGemtype);
		lblGemtype.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JComboBox cBoxGem = new JComboBox();
		cBoxGem.setBounds(129, 30, 115, 20);
		pEnchant.add(cBoxGem);
		
		JButton btnNewButton = new JButton("enchant!");
		btnNewButton.setBounds(10, 86, 234, 23);
		pEnchant.add(btnNewButton);
		
		JTextPane resultPane = new JTextPane();
		resultPane.setBounds(10, 120, 234, 149);
		pEnchant.add(resultPane);
		
		JLabel lblStartingTable = new JLabel("Starting Table");
		lblStartingTable.setBounds(10, 63, 86, 14);
		pEnchant.add(lblStartingTable);
		
		JComboBox cBoxTable = new JComboBox();
		cBoxTable.setBounds(93, 61, 151, 20);
		pEnchant.add(cBoxTable);
		
		btnMainMenu = new JButton("back");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 254, 329);
				showPanels(pMainMenu);
			}
		});
		btnMainMenu.setBounds(10, 280, 234, 23);
		pEnchant.add(btnMainMenu);
		
		ControllingProgram.main();
		
		for(ItemKind ik:ItemKind.itemKinds)
			cBoxItem.addItem(ik);
		for(GemKind gk:GemKind.gemKinds)
			cBoxGem.addItem(gk);
		for(Table t:Table.tables)
			cBoxTable.addItem(t);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ControllingProgram.main();
				
				EnchantedItem newItem=new EnchantedItem();
				newItem.setItemKind((ItemKind) cBoxItem.getSelectedItem());
				GemKind gem=(GemKind) cBoxGem.getSelectedItem();
				Table startingTable=(Table) cBoxTable.getSelectedItem();
				Enchanter.enchantItem(startingTable, newItem, gem);
				
				String fullItem;
				String item="";
				String effects="";
				for(Effect effect:newItem.getEffects()){
					item+=effect.getName()+" ";
					effects+=effect.getName()+" - "+effect.getDescription()+"\n";
				}
				item+=newItem.getItemKind();
				fullItem=item+"\n"+effects;
				
				
				
				resultPane.setText(fullItem);
			}
		});
	}

	private static void buildMainMenu(){
		JButton btnEnchant = new JButton("Enchant");

		btnEnchant.setBounds(10, 226, 228, 23);
		pMainMenu.add(btnEnchant);
		
		JButton btnManageTables = new JButton("Manage Tables");
		btnManageTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 362, 366);
				showPanels(pTableManager);
			}
		});
		btnManageTables.setBounds(10, 260, 228, 23);
		pMainMenu.add(btnManageTables);
		
		btnEnchant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 265, 350);
				showPanels(pEnchant);
			}
		});
	}

	private void buildTableManager(){
		
		JLabel lblChooseTable = new JLabel("Choose Table:");
		lblChooseTable.setBounds(10, 11, 195, 14);
		pTableManager.add(lblChooseTable);
		
		cTablePicker = new JComboBox();
		
		cTablePicker.setBounds(10, 36, 325, 20);
		pTableManager.add(cTablePicker);
		
		JButton btnNewTable = new JButton("new Table");
		btnNewTable.setBounds(235, 7, 100, 23);
		pTableManager.add(btnNewTable);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 67, 325, 226);
		pTableManager.add(tabbedPane);
		
		pTableSettings = new JPanel();
		tabbedPane.addTab("Table Settings", null, pTableSettings, null);
		pTableSettings.setLayout(null);
		
		lblTablename = new JLabel("Tablename:");
		lblTablename.setBounds(14, 48, 110, 14);
		pTableSettings.add(lblTablename);
		
		tableName = new JTextField();
		tableName.setBounds(134, 45, 180, 20);
		pTableSettings.add(tableName);
		tableName.setColumns(10);
		
		checkBoxItem = new JCheckBox("requires Item");
		checkBoxItem.setBounds(10, 69, 114, 23);
		pTableSettings.add(checkBoxItem);
		
		checkBoxGem = new JCheckBox("requires Gem");
		checkBoxGem.setBounds(10, 95, 114, 23);
		pTableSettings.add(checkBoxGem);
		
		cBoxItem = new JComboBox();
		cBoxItem.setBounds(134, 70, 180, 20);
		pTableSettings.add(cBoxItem);
		
		cBoxGem = new JComboBox();
		cBoxGem.setBounds(134, 96, 145, 20);
		pTableSettings.add(cBoxGem);
		
		btnAddGem = new JButton("+");
		btnAddGem.setBounds(289, 95, 25, 23);
		btnAddGem.setMargin(new Insets(0,0,0,0));
		pTableSettings.add(btnAddGem);
		
		tpTableGems = new JTextPane();
		tpTableGems.setBounds(14, 125, 300, 20);
		pTableSettings.add(tpTableGems);
		
		btnSaveTable = new JButton("save");
		btnSaveTable.setBounds(10, 11, 300, 23);
		pTableSettings.add(btnSaveTable);

		
		pTableEffects = new JScrollPane();
		pTableEffects.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("Table Effects", null, pTableEffects, null);
		pTableEffects.setLayout(null);
		
		JLabel lblMin = new JLabel("min");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(10, 10, 23, 14);
		pTableEffects.add(lblMin);
		
		JLabel lblNewLabel = new JLabel("max");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(35, 10, 30, 14);
		pTableEffects.add(lblNewLabel);
		
		JLabel lblEffectName = new JLabel("Effect Name");
		lblEffectName.setBounds(65, 10, 245, 14);
		pTableEffects.add(lblEffectName);
		
		JButton button = new JButton("+");

		button.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button.setBounds(290, 28, 20, 20);
		button.setMargin(new Insets(0, 0, 0, 0));
		pTableEffects.add(button);
		
		tNewMin = new JTextField();
		tNewMin.setBounds(10, 28, 20, 20);
		pTableEffects.add(tNewMin);
		tNewMin.setColumns(10);
		
		tNewMax = new JTextField();
		tNewMax.setBounds(35, 28, 20, 20);
		pTableEffects.add(tNewMax);
		tNewMax.setColumns(10);
		
		tNewName = new JTextField();
		tNewName.setBounds(65, 28, 221, 20);
		pTableEffects.add(tNewName);
		tNewName.setColumns(10);
		
		btnMainMenu_1 = new JButton("back");
		btnMainMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 254, 329);
				showPanels(pMainMenu);
			}
		});
		btnMainMenu_1.setBounds(10, 293, 325, 23);
		pTableManager.add(btnMainMenu_1);
		
		btnNewButton_1 = new JButton("edit Effects");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table selectedTable=(Table)cTablePicker.getSelectedItem();
				EffectsEditor.startEdit(selectedTable);
			}
		});
		btnNewButton_1.setBounds(132, 7, 100, 23);
		pTableManager.add(btnNewButton_1);
		

		for(Table t:Table.tables)
			cTablePicker.addItem(t);
		for(ItemKind ik:ItemKind.itemKinds){
			cBoxItem.addItem(ik);
		}
		for(GemKind gk:GemKind.gemKinds){
			cBoxGem.addItem(gk);
		}
		
		cTablePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTableDisplay();
			}
		});

		checkBoxItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cBoxItem.setVisible(checkBoxItem.isSelected());
			}
		});

		checkBoxGem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxGem.setSelected(checkBoxGem.isSelected());
				tpTableGems.setVisible(checkBoxGem.isSelected());
				cBoxGem.setVisible(checkBoxGem.isSelected());
				btnAddGem.setVisible(checkBoxGem.isSelected());
			}
		});
		
		btnSaveTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table sTable=(Table) cTablePicker.getSelectedItem();
				sTable.setName(tableName.getText());
				if(checkBoxItem.isSelected()){
					sTable.setReqiredItem((ItemKind) cBoxItem.getSelectedItem());
				} else {
					sTable.setReqiredItem(null);
				}
				if(checkBoxGem.isSelected()){
					String[] gems=tpTableGems.getText().split(", ");
					GemKind[] allGems=new GemKind[gems.length];
					for(int i=0;i<gems.length;i++){
						if(GemKind.getKindFromString(gems[i])!=null)
							allGems[i]=GemKind.getKindFromString(gems[i]);
					}
					sTable.setRequiredGems(allGems);
				} else {
					sTable.setRequiredGems(new GemKind[0]);
				}
				saveToFile(sTable);
			}
		});

		btnNewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName="newTable"+(Table.tables.size()+1);
				Table newTable=new Table(tableName);
				cTablePicker.addItem(newTable);
				cTablePicker.setSelectedItem(newTable);
				updateTableDisplay();
			}
		});
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(SwingUtilities.isRightMouseButton(arg0)){
					Table selectedTable=(Table)cTablePicker.getSelectedItem();
					EffectsEditor.startEdit(selectedTable);
				}
					
					
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					int newMin=Integer.valueOf(tNewMin.getText());
					int newMax=Integer.valueOf(tNewMax.getText());
					RollableEffect newRollableEffect=new RollableEffect(newMin,newMax);
					Effect newEffect =new Effect();
					newEffect.setName(tNewName.getText());
					newRollableEffect.setEffect(newEffect);
					
					Table selectedTable=(Table)cTablePicker.getSelectedItem();
					selectedTable.addRollableEffect(newRollableEffect);
					
					
				}catch (NumberFormatException exception){
					JDialog errorMSG=new JDialog();
					errorMSG.setTitle("Error");
					errorMSG.setModal(true);
					errorMSG.setBounds( MouseInfo.getPointerInfo().getLocation().x,
										MouseInfo.getPointerInfo().getLocation().y, 
										180,
										100);
					errorMSG.setResizable(false);
					JLabel msg=new JLabel("input is not a number");
					errorMSG.getContentPane().add(msg);
					errorMSG.setVisible(true);
				}

				cTablePicker.setSelectedIndex(cTablePicker.getSelectedIndex());
				
			}
		});
		updateTableDisplay();
	}

	public static void updateTableDisplay(){
		
			Table selectedTable=(Table)cTablePicker.getSelectedItem();

			tableName.setText(selectedTable.getName());
			if(selectedTable.getRequiredItemKind()!=null){
				checkBoxItem.setSelected(true);
				cBoxItem.setSelectedItem(selectedTable.getRequiredItemKind());
				cBoxItem.setVisible(true);
			} else {
				checkBoxItem.setSelected(false);
				cBoxItem.setVisible(false);
			}
			
			if(selectedTable.getRequiredGems()!=null
					&& selectedTable.getRequiredGems().length>0
					&& selectedTable.getRequiredGems()[0]!=null){
				checkBoxGem.setSelected(true);
				tpTableGems.setVisible(true);
				cBoxGem.setVisible(true);
				btnAddGem.setVisible(true);
				for(GemKind gk:selectedTable.getRequiredGems()){
					String gems;
					tpTableGems.setText("");
					if(tpTableGems.getText().length()>1){
						gems=tpTableGems.getText()+", "+gk;
					} else {
						gems=gk.toString();
					}
					tpTableGems.setText(gems);
				}
			} else {
				checkBoxGem.setSelected(false);
				tpTableGems.setVisible(false);
				tpTableGems.setText("");
				cBoxGem.setVisible(false);
				btnAddGem.setVisible(false);
			}
			
			
			for(JLabel dLabel:dynamicLabels){
				pTableSettings.remove(dLabel);
				pTableEffects.remove(dLabel);
			}
			dynamicLabels=new ArrayList<JLabel>();
			
			GemKind[] reqGems=selectedTable.getRequiredGems();
			/*int gemLableXPos=61;
			for(GemKind gem:reqGems){
				if(gem!=null){
					JLabel gemLabel=new JLabel(gem.name);
					gemLabel.setBounds(117, gemLableXPos, 193, 14);
					pTableSettings.add(gemLabel);

					gemLableXPos+=15;
					dynamicLabels.add(gemLabel);
				}

			}*/
			
			ArrayList<RollableEffect> rollableEffects=selectedTable.getRollableEffects();
			int effectLabelXPos=50;
			for(RollableEffect rEffect:rollableEffects){
				JLabel min=new JLabel(""+rEffect.getMinRoll());
				min.setBounds(10, effectLabelXPos, 20, 14);
				pTableEffects.add(min);
				dynamicLabels.add(min);
				
				JLabel max=new JLabel(""+rEffect.getMaxRoll());
				max.setBounds(35, effectLabelXPos, 20, 14);
				pTableEffects.add(max);
				dynamicLabels.add(max);
				
				JLabel name=new JLabel(rEffect.getEffect().getName());
				name.setToolTipText(rEffect.getEffect().getDescription());
				name.setBounds(65, effectLabelXPos, 254,14);
				pTableEffects.add(name);
				dynamicLabels.add(name);

				effectLabelXPos+=15;
			}
			
		pTableSettings.updateUI();
		pEnchant.updateUI(); 
		pMainMenu.updateUI();
		pTableManager.updateUI();
		pTableEffects.updateUI();
	}
	public static void saveToFile(Table table){
		String tableString="";
		tableString+="<table name=\""+table.getName()+"\">\n";
		tableString+="\t<reqItem>"+((table.getRequiredItemKind()!=null)?table.getRequiredItemKind():"null")+"</reqItem>\n";
		boolean gemsRequired=(table.getRequiredGems()!=null && table.getRequiredGems().length>0);
		if(gemsRequired){
			for(GemKind gem:table.getRequiredGems()){
				tableString+="\t<reqGem>"+gem.toString()+"</reqGem>\n";
			}
		} else {
			tableString+="\t<reqGem>null</reqGem>\n";
		}
		int i=0;
		for(RollableEffect rEffect:table.getRollableEffects()){
			tableString+="\t<RollableEffect n=\""+(++i)+"\">\n";
			if(rEffect.getRequiredItemKind()!=null){
				tableString+="\t\t<reqEffectItem>"+rEffect.getRequiredItemKind()+"</reqEffectItem>\n";
			} 
			
			boolean effectGemRequired=(rEffect.getRequiredGemKind()!=null && rEffect.getRequiredGemKind().length>0);
			if(effectGemRequired){
				for(GemKind gk:rEffect.getRequiredGemKind()){
					tableString+="\t\t<reqEffectGem>"+gk+"</reqEffectGem>\n";
				}
			}
			
			tableString+="\t\t<minRoll>"+rEffect.getMinRoll()+"</minRoll>\n";
			tableString+="\t\t<maxRoll>"+rEffect.getMaxRoll()+"</maxRoll>\n";
			tableString+="\t\t<EffectName>"+rEffect.getEffect().getName()+"</EffectName>\n";
			tableString+="\t\t<Effect>"+rEffect.getEffect().getDescription()+"</Effect>\n";
			
			if(rEffect.getTable()!=null && rEffect.getTable().size()!=0){
				for(Table extraTable:rEffect.getTable()){
					tableString+="\t\t<extraTable>"+extraTable.getName()+"</extraTable>\n";
				}
			}
			
			tableString+="\t</RollableEffect>\n";
			
		}
		tableString+="</table>";
		
		File tableFile=new File(table.getName()+".xml");
		try(PrintWriter out=new PrintWriter(table.getName()+".xml")){
			out.print(tableString);
		} catch(Exception e){e.printStackTrace();}
	}
}

class ImagePanel extends JPanel{
	private Image img;
	public ImagePanel(){
		
		try{
			File imgFile=new File("enchanting.png");
			img=ImageIO.read(imgFile);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
		if(img!=null){
			g.drawImage(img,0,0,this);
		}
	}
}
