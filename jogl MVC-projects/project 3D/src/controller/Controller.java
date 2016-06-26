//////////////////////////////////////////// 
// Project skeleton for Computer 3D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package controller;

//JOGL imports
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;




//import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.gl2.GLUT;




//AWT imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




//Specific imports
import model.Scene;
import model.Camera;

public class Controller implements GLEventListener, KeyListener{

	private Scene scene;   // The model which is controlled 
	private Camera camera; // The viewer


	private GLAutoDrawable canvas; // The viewport that is used to render the model


	private final GLU  glu = new GLU();  //This object is kept for invoking glu  commands
	@SuppressWarnings("unused")
	private final GLUT glut= new GLUT(); //This object is kept for invoking glut commands
	private boolean rotate;
	private int anguloRotarX,anguloRotarY,anguloRotarZ;
	private boolean reset;

	/////////////////////////////////
	public Controller(GLAutoDrawable canvas1){
		System.out.print("Into Controller's constructor\n\n");

		canvas= canvas1;

		//Frustum parameters 
		float frustumWidth= (float)canvas.getWidth();
		float frustumHeight= (float)canvas.getHeight();	
		float xRight= frustumWidth/2.0f; 
		float xLeft= -xRight;
		float yTop= frustumHeight/2.0f; 
		float yBottom= -yTop;
		float near= 1;
		float far= 1000;

		//View parameters
		float[] eye=  {200.0f, 200.0f, 200.0f};
		float[] look= {0.0f, 0.0f, 0.0f};
		float[] up=   {0, 1, 0};

		rotate = false;
		anguloRotarX = 0;
		anguloRotarY = 0;
		anguloRotarZ = 0;

		reset = false;
		//Camera construction
		camera= new Camera(eye, look, up, xLeft, xRight, yTop, yBottom, near, far);

		//Scene construction 
		scene= new Scene(xLeft, xRight, yTop, yBottom, near, far); //Initialize the scene size with the frustum size 	    

		System.out.println("Scene bounds:");
		System.out.println("xLeft:  \t" + xLeft +   " xRight:\t" +  xRight);
		System.out.println("yBottom:\t" + yBottom + " yTop:  \t" +  yTop);
		System.out.println("near:\t" + near + " far:  \t" +  far);
		System.out.println();

	}


	/////////////////////////////////
	// GLEventListener implementation
	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.print("Into init\n\n");

		GL2 gl = drawable.getGL().getGL2();

		//Background color
		gl.glClearColor(0, 0, 0, 1);
		//gl.glClearColor(0.6f,0.7f,0.8f,1.0f);


		//Thickness
		gl.glPointSize(4.0f);
		gl.glLineWidth(2.0f);

		//Lighting
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		float[] d={1.0f,1.0f,1.0f,1.0f};
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, d, 0);
		float[] a={0.3f,0.3f,0.3f,1.0f};
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, a, 0);
		float[] p={800.0f, 800.0f, 800.0f, 0};	 
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, p, 0);

		//Materials
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.1f);

		//Other 3D parameters
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_NORMALIZE); //Default setting!
		gl.glShadeModel(GL2.GL_SMOOTH); //Default setting!
		gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, GL2.GL_TRUE); //This is not the default setting!


		//Projection matrix setting
		camera.setProjection(drawable);

		//Modelview matrix setting
		camera.setView(drawable, glu);

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		System.out.print("Into display\n");

		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glPushMatrix();
	
		if(reset){
			reset = false;		
			gl.glRotated(anguloRotarX, 1, 0, 0);
			gl.glRotated(anguloRotarY, 0, 1, 0);
			gl.glRotated(anguloRotarZ, 0, 0, 1);
		}
		else if(rotate){
			gl.glRotated(anguloRotarX, 1, 0, 0);
			gl.glRotated(anguloRotarY, 0, 1, 0);
			gl.glRotated(anguloRotarZ, 0, 0, 1);
		}
		

		gl.glScaled(100, 100, 100);
		drawAxis(drawable);
		scene.draw(drawable);
	    gl.glPopMatrix();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {

		float viewPortRatio= (float)width/(float)height;	
		camera.reshape(viewPortRatio);
		camera.setProjection(drawable);	
	}

	/////////////////////////////////
	// KeyListener implementation
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_X: rotateX(true);	break;
		case KeyEvent.VK_Y: rotateY(true);	break;
		case KeyEvent.VK_Z: rotateZ(true);	break;
		case KeyEvent.VK_R: reinicia();		break;
		case KeyEvent.VK_P: cambiarModo(2);	break;
		case KeyEvent.VK_V: cambiarModo(1);	break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}	
	public void rotateX(boolean rotarDerecha){

		rotate = true;
		if(rotarDerecha){
			//tuplaReset.add(new TuplaReset(1, 0, 0, 10));
			//resetAngles();
			anguloRotarX+=5;
		}
		else{
			//tuplaReset.add(new TuplaReset(1, 0, 0, -10));
			//resetAngles();
			anguloRotarX-=5;
		}
		canvas.display();
	}
	public void rotateY(boolean rotarDerecha){

		rotate = true;
		if(rotarDerecha){
			//tuplaReset.add(new TuplaReset(0, 1, 0, 10));
			//resetAngles();
			anguloRotarY+=5;
		}
		else{
			//tuplaReset.add(new TuplaReset(0, 1, 0, -10));
			//resetAngles();
			anguloRotarY-=5;
		}
		canvas.display();
	}

	public void rotateZ(boolean rotarDerecha){

		rotate = true;
		if(rotarDerecha){
			//tuplaReset.add(new TuplaReset(0, 0, 1, 10));
			//resetAngles();
			anguloRotarZ+=5;
		}		
		else{
			//tuplaReset.add(new TuplaReset(0, 0, 1, -10));
			//resetAngles();
			anguloRotarZ-=5;
		}

		canvas.display();
	}

	private void resetAngles(){ anguloRotarX = 0; anguloRotarY = 0; anguloRotarZ = 0; }


	public void reinicia(){
		reset = true;
		resetAngles();
		canvas.display();
	}


	/////////////////////////////////
	// Specific methods	
	//Axis rendering
	private void drawAxis(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();    
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);  
		gl.glBegin(GL.GL_LINES);
		gl.glColor3f(1,  0,  0);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(300, 0, 0); 

		gl.glColor3f(0,  1,  0);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 300, 0);

		gl.glColor3f(0,  0,  1);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 0, 300);
		gl.glEnd();
	}


	public void cambiarModo(int modo) {
		scene.cambiarModo(modo);
		reset = true;
		canvas.display();
	}
}