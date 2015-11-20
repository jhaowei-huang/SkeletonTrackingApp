package app;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import edu.ufl.digitalworlds.j4k.Skeleton;

public class SkeletonTrackingResource extends CoapResource {
	public SkeletonTrackingResource() {
		super("peopleCount");
		this.setObservable(true);
	}
	
	public void peopleCountChanged() {
		System.out.println("observe");
		this.changed();
	}
	
	@Override
    public void handleGET(CoapExchange exchange) {
        // respond to the request
		
		exchange.respond("" + Kinect.getCurrent());
    }
}
