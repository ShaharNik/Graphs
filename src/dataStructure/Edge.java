package dataStructure;

import java.io.Serializable;

public class Edge implements edge_data, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6851959467093762061L;
	int _src;
	int _dest;
	double _weight;
	String _info;
	int _tag;
	
	public Edge(int srcVertex,int destVerex,double weight) {
		this._src = srcVertex;
		this._dest = destVerex;
		this._weight = weight;
		this._tag = 0;
		
	}
	public Edge(int srcVertex,int destVerex,double weight,int tag) {
		this._src = srcVertex;
		this._dest = destVerex;
		this._weight = weight;
		this._tag = tag;
	}
	public Edge(Edge other)
	{
		this(other._src,other._dest,other._weight,other._tag);
	}
		
	@Override
	public int getSrc() {
		// TODO Auto-generated method stub
		return this._src;
	}

	@Override
	public int getDest() {
		// TODO Auto-generated method stub
		return this._dest;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return this._weight;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return this._info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		this._info = s;
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return this._tag;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		this._tag = t;
	}

}
