package drawing;
import geometry.Point;
import geometry.Shape;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDrawing extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Shape selected;
	private int index;
	
	public void paint(Graphics g)
	{	
		super.paint(g);
		for(Shape s : shapes)
		{
			s.draw(g);
		}
	}
	
	public PnlDrawing(FrmDrawing frame)
	{
		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				frame.setClick2(frame.getClick1());
				frame.setClick1(new Point(e.getX(), e.getY()));
				frame.update();
			}
		});
	}
	
	public int getIndex()
	{
		return this.index;
	}
	
	public Shape getSelected()
	{
		return this.selected;
	}
	
	public boolean isShapesEmpty()
	{
		return this.shapes.isEmpty();
	}
	
	public int getShapesSize()
	{
		return this.shapes.size();
	}
	
	public Shape getShapesShape(int i)
	{
		return this.shapes.get(i);
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public void setSelected(int i)
	{
		this.selected = this.shapes.get(i);
	}
	
	public void setSelected(Shape shape)
	{
		this.selected = shape;
	}
	
	public void setShapesSelected(int i, boolean select)
	{
		this.shapes.get(i).setSelected(select);
	}
	
	public void shapesAdd(Shape shape)
	{
		this.shapes.add(shape);
	}
	
	public void shapesAdd(int i, Shape shape)
	{
		this.shapes.add(i, shape);
	}
	
	public void shapesRemove(int i)
	{
		this.shapes.remove(i);
	}
	
	public void shapesClear()
	{
		this.shapes.clear();
	}
}
