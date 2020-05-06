import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		System.out.println("Введите выражение вида a ДЕЙСТВИЕ b (без пробелов): ");
		Scanner sc = new Scanner(System.in);
		String expressing;
		
		try {
			expressing = sc.next();
			expressing = expressing.replaceAll(" ", "");
			new Calculator(expressing);
		} catch (Exception ex){
			System.out.println(ex.getMessage());
			return;
		} finally {
			sc.close();
		}	
	}

}

class Calculator {
	
	String[] rimNbig = new String[]{"0","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX","XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX","XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL","XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L","LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX","LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX","LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX","LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC","XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"};

	Boolean isNumeric0 = false;
	Boolean isNumeric1 = false;
	Boolean isRim0 = false;
	Boolean isRim1 = false;
	
	int a = 0;
	int b = 0;
	
	String action = "";           // Действие + - * /
	
	public Calculator(String expr) throws MyOutNumberException{
		parseExpr(expr);
	}
	
	private void parseExpr (String expr) throws MyOutNumberException {            //Разбираем полученное выражение
		String[] dlyProverki;
		dlyProverki = expr.split("\\+|\\-|\\*|\\/",2);
		//System.out.println(dlyProverki[0] + "    "+dlyProverki[1]);

		this.action = expr.substring(expr.lastIndexOf(dlyProverki[1])-1, expr.lastIndexOf(dlyProverki[1]));		
		if (dlyProverki[0].matches("\\d+")){
			isNumeric0 = true;
			a = Integer.parseInt(dlyProverki[0]);
		} else {
			isNumeric0 = false;
			dlyProverki[0]=dlyProverki[0].toUpperCase();
		}
		if (dlyProverki[1].matches("\\d+")){
			isNumeric1 = true;
			b = Integer.parseInt(dlyProverki[1]);
		} else {
			isNumeric1 = false;
			dlyProverki[1]=dlyProverki[1].toUpperCase();
		}
		if (!dlyProverki[1].matches("\\d+")){
			for (int i = 0 ; i < rimNbig.length; i++ ){
				if (dlyProverki[0].equals(rimNbig[i])){
					isRim0 = true;
					this.a = i;
				}
				if (dlyProverki[1].equals(rimNbig[i])){
					isRim1 = true;
					this.b = i;
				}
			}
		}
		if (isNumeric0 && isNumeric1){ // Если две арабские
			if ((this.a>10)||(this.b>10)) throw new MyOutNumberException("Вылет.....");
			System.out.println(action(this.action, true));
		}
		if (isRim0 && isRim1){ // Если две римские
			if ((this.a>10)||(this.b>10)) throw new MyOutNumberException("Вылет.....");
			System.out.println(action(this.action));
		}
	}
	
	public int action(String act, Boolean num){ // метод действия с арабскими цыфрами
		int rez = 0;
		
		switch (action){ 
			case "+": rez = a + b; break;
			case "-": rez = a - b; break;
			case "*": rez = a * b; break;
			case "/": rez = a / b; break;
		}
		
		return rez;
	}
	
	public String action(String act){ // метод действия с римскими цыфрами
		int rez = 0;
		String ops = "";
		
		switch (action){
			case "+": rez = a + b; break;
			case "-": rez = a - b; break;
			case "*": rez = a * b; break;
			case "/": rez = a / b; break;
		}
		
		for (int i = 0; i < rimNbig.length; i++){
			if (rez == i){
				ops = rimNbig[i];
			}
		}
		return ops;
	}
}
class MyOutNumberException extends Exception {
	public MyOutNumberException (String str){
		super(str);
	}
}
