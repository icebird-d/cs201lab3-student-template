import java.util.Arrays;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {
          //first checks
          String[] trav1 = traversal1.split("-");
          String[] trav2 = traversal2.split("-");
          String[] trav3 = traversal3.split("-");

          if (trav1.length != trav2.length || trav1.length != trav3.length) {
               return "Invalid traversals.";
          }

          if (checkTraversals(trav1, trav2, trav3) && checkInorder(trav2)) {
               return "Traversal 1 - Preorder, Traversal 2 - Inorder, Traversal 3 - Postorder";
          } else if (checkTraversals(trav1, trav3, trav2) && checkInorder(trav3)) {
               return "Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder";
          } else if (checkTraversals(trav2, trav1, trav3) && checkInorder(trav1)) {
               return "Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder";
          } else if (checkTraversals(trav2, trav3, trav1) && checkInorder(trav3)) {
               return "Traversal 1 - Postorder, Traversal 2 - Preorder, Traversal 3 - Inorder";
          } else if (checkTraversals(trav3, trav1, trav2) && checkInorder(trav1)) {
               return "Traversal 1 - Inorder, Traversal 2 - Postorder, Traversal 3 - Preorder";
          } else if (checkTraversals(trav3, trav2, trav1) && checkInorder(trav2)) {
               return "Traversal 1 - Postorder, Traversal 2 - Inorder, Traversal 3 - Preorder";
          }

          return "Invalid traversals.";
     }

     public static boolean checkTraversals(String[] pre, String[] in, String[] post) {
          //base case
          if (pre.length == 1) {
               return true;
          }
          
          int last = pre.length - 1; //last index

          //check root
          if (!(pre[0].equals(post[last]))) {
               return false;
          }

          String root = pre[0];

          //split subtrees
          int rootIn = Arrays.asList(in).indexOf(root); //index of root in Inorder
          
          //left subtree
          if (rootIn > 0) {
               String[] preL = Arrays.copyOfRange(pre, 1, rootIn+1);
               String[] inL = Arrays.copyOfRange(in, 0, rootIn);
               String[] postL = Arrays.copyOfRange(post, 0, rootIn);

               if (!(checkTraversals(preL, inL, postL))) {
                    return false;
               }
          }

          //right subtree
          if (rootIn < last) {
               String[] preR = Arrays.copyOfRange(pre, rootIn+1, last+1);
               String[] inR = Arrays.copyOfRange(in, rootIn+1, last+1);
               String[] postR = Arrays.copyOfRange(post, rootIn, last);

               if (!(checkTraversals(preR, inR, postR))) {
                    return false;
               }
          }
          
          return true;
     }

     public static boolean checkInorder(String[] in) {
          for (int i = 1; i < in.length; i++) {
               if (in[i].compareTo(in[i-1]) < 0) {
                    return false;
               }
          }
          return true;
     }
}