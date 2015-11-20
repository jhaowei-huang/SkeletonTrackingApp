package app;

import org.eclipse.californium.core.CoapResource;

public class Root extends CoapResource {
	private CoapResource child;
	
	public Root(String name) {
		super(name);
		if(name.equals("sensor"))
			child = add(new SkeletonTrackingResource());
	}
	
	public CoapResource getChild() {
		return child;
	}
}
