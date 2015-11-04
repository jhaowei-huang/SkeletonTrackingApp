package app;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import edu.ufl.digitalworlds.j4k.Skeleton;

public class SkeletonTrackingResource extends CoapResource {
	public SkeletonTrackingResource() {
		super("skeletontracking");
        // set display name
        getAttributes().setTitle("skeletontrackingapp");
	}
	
	@Override
    public void handleGET(CoapExchange exchange) {
        // respond to the request
		
		exchange.respond(Main.detectSkeletonsTask.getResult());
    }
}
