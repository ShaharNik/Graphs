package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 720151264462843803L;
	graph graph;

	public Graph_Algo()
	{
		this.graph = new DGraph();
	}
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
			o.writeObject(this);
			o.close();
			f.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isConnectedNotEfficient() 
	{
		Collection<node_data> vertex = this.graph.getV();
		ArrayList<Integer> arrSrc= new ArrayList<>();
		ArrayList<Integer> arrDest= new ArrayList<>();
		if(vertex.size()==1)
			return true;
		if(vertex.size()==0)
			throw new RuntimeException("no vertex");
		for(node_data n:graph.getV()) 
		{
			for (edge_data e : graph.getE(n.getKey()))
			{
				arrSrc.add(e.getSrc());
				arrDest.add(e.getDest()); 
			}
		}
		for(node_data n:graph.getV())
		{
			if(!arrSrc.contains(n.getKey())||!arrDest.contains(n.getKey()))
				return false;
		}
		return true;
	}
	public boolean isConnected() 
	{
		//graph copy = copy();
		Collection<node_data> vertexes = graph.getV();
		for (node_data node : vertexes) 
		{
			ResetTags(graph);
			if(graph.nodeSize() > count_edges(node))
				return false;	
		}

		return true;
	}

	private int count_edges(node_data v)
	{
		if (v.getTag() == 1) 
			return 0;
		v.setTag(1);
		Collection<edge_data> edgesOfv = graph.getE(v.getKey());
		int count = 1;
		for (edge_data edge : edgesOfv)
		{
			count += count_edges(graph.getNode(edge.getDest()));
		}

		return count;	
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

	public boolean isConnectedOLD() 
	{
		// TODO Auto-generated method stub
		Collection<node_data> Verts = graph.getV();
		if(Verts.size() == 1)
			return true;
		if(Verts.size()==0)
			throw new RuntimeException("No vertex");


		// Get Node s to start BFS from him
		node_data FirstNode = Verts.iterator().next();

		int a1 = Count_Vertex_Stepped_BFS(graph, FirstNode);
		int b1 = graph.getV().size() +1;
		if (a1 != b1) // if we haven't reached all the vertex from vertex s
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
				if (curEdest == curEsrc)
					g.connect(curEdest,curEsrc,temp.getWeight());
				edgesToRemove.add(temp);

			}
			for (edge_data edge_data2 : edgesToRemove) // delete edges
			{
				g.removeEdge(edge_data2.getSrc(), edge_data2.getDest());
			}
		}
		return g;
	}
	/**
	 * Reverse a graph.turns over all the edges to be b-->a instead of a-->b 
	 *
	 * @param g-graph
	 * @return the same graph reversed
	 */

	private static graph ReversedNew(graph g)
	{

		ArrayList<edge_data> edgesToRemove = new ArrayList<edge_data>();
		//graph new_g;
		for(node_data n:g.getV()) 
		{
			//Collection<edge_data> edgesOfV = g.getE(n.getKey());
			//Iterator<edge_data> iter = edgesOfV.iterator();

			// new
			for (edge_data e : g.getE(n.getKey()))
			{
				/*
				if (g.getNode(e.getDest()). == g.getNode(e.getSrc()))
				{

				}
				 */
				if (e.getDest() == e.getSrc())
				{
					// dont remove
				}
				else
				{
					g.connect(e.getDest(),e.getSrc(),e.getWeight());
					edgesToRemove.add(e);
				}
			}
			// new
			for (edge_data edge_data2 : edgesToRemove) // delete edges
			{
				g.removeEdge(edge_data2.getSrc(), edge_data2.getDest());
			}
		}
		return g;
	}


	@Override
	public double shortestPathDist(int src, int dest) 
	{
		// TODO Auto-generated method stub

		//if (!this.isConnected()) // if the graph isn't connected
		//return Double.POSITIVE_INFINITY;
		if (src == dest)
			return 0;
		if (src < 0 || dest < 0)
			return -1;
		if (this.graph.getV().size() == 0 || this.graph == null)
			return -1;

		// Initialize distances of all vertices as infinite, and Set tags to zero.
		setTagsAndWeight();

		//Generate a new Priority Queue with Comparator of weight
		PriorityQueue<node_data> PrioQueue = new PriorityQueue<node_data>(new comperator());

		// Start saving distances using Dikstra From the src vertex
		PrioQueue.add(graph.getNode(src)); 
		// set first node weight 0 zero
		PrioQueue.peek().setWeight(0); 


		while (!PrioQueue.isEmpty()) // while not all vertexes reached
		{
			//Extract minimum distance vertex from Set. 
			node_data currNode = PrioQueue.poll();

			if (currNode.getTag() == 0) // Vertex not stepped yet
			{
				currNode.setTag(1); // mark vertex as visited
				PrioQueue.poll(); // remove from queue.
				Collection<edge_data> edges = graph.getE(currNode.getKey()); // Extract minimum distance vertex from prioQueue
				for (edge_data edge : edges) // Loop through all adjacent of u and do following for every destination vertex.	       
				{
					node_data destNode = graph.getNode(edge.getDest()); // dest vertex
					if(destNode.getWeight() > currNode.getWeight() + edge.getWeight())
					{
						destNode.setWeight(currNode.getWeight() + edge.getWeight());
						destNode.setInfo(currNode.getKey()+"");
						if(destNode.getTag() == 0)
						{
							PrioQueue.add(destNode);
						}
					}
				}
			}
			else
			{
				PrioQueue.poll();
			}
		}
		return graph.getNode(dest).getWeight();
	}


	private void setTagsAndWeight()
	{
		Collection<node_data> c_node_data = graph.getV();
		for (node_data node_data : c_node_data) 
		{
			node_data.setTag(0);
			node_data.setWeight(Integer.MAX_VALUE);
			node_data.setInfo("");
		}
	}

	/**
	 * The function starts from the destination vertex, every vertex info is the prev vertex that we came from, that's how we backtrack finding the path.
	 **/
	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{
		// TODO Auto-generated method stub
		double d = shortestPathDist(src, dest);
		List<node_data> ans = new ArrayList<node_data>();
		node_data currNode = graph.getNode(dest);
		while(!currNode.getInfo().isEmpty())
		{
			ans.add(0, currNode);
			currNode = graph.getNode(Integer.parseInt(currNode.getInfo()));
		}
		ans.add(0, currNode);
		return ans;
	}
	private boolean isTherePath(int key1, int key2) 
	{
		if(shortestPath(key1, key2) == null || shortestPath(key1, key2).size() == 0) 
			return false;

		return true;
	}
	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		// TODO Auto-generated method stub
		List <node_data> ans = new ArrayList<>();
		if (targets.size() == 0)
			return ans;
		
		if (targets.size() == 1)
		{
			ans.add(graph.getNode(targets.get(0)));
			return ans;
		}
		
		if (!isTherePath(targets.get(0), targets.get(1)))
			return null;
		
		ans.addAll(shortestPath(targets.get(0), targets.get(1)));
		
		int last_path = 1;

		for(int i = 2; i < targets.size(); i++)
		{
			if(!ans.contains(this.graph.getNode(targets.get(i))))
			{
				if (!isTherePath(targets.get(last_path), targets.get(i)))
					return null;
				ans.addAll(shortestPath(targets.get(last_path), targets.get(i)));
				
				// Update the last node we checked path from
				last_path = i;
				ans.remove(i);
			}
		}
		return ans;
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
class comperator implements Comparator<node_data>
{

	@Override
	public int compare(node_data o1, node_data o2) 
	{
		// TODO Auto-generated method stub
		return (int)(o1.getWeight() - o2.getWeight());
	}

}


