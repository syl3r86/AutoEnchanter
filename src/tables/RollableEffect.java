package tables;
import java.util.ArrayList;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RollableEffect
{
	private int minRoll;
	private int maxRoll;
	private ItemKind requiredItem=null;
	private GemKind[] requiredGem=null;
	private Effect effect;
	private ArrayList<Table> table=new ArrayList<Table>();


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public RollableEffect(int min, int max) {
		super();
		this.minRoll=min;
		this.maxRoll=max;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean wasRolled(int roll) {
		if(roll>=this.minRoll && roll<=this.maxRoll)
			return true;
		else
			return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setEffect(String name, String description) {
		this.effect = new Effect();
		this.effect.setName(name);
		this.effect.setDescription(description);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setEffect(Effect newEffect) {
		this.effect = newEffect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Effect getEffect() {
		
		return this.effect;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public int getMinRoll() {
		
		return this.minRoll;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public int getMaxRoll() {
		
		return this.maxRoll;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addTable(Table newTable) {
		this.table.add(newTable);
	}
	
	public ItemKind getRequiredItemKind(){
		return this.requiredItem;
	}
	
	public GemKind[] getRequiredGemKind(){
		return this.requiredGem;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setTable(ArrayList<Table> newTableList) {
		this.table = newTableList;
	}

	public String toString(){
		return this.getEffect().getName();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Table> getTable() {
		return this.table;
	}

	public void setRequirements(ItemKind item, GemKind[] gem){
		requiredItem=item;
		requiredGem=gem;
	}
	
	public boolean areRequirementsMet(ItemKind item, GemKind... gems){
		if(this.requiredItem==null && this.requiredGem==null)
			return true;
		else if(this.requiredItem==item || this.requiredItem==null){
			for(GemKind reqGems:this.requiredGem){
				boolean found=false;
				for(GemKind givenGem:gems){
					if(reqGems==givenGem){
						found=true;
						break;
					}						
				}
				if(found==false)
					return false;
			}
			return true;
		}
		
		return false;
	}
}

