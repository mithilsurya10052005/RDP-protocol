import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JPanel;

public class SendEvents implements KeyListener, MouseMotionListener, MouseListener {
  private Socket cSocket = null;
  private JPanel cPanel = null;
  private PrintWriter writer = null;
  String width = "", height = "";
  double w;
  double h;

  public SendEvents(Socket s, JPanel p, String width, String height) {
    cSocket = s;
    cPanel = p;
    this.width = width;
    this.height = height;
    w = Double.parseDouble(width.trim());
    h = Double.parseDouble(height.trim());
    cPanel.addKeyListener(this);
    cPanel.addMouseListener(this);
    cPanel.addMouseMotionListener(this);
    try {
      writer = new PrintWriter(cSocket.getOutputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mouseDragged(MouseEvent e) {
    
  }

  public void mousePressed(MouseEvent e) {
    writer.println(Commands.PRESS_MOUSE.getAbbrev());
    int button = e.getButton();
    int xButton = 16;
    if (button == 3) {
      xButton = 4;
    }
    writer.println(xButton);
    writer.flush();
  }

  public void mouseMoved(MouseEvent e) {
    double xscale = w / cPanel.getWidth();
    double yscale = h / cPanel.getHeight();
    writer.println(Commands.MOVE_MOUSE.getAbbrev());
    writer.println((int) (e.getX() * xscale));
    writer.println((int) (e.getY() * yscale));
    writer.flush();
  }

  public void mouseClicked(MouseEvent e) {

  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
    writer.println(Commands.RELEASE_MOUSE.getAbbrev());
    int button = e.getButton();
    int xButton = 16;
    if (button == 3) {
      xButton = 4;
    }
    writer.println(xButton);
    writer.flush();
  }

  public void keyTyped(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {
    writer.println(Commands.PRESS_KEY.getAbbrev());
    writer.println(e.getKeyCode());
    writer.flush();
  }

  public void keyReleased(KeyEvent e) {
    writer.println(Commands.RELEASE_KEY.getAbbrev());
    writer.println(e.getKeyCode());
    writer.flush();
  }
}
