package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import exceptions.FullHandException;
import model.heroes.Hero;

public class GameOver extends JFrame implements ActionListener {
	private JButton exit;
	private JButton replay;
	private JButton newGame;
	private JButton winner;
	private JButton loser;
	private JTextArea Winner_name;
	private JTextArea Loser_name;
	private InfoMinion Winner_Info;
	private InfoMinion Loser_Info;
	private Dimension size;
	private MyContent window;
	private GameView gameview;
	private Start start;
	private String a;
	private String b;
	private String name1;
	private String name2;
	private boolean t;

	public GameOver(Game game, GameView GameView,String first,String second) throws IOException {
		a = GameView.getA();
		b = GameView.getB();
		name1 = GameView.getName3();
		name2 = GameView.getName4();
		t = GameView.isT2();
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
		// ---------------------------------------------------------------------------------
		exit = new JButton();
		exit.setBounds((int) (775.0 / 1366 * size.width), (int) (700.0 / 786 * size.height),
				(int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height));
		Image imgExit = ImageIO.read(new File("images/exit.PNG"));
		imgExit = imgExit.getScaledInstance((int) (100.0 / 1366 * size.width), (int) (50.0 / 768 * size.height),
				java.awt.Image.SCALE_SMOOTH);
		exit.setIcon(new ImageIcon(imgExit));
		replay = new JButton();
		replay.setBounds((int) (525.0 / 1366 * size.width), (int) (700.0 / 786 * size.height),
				(int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height));
		Image imgStart = ImageIO.read(new File("images/replay.jpeg"));
		imgStart = imgStart.getScaledInstance((int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height),
				java.awt.Image.SCALE_SMOOTH);
		replay.setIcon(new ImageIcon(imgStart));
		newGame = new JButton();
		Image imgHelp = ImageIO.read(new File("images/newGame.jpeg"));
		newGame.setBounds((int) (650.0 / 1366 * size.width), (int) (700.0 / 786 * size.height),
				(int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height));
		imgHelp = imgHelp.getScaledInstance((int) (100.0 / 1366 * size.width), (int) (50.0 / 786 * size.height),
				java.awt.Image.SCALE_SMOOTH);
		newGame.setIcon(new ImageIcon(imgHelp));
		exit.addActionListener(this);
		replay.addActionListener(this);
		newGame.addActionListener(this);
		this.add(replay);
		this.add(newGame);
		this.add(exit);
		// ----------------------------------------------------------------------------------------------
		winner = new JButton();
		winner.setBounds((int) (100.0 / 1366 * size.width), (int) (100.0 / 786 * size.height),
				(int) (300.0 / 1366 * size.width), (int) (400.0 / 786 * size.height));
		this.add(winner);
		loser = new JButton();
		loser.setBounds((int) (1000.0 / 1366 * size.width), (int) (100.0 / 786 * size.height),
				(int) (300.0 / 1366 * size.width), (int) (400.0 / 786 * size.height));
		this.add(loser);
		// ------------------------------------------------------------------------------------------
		Winner_name = new JTextArea();
		Winner_name.setBounds((int) (100.0 / 1366 * size.width), (int) (15.0 / 786 * size.height),
				(int) (300.0 / 1366 * size.width), (int) (70.0 / 786 * size.height));
		this.add(Winner_name);
		Winner_Info = new InfoMinion((int) (100.0 / 1366 * size.width), (int) (515.0 / 786 * size.height),
				(int) (300.0 / 1366 * size.width), (int) (200.0 / 786 * size.height));
		this.add(Winner_Info);
		Loser_name = new JTextArea();
		Loser_name.setBounds((int) (1000.0 / 1366 * size.width), (int) (15.0 / 786 * size.height),
				(int) (300.0 / 1366 * size.width), (int) (70.0 / 786 * size.height));
		this.add(Loser_name);
		Loser_Info = new InfoMinion((int) (1000.0 / 1366 * size.width), (int) (515.0 / 786 * size.height),
				(int) (300.0 / 1366 * size.width), (int) (200.0 / 786 * size.height));
		this.add(Loser_Info);
		// -----------------------------------------------------------------------------------------------
		Font f = new Font("Arial Black", 28, 22);
		Winner_name.setFont(f);
		Winner_name.setForeground(Color.red);
		Winner_name.setText("\n  CONGRTULATIONS ^_^");
		Winner_name.setOpaque(false);
		Loser_name.setFont(f);
		Loser_name.setForeground(Color.red);
		Loser_name.setText("\n        HARD LUCK ;(");
		Loser_name.setOpaque(false);
		// -----------------------------------------------------------------------------------------------
		String W;
		String L;
		Hero Win;
		Hero Lose;
		if (game.getCurrentHero().getCurrentHP() == 0) {
			L = game.getCurrentHero().getName();
			W = game.getOpponent().getName();
			Lose = game.getCurrentHero();
			Win = game.getOpponent();
			String tmp = name1;
			name1 = name2;
			name2 = tmp;
		} else {
			W = game.getCurrentHero().getName();
			L = game.getOpponent().getName();
			Win = game.getCurrentHero();
			Lose = game.getOpponent();
		}
		switch (W) {
		case "Jaina Proudmoore":
			W = "Mage";
			break;
		case "Rexxar":
			W = "Hunter";
			break;
		case "Uther Lightbringer":
			W = "Paladin";
			break;
		case "Anduin Wrynn":
			W = "Priest";
			break;
		case "Gul'dan":
			W = "Warlock";
			break;
		}
		switch (L) {
		case "Jaina Proudmoore":
			L = "Mage";
			break;
		case "Rexxar":
			L = "Hunter";
			break;
		case "Uther Lightbringer":
			L = "Paladin";
			break;
		case "Anduin Wrynn":
			L = "Priest";
			break;
		case "Gul'dan":
			L = "Warlock";
			break;
		}
		loser.setIcon(new ImageIcon(ImageIO.read(new File("images/" + L + ".jpg")).getScaledInstance(winner.getWidth(),
				winner.getHeight(), java.awt.Image.SCALE_SMOOTH)));
		winner.setIcon(new ImageIcon(ImageIO.read(new File("images/" + W + ".jpg")).getScaledInstance(winner.getWidth(),
				winner.getHeight(), java.awt.Image.SCALE_SMOOTH)));
		// ----------------------------------------------------------------------------------------
		Winner_Info.getHp().setText("HP :" + Win.getCurrentHP());
		Winner_Info.getAttack().setText("Hand :" + Win.getHand().size());
		Winner_Info.getCharge().setText("Field:" + Win.getField().size());
		Winner_Info.getIsattack().setText("Deck:" + Win.getDeck().size());
		Winner_Info.getRarity().setText(first);
		Loser_Info.getHp().setText("HP :" + Lose.getCurrentHP());
		Loser_Info.getAttack().setText("Hand :" + Lose.getHand().size());
		Loser_Info.getCharge().setText("Field:" + Lose.getField().size());
		Loser_Info.getIsattack().setText("Deck:" + Lose.getDeck().size());
		Loser_Info.getRarity().setText(second);
		this.revalidate();
		this.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		JButton B = (JButton) e.getSource();
		if (B.equals(exit))
			System.exit(0);
		else if (B.equals(replay)) {
			try {
				gameview = new GameView(a, b, name1, name2, t);
			} catch (FullHandException | IOException | CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
		} else if (B.equals(newGame)) {
			try {
				start = new Start();
			} catch (IOException | CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
	}
}
