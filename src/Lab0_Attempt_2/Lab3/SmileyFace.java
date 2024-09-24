package Lab0_Attempt_2.Lab3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SmileyFace extends Shape {
    private final double radius;

    public SmileyFace(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getAuthor() {
        return "Jacob";
    }

    @Override
    public double getPerimiter() {
        // Perimeter of a hexagon = 6 * side length
        double sideLength = radius; // Using radius as the length of each side
        return 6 * sideLength;
    }

    @Override
    public double getArea() {
        // Area of a hexagon = (3 * √3 / 2) * side^2
        double sideLength = radius; // Using radius as the length of each side
        return (3 * Math.sqrt(3) / 2) * sideLength * sideLength;
    }

    public static void main(String[] args) throws Exception {
        SmileyFace smiley = new SmileyFace(100);
        smiley.renderAsJpeg(new File("c:\\temp\\smiley_face_radius100.jpg"));
    }

    @Override
    public void renderAsJpeg(File fileToJpeg) throws Exception {
        
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

        // Get radius
        double r = this.radius;

        // Calculate hexagon vertices
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (width / 2 + r * Math.cos(i * Math.PI / 3));
            yPoints[i] = (int) (height / 2 + r * Math.sin(i * Math.PI / 3));
        }

        // Draw the head (hexagon)
        g2d.setColor(getColor());
        g2d.fillPolygon(xPoints, yPoints, 6);

        // Set facial feature colors
        if (getColor().equals(Color.BLACK))
            g2d.setColor(Color.WHITE);
        else
            g2d.setColor(Color.BLACK);

        // Get horizontal center
        int centerX = width / 2;

        // Draw the smile
        int smileHeight = (int)(r / 5);
        int smileWidth = (int)(r / 2); // Width of the smile
        int smileX = centerX - (smileWidth / 2);
        int smileY = (int)((height - smileHeight) / 2 + r / 4); // Centered vertically

        // Draw the eyes
        int eyeSize = (int)(r / 10);
        int eyeDistanceFromCenter = (int)(r / 4); // How far apart the eyes will be
        int leftEyeX = centerX - eyeDistanceFromCenter - eyeSize / 2; // Left eye
        int rightEyeX = centerX + eyeDistanceFromCenter - eyeSize / 2; // Right eye
        int eyeY = smileY - eyeSize; // Place the eyes above the mouth

        g2d.fillOval(leftEyeX, eyeY, eyeSize, eyeSize);
        g2d.fillOval(rightEyeX, eyeY, eyeSize, eyeSize);

        g2d.drawArc(smileX, smileY, smileWidth, smileHeight, 0, -180);

        addAuthorText(g2d, width, height);

        // Dispose the graphics object
        g2d.dispose();

        // Save the image as a JPEG file
        ImageIO.write(image, "jpg", fileToJpeg);
        System.out.println("Image saved as smiley_face.jpg");
    }
}
