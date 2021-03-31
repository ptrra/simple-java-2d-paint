package drawing;
import geometry.Point;
import geometry.Line;
import geometry.Circle;
import geometry.Rectangle;
import geometry.Donut;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import java.awt.Dimension;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JList;

public class FrmDrawing extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JList<String> list = new JList<String>();
	private ButtonGroup btnShapes = new ButtonGroup();
	private static PnlDrawing pnlDrawing;
	private Point[] click = new Point[2];
	private String s;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				}
					catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public FrmDrawing()
	{
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Rakic Petar IT19-2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 900) / 2, (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 600) / 2, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{115, 604, 0};
		gbl_contentPane.rowHeights = new int[]{451, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel pnlLeft = new JPanel();
		pnlLeft.setBackground(Color.WHITE);
		pnlLeft.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		GridBagConstraints gbc_pnlLeft = new GridBagConstraints();
		gbc_pnlLeft.fill = GridBagConstraints.BOTH;
		gbc_pnlLeft.insets = new Insets(0, 0, 0, 5);
		gbc_pnlLeft.gridx = 0;
		gbc_pnlLeft.gridy = 0;
		contentPane.add(pnlLeft, gbc_pnlLeft);
		GridBagLayout gbl_pnlLeft = new GridBagLayout();
		gbl_pnlLeft.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnlLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlLeft.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		pnlLeft.setLayout(gbl_pnlLeft);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 1;
		gbc_rigidArea.gridy = 0;
		pnlLeft.add(rigidArea, gbc_rigidArea);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_1.gridx = 1;
		gbc_rigidArea_1.gridy = 1;
		pnlLeft.add(rigidArea_1, gbc_rigidArea_1);
		
		JLabel lblOperation = new JLabel("Operation:");
		GridBagConstraints gbc_lblOperation = new GridBagConstraints();
		gbc_lblOperation.insets = new Insets(0, 0, 5, 5);
		gbc_lblOperation.gridx = 1;
		gbc_lblOperation.gridy = 2;
		pnlLeft.add(lblOperation, gbc_lblOperation);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (s != null && click[0] != null)
				{
					if (s == "Point")
					{
						DlgPoint dlgAdd = new DlgPoint();
						dlgAdd.setTxt(click[0].getX(), click[0].getY());
						dlgAdd.setVisible(true);
						if (dlgAdd.isOk())
						{
							pnlDrawing.shapesAdd(dlgAdd.getP());
							pnlDrawing.repaint();
						}
						dlgAdd.dispose();
					}
					else if (s == "Line")
					{
						if (click[1] != null)
						{
							DlgLine dlgAdd = new DlgLine();
							dlgAdd.setTxt(click[1].getX(), click[1].getY(), click[0].getX(), click[0].getY());
							dlgAdd.setVisible(true);
							if (dlgAdd.isOk())
							{
								pnlDrawing.shapesAdd(dlgAdd.getL());
								pnlDrawing.repaint();
							}
							dlgAdd.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "You need to click on the drawing panel!");
					}
					else if (s == "Circle")
					{
						DlgCircle dlgAdd = new DlgCircle();
						dlgAdd.setTxt(click[0].getX(), click[0].getY(), 0);
						dlgAdd.setVisible(true);
						if (dlgAdd.isOk())
						{
							pnlDrawing.shapesAdd(dlgAdd.getC());
							pnlDrawing.repaint();
						}
						dlgAdd.dispose();
					}
					else if (s == "Rectangle")
					{
						DlgRectangle dlgAdd = new DlgRectangle();
						dlgAdd.setTxt(click[0].getX(), click[0].getY(), 0, 0);
						dlgAdd.setVisible(true);
						if (dlgAdd.isOk())
						{
							pnlDrawing.shapesAdd(dlgAdd.getR());
							pnlDrawing.repaint();
						}
						dlgAdd.dispose();
					}
					else if (s == "Donut")
					{
						DlgDonut dlgAdd = new DlgDonut();
						dlgAdd.setTxt(click[0].getX(), click[0].getY(), 0, 0);
						dlgAdd.setVisible(true);
						if (dlgAdd.isOk())
						{
							pnlDrawing.shapesAdd(dlgAdd.getD());
							pnlDrawing.repaint();
						}
						dlgAdd.dispose();
					}
				}
				else if (click[0] == null)
				{
					JOptionPane.showMessageDialog(null, "You need to click on the drawing panel!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You need to select a shape!");
				}
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 3;
		pnlLeft.add(btnAdd, gbc_btnAdd);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (pnlDrawing.getIndex() != -1 && pnlDrawing.getSelected() != null)
				{
					if (pnlDrawing.getSelected() instanceof Point)
					{
						Point pomm = (Point) pnlDrawing.getSelected();
						DlgPoint dlgModify = new DlgPoint();
						dlgModify.setTxt(pomm.getX(), pomm.getY());
						dlgModify.setColor(pomm.getColor());
						dlgModify.setVisible(true);
						if (dlgModify.isOk())
						{
							pnlDrawing.shapesRemove(pnlDrawing.getIndex());
							pnlDrawing.shapesAdd(pnlDrawing.getIndex(), dlgModify.getP());
							pnlDrawing.setIndex(-1);
							pnlDrawing.setSelected(null);
							pnlDrawing.repaint();
						}
						dlgModify.dispose();
					}
					else if (pnlDrawing.getSelected() instanceof Line)
					{
						Line pomm = (Line) pnlDrawing.getSelected();
						DlgLine dlgModify = new DlgLine();
						dlgModify.setTxt(pomm.getStartPoint().getX(), pomm.getStartPoint().getY(), pomm.getEndPoint().getX(), pomm.getEndPoint().getY());
						dlgModify.setColor(pomm.getColor());
						dlgModify.setVisible(true);
						if (dlgModify.isOk())
						{
							pnlDrawing.shapesRemove(pnlDrawing.getIndex());
							pnlDrawing.shapesAdd(pnlDrawing.getIndex() ,dlgModify.getL());
							pnlDrawing.setIndex(-1);
							pnlDrawing.setSelected(null);
							pnlDrawing.repaint();
						}
						dlgModify.dispose();
					}
					else if (pnlDrawing.getSelected() instanceof Rectangle)
					{
						Rectangle pomm = (Rectangle) pnlDrawing.getSelected();
						DlgRectangle dlgModify = new DlgRectangle();
						dlgModify.setTxt(pomm.getUpperLeftPoint().getX(), pomm.getUpperLeftPoint().getY(), pomm.getWidth(), pomm.getHeight());
						dlgModify.setColor(pomm.getColor());
						dlgModify.setInnerColor(pomm.getInnerColor());
						dlgModify.setVisible(true);
						if (dlgModify.isOk())
						{
							pnlDrawing.shapesRemove(pnlDrawing.getIndex());
							pnlDrawing.shapesAdd(pnlDrawing.getIndex() ,dlgModify.getR());
							pnlDrawing.setIndex(-1);
							pnlDrawing.setSelected(null);
							pnlDrawing.repaint();
						}
						dlgModify.dispose();
					}
					else if (pnlDrawing.getSelected() instanceof Donut)
					{
						Donut pomm = (Donut) pnlDrawing.getSelected();
						DlgDonut dlgModify = new DlgDonut();
						dlgModify.setTxt(pomm.getCenter().getX(), pomm.getCenter().getY(), pomm.getRadius(), pomm.getInnerRadius());
						dlgModify.setColor(pomm.getColor());
						dlgModify.setInnerColor(pomm.getInnerColor());
						dlgModify.setVisible(true);
						if (dlgModify.isOk())
						{
							pnlDrawing.shapesRemove(pnlDrawing.getIndex());
							pnlDrawing.shapesAdd(pnlDrawing.getIndex() ,dlgModify.getD());
							pnlDrawing.setIndex(-1);
							pnlDrawing.setSelected(null);
							pnlDrawing.repaint();
						}
						dlgModify.dispose();
					}
					else if (pnlDrawing.getSelected() instanceof Circle)
					{
						Circle pomm = (Circle) pnlDrawing.getSelected();
						DlgCircle dlgModify = new DlgCircle();
						dlgModify.setTxt(pomm.getCenter().getX(), pomm.getCenter().getY(), pomm.getRadius());
						dlgModify.setColor(pomm.getColor());
						dlgModify.setInnerColor(pomm.getInnerColor());
						dlgModify.setVisible(true);
						if (dlgModify.isOk())
						{
							pnlDrawing.shapesRemove(pnlDrawing.getIndex());
							pnlDrawing.shapesAdd(pnlDrawing.getIndex() ,dlgModify.getC());
							pnlDrawing.setIndex(-1);
							pnlDrawing.setSelected(null);
							pnlDrawing.repaint();
						}
						dlgModify.dispose();
					}
				}
				else if (click[0] == null)
				{
					JOptionPane.showMessageDialog(null, "You need to click on the drawing panel!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You need to select a shape on the drawing panel!!");
				}
			}
		});
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.insets = new Insets(0, 0, 5, 5);
		gbc_btnModify.gridx = 1;
		gbc_btnModify.gridy = 4;
		pnlLeft.add(btnModify, gbc_btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!pnlDrawing.isShapesEmpty())
				{
					if (pnlDrawing.getIndex() != -1 && pnlDrawing.getSelected() != null)
					{
						if(JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						{
							pnlDrawing.shapesRemove(pnlDrawing.getIndex());
							pnlDrawing.setIndex(-1);
							pnlDrawing.setSelected(null);
							pnlDrawing.repaint();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "You need to select a shape!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are no shapes to be deleted!");
				}
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 5;
		pnlLeft.add(btnDelete, gbc_btnDelete);
		
		JButton btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!pnlDrawing.isShapesEmpty())
				{
					if(JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						pnlDrawing.shapesClear();
						pnlDrawing.repaint();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are no shapes to be deleted!");
				}
			}
		});
		GridBagConstraints gbc_btnDeleteAll = new GridBagConstraints();
		gbc_btnDeleteAll.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteAll.gridx = 1;
		gbc_btnDeleteAll.gridy = 6;
		pnlLeft.add(btnDeleteAll, gbc_btnDeleteAll);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_2 = new GridBagConstraints();
		gbc_rigidArea_2.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_2.gridx = 1;
		gbc_rigidArea_2.gridy = 7;
		pnlLeft.add(rigidArea_2, gbc_rigidArea_2);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_3 = new GridBagConstraints();
		gbc_rigidArea_3.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_3.gridx = 1;
		gbc_rigidArea_3.gridy = 8;
		pnlLeft.add(rigidArea_3, gbc_rigidArea_3);
		
		JLabel lblShapes = new JLabel("Shapes:");
		GridBagConstraints gbc_lblShapes = new GridBagConstraints();
		gbc_lblShapes.insets = new Insets(0, 0, 5, 5);
		gbc_lblShapes.gridx = 1;
		gbc_lblShapes.gridy = 9;
		pnlLeft.add(lblShapes, gbc_lblShapes);
		
		JToggleButton tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s = "Point";
			}
		});
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPoint.gridx = 1;
		gbc_tglbtnPoint.gridy = 10;
		pnlLeft.add(tglbtnPoint, gbc_tglbtnPoint);
		
		btnShapes.add(tglbtnPoint);
		
		JToggleButton tglbtnLine = new JToggleButton("Line");
		tglbtnLine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s = "Line";
			}
		});
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnLine.gridx = 1;
		gbc_tglbtnLine.gridy = 11;
		pnlLeft.add(tglbtnLine, gbc_tglbtnLine);
		btnShapes.add(tglbtnLine);
		
		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s = "Circle";
			}
		});
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCircle.gridx = 1;
		gbc_tglbtnCircle.gridy = 12;
		pnlLeft.add(tglbtnCircle, gbc_tglbtnCircle);
		btnShapes.add(tglbtnCircle);
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s = "Rectangle";
			}
		});
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnRectangle.gridx = 1;
		gbc_tglbtnRectangle.gridy = 13;
		pnlLeft.add(tglbtnRectangle, gbc_tglbtnRectangle);
		btnShapes.add(tglbtnRectangle);
		
		JToggleButton tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				s = "Donut";
			}
		});
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnDonut.gridx = 1;
		gbc_tglbtnDonut.gridy = 14;
		pnlLeft.add(tglbtnDonut, gbc_tglbtnDonut);
		btnShapes.add(tglbtnDonut);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_5 = new GridBagConstraints();
		gbc_rigidArea_5.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_5.gridx = 1;
		gbc_rigidArea_5.gridy = 15;
		pnlLeft.add(rigidArea_5, gbc_rigidArea_5);
		
		list = new JList<String>();
		list.setVisibleRowCount(2);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridx = 1;
		gbc_list.gridy = 16;
		pnlLeft.add(list, gbc_list);
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setModel(dlm);
		dlm.addElement("  Point2  ");
		dlm.addElement("  Point1  ");	
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_6 = new GridBagConstraints();
		gbc_rigidArea_6.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_6.gridx = 1;
		gbc_rigidArea_6.gridy = 17;
		pnlLeft.add(rigidArea_6, gbc_rigidArea_6);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DlgHelp dlgHelp = new DlgHelp();
				dlgHelp.setVisible(true);
				dlgHelp.dispose();
			}
		});
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.insets = new Insets(0, 0, 5, 5);
		gbc_btnHelp.gridx = 1;
		gbc_btnHelp.gridy = 18;
		pnlLeft.add(btnHelp, gbc_btnHelp);
		
		JPanel pnlRight = new JPanel();
		pnlRight.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		GridBagConstraints gbc_pnlRight = new GridBagConstraints();
		gbc_pnlRight.fill = GridBagConstraints.BOTH;
		gbc_pnlRight.gridx = 1;
		gbc_pnlRight.gridy = 0;
		contentPane.add(pnlRight, gbc_pnlRight);
		pnlRight.setLayout(new CardLayout(0, 0));
		
		pnlDrawing = new PnlDrawing(this);
		pnlDrawing.setBackground(Color.WHITE);
		pnlRight.add(pnlDrawing, "");
	}
	
	public Point getClick1()
	{
		return click[0];
	}
	
	public Point getClick2()
	{
		return click[1];
	}
	
	public void setClick1(Point p1)
	{
		this.click[0] = p1;
	}
	
	public void setClick2(Point p2)
	{
		this.click[1] = p2;
	}
	
	public void update()
	{
		dlm.removeAllElements();
		if (click[1] != null)
			dlm.addElement(click[1].toString());
		else
			dlm.addElement("  Point2  ");
		if (click[0] != null)
			dlm.addElement(click[0].toString());
		else
			dlm.addElement("  Point1  ");
		pnlDrawing.setIndex(-1);
		pnlDrawing.setSelected(null);
		for (int i = 0; i < pnlDrawing.getShapesSize(); i++)
		{
			pnlDrawing.setShapesSelected(i, false);
		}
		for (int i = pnlDrawing.getShapesSize() - 1; i >= 0; i--)
		{
			if (pnlDrawing.getShapesShape(i).contains(click[0]))
			{
				pnlDrawing.setShapesSelected(i, true);
				pnlDrawing.setSelected(i);
				pnlDrawing.setIndex(i);
				break;
			}
		}
		pnlDrawing.repaint();
	}
}
