public class driver{
  public static void main(String[] args){
    KnightBoard newboard = new KnightBoard(5,5);
    System.out.println(newboard.solve(0,0));
    System.out.println(newboard);
  }
}
