package model.figuras;

import java.util.ArrayList;

import model.recursos.Cara;
import model.recursos.PV3D;
import model.recursos.VerticeNormal;


public class Pretzel extends Malla{


	private PV3D perfil[];
	private int nP;


	public Pretzel(){
		this.nP = 32; //Lados de la figura inicial (Elipse)
		crearPerfil();
		hazMallaSuperficie();
	}


	private void crearPerfil() {
		double rangoElipse = 2.0 / nP;
		int W = 7;
		int H = 15;
		this.perfil = new PV3D[nP];

		for (int i = 0; i < nP; i++){
			perfil[i]=new PV3D( W*Math.cos(Math.PI*rangoElipse) , H*Math.sin(Math.PI*rangoElipse) , 0 , 1);			
			rangoElipse = rangoElipse + (2.0/nP);
		}		
	}




	 public void hazMallaSuperficie(){		

		
		int r = 40;
		int R = 80;
		int nRep = 50;
		numVertices = nP * nRep;
		tablaVertices = new PV3D[numVertices];
		numCaras = numVertices/2;
		tablaCaras = new ArrayList<Cara>();
		numNormales = numCaras;
		tablaNormales = new ArrayList<PV3D>();

		double rangoPretzel = 0;	//[0, 4pi]


		for (int i = 0; i < nRep; i++){
			

			PV3D CurvaEjeExtrusion = new PV3D((R + r * Math.sin(Math.PI*rangoPretzel/2)) * Math.cos(Math.PI*rangoPretzel),
					0, (R + r * Math.sin(Math.PI*rangoPretzel/2)) * Math.sin(Math.PI*rangoPretzel), 1);

			PV3D marcoFrenet = new PV3D((r/2.0 * Math.cos(Math.PI*rangoPretzel/2) * Math.cos(Math.PI*rangoPretzel)) - ((R + r*Math.sin(Math.PI * rangoPretzel /2 )) * Math.sin(Math.PI*rangoPretzel)),
					0, (r/2.0 * Math.cos(Math.PI*rangoPretzel/2) * Math.sin(Math.PI*rangoPretzel)) - ((R + r*Math.sin(Math.PI * rangoPretzel /2 )) * Math.cos(Math.PI*rangoPretzel)), 0);

			PV3D T = marcoFrenet;

			T.normalizar();

			PV3D B = new PV3D(0, 1, 0, 0);

			PV3D N = B.productoVectorial(T);
			
			//Calculo de vertices

			for (int j = 0; j < nP; j++) {
				
				double X = N.getX() * perfil[j].getX() + B.getX() * perfil[j].getY() + T.getX() * perfil[j].getZ() + CurvaEjeExtrusion.getX();
				double Y = N.getY() * perfil[j].getX() + B.getY() * perfil[j].getY() + T.getY() * perfil[j].getZ() + CurvaEjeExtrusion.getY();
				double Z = N.getZ() * perfil[j].getX() + B.getZ() * perfil[j].getY() + T.getZ() * perfil[j].getZ() + CurvaEjeExtrusion.getZ();
				
				int indice = i * nP + j;
				tablaVertices[indice]=new PV3D(X, Y, Z, 1);
				
				//Calculo de caras

				if (i > 0 && j > 0) {					
					VerticeNormal[] vn = new VerticeNormal[4];
					vn[0] = new VerticeNormal(indice, indice);
					vn[1] = new VerticeNormal(indice - nP, indice - nP);
					vn[2] = new VerticeNormal(indice - nP - 1, indice - nP - 1);
					vn[3] = new VerticeNormal(indice - 1, indice - 1);
					tablaCaras.add(new Cara(4, vn));
				}
			}
			rangoPretzel = rangoPretzel + (4.0/(nRep));
		}
		
		//Normales
		CalculaNormalPorNewell();
	}	
}

