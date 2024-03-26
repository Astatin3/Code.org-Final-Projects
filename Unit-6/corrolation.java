// Object structure for correlation between two words
public class corrolation {
  
  public int correlativeOccurrences = 0;
  
  public word startWord;
  public word endWord;

  public double getCorrelation() {
    return (double)(correlativeOccurrences) / (double)(startWord.count);
  }
}