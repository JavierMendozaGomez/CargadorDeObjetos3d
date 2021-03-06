//////////////////////////////////////////// 
// Project skeleton for Computer 2D Graphics
// MVC-based design
// Author: Chus Mart�n
// 2014
//////////////////////////////////////////// 

package model;

//JOGL imports
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Scene {

	// Scene Visible Area (SVA)
	private double xLeft, xRight, yTop, yBottom; // SVA position
	
	// Scene variables
	private double xTriangle, yTriangle;
	private double triangleWidth, triangleHeight;
	private boolean redColor;
	
	/////////////////////////////////
	public Scene(double xLeft1, double xRight1, double yTop1, double yBottom1){
		// SVA
		xLeft= xLeft1;
		xRight= xRight1;
		yTop= yTop1;
		yBottom= yBottom1;
		
		// Triangle size
		triangleWidth= 0.4*(xRight-xLeft);
        triangleHeight= 0.8*(yTop-yBottom);
        // Triangle initial location
        xTriangle= xLeft + 0.3*(xRight-xLeft);
        yTriangle= yBottom + 0.1*(yTop-yBottom);	
        
        redColor= true;
	     
	}
	
	/////////////////////////////////
	public double getXLeft()   { return xLeft;}
	public double getXRight()  { return xRight;}
	public double getYTop()    { return yTop;}
	public double getYBottom() { return yBottom;}
	
	public double getWidth()   { return xRight-xLeft;}
	public double getHeight()  { return yTop-yBottom;}
	
    /////////////////////////////////
	public void resize(double viewPortRatio){		
		double sceneVisibleAreaRatio=(xRight-xLeft)/(yTop-yBottom);
		
		if (sceneVisibleAreaRatio>=viewPortRatio){
			     // Increase SVA height
			     double newHight= (xRight-xLeft)/viewPortRatio;
			     double yCenter= (yBottom+yTop)/2.0;
			     yTop= yCenter + newHight/2.0;
			     yBottom= yCenter - newHight/2.0;
		}
		else{
				// Increase SVA width
				double newWidth= viewPortRatio*(yTop-yBottom);
				double xCenter= (xLeft+xRight)/2.0;
				xRight= xCenter + newWidth/2.0;
				xLeft= xCenter - newWidth/2.0;
		}
	}

	/////////////////////////////////
	public void draw(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
        
        if(redColor) gl.glColor3f(1.0f,0.0f,0.0f);
        else gl.glColor3f(0.0f,1.0f,0.0f); 

        gl.glBegin(GL.GL_TRIANGLES);
	        gl.glVertex2d( xTriangle, yTriangle );
	        gl.glVertex2d( xTriangle + triangleWidth, yTriangle );
	        gl.glVertex2d( xTriangle + triangleWidth/2.0, yTriangle + triangleHeight );
        gl.glEnd();
        
        gl.glFlush();
	}
	
	/////////////////////////////////
	public void moveTriangle(double xShift){
		xTriangle += xShift;
	}
	
	/////////////////////////////////
	public void changeColor(){
		redColor = !redColor;
	}

}