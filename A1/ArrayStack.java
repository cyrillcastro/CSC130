/* Cyrill Castro
** CSC 130-02
** Prof Anna Baynes & Prof Jagan Chidella
**
** Project 1 : Sound Blaster
*/

import java.util.EmptyStackException;

public class ArrayStack implements DStack {
   private double stack[]; // array that is being used 
   private int index;
      
   public ArrayStack() {
      stack = new double[10];
      index = 0;
   }
      
   public boolean isEmpty() {
      return (index == 0);
   }
    
   /* Increment the number of values, then push the desired
   ** value into the next available index. Afterwards, check if array 
   ** needs to be resized
   */
   public void push(double d) {
      if (this.index == this.stack.length) {
         double newstack[] = new double[2*this.stack.length];
         for (int i = 0; i < this.stack.length; i++) {
            newstack[i] = this.stack[i];
         }
         this.stack = newstack; //Updated array overwrites old
      }
      stack[this.index] = d;
      this.index++;      
   }

   /* 
   ** Return the last value, which will be deleted. Throw exception if empty. 
   */
   public double pop() {
      double result = 0.0;
      if (this.isEmpty() == true) {
         throw new EmptyStackException();
      } else {
         result = stack[index];
         this.index--; 
         return result;
      }
   }

   /**
   * Throw EmptyStackException if stack is empty
   */
   public double peek() {      
      if (this.isEmpty() == true) {
         throw new EmptyStackException();
      } else {
         return stack[index];
      }
   }

}