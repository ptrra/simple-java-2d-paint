package cmd;

import java.io.Serializable;

import geometry.Hexagon;
import mvc.DrawingModel;

public class ModifyHexagonCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private Hexagon hexagon;
	private Hexagon oldH;
	
	public ModifyHexagonCmd(Hexagon hexagon, Hexagon newH, DrawingModel model, int index)
	{
		oldH = hexagon.clone();
		this.hexagon = newH.clone();
		this.model = model;
		setIndex(index);
	}
	
	@Override
	public void execute()
	{
		if (oldH.isSelected() == false && hexagon.isSelected() == true)
			model.addSelected(getIndex());
		else if (oldH.isSelected() == true && hexagon.isSelected() == false)
			model.removeSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), hexagon);
	}

	@Override
	public void execute(DrawingModel model)
	{
		execute();
	}
	
	@Override
	public void unexecute()
	{	
		if (oldH.isSelected() == false && hexagon.isSelected() == true)
			model.removeSelected(getIndex());
		else if (oldH.isSelected() == true && hexagon.isSelected() == false)
			model.addSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), oldH);
	}

	@Override
	public String toString()
	{
		return "Modified hexagonAdapter from " + oldH.toString() + " to " + hexagon.toString() + " i: " + getIndex() + " " + oldH.isSelected() + " " + hexagon.isSelected();
	}
}
