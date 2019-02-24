public class driver{
  public static void main(String[] args){
    KnightBoard newboard = new KnightBoard(3,4);
    //System.out.println(newboard);
    System.out.println(newboard.countSolutions(1,0));
    //System.out.println(newboard.solve(0,0));
    //System.out.println(newboard);
  }
}
