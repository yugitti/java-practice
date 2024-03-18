import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DiffSampleInherit {
    public static void run(){
        var f = new JFrame("diff sample");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var img = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        var g = img.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0,0,600,400);
        g.drawImage(lineImage(), 10, 10, f);
        g.drawImage(rectImage(), 300, 80, f);
        var label = new JLabel(new ImageIcon(img));
        f.add(label);
        f.pack();
        f.setVisible(true);
    }

    // no diff
    static BufferedImage lineImage(){
        var image = new BufferedImage(250, 200, BufferedImage.TYPE_INT_RGB);
        var graphics = image.createGraphics();
        graphics.drawLine(10, 10, 220, 180);
        return image;
    }

    static BufferedImage rectImage(){
        var image = new BufferedImage(250, 200, BufferedImage.TYPE_INT_RGB);
        var graphics = image.createGraphics();
        graphics.drawRect(10, 10, 220, 180);
        return image;
    }

    // diff by inherit class
    public static void run2(){
        var f = new JFrame("diff sample");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var img = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        var g = img.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0,0,600,400);
        g.drawImage(lineImage2(), 10, 10, f);
        g.drawImage(rectImage2(), 300, 80, f);
        var label = new JLabel(new ImageIcon(img));
        f.add(label);
        f.pack();
        f.setVisible(true);
    }

    static abstract class ImageDrawer{
        BufferedImage createImage(){
            var image = new BufferedImage(250, 200, BufferedImage.TYPE_INT_RGB);
            var graphics = image.createGraphics();
            draw(graphics);
            return image;
        }

        abstract void draw(Graphics2D g);
    }

    static class LineDrawer extends ImageDrawer{

        @Override
        void draw(Graphics2D g) {
            g.drawLine(10, 10, 220, 180);
        }
    }

    static class RectDrawer extends ImageDrawer{
        @Override
        void draw(Graphics2D g){
            g.drawRect(10, 10, 220, 180);
        }
    }

    static BufferedImage lineImage2(){
        return new LineDrawer().createImage();
    }

    static BufferedImage rectImage2(){
        return new RectDrawer().createImage();
    }
}
