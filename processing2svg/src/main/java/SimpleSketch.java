
import java.awt.Dimension;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import processing.awt.PGraphicsJava2D;
import processing.core.*;

public class SimpleSketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main(new String[] {"SimpleSketch"});
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
		
		pgj.background(51);
		pgj.noFill();
		pgj.stroke(255);
		pgj.ellipse(30, 30, 60, 60);
		
		
		pgj.endDraw();

		// Finally, stream out SVG to the standard output using
		// UTF-8 encoding.
	    boolean useCSS = false; // we want to use CSS style attributes
	    
	    try {
	    	Writer out = new OutputStreamWriter(System.out, "UTF-8");
			svgGenerator.stream(out, useCSS);
		} catch (SVGGraphics2DIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.exit(0);
	}
}