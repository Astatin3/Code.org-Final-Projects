import java.util.ArrayList;

// Randomly generated bee movie lines.

public class NLPRunner {

  // Loop through and see if an existing word already exists in array
  private static int getWordIndex(ArrayList<word> wordArr, String wordStr){
    for(int i = 0; i < wordArr.size(); i++){
      word c = wordArr.get(i);
      if(c.str.equals(wordStr)){
        return i;
      }
    }
    return -1;
  }

  // Loop through array, and see if an existing corrolation object between two words already exists
  private static int getCorrolationIndex(ArrayList<corrolation> corrolations, 
                                          String startWord, String endWord){
    for(int i = 0; i < corrolations.size(); i++){
      if(corrolations.get(i).startWord.equals(startWord) && 
         corrolations.get(i).endWord.equals(endWord)){
        return i;
      }
    }
    return -1;
  }

  // Recursively choose a sentance
  private static void recursiveWordChooser(ArrayList<corrolation> corrolations, String word){
    double total = 0;
    final double randVal = (Math.random());

    // Loop through corrolations
    for(int i = 0; i < corrolations.size(); i++){
      corrolation c = corrolations.get(i);
      if(c.startWord.str.equals(word)){
        final double correlation = c.getCorrelation();

        // Weighted random
        if(total <= randVal && randVal < (total + correlation)){
          
          final String nextWord = c.endWord.str;
          
          if(nextWord.equals("<end>")){
            System.out.print("\n");
          }else{
            System.out.print(" " + nextWord);
          }
          recursiveWordChooser(corrolations, nextWord);
        }
        
        total += correlation;
      }
    }
    // return words;
  }
  
  public static void main(String[] args) {
    
    System.out.println(" --- Parsing file ---");
    
    // Parse bee movie script and convert it for easier processing
    String script = String.join(" <end> ", FileReader.toStringArray("BeeMovie.txt"));
    script = script.replace(".", "");
    script = script.replace(",", "");
    script = script.replace("!", "");
    script = script.replace("?", "");
    script = script.replace(" <end>  <end>  <end>  <end> ", " <end> ");
    script = script.replace(" <end>  <end>  <end> ", " <end> ");
    script = script.replace(" <end>  <end> ", " <end> ");
    script = script.replace("\"", "");
    script = script.replace(":", "");
    script = script.replace("- ", "");
    script = script.replace("-", "");
    script = script.toLowerCase();

    String[] words = script.split(" ");
    
    ArrayList<word> wordArr = new ArrayList<word>();

    // Add words to array
    for(int i = 0; i < words.length; i++){
      final String startWord = words[i];
      final int wordIndex = getWordIndex(wordArr, startWord);

      if(wordIndex != -1){
        word w = wordArr.get(wordIndex);
        w.count = w.count + 1;
      }else{
        word w = new word();
        w.str = startWord;
        w.count = 1;
        wordArr.add(w);
      }
    }
    
    ArrayList<corrolation> corrolations = new ArrayList<corrolation>();

    // Convert list of words to list of corrolations
    for(int i = 0; i < words.length-1; i++){
      final String startWord = words[i];
      final String endWord = words[i+1];
      
      final int corrolationIndex = getCorrolationIndex(corrolations, startWord, endWord);

      // Add correlation
      if(corrolationIndex != -1){
        corrolation c = corrolations.get(corrolationIndex);
        c.correlativeOccurrences = c.correlativeOccurrences + 1;
      }else{
        corrolation c = new corrolation();
        c.startWord = wordArr.get(getWordIndex(wordArr, startWord));
        c.endWord = wordArr.get(getWordIndex(wordArr, endWord));
        c.correlativeOccurrences = 1;

        corrolations.add(c);
      }
    }

    
    final String startGenWord = "crazy";
    // Generate words
    System.out.print(" " + startGenWord);
    while(true){
      recursiveWordChooser(corrolations, startGenWord);
    }
  }
}