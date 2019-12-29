package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class Window extends JFrame implements ActionListener
{
	//LinkedList<Point3D> points = new LinkedList<Point3D>();
	graph _graph;

	//graph_algorithms g = new Graph_Algo();

	public Window()
	{
		this._graph = new DGraph();
		initGUI();
	}

	private void initGUI() //Bottoms
	{
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		MenuItem item4 = new MenuItem("Shortest Path");
		item4.addActionListener(this);

		MenuItem item5 = new MenuItem("TSP");
		item5.addActionListener(this);


		menu2.add(item3);
		menu2.add(item4);
		menu2.add(item5);
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
					g.setColor(Color.RED);
					g.drawLine((int)v.getLocation().x(), (int)v.getLocation().y(), (int)_graph.getNode(edge.getDest()).getLocation().x(), (int)_graph.getNode(edge.getDest()).getLocation().y());
					g.setColor(Color.YELLOW);
					double xHalf = (v.getLocation().x() + _graph.getNode(edge.getDest()).getLocation().x()) /2;
					double yHalf = (v.getLocation().y() + _graph.getNode(edge.getDest()).getLocation().y()) /2;
					double halfOfHalf_X = (xHalf + _graph.getNode(edge.getDest()).getLocation().x()) / 2;
					double halfOfHalf_Y = (yHalf + _graph.getNode(edge.getDest()).getLocation().y()) / 2;
					g.fillOval((int)halfOfHalf_X, (int)halfOfHalf_Y, 8, 8);
					g.setColor(Color.MAGENTA);
					g.drawString(String.valueOf(v.getWeight()), (int)xHalf,(int)yHalf);
				}
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//graph_algorithms g = new Graph_Algo(); 
		//DGraph Dgraph = new graph_algorithms();
		// problem need graph interface and graph_algo interface, how to combine?
		String Option = e.getActionCommand();
		switch (Option)
		{
		case "Init Graph From File": initGraph();
		break;
		case "Save Graph As textFile": saveToFile();
		break;
		case "isConnected": isConnected();
		break;
		case "Shortest Path": shortestPath();
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
		this._graph.addNode(new Vertex(1,new Point3D(100,100)));
		this._graph.addNode(new Vertex(2,new Point3D(50,300)));
		this._graph.addNode(new Vertex(3,new Point3D(400,150)));
		this._graph.addNode(new Vertex(4,new Point3D(88,76)));
		this._graph.addNode(new Vertex(5,new Point3D(566,444)));
		this._graph.addNode(new Vertex(6,new Point3D(45,78)));
		this._graph.addNode(new Vertex(7,new Point3D(203,567)));
		_graph.connect(1, 2, 0);
		_graph.connect(3, 1, 0);
		_graph.connect(1, 3, 0);
		_graph.connect(2, 4, 0);
		_graph.connect(2, 5, 0);
		_graph.connect(3, 2, 0);
		_graph.connect(4, 6, 0);
		_graph.connect(4, 5, 0);
		_graph.connect(5, 6, 0);
		_graph.connect(5, 7, 0);
		_graph.connect(6, 7, 0);
		_graph.connect(7, 2, 0);
	}
	private void shortestPath()
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

}
