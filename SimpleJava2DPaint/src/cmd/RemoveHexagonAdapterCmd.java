package cmd;

import java.io.Serializable;

import adapter.HexagonAdapter;
import mvc.DrawingModel;

public class RemoveHexagonAdapterCmd extends IndexCmd implements ICommand, Serializable
{
	private static final long serialVersionUID = 1L;
	private transient DrawingModel model;
	private HexagonAdapter hexaAdapter;
	
	public RemoveHexagonAdapterCmd(HexagonAdapter hexaAdapter, DrawingModel model, int index)
	{
		this.hexaAdapter = hexaAdapter.clone();
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
		model.addShape(getIndex(), hexaAdapter);
		model.addSelected(getIndex());
	}
	
	@Override
    public String toString()
	{
        return "Removed hexagonAdapter " + hexaAdapter.toString() + " i: " + Integer.toString(getIndex());
    }
}