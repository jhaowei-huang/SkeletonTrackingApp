## SkeletonTrackingApp with CoAP Server
### includes:
- [**Java for Kinct (J4K)**](http://research.dwi.ufl.edu/ufdw/j4k/)
  - The J4K library is a popular open source Java library that implements a Java binding for the Microsoft's Kinect SDK. It communicates with a native Windows library, which handles the depth, color, infrared, and skeleton streams of the Kinect using the Java Native Interface (JNI).</br>

- [**Californium (Cf) CoAP framework**](https://github.com/eclipse/californium)
  - Californium is a Java implementation of CoAP for the IoT backend and less constrained IoT devices. Thus, the focus is on scalability and usability instead of resource-efficiency like for embedded devices. Yet Californium is also suitable for embedded JVMs.</br>

- [**JavaFX**](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html)
  - JavaFX is the next step in the evolution of Java as a rich client platform. It is designed to provide a lightweight, hardware-accelerated Java UI platform for enterprise business applications. With JavaFX, developers can preserve existing investments by reusing Java libraries in their applications. They can even access native system capabilities, or seamlessly connect to server-based middleware applications.</br>

----------------------------------------------------
### project 
- lib
  - californium-core
    - central framework with the protocol implementation to build your IOT apps
  - element-connector
    - UDP socket abstraction
  - gluegen
    - generates the Java and JNI code
    - developed for Java OpenGL
  - jogl
    - Java OpenGL lib
  - ufdw
    - include J4K and OpenGL
- src
  - **Kinect.java** extends J4KSDK (udfw)
    - onDepthFrameEvent
    - onSkeletonFrameEvent
      - refresh when Kinect find skeletons
    - onColorFrameEvent
    - onInfraredFrameEvent
  - **Main.java** extends Application (JavaFX)
    - initialize Kinect and CoAP server
    - detect skeletons per second 
  - **Root.java** extends CoapResource (californium-core)
    - add resource and resource child
  - **Server.java** extends extends CoapServer (californium-core)
    - provide an instance of resources
  - **SkeletonTrackingResource.java**
    - observe people count
  - **ViewerPanel** (not implement)
    - extends OpenGLPanel (udfw)
    - draw
    - OpenGL config
