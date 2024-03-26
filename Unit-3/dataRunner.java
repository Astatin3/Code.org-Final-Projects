import java.util.Scanner;

public class DataRunner {
  public static void main(String[] args) {
    // Statistics plotter thing!
    // Options:
    // Option 0 - population
    // Option 1 - unemployment %
    // Option 2 - internet %

    int opt1 = 0;
    int opt2 = 2;

    int width = 50;
    int height = 100;
    
    UserStory us = new UserStory(opt1, opt2, width, height);
    System.out.print(us);
  }
}