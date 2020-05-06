import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		System.out.println("Введите выражение: ");
		Scanner sc = new Scanner(System.in);
		String expressing = "";
		try {
		expressing = sc.next();
		String regx = "(1|2|3|4|5|6|7|8|9|10|i|I|ii|II|iii|III|iv|IV|v|V|vi|VI|vii|VII|viii|VIII|ix|IX|x|X)(\\+|\\-|\\*|\\/)(1|2|3|4|5|6|7|8|9|10|i|I|ii|II|iii|III|iv|IV|v|V|vi|VI|vii|VII|viii|VIII|ix|IX|x|X)" ;
		if (expressing.matches(regx)) {
			new Calculator(expressing);
		} else {
			return;
		}
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		} finally {
			sc.close();
		}	
	}

}

class Calculator {
	int [] arN = {0,1,2,3,4,5,6,7,8,9,10};
	String[] rimNbig = new String[]{"0","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX"};
	String[] rimNmini = new String[]{"0","i","ii","iii","iv","v","vi","vii","viii","ix","x","xi","xii","xiii","xiv","xv","xvi","xvii","xviii","xix","xx"};

	Boolean isNumeric0 = false;
	Boolean isNumeric1 = false;
	Boolean isRim0 = false;
	Boolean isRim1 = false;
	
	int a = 0;
	int b = 0;
	
	String action = "";           // Действие + - * /
	
	public Calculator(String expr) {
		parseExpr(expr);
	}
	private void parseExpr (String expr){            //Разбираем полученное выражение
		String[] dlyProverki;
		dlyProverki = expr.split("\\+|\\-|\\*|\\/",2);
		this.action = expr.substring(expr.lastIndexOf(dlyProverki[1])-1, expr.lastIndexOf(dlyProverki[1]));		
		
		for (int i = 0 ; i < arN.length; i++){
			if (dlyProverki[0].equals(Integer.toString(arN[i]))){
				isNumeric0 = true;
				a = i;
			}
			if (dlyProverki[1].equals(Integer.toString(arN[i]))){
				isNumeric1 = true;
				b = i;
			}
		}
		for (int i = 0 ; i < rimNbig.length; i++ ){
			if (dlyProverki[0].equals(rimNbig[i])){
				isRim0 = true;
				a = i;
			}
			if (dlyProverki[1].equals(rimNbig[i])){
				isRim1 = true;
				b = i;
			}
		}
		for (int i = 0 ; i < rimNmini.length; i++){
			if (dlyProverki[0].equals(rimNmini[i])){
				isRim0 = true;
				a = i;
			}
			if (dlyProverki[1].equals(rimNmini[i])){
				isRim1 = true;
				b = i;
			}
		}
		
		if (isNumeric0 && isNumeric1){ // Если две арабские
			System.out.println(action(this.action, true));
		}
		if (isRim0 && isRim1){ // Если две римские
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
