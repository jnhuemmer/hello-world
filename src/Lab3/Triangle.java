package Lab3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Triangle extends Shape
{
	private final double base;
	private final double height;
	
	
	public double getBase()
	{
		return base;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	
	
	public Triangle(double base, double height)
	{
		
		this.base = base;
		this.height = height;
	}

	@Override
	public double getArea()
	{
		return 0.5 * base * height;
	}
	
	@Override
	public double getPerimiter()
	{
		// not implemented
		
		throw  new RuntimeException("Not implemented");
		
	}
	
	@Override
	public String getAuthor()
	{
		return "Anthony";
	}
	
	@Override
	public void renderAsJpeg(File fileToJpeg) throws Exception
	{
		double b = this.base; // Base of the triangle
	    double h = this.height;// Height of the triangle
	    int width = 256; // Image width
	    int height = 256; // Image height
	        
	        // Create a buffered image with dimensions 256x256
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        
	        // Get the graphics object
	        Graphics2D g2d = image.createGraphics();
	        
	        // Set background color (white)
	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(0, 0, width, height);
	        
	  
	        // Set color for the triangle (black)
	        g2d.setColor(this.getColor());
	        
	        // Define the coordinates for the triangle
	        int[] xPoints = {
	            (int)((width - b) / 2), // Left point (x)
	            (int)((width + b) / 2), // Right point (x)
	            width / 2         // Top point (x)
	        };
	        int[] yPoints = {
	            (int)(height - (height - h) / 2), // Bottom left (y)
	            (int)(height - (height - h) / 2), // Bottom right (y)
	            (int)(height - h) / 2            // Top (y)
	        };
	        
	        // Draw the triangle
	        g2d.fillPolygon(xPoints, yPoints, 3);
	  

	        addAuthorText(g2d, width, height);
	        // Dispose the graphics object
	        g2d.dispose();
	        
	        	            ImageIO.write(image, "jpeg", fileToJpeg);
	            System.out.println("Triangle image saved as triangle.jpg");
	}
	
	public static void main(String[] args) throws Exception
	{
		Triangle t = new Triangle(30, 15);
		t.renderAsJpeg(new File("c:\\temp\\triangle.jpg"));
	}
}
