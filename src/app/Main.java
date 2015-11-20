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
	protected Kinect kinect = null;
	private Timer timer = new Timer();
	protected static DetectSkeletonsTask detectSkeletonsTask; // = new DetectSkeletonsTask();
	private Server server;
	
	public Main() {
		kinect = new Kinect();
		// kinect.start(J4KSDK.SKELETON | J4KSDK.COLOR | J4KSDK.DEPTH | J4KSDK.INFRARED);
		kinect.start(J4KSDK.SKELETON);
		kinect.initSkeletons();
		detectSkeletonsTask = new DetectSkeletonsTask();
		timer.schedule(detectSkeletonsTask, 100, 1000);
		
		try {
            // create server
            server = new Server();
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
		private int peopleCount = 0;
		@Override
		public void run() {
			if(kinect == null)
				return;
			
			int eventCounts = kinect.getSkeletonsEventCounts();
			String msg = "event counts = " + eventCounts;
			// no person
			if(previousEventCounts == eventCounts) {
				result = msg + " -> current = " + Kinect.getCurrent();
				if(kinect.getCurrent() != 0) {
					peopleCount = 0;
					kinect.setCurrent(0);
					observePeopleCount();
				}
			}
			// current people count > 0
			else {
				result = msg + " -> current = " + Kinect.getCurrent();
				// people count changed
				if(peopleCount != kinect.getCurrent()) {
					observePeopleCount();
					peopleCount = kinect.getCurrent();
				}
			}

			System.out.println(result);
			previousEventCounts = eventCounts;
		}
		
		public boolean observePeopleCount() {
			if(server != null) {
				SkeletonTrackingResource skr = (SkeletonTrackingResource) server.getRoot().getChild("sensor").getChild("peopleCount");
				if(skr != null) {
					System.out.println("observe " + skr.getName());
					skr.peopleCountChanged();
				}
				else return false;
			}
			else {
				System.out.println("no server");
				return false;
			}
			
			return true;
		}
	}
}