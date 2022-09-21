package cmd;

import java.io.Serializable;

import geometry.Donut;
import mvc.DrawingModel;

public class ModifyDonutCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private Donut donut;
	private Donut oldD;
	
	public ModifyDonutCmd(Donut donut, Donut newD, DrawingModel model, int index)
	{
		oldD = donut.clone();
		this.donut = newD.clone();
		this.model = model;
		setIndex(index);
	}
	
	@Override
	public void execute()
	{
		if (oldD.isSelected() == false && donut.isSelected() == true)
			model.addSelected(getIndex());
		else if (oldD.isSelected() == true && donut.isSelected() == false)
			model.removeSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), donut);
	}

	@Override
	public void execute(DrawingModel model)
	{
		execute();
	}
	
	@Override
	public void unexecute()
	{
		if (oldD.isSelected() == false && donut.isSelected() == true)
			model.removeSelected(getIndex());
		else if (oldD.isSelected() == true && donut.isSelected() == false)
			model.addSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), oldD);
	}

	@Override
	public String toString()
	{
		return "Modified donut from " + oldD.toString() + " to " + donut.toString()  + " i: " + getIndex()  + " " + oldD.isSelected() + " " + donut.isSelected();
	}
}
