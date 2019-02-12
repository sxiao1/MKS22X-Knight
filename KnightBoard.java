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
  
}
