package Lab3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPEG_Shape_Displayers
{
	
	public static void main(String[] args) throws Exception
	{
		Color[] colors = {Color.BLACK, Color.GRAY, Color.GREEN, Color.RED, Color.BLUE}; 
		
		List<Shape> list = new ArrayList<>();
		
		for( int x=1; x<= 3; x++)
		{
			Circle c = new Circle(20*x);
			c.setColor(colors[x-1]);
			list.add(c);
		}
		
		for(int x=1; x <= 3; x++)
		{
			Triangle t = new Triangle(20*x, 30*x);
			t.setColor(colors[x-1]);
			list.add(t);
		}
		
		/*
		for( int x=1; x <= 4; x++)
		{
			Square sq = new Square(20*x);
			sq.setColor(colors[x-1]);
			list.add(sq);
		}
		
		Square
		for( int x=1; x <= 4; x++)
		{
			Square sq = new Square(20*x);
			sq.setColor(colors[x-1]);
			list.add(sq);
		}
		*/
		
		
		for( int x=1; x <= 4; x++)
		{
			Tesseract ts = new Tesseract(x);
			ts.setColor(colors[x-1]);
			list.add(ts);
		}
					
		ShowImages(list);
	}
	   
	// modified from ChatGPT query "Make code in Java to display 10 256 * 256 jpeg images"
	// will show the first 10 images in the list
	// makes and deletes a tempFile called "shapeTemp.jpg"
	public static void ShowImages(List<Shape> list) throws Exception
	{
		// Create a JFrame to hold the images
	    JFrame frame = new JFrame("Display 10 Shapes");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(800, 800); // Adjust the size as needed

	    // Create a JPanel with a grid layout (2 rows, 5 columns)
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(2, 5));

	    // Load and add 10 images
	     for (int i = 0; i < 10; i++) 
	     {
	    	 File tempFile = new File("shapeTemp.jpg");
	    	 Shape s= list.get(i);
	    	 s.renderAsJpeg(tempFile);
	         BufferedImage img = ImageIO.read(tempFile);
	               
	         ImageIcon icon = new ImageIcon(img);
	                
	          // Create a JLabel with the ImageIcon
	          JLabel label = new JLabel(icon);
	                
	          // Add the label to the panel
	          panel.add(label);
	      }

	       // Add the panel to the frame
	       frame.add(panel);
	        
	        // Make the frame visible
	        frame.setVisible(true);
	    }

}
