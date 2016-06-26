package model.recursos;

public class PV3D {
	/**
	 * 
	 */
	private double x;
	private double y;
	private double z;
	private int homogeneo;
	
	public PV3D(double x, double y, double z,int homogeneo){
		this.x = x;
		this.y = y;
		this.z = z;
		this.homogeneo = homogeneo;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public int getHomogeneo(){
		return this.homogeneo;
	}

	public PV3D clona(){
		return new PV3D(this.x, this.y, this.z,this.homogeneo);
	}

	public void normalizar(){
		double norm;
		norm = 1.0/Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
		this.x *= norm;
		this.y *= norm;
		this.z *= norm;
	}

	public boolean esPunto() {
		if(homogeneo == 1)
			return true;
		else
			return false;
	}

	public boolean esVector() {
		if(homogeneo == 0)
			return true;
		else
			return false;
	}
	public  double productoEscalar(PV3D v1){		///PRODUCTO ESCALAR

		return (this.x*v1.getX() + this.y*v1.getY() + this.z*v1.getZ());
	}

	public PV3D productoVectorial(PV3D p){			///PRODUCTO VECTORIAL
		double auxX,auxY,auxZ;
		
		auxX = this.y * p.getZ() - p.getY() * this.z;
		auxY = (this.x * p.getZ() - p.getX() * this.z) * (-1);
		auxZ = this.x * p.getY() - p.getX() * this.y;		
		
		return new PV3D(auxX,auxY,auxZ,p.getHomogeneo());
	}

}
