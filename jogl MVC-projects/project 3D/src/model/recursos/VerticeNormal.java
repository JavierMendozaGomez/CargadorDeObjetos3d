package model.recursos;

public class VerticeNormal {
	
	private int indiceVertice;
	

	private int indiceNormal;
	
	public VerticeNormal(int indV, int indN){
		this.indiceVertice = indV;
		this.indiceNormal = indN;
	}
	

	public int getIndiceVertice() {
		return indiceVertice;
	}


	public int getIndiceNormal() {
		return indiceNormal;
	}

}
