package com.RecurrssionConcepts;

public class sudokuSolver {
    public static void main(String[] args) {
        int[][] board={
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        if (solve(board)){
            displayBoard(board);
        }else {
            System.out.println("cannot be solved");
        }
    }
    
    static boolean solve(int[][] board){
        int n= board.length;
        int row=-1;
        int col=-1;
        //to indicate empty box
        boolean emptyLeft=true;//this means no empty box in the board
        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j]==0){
                    row=i;
                    col=j;
                    emptyLeft=false;//this means there are empty box in the board
                    break;
                }
            }
            //if you found element in the row then break
            if(emptyLeft==false){
                break;
            }
        }
        //if empty left is true the sudoko is solved
        if(emptyLeft==true){
            return true;
        }

        //backtrack
        for (int number = 1; number <=9 ; number++) {
            if(isSafe(board,row,col,number)){
                board[row][col]=number;
                if(solve(board)){
                    return true;
                }
                else {
                    board[row][col]=0;
                }
            }
        }
        return false;
    }

    static void displayBoard(int[][] board){
        for (int[] row :board){
            for (int num:row) {
                System.out.print(num+" ");
            }
            System.out.println();
        }
    }


    static boolean isSafe(int[][] board,int row,int col,int n){
        //checking row
        for (int i = 0; i < board.length; i++) {
            if(board[row][i]==n){
                return false;
            }
        }

        //checking coloumn
        for (int i = 0; i < board.length; i++) {
            if(board[i][col]==n){
                return false;
            }
        }

        //checking the box
        int term=(int)Math.sqrt(board.length);
        int brow=row-row%term;
        int bcol=col-col%term;
        for (int i = 0; i < term; i++) {
            for (int j = 0; j < term; j++) {
                if (board[brow+i][bcol+j]==n){
                    return false;
                }
            }
        }
        
        return true;

    }
}
