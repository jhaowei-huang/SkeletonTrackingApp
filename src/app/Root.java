package app;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class Root extends CoapResource {
	public Root(String name) {
		super(name);
		
		if(name.equals("sensors")) {
			add(new SkeletonTrackingResource());
			// postDetection not implement
			add(new CoapResource("postDetection") {
				@Override
			    public void handleGET(CoapExchange exchange) {
					exchange.respond("resourceID: 2");
				}
			});	
			// voiceCommand not implement
			add(new CoapResource("voiceCommand") {
				@Override
			    public void handleGET(CoapExchange exchange) {
					exchange.respond("resourceID: 3");
				}
			});	
		}
		else if(name.equals("actuators")) {
			add(new CoapResource("getDeviceName") {
				@Override
			    public void handleGET(CoapExchange exchange) {
			        // respond to the request
					// Kinect/VedioCam typeID is 106
					exchange.respond("106");
			    }
			});
		}
	}
}
