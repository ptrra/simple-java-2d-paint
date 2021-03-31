package stack;
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

public class FrmStack extends JFrame
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
					FrmStack frame = new FrmStack();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public FrmStack()
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
				DlgStack dlgAdd = new DlgStack();
				dlgAdd.setVisible(true);
				if (dlgAdd.isOk())
				{
					rr.add(dlgAdd.getR());
					dlm.removeAllElements();
					for (int i = rr.size() - 1; i >= 0 ; i--)
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
				if (!dlm.isEmpty())
				{
					DlgStack dlgModify = new DlgStack();
					dlgModify.setTxt(rr.get(rr.size() - listRectangle.getSelectedIndex() - 1).toString().split(","));
					dlgModify.setVisible(true);
					if (dlgModify.isOk())
					{
						rr.remove(rr.size() - listRectangle.getSelectedIndex() - 1);
						rr.add(rr.size() - listRectangle.getSelectedIndex(), dlgModify.getR());
						dlm.removeAllElements();
						for (int i = rr.size() - 1; i >= 0 ; i--)
						{
							dlm.addElement(rr.get(i).toString());
						}
					}
					dlgModify.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The list is empty!");
				}
			}
		});
		pnlSouth.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!dlm.isEmpty())
				{
					DlgStack dlgDelete = new DlgStack();
					dlgDelete.setTxt(rr.get(rr.size() - 1).toString().split(","));
					dlgDelete.setVisible(true);
					if (dlgDelete.isOk())
					{
						rr.remove(rr.size() - 1);
						dlm.removeAllElements();
						for (int i = rr.size() - 1; i >= 0 ; i--)
						{
							dlm.addElement(rr.get(i).toString());
						}
					}
					dlgDelete.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The list is empty!");
				}
			}
		});
		pnlSouth.add(btnDelete);
	}
}