import java.util.*;
public class KnightBoard{
  private int [][] board;
  private int [][] moves = {{1,2}, {1,-2}, {-1,2}, {-1,-2}, {2,1}, {2,-1} ,{-2,1} ,{-2,-1}};
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        board[i][x] = 0;
      }
    }
  }
  public String toString(){
    String newstr = "";
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        if(board[i][x] == 0){
          newstr += "_ ";
        }
        else{
          newstr += board[i][x] + " ";
        }
      }
      newstr += "\n";
    }
    return newstr;
  }
  public boolean solve(int startingRow, int startingCol){
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        if(board[i][x] != 0){
          throw new IllegalStateException();
        }
      }
    }
    if(startingRow < 0 && startingCol< 0 && startingRow > board.length && startingCol > board[0].length){
      throw new IllegalArgumentException();
    }
    return solveHelper(startingRow,startingCol,1);
  }
  public boolean addK(int row, int col, int position){
    if(row >= 0 && col >= 0 && row < board.length && col < board[0].length && board[row][col] == 0){
      board[row][col] = position;
      return true;
    }
    else{
      return false;
    }
  }
  public boolean removeK(int row, int col, int position){
    if(row >= 0 && col >= 0 && row < board.length && col < board[0].length){
      board[row][col] = 0;
      return true;
    }
    else{
      return false;
    }
  }
  public boolean solveHelper(int startingRow, int startingCol, int position){
    if(position == board.length * board[0].length){
      board[startingRow][startingCol] = position;
      return true;
    }
    ArrayList<Integer> list = diffMoves(startingRow, startingCol);
    board[startingRow][startingCol] = position;
		//System.out.println(this);
    for(int i = 0; i < list.size(); i += 2){
      if(solveHelper(list.get(i), list.get(i+1), position + 1)){
        return true;
      }
    }
    removeK(startingRow, startingCol, position);
    return false;
  }
  public ArrayList<Integer> diffMoves(int row, int col){
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i = 0; i < moves.length; i++){
      int newRow = row + moves[i][0];
      int newCol = col + moves[i][1];
      if(newRow >= 0 && newCol >= 0 && newRow < board.length && newCol < board[0].length && board[newRow][newCol] == 0){
        //System.out.println(moves[i][0] + " " + moves[i][1]);
        list.add(newRow);
        list.add(newCol);
      }
    }
    return list;
  }
  public int countSolutions(int startRow, int startCol){
    if(board.length == 0){
      return 0;
    }
    for(int r = 0; r < board.length; r++){
      for(int c = 0; c < board[0].length; c++){
        if(board[r][c] != 0){
          throw new IllegalStateException();
        }
      }
    }
    if(startRow < 0 || startCol < 0 || startRow > board.length || startCol > board[0].length){
      throw new IllegalArgumentException();
    }
    return countHelper(startRow, startCol,0);
  }
  public int countHelper(int row, int col, int count){
    int temp = 0;
    for(int i = 0; i < moves.length; i++){
      if(addK(row, col, 1)){
        int newRow = row + moves[i][0];
        int newCol = col + moves[i][1];
        if(newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length){
          temp += countHelper(newRow, newCol, count);
        }
        removeK(row, col, 1);
      }
    }
    return count + temp;
  }
}
