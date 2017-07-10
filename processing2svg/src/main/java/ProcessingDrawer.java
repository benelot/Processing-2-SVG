import java.awt.Dimension;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import processing.awt.PGraphicsJava2D;
import processing.core.PApplet;


public abstract class ProcessingDrawer {
	
	PGraphicsJava2D canvas; 												// Processing graphics canvas 
	
	SVGGraphics2D svgGenerator; 											// Apache Batik svg generator
	
	int width, height;														// width and height of the canvas
	
	String svg;
	
	public ProcessingDrawer(){
		
	}
	
	public ProcessingDrawer(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void run(){
		PApplet.main(new String[] {this.getClass().getCanonicalName()});
	}

	public void settings() {
	}
	
	public void setup() {
		
		// Get a DOMImplementation.
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

																			// Create an instance of org.w3c.dom.Document.
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);

		svgGenerator = new SVGGraphics2D(document); 						// Create an instance of the SVG Generator.

		svgGenerator.setSVGCanvasSize(new Dimension(width, height));
		canvas = new PGraphicsJava2D();
		canvas.setSize(width, height);
	}
	
	
	/**
	 * Implement this to draw your picture.
	 */
	public abstract void paintCanvas();
	
	public void draw() {
		getCanvas().beginDraw();
		getCanvas().g2 = svgGenerator;
		
		paintCanvas();
		
		getCanvas().endDraw();
		svg = exportSVG();
	}
	
	private String exportSVG(){
		return exportSVG(true);
	}
	
	private String exportSVG(boolean useCSS){
		String svgContent = "";
	    try {
	    	Writer out = new StringWriter();
			svgGenerator.stream(out, useCSS);
			svgContent = out.toString();
		} catch (SVGGraphics2DIOException e) {
			e.printStackTrace();
		}
	    
	    return svgContent;
	}

	public PGraphicsJava2D getCanvas() {
		return canvas;
	}

	public String getSVG() {
		return svg;
	}

}
