package model.figuras;

import java.util.ArrayList;

import model.LeeFichero;
import model.recursos.Cara;
import model.recursos.PV3D;
import model.recursos.VerticeNormal;

public class Vara extends Malla{

	
	public Vara(){
		hazMallaSuperficie();
	}


	public void hazMallaSuperficie() {

		LeeFichero fichero = new LeeFichero("staff.outline");

		//Dimensiones de la superficie
		//double uMin = fichero.getMinX(), uMax = fichero.getMaxX(), vMin = fichero.getMinY(), vMax = fichero.getMaxY();
		ArrayList<Double> arrayX = fichero.getArrayX();
		ArrayList<Double> arrayY = fichero.getArrayY();

		//Número de divisiones
		int nU = 2000;							//numero de cortes verticales
		int nV = fichero.getNumVertices();		//numero de cortes horizontales

		/*
		//Incrementos
		double incU = (uMax-uMin)/(nU-1);
		double incV = (vMax-vMin)/(nV-1);
		 */

		double incr =((2*Math.PI)/(nU-1));

		//Tamaños de los arrays
		numVertices = nU*nV;
		numCaras = (nU-1)*(nV-1);
		numNormales = numCaras; 	//Una normal por cara

		//Creación de los arrays
		tablaVertices = new PV3D[numVertices];
		tablaNormales = new ArrayList<PV3D>();		
		tablaCaras = new ArrayList<Cara>();

		for (int i=0; i < nU; i++)
			for (int j=0; j<nV; j++) {

				//Calculo de vertices
				int indice = i * nV + j;				
				tablaVertices[indice] =	new PV3D((arrayX.get(j)* Math.cos((i*incr))),arrayY.get(j), arrayX.get(j)*Math.sin((i*incr)), 1);/////no lo se


				//Construcción de caras cuadrangulares
				if (i > 0 && j > 0) {
				//	int indiceCara = (i-1)*(nV-1)+(j - 1);
					VerticeNormal[] vn = new VerticeNormal[4];
					vn[0] = new VerticeNormal(indice, indice);
					vn[1] = new VerticeNormal(indice - nV, indice - nV);
					vn[2] = new VerticeNormal(indice - nV - 1, indice - nV - 1);
					vn[3] = new VerticeNormal(indice - 1, indice - 1);
					Cara cara = new Cara(4, vn);
					tablaCaras.add(cara) ;

				}//if
			}//for
		CalculaNormalPorNewell();
	}
	
	

}
