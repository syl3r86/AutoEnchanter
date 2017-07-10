package tables;
import java.util.Random;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class DiceRoller
{
	static private Random dice=new Random();


	/**
	 * returns an Integer between 1 and dieFaces (inclusive)
	 * 
	 * @generated
	 * @ordered
	 */
	
	public static int rollDice(int dieFaces) {
		return (dice.nextInt(dieFaces)+1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * rolls multiple dice and returns the sum
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static int rollDice(int diceCount, int dieFaces) {
		int result=0;
		for(int i=0;i<diceCount;i++){
			result += rollDice(dieFaces);
		}
		return result;
	}

}

