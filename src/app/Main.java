package app;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import edu.ufl.digitalworlds.j4k.J4KSDK;
 
public class Main extends Application {
	private final Box box = new Box(200, 200, 200);
	private final Text text = new Text(200, 200, "#current");
	protected static Kinect kinect = null;
	private Timer timer = new Timer();
	protected static DetectSkeletonsTask detectSkeletonsTask; // = new DetectSkeletonsTask();
	
	public Main() {
		kinect = new Kinect();
		// kinect.start(J4KSDK.SKELETON | J4KSDK.COLOR | J4KSDK.DEPTH | J4KSDK.INFRARED);
		kinect.start(J4KSDK.SKELETON);
		kinect.initSkeletons();
		detectSkeletonsTask = new DetectSkeletonsTask();
		timer.schedule(detectSkeletonsTask, 100, 3000);
		
		try {
            // create server
            CoAPServer server = new CoAPServer();
            server.start();
            System.out.println("coap server start!");
        } catch (SocketException e) {
            System.err.println("Failed to initialize server: " + e.getMessage());
        }
	}
	
	@Override
	public void start(Stage stage) {
	    Group root = new Group();
	    stage.setScene(new Scene(root, 400, 400));
	    stage.show();
	    
	    box.setTranslateX(200);
	    box.setTranslateY(200);
	    box.setTranslateZ(200);
	    box.setRotationAxis(Rotate.Y_AXIS);
	    
	    root.getChildren().addAll(box, text);
	}
 
	public static void main(String[] args) {
		launch(args);
	}
	
	class DetectSkeletonsTask extends TimerTask {
		private int previousEventCounts = 0;
		private String result = "no person";
		@Override
		public void run() {
			int eventCounts = Kinect.getSkeletonsEventCounts();
			String msg = "event counts = " + eventCounts;
			
			if(previousEventCounts == eventCounts)
				result = msg + " -> no person";
			else
				result = msg + " -> current = " + Kinect.getCurrent();
			
			System.out.println(result);
			previousEventCounts = eventCounts;
		}
		
		public String getResult() {
			return result;
		}
	}
}