package com.tacoscott.chapter01;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by scott.powers on 3/21/17.
 */
public class q4 {
  //Start @ 9:15am
  //My re-wording of question:
  //  Given a string, output true/false if the string can be rearranged into a palindrome.
  //Questions I would ask:
  //  Actually output all the variants? Because that complicates things.
  //Initial Thoughts:
  //  A palindrome must be the same backwards as forwards. There are two variants. Even length and odd length.
  //  Even length inputs must contain the same amount of each character.
  //  Odd length inputs must contain the same amount of each character for all but one character, which must contain an
  //  odd number of that character. (I think).


  private static boolean firstPass(String input) {
    Map<Character,Integer> charCount = new HashMap<>();
    String lowerCasedInput = input.toLowerCase(); //lets assume lowercase, seems to be the case with these questions
    for (int i=0;i<input.length();i++) {
      char c = lowerCasedInput.charAt(i);
      if (c ==  ' ') { continue; } //might be good to set up a blacklist
      if (charCount.containsKey(c)) {
        charCount.put(c,charCount.get(c) + 1);
      } else {
        charCount.put(c,1);
      }
    }

    int foundOdds = 0;
    for (Character c: charCount.keySet()) {
       if (charCount.get(c) % 2 != 0) {
         foundOdds++;
         if (foundOdds > 1) {
           return false;
         }
       }
    }
    return true;
  }

  //cleanup of some extra work
  private static boolean secondPass(String input) {
    boolean[] charCounts = new boolean[128];
    String lowerCasedInput = input.toLowerCase(); //lets assume lowercase, seems to be the case with these questions
    for (int i=0;i<input.length();i++) {
      char c = lowerCasedInput.charAt(i);
      if (c ==  ' ') { continue; } //might be good to set up a blacklist
      charCounts[c] = !charCounts[c];
    }

    int oddCounts = 0;
    for (boolean charCount : charCounts) {
      if (charCount) {
        oddCounts++;
        if (oddCounts > 1) {
          return false;
        }
      }
    }

    return true;
  }
  //End @ 9:34am
  //Hints used: none
  //Thoughts on solution before looking at book:
  //  I like the second pass better, but it again limits to standard ascii, but that seems to be what the book is going
  //  for in these initial questions. I like the bitwise "flip" count method better.
  //Thoughts on solution(s) after looking at book:
  //  I had thought about using their solution 2, kind of keeping a rolling odd counter but thought it wasn't much
  //  better than the route I was going. They seem to somewhat agree.
  //  I had thought of an alternative bit solution similar to their solution 3, but now that I'm timing my solutions
  //  in a way similar to a whiteboarding session, I did not come up with that solution first.

  private static void passThru(String input) {
    System.out.println("Palindrome Detector: '" + input + "' -> '" + secondPass(input) + "'");
  }

  public static void main(String[] args) {

    passThru("tacobell");
    passThru("tacocat");
    passThru("abcba");
    passThru("cato");
  }
}
