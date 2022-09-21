package mvc;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import cmd.*;
import dlg.*;
import geometry.*;
import strategy.*;

enum ModeAddSel { SELECT, POINT, LINE, CIRCLE, RECT, DONUT, HEXA, HEXAA };

public class DrawingController
{
	private DrawingModel drwMdl;
	private DrawingFrame drwFrm;
	
	private Point[] click = new Point[2];
	private Color[] color = { Color.BLUE, Color.RED };
	
	private ModeAddSel modeAddSel = ModeAddSel.POINT;
	
	private ICommand cmd = null;
	
	public DrawingController(DrawingModel drwMdl, DrawingFrame drawingFrame)
	{
		this.drwMdl = drwMdl;
		this.drwFrm = drawingFrame;
		this.drwMdl.addPropertyChangeListener(this.drwFrm);
	}
	
	public void click()
	{
		if (modeAddSel != null)
		{
			switch (modeAddSel)
			{
				case SELECT:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					findSelected();
					setClick1(null);
					break;
				case POINT:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					addPoint();
					setClick1(null);
					break;
				case LINE:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					if (getClick2() != null)
					{
						drwFrm.addClick("2: " + "(" + getClick2().getX() + "," + getClick2().getY() + ")");
						addLine();
						setClick1(null);
						setClick2(null);
					}
					else
						drwFrm.addClick("2: (x2,y2)");
					break;
				case CIRCLE:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					addCircle();
					setClick1(null);
					break;
				case RECT:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					addRect();
					setClick1(null);
					break;
				case DONUT:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					addDonut();
					setClick1(null);
					break;
				case HEXA:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					addHexagon();
					break;
				case HEXAA:
					drwFrm.clearClick();
					drwFrm.addClick("1: " + "(" + getClick1().getX() + "," + getClick1().getY() + ")");
					addHexaAdapter();
					setClick1(null);
					break;
			}
			drwFrm.getDrwView().repaint();		
		}
		else
			drwFrm.info("You need to select a shape to add.", "How To Start Drawing");
	}
	
