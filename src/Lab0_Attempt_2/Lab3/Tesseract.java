package Lab0_Attempt_2.Lab3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Tesseract extends Shape {
    private final double size;

    public Tesseract(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    @Override
    public double getArea() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public double getPerimiter() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getAuthor() {
        return "Anthony";
    }

    @Override
    public void renderAsJpeg(File fileToJpeg) throws Exception {
        int width = 256;
        int height = 256;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(this.getColor());

        // Define the vertices of the tesseract in 4D space
        int[][] points = new int[16][4];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                points[i][j] = (i & (1 << j)) == 0 ? -1 : 1;
            }
        }

        // Project 4D points into 2D space
        int[][] projected = new int[16][2];
        for (int i = 0; i < 16; i++) {
            double x = points[i][0] * size;
            double y = points[i][1] * size;
            double z = points[i][2] * size;
            double w = points[i][3] * size;

            // Perspective projection
            double perspective = 1 / (1 + w / 4);
            projected[i][0] = (int) (x * perspective) + width / 2;
            projected[i][1] = (int) (y * perspective) + height / 2;
        }

        // Draw edges between the projected points
        for (int i = 0; i < 16; i++) {
            for (int j = i + 1; j < 16; j++) {
                if (Integer.bitCount(i ^ j) == 1) {
                    g2d.drawLine(projected[i][0], projected[i][1], projected[j][0], projected[j][1]);
                }
            }
        }

        addAuthorText(g2d, width, height);
        g2d.dispose();
        ImageIO.write(image, "jpeg", fileToJpeg);
        System.out.println("Tesseract image saved as tesseract.jpg");
    }

    public static void main(String[] args) throws Exception {
        Tesseract tesseract = new Tesseract(100);
        tesseract.renderAsJpeg(new File("c:\\temp\\tesseract.jpg"));
    }
}
