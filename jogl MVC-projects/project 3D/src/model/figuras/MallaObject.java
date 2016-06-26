package model.figuras;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import model.recursos.Cara;
import model.recursos.PV3D;

public class MallaObject {

	private ArrayList<PV3D> tablaVertices;
	private ArrayList<PV3D> tablaNormales;
	private ArrayList<Cara> tablaCaras;
	public MallaObject(ArrayList<PV3D> tablaVertices,ArrayList<PV3D> tablaNormales,ArrayList<Cara> tablaCaras){
		this.tablaVertices = tablaVertices;
		this.tablaNormales = tablaNormales;
		this.tablaCaras = tablaCaras;
	}
	
	public void dibujaObject(GLAutoDrawable drawable){		
		GL2 gl = drawable.getGL().getGL2();

	//	gl.glColor3f(1f ,1f, 1f);
	gl.glColor3f(0.8f,0.6f,0.0f);

		for (int i=0; i< tablaCaras.size(); i++) {
			gl.glLineWidth(1);

			//gl.glNormal3d(tablaNormales.get(i).getX(),tablaNormales.get(i).getY(),tablaNormales.get(i).getZ());

			gl.glBegin(GL2.GL_POLYGON);  //o glBegin(GL_LINE_LOOP);
			for (int j=0; j< tablaCaras.get(i).getNumVertices(); j++) {
				//int iN=tablaCaras[i].getIndiceNormal(j);
				int iN=tablaCaras.get(i).getIndiceNormal(j );
				gl.glNormal3d(tablaNormales.get(iN ).getX(),tablaNormales.get(iN).getY(),tablaNormales.get(iN ).getZ());

				int iV=tablaCaras.get(i).getIndiceVertice(j );
				//Si hubiera coordenadas de textura, aquí se suministrarían
				//las coordenadas de textura del vértice j con glTexCoor2f(…);
				gl.glVertex3d(tablaVertices.get(iV).getX(), tablaVertices.get(iV).getY(), tablaVertices.get(iV).getZ());
				
			}

			gl.glEnd();
		}

	}
}
