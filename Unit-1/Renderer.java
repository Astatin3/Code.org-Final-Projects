import org.code.neighborhood.*;

/*
 * MuralPainter is a PainterPlus that paints
 * murals in The Neighborhood
 */
public class Renderer extends Painter {

  
   public void render(String[][] mural) {

     this.setPaint((8*8));
     int index1 = 0;
     for(String[] line : mural){
      // System.out.println(line[0].toString());
      int index2 = 0;
      for(String color : line){

        // System.out.println(color);
        
        if(!this.isOnPaint()){
          this.paint(color);
        }else if(this.isOnPaint() && this.getColor() != color){
          this.scrapePaint();
          this.paint(color);
        }
        
        
        if(index2 != line.length-1){
          this.move();
        }
        index2++;

       }

       if(index1 != mural.length-1){
        this.turnLeft();
        this.turnLeft();
        this.turnLeft();
        this.move();
        this.turnLeft();
        this.turnLeft();
        this.turnLeft();

        for(int i=0;i<line.length-1;i++){
          this.move();
        }
        this.turnLeft();
        this.turnLeft();
       }else{
        this.turnLeft();
        for(int i=0;i<mural.length-1;i++){
          this.move();
        }
        this.turnLeft();
        for(int i=0;i<line.length-1;i++){
          this.move();
        }
        this.turnLeft();
        this.turnLeft();
       }
       index1++;
     }
   }
  
  
  
}