import java.util.Scanner;
public class NQueens {
    char board[][];
    public NQueens(int n) {
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ' ';
            }
        }
    }
    int countOfQueenOnBoard = 0 ;
    //The following solves it by assuming at first there is no Queen on board and can solve approximately up to N = 13
    public boolean solve (){
    for (int i = 0 ; i<board.length;i++) {
        for (int j = 0; j < board.length; j++) {
            if(constraintsSatisfied(i,j) && !inCol(j)){
             board[i][j]='Q';
                countOfQueenOnBoard++;
             if(solve())
                 return true;
             board[i][j]=' ';
                countOfQueenOnBoard--;
            }
        }
    }
    if(countOfQueenOnBoard==board.length)
        return true ;
    return false ;
    }
    //the following function solves it by assuming there is at least should be one queen at every column and it can solve up to approximatly N=30
    public boolean solve(int column) {
        if (column >= board.length)
            return true;
        for (int i = 0; i < board.length; i++) {
            if (constraintsSatisfied(i, column)) {
                board[i][column] = 'Q';
                if (solve(column + 1))
                    return true;
                board[i][column] = ' ';
            }
        }
        return false;
    }

    public boolean constraintsSatisfied(int row, int column) {
        return !(inRow(row) || inDiagonal(row, column) || inSecDiogonal(row, column));
    }
    public boolean inCol (int col) {
        for (int i = 0; i < board.length ; i++)
            if (board[i][col]=='Q')
                return true;
        return false ;
    }
    public boolean inRow(int row) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == 'Q')
                return true;
        }
        return false;
    }

    public boolean inDiagonal(int row, int column) {
        if (board[row][column] == 'Q')
            return true;
        else {
            int i = row;
            int j = column;
            while (i < board.length && j < board.length) {
                if (board[i][j] == 'Q')
                    return true;
                i++;
                j++;
            }
            i = row;
            j = column;
            while (i >= 0 && j >= 0) {
                if (board[i][j] == 'Q')
                    return true;
                i--;
                j--;
            }
            return false;
        }
    }

    public boolean inSecDiogonal(int row, int column) {
        if (board[row][column] == 'Q')
            return true;
        else {
            int i = row;
            int j = column;
            while (i < board.length && j >= 0) {
                if (board[i][j] == 'Q')
                    return true;
                i++;
                j--;
            }
            i = row;
            j = column;
            while (i >= 0 && j < board.length) {
                if (board[i][j] == 'Q')
                    return true;
                i--;
                j++;
            }
        }
        return false;
    }
    public void printBoard (){
        for (int i = 0 ;i<board.length;i++){
            for (int j = 0; j <board.length ; j++) {
                System.out.print("|"+board[i][j]);
                if (j==board.length-1)
                    System.out.print("|");

            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        NQueens y = new NQueens(n);
        y.solve(0);
        y.printBoard();
        System.out.println();
        NQueens x = new NQueens(n);
        x.solve();
        x.printBoard();

    }
}