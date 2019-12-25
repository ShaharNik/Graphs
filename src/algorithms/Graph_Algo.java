package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	graph graph;
	@Override
	public void init(graph g) 
	{
		// TODO Auto-generated method stub
		this.graph = g;
	}

	@Override
	public void init(String file_name) 
	{
		// TODO Auto-generated method stub
		try
		{
			FileInputStream f = new FileInputStream(file_name);
			ObjectInputStream o = new ObjectInputStream(f);
			//o.writeObject(this.graph);
			Graph_Algo GA = (Graph_Algo)o.readObject();
			this.graph = GA.graph;
			//this.graph = (graph)o.readObject();
			o.close();
			f.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(String file_name) 
	{
		// TODO Auto-generated method stub
		try
		{
			FileOutputStream f = new FileOutputStream(file_name);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(this.graph);
			o.close();
			f.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int Count_Vertex_Stepped_BFS(graph g, node_data s)
	{
		// BFS
		//Collection<node_data> Verts = g.getV();

		// Mark all the vertices as not visited
		//cleanTags();
		ResetTags(g);

		// Create a queue for BFS
		LinkedList<node_data> queue = new LinkedList<node_data>(); // maybe should use stack for reverse running?


		// Mark the current node as visited and enqueue it
		s.setTag(1);
		queue.add(s);

		int count_vertex_reached = 0;
		while(queue.size() != 0)
		{
			// Dequeue a vertex from queue and print it 
			s = queue.poll(); 

			// Get all adjacent vertices of the dequeued vertex s 
			// If a adjacent has not been visited, then mark it 
			s.setTag(1);
			Collection<edge_data> edge = g.getE(s.getKey()); // get all adjacent of s
			for (edge_data adj : edge) 
			{
				if(adj.getTag() == 0)
				{
					adj.setTag(1);
					count_vertex_reached++;
					queue.add(g.getNode(adj.getDest()));
				}
			}
		}
		return count_vertex_reached;
	}
	@Override
	public boolean isConnected() 
	{
		// TODO Auto-generated method stub
		
		// Get Node s to start BFS from him
		Collection<node_data> Verts = graph.getV();
		node_data FirstNode = Verts.iterator().next();
		
		if (Count_Vertex_Stepped_BFS(graph, FirstNode) != graph.getV().size() +1) // if we haven't reached all the vertex from vertex s
			return false;
		
		graph ReversedGraph = copy(); // create a copy of the graph.
		Reversed(ReversedGraph); // reverse it
		//ResetTags(ReversedGraph); // in bfs func
		int a = Count_Vertex_Stepped_BFS(ReversedGraph, FirstNode);
		int b = graph.getV().size() + 1;
		if (a != b) // if we haven't reached all the vertex from vertex s
			return false;

		return true;
	}
	/**
	 * Resets all tags to be 0.
	 * @param graph g
	 */
	private static void ResetTags(graph g) 
	{
		for(node_data v : g.getV()) 
		{
			v.setTag(0);
		}
	}
	private void cleanTags()
	{
		Collection<node_data> Verts = graph.getV();
		for (node_data v : Verts)
		{
			v.setTag(0);
		}
		//PriorityQueue(Collection<E> c)
	}
	/**
	 * Reverse a graph.turns over all the edges to be b-->a instead of a-->b 
	 *
	 * @param g-graph
	 * @return the same graph reversed
	 */
	private static graph Reversed(graph g)
	{
		
		ArrayList<edge_data> edgesToRemove = new ArrayList<edge_data>();
		//graph new_g;
		for(node_data n:g.getV()) 
		{
			Collection<edge_data> edgesOfV = g.getE(n.getKey());
			Iterator<edge_data> iter = edgesOfV.iterator();
			
			while(iter.hasNext())
			{
				edge_data temp = iter.next();
				
				int curEdest = temp.getDest();
				int curEsrc = temp.getSrc();
				g.connect(curEdest,curEsrc,temp.getWeight());
				edgesToRemove.add(temp);
				
			}
			for (edge_data edge_data2 : edgesToRemove) {
				g.removeEdge(edge_data2.getSrc(), edge_data2.getDest());
			}
			/*
			for(edge_data e: g.getE(n.getKey()))
			{
				//int revSrc = e.getSrc();
				//int revDest = e.getDest();
				g.connect(e.getDest(),e.getSrc(),e.getWeight());
				g.removeEdge(e.getSrc(),e.getDest());
			}
			*/
		}
		return g;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void Dikstra(graph g)
	{
		LinkedList<node_data> VertexQueue = new LinkedList<>();
		int v_Reached = 0;
		double distance = 0;
		while (v_Reached < g.getV().size())
		{
			for(node_data n:g.getV())
			{
				for(edge_data e: g.getE(n.getKey()))
				{
					
				}
			}
			
			v_Reached++;
		}
	}
	private void setTagsAndWeight()
	{
//		for(node_data n:graph.getV()) 
//		{
//			n.setTag(0);
//			for(edge_data e: graph.getE(n.getKey()))
//			{
//				e.setWeight = Integer.MAX_VALUE; // ?
//			}
//		}
		Collection<node_data> c_node_data = graph.getV();
		for (node_data node_data : c_node_data) {
			node_data.setTag(0);
			node_data.setWeight(Integer.MAX_VALUE);
			node_data.setInfo("");
		}
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		// TODO Auto-generated method stub


		return null;
	}

	@Override
	public graph copy() 
	{
		// TODO Auto-generated method stub
		graph new_graph = new DGraph();
		Collection<node_data> nodes = graph.getV();
		for (node_data vert : nodes) 
		{
			new_graph.addNode(new Vertex(vert));
			Collection<edge_data> edges = graph.getE(vert.getKey());
			for (edge_data edge : edges) 
			{
				new_graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
			}
		}
		return new_graph;
	}

}
