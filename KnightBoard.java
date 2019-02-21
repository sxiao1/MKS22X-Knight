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
          newstr += "" + board[i][x];
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
    if(row > 0 && col > 0 && row < board.length && col < board[0].length && board[row][col] == 0){
      board[row][col] = position;
      return true;
    }
    else{
      return false;
    }
  }
  public boolean removeK(int row, int col, int position){
    if(row > 0 && col > 0 && row < board.length && col < board[0].length){
      board[row][col] = 0;
      return true;
    }
    else{
      return false;
    }
  }
  public boolean solveHelper(int startingRow, int startingCol, int position){
    if(position > board.length * board[0].length){
      return true;
    }
    else{
      if(addK(startingRow, startingCol, position)){
        ArrayList<Integer> list = diffMoves(startingRow, startingCol);
        for(int i = 0; i < list.size(); i += 2){
          int newRow = startingRow + list.get(i);
          int newCol = startingCol + list.get(i + 1);
          if(solveHelper(newRow, newCol, position + 1)){
            return true;
          }
          removeK(startingRow, startingCol, position);
        }
      }
      return false;
    }
  }
  public ArrayList<Integer> diffMoves(int row, int col){
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i = 0; i < moves.length; i++){
      int newRow = row + moves[i][0];
      int newCol = col + moves[i][1];
      if(newRow > 0 && newCol > 0 && newRow < board.length && newCol < board[0].length){
        list.add(moves[i][0]);
        list.add(moves[i][1]);
      }
    }
    return list;
  }
}
