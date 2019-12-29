package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.graph;
import dataStructure.node_data;

class AlgoTests 
{

	@Test
	void testIsConnected() 
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
		s.connect(1, 3, 0);
		s.connect(2, 4, 0);
		s.connect(2, 5, 0);
		s.connect(3, 2, 0);
		s.connect(4, 6, 0);
		s.connect(4, 5, 0);
		s.connect(5, 6, 0);
		s.connect(5, 7, 0);
		s.connect(6, 7, 0);
		s.connect(7, 1, 0);
		////		s.addNode(new vertex(1));
		////		s.addNode(new vertex(2));
		////		s.connect(1, 2, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(true, e.isConnected());

		graph g2 = new DGraph();
		g2.addNode(new Vertex(1));
		g2.addNode(new Vertex(2));
		g2.addNode(new Vertex(3));
		g2.addNode(new Vertex(4));
		g2.addNode(new Vertex(5));
		g2.connect(1, 5, 30);
		g2.connect(5, 1, 30);
		g2.connect(2, 5, 30);
		g2.connect(5, 2, 30);
		g2.connect(3, 5, 30);
		g2.connect(5, 3, 30);
		g2.connect(4, 5, 30);
		g2.connect(5, 4, 30);
		Graph_Algo ga2 = new Graph_Algo();
		ga2.init(g2);
		assertEquals(true, ga2.isConnected());

		graph g3 = new DGraph();
		g3.addNode(new Vertex(1));
		g3.addNode(new Vertex(2));
		g3.addNode(new Vertex(3));
		g3.addNode(new Vertex(4));
		g3.addNode(new Vertex(5));
		g3.connect(1, 2, 30);
		g3.connect(2, 3, 30);
		g3.connect(3, 4, 30);
		g3.connect(4, 5, 30);
		Graph_Algo ga3 = new Graph_Algo();
		ga3.init(g3);
		assertEquals(false, ga3.isConnected());

	}

	//@Test
	void testShortestPath() {
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.addNode(new Vertex(3));
		s.addNode(new Vertex(4));
		s.addNode(new Vertex(5));
		s.addNode(new Vertex(6));
		s.addNode(new Vertex(7));
		s.addNode(new Vertex(8));
		s.connect(1, 2, 1);
		s.connect(1, 3, 7);
		s.connect(1, 4, 4);
		s.connect(2, 3, 2);
		s.connect(3, 4, 4);
		s.connect(3, 5, 3);
		s.connect(4, 5, 5);
		s.connect(5, 6, 4);
		s.connect(5, 7, 13);
		s.connect(6, 7, 8);
		s.connect(6, 8, 10);
		s.connect(7, 8, 3);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		List<node_data> ans = e.shortestPath(1, 8);
		System.out.println(ans.toString());
	}
	@Test
	void testShortestPathDist() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.addNode(new Vertex(3));
		s.addNode(new Vertex(4));
		s.addNode(new Vertex(5));
		s.addNode(new Vertex(6));
		s.addNode(new Vertex(7));
		s.addNode(new Vertex(8));
		s.connect(1, 2, 1);
		s.connect(1, 3, 7);
		s.connect(1, 4, 4);
		s.connect(2, 3, 2);
		s.connect(3, 4, 4);
		s.connect(3, 5, 3);
		s.connect(4, 5, 5);
		s.connect(5, 6, 4);
		s.connect(5, 7, 13);
		s.connect(6, 7, 8);
		s.connect(6, 8, 10);
		s.connect(7, 8, 3);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		//System.out.println(e.shortestPathDist(1, 8)+"");
		assertEquals(20.0, e.shortestPathDist(1, 8),0.001);
	}
	@Test
	void testShortestPathDist2() 
	{
		// the graph is not strongly connected
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.connect(1, 2, 5);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		System.out.println("distance: "+e.shortestPathDist(2, 1));
	}
	@Test
	void testSave() 
	{
		Graph_Algo a = new Graph_Algo();
		a.save("Gamlol.txt");
		Graph_Algo b = new Graph_Algo();
		b.init("Gamlol.txt");
		double a1_to_6 = a.shortestPathDist(1, 6);
		double b1_to_6 = b.shortestPathDist(1, 6);
		assertEquals(a1_to_6, b1_to_6,0.001);
	}

}
