import java.util.Scanner;

public class valley{
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number of steps: ");
		int n = scan.nextInt();
		System.out.print("Enter d or u: ");
		String s = scan.next();

		s = s.toLowerCase();

		int[] pattern = new int[n + 2];
		int[] patternClone = new int[n + 2];
		String[][] array = new String[(n * 2) + 2][n + 2];

		for(int ya = 0; ya < (n * 2) + 1; ya++){
			for(int y = 0; y < n + 2; y++){
				array[ya][y] = " ";
			}
		}
		/////----------------------------------------------------------
		pattern[0] = n + 1; 

		int up = n + 1;
		int down = n + 2;
		int x = 1;


		//This loop goes like this:
		//If n is equal to 7 pattern[0] will start at 8. If the char, that is currently selected, from the string is equal to
		//"u", pattern[1] is equal to pattern[0]. But if the char is equal to "d" pattern 2 is equal to 7.
		//The next value of pattern will stay the same if the next char was different from the previous char.
		//udu
		//If it is the same "u" will decrement and "d" will increment.
		//Example:
		//n = 3 and s = "udu"
		//pattern values, starting from index 0, looks like this
		//4444
		//Since the char was different from the previous one the value stays the same but if the input is like this:
		//n = 3 and s = "uuu" pattern values, starting from index 0, looks like this
		//4432


		for (int i = 0; i < n ; i++) {
			if(String.valueOf(s.charAt(i)).equals("u")) {
				pattern[x] = up;
				down = up;
				up--;
			}
			else if(String.valueOf(s.charAt(i)).equals("d")) {
				pattern[x] = down;
				up = down;
				down++;
			}

			x++;
		}


		//This conditions are used to determine what value is the last index of pattern.
		if (String.valueOf(s.charAt(n - 1)).equals("u"))
			pattern[n + 1] = pattern[n] - 1;
		else if (String.valueOf(s.charAt(n - 1)).equals("d")) 
			pattern[n + 1] = pattern[n];

		/////----------------------------------------------------------
		
		for (int clone = 0; clone < n+2 ; clone++) {
			patternClone[clone] = pattern[clone];
		}


		//This loop gets the lowest number and place it to the lowest index of the patternClone array.
		for(int num = patternClone.length - 1; num > patternClone.length -patternClone.length ; num--){
			if (patternClone[num] < patternClone[num - 1]) {
				int o = patternClone[num];
				patternClone[num] = patternClone[num - 1];
				patternClone[num - 1] = o;
			}
		}

		int lowest = patternClone[0] - 2;

		

		/////----------------------------------------------------------
		//This part will fill the blank indices with strings such as "/","\\" and "_".


		int num = 0;

		for (int i = 1; i < n + 1; i++) {
			if (String.valueOf(s.charAt(num)).equals("u")) {
				//Row was subtracted by int lowest so that it moves up when it prints because it will print in the center if
				//not subtracted. 
				array[pattern[i]- lowest][i] = "/";
				array[pattern[i] - (lowest - 1)][i] = "/";
			}
			else if (String.valueOf(s.charAt(num)).equals("d")) {
				array[pattern[i]- lowest][i] = "\\";
				array[pattern[i] -(lowest - 1)][i] = "\\";
			}
			num++;
		}

		array[pattern[0] - lowest][0] = "_";
		array[pattern[n+1] - lowest][n+1] = "_";
		array[pattern[0] - (lowest - 1)][0] = "_";
		array[pattern[n+1] - (lowest - 1)][n+1] = "_";
		
		num = 0;

		for (int i = 1; i < n + 1; i++) {
			if (String.valueOf(s.charAt(num)).equals("u")) {
				array[pattern[i] - (lowest - 1)][i] = "/";
			}
			else if (String.valueOf(s.charAt(num)).equals("d")) {
				array[pattern[i] -(lowest - 1)][i] = "\\";
			}
			num++;
		}

		array[pattern[0] - (lowest - 1)][0] = "_";
		array[pattern[n+1] - (lowest - 1)][n+1] = "_";

		/////----------------------------------------------------------	
		//This part determines the indices at array[][] from bottom to top, right to left.
		//If the row is all blank, " ", excess will increment proving that every column from the row was empty.
		//The loop will break if it found strings such as "_", "/" and "\\", which proves that every column from the row was not empty.
		//The excess is then subtracted to the total row count of array[][] so that it will not print blank indices.


		boolean empty = true;
		int excess = 0;
		for (int re = n * 2 - 1; re >= 0; re-- ) {
			for (int ro = n + 1 - 1; ro >= 0 ; ro--) {
				if (array[re][ro].equals("_") || array[re][ro].equals("/") || array[re][ro].equals("\\")) {
					empty = false;
					break;
				}
			}
			if (empty == false) {
					break;
			}
			excess++;
		}
		/////----------------------------------------------------------
		for (int b = 0; b < ((n * 2) + 1) - excess ;b++ ) {
			for (int y = 0; y < n + 2 ;y++ ) {
				System.out.print(array[b][y]);
			}
			System.out.print("\n");
		}


		//This part is only used if you want to know the indices of the array that is not empty.
		/*for (int ya : pattern) {
			System.out.print(ya);
		}
		System.out.print("\n" );
		for (int ye : patternClone) {
			System.out.print(ye);
		}

		System.out.print("\n" + patternClone[0] + " " + excess);*/

	}

}