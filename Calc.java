import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		new Calculator().run();
	}

}

class Calculator {
	int [] arN = {0,1,2,3,4,5,6,7,8,9,10};
	String[] rimNbig = new String[]{"0","I","II","III","IV","V","VI","VII","VIII","IX","X"};
	String[] rimNmini = new String[]{"0","i","ii","iii","iv","v","vi","vii","viii","ix","x"};
	String[] dlyProverki;
	Scanner sc;
	Boolean isNumeric0 = false;
	Boolean isNumeric1 = false;
	Boolean isRim0 = false;
	Boolean isRim1 = false;
	int a = 0;
	int b = 0;
	String action = "";
	public int action(String act){
		int rez = 0;
		switch (action){
			case "+": rez = a + b; break;
			case "-": rez = a - b; break;
			case "*": rez = a * b; break;
			case "/": rez = a / b; break;
		}
		return rez;
	}
	public void run() {
		String regx = "(1|2|3|4|5|6|7|8|9|10|i|I|ii|II|iii|III|iv|IV|v|V|vi|VI|vii|VII|viii|VIII|ix|IX|x|X)(\\+|\\-|\\*|\\/)(1|2|3|4|5|6|7|8|9|10|i|I|ii|II|iii|III|iv|IV|v|V|vi|VI|vii|VII|viii|VIII|ix|IX|x|X)" ;
		sc = new Scanner(System.in);
		String rep;
		
		do {
			System.out.print("Введите выражение: ");
			rep = sc.next();
		
			if (rep.matches(regx)){
				dlyProverki = rep.split("(\\+|\\-|\\*|\\/)",2);
				action = rep.substring(rep.lastIndexOf(dlyProverki[1])-1, rep.lastIndexOf(dlyProverki[1]));
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
				if (isNumeric0 && isNumeric1){
					System.out.println("Две цифры a="+a+" b="+b);
					System.out.println(action(action));
				}
				if (isRim0 && isRim1){
					System.out.println("Две Римскихa a="+a+" b="+b);
					System.out.println(action(action));
				}
			}
		} while (!rep.matches(regx));
		sc.close();
		
		System.out.println("Конец программы!!!");
	};
}
