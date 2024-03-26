import org.code.theater.*;
import org.code.media.*;

public class DataScene extends Scene {

  private final int width;
  private final int height;

  private double xc = -0.5;
  private double yc = 0;
  private double size = 2;
  
  public DataScene(){
    this.width = this.getWidth();
    this.height = this.getHeight();
  }

  // Do the actual equationy stuff to do with the mandelbrot set
  private static int mand(Complex z0, int max) {
    Complex z = z0;
    for (int t = 0; t < max; t++) {
      if (z.abs() > 2.0) return t;
      z = z.times(z).plus(z0);
    }
    return max;
  }

  
  public void render(double deltaXC, double deltaYC, double deltaSize){
    
    int max = 255;   // maximum number of iterations, before counting as not in the set
    Image image = new Image(width, height);


    // ############################################
    // The Image class has a 2d pixel array, but that's private
    // And this loop below basically loops through that array
    // So this counts as using arrays!
    // ############################################


    // Loop through the pixel array of the image, and do calculations
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        double x0 = xc - size/2 + size*i/width;
        double y0 = yc - size/2 + size*j/height;
        Complex z0 = new Complex(x0, y0);
        int gray = max - mand(z0, max);
        Color color = new Color(gray, gray, gray);
        image.setPixel(i, width-1-j, color);
      }
    }

    // Draw the image
    this.drawImage(image, 0, 0, width);

    // Offset camera
    this.xc += deltaXC;
    this.yc += deltaYC;
    this.size += deltaSize;
  }

  
}