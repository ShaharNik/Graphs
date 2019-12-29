package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.graph;
import utils.Point3D;

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
		e.init(s);
		System.out.println(e.isConnected());
	}
	static void isConnected2() 
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
		System.out.println("2) need true and get: "+e.isConnected());
	}
	static void isConnected3() 
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
		System.out.println("3) need true and get: "+e.isConnected());
	}
	static void isConnected4() 
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
		System.out.println("4) need false and get: "+e.isConnected());
	}
	static void isConnected5() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.connect(1, 2, 0);
		s.connect(2, 1, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		System.out.println("5) need true and get: "+e.isConnected());
	}
	static void isConnected6() 
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1));
		s.addNode(new Vertex(2));
		s.connect(1, 2, 0);
		Graph_Algo e = new Graph_Algo();
		e.init(s);
		System.out.println("6) need false and get: "+e.isConnected());
	}
	static void isConnected7()
	{
		graph s = new DGraph();
		s.addNode(new Vertex(1,new Point3D(100,100)));
		s.addNode(new Vertex(2,new Point3D(50,300)));
		s.addNode(new Vertex(3,new Point3D(400,150)));
		s.addNode(new Vertex(4,new Point3D(88,76)));
		s.addNode(new Vertex(5,new Point3D(566,444)));
		s.addNode(new Vertex(6,new Point3D(45,78)));
		s.addNode(new Vertex(7,new Point3D(203,567)));
		s.connect(1, 2, 0);
		s.connect(3, 1, 0);
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
		System.out.println("7) need false and get: "+e.isConnected());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//isConnected();
		isConnected2();
		isConnected3();
		isConnected4();
		isConnected5();
		isConnected6();
		isConnected7();
	}

}
