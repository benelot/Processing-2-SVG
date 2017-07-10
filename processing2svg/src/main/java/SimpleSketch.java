import java.util.Random;


public class SimpleSketch extends ProcessingDrawer{
	
	Random r = new Random();
	
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

		for(int i = 0; i < 30; i++){
			getCanvas().stroke(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			getCanvas().ellipse(r.nextInt(width), r.nextInt(width), 60, 60);
		}
	}
	
	public static void main(String[] args){
		SimpleSketch ss = new SimpleSketch(400, 400);
		ss.setup();
		ss.draw();
		System.out.print(ss.getSVG());
	}
}