package cmd;

import java.io.Serializable;

import geometry.Hexagon;
import mvc.DrawingModel;

public class RemoveHexagonCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private Hexagon hexagon;
	
	public RemoveHexagonCmd(Hexagon hexagon, DrawingModel model, int index)
	{
		this.hexagon = hexagon.clone();
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
		model.addShape(getIndex(), hexagon);
		model.addSelected(getIndex());
	}
	
	@Override
    public String toString()
	{
        return "Removed hexagonA " + hexagon.toString() + " i: " + Integer.toString(getIndex());
    }
}
