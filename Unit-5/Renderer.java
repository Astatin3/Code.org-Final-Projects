import org.code.theater.*;
import org.code.media.*;


// Utility function to make messing with code.org's libraries easier
public class Renderer extends Scene {
  private Theater Theater;
  private final Image image;
  private final double delay = 0.5;

  public final int width;
  public final int height;
  
  public Renderer() {
    Theater = new Theater();
    width = getWidth();
    height = getHeight();
    image = new Image(width, height);
  }

  public void refreshImage(){
    this.drawImage(image, 0, 0, width);
    pause(delay);
  }

  public void setPixel(int x, int y, Color color){
    image.setPixel(x, y, color);
  }

  public void render() {
    System.out.println("Sending video to client...");
    Theater.playScenes(this);
  }
}