import java.util.Arrays;

public class donut {
    private int height;
    private int width;

    private double A = 0;
    private double B = 0;
    private char[] buffer;
    private double[] z;
  
    final static String newLine = "\n";
    final static double phi_step = 0.07;
    final static double theta_step = 0.02;
    final static char[] shade_chars = {'.',',','-','~',':',';','=','!','*','#','$','@'};

    final static double TWO_PI = 6.28;

    public donut(int height, int width) {
        this.buffer = new char[height * width];
        this.z = new double[height * width];
        this.height = height;
        this.width = width;
    }


    public String render(double zoom, double thickness){
        double outer_size = this.width/thickness;
        int buffer_size = this.height * this.width;
        Arrays.fill(this.buffer,0,buffer_size,' ');
        Arrays.fill(z,0,buffer_size,0);
        for(double phi = 0; phi < TWO_PI; phi += phi_step){
            for(double theta = 0; theta < TWO_PI; theta += theta_step){
                double sin_theta = Math.sin(theta);
                double cos_phi = Math.cos(phi);
                double sin_A = Math.sin(A);
                double sin_phi = Math.sin(phi);
                double cos_A = Math.cos(A);
                double h = cos_phi + outer_size;
                double D = (1 / (sin_theta*h*sin_A*sin_phi*cos_A + 5)) * zoom;
                double cos_theta = Math.cos(theta);
                double cos_B = Math.cos(B);
                double sin_B = Math.sin(B);
                double t = sin_theta * h * cos_A - sin_phi * sin_A;
                int x = (int) ((this.width / 2) + 30 * D * (cos_theta * h * cos_B - t * sin_B));
                int y = (int) ((this.height / 2 + 1) + 15 * D * (cos_theta * h * sin_B + t * cos_B));
                int o = x + this.width * y;
                int N = (int) (8 * ((sin_phi * sin_A - sin_theta * cos_phi * cos_A) * cos_B - sin_theta * cos_phi * sin_A - sin_phi * cos_A - cos_theta * cos_phi * sin_B));

                if(this.height > y && y > 0 && x > 0 && this.width > x && D > z[o]){ 
                    z[o] = D;
                    int num = Math.max(N, 0);
                    this.buffer[o] = shade_chars[num];
                }
            }
        }
        A += 0.04;
        B += 0.02;
        String str = "";
        for(int k=0; buffer_size >= k; k++){
            if(k % this.width > 0){
                str += (this.buffer[k]);
            }else{
                str += (newLine);
            }
        }
        return str;
    }
}