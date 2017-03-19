package com.tacoscott.chapter01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by scott.powers on 3/19/17.
 */
public class q2 {
  //Start @ 2:20pm
  //My re-wording of question:
  //  Given two strings, check to see if they both contain the same chars, in the same amounts.
  //Questions I would ask:
  //  Any limitations on character set? Standard ASCII? English chars? Unicode/etc?
  //  Case sensitive?
  //Function will return true if both strings are permutations, false if there are not

  //First thought is to make sure the strings are the same length
  //Then I'd use a Map<Character,Integer> to maintain char counts;
  private static boolean firstPass(String compare1, String compare2) {
    if (compare1.length() != compare2.length()) { return false; }
    Map<Character,Integer> charCounts = new HashMap<>();

    for (int i=0;i<compare1.length();i++) {
      Character c = compare1.charAt(i);
      if (charCounts.containsKey(c)) {
        int newCount = charCounts.get(c) + 1;
        charCounts.put(c,newCount);
      } else {
        charCounts.put(c,1);
      }
    }

    for (int i=0;i<compare2.length(); i++) {
      Character c = compare2.charAt(i);
      if (charCounts.containsKey(c)) {
        int newCount = charCounts.get(c) - 1;
        if (newCount < 0) { return false; }
        charCounts.put(c,newCount);
      } else {
        return false;
      }
    }
    return true;
  }


  //End @ 2:34
  //Hints used: none
  //Thoughts before looking at solution(s):
  //  if we can limit the character set, similar to the 1.1, we could do this with a array of ints
  //  we could also sort the strings first, and then do a simple index by index comparision, but that
  //  seems like it would be more total operations
  //  otherwise, I feel like this solution is generally reasonable.
  //Thoughts after looking at solution(s):
  //  They sort the string first using char's which will fail on multibyte characters. Although I'm starting to suspect
  //  they generally don't care about unicode strings so may account for that first in future solutions.
  //  Otherwise, this is pretty close to one of my alternate ideas of comparing lengths first, sorting,
  //  then doing a simple comparision
  //  the second solution they offer is closer to mine, but they again use simpler data types/structures which I will
  //  consider for future solutions.

  private static void passThru(String compare1, String compare2) {
    System.out.println("Comparing: " + compare1 + " -> " + compare2 + " : " + firstPass(compare1,compare2));
  }

  public static void main(String[] args) {
    passThru("tacobell","dfgdfg");
    passThru("taco","ocat");
    passThru("aabbcc","bbggdd");
    passThru("abcd","dacb");
  }
}
