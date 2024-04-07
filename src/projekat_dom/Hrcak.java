package projekat_dom;

import java.util.Scanner;

public class Hrcak {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		float br_kreveta=sc.nextFloat();
		boolean novi=sc.nextBoolean();
		float skor=0;
		if(novi) {
			skor=(float) (1.01+1/br_kreveta);
		}
		else
			skor=(float)0.5+1/br_kreveta;
		System.out.println("\n"+skor);

	}

}
