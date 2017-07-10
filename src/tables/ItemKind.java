package tables;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class ItemKind{
	public static ArrayList<ItemKind> itemKinds=new ArrayList<ItemKind>();
	public String name;
	static{
		try{
			BufferedReader br = new BufferedReader(new FileReader("itemKinds.txt"));
			
			String line = br.readLine();

		    while (line != null) {
		        new ItemKind(line);
		        line = br.readLine();
		    };
			br.close();
			
			
		}catch(Exception e){
			e.getStackTrace();
		}
		
	}
	public ItemKind(String newName){
		name=newName;
		itemKinds.add(this);
	}
	public static ItemKind getKindFromString(String itemKind){
		ItemKind rValue=null;
		for(ItemKind ik:itemKinds){
			if(ik.name.matches(itemKind)){
				rValue=ik;
			}
		}
		return rValue;
	}
	
	@Override
	public String toString(){
		return name;
	}
}