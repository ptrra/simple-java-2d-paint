package sort;
import geometry.Rectangle;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;

public class FrmSort extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JList<String> listRectangle = new JList<String>();
	private ArrayList<Rectangle> rr = new ArrayList<Rectangle>();

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public FrmSort()
	{
		setTitle("Rakic Petar IT19-2019");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 450) / 2, (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 300) / 2, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrlPaneRectangle = new JScrollPane();
		pnlCenter.add(scrlPaneRectangle);
		listRectangle.setVisibleRowCount(12);
		listRectangle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrlPaneRectangle.setViewportView(listRectangle);
		listRectangle.setModel(dlm);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DlgSort dlgAdd = new DlgSort();
				dlgAdd.setTitle("Add");
				dlgAdd.setVisible(true);
				if (dlgAdd.isOk())
				{
					rr.add(dlgAdd.getR());
					dlm.removeAllElements();
					Rectangle pom1;
					Rectangle pom2;
					for (int i = 0; i < rr.size() ; i++)
					{
						for (int j = i + 1; j < rr.size(); j++)
						{
							if (rr.get(i).area() > rr.get(j).area())
							{
								pom1 = rr.get(i);
								pom2 = rr.get(j);
								rr.remove(i);
								rr.add(i,pom2);
								rr.remove(j);
								rr.add(j,pom1);
							}
						}
					}
					for (int i = 0; i < rr.size() ; i++)
					{
						dlm.addElement(rr.get(i).toString());
					}
				}
				dlgAdd.dispose();
			}
		});
		pnlSouth.add(btnAdd);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!listRectangle.isSelectionEmpty())
				{
					DlgSort dlgModify = new DlgSort();
					dlgModify.setTxt(rr.get(rr.size() - listRectangle.getSelectedIndex() - 1));
					dlgModify.setTitle("Modify");
					dlgModify.setVisible(true);
					if (dlgModify.isOk())
					{
						rr.remove(listRectangle.getSelectedIndex());
						rr.add(listRectangle.getSelectedIndex(), dlgModify.getR());
						dlm.removeAllElements();
						Rectangle pom1;
						Rectangle pom2;
						for (int i = 0; i < rr.size() ; i++)
						{
							for (int j = i + 1; j < rr.size(); j++)
							{
								if (rr.get(i).area() > rr.get(j).area())
								{
									pom1 = rr.get(i);
									pom2 = rr.get(j);
									rr.remove(i);
									rr.add(i,pom2);
									rr.remove(j);
									rr.add(j,pom1);
								}
							}
						}
						for (int i = 0; i < rr.size() ; i++)
						{
							dlm.addElement(rr.get(i).toString());
						}
					}
					dlgModify.dispose();
				}
				else if (dlm.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "The list is empty!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You have to select an item!");
				}
			}
		});
		pnlSouth.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!listRectangle.isSelectionEmpty())
				{
					DlgSort dlgDelete = new DlgSort();
					dlgDelete.setTxt(rr.get(rr.size() - listRectangle.getSelectedIndex() - 1), false);
					dlgDelete.setTitle("Delete");
					dlgDelete.setVisible(true);
					if (dlgDelete.isOk())
					{
						rr.remove(listRectangle.getSelectedIndex());
						dlm.removeAllElements();
						Rectangle pom1;
						Rectangle pom2;
						for (int i = 0; i < rr.size() ; i++)
						{
							for (int j = i + 1; j < rr.size(); j++)
							{
								if (rr.get(i).area() > rr.get(j).area())
								{
									pom1 = rr.get(i);
									pom2 = rr.get(j);
									rr.remove(i);
									rr.add(i,pom2);
									rr.remove(j);
									rr.add(j,pom1);
								}
							}
						}
						for (int i = 0; i < rr.size() ; i++)
						{
							dlm.addElement(rr.get(i).toString());
						}
					}
					dlgDelete.dispose();
				}
				else if (dlm.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "The list is empty!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You have to select an item!");
				}
			}
		});
		pnlSouth.add(btnDelete);
	}
}
