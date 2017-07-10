package tables;
import java.util.ArrayList;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Enchanter
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private static ArrayList<Table> firstTables=new ArrayList<Table>();
	private static Table startingTable;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static void addFirstTable(Table table) {
		firstTables.add(table);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static void enchantItem(EnchantedItem item, GemKind... gems) {
		// TODO implement me
		startingTable=determinFirstTable(item.getItemKind(),gems);
		enchantItem(startingTable,item,gems);
	}

	public static void enchantItem(Table firstTable, EnchantedItem item, GemKind... gems){
		startingTable=firstTable;
		ArrayList<Effect> effects=startingTable.rollTable(item.getItemKind(),gems);
		item.setEffectStack(effects);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static Table determinFirstTable(ItemKind item, GemKind... gem) {
		// TODO implement me
		for(Table st:firstTables){
			if(st.isUsable(item, gem)){
				startingTable=st;
				return st;
			}
		}
		return null;
	}

}

