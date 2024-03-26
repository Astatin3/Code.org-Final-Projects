import java.util.Scanner;

public class UserStory {

  private String[] countries = FileReader.toStringArray("countries.txt");
  
  private double[] populations = FileReader.toDoubleArray("populations.txt");
  private double[] unemployment = FileReader.toDoubleArray("unemployment.txt");
  private double[] internetpercent = FileReader.toDoubleArray("internetpercent.txt");
  
  private int opt1;
  private int opt2;

  private int width;
  private int height;
  
  public UserStory() {
    this(0, 2, 50, 100);
  }

  public UserStory(int opt1, int opt2, int width, int height){
    this.opt1 = opt1;
    this.opt2 = opt2;
    
    this.width = width;
    this.height = height;
  }

  public double[] getArrayFromOpt(int i) {
    switch(i) {
      case 0:
        return this.populations;
      case 1:
        return this.unemployment;
      case 2:
        return this.internetpercent;
      default:
        return null;
    }
  }

  public String getNameFromOpt(int i) {
    switch(i) {
      case 0:
        return "populations";
      case 1:
        return "unemployment";
      case 2:
        return "internetpercent";
      default:
        return null;
    }
  }

  // Method to find maximum in an array 
  public double max(double[] arr){ 
    double max = arr[0]; 
    for (int i = 0; i < arr.length; i++) 
      if (arr[i] > max){
        max = arr[i]; 
      }
     
    return max; 
  }

  // Find the corrosponding country name, of largest value of array
  public String maxStr(double[] arr){
    double max = arr[0]; 
    String maxName = "";
    for (int i = 0; i < arr.length; i++) 
      if (arr[i] > max){
        max = arr[i]; 
        maxName = this.countries[i];
      }
     
    return maxName; 
  }

  // Method to find minimum in an array 
  public double min(double[] arr){ 
    double min = arr[0]; 
    for (int i = 0; i < arr.length; i++) 
      if (arr[i] < min){
        min = arr[i]; 
      }
     
    return min; 
  }
  
  // Find the corrosponding country name, of smallest value of array
  public String minStr(double[] arr){
    double min = arr[0]; 
    String minName = "";
    for (int i = 0; i < arr.length; i++) 
      if (arr[i] < min){
        min = arr[i]; 
        minName = this.countries[i];
      }
     
    return minName; 
  }

  // A method to find at which x and y choords are there points.
  public boolean[][] pointAtChoord(double[] arr1, double[] arr2, double maxX, double maxY) {

    // Create a 2d boolean array that is all false
    boolean[][] result = new boolean[this.width][this.height];
    for(int a = 0; a < this.width; a++){
      for(int b = 0; b < this.height; b++){
        result[a][b] = false;
      }
    }
    
    for(int i=0;i<arr1.length;i++){
      double x = arr1[i];
      double y = arr2[i];

      // Map the numbers onto points in the output
      x = width-Math.round((x/maxX)*(this.width))-2;
      y = Math.round((y/maxY)*(this.height))-1;

      // Make all values < 0, = 0
      x = Math.max(x, 0);
      y = Math.max(y, 0);

      // Make the output point true
      result[(int)x][(int)y] = true;
    }
    return result;
  }

  // Output the scatter plot, and some other numbers, as a string.
  public String plotter() {

    // Get arrays from options
    String arr1Name = getNameFromOpt(this.opt1);
    String arr2Name = getNameFromOpt(this.opt2);
    double[] arr1 = getArrayFromOpt(this.opt1);
    double[] arr2 = getArrayFromOpt(this.opt2);

    // Calc maximum x and y vars
    double maxX = max(arr1);
    double maxY = max(arr2);

    
    // Calc points
    boolean[][] plot = pointAtChoord(arr1, arr2, maxX, maxY);
    
    String out = "";

    // Format scatter plot
    out += "\n";
    out += arr1Name + " As (x) vs. " + arr2Name + " As (y)";
    out += "\n";
    out += "0" + (" ".repeat(this.height)) + maxX + "\n";
    out += "/" + ("-".repeat(this.height)) + "\\ " + maxY + "\n";
    for(int a=0;a<this.width;a++){
      out += "|";
      for(int b=0;b<height;b++){
        if(plot[a][b]){
          out += "#";
        }else{
          out += " ";
        }
      }
      out += "|\n";
    }
    out += "\\" + ("-".repeat(this.height)) + "/ 0";
    out += "\n\n";
    
    return out;
  }

  // Get stats about the arrays passed
  public String metaStats() {
    // Get arrays from options
    String arr1Name = getNameFromOpt(this.opt1);
    String arr2Name = getNameFromOpt(this.opt2);
    double[] arr1 = getArrayFromOpt(this.opt1);
    double[] arr2 = getArrayFromOpt(this.opt2);

    String out = "";
    
    // Calc the values and countries of min and max stats
    out += arr1Name + " Maximum: \n";
    out += max(arr1) + " - " + maxStr(arr1) + "\n";
    out += arr1Name + " Minimum: \n";
    out += min(arr1) + " - " + minStr(arr1) + "\n";
      
    out += arr2Name + " Maximum: \n";
    out += max(arr2) + " - " + maxStr(arr2) + "\n";
    out += arr2Name + " Minimum: \n";
    out += min(arr2) + " - " + minStr(arr2) + "\n";

    out += "\n\n";
    
    return out;
  }
  
  public String toString() {
    return plotter() + metaStats();
  }
  
}