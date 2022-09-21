package cmd;

import java.io.Serializable;

import geometry.Rectangle;
import mvc.DrawingModel;

public class ModifyRectangleCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private Rectangle rectangle;
	private Rectangle oldR;
	
	public ModifyRectangleCmd(Rectangle rectangle, Rectangle newR, DrawingModel model, int index)
	{
		oldR = rectangle.clone();
		this.rectangle = newR.clone();
		this.model = model;
		setIndex(index);
	}
	
	@Override
	public void execute()
	{
		if (oldR.isSelected() == false && rectangle.isSelected() == true)
			model.addSelected(getIndex());
		else if (oldR.isSelected() == true && rectangle.isSelected() == false)
			model.removeSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), rectangle);
	}

	@Override
	public void execute(DrawingModel model)
	{
		execute();
	}
	
	@Override
	public void unexecute()
	{
		if (oldR.isSelected() == false && rectangle.isSelected() == true)
			model.addSelected(getIndex());
		else if (oldR.isSelected() == true && rectangle.isSelected() == false)
			model.removeSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), oldR);
	}

	@Override
	public String toString()
	{
		return "Modified rectangle from " + oldR.toString() + " to " + rectangle.toString() + " i: " + getIndex() + " " + oldR.isSelected() + " " + rectangle.isSelected();
	}
}
