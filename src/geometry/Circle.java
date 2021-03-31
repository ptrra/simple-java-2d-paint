package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape
{
	private Point center;
	private int radius;
	
	public Point getCenter()
	{
		return center;
	}
	
	public void setCenter(Point center)
	{
		this.center = center;
	}
	
	public int getRadius()
	{
		return radius;
	}
	
	public void setRadius(int radius)
	{
		if(radius > 0)
		{
			this.radius = radius;
		}
		else
		{
			throw new NumberFormatException("Radius has to be greater value than 0!");
		}
	}

	public double area()
	{
		return radius * radius * Math.PI;
	}
	
	public Circle()
	{
		
	}
	
	public Circle(Point center)
	{
		this.center = center;
	}
	
	public Circle(Point center, int radius)
	{
		this(center);
		setRadius(radius);
	}
	
	public Circle(Point center, int radius, boolean selected)
	{
		this(center, radius);
		setSelected(selected);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color)
	{
		this(center, radius, selected);
		setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor)
	{
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	public String toString()
	{
		return "c = " + center.toString() + ", r = " + radius;
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof Circle)
		{
			Circle pom = (Circle) obj;
			if (this.center.equals(pom.center) && this.radius == pom.radius)
			{
				return true;
			} 
			else
			{
				return false;
			}
		}
		else
			return false;
	}
	
	public boolean contains(int x, int y)
	{
		return this.center.distance(x, y) <= this.radius;
	}
	
	public boolean contains(Point p)
	{
		return this.center.distance(p.getX(), p.getY()) <= this.radius;
	}

	public void draw(Graphics g)
	{
		g.setColor(getColor());
		g.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2, this.radius * 2);
		this.fill(g);
		
		if (isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - this.radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + this.radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - this.radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + this.radius - 3, 6, 6);
		}
	}
	
	public void fill(Graphics g)
	{
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1, this.radius * 2 - 2, this.radius * 2 - 2);
	}
	
	public void moveBy(int byX, int byY)
	{
		this.center.moveBy(byX, byY);
	}
	
	public int compareTo(Object o)
	{
		if (o instanceof Circle)
		{
			return (int) (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
}