package cmd;

import java.io.Serializable;

import adapter.HexagonAdapter;
import mvc.DrawingModel;

public class ModifyHexagonAdapterCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private HexagonAdapter hexaAdapter;
	private HexagonAdapter oldHA;
	
	public ModifyHexagonAdapterCmd(HexagonAdapter hexaAdapter, HexagonAdapter newHA, DrawingModel model, int index)
	{
		oldHA = hexaAdapter.clone();
		this.hexaAdapter = newHA.clone();
		this.model = model;
		setIndex(index);
	}
	
	@Override
	public void execute()
	{
		if (oldHA.isSelected() == false && hexaAdapter.isSelected() == true)
			model.addSelected(getIndex());
		else if (oldHA.isSelected() == true && hexaAdapter.isSelected() == false)
			model.removeSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), hexaAdapter);
	}

	@Override
	public void execute(DrawingModel model)
	{
		execute();
	}
	
	@Override
	public void unexecute()
	{
		if (oldHA.isSelected() == false && hexaAdapter.isSelected() == true)
			model.removeSelected(getIndex());
		else if (oldHA.isSelected() == true && hexaAdapter.isSelected() == false)
			model.addSelected(getIndex());
		model.removeShape(getIndex());
		model.addShape(getIndex(), oldHA);
	}

	@Override
	public String toString()
	{
		return "Modified hexagonAdapter from " + oldHA.toString() + " to " + hexaAdapter.toString()  + " i: " + getIndex() + " " + oldHA.isSelected() + " " + hexaAdapter.isSelected();
	}
}
