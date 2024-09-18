package Lab3;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.File;

public abstract class Shape implements Comparable<Shape>
{
	public abstract double getPerimiter();
	public abstract double getArea();
	public abstract String getAuthor();
	public abstract void renderAsJpeg(File fileToJpeg) throws Exception;
	
	private Color drawingColor = Color.BLACK;
	
	/*
	 * Modified from chatGTP query "java code to center text is a 256 by 256 image"

	 */
	protected void addAuthorText(Graphics2D g2d, int width, int height) throws Exception
	{
		String text = getAuthor(); // The text to be centered

		// Set font and size
        Font font = new Font("Arial", Font.BOLD, 24);
        g2d.setColor(Color.black);
        g2d.setFont(font);

        // Get the FontMetrics object to calculate the position
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        // Calculate the coordinates to center the text
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fm.getAscent();

        // Draw the text centered
        g2d.drawString(text, x, y);

	}
	
	@Override
	public int compareTo(Shape o)
	{
		return Double.compare(this.getArea(), o.getArea());
	}
	
	public Color getColor() 
	{
		return drawingColor;
	}
	
	public void setColor(Color color)
	{
		this.drawingColor = color;
	}
}

