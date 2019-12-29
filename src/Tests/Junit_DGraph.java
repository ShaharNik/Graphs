package Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Point3D;



class Junit_DGraph
{

	private DGraph graph;
	
	@BeforeEach
	void setup()
	{
		graph = createGraphBefore();
	}
	
	@Test
	
	void test_1_AddNode()               // my new test for addNode (1/2)
	{
	
		Vertex v1 = new Vertex(6);
		Vertex v2 = new Vertex(4);
		Vertex v3 = new Vertex(2);
		graph g = new DGraph();
		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		if (g.nodeSize()!=3)
		{ 
			fail();
		}
	}
	
	@Test
	void testgetNode() {            // רוצה לשנות קודקודים ולברר בדיבאג איזה אם.סי יוצא כדי לשנות בפונ' למטה 
		node_data one = graph.getNode(1);
		node_data two = graph.getNode(3);
		node_data three = graph.getNode(4);
		node_data four = graph.getNode(7);  // ניסיתי להוסיף קודקוד אבל אז הפונ' לא עבדה משום מה. כדאי שיהיו לנו פה שישה קוד' לדעתי
		assertEquals(1, one.getKey());
		assertEquals(3, two.getKey());
		assertEquals(4, three.getKey());
		if(four!=null)
		{
			fail("four need to be null");
		}
	}
	@Test
	void testRemoveNode()    //  fail, after it will work - Ill change data
	{
		assertEquals(5, graph.edgeSize());
		graph.connect(6, 1, 20);
		graph.connect(5, 1, 20);
		graph.connect(4, 1, 4);
		graph.connect(3, 1, 8);
		graph.connect(2, 1,8);
		graph.connect(1, 3, 20);
		graph.connect(1, 4, 4);
		graph.connect(1, 5, 8);
		graph.connect(1, 6,8);
		assertEquals(14, graph.edgeSize());
		graph.removeNode(1);
		assertEquals(4, graph.edgeSize());
		graph.removeNode(4);
		assertEquals(2, graph.edgeSize());
	}
	@Test
	void test_2_AddNode()         // work , 
	{
		assertEquals(6, graph.getV().size());
		graph.addNode(new Vertex(7,new Point3D(12,6)));
		assertEquals(7, graph.getV().size());
		assertEquals(7, graph.getNode(7).getKey());
	}
	@Test
	void removeEdge()					// fail, but I changed names
	{
		Graph_Algo graph_one = new Graph_Algo();
		graph_one.init(graph);
		double ans = graph_one.shortestPathDist(1, 6);
		if(ans == Integer.MAX_VALUE)
		{
			fail("Should'nt be MAX Value");
		}
		graph.removeEdge(3, 4);
		ans = graph_one.shortestPathDist(1, 6);
		if(ans != Integer.MAX_VALUE)
		{
			fail("There should'nt be any path to 6 therefor should be MAX VALUE"); // need to change a bit while it work
		}
	}
	@Test
	void testMC()
	{
		assertEquals(11, graph.getMC());
		graph.addNode(new Vertex(7));
		graph.addNode(new Vertex(8));
		graph.addNode(new Vertex(9));
		graph.connect(7, 8, 10);
		assertEquals(15, graph.getMC());
		graph.removeEdge(7, 8);
		assertEquals(16, graph.getMC());
	}
	DGraph createGraphBefore()
	{
		DGraph graph = new DGraph();
		graph.addNode(new Vertex(1, new Point3D(250 ,120)));
		graph.addNode(new Vertex(2, new Point3D(20,140)));
		graph.addNode(new Vertex(3,new Point3D(190,10)));
		graph.addNode(new Vertex(4,new Point3D(130,210)));
		graph.addNode(new Vertex(5,new Point3D(340,50)));
		graph.addNode(new Vertex(6,new Point3D(200,400)));
		graph.connect(1, 2, 20);
		graph.connect(2, 3, 20);
		graph.connect(3, 4, 4);
		graph.connect(4, 5, 8);
		graph.connect(5, 6,8);
		return graph;
	}
}