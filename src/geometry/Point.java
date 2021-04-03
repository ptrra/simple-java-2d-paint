package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape
{
	private int x;
	private int y;
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}

	public void setX(int x)
	{
		if(x > 0)
		{
			this.x = x;
		}
		else
		{
			throw new NumberFormatException("X has to be greater value than 0!");
		}
	}
	
	public void setY(int y)
	{
		if(y > 0)
		{
			this.y = y;
		}
		else
		{
			throw new NumberFormatException("Y has to be greater value than 0!");
		}
	}
	
	public double distance(int x2, int y2)
	{
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	
	public Point()
	{
		
	}
	
	public Point(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public Point(int x, int y, boolean selected)
	{
		this(x, y);
		setSelected(selected);
	}
	
	public Point(int x, int y, boolean selected, Color color)
	{
		this(x, y, selected);
		setColor(color);
	}
	
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof Point)
		{
			Point pom = (Point) obj;
			if (this.x == pom.x && this.y == pom.y)
			{
				return true;
			} else {
				return false;
			}
		}
		else
			return false;
	}
	
	public boolean contains(int x, int y)
	{
		return this.distance(x, y) <= 5;
	}
	
	public boolean contains(Point p)
	{
		return this.distance(p.getX(), p.getY()) <= 5;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(getColor());
		g.drawLine(this.x - 5, this.y, this.x + 5, this.y);
		g.drawLine(this.x, this.y - 5, this.x, this.y + 5);
		
		if(isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 5, this.y - 5, 10, 10);
		}
	}
	
	public void moveBy(int byX, int byY)
	{
		this.x = x + byX;
		this.y = y + byY;
	}
	
	public int compareTo(Object o)
	{
		if (o instanceof Point)
		{
			Point pom = new Point(0,0);
			return (int) (this.distance(pom.getX(), pom.getY()) - ((Point) o).distance(pom.getX(), pom.getY()));
		}
		return 0;
	}
}