import java.util.*;
public class KnightBoard{
  private int total = 0;
  private int [][] board;
  private int [][] moves = {{1,2}, {1,-2}, {-1,2}, {-1,-2}, {2,1}, {2,-1} ,{-2,1} ,{-2,-1}}; //array of moves
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[i].length; x++){
        board[i][x] = 0; //setting all positions to 0
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
    return solveHelper(startingRow,startingCol,1); //calling helper
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
  public boolean removeK(int row, int col){
    if(row >= 0 && col >= 0 && row < board.length && col < board[0].length && board[row][col] != 0){
      board[row][col] = 0;
      return true;
    }
    else{
      return false;
    }
  }
  public boolean solveHelper(int startingRow, int startingCol, int position){ //helper
    if(position == board.length * board[0].length){
      board[startingRow][startingCol] = position;
      return true;
    }
    ArrayList<Integer> list = diffMoves(startingRow, startingCol); //list of possible moves
    board[startingRow][startingCol] = position;
		//System.out.println(this);
    for(int i = 0; i < list.size(); i += 2){
      if(solveHelper(list.get(i), list.get(i+1), position + 1)){ //recursive
        return true;
      }
    }
    removeK(startingRow, startingCol);
    return false;
  }
  public ArrayList<Integer> diffMoves(int row, int col){ //creating the array
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
    //board[startRow][startCol] = 1;
    int count = 0;
    for(int i = 0; i < board.length; i++){
      for(int x = 0; x < board[0].length; x++){
        count += countHelper(startRow, startCol, 1); //calling helper
      }
    }
    return count;
  }
  public int countHelper(int row, int col,int position){
      if(position == board.length * board[0].length + 1){
        //removeK(row, col, position);
        return 1;
      }
      if(row >= board.length || col >= board[0].length || row < 0 || col < 0){
        return 0;
      }
    else{
      if(addK(row, col, position)){
        //if(position == board.length* board[0].length){
        //  total ++;
        //}
      //ArrayList<Integer> list = diffMoves(row,col);
      for(int i = 0; i < moves.length; i++){
        if(row + moves[i][0] >= 0 && col + moves[i][1]>= 0 && row + moves[i][0]< board.length && col + moves[i][1]< board[0].length){
        total += countHelper(row + moves[i][0], col + moves[i][1], position + 1);
        board[row + moves[i][0]][col + moves[i][1]] = 0;
      }
      }
    }
  }
  return total;
}
}
