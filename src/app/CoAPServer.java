package app;

import java.net.SocketException;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.config.NetworkConfig;

public class CoAPServer extends CoapServer {
	public CoAPServer() throws SocketException {
		// provide an instance of a SkeletonTracking resource
        add(new SkeletonTrackingResource());
	}
}
