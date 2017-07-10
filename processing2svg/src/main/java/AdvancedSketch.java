
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import processing.core.*;


public class AdvancedSketch extends ProcessingDrawer{
	
	Random r = new Random();
	
	public AdvancedSketch(){
		super();
	}

	public AdvancedSketch(int width, int height) {
		super(width, height);
	}

	@Override
	public void paintCanvas() {

		int dim = getCanvas().width/2;
		getCanvas().background(0);
		getCanvas().colorMode(PApplet.HSB, 360, 100, 100);
		getCanvas().noStroke();
		getCanvas().ellipseMode(PApplet.RADIUS);

		drawGradient(dim, dim, getCanvas().height/2);	
	}
	
	void drawGradient(int dim, float x, float y) {
		  int radius = dim/2;
		  float h = r.nextInt(360);
		  for (int r = radius; r > 0; --r) {
		    getCanvas().fill(h, 90, 90);
		    getCanvas().ellipse(x, y, r, r);
		    h = (h + 1) % 360;
		  }
		}
	
	public static void main(String[] args){
		AdvancedSketch as = new AdvancedSketch(400, 400);
		as.setup();
		as.draw();
		System.out.print(as.getSVG());
		
		try{
	        PrintWriter writer = new PrintWriter("AdvancedSketch.svg", "UTF-8");
	        writer.print(as.getSVG());
	        writer.close();
	    } catch (IOException e) {
	       // do something
	    }
	}
}