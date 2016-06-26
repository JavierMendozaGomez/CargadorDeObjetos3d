package model.recursos;

public class Cara {
	
	private int numVertices;
	private VerticeNormal[] arrayVN;
	
	public Cara(int numVertices, VerticeNormal[] vn){
		this.numVertices = numVertices;
		this.arrayVN = vn;
	}
	
	public int getNumVertices(){
		return numVertices;
	}

	public int getIndiceNormal(int j) {
		return arrayVN[j].getIndiceNormal();
	}

	public int getIndiceVertice(int j) {
		// TODO Auto-generated method stub
		return arrayVN[j].getIndiceVertice();
	}
}
