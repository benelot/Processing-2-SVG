
public class SimpleSketch extends ProcessingDrawer{
	
	public SimpleSketch(){
		super();
	}

	public SimpleSketch(int width, int height) {
		super(width, height);
	}

	@Override
	public void paintCanvas() {

		getCanvas().background(51);
		getCanvas().noFill();
		getCanvas().stroke(255);
		getCanvas().ellipse(30, 30, 60, 60);		
	}
	
	public static void main(String[] args){
		SimpleSketch ss = new SimpleSketch(400, 400);
		ss.setup();
		ss.draw();
		System.out.print(ss.getSVG());
	}
}