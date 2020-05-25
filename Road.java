package project3;

/*
Name: Jiayi Yuan, Jiaxin Zhang

Net ID: juan15, jzhang89

Lab Section: MW 12:30-13:45

E-mail:jyuan15@u.rochester.edu, jzhang89@u.rochester.edu
 */


public class Road{
	private String ID;
	private Node N1,N2;
	private double weight;
	
	public Road(String id,Node n1, Node n2) {
		this.ID = id;
		this.N1 = n1;
		this.N2 = n2;
		this.weight = calWeight(n1,n2);
		
	}
	
	//calculate the distance(weight)
	
	public double calWeight(Node n1, Node n2) {
		//radius of the earth in miles
		double r = 3959;
		//latitude of both nodes
		double la1 = Math.toRadians(n1.getLatitude());
		double la2 = Math.toRadians(n2.getLatitude());
		//longitude of both nodes
		double lo1 = Math.toRadians(n1.getLongitude());
		double lo2 = Math.toRadians(n2.getLongitude());
		
		//haversine formula
		double deltaLa = la2-la1;
		double deltaLo = lo2-lo1;
		
		double x = (Math.sin(deltaLa/2) * Math.sin(deltaLa/2)) + (Math.cos(la1) * Math.cos(la2) * Math.sin(deltaLo/2) * Math.sin(deltaLo/2));
		
		double d = r * (2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x)));
		
		return d;
	}


	public double getWeight() {
		return weight;
	}

	public String getID() {
		return ID;
	}

	public Node getN1() {
		return N1;
	}

	public Node getN2() {
		return N2;
	}
	
	
	

}
