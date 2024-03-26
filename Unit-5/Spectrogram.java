import org.code.theater.*;
import org.code.media.*;
import java.util.Arrays;

public class Spectrogram {
    public static void run(String filepath) {
        //get raw double array containing .WAV data
        //code.org has a function to get data from a .wav file, this makes things a lot easier
        double[] rawData = SoundLoader.read(filepath);
      
        int length = rawData.length;

        Renderer r = new Renderer();

        //initialize parameters for FFT
        int WS = 512; //WS = window size
        int OF = 8;    //OF = overlap factor
        int windowStep = WS/OF;

        //initialize plotData array
        int nX = (length-WS)/windowStep;
        int nY = WS/2 + 1;
        double[][] plotData = new double[nX][nY]; 

        //apply FFT and find MAX and MIN amplitudes
        double maxAmp = Double.MIN_VALUE;
        double minAmp = Double.MAX_VALUE;

        double amp_square;

        double[] inputImag = new double[length];

        System.out.println("Calculating Spectrogram...");

        // Do the FFT Stuff
        for (int i = 0; i < nX; i++){
            Arrays.fill(inputImag, 0.0);
            double[] WS_array = FFT.fft(Arrays.copyOfRange(rawData, i*windowStep, i*windowStep+WS), inputImag, true);
            for (int j = 0; j < nY; j++){

              // IDK, Stolen code. I think this converts the data from the fft
                amp_square = (WS_array[2*j]*WS_array[2*j]) + (WS_array[2*j+1]*WS_array[2*j+1]);
                if (amp_square == 0.0){
                    plotData[i][j] = amp_square;
                }
                else{
                    plotData[i][nY-j-1] = 10 * Math.log10(amp_square);
                }

                //find MAX and MIN amplitude
                if (plotData[i][j] > maxAmp)
                    maxAmp = plotData[i][j];
                else if (plotData[i][j] < minAmp)
                    minAmp = plotData[i][j];

            }
        }

        //Normalization, to show the details in the spectrograph
        final double diff = maxAmp - minAmp;
        for (int i = 0; i < nX; i++){
            for (int j = 0; j < nY; j++){
                plotData[i][j] = (plotData[i][j]-minAmp)/diff;
            }
        }
    

        //Video settings
        //The docs get the sample rate wrong, by a factor of 10, lol.
        final double SR = 44100;

        // This is around the max framerate that code.org allows
        final double maxFramerate = 2.05;
        final int maxFrameCount = 120;

        // Most ways to get the length of the audio require getting the bitrate of the audio. This seems to work.
        final double audioLength = length/SR;
        final int frameCount = Math.min((int)Math.round(audioLength*maxFramerate), maxFrameCount);
        final double delay = Math.max(((audioLength/frameCount)-(double)(1/maxFramerate)), 0);


        final int initialXOffset = (int)(r.width/2)*-1;

        System.out.println("Rendering Video...");
        System.out.println("Audio length: " + Math.round(audioLength) + " seconds");      
        // System.out.println(frameCount);
        // System.out.println(delay);
      
        r.playSound(filepath);      
        
        // Loop through all frames
        for(int f = 0; f<=frameCount; f++){
          
          int xOffset = initialXOffset+(int)(f*((nX+r.width)/frameCount));

          // Loop through pixels in image
          for(int y = 0; y<r.height; y++){
            int specY = (int)((double)(((double)y/(double)r.height)*(double)nY));
            for(int x = 0; x<r.width; x++){

              
              if(x+xOffset<0 || x+xOffset+1 > nX){
                // If pixel is outside image, set to black.
                r.setPixel(x, y, Color.BLACK);
              }else{
                // Greyscale image
                final int L = (int)(plotData[x+xOffset][specY]*255);
                r.setPixel(x, y, new Color(L, L, L));
              }
            }
          }
          r.refreshImage();
          // r.pause(delay);
        }
        r.render();
        
    }
}