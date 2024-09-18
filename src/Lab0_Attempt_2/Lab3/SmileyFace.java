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
        return Math.PI * 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
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

        // Draw the smiley face
        double r = this.radius;

        // Draw the face (yellow)
        g2d.setColor(getColor());
        g2d.fillOval((int)((width - 2 * r) / 2), (int)((height - 2 * r) / 2), (int)(2 * r), (int)(2 * r));

        // Draw the eyes (black)
        
        if (getColor().equals(Color.BLACK))
        	g2d.setColor(Color.WHITE);
        else
        	g2d.setColor(Color.BLACK);

        int eyeSize = (int)(r / 10);
     
        // Centering the eyes
        
        
        int centerX = width / 2;
        
        int eyeDistanceFromCenter = (int)(r / 4); // You can adjust this value as needed
        
        int leftEyeX = centerX - eyeDistanceFromCenter - eyeSize / 2; // Left eye
        int rightEyeX = centerX + eyeDistanceFromCenter - eyeSize / 2; // Right eye
        
        
        /* We are cooked
        
        int leftEyeX = (int)((width - 2 * r) / 2 + (3 * r / 4) - eyeSize / 2); // Left eye remains in place
        int distanceToCenter = leftEyeX - centerX;
        int rightEyeX = centerX + Math.abs(distanceToCenter); // Right eye positioned to the right of the left eye

        

        //int eyeDistance = (int)(r / 2); // Distance between eyes
        //int rightEyeX = leftEyeX + eyeDistance; // Right eye positioned to the right of the left eye

*/
        
        // Draw the smile (black) - centered
        int smileHeight = (int)(r / 5);
        int smileWidth = (int)(r / 3); // Width of the smile
        int smileX = centerX - (smileWidth / 2); 
        int smileY = (int)((height - smileHeight) / 2 + r / 4); // Centered vertically

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
