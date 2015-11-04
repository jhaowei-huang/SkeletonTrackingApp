package app;

import java.util.List;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.awt.EventQueue; 

import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;

public class Kinect extends J4KSDK {
	private static Skeleton skeletons[];
	private static int current = 0;
	private static int skeletonsEventCounts = 0;
	
	@Override
	public void onColorFrameEvent(byte[] arg0) {}

	@Override
	public void onDepthFrameEvent(short[] arg0, byte[] arg1, float[] arg2, float[] arg3) {}

	@Override
	public void onSkeletonFrameEvent(boolean[] skeletonTracked, float[] positions,
            float[] orientations, byte[] jointStatus) {		
		skeletonsEventCounts++;
		this.current = 0;
		for(int i = 0; i < getSkeletonCountLimit(); i++) {
			skeletons[i] = Skeleton.getSkeleton(i, skeletonTracked, positions, 
					orientations, jointStatus, this);
		}

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
		
		// System.out.println("current user(s): " + getCurrent());
	}
	public Skeleton[] getSkeletons() {
		return this.skeletons;
	}
	// init skeletons
	public void initSkeletons() {
		skeletons = new Skeleton[6];
		this.current = 0;
	}
	// do skeleton tracking
	public void findSkeleton() {
		this.current += 1;	
	}
	// get current user number
	public static int getCurrent() {
		return current;
	}

	public static int getSkeletonsEventCounts() {
		return skeletonsEventCounts;
	}

	public static void setSkeletonsEventCounts(int skeletonsEventCounts) {
		Kinect.skeletonsEventCounts = skeletonsEventCounts;
	}
}
