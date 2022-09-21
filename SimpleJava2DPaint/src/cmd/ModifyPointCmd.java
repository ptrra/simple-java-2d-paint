package cmd;

import java.io.Serializable;

import geometry.Point;
import mvc.DrawingModel;

public class ModifyPointCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private Point point;
	private Point oldP;
	
	public ModifyPointCmd(Point point, Point newP, DrawingModel model, int index)
	{
		oldP = point.clone();
		this.point = newP.clone();
		this.model = model;
		setIndex(index);
	}
	
	@Override
	public void execute()
	{
		if (oldP.isSelected() == false && point.isSelected() == true)
			model.addSelected(getIndex());
		else if (oldP.isSelected() == true && point.isSelected() == false)
			model.removeSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), point);
	}

	@Override
	public void execute(DrawingModel model)
	{
		execute();
	}
	
	@Override
	public void unexecute()
	{
		if (oldP.isSelected() == false && point.isSelected() == true)
			model.removeSelected(getIndex());
		else if (oldP.isSelected() == true && point.isSelected() == false)
			model.addSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), oldP);
		
	}

	@Override
	public String toString()
	{
		return "Modified point from " + oldP.toString() + " to " + point.toString() + " i: " + getIndex() + " " + oldP.isSelected() + " " + point.isSelected();
	}
}
