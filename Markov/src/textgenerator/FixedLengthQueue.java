package textgenerator;

import java.util.LinkedList;
//Code based off of post by GreyCat on StackOverflow
public class FixedLengthQueue<E> extends LinkedList<E>
{
	private int limit;

	public FixedLengthQueue(int limit)
	{
		this.limit = limit;
	}

	@Override
	public boolean add(E o)
	{
		super.add(o);
		while (size() > limit){super.remove();}
		return true;
	}
}