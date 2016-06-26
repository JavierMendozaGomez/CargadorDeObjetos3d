package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LeeFichero {
	
	private String nombre;
	private int numVertices;
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	
	private ArrayList<Double> arrayX;
	private ArrayList<Double> arrayY;
	public LeeFichero(String nombre){
		this.arrayX = new ArrayList<Double>();
		this.arrayY = new ArrayList<Double>();
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
			
			br.readLine(); //Basura
			
			String vertex = br.readLine();
			this.numVertices = Integer.parseInt(vertex);
			
			br.readLine(); //Basura
			br.readLine(); //Basura
			
			String rango = br.readLine();
			String rangoSplit[] = rango.split("\t");
			this.minX = Double.parseDouble(rangoSplit[0]);
			this.minY = Double.parseDouble(rangoSplit[1]);
			this.maxX = Double.parseDouble(rangoSplit[2]);
			this.maxX = Double.parseDouble(rangoSplit[3]);
			System.out.println(minX);
			System.out.println(minY);
			System.out.println(maxX);
			System.out.println(maxY);
			
			br.readLine(); //Basura
			br.readLine(); //Basura
			
			for(int i= 0; i < numVertices; i++){
				String coor = br.readLine();
				String coorSplit[] = coor.split("\t");
				arrayX.add(Double.parseDouble(coorSplit[0]));
				arrayY.add(Double.parseDouble(coorSplit[1]));
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public int getNumVertices() {
		return numVertices;
	}

	public double getMinX() {
		return minX;
	}

	public double getMinY() {
		return 0;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMaxY() {
		return 48;
	}
	
	public ArrayList<Double> getArrayX(){
		return this.arrayX;
	}
	
	public ArrayList<Double> getArrayY(){
		return this.arrayY;
	}

}
