package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle
{
	private int innerRadius;
	
	public int getInnerRadius()
	{
		return innerRadius;
	}
	
	public void setInnerRadius(int innerRadius)
	{
		if(innerRadius > 0)
		{
			this.innerRadius = innerRadius;
		}
		else
		{
			throw new NumberFormatException("Width has to be greater value than 0!");
		}
	}

	public double area()
	{
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public Donut()
	{
		
	}
	
	public Donut(Point center, int radius, int innerRadius)
	{
		super(center, radius);
		if(radius > innerRadius)
		{
			setInnerRadius(innerRadius);
		}
		else
		{
			throw new NumberFormatException("Radius has to be greater value than innerRadius!");
		}
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected)
	{
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public String toString()
	{
		return super.toString() + ", innerRadius = " + innerRadius;
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof Donut)
		{
			Donut pom = (Donut) obj;
			if (this.innerRadius == pom.innerRadius &&
				this.getCenter().equals(pom.getCenter()) &&
				this.getRadius() == pom.getRadius())
			{
				return true;
			} 
			else
			{
				return false;
			}
		} 
		else
		{
			return false;
		}
	}
	
	public boolean contains(int x, int y)
	{
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p)
	{
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	public void draw(Graphics g)
	{
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		this.fill(g);
	}
	
	public void fill(Graphics g)
	{
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.white);
		g.fillOval(this.getCenter().getX() - this.innerRadius + 1, this.getCenter().getY() - this.innerRadius + 1, this.innerRadius * 2 - 2, this.innerRadius * 2 - 2);
	}
	
	public int compareTo(Object o)
	{
		if (o instanceof Donut)
		{
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
}