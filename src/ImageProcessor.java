import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageProcessor {
	BufferedImage image;
	
	ImageProcessor(BufferedImage image)
	{
		this.image = image;
	}
	
	ImageProcessor(ImageIcon icon)
	{
		this.image = convertImageIconToBufferedImage(icon);
	}
	
	public BufferedImage getImage()
	{
		return this.image;
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage changeColorScheme(int colorMask)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int r = colorMask & 0x00ff0000;
		int g = colorMask & 0x0000ff00;
		int b = colorMask & 0x000000ff;
		//System.out.println("here");
		System.out.printf(" r = %x, g = %x, b = %x \n", r, g, b);
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				int curPix = image.getRGB(col, row);
				int newr = r & curPix;
				int newg = g & curPix;
				int newb = b & curPix;
				int newRGB = 0xff000000 + newr + newg + newb; //alpha + rgb
				image.setRGB(col, row, newRGB);
			}
		}
		return getImage();
	}
	
	private BufferedImage convertImageIconToBufferedImage(ImageIcon ic)
	{
		BufferedImage temp = new BufferedImage(
				ic.getIconWidth(),
				ic.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = temp.createGraphics();
		ic.paintIcon(null, g, 0, 0);
		g.dispose();
		return temp;
	}
	
}















