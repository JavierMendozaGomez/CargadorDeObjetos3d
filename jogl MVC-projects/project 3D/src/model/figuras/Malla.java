package model.figuras;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import model.recursos.Cara;
import model.recursos.PV3D;

public abstract class Malla {

	protected int numVertices;
	protected PV3D[] tablaVertices;

	protected int numNormales; //=numCaras, frecuentemente
	protected ArrayList<PV3D> tablaNormales;

	protected int numCaras;
	protected ArrayList<Cara> tablaCaras;


	public void dibuja(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();

		//gl.glColor3f(1f ,1f, 1f);
		gl.glColor3f(0.8f,0.6f,0.0f);

		for (int i=0; i< tablaCaras.size(); i++) {
			gl.glLineWidth(1);

			gl.glNormal3d(tablaNormales.get(i).getX(),tablaNormales.get(i).getY(),tablaNormales.get(i).getZ());

			gl.glBegin(GL2.GL_POLYGON);  //o glBegin(GL_LINE_LOOP);
			for (int j=0; j< tablaCaras.get(i).getNumVertices(); j++) {
				//int iN=tablaCaras[i].getIndiceNormal(j);
				int iV=tablaCaras.get(i).getIndiceVertice(j);
				//Si hubiera coordenadas de textura, aquí se suministrarían
				//las coordenadas de textura del vértice j con glTexCoor2f(…);
				gl.glVertex3d(tablaVertices[iV].getX(), tablaVertices[iV].getY(), tablaVertices[iV].getZ());
			}

			gl.glEnd();
		}

	}
	
	


	///Devuelve la lista completa de normales calculadas por el metodo de newell (una por cada cara)
	protected void CalculaNormalPorNewell(){
		System.out.println("el tamaño es " + tablaCaras.size() );
		for (int k = 0; k < tablaCaras.size(); k++) {
			Cara c = tablaCaras.get(k);
			PV3D v = new PV3D(0,0,0,0);
			
			for (int i = 0; i < c.getNumVertices(); i++) {
				PV3D verticeActual = tablaVertices[c.getIndiceVertice(i)];
				int siguiente = c.getIndiceVertice((i+1)% c.getNumVertices());
				PV3D verticeSiguiente = tablaVertices[siguiente];

				v.setX(v.getX() + 
						((verticeActual.getY() - verticeSiguiente.getY())*
								(verticeActual.getZ() + verticeSiguiente.getZ()))
						);

				v.setY(v.getY() + 
						((verticeActual.getZ() - verticeSiguiente.getZ())*
								(verticeActual.getX() + verticeSiguiente.getX()))
						);

				v.setZ(v.getZ() +
						((verticeActual.getX() - verticeSiguiente.getX())*
								(verticeActual.getY() + verticeSiguiente.getY()))
						);
			}
			v.normalizar();
			tablaNormales.add(v);
		}

	}
	
	public abstract void hazMallaSuperficie();


}





