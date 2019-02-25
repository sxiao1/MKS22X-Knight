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
  public boolean checker(int row, int col){
    return (row < board.length && row >= 0 && col < board[0].length && col >= 0);
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
    return countHelper(startRow, startCol, 1);
  }
  public int countHelper(int row, int col,int position){
      if(position >= board.length * board[0].length){
        //removeK(row, col, position);
        return 1;
      }
      if(row >= board.length || col >= board[0].length || row < 0 || col < 0){
        return 0;
      }
    for(int i = 0; i < moves.length; i++){
      int newRow = row + moves[i][0];
      int newCol = col + moves[i][1];
      if(checker(newRow, newCol) && board[newRow][newCol] == 0){
        board[row][col] = position;
        total += countHelper(newRow, newCol, position +1);
        board[row][col] = 0;
      }
    }
  return total;
}
//testcase must be a valid index of your input/output array
public static void runTest(int i){

  KnightBoard b;
  int[]m =   {4,5,5,5,5};
  int[]n =   {4,5,4,5,5};
  int[]startx = {0,0,0,1,2};
  int[]starty = {0,0,0,1,2};
  int[]answers = {0,304,32,56,64};
  if(i >= 0 ){
    try{
      int correct = answers[i];
      b = new KnightBoard(m[i%m.length],n[i%m.length]);

      int ans  = b.countSolutions(startx[i],starty[i]);

      if(correct==ans){
        System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
      }
    }catch(Exception e){
      System.out.println("FAIL Exception case: "+i);

    }
  }
}
public static String checker(int num){
  runTest(1);
  return "";
}
}
