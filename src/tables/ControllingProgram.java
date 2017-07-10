package tables;
import java.util.ArrayList;

// imports for xmlreading
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ControllingProgram
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private static ArrayList<Table> tables=new ArrayList<Table>();

	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public ControllingProgram(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static void main(String... args) {

		//loadTables();
		//createTable();
		Enchanter.addFirstTable(Table.tables.get(2));
		
/*
		for(int i=0;i<15;i++){
			System.out.print("Enchantment: ");
			EnchantedItem newItem=new EnchantedItem();
			newItem.setItemKind(ItemKind.getKindFromString("ARMOR"));
			Enchanter.enchantItem(newItem, GemKind.getKindFromString("rare"));
			newItem.printItem();
		}
*/
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static void createTable() {
		// TODO implement me
		/*Table table=new Table("table1",ItemKind.WEAPON,GemKind.LOW);
		
		Effect crushing=new Effect();
		crushing.setName("crushing");
		crushing.setDescription("adds 2 crushing damage");
		RollableEffect rollableCrushing=new RollableEffect(1,10);
		rollableCrushing.setEffect(crushing);
		
		Effect slicing=new Effect();
		slicing.setName("slicing");
		slicing.setDescription("adds 2 slicing damage");
		RollableEffect rollableSlicing=new RollableEffect(11,20);
		rollableSlicing.setEffect(slicing);
		
		table.addRollableEffect(rollableCrushing);
		table.addRollableEffect(rollableSlicing);
		
		startingTables.add(table);
		
		Table secondTable=new Table("table2");
		
		Effect superior=new Effect();
		superior.setName("superior");
		superior.setDescription("really great weapon");
		RollableEffect rollableSuperior=new RollableEffect(1,10);
		rollableSuperior.setEffect(superior);
		
		Effect mindboggeling=new Effect();
		mindboggeling.setName("mindboggeling");
		mindboggeling.setDescription("really great weapon");
		RollableEffect rollableMindboggeling=new RollableEffect(11,20);
		rollableMindboggeling.setEffect(mindboggeling);


		RollableEffect rollableNothing=new RollableEffect(21,40);
		rollableNothing.setEffect(null);
		
		secondTable.addRollableEffect(rollableSuperior);
		secondTable.addRollableEffect(rollableMindboggeling);
		secondTable.addRollableEffect(rollableNothing);
		rollableSlicing.addTable(secondTable);*/
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static void saveTables() {
		// TODO implement me
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public static Table loadTables(String tableName){
		try{
			//loading the file
			File inputFile=new File(tableName+".xml");
	        DocumentBuilderFactory dbFactory 
	        	= DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        
	        //reading the tablenodes
	        NodeList reqItemList=doc.getElementsByTagName("reqItem");
	        NodeList reqGemList=doc.getElementsByTagName("reqGem");
	        NodeList effectsList=doc.getElementsByTagName("RollableEffect");
	        
	        //creating the Table
	        ItemKind reqItem;
	        String reqItemString=reqItemList.item(0).getTextContent();
	        
	        try{
	        	reqItem=ItemKind.getKindFromString(reqItemString);
	        }catch (Exception e) {
		        reqItem=null;
		        //e.printStackTrace();
		    }
	        
	        GemKind[] reqGem=new GemKind[reqGemList.getLength()];
	        for(int i=0;i<reqGemList.getLength();i++){
	        	String reqGemString=reqGemList.item(i).getTextContent();
	        	try{
	        		reqGem[i]=(GemKind.getKindFromString(reqGemString));
	        	} catch(Exception e){ }
	        }
	        
	        Table newTable=new Table(tableName,reqItem,reqGem);
	        
	        
	        // creating and adding RollableEffects
	        
	        for(int i=0;i<effectsList.getLength();i++){
	        	Node currentNode=effectsList.item(i);
	        	if(currentNode instanceof Element){
		        	int lowerLimit=0;
		        	int upperLimit=0;
		        	String effectName="";
		        	String effectDescritpion="";
		        	ItemKind requiredItem=null;
		        	GemKind[] requiredGem=null;		        	
		        	ArrayList<Table> extraTables=new ArrayList<Table>();
		        	
		        	Element currentElement=(Element) currentNode;
			        lowerLimit=Integer.parseInt(currentElement.getElementsByTagName("minRoll").item(0).getTextContent());
			        upperLimit=Integer.parseInt(currentElement.getElementsByTagName("maxRoll").item(0).getTextContent());
			        effectName=currentElement.getElementsByTagName("EffectName").item(0).getTextContent();
			        effectDescritpion=currentElement.getElementsByTagName("Effect").item(0).getTextContent();
			        
			        
			        NodeList tableNodes=currentElement.getElementsByTagName("extraTable");

			        for(int i2=0;i2<tableNodes.getLength();i2++){
			        	Node node=tableNodes.item(i2);
			        	String extraTableString=node.getTextContent();
			        	Table exTable=null;

			        	for(Table cTable:tables){
			        		if(cTable.getName().matches(extraTableString))
			        			exTable=cTable;
			        	}
			        	if(exTable==null){
			        		exTable=loadTables(extraTableString);
			        		tables.add(exTable);
			        	}
			        	extraTables.add(exTable);
			        }
			        // requiredItem , requiredGem
			        NodeList reqEffectItemList=currentElement.getElementsByTagName("reqEffectItem");
			        NodeList reqEffectGemList=currentElement.getElementsByTagName("reqEffectGem");
			        
			        if(reqEffectItemList.getLength()>0){
			        	reqItemString=reqEffectItemList.item(0).getTextContent();
			        }
			        else{
			        	reqItemString="null";
			        }
			        
			        try{
			        	requiredItem=ItemKind.getKindFromString(reqItemString);
			        }catch (Exception e) {
			        	requiredItem=null;
			        	
				        //e.printStackTrace();
				    }
			        
			        requiredGem=new GemKind[reqEffectGemList.getLength()];
			        for(int i3=0;i3<reqEffectGemList.getLength();i3++){
			        	String reqGemString=reqEffectGemList.item(i3).getTextContent();
			        	try{
			        		requiredGem[i3]=(GemKind.getKindFromString(reqGemString));
			        	} catch(Exception e){ }
			        }
			        
			        
			        RollableEffect newRollableEffect=new RollableEffect(lowerLimit,upperLimit);
			        Effect newEffect=new Effect();
			        newEffect.setName(effectName);
			        newEffect.setDescription(effectDescritpion);
			        newRollableEffect.setEffect(newEffect);
			        newRollableEffect.setRequirements(requiredItem, requiredGem);
			        newRollableEffect.setTable(extraTables);
			        newTable.addRollableEffect(newRollableEffect);
		        }
	        }
	        

	        
	        return newTable;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public static void loadTables() {
		// TODO implement me
		tables.add(loadTables("table1"));	
		
	}

}

