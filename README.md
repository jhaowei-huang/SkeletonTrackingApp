## SkeletonTrackingApp
SkeletonTrackingApp include libs: 

- [**Java for Kinct (J4K)**](http://research.dwi.ufl.edu/ufdw/j4k/)
  - The J4K library is a popular open source Java library that implements a Java binding for the Microsoft's Kinect SDK. It communicates with a native Windows library, which handles the depth, color, infrared, and skeleton streams of the Kinect using the Java Native Interface (JNI).</br>
- [**JavaFX**](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html)
  - JavaFX is the next step in the evolution of Java as a rich client platform. It is designed to provide a lightweight, hardware-accelerated Java UI platform for enterprise business applications. With JavaFX, developers can preserve existing investments by reusing Java libraries in their applications. They can even access native system capabilities, or seamlessly connect to server-based middleware applications.</br>

----------------------------------------------------
### project 
- lib
  - gluegen
    - generates the Java and JNI code
    - developed for Java OpenGL
  - jogl
    - Java OpenGL lib
  - ufdw
    - include J4K and OpenGL
- src
  - **Kinect class**
    - extends J4KSDK (udfw)
    - override methods
    - onDepthFrameEvent
    - **onSkeletonFrameEvent** (important)
    - onColorFrameEvent
    - onInfraredFrameEvent
  - **Main class**
    - extends Application (JavaFX)
    - main and GUI
  - **ViewerPanel** (not implement)
    - extends OpenGLPanel (udfw)
    - draw
    - OpenGL config
    
----------------------------------------------------
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

