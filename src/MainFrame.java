import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class MainFrame extends JFrame {

	final int FRAME_WIDTH = 800;
	final int FRAME_HEIGHT = 600;
	final Dimension PICTURE_PANEL_DIM = new Dimension(FRAME_WIDTH / 4 * 3,FRAME_HEIGHT);
	final Dimension MENU_PANEL_DIM = new Dimension(FRAME_WIDTH/4, FRAME_HEIGHT);
	final Dimension DEFAULT_BUTTON_DIM = new Dimension(100, 60);
	final Dimension MENU_BUTTON_SPACE_DIM = new Dimension(100,20);
	
	final int PICTURE_BORDER_WIDTH = 3;
	
	
	ImageIcon currentPic;
	JLabel picture;
	
	public MainFrame()
	{
		
		// Declarations:
		JPanel picturePanel = new JPanel();
		JPanel menuPanel = new JPanel();
		
		JButton openButton = new JButton("Open New");
		JButton removeColorBtn = new JButton("Remove Color");
		
		picture = new JLabel();
		JLabel menuTitle = new JLabel("Menu:");
		
		Border pictureBorder = BorderFactory.createLineBorder(Color.black, PICTURE_BORDER_WIDTH);
		
		// picture placeholder:
		currentPic = new ImageIcon("Images/sample1.jpg");
		
		// picturePanel Settings:
		picturePanel.setBackground(Color.red);
		picturePanel.setPreferredSize(PICTURE_PANEL_DIM);
		picturePanel.setBorder(pictureBorder);
		picturePanel.setLayout(new GridLayout());
		picturePanel.add(picture);
		
		// menuPanel Settings:
		menuPanel.setBackground(Color.blue);
		menuPanel.setPreferredSize(new Dimension(MENU_PANEL_DIM));
		
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		
		menuPanel.add(menuTitle);
		menuPanel.add(Box.createRigidArea(MENU_BUTTON_SPACE_DIM));
		menuPanel.add(openButton);
		menuPanel.add(Box.createRigidArea(MENU_BUTTON_SPACE_DIM));
		menuPanel.add(removeColorBtn);
		
		
		// openButton Settings:
		
		openButton.setBackground(Color.cyan);
		openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				loadIcon();
			}
		});
		
		// removeColorButton Settings:
		
		removeColorBtn.setBackground(new Color(0xdb2491));
		removeColorBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		removeColorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				openColorRemoverWindow();
			}
		});
		
		// picture label Settings:
		
		picture.setVerticalTextPosition(JLabel.CENTER);
		picture.setHorizontalTextPosition(JLabel.CENTER);
		picture.setVerticalAlignment(JLabel.CENTER);
		picture.setHorizontalAlignment(JLabel.CENTER);
		picture.setBorder(pictureBorder);
		picture.setIcon(currentPic);
		
		// menu title Settings:
		menuTitle.setFont(new Font("MV Boli", Font.BOLD, 20));
		menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		
		// Main frame Settings:
		this.setLayout(new BorderLayout());
		this.add(picturePanel, BorderLayout.EAST);
		this.add(menuPanel, BorderLayout.WEST);
		
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	public void loadIcon()
	{
		
	}
	
	public void openColorRemoverWindow()
	{
		ColorRemoverOptionsPane c = new ColorRemoverOptionsPane(this);
		int answer = c.getVal();
		//JOptionPane.showMessageDialog(null, answer);
		//System.out.printf("%x", answer);
		if(currentPic != null)
		{
			ImageProcessor p = new ImageProcessor(currentPic);
			BufferedImage i = p.changeColorScheme(answer);
			ImageIcon ic = new ImageIcon(i);
			currentPic = ic;
			updateImage();
		}
			
	}
	
	
	private void updateImage()
	{
		picture.setIcon(currentPic);
	}
}
