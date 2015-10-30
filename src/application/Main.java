package application;
	

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import edu.ufl.digitalworlds.j4k.J4KSDK;
 
public class Main extends Application {
	private final Box box = new Box(200, 200, 200);
	private final Text text = new Text(200, 200, "#current");
	private Kinect kinect = null;
	
	public Main() {
		kinect = new Kinect();
		kinect.start(J4KSDK.SKELETON);
		kinect.initSkeletons();
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
}