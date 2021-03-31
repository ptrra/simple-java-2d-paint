package drawing;
import geometry.Point;
import geometry.Circle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DlgCircle extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean ok;
	private Circle c;
	private Color color = Color.green;
	private Color innerColor = Color.yellow;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtR;

	public static void main(String[] args)
	{
		try
		{
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public DlgCircle()
	{
		setTitle("Circle");
		setModal(true);
		setResizable(false);
		setBounds((int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 160) / 2, (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 210) / 2, 160, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblR = new JLabel("R:");
			GridBagConstraints gbc_lblR = new GridBagConstraints();
			gbc_lblR.anchor = GridBagConstraints.EAST;
			gbc_lblR.insets = new Insets(0, 0, 5, 5);
			gbc_lblR.gridx = 0;
			gbc_lblR.gridy = 2;
			contentPanel.add(lblR, gbc_lblR);
		}
		{
			txtR = new JTextField();
			GridBagConstraints gbc_txtR = new GridBagConstraints();
			gbc_txtR.insets = new Insets(0, 0, 5, 0);
			gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtR.gridx = 1;
			gbc_txtR.gridy = 2;
			contentPanel.add(txtR, gbc_txtR);
			txtR.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Color");
			btnColor.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					color = JColorChooser.showDialog(btnColor, "Color", color);
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 3;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JButton btnInnerColor = new JButton("InnerColor");
			btnInnerColor.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					innerColor = JColorChooser.showDialog(btnInnerColor, "InnerColor", innerColor);
				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.gridx = 1;
			gbc_btnInnerColor.gridy = 4;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
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
						try
						{
							c = new Circle(new Point(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())), 
										Integer.parseInt(txtR.getText()), false, color, innerColor);
							ok = true;
							setVisible(false);
				        }
						catch (NumberFormatException ee)
						{
				        	ok = false;
				        	JOptionPane.showMessageDialog(null, "Valid values haven't been entered!");
				        }
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
						ok = false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean isOk()
	{
		return this.ok;
	}
	
	public Circle getC()
	{
		return this.c;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public void setInnerColor (Color innerColor)
	{
		this.innerColor = innerColor;
	}
	
	public void setTxt(int x, int y, int r)
	{
		txtX.setText(Integer.toString(x));
		txtY.setText(Integer.toString(y));
		if (r != 0)
			txtR.setText(Integer.toString(r));
	}
}
