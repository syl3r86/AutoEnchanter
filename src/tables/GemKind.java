package tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class GemKind{
	public static ArrayList<GemKind> gemKinds=new ArrayList<GemKind>();
	public String name;
	static{
		try{
			BufferedReader br = new BufferedReader(new FileReader("gemKinds.txt"));
			
			String line = br.readLine();

		    while (line != null) {
		        new GemKind(line);
		        line = br.readLine();
		    };
			br.close();
			
			
		}catch(Exception e){
			e.getStackTrace();
		}
		
	}
	public GemKind(String newName){
		name=newName;
		gemKinds.add(this);
	}
	public static GemKind getKindFromString(String itemKind){
		GemKind rValue=null;
		for(GemKind ik:gemKinds){
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