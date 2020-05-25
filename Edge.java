package project3;

/*
Name: Jiayi Yuan, Jiaxin Zhang

Net ID: juan15, jzhang89

Lab Section: MW 12:30-13:45

E-mail:jyuan15@u.rochester.edu, jzhang89@u.rochester.edu
 */


public class Edge {
	
	private String ID;
	private String V1,V2; //intersections connected by the road
	private double weight;
	
	public Edge(String id, String v1, String v2){
		this.ID = id;
		this.V1 = v1;
		this.V2 = v2;
	}

	public String getID() {
		return ID;
	}

	public String getV1() {
		return V1;
	}

	public String getV2() {
		return V2;
	}

	public double getWeight() {
		return weight;
	}
	
	
}
	
