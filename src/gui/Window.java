package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class Window extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3074823522732093831L;
	//LinkedList<Point3D> points = new LinkedList<Point3D>();
	graph _graph;

	//graph_algorithms g = new Graph_Algo();

	public Window()
	{
		this._graph = new DGraph();
		initGUI();
	}

	public Window(graph g) {
		// TODO Auto-generated constructor stub
		this._graph = g;
		initGUI();
	}

	private void initGUI() //Bottoms
	{
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("Init Graph From File");
		item1.addActionListener(this);

		MenuItem item2 = new MenuItem("Save Graph As textFile");
		item2.addActionListener(this);

		menu.add(item1);
		menu.add(item2);

		//this.addMouseListener(this);
		Menu menu2 = new Menu("Algo");
		menuBar.add(menu2);
		this.setMenuBar(menuBar);
		MenuItem item3 = new MenuItem("isConnected");
		item3.addActionListener(this);

		MenuItem item4 = new MenuItem("Shortest Path Dist");
		item4.addActionListener(this);
		
		MenuItem item5 = new MenuItem("Shortest Path");
		item5.addActionListener(this);

		MenuItem item6 = new MenuItem("TSP");
		item6.addActionListener(this);


		menu2.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menu2.add(item6);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		if (_graph != null)
		{
			Collection<node_data> points = _graph.getV();
			for(node_data v : points)
			{
				g.setColor(Color.BLUE);
				g.fillOval((int)v.getLocation().x(), (int)v.getLocation().y(), 10, 10);
				g.drawString(String.valueOf(v.getKey()),(int)v.getLocation().x(), (int)v.getLocation().y());
				Collection<edge_data> edgesOfVertex = _graph.getE(v.getKey());
				for (edge_data edge : edgesOfVertex) 
				{
					if(edge.getTag() == 555)
					{
						edge.setTag(0);
						g.setColor(Color.GREEN);
					}
					else
					{
						g.setColor(Color.RED);
					}
					g.drawLine((int)v.getLocation().x(), (int)v.getLocation().y(), (int)_graph.getNode(edge.getDest()).getLocation().x(), (int)_graph.getNode(edge.getDest()).getLocation().y());
					g.setColor(Color.YELLOW);
					double xHalf = (v.getLocation().x() + _graph.getNode(edge.getDest()).getLocation().x()) /2;
					double yHalf = (v.getLocation().y() + _graph.getNode(edge.getDest()).getLocation().y()) /2;
					double halfOfHalf_X = (xHalf + _graph.getNode(edge.getDest()).getLocation().x()) / 2;
					double halfOfHalf_Y = (yHalf + _graph.getNode(edge.getDest()).getLocation().y()) / 2;
					g.fillOval((int)halfOfHalf_X, (int)halfOfHalf_Y, 8, 8);
					g.setColor(Color.MAGENTA);
					g.drawString(edge.getWeight()+"", (int)xHalf, (int)yHalf);
				}
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String Option = e.getActionCommand();
		switch (Option)
		{
		case "Init Graph From File": initGraph(); // NEED TO CHANGE FOR GRAPH FROM FILE
		break;
		case "Save Graph As textFile": saveToFile();
		break;
		case "isConnected": isConnected();
		break;
		case "Shortest Path Dist": shortestPathDist();
		break;
		case "Shortest Path": shortestPath();
		break;
		case "TSP": TSP();
		break;
		}

	}
	private void isConnected()
	{
		JFrame jinput = new JFrame();
		if (_graph.getV().size() == 0 || this._graph == null)
			JOptionPane.showMessageDialog(jinput, "Init a graph first");
		else
		{
			Graph_Algo ga = new Graph_Algo();
			ga.init(_graph);

			if (ga.isConnected())
				JOptionPane.showMessageDialog(jinput, "The Graph is Strongly Connected");
			else
				JOptionPane.showMessageDialog(jinput, "The Graph is NOT Strongly Connected");
		}
	}
	/**
	 * Save the current graph into a deserializable file.
	 */
	private void saveToFile()
	{
		JFrame jinput = new JFrame();
		if (_graph.getV().size() == 0 || this._graph == null)
			JOptionPane.showMessageDialog(jinput, "The graph is empty, there is nothing to save");
		else
		{
			Graph_Algo ga = new Graph_Algo();
			ga.init(this._graph);
			JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jf.setDialogTitle("Choose a directory to save your file: ");

			jf.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
			jf.addChoosableFileFilter(filter);

			int returnV = jf.showOpenDialog(null);
			if (returnV == JFileChooser.APPROVE_OPTION) 
			{
				try 
				{
					ga.save(jf.getSelectedFile()+".txt");
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
		}
	}
	private void initGraph()
	{
		Graph_Algo ga = new Graph_Algo();
		JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		jf.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		jf.addChoosableFileFilter(filter);

		int returnV = jf.showOpenDialog(null);
		if (returnV == JFileChooser.APPROVE_OPTION) 
		{
			File selectedFile = jf.getSelectedFile();
			ga.init(selectedFile.getAbsolutePath());
			this._graph = ga.copy();
			repaint();
		}
	}
	private void makeGraph()
	{
		this._graph.addNode(new Vertex(1,new Point3D(100,100), 8.4));
		this._graph.addNode(new Vertex(2,new Point3D(50,300), 20));
		this._graph.addNode(new Vertex(3,new Point3D(400,150), 30));
		this._graph.addNode(new Vertex(4,new Point3D(88,76), 55));
		this._graph.addNode(new Vertex(5,new Point3D(566,444), 7.8));
		this._graph.addNode(new Vertex(6,new Point3D(45,78), 60));
		this._graph.addNode(new Vertex(7,new Point3D(203,567), 35));
		_graph.connect(1, 2, 5);
		_graph.connect(3, 1, 6);
		_graph.connect(1, 3, 3);
		_graph.connect(2, 4, 4);
		_graph.connect(2, 5, 1);
		_graph.connect(3, 2, 2);
		_graph.connect(4, 6, 8);
		_graph.connect(4, 5, 9);
		_graph.connect(5, 6, 4);
		_graph.connect(5, 7, 5);
		_graph.connect(6, 7, 5);
		_graph.connect(7, 2, 5);
		repaint();
	}
	private void shortestPathDist()
	{
		JFrame jinput = new JFrame();
		if (_graph.getV().size() == 0 || this._graph == null)
			JOptionPane.showMessageDialog(jinput, "The graph is empty, there is nothing to save");

		String src_key = JOptionPane.showInputDialog(jinput,"Enter Src Vertex Key");		
		String dest_key = JOptionPane.showInputDialog(jinput,"Enter Destination Vertex Key");
		try
		{
			int src = Integer.parseInt(src_key);
			int dest = Integer.parseInt(dest_key);
			Graph_Algo ga = new Graph_Algo();
			ga.init(this._graph);
			double dis = ga.shortestPathDist(src, dest);
			JOptionPane.showMessageDialog(jinput, "The shortest distance between them is:" + dis);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	private void shortestPath()
	{
		JFrame jinput = new JFrame();
		String src_key = JOptionPane.showInputDialog(jinput,"Enter From");		
		String dest_key = JOptionPane.showInputDialog(jinput,"Enter To");
		try
		{
			int src = Integer.parseInt(src_key);
			int dest = Integer.parseInt(dest_key);
			Graph_Algo ga = new Graph_Algo();
			ga.init(_graph);
			List<node_data> ans = ga.shortestPath(src, dest);
			for (int j = 0; j < ans.size()-1; j++) 
			{
				_graph.getEdge(ans.get(j).getKey(), ans.get(j+1).getKey()).setTag(555);
			}
			repaint();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void TSP()
	{
		JFrame jinput = new JFrame();
		if (_graph.getV().size() == 0 || this._graph == null)
			JOptionPane.showMessageDialog(jinput, "The graph is empty");
		else
		{
			String amount = JOptionPane.showInputDialog(jinput, "How many targets?");
			ArrayList<Integer> arrayTSP = new ArrayList<Integer>();
			for (int i = 0; i < Integer.parseInt(amount); i++) 
			{
				try
				{
					String x = JOptionPane.showInputDialog(jinput, "Enter The "+i+"'th Target Node");
					arrayTSP.add(Integer.parseInt(x));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			Graph_Algo ga = new Graph_Algo();
			ga.init(this._graph);
			List<node_data> ansTSP = ga.TSP(arrayTSP); // problem
			for (int j = 0;j < ansTSP.size()-1; j++) 
			{
				//JOptionPane.showMessageDialog(jinput, "Node index: "+ansTSP.get(j).getKey());
				this._graph.getEdge(ansTSP.get(j).getKey(), ansTSP.get(j+1).getKey()).setTag(555);
			}
			repaint();

		}
	}

}
