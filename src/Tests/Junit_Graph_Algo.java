package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;
import dataStructure.*;
import utils.Point3D;

class Junit_Graph_Algo
{

	Graph_Algo graph = new Graph_Algo();
	@BeforeEach
	void setup()
	{
		graph.init(createGraphBefore());
	}
	@Test
	void testInitString() 
	{
		graph.init("Ex2.txt");
		System.out.println();
	}

	@Test
	void testIsConnected_1() 
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

		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(false, e.isConnected());
	}


	@Test
	void testIsConnected_2() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.addNode(new Vertex(3));
		s.connect(1, 2, 0);
		s.connect(3, 2, 0);
		s.connect(1, 3, 0);
		s.connect(2, 1, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(true, e.isConnected());
	}

	@Test
	void testIsConnected_3() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.addNode(new Vertex(3));
		s.connect(1, 2, 0);
		s.connect(2, 3, 0);
		s.connect(3, 1, 0);
		//s.connect(2, 1, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(true, e.isConnected());
	}

	@Test
	void testIsConnected_4() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.addNode(new Vertex(3));
		s.connect(1, 2, 0);
		s.connect(3, 2, 0);
		s.connect(1, 3, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(false, e.isConnected());
	}

	@Test
	void testIsConnected_5() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.connect(1, 2, 0);
		s.connect(2, 1, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(true, e.isConnected());
	}

	@Test
	void testIsConnected_6() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.connect(1, 2, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(false, e.isConnected());
	}


	@Test
	void testIsConnected_7() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.connect(1, 2, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(false, e.isConnected());
	}


	@Test
	void testIsConnected_8() 
	{
		graph Graph_t = new DGraph();
		Graph_t.addNode(new Vertex(1));
		Graph_t.addNode(new Vertex(2));
		Graph_t.addNode(new Vertex(3));
		Graph_t.addNode(new Vertex(4));
		Graph_t.addNode(new Vertex(5));
		Graph_t.addNode(new Vertex(6));
		Graph_t.addNode(new Vertex(7));
		Graph_t.connect(1, 2, 0);
		Graph_t.connect(1, 3, 0);
		Graph_t.connect(2, 4, 0);
		Graph_t.connect(2, 5, 0);
		Graph_t.connect(3, 2, 0);
		Graph_t.connect(4, 6, 0);
		Graph_t.connect(4, 5, 0);
		Graph_t.connect(5, 6, 0);
		Graph_t.connect(5, 7, 0);
		Graph_t.connect(6, 7, 0);
		Graph_t.connect(7, 1, 0);

		Graph_Algo Graph2 = new Graph_Algo();
		Graph2.init(Graph_t);
		assertEquals(true, Graph2.isConnected());

		graph graph_b = new DGraph();
		graph_b.addNode(new Vertex(1));
		graph_b.addNode(new Vertex(2));
		graph_b.addNode(new Vertex(3));
		graph_b.addNode(new Vertex(4));
		graph_b.addNode(new Vertex(5));
		graph_b.connect(1, 5, 0);
		graph_b.connect(5, 1, 0);
		graph_b.connect(2, 5, 0);
		graph_b.connect(5, 2, 0);
		graph_b.connect(3, 5, 0);
		graph_b.connect(5, 3, 0);
		graph_b.connect(4, 5, 0);
		graph_b.connect(5, 4, 0);
		Graph_Algo graph_c = new Graph_Algo();
		graph_c.init(graph_b);
		assertEquals(true, graph_c.isConnected());

		graph G = new DGraph();
		G.addNode(new Vertex(1));
		G.addNode(new Vertex(2));
		G.addNode(new Vertex(3));
		G.addNode(new Vertex(4));
		G.addNode(new Vertex(5));
		G.connect(1, 2, 0);
		G.connect(2, 3, 0);
		G.connect(3, 4, 0);
		G.connect(4, 5, 0);
		Graph_Algo G_other = new Graph_Algo();
		G_other.init(G);
		assertEquals(false, G_other.isConnected());


	}


	@Test                     
	void testSave()
	{
		Graph_Algo graph = new Graph_Algo();
		graph.save("Ex2.txt");
		Graph_Algo graph_other = new Graph_Algo();
		graph_other.init("Ex2.txt");
		double graph_dis = graph.shortestPathDist(0, 4);
		double graph_other_dis = graph_other.shortestPathDist(0, 4);
		assertEquals(graph_dis, graph_other_dis,0.01);
	}

	@Test
	void testShortestPath_1() {
		graph graph_sp = new DGraph();
		graph_sp.addNode(new Vertex(1));
		graph_sp.addNode(new Vertex(2));
		graph_sp.addNode(new Vertex(3));
		graph_sp.addNode(new Vertex(4));
		graph_sp.addNode(new Vertex(5));
		graph_sp.addNode(new Vertex(6));
		graph_sp.addNode(new Vertex(7));
		graph_sp.addNode(new Vertex(8));
		graph_sp.connect(1, 2, 1);
		graph_sp.connect(1, 3, 7);
		graph_sp.connect(1, 4, 4);
		graph_sp.connect(2, 3, 2);
		graph_sp.connect(3, 4, 4);
		graph_sp.connect(3, 5, 3);
		graph_sp.connect(4, 5, 5);
		graph_sp.connect(5, 6, 4);
		graph_sp.connect(5, 7, 13);
		graph_sp.connect(6, 7, 8);
		graph_sp.connect(6, 8, 10);
		graph_sp.connect(7, 8, 3);
		Graph_Algo graph2 = new Graph_Algo();
		graph2.init(graph_sp);
		List<node_data> ans = graph2.shortestPath(1, 8);
		System.out.println(ans.toString());
	}
	@Test
	void testShortestPathDist_1() 
	{
		graph graph_dis = new DGraph();
		graph_dis.addNode(new Vertex(1));
		graph_dis.addNode(new Vertex(2));
		graph_dis.addNode(new Vertex(3));
		graph_dis.addNode(new Vertex(4));
		graph_dis.addNode(new Vertex(5));
		graph_dis.addNode(new Vertex(6));
		graph_dis.addNode(new Vertex(7));
		graph_dis.addNode(new Vertex(8));
		graph_dis.connect(1, 2, 1);
		graph_dis.connect(1, 3, 7);
		graph_dis.connect(1, 4, 4);
		graph_dis.connect(2, 3, 2);
		graph_dis.connect(3, 4, 4);
		graph_dis.connect(3, 5, 3);
		graph_dis.connect(4, 5, 5);
		graph_dis.connect(5, 6, 4);
		graph_dis.connect(5, 7, 13);
		graph_dis.connect(6, 7, 8);
		graph_dis.connect(6, 8, 10);
		graph_dis.connect(7, 8, 3);
		Graph_Algo graph2 = new Graph_Algo();
		graph2.init(graph_dis);
		
		assertEquals(20.0, graph2.shortestPathDist(1, 8),0.01);
	}
	@Test
	void testShortestPathDist_2() 
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






	@Test						// worked, changing and check if still good - seems fine
	void testShortestPathDist_3() {
		graph My_Graph = new DGraph();
		My_Graph.addNode(new Vertex(1));
		My_Graph.addNode(new Vertex(2));
		My_Graph.addNode(new Vertex(3));
		My_Graph.addNode(new Vertex(4));
		My_Graph.addNode(new Vertex(5));
		My_Graph.addNode(new Vertex(6));
		My_Graph.addNode(new Vertex(7));
		My_Graph.addNode(new Vertex(8));
		My_Graph.connect(1, 2, 1);
		My_Graph.connect(1, 3, 7);
		My_Graph.connect(1, 4, 4);
		My_Graph.connect(2, 3, 2);
		My_Graph.connect(3, 4, 4);
		My_Graph.connect(3, 5, 3);
		My_Graph.connect(4, 5, 5);
		My_Graph.connect(5, 6, 4);
		My_Graph.connect(5, 7, 13);
		My_Graph.connect(6, 7, 8);
		My_Graph.connect(6, 8, 10);
		My_Graph.connect(7, 8, 3);
		Graph_Algo G_algo = new Graph_Algo();
		G_algo.init(My_Graph);
		assertEquals(20.0, G_algo.shortestPathDist(1, 8),0.001);  
	}

	@Test
	void testShortestPath_2() {				
		graph graph1 = new DGraph();
		graph1.addNode(new Vertex(1));
		graph1.addNode(new Vertex(2));
		graph1.addNode(new Vertex(3));
		graph1.addNode(new Vertex(4));
		graph1.addNode(new Vertex(5));
		graph1.addNode(new Vertex(6));
		graph1.addNode(new Vertex(7));
		graph1.addNode(new Vertex(8));
		graph1.connect(1, 2, 1);
		graph1.connect(1, 3, 7);
		graph1.connect(1, 4, 4);
		graph1.connect(2, 3, 2);
		graph1.connect(3, 4, 4);
		graph1.connect(3, 5, 3);
		graph1.connect(4, 5, 5);
		graph1.connect(5, 6, 4);
		graph1.connect(5, 7, 13);
		graph1.connect(6, 7, 8);
		graph1.connect(6, 8, 10);
		graph1.connect(7, 8, 3);
		Graph_Algo e = new Graph_Algo();
		e.init(graph1);
		List<node_data> ans = e.shortestPath(1, 8);
		//System.out.println(ans.toString());
	}

	@Test
	void testTSP() {                                
		graph graph_Test = new DGraph();
		graph_Test.addNode(new Vertex(1));
		graph_Test.addNode(new Vertex(2));
		graph_Test.addNode(new Vertex(3));
		graph_Test.addNode(new Vertex(4));
		graph_Test.addNode(new Vertex(5));
		graph_Test.addNode(new Vertex(6));
		graph_Test.addNode(new Vertex(7));
		graph_Test.addNode(new Vertex(8));
		graph_Test.connect(1, 2, 1);
		graph_Test.connect(1, 3, 7);
		graph_Test.connect(3, 1, 7);
		graph_Test.connect(1, 4, 4);
		graph_Test.connect(2, 3, 2);
		graph_Test.connect(3, 4, 4);
		graph_Test.connect(3, 5, 3);
		graph_Test.connect(4, 5, 5);
		graph_Test.connect(5, 6, 4);
		graph_Test.connect(5, 7, 13);
		graph_Test.connect(6, 7, 8);
		graph_Test.connect(6, 8, 10);
		graph_Test.connect(7, 8, 3);
		Graph_Algo Other_Graph = new Graph_Algo();
		Other_Graph.init(graph_Test);

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(6);
		list.add(8);


		List<node_data> ans2 = Other_Graph.TSP(list);
		List<Integer> ans = new ArrayList<Integer>();
		ans.add(1);
		ans.add(2);
		ans.add(3);
		ans.add(5);
		ans.add(6);
		ans.add(7);
		ans.add(8);
		for (int i = 0; i < ans2.size(); i++) {
			if(ans2.get(i).getKey() != ans.get(i))
			{
				fail("Should not fail");
			}
		}
		
	}

	@Test   		
	void testCopy() {
		graph graph_a = new DGraph();
		graph_a.addNode(new Vertex(1));
		graph_a.addNode(new Vertex(2));
		graph_a.addNode(new Vertex(3));
		graph_a.addNode(new Vertex(4));
		graph_a.addNode(new Vertex(5));
		graph_a.addNode(new Vertex(6));
		graph_a.addNode(new Vertex(7));
		graph_a.addNode(new Vertex(8));
		graph_a.connect(1, 2, 1);
		graph_a.connect(1, 3, 7);
		graph_a.connect(1, 4, 4);
		graph_a.connect(2, 3, 2);
		graph_a.connect(3, 4, 4);
		graph_a.connect(3, 5, 3);
		graph_a.connect(4, 5, 5);
		graph_a.connect(5, 6, 4);
		graph_a.connect(5, 7, 13);
		graph_a.connect(6, 7, 8);
		graph_a.connect(6, 8, 10);
		graph_a.connect(7, 8, 3);
		Graph_Algo e = new Graph_Algo();
		e.init(graph_a);

		graph n = e.copy();
		graph_a.getNode(1).setWeight(30);
		if(n.getNode(1).getWeight() == graph_a.getNode(1).getWeight())
		{
			fail("Should be different");
		}
	}
	@Test
	void test()
	{
		graph graph_test = graphFactory();

	}

	graph createGraphBefore()
	{
		graph graph_test = new DGraph();
		graph_test.addNode(new Vertex(1, new Point3D(50 ,20), 30));
		graph_test.addNode(new Vertex(2));
		graph_test.addNode(new Vertex(3));
		graph_test.addNode(new Vertex(4));
		graph_test.addNode(new Vertex(5));
		graph_test.addNode(new Vertex(6));
		graph_test.connect(1, 2, 12);
		graph_test.connect(2, 3, 11);
		graph_test.connect(3, 7, 4);
		graph_test.connect(4, 6, 8);
		graph_test.connect(5, 3,8);
		return graph_test;

	}
	graph graphFactory()
	{
		graph g_fact = new DGraph();
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {

			Point3D p = new Point3D(rand.nextInt(400), rand.nextInt(400));
			g_fact.addNode(new Vertex(i,p));
		}
		Collection<node_data> nd = g_fact.getV();
		for (node_data node_data : nd) {
			int amount_of_edegs = rand.nextInt(20);
			for (int i = 0; i < amount_of_edegs; i++) {
				int dest = rand.nextInt(100);
				while(dest != node_data.getKey())
				{
					dest = rand.nextInt(100);
				}
				double weight = rand.nextDouble();
				weight*= 100;
				g_fact.connect(node_data.getKey(), dest, weight);
			}
		}
		return g_fact;
	}
	graph vertexs_100000()
	{
		graph g_million = new DGraph();
		Random random = new Random();
		for (int i = 0; i < 1000000; i++) {
			Point3D p = new Point3D(random.nextInt(500), random.nextInt(500));
			g_million.addNode(new Vertex(i,p));
		}
		for (int i = 0; i < 1000000-10; i++) {
			g_million.connect(i, i+1, 10);
			g_million.connect(i, i+2, 10);
			g_million.connect(i, i+3, 10);
			g_million.connect(i, i+4, 10);
			g_million.connect(i, i+5, 10);
			g_million.connect(i, i+6, 10);
			g_million.connect(i, i+7, 10);
			g_million.connect(i, i+8, 10);
			g_million.connect(i, i+9, 10);
			g_million.connect(i, i+10, 10);

		}
		g_million.connect(353996, 0, 10);
		g_million.connect(246997, 0, 10);
		g_million.connect(463788, 0, 10);
		g_million.connect(575645, 0, 10);


		return g_million;
	}







}