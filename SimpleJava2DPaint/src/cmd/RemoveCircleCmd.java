package cmd;

import java.io.Serializable;

import geometry.Circle;
import mvc.DrawingModel;

public class RemoveCircleCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private Circle circle;
	
	public RemoveCircleCmd(Circle circle, DrawingModel model, int index)
	{
		this.circle = circle.clone();
		this.model = model;
		setIndex(index);
	}

	@Override
	public void execute()
	{
		model.removeSelected(getIndex());
		model.removeShape(getIndex());
	}
	
	@Override
	public void execute(DrawingModel model)
	{
		this.model = model;
		this.execute();
	}

	@Override
	public void unexecute()
	{
		model.addShape(getIndex(), circle);
		model.addSelected(getIndex());
	}
	
	@Override
    public String toString()
	{
        return "Removed circle " + circle.toString() + " i: " + Integer.toString(getIndex());
    }
}