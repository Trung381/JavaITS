public class MultiplicationTable {
    public void printMultiplicationTable(){
        System.out.print("\nBảng cửu chương 1 -> 9 :v");
        for(int num1 = 1; num1 <10; num1 ++){
            System.out.print("\n");
            for(int num2 = 1; num2 < 10; num2 ++){
                System.out.printf("%d * %d = %d \t\t", num2, num1, num1 * num2);
            }
        }
    }
}
