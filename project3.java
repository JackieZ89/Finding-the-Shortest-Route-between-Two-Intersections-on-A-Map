package project3;

/*
Name: Jiayi Yuan, Jiaxin Zhang

Net ID: juan15, jzhang89

Lab Section: MW 12:30-13:45

E-mail:jyuan15@u.rochester.edu, jzhang89@u.rochester.edu
 */


import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class project3 {
	
	public static HashMap<String, Road> roads = new HashMap<String,Road>();
	public static HashMap<String, Node> nodes = new HashMap<String,Node>();
	
	//array stores information that can be used when drawing graphs.
	public static ArrayList<Edge> drawEdge = new ArrayList<Edge>();
	public static ArrayList<Edge> edges2 = new ArrayList<Edge>();
	
	//for finding the shortest path
	public static PriorityQueue<Node> visited = new PriorityQueue<Node>(10000000);
	public static ArrayList<String>path = new ArrayList<String>();
	public static ArrayList<Road> shortestPath = new ArrayList<Road>();
	
	
	//Dijkstra algorithm
	static void Dijkstra(Node start, Node end) {
		
		start.setDistance(0);
		start.setVisited(true);
		
		visited.add(start);
		
		Node curr = start;
		
		//keep looping until reach to the destination
		while(end.isVisited()==false) {
			
			curr.setVisited(true);
			
			//loop through current node's neighbors.
			for(DijkElem d : curr.adjDijkElem) {
			
				//add the nodes into visited array
				if(d.N.isVisited()==false && !visited.contains(d.N)) {
					//visited.add(d.N.getID());
					visited.add(d.N);
				}
				
				if(d.N.isVisited() == false) {
					if(d.N.getDistance() > curr.getDistance()+d.weight) {
						//assign the shorter distance to the node
						d.N.setDistance(curr.getDistance()+d.weight);
						//set the new ancestor to the node if there is a change of shortest path.
						d.N.ancestor = curr;
					}
				}
			}
			//get the node in visited array which has the shortest distance
			curr = visited.poll();
		}
	}
	
	
	//function for printing and storing the shortest path
	public static void printPath(Node start,Node end){
		
		try {
			//find the shortest path
			Dijkstra(start, end);

			Node n = end;
			path.add(end.getID());
			
			while (n != start) {
				Road r = new Road(n.getID(), n, n.ancestor);
				shortestPath.add(r);
				//trace back the ancestors
				path.add(n.ancestor.getID());
				n = n.ancestor;
			}

			//print the path
			for (int i = path.size() - 1; i >= 0; i--) {
				System.out.println(path.get(i));
			}

			// print out final distance
			System.out.println("\nThe total distance between " + start.getID() + " and " + end.getID() + " is: "
					+ end.getDistance() + " miles");
			
		} catch (NullPointerException e) {
			//if there is no path between the two nodes, print the notice
			System.out.println("There is no path.");
		}
		
		//store the path information into edges2 array, so it can be used when drawing graph
		for(Road r: shortestPath) {
			edges2.add(new Edge(r.getID(),r.getN1().getID(),r.getN2().getID()));
		}
		
	}
	
	
	
	public static void main(String[] args) {
		String read;
		String[] split;
		String a,b,c,d;
		double latitude,longitude;
		long start = System.currentTimeMillis();
		
		Double maxlat=Double.NEGATIVE_INFINITY;
		Double maxlong = Double.NEGATIVE_INFINITY;
		Double minlat = Double.POSITIVE_INFINITY;
		Double minlong = Double.POSITIVE_INFINITY;
		
		
		//read file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			read = reader.readLine();
			while(read!=null) {
				split = read.split("\t");
				a = split[0];
				b = split[1];
				c = split[2];
				d = split[3];
				
				if(a .equals("i")) {
					latitude = Double.parseDouble(c);
					longitude = Double.parseDouble(d);
					Node newnode = nodes.get(b);
					if(newnode == null) {
						newnode = new Node(b, latitude,longitude);
						nodes.put(b, newnode);
					}
					if(latitude>maxlat) {
						maxlat = latitude;
					}
					if(longitude>maxlong) {
						maxlong = longitude;
					}
					if(latitude<minlat) {
						minlat = latitude;
					}
					if(longitude<minlong) {
						minlong = longitude;
					}
					
					//read in roads
				}else if(a.equals("r")) {
					String rID = b;
					Node node1 = nodes.get(c);
					Node node2 = nodes.get(d);
					Road newroad = new Road(rID,node1,node2);
					//add to road hashmap
					roads.put(b, newroad);
					//add to each nodes' adjNode list
					node1.addAdj(new DijkElem(node2,newroad.getWeight()));
					node2.addAdj(new DijkElem(node1,newroad.getWeight()));
					Edge newedge = new Edge(rID,c,d);
					
					//add to edge arraylist
					drawEdge.add(newedge);
				}else
					System.out.println("Invalid inputs");
				read = reader.readLine();
			}		
			reader.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		//read command line arguments
		try {
			if (args[1]!=null) {
				if (args[1].equals("-show")) {

					// draw graph
					JFrame frame = new JFrame();
					frame.setPreferredSize(new Dimension(900, 700));
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					drawMap drawmap = new drawMap(drawEdge, edges2,nodes, minlat, minlong, maxlat, maxlong);
					frame.add(drawmap);
					frame.pack();
					frame.setResizable(true);
					frame.setVisible(true);
					
				} else if ((!args[1].equals("-show")) && args[1].equals("-directions")) {
					//Show the direction
					System.out.println("Path from " + args[2] + " to " + args[3] + " is: ");
					//print the path
					printPath(nodes.get(args[2]), nodes.get(args[3]));

				}
			}
			if (args[2] != null && args[2].equals("-directions")) {
				
				System.out.println("Path from " + args[3] + " to " + args[4] + " is: ");
				printPath(nodes.get(args[3]), nodes.get(args[4]));
				
				// draw graph and direction
				JFrame frame = new JFrame();
				frame.setPreferredSize(new Dimension(900, 700));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				drawMap drawmap = new drawMap(drawEdge, edges2,nodes, minlat, minlong, maxlat, maxlong);
				frame.add(drawmap);
				frame.pack();
				frame.setResizable(true);
				frame.setVisible(true);
				
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	
		//running time
		long end = System.currentTimeMillis();
		long runningtime = end-start;
		System.out.println("The running time is: "+runningtime+" milliseconds.");
		
		
	}

	
}
