package com.tacoscott.chapter01;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by scott.powers on 3/21/17.
 */
public class q5 {
  //Start @ 5:43pm
  //My re-wording of question:
  //  Given two strings, check to see if the second is one minor edit away from the first.
  //  Minor edits are considered a single insert/delete/replace of a char
  //Questions I would ask:
  //  None that I'm away
  //Initial Thoughts:
  //If the string lengths vary by greater than one, return false


  private static boolean firstPass(String input1, String input2) {
    if (Math.abs(input1.length() - input2.length()) > 1) { return false; }
    boolean foundEdit = false;
    for(int i=0;i<input1.length();i++) {
      if (input2.length() < i) {
        char a = input1.charAt(i);
        char b = input2.charAt(i);
        if (a != b) {
          if (foundEdit) {
            return false;
          } else {
            foundEdit = true;
          }
        }
      }
    }
    return foundEdit;
  }


  private static void passThru(String input1, String input2) {
    System.out.println("Minor Edit Detector Detector: '" + input1 + "' '"+input2+"' -> " + firstPass(input1,input2));
  }

  public static void main(String[] args) {

    passThru("tacobell","tacbell"); //delete
    passThru("tacobell","tacobbell"); //insert
    passThru("tacobell","tacogell"); //replace
  }
}
