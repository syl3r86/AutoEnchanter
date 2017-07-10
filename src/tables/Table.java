package tables;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Table
{
	public static List<Table> tables=new ArrayList<Table>();
	private String name;
	private ItemKind requiredItem=null;
	private GemKind[] requiredGem=null;
	private ArrayList<RollableEffect> rollableEffects = new ArrayList<RollableEffect>();
	private int lowerLimit=0;
	private int upperLimit=0;

	static {
		loadTablesFromFile();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */	
	public Table(String newName) {
		super();
		this.name=newName;
		tables.add(this);
	}
	
	public Table(String newName, ItemKind requiredItem, GemKind... requiredGem) {
		this(newName);
		this.requiredItem = requiredItem;
		this.requiredGem = requiredGem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addRollableEffect(RollableEffect newRollableEffect) {
		// TODO implement me
		rollableEffects.add(newRollableEffect);
		calculateLimits();
	}

	public String toString(){
		return this.name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ItemKind getRequiredItemKind(){
		return requiredItem;
	}
	
	public GemKind[] getRequiredGems(){
		return requiredGem;
	}
	
	public ArrayList<RollableEffect> getRollableEffects(){
		return rollableEffects;
	}
	
	public void setName(String newName){
		name=newName;
	}
	
	public void setReqiredItem(ItemKind item){
		requiredItem=item;
	}
	
	public void setRequiredGems(GemKind[] gems){
		requiredGem=gems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Effect> rollTable(ItemKind givenItem, GemKind... gems) {
		// TODO implement me
		ArrayList<Effect> effects=new ArrayList<Effect>();
		int die=this.upperLimit-this.lowerLimit+1;
		int roll=this.lowerLimit-1+DiceRoller.rollDice(die);
//System.out.println("rolled table: "+this.name);
		for(RollableEffect rE:rollableEffects){
			if(rE.wasRolled(roll) && rE.areRequirementsMet(givenItem, gems)){
				if(rE.getEffect()!=null)
					effects.add(rE.getEffect());
				if(rE.getTable()!=null){
					for(Table t:rE.getTable()){
						if(t.isUsable(givenItem, gems))
							effects.addAll(t.rollTable(givenItem, gems));
					}
				}
			}
		}
		
		return effects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void calculateLimits() {
		// TODO implement me
		
		for(RollableEffect rE:rollableEffects){
			int newMin=rE.getMinRoll();
			int newMax=rE.getMaxRoll();
			if(newMin<this.lowerLimit || this.lowerLimit==0) this.lowerLimit=newMin;
			if(newMax>this.upperLimit || this.upperLimit==0) this.upperLimit=newMax;
		}
	}

	public boolean isUsable(ItemKind item, GemKind... gems) {
		if(this.requiredItem==item || this.requiredItem==null){
			if(this.requiredGem==null) return true;
			else {
				for(int i=0;i<this.requiredGem.length;i++){
					if(this.requiredGem[i]==null) return true;
					
					boolean requiredGemIsThere=false;
					for(int i2=0;i2<gems.length;i2++){
						if(gems[i2]==this.requiredGem[i]){
							requiredGemIsThere=true;
						}
					}
					if(!requiredGemIsThere)
						return false;
				}
			}
			return true;
		}
		else
			return false;
	}
	
	public static void loadTablesFromFile(){
		loadTableFromFile(Settings.StartingTableName);
	}
	
	public static Table loadTableFromFile(String tableName){
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
			        	Table exTable=findTableByName(extraTableString);

			        	for(Table cTable:tables){
			        		if(cTable.getName().matches(extraTableString))
			        			exTable=cTable;
			        	}
			        	if(exTable==null){
			        		exTable=loadTableFromFile(extraTableString);
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
	
	static public Table findTableByName(String tableName){
		Table table=null;
		
		for(Table t:tables){
			if(t.getName().equalsIgnoreCase(tableName)){
				table=t;
				break;				
			}
		}
		
		return table;
	}
}

