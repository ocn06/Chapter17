/**
 * Created by Olivi on 13-10-2016.
 */
public class Main {
    public static void main(String[] args) {

        IntTree one = new IntTree(7);
        IntTree two = new IntTree(7);
        //System.out.println("Tree structure:");
        one.printSideways();
        //System.out.println();
        //it.printPreorder();
        //it.printInorder();
        //it.printPostorder();

        //System.out.println(one.countLeftNodes()); //ex1
        //System.out.println(one.countEmpty());     //ex2
        //System.out.println(one.depthSum());       //ex3
        //System.out.println(one.countEvenBranches()); //ex4
        //one.printLevel(3);                        //ex5
        //one.printLeaves();                        //ex6
        //System.out.println(one.isFull());         //ex7
        System.out.println(one.toString());     //ex8 VIRKER IKKE
        //System.out.println(one.equals(two));      //ex9 VIRKER IKKE

    }
}
