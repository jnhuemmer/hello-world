package Lab3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Circle extends Shape
{
	private final double radius;
	
	public Circle(double radius)
	{
		this.radius = radius;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	@Override
	public String getAuthor()
	{
		return "Anthony";
	}

	@Override
	public double getPerimiter()
	{
		return Math.PI * 2 * radius;
	}

	@Override
	public double getArea()
	{
		return Math.PI*radius*radius;
	}

	public static void main(String[] args) throws Exception
	{
		Circle c = new Circle(100);
		c.renderAsJpeg(new File("c:\\temp\\circle_radius100.jpg"));
		
		Square s = new Square(100);
		s.renderAsJpeg(new File("c:\\temp\\square_side_100.jpg"));
	}
	
	@Override
	// modified from chatGTP query "write code in Java to represent a circle of radius r as a 256 by 256 jpeg file"
	public void renderAsJpeg(File fileToJpeg) throws Exception
	{// Set the image dimensions
        int width = 256;
        int height = 256;

        double r = this.radius; // Change this value as needed
        
        // Create a BufferedImage object
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get the Graphics2D object
        Graphics2D g2d = image.createGraphics();

        // Set the background color (white)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        
        // Set the circle color (black)
        g2d.setColor(getColor());

        // Draw the circle centered in the image
        double x = (width - 2 * r) / 2;
        double y = (height - 2 * r) / 2;
        g2d.fillOval((int)x, (int)y, (int)(2 * r), (int)(2 * r));

        addAuthorText(g2d, width, height);
        
        // Dispose the graphics object
        g2d.dispose();

        // Save the image as a JPEG file
            ImageIO.write(image, "jpg", fileToJpeg);
            System.out.println("Image saved as circle.jpg");
 
	}

}
