
import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import processing.awt.PGraphicsJava2D;
import processing.core.*;

public class AdvancedSketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main(new String[] {"AdvancedSketch"});
	}

	@Override
	public void settings() {
	}

	PGraphics pg;
	
	PGraphicsJava2D pgj;
	
	SVGGraphics2D svgGenerator;

	@Override
	public void setup() {

		// Get a DOMImplementation.
		DOMImplementation domImpl =
				GenericDOMImplementation.getDOMImplementation();

		// Create an instance of org.w3c.dom.Document.
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);

		// Create an instance of the SVG Generator.
		svgGenerator = new SVGGraphics2D(document);
		
		int width = 400;
		int height = 400;

		svgGenerator.setSVGCanvasSize(new Dimension(width, height));
		pg = createGraphics(width, height, JAVA2D);

		pgj = (PGraphicsJava2D) pg;
	}


	@Override
	public void draw() {

		pgj.beginDraw();
		pgj.g2 = svgGenerator;
		
		int dim = pgj.width/2;
		pgj.background(0);
		pgj.colorMode(HSB, 360, 100, 100);
		pgj.noStroke();
		pgj.ellipseMode(RADIUS);

		drawGradient(dim, dim, pgj.height/2);
		
		
		pgj.endDraw();

		// Finally, stream out SVG to a string
	    boolean useCSS = true; // we want to use CSS style attributes
	    
	    String svg = "";
	    try {
	    	Writer out = new StringWriter();
			svgGenerator.stream(out, useCSS);
			svg = out.toString();
		} catch (SVGGraphics2DIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.print(svg);
	    
	    
	    
	    try{
	        PrintWriter writer = new PrintWriter("AdvancedSketch.svg", "UTF-8");
	        writer.print(svg);
	        writer.close();
	    } catch (IOException e) {
	       // do something
	    }
	    System.exit(0);
	}
	
	void drawGradient(int dim, float x, float y) {
		  int radius = dim/2;
		  float h = random(0, 360);
		  for (int r = radius; r > 0; --r) {
		    pgj.fill(h, 90, 90);
		    pgj.ellipse(x, y, r, r);
		    h = (h + 1) % 360;
		  }
		}
}