package com.tacoscott.chapter01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by scott.powers on 3/19/17.
 */
public class q3 {
  //Start @ 2:45pm
  //My re-wording of question:
  //  Replace all spaces in a string with %20, thus moving the remaining contents a total of 2 spaces to the "right".
  //Questions I would ask:
  //  My question(s) seemed to be address by the question this time. Limit to char array, and perform the replace/shift
  //  in place

  private static char[] firstPass(char[] urlify, int expectedLength) {
    for (int i=0; i < expectedLength; i++) {
      //if we see a space at current index
      if (urlify[i] == ' ') {
        //move content after the space
        for (int x=expectedLength-1; x > i; x--) {
          urlify[x] = urlify[x-2];
        }
        urlify[i] = '%';
        urlify[i+1] = '2';
        urlify[i+2] = '0';
      }
    }
    return urlify;
  }

  // I doubt I have to shift the string every time we find a space, we can pre compute the shift offset.
  private static char[] secondPass(char[] urlify, int expectedLength) {
    if (expectedLength == 0) { return urlify; }
    if (expectedLength == 3 && urlify[0] == ' ') {
      urlify[0] = '%';
      urlify[1] = '2';
      urlify[2] = '0';
      return urlify;
    }

    int trailingSpaceStartIndex = -1;
    for (int i=expectedLength-1; i > 0; i--) {
      if (urlify[i] == ' ' && urlify[i-1] != ' ') {
        trailingSpaceStartIndex = i;
        break;
      }
    }

    int writeIndex = expectedLength - 1;
    for (int i=trailingSpaceStartIndex-1; i >= 0; i--) {

      if (urlify[i] == ' ') {
        urlify[writeIndex-2] = '%';
        urlify[writeIndex-1] = '2';
        urlify[writeIndex] = '0';
        writeIndex-=3;
      } else {
        urlify[writeIndex] = urlify[i];
        writeIndex--;
      }
    }
    return urlify;
  }
  //End @ 3:15pm
  //Hints used: none
  //Thoughts on solution before looking at book:
  //  An initial solution did not take me much time, but it shifts the remaining char array in place for every space it
  //  finds, and this was duplicate work, but this solution took me very little time to arrive at.
  //  secondPass took me about 20 minutes, and while it reduces some operations, it feels a little clunky.
  //Thoughts on solution(s) after looking at book:
  //  I didn't so much look at the space count, as I looked at where I should start writing chars to, although it ends
  //  up being basically the same thing. I used a write index which starts at the last index of the char array.
  //  I then start looking at the char array starting at where the trailing spaces begin, minus one index. That gives me
  //  the actual start of the "string" and the actual index to write to.
  //  I then iterate backwards from the start of the actual "string" and start writing to the "Write index", adjusting
  //  it by -3 every time I find a legit space, and adjusting it by -1 every time I find a regular char.
  //  I'm not sure if my solution is better or worse and I'd like to move on to more questions so I might revisit.

  private static void passThru(char[] urlify,int expectedLength) {
    System.out.println("Urlify: '" + new String(urlify) + "' -> '" + new String(secondPass(urlify,expectedLength)) + "'");
  }

  public static void main(String[] args) {
    //rare case that problem might not care about
    passThru("   ".toCharArray(),"   ".length());
    passThru(" sdfsf sdf    ".toCharArray()," sdfsf sdf    ".length());
    passThru("".toCharArray(),"".length());
    passThru("tacobell".toCharArray(),8);
    passThru("taco bell  ".toCharArray(),"taco bell  ".length());
    passThru("taco bell taco    ".toCharArray(),"taco bell taco    ".length());
  }
}
