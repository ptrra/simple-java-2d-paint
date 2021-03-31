package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape
{
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Point getUpperLeftPoint()
	{
		return upperLeftPoint;
	}
	
	public void setUpperLeftPoint(Point upperLeftPoint)
	{
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		if(width > 0)
		{
			this.width = width;
		}
		else
		{
			throw new NumberFormatException("Width has to be greater value than 0!");
		}
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		if(height > 0)
		{
			this.height = height;
		}
		else
		{
			throw new NumberFormatException("Height has to be greater value than 0!");
		}
	}

	public int area()
	{
		return width * height;
	}
	
	public Rectangle()
	{
		
	}
	
	public Rectangle(Point upperLeftPoint)
	{
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height)
	{
		this(upperLeftPoint);
		setWidth(width);
		setHeight(height);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected)
	{
		this(upperLeftPoint, width, height);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color)
	{
		this(upperLeftPoint, width, height, selected);
		setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color, Color innerColor)
	{
		this(upperLeftPoint, width, height, selected, color);
		setInnerColor(innerColor);
	}
	
	public String toString()
	{
		return "ulp = " + upperLeftPoint.toString() + ", w = " + width + ", h = " + height;
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof Rectangle)
		{
			Rectangle pom = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pom.upperLeftPoint) && this.width == pom.width && this.height == pom.height)
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
		if (this.upperLeftPoint.getX() <= x && 
			this.upperLeftPoint.getY() <= y &&
			x <= this.upperLeftPoint.getX() + this.width &&
			y <= this.upperLeftPoint.getY() + this.height)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean contains(Point p)
	{
		if (this.upperLeftPoint.getX() <= p.getX() && 
			this.upperLeftPoint.getY() <= p.getY() &&
			p.getX() <= this.upperLeftPoint.getX() + this.width &&
			p.getY() <= this.upperLeftPoint.getY() + this.height)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void draw(Graphics g)
	{
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		this.fill(g);
		
		if (isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
		}
	}
	
	public void fill(Graphics g)
	{
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.upperLeftPoint.getY() + 1, this.width - 1, this.height - 1);
	}
	
	public void moveBy(int byX, int byY)
	{
		this.upperLeftPoint.moveBy(byX, byY);
	}
	
	public int compareTo(Object o)
	{
		if (o instanceof Rectangle)
		{
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
}