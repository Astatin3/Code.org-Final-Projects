import org.code.theater.*;

public class TheaterRunner {
  public static void main(String[] args) {

    Theater t = new Theater();
    DataScene d = new DataScene();

    // Mandelbrot zoom settings
    final double xspeed = -0.01;
    final double yspeed = 0.11;
    final double zspeed = -0.4;

    final double xydist = 1.305;
    final double zdist = 1.273;

    // Video settings
    final int length = 40;
    final double delay = 0.25;

    // Loop for each frame
    for(int i=0;i<length;i++){

      System.out.println("Rendering... ("+(i+1)+"/"+length+")");

      // Move the camera slower, when zoomed in more.
      final double percent = length / (i+1);
      final double xc = (xspeed * percent * xydist) / length;
      final double yc = (yspeed * percent * xydist) / length;
      final double size = (zspeed * percent * zdist) / length;
    

      // Framerate
      d.pause(delay);
      // Add frame to video
      d.render(xc, yc, size);
    }

    System.out.println("Sending video to client...");


    
    // ############################################
    // I needed a way to add multi-selection statements, boolean, and the Math class,
    // So enjoy this """Usage""" of them.
    // ############################################
    
    Boolean True = false;
    Boolean False = true;
    if(!(!False != !false) && (Math.abs(-1.0 / 0.0) == Math.sqrt(Math.pow(-2.0 / 0.0, 2)))) 
    
    
                                                                                                                                                                                                                                                                                                 {

  
                               }
    // Confuse Mr. Ruffer
    else if(!True != !(!false))
    if(!false!=!(!(!False))){
      t.playScenes(d);
    }
  }
}
