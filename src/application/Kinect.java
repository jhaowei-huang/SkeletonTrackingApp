package application;

import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;

public class Kinect extends J4KSDK {
	private Skeleton skeletons[];
	private int current = 0;
	
	@Override
	public void onColorFrameEvent(byte[] arg0) {}

	@Override
	public void onDepthFrameEvent(short[] arg0, byte[] arg1, float[] arg2, float[] arg3) {}

	@Override
	public void onSkeletonFrameEvent(boolean[] skeletonTracked, float[] positions,
            float[] orientations, byte[] jointStatus) {		

		for(int i = 0; i <getSkeletonCountLimit(); i++) {
			skeletons[i]=Skeleton.getSkeleton(i, skeletonTracked, positions, 
					orientations, jointStatus, this);
		}
		this.current = 0;
		
		for(int i = 0; i < skeletons.length; i++) {
			if(skeletons[i] != null) 
	    	{
				/*
	    		if(skeletons[i].getTimesDrawn() <= 10 && Wskeletons[i].isTracked())
	    		{
	    			 skeletons[i].draw(gl);
	    			skeletons[i].increaseTimesDrawn();
	    			findSkeleton();
	    		}
	    		*/
	    		if(skeletons[i].isTracked()) {
	    			findSkeleton();
	    		}
	    	}
	    }
		System.out.println("current user(s): " + getCurrent());
	}
	// init skeletons
	public void initSkeletons() {
		skeletons = new Skeleton[6];
		setCurrent(0);
	}
	// do skeleton tracking
	public void findSkeleton() {
		setCurrent(++this.current);	
	}
	// set current user number
	public void setCurrent(int current) {
		this.current = current;
	}
	// get current user number
	public int getCurrent() {
		return current;
	}
}
