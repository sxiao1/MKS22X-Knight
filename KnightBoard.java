public class KnightBoard{
  private int [][] board;
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
        newstr += board[i][x];
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
    return true;
  }
  public boolean solveHelper(int startingRow, int startingCol){
    return true;
  }
  public boolean addKnight(int row, int col){
    if(board[row][col] == 0){ //trying all 8 possibilities
      mark(row, col, 1, 2);
      mark(row, col, 1, -2);
      mark(row, col, -1, 2);
      mark(row, col, -1, -2);
      mark(row, col, 2, 1);
      mark(row, col, 2, -1);
      mark(row, col, -2, 1);
      mark(row, col, -2, -1);
      board[row][col] = -1; //setting knight
      return true;
    }
    return false;
  }
  public boolean removeKnight(int row, int col){
    if(board[row][col] == -1){
      demark(row, col, 1, 2);
      demark(row, col, 1, -2);
      demark(row, col, -1, 2);
      demark(row, col, -1, -2);
      demark(row, col, 2, 1);
      demark(row, col, 2, -1);
      demark(row, col, -2, 1);
      demark(row, col, -2, -1);
      board[row][col] = 0;
      return true;
    }
    return false;
  }
  public void mark(int row, int col, int acrossShift, int upShift){
    if(row + acrossShift >= 0 && row + acrossShift < board.length && col+ upShift >= 0 && col + upShift< board[0].length){
      board[row + acrossShift][col + upShift]++;
    }
  }
  public void demark(int row, int col, int acrossShift, int upShift){
    if(row + acrossShift >= 0 && row + acrossShift < board.length && col+ upShift >= 0 && col + upShift< board[0].length){
      board[row + acrossShift][col + upShift]--;
    }
  }
}
