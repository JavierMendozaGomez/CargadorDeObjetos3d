//////////////////////////////////////////// 
// Project skeleton for Computer 3D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package model;

import javax.media.opengl.GLAutoDrawable;

import model.figuras.MallaObject;
import model.figuras.Pretzel;
import model.figuras.Vara;

public class Scene {
	
	//Scene variables
	//private double xPrism, yPrism, zPrism; //Lower corner
	//private double prismWidth, prismHeight, prismDepth; //Prism size
	private Vara vara;
	private Pretzel pretzel;
	private int modo;
	private MallaObject FiguraObject;
	
	/////////////////////////////////
	public Scene(double xLeft, double xRight, double yTop, double yBottom, double near, double far){
		
		//Cube construction
		/*prismWidth= 0.4*(xRight-xLeft);
		prismHeight= 0.4*(yTop-yBottom);;
		prismDepth= 0.1* (far-near);
		
        //Centered at (0,0,0)
		xPrism= -prismWidth/2.0;
        yPrism= -prismHeight/2.0;
        zPrism= -prismDepth/2.0; */
		modo = 3;
		vara = new Vara();
		pretzel = new Pretzel();
		LeeObject archivo = new LeeObject("bunny.obj");
		FiguraObject = new MallaObject(archivo.getTablaVertices(), archivo.getTablaNormales(), archivo.getTablaCaras());
	}
	

	/////////////////////////////////
	public void draw(GLAutoDrawable drawable){
	
		if(modo == 1)
			vara.dibuja(drawable);
		else if(modo == 2)
			pretzel.dibuja(drawable);
		else
			FiguraObject.dibujaObject(drawable);
	}


	public void cambiarModo(int modo) {
		this.modo = modo;		
	}
	
	/////////////////////////////////
	/*public void movePrism(double xShift, double yShift, double zShift){
		xPrism+= xShift;
		yPrism+= yShift;
		zPrism+= zShift;
	}*/
}