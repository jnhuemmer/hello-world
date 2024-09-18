package Lab3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Square extends Shape
{
	private final double sideLength;
	
	@Override
	public String getAuthor()
	{
		return "Anthony";
	}
	
	public Square(double sideLength)
	{
		this.sideLength = sideLength;
	}

	@Override
	public double getPerimiter()
	{
		return 4 * sideLength;
	}

	@Override
	public double getArea()
	{
		return sideLength * sideLength;
	}

	@Override
	// modified from chatGPT from the query "write code in Java to represent a square of side length s as a 256 by 256 jpeg file"
	public void renderAsJpeg(File fileToJpeg) throws Exception
	{
		// Set the image dimensions
        int width = 256;
        int height = 256;

        // Create a BufferedImage object
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get the Graphics2D object
        Graphics2D g2d = image.createGraphics();

        // Set the background color (white)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        
        // Set the square color (black)
        g2d.setColor(getColor());

        // Draw the square centered in the image
        int x = (int)( (width - sideLength) / 2);
        int y = (int) ((height - sideLength) / 2);
        g2d.fillRect(x, y, (int)sideLength, (int)sideLength);

        addAuthorText(g2d, width, height);
        // Dispose the graphics object
        g2d.dispose();

       ImageIO.write(image, "jpg", fileToJpeg);
        System.out.println("Image saved as square.jpg");
        
	}

}
