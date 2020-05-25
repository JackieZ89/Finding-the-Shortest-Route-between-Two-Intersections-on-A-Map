package project3;

/*
Name: Jiayi Yuan, Jiaxin Zhang

Net ID: juan15, jzhang89

Lab Section: MW 12:30-13:45

E-mail:jyuan15@u.rochester.edu, jzhang89@u.rochester.edu
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class drawMap extends JPanel {
	
	public static HashMap<String, Node> nodes;
	public static ArrayList<Edge> edges;
	public static ArrayList<Edge> edges2;

	public static double minLa, minLo, maxLa, maxLo;
	public static double xScale, yScale;
	
	
	public drawMap(ArrayList<Edge> edges, ArrayList<Edge> edges2 , HashMap<String, Node> nodes, double minLa, double minLo, double maxLa, double maxLo) {
		drawMap.edges = edges;
		drawMap.edges2 = edges2;
		drawMap.nodes = nodes;
		
		drawMap.minLa = minLa;
		drawMap.minLo = minLo;
		drawMap.maxLa = maxLa;
		drawMap.maxLo = maxLo;
		
	}
	
	
	
	public void paintComponent(Graphics g) {
		//set the scales
		xScale = this.getWidth() / (maxLo - minLo);
		yScale = this.getHeight() / (maxLa - minLa);
		
		Graphics2D g2 = (Graphics2D) g;
		
		super.paintComponent(g2);
		
		g2.setColor(Color.BLACK);
		
		//print the whole map
		for(Edge e : edges) {
			
			//get the ID of two intersections
			Node N1 = nodes.get(e.getV1());
			Node N2 = nodes.get(e.getV2());
			
			double lat1 = N1.getLatitude();
			double long1 = N1.getLongitude();
			double lat2 = N2.getLatitude();
			double long2 = N2.getLongitude();
			
			g2.draw(new Line2D.Double((long1-minLo) * xScale, getHeight() - ((lat1 - minLa) * yScale), 
					(long2-minLo) * xScale, getHeight() - ((lat2 - minLa) * yScale)));	
			
		}
		
		//printing the shortest path if exists
		if(edges2 != null) {
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(2));
			
			for(Edge e : edges2) {
				
				//get the ID of two intersections
				Node N1 = nodes.get(e.getV1());
				Node N2 = nodes.get(e.getV2());
				
				double lat1 = N1.getLatitude();
				double long1 = N1.getLongitude();
				double lat2 = N2.getLatitude();
				double long2 = N2.getLongitude();
				
				g2.draw(new Line2D.Double((long1-minLo) * xScale, getHeight() - ((lat1 - minLa) * yScale), 
						(long2-minLo) * xScale, getHeight() - ((lat2 - minLa) * yScale)));	
				
			}
		}
		

	}
	

}