	public void findSelected()
	{
		boolean ok = false;
		for (int i = drwMdl.getShapesSize() - 1; i >= 0; i--)
			if (drwMdl.getShape(i).contains(click[0]))
			{
				if (!drwMdl.isShapeSelected(i))
				{
					Shape selected = drwMdl.getShape(i);
					if (selected instanceof Point)
					{
						Point oldP = (Point) selected.clone();
						Point newP = oldP.clone();
						newP.setSelected(true);
						cmd = new ModifyPointCmd(oldP, newP, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Line)
					{
						Line oldL = (Line) selected.clone();
						Line newL = oldL.clone();
						newL.setSelected(true);
						cmd = new ModifyLineCmd(oldL, newL, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Rectangle)
					{
						Rectangle oldR = (Rectangle) selected.clone();
						Rectangle newR = oldR.clone();
						newR.setSelected(true);
						cmd = new ModifyRectangleCmd(oldR, newR, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Donut)
					{
						Donut oldD = (Donut) selected.clone();
						Donut newD = oldD.clone();
						newD.setSelected(true);
						cmd = new ModifyDonutCmd(oldD, newD, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Circle)
					{
						Circle oldC = (Circle) selected.clone();
						Circle newC = oldC.clone();
						newC.setSelected(true);
						cmd = new ModifyCircleCmd(oldC, newC, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Hexagon)
					{
						Hexagon oldH = (Hexagon) selected.clone();
						Hexagon newH = oldH.clone();
						newH.setSelected(true);
						cmd = new ModifyHexagonCmd(oldH, newH, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof HexagonAdapter)
					{
						HexagonAdapter oldHA = (HexagonAdapter) selected.clone();
						HexagonAdapter newHA = oldHA.clone();
						newHA.setSelected(true);
						cmd = new ModifyHexagonAdapterCmd(oldHA, newHA, drwMdl, i);
						execute(cmd);
					}
				}
				if (!ok)
					ok = true;
				break;
			}
		
		if (!ok)
		{
			for (int i = 0; i < drwMdl.getShapesSize(); i++)
				if (drwMdl.getShape(i).isSelected())
				{
					Shape selected = drwMdl.getShape(i);
					if (selected instanceof Point)
					{
						Point oldP = (Point) selected.clone();
						Point newP = oldP.clone();
						newP.setSelected(false);
						cmd = new ModifyPointCmd(oldP, newP, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Line)
					{
						Line oldL = (Line) selected.clone();
						Line newL = oldL.clone();
						newL.setSelected(false);
						cmd = new ModifyLineCmd(oldL, newL, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Rectangle)
					{
						Rectangle oldR = (Rectangle) selected.clone();
						Rectangle newR = oldR.clone();
						newR.setSelected(false);
						cmd = new ModifyRectangleCmd(oldR, newR, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Donut)
					{
						Donut oldD = (Donut) selected.clone();
						Donut newD = oldD.clone();
						newD.setSelected(false);
						cmd = new ModifyDonutCmd(oldD, newD, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Circle)
					{
						Circle oldC = (Circle) selected.clone();
						Circle newC = oldC.clone();
						newC.setSelected(false);
						cmd = new ModifyCircleCmd(oldC, newC, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof Hexagon)
					{
						Hexagon oldH = (Hexagon) selected.clone();
						Hexagon newH = oldH.clone();
						newH.setSelected(false);
						cmd = new ModifyHexagonCmd(oldH, newH, drwMdl, i);
						execute(cmd);
					}
					else if (selected instanceof HexagonAdapter)
					{
						HexagonAdapter oldHA = (HexagonAdapter) selected.clone();
						HexagonAdapter newHA = oldHA.clone();
						newHA.setSelected(false);
						cmd = new ModifyHexagonAdapterCmd(oldHA, newHA, drwMdl, i);
						execute(cmd);
					}
				}
			drwMdl.clearSelected();
		}
	}
	
	public void modify()
	{
		Shape selected = drwMdl.getShape(drwMdl.getSelected(0));
		if (selected instanceof Point)
			modPoint();
		else if (selected instanceof Line)
			modLine();
		else if (selected instanceof Rectangle)
			modRect();
		else if (selected instanceof Donut)
			modDonut();
		else if (selected instanceof Circle)
			modCircle();
		else if (selected instanceof Hexagon)
			modHexagon();
		else if (selected instanceof HexagonAdapter)
			modHexaAdapter();
		drwFrm.getDrwView().repaint();
	}
	
	public void delete()
	{
		if (drwFrm.confirm("Are you sure?", "Confirm Deletion") == JOptionPane.YES_OPTION)
		{
			Shape deleted;
			for (int i = 0; i < drwMdl.getSelectedSize(); i++)
			{
				deleted = drwMdl.getShape(drwMdl.getSelected(i));
				if (deleted instanceof Point)
					cmd = new RemovePointCmd((Point) deleted, drwMdl, drwMdl.getSelected(i));
				else if (deleted instanceof Line)
					cmd = new RemoveLineCmd((Line) deleted, drwMdl, drwMdl.getSelected(i));
				else if (deleted instanceof Rectangle)
					cmd = new RemoveRectangleCmd((Rectangle) deleted, drwMdl, drwMdl.getSelected(i));
				else if (deleted instanceof Donut)
					cmd = new RemoveDonutCmd((Donut) deleted, drwMdl, drwMdl.getSelected(i));
				else if (deleted instanceof Circle)
					cmd = new RemoveCircleCmd((Circle) deleted, drwMdl, drwMdl.getSelected(i));
				else if (deleted instanceof Hexagon)
					cmd = new RemoveHexagonCmd((Hexagon) deleted, drwMdl, drwMdl.getSelected(i));
				else if (deleted instanceof HexagonAdapter)
					cmd = new RemoveHexagonAdapterCmd((HexagonAdapter) deleted, drwMdl, drwMdl.getSelected(i));
				drwMdl.addCmd(cmd);
			}
			for (int i = drwMdl.getStep() + 1; i < drwMdl.getCmdSize(); i++)
			{
				drwMdl.getCmd(i).execute();
				drwMdl.log(drwMdl.getCmd(i).toString());
				drwFrm.log(drwMdl.getCmd(i).toString());
				drwMdl.addSteps(drwFrm.getLogSize() - 1);
				drwMdl.setStep(drwMdl.getStep() + 1);
			}
			drwFrm.getDrwView().repaint();
		}
	}
	
	public void clear()
	{
		if (drwFrm.confirm("Are you sure?", "Clear Everything") == JOptionPane.YES_OPTION)
		{
				drwMdl.clearShapes();
				drwMdl.clearSelected();
				drwMdl.clearCmds();
				drwFrm.clearLog();
				drwMdl.clearLog();
				drwMdl.clearSteps();
				drwMdl.setStep(-1);
				drwFrm.getDrwView().repaint();
		}
	}
	
	public void clearSelected()
	{
		drwMdl.clearSelected();
		drwFrm.getDrwView().repaint();
	}
	
	private void addPoint()
	{
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setTitle("Add Point");
		if (click[0] != null)
			dlgPoint.setTxtXY(click[0].getX(), click[0].getY());
		dlgPoint.setFillColor(color[0]);
		dlgPoint.setVisible(true);
		if (dlgPoint.isOk())
		{
			cmd = new AddPointCmd(dlgPoint.getP(), drwMdl, drwMdl.getShapesSize());
			execute(cmd);
		}
		dlgPoint.dispose();
	}
	
	private void addLine()
	{
		DlgLine dlgLine = new DlgLine();
		dlgLine.setTitle("Add Line");
		if (click[0] != null)
			dlgLine.setTxtXY1(click[0].getX(), click[0].getY());
		if (click[1] != null)
			dlgLine.setTxtXY2(click[1].getX(), click[1].getY());
		dlgLine.setFillColor(color[0]);
		dlgLine.setVisible(true);
		if (dlgLine.isOk())
		{
			cmd = new AddLineCmd(dlgLine.getL(), drwMdl, drwMdl.getShapesSize());
			execute(cmd);
		}
		dlgLine.dispose();
	}
	
	private void addCircle()
	{
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setTitle("Add Circle");
		if (click[0] != null)
			dlgCircle.setTxtXY(click[0].getX(), click[0].getY());
		dlgCircle.setFillColor(color[0]);
		dlgCircle.setBorderColor(color[1]);
		dlgCircle.setVisible(true);
		if (dlgCircle.isOk())
		{
			cmd = new AddCircleCmd(dlgCircle.getC(), drwMdl, drwMdl.getShapesSize());
			execute(cmd);
		}
		dlgCircle.dispose();
	}
	
	private void addRect()
	{
		DlgRectangle dlgRect = new DlgRectangle();
		dlgRect.setTitle("Add Rectangle");
		if (click[0] != null)
			dlgRect.setTxtXY(click[0].getX(), click[0].getY());
		dlgRect.setFillColor(color[0]);
		dlgRect.setBorderColor(color[1]);
		dlgRect.setVisible(true);
		if (dlgRect.isOk())
		{
			cmd = new AddRectangleCmd(dlgRect.getR(), drwMdl, drwMdl.getShapesSize());
			execute(cmd);
		}
		dlgRect.dispose();
	}
	
	private void addDonut()
	{
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setTitle("Add Donut");
		if (click[0] != null)
			dlgDonut.setTxtXY(click[0].getX(), click[0].getY());
		dlgDonut.setFillColor(color[0]);
		dlgDonut.setBorderColor(color[1]);
		dlgDonut.setVisible(true);
		if (dlgDonut.isOk())
		{
			cmd = new AddDonutCmd(dlgDonut.getD(), drwMdl, drwMdl.getShapesSize());
			execute(cmd);
		}
		dlgDonut.dispose();
	}
	
	private void addHexagon()
	{
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setTitle("Add Hexagon");
		if (click[0] != null)
			dlgHexagon.setTxtXY(click[0].getX(), click[0].getY());
		dlgHexagon.setFillColor(color[0]);
		dlgHexagon.setBorderColor(color[1]);
		dlgHexagon.setVisible(true);
		if (dlgHexagon.isOk())
		{
			cmd = new AddHexagonCmd(dlgHexagon.getH(), drwMdl, drwMdl.getShapesSize());
			execute(cmd);
		}
		dlgHexagon.dispose();
	}
	
	private void addHexaAdapter()
	{
		DlgHexagon dlgHexaAdapter = new DlgHexagon();
		dlgHexaAdapter.setTitle("Add HexagonAdapter");
		if (click[0] != null)
			dlgHexaAdapter.setTxtXY(click[0].getX(), click[0].getY());
		dlgHexaAdapter.setFillColor(color[0]);
		dlgHexaAdapter.setBorderColor(color[1]);
		dlgHexaAdapter.setVisible(true);
		if (dlgHexaAdapter.isOk())
		{
			cmd = new AddHexagonAdapterCmd(dlgHexaAdapter.getHA(), drwMdl, drwMdl.getShapesSize());
			execute(cmd);
		}
		dlgHexaAdapter.dispose();
	}
	
	private void modPoint()
	{
		Point p = (Point) drwMdl.getShape(drwMdl.getSelected(0));
		DlgPoint dlgPoint = new DlgPoint();
		dlgPoint.setTitle("Modify Point");
		dlgPoint.setTxtXY(p.getX(), p.getY());
		dlgPoint.setFillColor(p.getFillColor());
		dlgPoint.setVisible(true);
		dlgPoint.setPSelected(true);
		if (dlgPoint.isOk())
		{	
			cmd = new ModifyPointCmd(p, dlgPoint.getP(), drwMdl, drwMdl.getSelected(0));
			execute(cmd);
		}
		dlgPoint.dispose();
	}
	
	private void modLine()
	{
		Line l = (Line) drwMdl.getShape(drwMdl.getSelected(0));
		DlgLine dlgLine = new DlgLine();
		dlgLine.setTitle("Modify Line");
		dlgLine.setTxtXY1(l.getStartPoint().getX(), l.getStartPoint().getY());
		dlgLine.setTxtXY2(l.getEndPoint().getX(), l.getEndPoint().getY());
		dlgLine.setFillColor(l.getFillColor());
		dlgLine.setVisible(true);
		dlgLine.setLSelected(true);
		if (dlgLine.isOk())
		{	
			cmd = new ModifyLineCmd(l, dlgLine.getL(), drwMdl, drwMdl.getSelected(0));
			execute(cmd);
		}
		dlgLine.dispose();
	}
	
	private void modCircle()
	{
		Circle c = (Circle) drwMdl.getShape(drwMdl.getSelected(0));
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setTitle("Modify Circle");
		dlgCircle.setTxtXY(c.getCenter().getX(), c.getCenter().getY());
		dlgCircle.setTxtR(c.getRadius());
		dlgCircle.setFillColor(c.getFillColor());
		dlgCircle.setBorderColor(c.getBorderColor());
		dlgCircle.setVisible(true);
		dlgCircle.setCSelected(true);
		if (dlgCircle.isOk())
		{	
			cmd = new ModifyCircleCmd(c, dlgCircle.getC(), drwMdl, drwMdl.getSelected(0));
			execute(cmd);
		}
		dlgCircle.dispose();
	}
	
	private void modRect()
	{
		Rectangle r = (Rectangle) drwMdl.getShape(drwMdl.getSelected(0));
		DlgRectangle dlgRect = new DlgRectangle();
		dlgRect.setTitle("Modify Rectangle");
		dlgRect.setTxtXY(r.getUpperLeftPoint().getX(), r.getUpperLeftPoint().getY());
		dlgRect.setTxtWH(r.getWidth(), r.getHeight());
		dlgRect.setFillColor(r.getFillColor());
		dlgRect.setBorderColor(r.getBorderColor());
		dlgRect.setVisible(true);
		dlgRect.setRSelected(true);
		if (dlgRect.isOk())
		{	
			cmd = new ModifyRectangleCmd(r, dlgRect.getR(), drwMdl, drwMdl.getSelected(0));
			execute(cmd);
		}
		dlgRect.dispose();
	}
	
	private void modDonut()
	{
		Donut d = (Donut) drwMdl.getShape(drwMdl.getSelected(0));
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setTitle("Modify Donut");
		dlgDonut.setTxtXY(d.getCenter().getX(), d.getCenter().getY());
		dlgDonut.setTxtRIR(d.getRadius(), d.getInnerRadius());
		dlgDonut.setFillColor(d.getFillColor());
		dlgDonut.setBorderColor(d.getBorderColor());
		dlgDonut.setVisible(true);
		dlgDonut.setDSelected(true);
		if (dlgDonut.isOk())
		{	
			cmd = new ModifyDonutCmd(d, dlgDonut.getD(), drwMdl, drwMdl.getSelected(0));
			execute(cmd);
		}
		dlgDonut.dispose();
	}
	
	private void modHexagon()
	{
		Hexagon h = (Hexagon) drwMdl.getShape(drwMdl.getSelected(0));
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setTitle("Modify Hexagon");
		dlgHexagon.setTxtXY(h.getCenter().getX(), h.getCenter().getY());
		dlgHexagon.setTxtL(h.getLength());
		dlgHexagon.setFillColor(h.getFillColor());
		dlgHexagon.setBorderColor(h.getBorderColor());
		dlgHexagon.setVisible(true);
		dlgHexagon.setHSelected(true);
		if (dlgHexagon.isOk())
		{	
			cmd = new ModifyHexagonCmd(h, dlgHexagon.getH(), drwMdl, drwMdl.getSelected(0));
			execute(cmd);
		}
		dlgHexagon.dispose();
	}
	
	private void modHexaAdapter()
	{
		HexagonAdapter ha = (HexagonAdapter) drwMdl.getShape(drwMdl.getSelected(0));
		DlgHexagon dlgHexagon = new DlgHexagon();
		dlgHexagon.setTitle("Modify HexagonAdapter");
		dlgHexagon.setTxtXY(ha.getX(), ha.getY());
		dlgHexagon.setTxtL(ha.getLength());
		dlgHexagon.setFillColor(ha.getFillColor());
		dlgHexagon.setBorderColor(ha.getBorderColor());
		dlgHexagon.setVisible(true);
		dlgHexagon.setHASelected(true);
		if (dlgHexagon.isOk())
		{
			cmd = new ModifyHexagonAdapterCmd(ha, dlgHexagon.getHA(), drwMdl, drwMdl.getSelected(0));
			execute(cmd);
		}
		dlgHexagon.dispose();
	}
	
	public int[] checkLayers()
	{
		int[] layers = { 0, 0 };
		
		try
		{
			drwMdl.getShape(drwMdl.getSelected(0) - 1);
			layers[0] = 1;
		}
		catch (IndexOutOfBoundsException exception) {}
		
		try
		{
			drwMdl.getShape(drwMdl.getSelected(0) + 1);
			layers[1] = 1;
		}
		catch (IndexOutOfBoundsException exception) {}
		
		return layers;
	}
	
	public void toBack()
	{
		cmd = new MoveCmd(drwMdl.getSelected(0) - 1, drwMdl, drwMdl.getSelected(0));
		execute(cmd);
		drwMdl.updateSelected(drwMdl.getSelected(0) - 1, 0);
		drwFrm.getDrwView().repaint();
	}
	
	public void toFront()
	{
		cmd = new MoveCmd(drwMdl.getSelected(0) + 1, drwMdl, drwMdl.getSelected(0));
		execute(cmd);
		drwMdl.updateSelected(drwMdl.getSelected(0) + 1, 0);
		drwFrm.getDrwView().repaint();
	}
	
	public void bringBack()
	{
		cmd = new MoveCmd(0, drwMdl, drwMdl.getSelected(0));
		execute(cmd);
		drwMdl.updateSelected(0, 0);
		drwFrm.getDrwView().repaint();
	}
	
	public void bringFront()
	{
		cmd = new MoveCmd(drwMdl.getShapesSize() - 1, drwMdl, drwMdl.getSelected(0));
		execute(cmd);
		drwMdl.updateSelected(drwMdl.getShapesSize() - 1, 0);
		drwFrm.getDrwView().repaint();
	}
	
	public void execute(ICommand cmd)
	{
		try
		{
			drwMdl.getCmd(drwMdl.getStep() + 1);
			drwMdl.updateCmdsSteps();
		}
		catch (IndexOutOfBoundsException exception) {}
		drwMdl.addCmd(cmd);
		drwMdl.getCmd(drwMdl.getCmdSize() - 1).execute();
		drwMdl.log(drwMdl.getCmd(drwMdl.getCmdSize() - 1).toString());
		drwFrm.log(drwMdl.getCmd(drwMdl.getCmdSize() - 1).toString());
		drwMdl.addSteps(drwFrm.getLogSize() - 1);
		drwMdl.setStep(drwMdl.getStep() + 1);
	}
	
	public void undo()
	{
		if (drwMdl.getCmd(drwMdl.getStep()) instanceof RemovePointCmd ||
			drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveLineCmd ||
			drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveCircleCmd ||
			drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveRectangleCmd ||
			drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveDonutCmd ||
			drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveHexagonCmd ||
			drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveHexagonAdapterCmd)
		{
			drwMdl.getCmd(drwMdl.getStep()).unexecute();
			drwMdl.log("Undo " + drwMdl.getCmd(drwMdl.getStep()).toString());
			drwFrm.log("Undo " + drwMdl.getCmd(drwMdl.getStep()).toString());
			drwMdl.setStep(drwMdl.getStep() - 1);
			while (true)
			{
				if (drwMdl.getCmd(drwMdl.getStep()) instanceof RemovePointCmd ||
						drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveLineCmd ||
						drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveCircleCmd ||
						drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveRectangleCmd ||
						drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveDonutCmd ||
						drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveHexagonCmd ||
						drwMdl.getCmd(drwMdl.getStep()) instanceof RemoveHexagonAdapterCmd)
					{
						drwMdl.getCmd(drwMdl.getStep()).unexecute();
						drwMdl.setStep(drwMdl.getStep() - 1);
					}
					else
						break;
			}
		}
		else
		{
			drwMdl.getCmd(drwMdl.getStep()).unexecute();
			drwMdl.log("Undo " + drwMdl.getCmd(drwMdl.getStep()).toString());
			drwFrm.log("Undo " + drwMdl.getCmd(drwMdl.getStep()).toString());
			drwMdl.setStep(drwMdl.getStep() - 1);
		}
		drwFrm.getDrwView().repaint();
	}
	
	public void redo()
	{
		if (drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemovePointCmd ||
				drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveLineCmd ||
				drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveCircleCmd ||
				drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveRectangleCmd ||
				drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveDonutCmd ||
				drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveHexagonCmd ||
				drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveHexagonAdapterCmd)
			{
				drwMdl.getCmd(drwMdl.getStep() + 1).execute();
				drwMdl.log("Redo " + drwMdl.getCmd(drwMdl.getStep() + 1).toString());
				drwFrm.log("Redo " + drwMdl.getCmd(drwMdl.getStep() + 1).toString());
				drwMdl.setStep(drwMdl.getStep() + 1);
				try
				{
					while (true)
						if (drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemovePointCmd ||
							drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveLineCmd ||
							drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveCircleCmd ||
							drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveRectangleCmd ||
							drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveDonutCmd ||
							drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveHexagonCmd ||
							drwMdl.getCmd(drwMdl.getStep() + 1) instanceof RemoveHexagonAdapterCmd)
						{
							drwMdl.getCmd(drwMdl.getStep() + 1).execute();
							drwMdl.setStep(drwMdl.getStep() + 1);
						}
						else
							break;
				}
				catch (Exception exception) {}
			}
			else
			{
				drwMdl.getCmd(drwMdl.getStep() + 1).execute();
				drwMdl.log("Redo " + drwMdl.getCmd(drwMdl.getStep() + 1).toString());
				drwFrm.log("Redo " + drwMdl.getCmd(drwMdl.getStep() + 1).toString());
				drwMdl.setStep(drwMdl.getStep() + 1);
			}
			drwFrm.getDrwView().repaint();
	}
	
	public void importFile()
	{
		JFileChooser fileChooser = new JFileChooser("../");
		int ok = fileChooser.showOpenDialog(drwFrm);
		if (ok == JFileChooser.APPROVE_OPTION)
		{
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			FileManager fileManager = new FileManager(new LogFile());
			fileManager.read(path);
			ArrayList<String> log = fileManager.getLog();
			
			drwMdl.clearShapes();
			drwMdl.clearSelected();
			drwMdl.clearCmds();
			drwFrm.clearLog();
			drwMdl.clearLog();
			drwMdl.clearSteps();
			drwMdl.setStep(-1);
			drwFrm.getDrwView().repaint();
			
			for (int i = 0; i < log.size(); i++)
			{
				if (drwFrm.confirm("Are you sure?", "Execute Command") == JOptionPane.YES_OPTION)
				{
					String temp[] = log.get(i).split(" ");
					switch(temp[0])
					{
						case "Created":
							switch(temp[1])
							{
								case "point":
									try
									{
										Point p = new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5]), false, new Color(Integer.parseInt(temp[7])));
										cmd = new AddPointCmd(p, drwMdl, Integer.parseInt(temp[9]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "line":
									try
									{
										Line l = new Line(Integer.parseInt(temp[4]), Integer.parseInt(temp[6]), Integer.parseInt(temp[9]), Integer.parseInt(temp[11]));
										l.setSelected(false);
										l.setFillColor(new Color(Integer.parseInt(temp[13])));
										cmd = new AddLineCmd(l, drwMdl, Integer.parseInt(temp[15]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "circle":
									try
									{
										Circle c = new Circle(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), false, new Color(Integer.parseInt(temp[9])), new Color(Integer.parseInt(temp[11])));
										cmd = new AddCircleCmd(c, drwMdl, Integer.parseInt(temp[13]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "rectangle":
									try
									{
										Rectangle r = new Rectangle(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), Integer.parseInt(temp[9]), false, new Color(Integer.parseInt(temp[11])), new Color(Integer.parseInt(temp[13])));
										cmd = new AddRectangleCmd(r, drwMdl, Integer.parseInt(temp[15]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "donut":
									try
									{
										Donut d = new Donut(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), Integer.parseInt(temp[9]), false, new Color(Integer.parseInt(temp[11])), new Color(Integer.parseInt(temp[13])));
										cmd = new AddDonutCmd(d, drwMdl, Integer.parseInt(temp[15]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "hexagon":
									try
									{
										Hexagon h = new Hexagon(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), false, new Color(Integer.parseInt(temp[9])), new Color(Integer.parseInt(temp[11])));
										cmd = new AddHexagonCmd(h, drwMdl, Integer.parseInt(temp[13]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "hexagonAdapter":
									try
									{
										HexagonAdapter ha = new HexagonAdapter(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), false, new Color(Integer.parseInt(temp[9])), new Color(Integer.parseInt(temp[11])));
										cmd = new AddHexagonAdapterCmd(ha, drwMdl, Integer.parseInt(temp[13]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								default:
									drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
									return;
							}
							break;
						case "Removed":
							switch(temp[1])
							{
								case "point":
									try
									{
										Point p = new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5]), true, new Color(Integer.parseInt(temp[7])));
										cmd = new RemovePointCmd(p, drwMdl, Integer.parseInt(temp[9]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "line":
									try
									{
										Line l = new Line(Integer.parseInt(temp[4]), Integer.parseInt(temp[6]), Integer.parseInt(temp[9]), Integer.parseInt(temp[11]));
										l.setSelected(true);
										l.setFillColor(new Color(Integer.parseInt(temp[13])));
										cmd = new RemoveLineCmd(l, drwMdl, Integer.parseInt(temp[15]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "circle":
									try
									{
										Circle c = new Circle(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), true, new Color(Integer.parseInt(temp[9])), new Color(Integer.parseInt(temp[11])));
										cmd = new RemoveCircleCmd(c, drwMdl, Integer.parseInt(temp[13]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "rectangle":
									try
									{
										Rectangle r = new Rectangle(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), Integer.parseInt(temp[9]), true, new Color(Integer.parseInt(temp[11])), new Color(Integer.parseInt(temp[13])));
										cmd = new RemoveRectangleCmd(r, drwMdl, Integer.parseInt(temp[15]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "donut":
									try
									{
										Donut d = new Donut(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), Integer.parseInt(temp[9]), true, new Color(Integer.parseInt(temp[11])), new Color(Integer.parseInt(temp[13])));
										cmd = new RemoveDonutCmd(d, drwMdl, Integer.parseInt(temp[15]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "hexagon":
									try
									{
										Hexagon h = new Hexagon(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), true, new Color(Integer.parseInt(temp[9])), new Color(Integer.parseInt(temp[11])));
										cmd = new RemoveHexagonCmd(h, drwMdl, Integer.parseInt(temp[13]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "hexagonAdapter":
									try
									{
										HexagonAdapter ha = new HexagonAdapter(new Point(Integer.parseInt(temp[3]), Integer.parseInt(temp[5])), Integer.parseInt(temp[7]), true, new Color(Integer.parseInt(temp[9])), new Color(Integer.parseInt(temp[11])));
										cmd = new RemoveHexagonAdapterCmd(ha, drwMdl, Integer.parseInt(temp[13]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								default:
									drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
									return;
							}
							break;
						case "Modified":
							switch(temp[1])
							{
								case "point":
									try
									{
										Point p1 = new Point(Integer.parseInt(temp[4]), Integer.parseInt(temp[6]), false, new Color(Integer.parseInt(temp[8])));
										Point p2 = new Point(Integer.parseInt(temp[11]), Integer.parseInt(temp[13]), false, new Color(Integer.parseInt(temp[15])));
										if (temp[18].equals("true"))
											p1.setSelected(true);
										if (temp[19].equals("true"))
											p2.setSelected(true);
										cmd = new ModifyPointCmd(p1, p2, drwMdl, Integer.parseInt(temp[17]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "line":
									try
									{
										Line l1 = new Line(Integer.parseInt(temp[5]), Integer.parseInt(temp[7]), Integer.parseInt(temp[10]), Integer.parseInt(temp[12]));
										l1.setSelected(false);
										l1.setFillColor(new Color(Integer.parseInt(temp[14])));
										
										Line l2 = new Line(Integer.parseInt(temp[18]), Integer.parseInt(temp[20]), Integer.parseInt(temp[23]), Integer.parseInt(temp[25]));
										l2.setSelected(false);
										l2.setFillColor(new Color(Integer.parseInt(temp[27])));
										
										if (temp[30].equals("true"))
											l1.setSelected(true);
										if (temp[31].equals("true"))
											l2.setSelected(true);
										
										cmd = new ModifyLineCmd(l1, l2, drwMdl, Integer.parseInt(temp[29]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "circle":
									try
									{
										Circle c1 = new Circle(new Point(Integer.parseInt(temp[4]), Integer.parseInt(temp[6])), Integer.parseInt(temp[8]), false, new Color(Integer.parseInt(temp[10])), new Color(Integer.parseInt(temp[12])));
										Circle c2 = new Circle(new Point(Integer.parseInt(temp[15]), Integer.parseInt(temp[17])), Integer.parseInt(temp[19]), false, new Color(Integer.parseInt(temp[21])), new Color(Integer.parseInt(temp[23])));
										if (temp[26].equals("true"))
											c1.setSelected(true);
										if (temp[27].equals("true"))
											c2.setSelected(true);
										cmd = new ModifyCircleCmd(c1, c2, drwMdl, Integer.parseInt(temp[25]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "rectangle":
									try
									{
										Rectangle r1 = new Rectangle(new Point(Integer.parseInt(temp[4]), Integer.parseInt(temp[6])), Integer.parseInt(temp[8]), Integer.parseInt(temp[10]), false, new Color(Integer.parseInt(temp[12])), new Color(Integer.parseInt(temp[14])));
										Rectangle r2 = new Rectangle(new Point(Integer.parseInt(temp[17]), Integer.parseInt(temp[19])), Integer.parseInt(temp[21]), Integer.parseInt(temp[23]), false, new Color(Integer.parseInt(temp[25])), new Color(Integer.parseInt(temp[27])));
										if (temp[30].equals("true"))
											r1.setSelected(true);
										if (temp[31].equals("true"))
											r2.setSelected(true);
										cmd = new ModifyRectangleCmd(r1, r2, drwMdl, Integer.parseInt(temp[29]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "donut":
									try
									{
										Donut d1 = new Donut(new Point(Integer.parseInt(temp[4]), Integer.parseInt(temp[6])), Integer.parseInt(temp[8]), Integer.parseInt(temp[10]), false, new Color(Integer.parseInt(temp[12])), new Color(Integer.parseInt(temp[14])));
										Donut d2 = new Donut(new Point(Integer.parseInt(temp[17]), Integer.parseInt(temp[19])), Integer.parseInt(temp[21]), Integer.parseInt(temp[23]), false, new Color(Integer.parseInt(temp[25])), new Color(Integer.parseInt(temp[27])));
										if (temp[30].equals("true"))
											d1.setSelected(true);
										if (temp[31].equals("true"))
											d2.setSelected(true);
										cmd = new ModifyDonutCmd(d1, d2, drwMdl, Integer.parseInt(temp[29]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "hexagon":
									try
									{
										Hexagon h1 = new Hexagon(new Point(Integer.parseInt(temp[4]), Integer.parseInt(temp[6])), Integer.parseInt(temp[8]), false, new Color(Integer.parseInt(temp[10])), new Color(Integer.parseInt(temp[12])));
										Hexagon h2 = new Hexagon(new Point(Integer.parseInt(temp[15]), Integer.parseInt(temp[17])), Integer.parseInt(temp[19]), false, new Color(Integer.parseInt(temp[21])), new Color(Integer.parseInt(temp[23])));
										if (temp[26].equals("true"))
											h1.setSelected(true);
										if (temp[27].equals("true"))
											h2.setSelected(true);
										cmd = new ModifyHexagonCmd(h1, h2, drwMdl, Integer.parseInt(temp[25]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								case "hexagonAdapter":
									try
									{
										HexagonAdapter ha1 = new HexagonAdapter(new Point(Integer.parseInt(temp[4]), Integer.parseInt(temp[6])), Integer.parseInt(temp[8]), false, new Color(Integer.parseInt(temp[10])), new Color(Integer.parseInt(temp[12])));
										HexagonAdapter ha2 = new HexagonAdapter(new Point(Integer.parseInt(temp[15]), Integer.parseInt(temp[17])), Integer.parseInt(temp[19]), false, new Color(Integer.parseInt(temp[21])), new Color(Integer.parseInt(temp[23])));
										if (temp[26].equals("true"))
											ha1.setSelected(true);
										if (temp[27].equals("true"))
											ha2.setSelected(true);
										cmd = new ModifyHexagonAdapterCmd(ha1, ha2, drwMdl, Integer.parseInt(temp[25]));
										execute(cmd);
									}
									catch(Exception exception)
									{
										drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
										return;
									}
									break;
								default:
									drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
									return;
							}
							break;
						case "Moved":
							try
							{
								cmd = new MoveCmd(Integer.parseInt(temp[6]), drwMdl, Integer.parseInt(temp[4]));
								execute(cmd);
							}
							catch (Exception exception)
							{
								drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
								return;
							}
							break;
						case "Undo":
							undo();
							break;
						case "Redo":
							redo();
							break;
						default:
							drwFrm.warn("Log failed to import because corrupted or bad file!", "Failed To Import Log");
							return;
					}
					drwFrm.getDrwView().repaint();	
				}
				else
					break;
			}
		}
	}
	
	public void exportFile()
	{
		JFileChooser fileChooser = new JFileChooser("../");
		int ok = fileChooser.showSaveDialog(drwFrm);
		if (ok == JFileChooser.APPROVE_OPTION)
		{
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			FileManager fileManager = new FileManager(new LogFile(drwMdl.getLog()));
			try
			{
				fileManager.write(path);
			}
			catch (IOException exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	public void importDrawing()
	{
		JFileChooser fileChooser = new JFileChooser("../");
		int ok = fileChooser.showOpenDialog(drwFrm);
		if (ok == JFileChooser.APPROVE_OPTION)
		{
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			FileManager fileManager = new FileManager(new DrawingFile());
			fileManager.read(path);
			if (fileManager.getDrawing() != null)
			{
				drwMdl.setShapes(fileManager.getDrawing());
				drwMdl.clearSelected();
				drwMdl.clearCmds();
				drwFrm.clearLog();
				drwMdl.setStep(-1);
				drwFrm.getDrwView().repaint();
			}
			else
				drwFrm.warn("Drawing failed to import because corrupted or bad file!", "Failed To Import Drawing");
		}
	}
	
	public void exportDrawing()
	{
		JFileChooser fileChooser = new JFileChooser("../");
		int ok = fileChooser.showSaveDialog(drwFrm);
		if (ok == JFileChooser.APPROVE_OPTION)
		{
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			FileManager fileManager = new FileManager(new DrawingFile(drwMdl.getShapes()));
			try
			{
				fileManager.write(path);
			}
			catch (IOException exception)
			{
				exception.printStackTrace();
			}
		}
	}
	
	public Point getClick1()
	{
		return click[0];
	}
	
	public void setClick1(int x, int y)
	{
		click[0] = new Point(x, y);
	}
	
	public void setClick1(Point click)
	{
		this.click[0] = click;
	}
	
	public Point getClick2()
	{
		return click[1];
	}
	
	public void setClick2(int x, int y)
	{
		click[1] = new Point(x, y);
	}
	
	public void setClick2(Point click)
	{
		this.click[1] = click;
	}
	
	public ModeAddSel getModeAddSel()
	{
		return modeAddSel;
	}
	
	public void setModeAddSel(ModeAddSel modeAddSel)
	{
		this.modeAddSel = modeAddSel;
	}
	
	public Color getColor1()
	{
		return color[0];
	}
	
	public void setColor1(Color color)
	{
		this.color[0] = color;
	}
	
	public Color getColor2()
	{
		return color[1];
	}
	
	public void setColor2(Color color)
	{
		this.color[1] = color;
	}
	
	public ICommand getCmd(int index)
	{
		return drwMdl.getCmd(index);
	}
	
	public int getSteps(int index)
	{
		return drwMdl.getSteps(index);
	}
}
