package project3;

/*
Name: Jiayi Yuan, Jiaxin Zhang

Net ID: juan15, jzhang89

Lab Section: MW 12:30-13:45

E-mail:jyuan15@u.rochester.edu, jzhang89@u.rochester.edu
 */


import java.util.Comparator;
import java.util.LinkedList;

public class Node implements Comparable<Node>{
	
	private String ID;
	private double latitude;
	private double longitude;
	
	
	private boolean visited;
	private double distance;
	
	public Node ancestor;
	//the linkedlist that stores the nodes adjacent nodes
	public LinkedList<DijkElem> adjDijkElem;
	
	public Node(String id, double l1, double l2) {
		
		this.ID = id;
		this.latitude = l1;
		this.longitude = l2;
		
		this.visited = false;
		this.distance = Double.POSITIVE_INFINITY;
		adjDijkElem = new LinkedList<DijkElem>();
		ancestor = null;
	}
	
	
	public void addAdj(DijkElem d) {
		adjDijkElem.add(d);
	}
	
	//implement comparator so the priority queue in main function can compare them by their distance
	@Override
	public int compareTo(Node n) {
    	
		//compare the distance of the roads
		if(this.distance < n.getDistance()) {
			return -1;
		}else if(this.distance > n.getDistance()) {
			return 1;
		}else {
			return 0;
		}
    }

	
	
	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public boolean isVisited() {
		return visited;
	}


	public void setVisited(boolean visited) {
		this.visited = visited;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	

}
