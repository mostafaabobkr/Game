package engine;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import engine.MyContent;
import exceptions.FullHandException;

public class Start extends JFrame implements ActionListener {
	private MyContent window;
	private JButton start;
	private JButton help;
	private JButton exit;
	private JButton enter;
	private JButton enter1;
	private JButton enter2;
	private JButton play1;
	private JButton play2;
	private String Heroselected1;
	private String Heroselected2;
	private JTextArea a;
	private JTextArea b;
	private JTextField name1;
	private JTextField name2;
	private JTextArea error;
	private JButton Mage1;
	private JButton Hunter1;
	private JButton Paladin1;
	private JButton Priest1;
	private JButton Warlock1;
	private JButton Mage2;
	private JButton Hunter2;
	private JButton Paladin2;
	private JButton Priest2;
	private JButton Warlock2;
	private int c;
	private int c1;
	private int c2;
	private String player1;
	private String player2;
	private GameView gameview;
	private Dimension size;
	private static Start s;

	public Start() throws IOException, CloneNotSupportedException {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		File pathToFile = new File("images/Hearthstone1.png");
		Image image = ImageIO.read(pathToFile);
		size = Toolkit.getDefaultToolkit().getScreenSize();
		image = image.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		window = new MyContent(image);
		this.setContentPane(window);
		this.revalidate();
		this.repaint();
		this.setLayout(null);
		// ------------------------------
		start = new JButton();
		start.setBounds((int) (525.0 / 1366 * size.width), (int) (525.0 / 786 * size.height),
				(int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height));
		Image imgStart = ImageIO.read(new File("images/Start.PNG"));
		imgStart = imgStart.getScaledInstance((int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height),
				java.awt.Image.SCALE_SMOOTH);
		start.setIcon(new ImageIcon(imgStart));
		help = new JButton();
		Image imgHelp = ImageIO.read(new File("images/help.PNG"));
		help.setBounds((int) (650.0 / 1366 * size.width), (int) (525.0 / 786 * size.height),
				(int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height));
		imgHelp = imgHelp.getScaledInstance((int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height),
				java.awt.Image.SCALE_SMOOTH);
		start.setIcon(new ImageIcon(imgStart));
		help.setIcon(new ImageIcon(imgHelp));
		exit = new JButton();
		exit.setBounds((int) (775.0 / 1366 * size.width), (int) (525.0 / 786 * size.height),
				(int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height));
		Image imgExit = ImageIO.read(new File("images/exit.PNG"));
		imgExit = imgExit.getScaledInstance((int) (100.0 / 1366 * size.width), (int) (50.0 / 768 * size.height),
				java.awt.Image.SCALE_SMOOTH);
		exit.setIcon(new ImageIcon(imgExit));
		help.addActionListener(this);
		start.addActionListener(this);
		exit.addActionListener(this);
		add(start);
		add(help);
		add(exit);
		// ------------------------------
		a = new JTextArea("Enter Your Name");
		Font f = new Font("TimesRoman", Font.BOLD, 26);
		a.setForeground(Color.white);
		a.setFont(f);
		a.setOpaque(false);
		a.setEditable(false);
		name1 = new JTextField();
		name1.setText("user");
		name1.setForeground(Color.white);
		name1.setBackground(new Color(139, 69, 19));
		enter = new JButton();
		enter.addActionListener(this);
		enter1 = new JButton();
		enter1.addActionListener(this);
		// -------------------------------
		b = new JTextArea("Second Player's name");
		b.setForeground(Color.white);
		b.setFont(f);
		b.setOpaque(false);
		b.setEditable(false);
		name2 = new JTextField();
		name2.setText("user");
		name2.setForeground(Color.white);
		name2.setBackground(new Color(139, 69, 19));
		name2.setBounds((int) (525.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
				(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
		enter2 = new JButton();
		enter2.addActionListener(this);
		enter2.setBounds((int) (750.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
				(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
		// -------------------------------
		play1 = new JButton();
		play2 = new JButton();
		play1.setBounds(size.width / 2 - (int) (200.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
				(int) (150.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
		Image imgPlay1 = ImageIO.read(new File("images/oneplayer.PNG"));
		imgPlay1 = imgPlay1.getScaledInstance(play1.getWidth(), play1.getHeight(), java.awt.Image.SCALE_SMOOTH);
		play1.setIcon(new ImageIcon(imgPlay1));
		play2.setBounds(size.width / 2 + (int) (50.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
				(int) (150.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
		Image imgPlay2 = ImageIO.read(new File("images/twoplayers.PNG"));
		imgPlay2 = imgPlay2.getScaledInstance(play2.getWidth(), play2.getHeight(), java.awt.Image.SCALE_SMOOTH);
		play2.setIcon(new ImageIcon(imgPlay2));
		play1.addActionListener(this);
		play2.addActionListener(this);
		// -------------------------------
		Mage1 = new JButton();
		Mage1.addActionListener(this);
		Hunter1 = new JButton();
		Hunter1.addActionListener(this);
		Paladin1 = new JButton();
		Paladin1.addActionListener(this);
		Priest1 = new JButton();
		Priest1.addActionListener(this);
		Warlock1 = new JButton();
		Warlock1.addActionListener(this);
		this.revalidate();
		this.repaint();
		Mage2 = new JButton();
		Mage2.addActionListener(this);
		Hunter2 = new JButton();
		Hunter2.addActionListener(this);
		Paladin2 = new JButton();
		Paladin2.addActionListener(this);
		Priest2 = new JButton();
		Priest2.addActionListener(this);
		Warlock2 = new JButton();
		Warlock2.addActionListener(this);
		// --------------------------------------
		error = new JTextArea();
		error.setFont(f);
		error.setForeground(Color.RED);
		error.setOpaque(false);
		this.revalidate();
		this.repaint();
	}

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		s = new Start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton A = (JButton) e.getSource();
		
		if (A.equals(start)) {
			PlaySound("sounds/click.wav");
			if (c == 0 && c1 == 0) {
				
				this.remove(start);
				remove(help);
				remove(exit);
				//this.add(play1);
				//this.add(play2);
				c1++;
				c2++;
				a.setBounds((int) (30.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
						(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				a.setText("First Player's name");
				this.add(a);
				b.setBounds((int) (690.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
						(int) (225.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(b);
				name1.setBounds((int) (245.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
						(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(name1);
				name2.setBounds((int) (945.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
						(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(name2);
				enter1.setBounds((int) (460.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
						(int) (150.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				enter2.setBounds((int) (1160.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
						(int) (150.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				Image imgEnter = null;
				try {
					imgEnter = ImageIO.read(new File("images/Choose Hero.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgEnter = imgEnter.getScaledInstance(enter2.getWidth(), enter2.getHeight(), java.awt.Image.SCALE_SMOOTH);
				enter2.setIcon(new ImageIcon(imgEnter));
				enter1.setIcon(new ImageIcon(imgEnter));
				start.setBounds((int) (size.width / 2) - (int) (60.0 / 1366.0 * size.width),
						(int) (650.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				this.add(start);
				add(enter1);
				add(enter2);
				this.revalidate();
				this.repaint();
				this.revalidate();
				this.repaint();
			} else if (c == 2) {
				System.out.println(c);
				error.setText("PLEASE SELECT A HERO");
				error.setBounds((int) (735.0 / 1366.0 * size.width), (int) (650.0 / 768.0 * size.height),
						(int) (300.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(error);
				PlaySound("sounds/Computer Error.wav");
				this.revalidate();
				this.repaint();
			} else if ((c1 <= 2 || c2 <= 2) && c<2) {
				System.out.println(c);
				error.setText("PLEASE SELECT A HERO");
				error.setBounds((int) (735.0 / 1366.0 * size.width), (int) (650.0 / 768.0 * size.height),
						(int) (300.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(error);
				PlaySound("sounds/Computer Error.wav");
				this.revalidate();
				this.repaint();
			} else {
				try {
					PlaySound("sounds/intro.wav");
					if (c > 2)
						gameview = new GameView(this.getHeroselected1(), this.getHeroselected2(), name1.getText(),
								name2.getText(), false);
					else
						gameview = new GameView(this.getHeroselected1(), this.getHeroselected2(), name1.getText(),
								name2.getText(), true);
					this.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				} catch (FullHandException e1) {
					e1.printStackTrace();
				}
			}
		} else if (A.equals(exit)) {
			PlaySound("sounds/click.wav");
			System.exit(0);
		} else if (A.equals(help)) {
			PlaySound("sounds/click.wav");
			try {
				Desktop.getDesktop().browse(new URI(
						"https://www.reddit.com/r/hearthstone/comments/bisn5q/hearthstone_beginners_guide_year_of_the_dragon/"));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else if (A.equals(play1)) {
			c++;
			this.remove(play1);
			this.remove(play2);
			a.setBounds((int) (300.0 / 1366.0 * size.width), (int) (530.0 / 768.0 * size.height),
					(int) (300.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
			this.add(a);
			name1.setBounds((int) (525.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (200.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
			this.add(name1);
			enter.setBounds((int) (750.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (200.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
			start.setBounds((int) (525.0 / 1366 * size.width), (int) (525.0 / 786 * size.height),
					(int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height));
			Image imgEnter = null;
			try {
				imgEnter = ImageIO.read(new File("images/Choose Hero.PNG"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			imgEnter = imgEnter.getScaledInstance(enter.getWidth(), enter.getHeight(), java.awt.Image.SCALE_SMOOTH);
			enter.setIcon(new ImageIcon(imgEnter));
			this.add(enter);
			this.revalidate();
			this.repaint();
		} else if (A.equals(enter)) {
			PlaySound("sounds/click.wav");
			if (!name1.getText().isEmpty()) {
				player1 = name1.getText();
				Mage1.setBounds((int) (size.width / 2) - (int) (300.0 / 1366.0 * size.width),
						(int) (590.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				Image imgMage1 = null;
				try {
					imgMage1 = ImageIO.read(new File("images/mage1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgMage1 = imgMage1.getScaledInstance(Mage1.getWidth(), Mage1.getHeight(), java.awt.Image.SCALE_SMOOTH);
				Mage1.setIcon(new ImageIcon(imgMage1));
				this.add(Mage1);
				Hunter1.setBounds((int) (size.width / 2) - (int) (180.0 / 1366.0 * size.width),
						(int) (590.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				Image imgHunter1 = null;
				try {
					imgHunter1 = ImageIO.read(new File("images/hunter1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgHunter1 = imgHunter1.getScaledInstance(Hunter1.getWidth(), Hunter1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Hunter1.setIcon(new ImageIcon(imgHunter1));
				this.add(Hunter1);
				Warlock1.setBounds((int) (size.width / 2) - (int) (60.0 / 1366.0 * size.width),
						(int) (590.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				Image imgWarlock1 = null;
				try {
					imgWarlock1 = ImageIO.read(new File("images/warlock1.jpeg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgWarlock1 = imgWarlock1.getScaledInstance(Warlock1.getWidth(), Warlock1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Warlock1.setIcon(new ImageIcon(imgWarlock1));
				this.add(Warlock1);
				Priest1.setBounds((int) (size.width / 2) + (int) (60.0 / 1366.0 * size.width),
						(int) (590.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				Image imgPriest1 = null;
				try {
					imgPriest1 = ImageIO.read(new File("images/priest1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPriest1 = imgPriest1.getScaledInstance(Warlock1.getWidth(), Warlock1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Priest1.setIcon(new ImageIcon(imgPriest1));
				this.add(Priest1);
				Paladin1.setBounds((int) (size.width / 2) + (int) (180.0 / 1366.0 * size.width),
						(int) (590.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				Priest1.setBounds((int) (size.width / 2) + (int) (60.0 / 1366.0 * size.width),
						(int) (590.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				Image imgPaladin1 = null;
				try {
					imgPaladin1 = ImageIO.read(new File("images/paladin1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPaladin1 = imgPriest1.getScaledInstance(Warlock1.getWidth(), Warlock1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Paladin1.setIcon(new ImageIcon(imgPaladin1));
				this.add(Paladin1);
				start.setBounds((int) (size.width / 2) - (int) (60.0 / 1366.0 * size.width),
						(int) (650.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				this.add(start);
				c++; // c=2
				this.remove(error);
			} else {
				error.setText("PLEASE enter your name");
				error.setBounds((int) (735.0 / 1366.0 * size.width), (int) (650.0 / 768.0 * size.height),
						(int) (300.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(error);
				PlaySound("sounds/Computer Error.wav");
			}
			this.revalidate();
			this.repaint();
		} else if (A.equals(play2)) {
			c1++;
			c2++;
			remove(play1);
			remove(play2);
			a.setBounds((int) (30.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
			a.setText("First Player's name");
			this.add(a);
			b.setBounds((int) (690.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (225.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
			this.add(b);
			name1.setBounds((int) (245.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
			this.add(name1);
			name2.setBounds((int) (945.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (200.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
			this.add(name2);
			enter1.setBounds((int) (460.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (150.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
			enter2.setBounds((int) (1160.0 / 1366.0 * size.width), (int) (525.0 / 768.0 * size.height),
					(int) (150.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
			Image imgEnter = null;
			try {
				imgEnter = ImageIO.read(new File("images/Choose Hero.PNG"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			imgEnter = imgEnter.getScaledInstance(enter2.getWidth(), enter2.getHeight(), java.awt.Image.SCALE_SMOOTH);
			enter2.setIcon(new ImageIcon(imgEnter));
			enter1.setIcon(new ImageIcon(imgEnter));
			start.setBounds((int) (size.width / 2) - (int) (60.0 / 1366.0 * size.width),
					(int) (650.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
					(int) (50.0 / 786.0 * size.height));
			this.add(start);
			add(enter1);
			add(enter2);
			this.revalidate();
			this.repaint();
		} else if (A.equals(enter1)) {
			PlaySound("sounds/click.wav");
			if (!name1.getText().isEmpty()) {
				player1 = name1.getText();
				Mage1.setBounds((int) (110.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgMage1 = null;
				try {
					imgMage1 = ImageIO.read(new File("images/mage1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgMage1 = imgMage1.getScaledInstance(Mage1.getWidth(), Mage1.getHeight(), java.awt.Image.SCALE_SMOOTH);
				Mage1.setIcon(new ImageIcon(imgMage1));
				this.add(Mage1);
				Hunter1.setBounds((int) (220.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgHunter1 = null;
				try {
					imgHunter1 = ImageIO.read(new File("images/hunter1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgHunter1 = imgHunter1.getScaledInstance(Hunter1.getWidth(), Hunter1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Hunter1.setIcon(new ImageIcon(imgHunter1));
				this.add(Hunter1);
				Warlock1.setBounds((int) (330.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgWarlock1 = null;
				try {
					imgWarlock1 = ImageIO.read(new File("images/warlock1.jpeg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgWarlock1 = imgWarlock1.getScaledInstance(Warlock1.getWidth(), Warlock1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Warlock1.setIcon(new ImageIcon(imgWarlock1));
				this.add(Warlock1);
				Priest1.setBounds((int) (440.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgPriest1 = null;
				try {
					imgPriest1 = ImageIO.read(new File("images/priest1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPriest1 = imgPriest1.getScaledInstance(Warlock1.getWidth(), Warlock1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Priest1.setIcon(new ImageIcon(imgPriest1));
				this.add(Priest1);
				Paladin1.setBounds((int) (550.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgPaladin1 = null;
				try {
					imgPaladin1 = ImageIO.read(new File("images/paladin1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPaladin1 = imgPaladin1.getScaledInstance(Warlock1.getWidth(), Warlock1.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Paladin1.setIcon(new ImageIcon(imgPaladin1));
				this.add(Paladin1);
				c1++;
				this.remove(error);
			} else {
				error.setText("PLEASE enter your name");
				error.setBounds((int) (735.0 / 1366.0 * size.width), (int) (650.0 / 768.0 * size.height),
						(int) (300.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(error);
				PlaySound("sounds/Computer Error.wav");
			}
			this.revalidate();
			this.repaint();
		} else if (A.equals(enter2)) {
			PlaySound("sounds/click.wav");
			if (!name2.getText().isEmpty()) {
				player2 = name2.getText();
				Mage2.setBounds((int) (810.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgMage1 = null;
				try {
					imgMage1 = ImageIO.read(new File("images/mage1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgMage1 = imgMage1.getScaledInstance(Mage2.getWidth(), Mage2.getHeight(), java.awt.Image.SCALE_SMOOTH);
				Mage2.setIcon(new ImageIcon(imgMage1));
				this.add(Mage2);
				Hunter2.setBounds((int) (920.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgHunter1 = null;
				try {
					imgHunter1 = ImageIO.read(new File("images/hunter1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgHunter1 = imgHunter1.getScaledInstance(Hunter2.getWidth(), Hunter2.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Hunter2.setIcon(new ImageIcon(imgHunter1));
				this.add(Hunter2);
				Warlock2.setBounds((int) (1030.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgWarlock1 = null;
				try {
					imgWarlock1 = ImageIO.read(new File("images/warlock1.jpeg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgWarlock1 = imgWarlock1.getScaledInstance(Warlock2.getWidth(), Warlock2.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Warlock2.setIcon(new ImageIcon(imgWarlock1));
				this.add(Warlock2);
				Priest2.setBounds((int) (1140.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgPriest1 = null;
				try {
					imgPriest1 = ImageIO.read(new File("images/priest1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPriest1 = imgPriest1.getScaledInstance(Warlock2.getWidth(), Warlock2.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Priest2.setIcon(new ImageIcon(imgPriest1));
				this.add(Priest2);
				Paladin2.setBounds((int) (1250.0 / 1366.0 * size.width), (int) (590.0 / 768.0 * size.height),
						(int) (100.0 / 1366 * size.width), (int) (50.0 / 786.0 * size.height));
				Image imgPaladin1 = null;
				try {
					imgPaladin1 = ImageIO.read(new File("images/paladin1.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				imgPaladin1 = imgPaladin1.getScaledInstance(Warlock2.getWidth(), Warlock2.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				Paladin2.setIcon(new ImageIcon(imgPaladin1));
				this.add(Paladin2);
				start.setBounds((int) (size.width / 2) - (int) (60.0 / 1366.0 * size.width),
						(int) (650.0 / 768.0 * size.height), (int) (100.0 / 1366 * size.width),
						(int) (50.0 / 786.0 * size.height));
				this.add(start);
				c2++;
				this.remove(error);
			} else {
				error.setText("PLEASE enter your name");
				error.setBounds((int) (735.0 / 1366.0 * size.width), (int) (650.0 / 768.0 * size.height),
						(int) (300.0 / 1366 * size.width), (int) (30.0 / 786.0 * size.height));
				this.add(error);
				PlaySound("sounds/Computer Error.wav");
			}
			this.revalidate();
			this.repaint();
		} else {
			PlaySound("sounds/click.wav");
			if (A.equals(Mage1)) {
				this.setHeroselected1("Mage");
				if (c >= 2)
					c++;
				else
					c1++;
			}
			if (A.equals(Hunter1)) {
				this.setHeroselected1("Hunter");
				if (c >= 2)
					c++;
				else
					c1++;
			}
			if (A.equals(Paladin1)) {
				this.setHeroselected1("Paladin");
				if (c >= 2)
					c++;
				else
					c1++;
			}
			if (A.equals(Priest1)) {
				this.setHeroselected1("Priest");
				if (c >= 2)
					c++;
				else
					c1++;
			}
			if (A.equals(Warlock1)) {
				this.setHeroselected1("Warlock");
				if (c >= 2)
					c++;
				else
					c1++;
			}
			if (A.equals(Warlock2)) {
				this.setHeroselected2("Warlock");
				c2++;
			}
			if (A.equals(Mage2)) {
				this.setHeroselected2("Mage");
				c2++;
			}
			if (A.equals(Paladin2)) {
				this.setHeroselected2("Paladin");
				c2++;
			}
			if (A.equals(Hunter2)) {
				this.setHeroselected2("Hunter");
				c2++;
			}
			if (A.equals(Priest2)) {
				this.setHeroselected2("Priest");
				c2++;
			}
		}
	}

	public void PlaySound(String filepath) {
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(input);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public String getHeroselected1() {
		return Heroselected1;
	}

	public void setHeroselected1(String heroselected1) {
		Heroselected1 = heroselected1;
	}

	public String getHeroselected2() {
		return Heroselected2;
	}

	public void setHeroselected2(String heroselected2) {
		Heroselected2 = heroselected2;
	}
}
