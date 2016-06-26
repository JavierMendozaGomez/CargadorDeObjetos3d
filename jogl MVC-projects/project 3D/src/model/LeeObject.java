package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import model.recursos.Cara;
import model.recursos.PV3D;
import model.recursos.VerticeNormal;

public class LeeObject {
	private String nombre;
	
	private ArrayList<PV3D> tablaVertices;
	private ArrayList<PV3D> tablaNormales;
	private ArrayList<Cara> tablaCaras;
	
	public LeeObject(String nombre){
		this.tablaVertices = new ArrayList<PV3D>();
		this.tablaNormales = new ArrayList<PV3D>();
		this.tablaCaras    = new ArrayList<Cara>();
		this.nombre = nombre;
		leeArchivo();
	}

	private void leeArchivo() {
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      
	      
	      try {
	      archivo = new File(nombre);

			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			br.readLine();          //#  Stanford Bunny
			br.readLine(); 				//#  Normals but no textures	
			br.readLine(); 
			String linea;
			while((linea = br.readLine()) != null){
				String cadena[] = linea.split(" ");
				if(cadena[0].equalsIgnoreCase("v")){			///Si es un vertice
					PV3D punto = new PV3D(Double.parseDouble(cadena[1]),Double.parseDouble(cadena[2]),
							Double.parseDouble(cadena[3]),1);
					tablaVertices.add(punto);
					
				}
				
				 if(cadena[0].equalsIgnoreCase("vn")){			///Si es un vertice
					PV3D punto = new PV3D(Double.parseDouble(cadena[1]),Double.parseDouble(cadena[2]),
							Double.parseDouble(cadena[3]),1);
					tablaNormales.add(punto);
					
				}
				
				 if(cadena[0].equalsIgnoreCase("f")){			///Si es un vertice
					String v1[] = cadena[1].split("//");
					String v2[] = cadena[2].split("//");
					String v3[] = cadena[3].split("//");

					VerticeNormal[] vn = new VerticeNormal[3];
					vn[0] = new VerticeNormal(Integer.parseInt(v1[0]) -1,Integer.parseInt(v1[1])-1);
					vn[1] = new VerticeNormal(Integer.parseInt(v2[0])-1,Integer.parseInt(v2[1]) -1);
					vn[2] = new VerticeNormal(Integer.parseInt(v3[0]) -1,Integer.parseInt(v3[1]) -1);
					Cara cara = new Cara(3, vn);
					
					tablaCaras.add(cara) ;
					
				}
					
					

			}
		 
		      fr.close();
		    }
			
			
			
			
		 catch (Exception e) {
			           System.out.println("Excepcion leyendo fichero "+ nombre + ": " + e);
		}
		
	}	
	
	
	



	public ArrayList<PV3D> getTablaVertices(){
		return this.tablaVertices;
	}
	
	public ArrayList<PV3D> getTablaNormales(){
		return this.tablaNormales;
	}
	public ArrayList<Cara> getTablaCaras(){
		return this.tablaCaras;
	}
}
