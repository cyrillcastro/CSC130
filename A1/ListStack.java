/* Cyrill Castro
** CSC 130-02
** Prof Anna Baynes & Prof Jagan Chidella
**
** Project 1 : Sound Blaster
*/

import java.util.EmptyStackException;

public class ListStack implements DStack {
   // Inner node class
   private static class StackNode {
      private double data;
      private StackNode next;
      
      public StackNode(double data, StackNode next) {
         this.data = data;
         this.next = next;
      }
   } 
   
   private StackNode top;
   
   /** 
   * Check if the stack is empty.
   */
   public boolean isEmpty() {
      return (top == null);
   }
    
   /**
   * Push the double into the stack.
   */
   public void push(double d) {
      if (isEmpty() == true) {
         top = new StackNode(d, null);
      } else {
         StackNode newNode = new StackNode(d, null);
         StackNode temp = top; // holds the rest of the list
	      top = newNode; //connect the new node to the beginning
         top.next = temp; //new top becomes connected to temp (original top)
      }
   }

   /**
   * Return the deleted value.
   * Throw EmptyStackException if stack is empty.
   */
   public double pop() {
      double result = 0.0;
      
      // check if the stack is empty
      if (isEmpty() == true) {
         throw new EmptyStackException();
      } else {
         result = top.data; // record value of front before deleting 
         top = top.next;
         return result;
      }
   }

   /**
   * Throw EmptyStackException if stack is empty
   */
   public double peek() {
      if (isEmpty() == true) {
         throw new EmptyStackException();
      } else {
	      return top.data;
      }
   }
}