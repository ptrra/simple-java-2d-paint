package cmd;

import java.io.Serializable;

import geometry.Donut;
import mvc.DrawingModel;

public class RemoveDonutCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private Donut donut;
	
	public RemoveDonutCmd(Donut donut, DrawingModel model, int index)
	{
		this.donut = donut.clone();
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
		model.addShape(getIndex(), donut);
		model.addSelected(getIndex());
	}
	
	@Override
    public String toString()
	{
        return "Removed donut " + donut.toString() + " i: " + Integer.toString(getIndex());
    }
}