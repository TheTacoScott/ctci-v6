package com.tacoscott.chapter01;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by scott.powers on 3/19/17.
 */
public class q1 {
  //Start @ 1:42pm
  //My re-wording of question:
  //  Implement a method that determines if a string contains more than one of the same character.
  //  2nd part of question: Do not use additional arrays/hashmaps etc. Attempt to solve it with only simple data types
  //Questions I would ask:
  //  Any limitations on character set? Standard ASCII? English chars? Unicode/etc?
  //Without questions answered, I will assume:
  //  Limited to case sensitive standard ascii english chars.
  //
  //Function will return true if all chars are unique, false if there are dupes

  private static boolean firstPass(String compare) {
    int[] foundChars = new int[256];
    for (int i=0;i<compare.length();i++) {
      foundChars[compare.charAt(i)]++;
      if (foundChars[compare.charAt(i)] > 1) {
        return false;
      }
    }
    return true;
  }

  //int's are useless and a waste of memory, booleans will work
  //256 could be reduced.
  private static boolean secondPass(String compare) {
    boolean[] foundChars = new boolean[256];
    for (int i=0;i<compare.length();i++) {
      if (foundChars[compare.charAt(i)]) {
        return false;
      }
      foundChars[compare.charAt(i)] = true;
    }
    return true;
  }

  //this would help us with some unicode considerations if that's needed, but we have to use full fledged objects
  //instead of primitives.
  private static boolean thirdPass(String compare) {
    //Did not know the max possible unicode chars:
    //Used: http://stackoverflow.com/questions/5924105/how-many-characters-can-be-mapped-with-unicode
    if (compare.length() > 1111998) { return false; }
    Set<Character> foundChars = new HashSet<>();
    for (int i=0;i<compare.length();i++) {
      Character c =compare.charAt(i);
      if (foundChars.contains(c)) {
        return false;
      }
      foundChars.add(c);
    }
    return true;
  }

  //excluding additional data structures
  private static boolean fourthPass(String compare) {
    for (int i=0;i<compare.length();i++) {
      for (int x=i+1;x<compare.length();x++) {
        if (compare.charAt(i) == compare.charAt(x)) {
          return false;
        }
      }
    }
    return true;
  }

  //End @ 1:58pm
  //hints used: none
  //thoughts on solution before looking at solution in book:
  //  Generally like thirdPass for it's expandability and readability.
  //  If absolute minimal memory footprint is required, it's "pretty good"
  //  fourthPass excludes other data structures, but I'm never a big fan of n^2
  //thoughts on solution after looking at solution in book:
  //  I did not think of a max length consideration as a way of short circuiting for my initial functions
  //  When you know the possible character range, this is a good idea.
  //  Otherwise, my secondPass solution is very similar, although I considered extended ascii.
  //  The bit operator solution was not something I'd considered, although I think with UniCode this would not work
  //  as desired anyway
  //  My excluded data structures solution was similar to the first option they proposed.
  //  I had thought about sorting the string and then comparing adjacent indexes, but had similar concerns the book had
  //  in that I thought I'd have to use a pretty poor sorting algorithm to sort it in place.
  //Final Thoughts:
  //  Generally I like thirdPass for it's readability, functionality and unicode support
  //  forthPass meets the criteria of the expanded question, but I find it clunky.

  private static boolean passThru(String compare) {
    return fourthPass(compare);
  }

  public static void main(String[] args) {
    System.out.println("Checking: taco: " + passThru("taco"));
    System.out.println("Checking: tacobell: " + passThru("tacobell"));
    System.out.println("Checking: tacobel9: " + passThru("tacobel9"));
    System.out.println("Checking: tacobel&&9: " + passThru("tacobel&&9"));
  }
}
