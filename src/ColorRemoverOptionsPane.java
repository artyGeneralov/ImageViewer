import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorRemoverOptionsPane{

	private enum rgbEnum{
		RED(0),
		GREEN(1),
		BLUE(2);
		final private int val;
		rgbEnum(int v) {this.val = v;}
		public int getVal() {return this.val;}
	}
	
	final int SLIDER_TICKING = 10;
	int sliderVal;
	int rgb[] = {0xff,0xff,0xff};
	JFrame parent;
	JOptionPane optionPane;
	
	ColorRemoverOptionsPane(JFrame parent)
	{
		this.parent = parent;
		optionPane = new JOptionPane();
		JSlider sliderR = getSlider(optionPane, rgbEnum.RED);
		JSlider sliderG = getSlider(optionPane, rgbEnum.GREEN);
		JSlider sliderB = getSlider(optionPane, rgbEnum.BLUE);
		optionPane.setMessage(new Object[] {"Select a value (%): ",
											"red: ", sliderR, "green: ",sliderG,"blue: ", sliderB});
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = optionPane.createDialog(parent, "My Slider");
		dialog.setSize(new Dimension(600, 600));
		
		setRGBVal(optionPane, rgb);
		
		dialog.setVisible(true);
	}
	
	public int getVal()
	{
		return (int) optionPane.getInputValue();
	}
	
	private JSlider getSlider(final JOptionPane optionPane, rgbEnum sliderId)
	{
		JSlider slider = new JSlider();
		slider.setMaximum(100);
		slider.setMajorTickSpacing(SLIDER_TICKING);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(slider.getMaximum());
		

		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider theSlider = (JSlider) e.getSource();
				if(!theSlider.getValueIsAdjusting())
				{
					rgb[sliderId.getVal()] = 0xff * (int)theSlider.getValue()/100;
					System.out.printf("\n theSlider = %d\n", (int)theSlider.getValue());
					setRGBVal(optionPane, rgb);
				}
			}
		};
		slider.addChangeListener(changeListener);
		return slider;
	}
	
	private void setRGBVal(final JOptionPane optionPane, int[] rgb)
	{
		int temp = 0xffffffff;
		int rgbAns = (temp & (rgb[0] << 16)) + (temp & (rgb[1]<<8)) + (temp & rgb[2]);
		System.out.printf("\n rgb[0] = %x, rgb[1] = %x, rgb[2] = %x , rgbans = %x\n", 
				rgb[0], rgb[1], rgb[2], rgbAns);
		optionPane.setInputValue(new Integer(rgbAns));
	}
}
