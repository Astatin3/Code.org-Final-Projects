public class regularDonut extends donut {
  private int width;
  private int height;
  private double zoom;
  private double thickness;
  
  public regularDonut() {
    this(22,80,1,40);
  }

  public regularDonut(int height, int width, double zoom, double thickness) {
    super(height, width);
    this.height = height;
    this.width = width;
    this.zoom = zoom;
    this.thickness = thickness;
  }

  public void setZoom(double zoom) {
    this.zoom = zoom;
  }

  public double getZoom() {
    return this.zoom;
  }

  public void setThickness(double thickness) {
    this.thickness = thickness;
  }

  public double getThickness() {
    return this.thickness;
  }
  
  public String toString() {
    return this.render(zoom, thickness);
  }
}
