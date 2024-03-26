import org.code.neighborhood.*;

public class ConwayRunner {

  public int mod(int n) {
    int o = n % 8;
    if(o < 0){
      return 8+o;
    }
    return o;
  }

  public int isAlive(String color) {
    boolean isAlive = (color == "White" || color == "1");
    if(isAlive){
      return 1;
    }else{
      return 0;
    }
  }
  
  public void run(Renderer r, String[][] state) {
     for(String[] line : state){
       System.out.println(String.join("",line));
     }
     
    r.render(state);
    this.iterate(r, state);
  }
  
  public void iterate(Renderer r, String[][] initState) {
      int index1 = 0;
      String[][] newState = new String[8][8];
      for(String[] line : initState) {
        int index2 = 0;
        for(String cell : line) {

          String cell1 = initState[this.mod(index1-1)][this.mod(index2-1)];
          String cell2 = initState[this.mod(index1-1)][this.mod(index2)];
          String cell3 = initState[this.mod(index1-1)][this.mod(index2+1)];
          String cell4 = initState[this.mod(index1)][this.mod(index2-1)];
          String cell5 = initState[this.mod(index1)][this.mod(index2+1)];
          String cell6 = initState[this.mod(index1+1)][this.mod(index2-1)];
          String cell7 = initState[this.mod(index1+1)][this.mod(index2)];
          String cell8 = initState[this.mod(index1+1)][this.mod(index2+1)];

          boolean isAlive = (cell == "White");
          int count = this.isAlive(cell1)+
                      this.isAlive(cell2)+
                      this.isAlive(cell3)+
                      this.isAlive(cell4)+
                      this.isAlive(cell5)+
                      this.isAlive(cell6)+
                      this.isAlive(cell7)+
                      this.isAlive(cell8);
        
          
          String newColor = new String();
          if(isAlive && count < 2){
            newColor = "Black";
          }else if(isAlive && count == 2 || count == 3){
            newColor = "White";
          }else if(isAlive && count > 3){
            newColor = "Black";
          }else if(!isAlive && count == 3){
            newColor = "White";
          }else if(!isAlive){
            newColor = "Black";
          }

          // System.out.println(count);
          
          // newLine.set(index2, newColor);
          newState[index1][index2] = (newColor);
          
          
          index2++;
        }
        index1++;
        // newState.set(index1, newLine);
     }
    this.run(r, newState);
    
  }
}