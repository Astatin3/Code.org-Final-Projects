import org.code.neighborhood.*;

public class NeighborhoodRunner {
  public static void main(String[] args) {

  String I = "White";
  String O = "Black";
    
  String[][] initState = new String[][] {
  new String[] {O,O,O,O,O,O,O,O},
  new String[] {O,I,I,O,O,O,O,O},
  new String[] {O,I,O,I,O,O,O,O},
  new String[] {O,I,O,O,O,O,O,O},
  new String[] {O,O,O,O,O,O,O,O},
  new String[] {O,O,O,O,O,I,I,O},
  new String[] {O,O,O,O,I,O,I,O},
  new String[] {O,O,O,O,O,O,I,O}};
    
    ConwayRunner cw = new ConwayRunner();
    Renderer r = new Renderer();
    cw.run(r, initState);
    cw.run(r, initState);
    
  }
}