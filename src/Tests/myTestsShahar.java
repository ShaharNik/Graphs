package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.graph;

public class myTestsShahar 
{

	static void isConnected() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.addNode(new Vertex(3));
		s.addNode(new Vertex(4));
		s.addNode(new Vertex(5));
		s.addNode(new Vertex(6));
		s.addNode(new Vertex(7));
		s.connect(1, 2, 0);
		s.connect(3, 5, 0);
		s.connect(1, 3, 0);
		s.connect(2, 4, 0);
		s.connect(2, 5, 0);
		s.connect(3, 2, 0);
		s.connect(4, 6, 0);
		s.connect(4, 5, 0);
		s.connect(5, 6, 0);
		s.connect(5, 7, 0);
		s.connect(6, 7, 0);
		s.connect(7, 2, 0);
//		s.addNode(new Vertex(1));
//		s.addNode(new Vertex(2));
//		s.connect(1, 2, 0);
		Graph_Algo e = new Graph_Algo();
		//Graph_Algo e = new Graph_Algo();
		e.init(s);
		System.out.println(e.isConnected());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		isConnected();
	}

}
