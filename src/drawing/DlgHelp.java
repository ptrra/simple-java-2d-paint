package drawing;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DlgHelp extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JList<String> listHelp;
	
	public static void main(String[] args)
	{
		try
		{
			DlgHelp dialog = new DlgHelp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public DlgHelp()
	{
		setTitle("Help");
		setModal(true);
		setResizable(false);
		setBounds((int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 750) / 2, (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 500) / 2, 750, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JScrollPane scrlPane = new JScrollPane();
			contentPanel.add(scrlPane, "name_37331342981600");
			{
				listHelp = new JList<String>();
				listHelp.setFont(new Font("Tahoma", Font.PLAIN, 14));
				scrlPane.setViewportView(listHelp);
				listHelp.setModel(dlm);
				dlm.addElement("If an error keeps popping up about valid values, you should know:");
				dlm.addElement("-Values should be numbers and not characters.");
				dlm.addElement("-Coordinates (X,Y), Radius (R), InnerRadius (IR), Width (W), Height(H) all have to be greater than 0!");
				dlm.addElement("When clicking on the drawing panel, sometimes the click won't work.");
				dlm.addElement("So wait a second and then try again.");
				dlm.addElement("If a color is not selected, default colors will be applied.");
				dlm.addElement("In this app your current and one click before are remembered.");
				dlm.addElement("Only for adding a line you need two clicks (click before and current).");
				dlm.addElement("For everything else you need one click (only current click).");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
