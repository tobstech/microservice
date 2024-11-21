package com.tiktactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	
	  /**
     * Your final solution should be implemented in the method below
     * @param inputs - A List of Strings containing the challenge inputs
     */
	public static String solution(List<String> inputs) {
        int xWinsCount = 0;
        int oWinsCount = 0;
        int invalidBoardsCounts = 0;

        // Iterate through each board input
        for (String matrixLine : inputs) {
            String[] splittedArray = matrixLine.split(" ");
            String firstString = splittedArray[0];
            int matrixSize = Integer.parseInt(firstString.substring(0, firstString.indexOf('x')));

            // Initialize the board
            char[][] board = new char[matrixSize][matrixSize];
            int countOfX = 0, countOfO = 0;

            // Fill the board and count Xs and Os
            for (int i = 0; i < matrixSize; i++) {
                String row = splittedArray[i + 1];
                for (int j = 0; j < matrixSize; j++) {
                    board[i][j] = row.charAt(j);
                    if (board[i][j] == 'x') countOfX++;
                    if (board[i][j] == 'o') countOfO++;
                }
            }

            // Check for alternating turns (X can be at most one more than O)
            if (Math.abs(countOfX - countOfO) > 1) {
            	invalidBoardsCounts++;
                continue;
            }

            boolean isWinnerX = verifyWinner(board, matrixSize, 'x');
            boolean isWinnerO = verifyWinner(board, matrixSize, 'o');

            // Check for invalid cases:
            // 1. If both X and O won simultaneously.
            // 2. If X and O counts do not match expected game rules.
            // 3. If there is no winner (no valid X or O).
            //if ((xWon && oWon) || (xWon && xCount <= oCount) || (oWon && oCount < xCount)) {
                
            if ((isWinnerX && isWinnerO) ) {
            	invalidBoardsCounts++;
            } else if (isWinnerX) {
            	xWinsCount++;
            } else if (isWinnerO) {
            	oWinsCount++;
            } else {
            	invalidBoardsCounts++; // No winner means invalid.
            }
        }

        return "x: " + xWinsCount + " o: " + oWinsCount + " invalid: " + invalidBoardsCounts;
    }

    // Method to check if a player has won on the board
    private static boolean verifyWinner(char[][] board, int matrixSize, char charXOValue) {
        // Check rows and columns
        for (int i = 0; i < matrixSize; i++) {
        	boolean vr = verifyRow(board, matrixSize, i, charXOValue);
        	boolean vc = verifyColumn(board, matrixSize, i, charXOValue);        	
            if (vr || vc) {
                return true;
            }
        }
        // Check diagonals
        boolean vd = verifyDiagonal(board, matrixSize, charXOValue);
    	boolean vac = verifyAntiDiagonal(board, matrixSize, charXOValue);        
        return vd || vac;
    }

    private static boolean verifyRow(char[][] board, int matrixSize, int row, char charXOValue) {
        for (int col = 0; col < matrixSize; col++) {
            if (board[row][col] != charXOValue) 
            	return false;
        }
        return true;
    }

    private static boolean verifyColumn(char[][] board, int matrixSize, int col, char charXOValue) {
        for (int row = 0; row < matrixSize; row++) {
            if (board[row][col] != charXOValue) 
            	return false;
        }
        return true;
    }

    private static boolean verifyDiagonal(char[][] board, int matrixSize, char charXOValue) {
        for (int i = 0; i < matrixSize; i++) {
            if (board[i][i] != charXOValue) 
            	return false;
        }
        return true;
    }

    private static boolean verifyAntiDiagonal(char[][] board, int matrixSize, char charXOValue) {
        for (int i = 0; i < matrixSize; i++) {
            if (board[i][matrixSize - 1 - i] != charXOValue) 
            	return false;
        }
        return true;
    }

    
    
    
    

    /////////////////////// Ignore the code below //////////////////////////////////
    public static void main (String[] args) {
    	System.out.println("Starting....");
        //Scanner sc = new Scanner(System.in);
//        List<String> inputs = new ArrayList<String>();
        //Input 1
//	      inputs.add("3x3 xxo oo- oxx");
//	      inputs.add("2x2 xo x-");
//	      inputs.add("4x4 xoxo xoxo x--- xo--"); 		
//	      inputs.add("3x3 xxo ooo oox");
//	      inputs.add("3x3 xxx ooo ---");
//	      System.out.println(solution(inputs));
	      
//        //input 2
//		inputs.add("4x4 xxxo o--- o-o- -oxx");
//		inputs.add("4x4 -xxo xxxx --oo -oo-");
//		inputs.add("4x4 o--- x--x xo-o oo-o");		
//        inputs.add("4x4 -x-x xoxx ox-- oo-x");  		
//        inputs.add("4x4 oox- -xo- --ox oo-x");
//		inputs.add("4x4 --ox -oxx xx-x oooo");
//		inputs.add("4x4 -xxo oxo- xxx- o--o");		
//		inputs.add("4x4 oxx- ---- oox- oxoo");	
//		inputs.add("4x4 --oo --x- ---- -oxx");
//		inputs.add("4x4 o-o- xooo ox-- -ooo"); 
//		System.out.println(solution(inputs));
//        //input 3
//		inputs.add("5x5 -xxo- oxo-o -oo-- xox-- x--ox");
//		inputs.add("5x5 x-o-x ooo-- -xoox ooxxo oo-ox");
//		inputs.add("5x5 -oxox ox--o -oxo- -o--- xox-o");		
//        inputs.add("5x5 o--xx ooxxo -oxxo -o-x- o-oxx"); //xwin 		
//        inputs.add("5x5 x---o --ooo o-xoo ---oo xxxx-");
//		inputs.add("5x5 xx--- -x-oo -xx-x -xxxx xoo-x");
//		inputs.add("5x5 xxo-- xoxo- x-x-o ooxo- xoxoo");		
//		inputs.add("5x5 xo-ox o-oxx --xo- -x-oo xo-ox"); //xwin		
//		inputs.add("5x5 o--o- xx-oo xo-ox x-ox- xxoxx");
//		inputs.add("5x5 -xxx- xoooo -o--- -oo-x xox-o");   
//		System.out.println(solution(inputs));
//        //input 4
//		inputs.add("2x2 xo -o");
//		inputs.add("9x9 ox---oo-- -ooxx-x-- ---o-xxox oo-ooxoxx -x-x-o--o oooxxo-xx ox-o-x-xo -----x--o -xoo-xxoo");
//		inputs.add("6x6 ox-x-- --oooo oxooox o--x-o o-ooxx oxoxxx");		
//        inputs.add("7x7 xxx-o-o o--oxxo ox-xxox -ox-x-o --xxxoo -oox-ox xxxoox-");  		
//        inputs.add("9x9 -oxxxo--- o-oooo-xo -o-oxoxoo -oxooo-xx -oo-ox-o- xxx-x-o-x xxx-o--ox xo-oox-o- o-o-xooxx");
//		inputs.add("2x2 -- -o");
//		inputs.add("6x6 oo--o- -x-o-- xoo-o- ox-xxo x-x-x- ox-oo-");		
//		inputs.add("10x10 xxoxoxxxxo -oooooo-ox -o-xxoxxxo x--ox-xxxo o---x-ox-x --ooooo-o- xoooooooxx xoxxx-xoxo ----xx-x-o oxo----xoo");	
//		inputs.add("6x6 -xoooo x-xooo --oo-x ox--xx o--x-- xxoo-x");
//		inputs.add("2x2 ox x-"); 
//		System.out.println(solution(inputs));
//        //input 5
//		inputs.add("8x8 oxxxox-- x-xoxxx- -oxooox- x-x-x-oo x-oo-xx- --o-xxoo -xooooxx xo-xo---");
//		inputs.add("7x7 --oo--o xxxxooo -ooxxox -xoxxo- xooxx-- -xooxoo oox-x--");
//		inputs.add("3x3 -oo oxo -ox");		
//        inputs.add("8x8 xxx-ooxx o-xxxo-o xo-xooxo o--xooxx x-xox-x- xxoo-xo- xx-x-xxx ---x-xox");  		
//        inputs.add("9x9 xooooxoox o----x-ox --xo-ooxo -o---xo-o --xxo-xoo xx---xxoo xxx-oxx-o -x-o-xox- x-xx-x--o");
//		inputs.add("6x6 --o-xo -oxooo o-oxo- --xoxx ooooox xx-xxx");
//		inputs.add("9x9 -o-xxoxoo -oo-x--x- oxxox-oox x-xoo-x-- o-ox--o-o oxox-oxox ooxx---xx o---oxxo- oo-o-oooo");		
//		inputs.add("2x2 xo ox");	
//		inputs.add("10x10 -x-oxo--oo oo-xxooxo- --xxooo-x- ---x-x-xox o--xoo---o xoooox-oo- --xxoo--o- xx-xx---x- --x-xoxx-- x--ox-xxxx");
//		inputs.add("5x5 --o-o xoxx- xo-ox xxoxx oxx-o"); 
//		System.out.println(solution(inputs));
//        //input 6
//		inputs.add("3x3 oxx xxo xoo");
//		inputs.add("3x3 o-o x-o xxx");
//		inputs.add("5x5 o--oo xoxxx xxoox o-oox x-oxo");		
//        inputs.add("6x6 xox-ox oooooo oxxxxx oxoooo xxxoo- xxxxxo");  		
//        inputs.add("4x4 oooo xoxx xxoo xx-x");
//		inputs.add("5x5 -ooox -ooxx --oxx xxxxx xoooo");
//		inputs.add("2x2 -o xo");		
//		inputs.add("7x7 oxoooox -xxxoxo ooooooo -x-ooox xoxx-ox x---xxx x-xoxx-");	
//		inputs.add("5x5 xxoo- xxxox xo-ox xoooo --xo-");
//		inputs.add("3x3 -ox o-x oox"); 
        
    	int[] arr = {2, 3, 4, 10, 40}; // Sorted array
        int x = 10;
        int l = 0;
        int r = arr.length - 1;
        binarySearch(arr, l, r, x);
    }
	
	static int binarySearch(int arr[], int l, int r, int x) {
		
		 int mid = l + (r - l) / 2;

		 if(r >= 1) {
		 
         // Check if target is present at mid
         if (arr[mid] == x) {
             return mid;  // Target found, return its index
         }

         // If target is greater, ignore left half
         if (arr[mid] > x) {
        	 return binarySearch(arr, l, mid+1, x);
             
         } 
         // If target is smaller, ignore right half
         return binarySearch(arr, mid+l, r, x);
		
		
		 }
		
		return -1;
	}
	
	
	
	
	
	

}
