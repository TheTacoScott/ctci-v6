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

  //Restarted at 10:23am the next day
  //Dropping firstPass and starting over fresh.

  private static boolean secondPass(String input1, String input2) {
    boolean possibleAdd = input1.length() - input2.length() == - 1;
    boolean possibleDelete = input1.length() - input2.length() == 1;
    boolean possibleReplace = input1.length() == input2.length();

    if (!(possibleAdd || possibleDelete || possibleReplace)) {
      return false;
    }

    int i1 = 0;
    int i2 = 0;

    boolean foundSingleChange = false;
    while (i1 < input1.length() && i2 < input2.length()) {

      char a = input1.charAt(i1);
      char b = input2.charAt(i2);
      if (a != b) {
        if (foundSingleChange) { return false; }
        foundSingleChange = true;

        if (possibleAdd) {
          i2++;
        } else if (possibleDelete) {
          i2--;
        }
      }
      i1++;
      i2++;
    }
    return foundSingleChange;
  }
  //End @ 10:35am
  //Hints used: none
  //Thoughts before looking at solution in book:
  //  Generally pretty happy with secondPass. It short circuits out if it's an obvious major edit, and can identify the
  //  type of edit it's likely to see/allow.
  //  The incrementing read indexes seem reasonable, and the while loop protect array out of bounds.
  //Thoughts after looking at solution(s) in book:
  //  My solution is very simialr to their refinement of their first solution. Using advancing read indexes.
  //  I pre-identify the type of minor edit that would be allowed based on the string length diff, and use that to
  //  return early if it meets no criteria. I then use that pre-identified type of edit to advance the indexes rather
  //  than do the calulate the string length diff every loop.

  private static void passThru(String input1, String input2) {
    System.out.println("Minor Edit Detector: '" + input1 + "' '"+input2+"' -> " + secondPass(input1,input2));
  }

  public static void main(String[] args) {

    passThru("tacobell","tacbell"); //delete
    passThru("tacobell","tacobbell"); //insert
    passThru("tacobell","tacogell"); //replace

    passThru("tacobell","tacobell"); //no change
    passThru("tacobell","tacobellll"); //too many changes
    passThru("tacobell","tac"); //too many changes
  }
}
