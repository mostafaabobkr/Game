package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import sun.audio.*;
public class GameView extends JFrame implements ActionListener, GameListener {
	private MyContent view;
	private Game game;
	private JButton start;
	private Image hero1;
	private Image hero2;
	private JButton endturn;
	private JButton playerC;
	private JButton playerP;
	private JButton deck1;
	private JButton deck2;
	private JTextArea Deck1;
	private JTextArea Deck2;
	private JButton display;
	private JPanel fieldc;
	private JPanel fieldo;
	private JPanel handc;
	private JPanel hando;
	private ArrayList<JButton> field_current;
	private ArrayList<JButton> field_opponent;
	private ArrayList<JButton> hand_opponent;
	private ArrayList<JButton> hand_current;
	private JButton Exit;
	private JTextArea first_name;
	private JTextArea second_name;
	private InfoPanel hero1_info;
	private InfoPanel hero2_info;
	private handlerClass mouse_event;
	private ArrayList<String> current_hand_images;
	private ArrayList<String> opponent_hand_images;
	private ArrayList<String> curr_field_images;
	private ArrayList<String> opp_field_images;
	private Image back;
	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	private InfoMinion minion;
	private MinionTarget targetSpell;
	private MinionTarget targetAttack;
	private MinionTarget HeroPower;
	private boolean castM;
	private boolean castH;
	private boolean attackM;
	private boolean attackH;
	private boolean powerM;
	private boolean powerH;
	private Card playedCard;
	private Minion MinionToAttack;
	private JTextArea error;
	private JButton Minion_Name;
	private GameOver finalWindow;
	private String name3;
	private String name4;
	private String a;
	private boolean t2;
	private JButton HowToPlay;

	public GameView(String a, String b, String name1, String name2, boolean t)
			throws IOException, CloneNotSupportedException, FullHandException {
		Hero p1 = new Hunter();
		Hero p2 = new Mage();
		File image2 = new File("images/Mage.jpg");
		File image1 = new File("images/Hunter.jpg");
		switch (a) {
		case "Mage":
			p1 = new Mage();
			break;
		case "Hunter":
			p1 = new Hunter();
			break;
		case "Paladin":
			p1 = new Paladin();
			break;
		case "Priest":
			p1 = new Priest();
			break;
		case "Warlock":
			p1 = new Warlock();
			break;
		}
		a = p1.getName();
		if (b != null) {
			switch (b) {
			case "Mage":
				p2 = new Mage();
				break;
			case "Hunter":
				p2 = new Hunter();
				break;
			case "Paladin":
				p2 = new Paladin();
				break;
			case "Priest":
				p2 = new Priest();
				break;
			case "Warlock":
				p2 = new Warlock();
				break;
			}
		}
		b = p2.getName();
		String tmpb = b;
		game = new Game(p1, p2);
		game.setListener(this);
		p1 = game.getCurrentHero();
		p2 = game.getOpponent();
		if (t == true) {
			a = p1.getName();
			b = p2.getName();
		}
		switch (a) {
		case "Jaina Proudmoore":
			image1 = new File("images/Mage.jpg");
			break;
		case "Rexxar":
			image1 = new File("images/Hunter.jpg");
			break;
		case "Uther Lightbringer":
			image1 = new File("images/Paladin.jpg");
			break;
		case "Anduin Wrynn":
			image1 = new File("images/Priest.jpg");
			break;
		case "Gul'dan":
			image1 = new File("images/Warlock.jpg");
			break;
		}
		switch (b) {
		case "Jaina Proudmoore":
			image2 = new File("images/Mage.jpg");
			break;
		case "Rexxar":
			image2 = new File("images/Hunter.jpg");
			break;
		case "Uther Lightbringer":
			image2 = new File("images/Paladin.jpg");
			break;
		case "Anduin Wrynn":
			image2 = new File("images/Priest.jpg");
			break;
		case "Gul'dan":
			image2 = new File("images/Warlock.jpg");
			break;
		}
		if (a.equals(tmpb) && t == true) {
			String tmp = name1;
			name1 = name2;
			name2 = tmp;
		}
		mouse_event = new handlerClass();
		// ---------------------------------------------------------------------
		hero1 = ImageIO.read(image1);
		hero2 = ImageIO.read(image2);
		Image hero1n = hero1.getScaledInstance((int) (100.0 / 1366.0 * size.getWidth()),
				(int) (80.0 / 768.0 * size.getHeight()), java.awt.Image.SCALE_SMOOTH);
		Image hero2n = hero2.getScaledInstance((int) (100.0 / 1366.0 * size.getWidth()),
				(int) (80.0 / 768.0 * size.getHeight()), java.awt.Image.SCALE_SMOOTH);
		hero1 = hero1n;
		hero2 = hero2n;
		playerC = new JButton();
		playerC.setIcon(new ImageIcon(hero1));
		playerP = new JButton();
		playerP.setSize(
				new Dimension((int) (100.0 / 1366.0 * size.getWidth()), (int) (150.0 / 768.0 * size.getHeight())));
		playerP.setIcon(new ImageIcon(hero2));
		playerP.setSize(
				new Dimension((int) (100.0 / 1366.0 * size.getWidth()), (int) (150.0 / 768.0 * size.getHeight())));
		playerC.addActionListener(this);
		playerP.addActionListener(this);
		name3 = name1;
		name4 = name2;
		t2 = t;
		this.a = a;
		this.b = b;
		playerC.addMouseListener(mouse_event);
		playerC.addMouseMotionListener(mouse_event);
		// ---------------------------------------------------------------------
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		this.setVisible(true);
		File file = new File("images/Field.jpg");
		Image background = ImageIO.read(file);
		background = background.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		view = new MyContent(background);
		this.setContentPane(view);
		this.revalidate();
		this.repaint();
		this.setLayout(null);
		// ----------------------------
		playerC.setBounds(this.getWidth() / 2 - (int) (42.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (210.0 / 768.0 * size.getHeight()), (int) (100.0 / 1366.0 * size.getWidth()),
				(int) (80.0 / 768.0 * size.getHeight()));
		playerP.setBounds(this.getWidth() / 2 - (int) (42.0 / 1366.0 * size.getWidth()),
				(int) (110.0 / 768.0 * size.getHeight()), (int) (100.0 / 1366.0 * size.getWidth()),
				(int) (80.0 / 768.0 * size.getHeight()));
		this.add(playerC);
		this.add(playerP);
		endturn = new JButton();
		endturn.setBounds(this.getWidth() - (int) (192.0 / 1366.0 * size.getWidth()),
				this.getHeight() / 2 - (int) (55.0 / 768.0 * size.getHeight()),
				(int) (100.0 / 1366.0 * size.getWidth()), (int) (40.0 / 768.0 * size.getHeight()));
		endturn.setOpaque(false);
		endturn.setBorderPainted(false);
		endturn.setContentAreaFilled(false);
		endturn.addActionListener(this);
		this.add(endturn);
		deck1 = new JButton();
		deck2 = new JButton();
		deck1.setBounds(this.getWidth() - (int) (175.0 / 1366.0 * size.getWidth()),
				(int) (115.0 / 768.0 * size.getHeight()), (int) (150.0 / 1366.0 * size.getWidth()),
				(int) (200.0 / 768.0 * size.getHeight()));
		deck2.setBounds(this.getWidth() - (int) (175.0 / 1366.0 * size.getWidth()), this.getHeight() / 2,
				(int) (150.0 / 1366.0 * size.getWidth()), (int) (200.0 / 768.0 * size.getHeight()));
		Image img = ImageIO.read(new File("images/card back.png"));
		img = img.getScaledInstance((int) (175.0 / 1366.0 * size.getWidth()), (int) (200.0 / 768.0 * size.getHeight()),
				java.awt.Image.SCALE_SMOOTH);
		deck1.setIcon(new ImageIcon(img));
		deck2.setIcon(new ImageIcon(img));
		deck1.setBorderPainted(false);
		deck2.setBorderPainted(false);
		add(deck1);
		add(deck2);
		// ------------------------------------------------------------------
		Font f = new Font("Arial Black", 18, 18);
		Deck1 = new JTextArea("" + p1.getDeck().size());
		Deck1.setEditable(false);
		Deck1.setFont(f);
		Deck1.setOpaque(false);
		Deck1.setForeground(Color.white);
		Deck2 = new JTextArea("" + p2.getDeck().size());
		Deck2.setEditable(false);
		Deck2.setFont(f);
		Deck2.setOpaque(false);
		Deck2.setForeground(Color.white);
		Deck2.setBounds(this.getWidth() - (int) (130.0 / 1366.0 * size.getWidth()),
				(int) (90.0 / 768.0 * size.getHeight()), (int) (30.0 / 1366.0 * size.getWidth()),
				(int) (20.0 / 768.0 * size.getHeight()));
		this.add(Deck2);
		Deck1.setBounds(this.getWidth() - (int) (130.0 / 1366.0 * size.getWidth()),
				this.getHeight() / 2 + (int) (200.0 / 768.0 * size.getHeight()),
				(int) (30.0 / 1366.0 * size.getWidth()), (int) (20.0 / 768.0 * size.getHeight()));
		this.add(Deck1);
		// --------------------------------------------------------------------
		display = new JButton();
		display.setBounds((int) (50.0 / 1366.0 * size.getWidth()), (int) (230.0 / 768.0 * size.getHeight()),
				(int) (200.0 / 1366.0 * size.getWidth()), (int) (265.0 / 768.0 * size.getHeight()));
		display.setOpaque(false);
		display.setContentAreaFilled(false);
		display.setBorderPainted(false);
		this.add(display);
		// --------------------------------------------------------------------
		field_current = new ArrayList<JButton>();
		field_opponent = new ArrayList<JButton>();
		fieldc = new JPanel(new GridLayout(1, 0, 10, 0));
		fieldc.setBounds((int) (265.0 / 1366.0 * size.getWidth()),
				(this.getHeight() / 2) - (int) (25.0 / 768.0 * size.getHeight()),
				(int) (870.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
		for (int i = 0; i < 7; i++) {
			JButton button1 = new JButton();
			button1.setSize((int) (77.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
			button1.addActionListener(this);
			button1.setOpaque(false);
			button1.setContentAreaFilled(false);
			button1.setBorderPainted(false);
			button1.addMouseListener(mouse_event);
			button1.addMouseMotionListener(mouse_event);
			field_current.add(button1);
			fieldc.add(button1);
		}
		this.add(fieldc);
		fieldc.setOpaque(false);
		fieldo = new JPanel(new GridLayout(1, 0, 10, 0));
		fieldo.setBounds((int) (256.0 / 1366.0 * size.getWidth()),
				(this.getHeight() / 2) - (int) (160.0 / 768.0 * size.getHeight()),
				(int) (870.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
		for (int i = 0; i < 7; i++) {
			JButton button2 = new JButton();
			button2.setSize((int) (77.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
			button2.setOpaque(false);
			button2.addActionListener(this);
			button2.setContentAreaFilled(false);
			button2.setBorderPainted(false);
			button2.addMouseListener(mouse_event);
			button2.addMouseMotionListener(mouse_event);
			field_opponent.add(button2);
			fieldo.add(button2);
		}
		this.add(fieldo);
		fieldo.setOpaque(false);
		curr_field_images = new ArrayList<String>();
		opp_field_images = new ArrayList<String>();
		// --------------------------------------------------------------------
		hand_current = new ArrayList<JButton>();
		hand_opponent = new ArrayList<JButton>();
		current_hand_images = new ArrayList<String>();
		opponent_hand_images = new ArrayList<String>();
		handc = new JPanel(new GridLayout(1, 0, 5, 0));
		handc.setBounds((int) (50.0 / 1366.0 * size.getWidth()), (int) (525.0 / 768.0 * size.getHeight()),
				(int) (500.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
		for (int i = 0; i < 10; i++) {
			JButton button = new JButton();
			button.setSize((int) (45.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
			button.addActionListener(this);
			button.addMouseListener(mouse_event);
			button.addMouseMotionListener(mouse_event);
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			hand_current.add(button);
			handc.add(button);
		}
		for (int i = 0; i < p1.getHand().size(); i++) {
			String string = p1.getHand().get(i).getName();
			if (string.equals("Shadow Word: Death")) {
				string = "Shadow Word Death";
			}
			string = "images/" + string + ".png";
			current_hand_images.add(string);
		}
		for (int i = 0; i < current_hand_images.size(); i++) {
			try {
				hand_current.get(i)
						.setIcon(new ImageIcon(ImageIO.read(new File(current_hand_images.get(i))).getScaledInstance(
								hand_current.get(i).getWidth(), hand_current.get(i).getHeight(),
								java.awt.Image.SCALE_SMOOTH)));
				hand_current.get(i).setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = current_hand_images.size(); i < hand_current.size(); i++) {
			hand_current.get(i).setVisible(false);
		}
		this.add(handc);
		handc.setOpaque(false);
		back = img.getScaledInstance((int) (45.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()),
				java.awt.Image.SCALE_SMOOTH);
		hando = new JPanel(new GridLayout(1, 0, 2, 0));
		hando.setBounds(50, 65, (int) (500.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
		for (int i = 0; i < 10; i++) {
			JButton button = new JButton();
			button.setSize((int) (45.0 / 1366.0 * size.getWidth()), (int) (125.0 / 768.0 * size.getHeight()));
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			hando.add(button);
			hand_opponent.add(button);
		}
		for (int i = 0; i < p2.getHand().size(); i++) {
			hand_opponent.get(i).setIcon(new ImageIcon(back));
		}
		this.add(hando);
		hando.setOpaque(false);
		// ---------------------------------------------------------------------
		first_name = new JTextArea(name1);
		second_name = new JTextArea(name2);
		first_name.setBounds(this.getWidth() / 2 - (int) (42.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (130.0 / 768.0 * size.getHeight()), (int) (100.0 / 1366.0 * size.getWidth()),
				(int) (30.0 / 768.0 * size.getHeight()));
		first_name.setFont(f);
		first_name.setForeground(Color.white);
		first_name.setOpaque(false);
		first_name.setEditable(false);
		this.add(first_name);
		second_name.setBounds(this.getWidth() / 2 - (int) (42.0 / 1366.0 * size.getWidth()),
				(int) (80.0 / 768.0 * size.getHeight()), (int) (100.0 / 1366.0 * size.getWidth()),
				(int) (30.0 / 768.0 * size.getHeight()));
		second_name.setFont(f);
		second_name.setForeground(Color.white);
		second_name.setOpaque(false);
		second_name.setEditable(false);
		this.add(second_name);
		// ----------------------------------------------------------------------
		hero1_info = new InfoPanel((this.getWidth() / 2) - (int) (400.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (40.0 / 768.0 * size.getHeight()), (int) (800.0 / 1366.0 * size.getWidth()),
				(int) (50.0 / 768.0 * size.getHeight()));
		hero1_info.getCurrentHP().setText("HP : " + p1.getCurrentHP() + "      ");
		hero1_info.getCurrentmana().setText("Currnet ManaCrystals: " + p1.getCurrentManaCrystals() + "    ");
		hero1_info.getTotalmana().setText("Total ManaCrystals: " + p1.getTotalManaCrystals());
		this.add(hero1_info);
		hero2_info = new InfoPanel((this.getWidth() / 2) - (int) (400.0 / 1366.0 * size.getWidth()),
				(int) (5.0 / 768.0 * size.getHeight()), (int) (800.0 / 1366.0 * size.getWidth()),
				(int) (50.0 / 768.0 * size.getHeight()));
		hero2_info.getCurrentHP().setText("HP : " + p2.getCurrentHP() + "      ");
		hero2_info.getCurrentmana().setText("Currnet ManaCrystals: " + p2.getCurrentManaCrystals() + "    ");
		hero2_info.getTotalmana().setText("Total ManaCrystals: " + p2.getTotalManaCrystals());
		this.add(hero2_info);
		// ----------------------------------------------------------------------
		Exit = new JButton();
		Exit.setBounds((int) (15.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (65.0 / 768.0 * size.getHeight()), (int) (100.0 / 1366.0 * size.getWidth()),
				(int) (50.0 / 768.0 * size.getHeight()));
		Exit.addActionListener(this);
		Exit.setIcon(new ImageIcon(ImageIO.read(new File("images/exit.PNG"))));
		this.add(Exit);
		// ---------------------------------------------------------------------------
		minion = new InfoMinion((int) (770.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (210.0 / 768.0 * size.getHeight()), 400, 100);
		this.add(minion);
		// --------------------------------------------------------------------------
		targetSpell = new MinionTarget(this.getWidth() / 2 - (int) (100.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (100.0 / 768.0 * size.getHeight()), 200, 50);
		this.add(targetSpell);
		targetSpell.getHeroButton().addActionListener(this);
		targetSpell.getMinionButton().addActionListener(this);
		targetSpell.setVisible(false);
		targetAttack = new MinionTarget(this.getWidth() / 2 - (int) (100.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (100.0 / 768.0 * size.getHeight()), 200, 50);
		this.add(targetAttack);
		targetAttack.getHeroButton().addActionListener(this);
		targetAttack.getMinionButton().addActionListener(this);
		targetAttack.setVisible(false);
		HeroPower = new MinionTarget(this.getWidth() / 2 - (int) (100.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (100.0 / 768.0 * size.getHeight()), 200, 50);
		this.add(HeroPower);
		HeroPower.getHeroButton().addActionListener(this);
		HeroPower.getMinionButton().addActionListener(this);
		HeroPower.setVisible(false);
		// ------------------------------------------------------------------------------
		error = new JTextArea();
		error.setFont(new Font("TimesRoman", Font.BOLD, 26));
		error.setForeground(Color.yellow);
		error.setOpaque(false);
		error.setBounds(this.getWidth() / 2 - (int) (500.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (100.0 / 768.0 * size.getHeight()), 400, 50);
		this.add(error);
		error.setVisible(false);
		error.setEditable(false);
		// ---------------------------------------------------------------------------------
		Minion_Name = new JButton();
		Minion_Name.setBounds((int) (770.0 / 1366.0 * size.getWidth()),
				this.getHeight() - (int) (100.0 / 768.0 * size.getHeight()), 400 / 2, 100 / 4);
		Minion_Name.setFont(new Font("Arial Black", 18, 18));
		Minion_Name.setOpaque(false);
		Minion_Name.setBorderPainted(false);
		Minion_Name.setContentAreaFilled(false);
		Minion_Name.setForeground(Color.white);
		this.add(Minion_Name);
		// --------------------------------------------------------
		HowToPlay = new JButton("Press to use your Hero Power");
		HowToPlay.setFont(new Font("Arial Black", 18, 16));
		;
		HowToPlay.setBounds((int) (1000.0 / 1366.0 * size.getWidth()), (int) (685.0 / 768.0 * size.getHeight()), 300,
				50);
		HowToPlay.setOpaque(false);
		HowToPlay.setBorderPainted(false);
		HowToPlay.setContentAreaFilled(false);
		HowToPlay.setForeground(Color.white);
		HowToPlay.setVisible(false);
		this.add(HowToPlay);
		this.revalidate();
		this.repaint();
	}

	public void onGameOver() {
		try {
			PlaySound("sounds/game-over.wav");
			finalWindow = new GameOver(this.game, this,first_name.getText(),second_name.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		JButton B = (JButton) e.getSource();
		if (B.equals(Exit)) {
			PlaySound("sounds/click.wav");
			System.exit(0);
		}
		if (B.equals(endturn)) {
			PlaySound("sounds/click.wav");
			this.revalidate();
			this.repaint();
			castM = false;
			castH = false;
			attackM = false;
			attackH = false;
			powerH = false;
			powerM = false;
			targetAttack.setVisible(false);
			targetSpell.setVisible(false);
			HeroPower.setVisible(false);
			error.setVisible(false);
			try {
				game.endTurn();
			} catch (FullHandException | CloneNotSupportedException e2) {
				error.setText(e2.getMessage());
				error.setVisible(true);
				PlaySound("sounds/Computer Error.wav");
				
			}
			Image tmp_hero_img = hero1;
			hero1 = hero2;
			hero2 = tmp_hero_img;
			playerC.setIcon(new ImageIcon(hero1));
			playerP.setIcon(new ImageIcon(hero2));
			// -------------------------------------
			String tmpString = first_name.getText();
			first_name.setText(second_name.getText());
			second_name.setText(tmpString);
		}
		// -------------------------------------
		else if (hand_current.contains(B)) {
			error.setVisible(false);
			try {
				int i = hand_current.indexOf(B);
				playedCard = game.getCurrentHero().getHand().get(i);
				Card c = playedCard;
				if (c.getManaCost() > game.getCurrentHero().getCurrentManaCrystals()) {
					throw new NotEnoughManaException("I don't have enough Mana !!");
				}
				if (c instanceof Minion) {
					PlaySound("sounds/attack.wav");
					game.getCurrentHero().playMinion((Minion) c);
				}
				if (c instanceof AOESpell) {
					game.getCurrentHero().castSpell((AOESpell) c, game.getOpponent().getField());
				}
				if (c instanceof FieldSpell) {
					game.getCurrentHero().castSpell((FieldSpell) c);
				}
				if (c instanceof HeroTargetSpell && c instanceof MinionTargetSpell) {
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_Thinking.wav");
					targetSpell.setVisible(true);
				} else if (c instanceof HeroTargetSpell) {
					castH = true;
				} else if (c instanceof MinionTargetSpell) {
					castM = true;
				}
				if (c instanceof LeechingSpell) {
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_Thinking.wav");
					targetSpell.setVisible(true);
				}
			} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
				error.setText(e1.getMessage());
				error.setVisible(true);
				if(e1 instanceof NotEnoughManaException) {
						if(game.getCurrentHero().getName().equals("Gul'dan") ||game.getCurrentHero().getName().equals("Anduin Wrynn") )
					       PlaySound("sounds/Gul'dan_Mana.wav");
						else
							PlaySound("sounds/" + game.getCurrentHero().getName() + "_Mana.wav");
					}
					else if (e1 instanceof FullFieldException)
						PlaySound("sounds/" + game.getCurrentHero().getName() + "_No space for this minion.wav");
					else {
						PlaySound("sounds/Computer Error.wav");
					}
			}
		} else if (B.equals(targetSpell.getHeroButton())) {
			error.setVisible(false);
			castH = true;
			targetSpell.setVisible(false);
		} else if (B.equals(targetSpell.getMinionButton())) {
			error.setVisible(false);
			castM = true;
			targetSpell.setVisible(false);
		} else if (B.equals(targetAttack.getHeroButton())) {
			error.setVisible(false);
			attackH = true;
			attackM = false;
			targetAttack.setVisible(false);
		} else if (B.equals(targetAttack.getMinionButton())) {
			error.setVisible(false);
			attackM = true;
			attackH = false;
			targetAttack.setVisible(false);
		} else if (B.equals(HeroPower.getHeroButton())) {
			error.setVisible(false);
			powerH = true;
			powerM = false;
			HeroPower.setVisible(false);
		} else if (B.equals(HeroPower.getMinionButton())) {
			error.setVisible(false);
			powerM = true;
			powerH = false;
			HeroPower.setVisible(false);
		} else if (B.equals(playerP)) {
			error.setVisible(false);
			try {
				if (castH == true) {
					game.getCurrentHero().castSpell((HeroTargetSpell) playedCard, game.getOpponent());
					castH = false;
				} else if (attackH == true) {
					game.validateAttack(MinionToAttack, game.getOpponent());
					PlaySound("sounds/Minion_Attack.wav");
					MinionToAttack.attack(game.getOpponent());
					attackH = false;
				} else if (powerH == true) {
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_Power.wav");
					if (game.getCurrentHero() instanceof Mage) {
						((Mage) (game.getCurrentHero())).useHeroPower(game.getOpponent());
					} else if (game.getCurrentHero() instanceof Priest)
						((Priest) (game.getCurrentHero())).useHeroPower(game.getOpponent());
					powerH = false;
				}
			} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException | TauntBypassException
					| NotSummonedException | CannotAttackException | HeroPowerAlreadyUsedException | FullHandException
					| FullFieldException | CloneNotSupportedException e1) {
				powerH = false;
				castH = false;
				attackH = false;
				castM = false;
				attackM = false;
				error.setText(e1.getMessage());
				error.setVisible(true);
				if(e1 instanceof NotEnoughManaException) {
					if(game.getCurrentHero().getName().equals("Gul'dan") ||game.getCurrentHero().getName().equals("Anduin Wrynn") )
					       PlaySound("sounds/Gul'dan_Mana.wav");
						else
							PlaySound("sounds/" + game.getCurrentHero().getName() + "_Mana.wav");
				}
				else if(e1.getMessage().equals("A minion with taunt is in the way") && game.getCurrentHero() instanceof Warlock) {
					PlaySound("sounds/" + "Anduin Wrynn" + "_" + e1.getMessage()+ ".wav");
				}
				else if (e1.getMessage().equals("This minion Cannot Attack")) {
					if(game.getCurrentHero() instanceof Mage)
						PlaySound("sounds/" + "Mage" + "_" + e1.getMessage()+ ".wav");
					else 
						PlaySound("sounds/" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof CannotAttackException && (game.getCurrentHero() instanceof Hunter || game.getCurrentHero() instanceof Warlock)) {
					PlaySound("sounds/" + "Anduin Wrynn" + "_" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof InvalidTargetException) {
					if(game.getCurrentHero().getName().equals("Anduin Wrynn") || game.getCurrentHero() instanceof Hunter) {
						PlaySound("sounds/" + "Gul'dan" + "_" + "InvalidTargetException.wav");
					}
					else
						PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + "InvalidTargetException.wav");
				}
				else if( e1 instanceof TauntBypassException || e1 instanceof NotSummonedException || e1 instanceof CannotAttackException || e1 instanceof FullHandException || e1 instanceof FullFieldException)
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + e1.getMessage()+ ".wav"); 
				else {	
					PlaySound("sounds/Computer Error.wav");}
			}
		} else if (field_opponent.contains(B)) {
			int index = field_opponent.indexOf(B);
			error.setVisible(false);
			try {
				if (castM == true) {
					if (playedCard instanceof MinionTargetSpell)
						game.getCurrentHero().castSpell((MinionTargetSpell) playedCard,
								(Minion) game.getOpponent().getField().get(index));
					else
						game.getCurrentHero().castSpell((LeechingSpell) playedCard,
								(Minion) game.getOpponent().getField().get(index));
					castM = false;
				} else if (attackM == true) {
					game.validateAttack(MinionToAttack, game.getOpponent().getField().get(index));
					MinionToAttack.attack(game.getOpponent().getField().get(index));
					PlaySound("sounds/Minion_Attack.wav");
					attackM = false;
				} else if (powerM == true) {
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_Power.wav");
					if (game.getCurrentHero() instanceof Mage) {
						((Mage) (game.getCurrentHero())).useHeroPower(game.getOpponent().getField().get(index));
					} else if (game.getCurrentHero() instanceof Priest)
						((Priest) (game.getCurrentHero())).useHeroPower(game.getOpponent().getField().get(index));
					powerM = false;
				}
				this.Update();
			} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException | TauntBypassException
					| NotSummonedException | CannotAttackException | HeroPowerAlreadyUsedException | FullHandException
					| FullFieldException | CloneNotSupportedException e1) {
				powerM = false;
				castM = false;
				attackM = false;
				castH = false;
				attackH = false;
				error.setText(e1.getMessage());
				error.setVisible(true);
				if(e1 instanceof NotEnoughManaException) {
					if(game.getCurrentHero().getName().equals("Gul'dan") ||game.getCurrentHero().getName().equals("Anduin Wrynn") )
					       PlaySound("sounds/Gul'dan_Mana.wav");
						else
							PlaySound("sounds/" + game.getCurrentHero().getName() + "_Mana.wav");
				}
				else if (e1.getMessage().equals("This minion Cannot Attack")) {
					if(game.getCurrentHero() instanceof Mage)
						PlaySound("sounds/" + "Mage" + "_" + e1.getMessage()+ ".wav");
					else 
						PlaySound("sounds/" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof CannotAttackException && (game.getCurrentHero() instanceof Hunter || game.getCurrentHero() instanceof Warlock)) {
					PlaySound("sounds/" + "Anduin Wrynn" + "_" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof InvalidTargetException) {
					if(game.getCurrentHero().getName().equals("Anduin Wrynn") || game.getCurrentHero() instanceof Hunter) {
						PlaySound("sounds/" + "Gul'dan" + "_" + "InvalidTargetException.wav");
					}
					else
						PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + "InvalidTargetException.wav");
				}
				else if( e1 instanceof TauntBypassException || e1 instanceof NotSummonedException || e1 instanceof CannotAttackException || e1 instanceof FullHandException || e1 instanceof FullFieldException)
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + e1.getMessage()+ ".wav"); 
				else {	
					PlaySound("sounds/Computer Error.wav");}
			}
		} else if (field_current.contains(B)) {
			error.setVisible(false);
			int index = field_current.indexOf(B);
			Minion m = game.getCurrentHero().getField().get(index);
			try {
				if (castM == true) {
					game.getCurrentHero().castSpell((MinionTargetSpell) playedCard,
							game.getCurrentHero().getField().get(index));
					PlaySound("sounds/power.wav");
					this.Update();
					castM = false;
				} else if (powerM == true) {
					PlaySound("sounds/power.wav");
					if (game.getCurrentHero() instanceof Mage) {
						((Mage) (game.getCurrentHero())).useHeroPower(game.getCurrentHero().getField().get(index));
					} else if (game.getCurrentHero() instanceof Priest)
						((Priest) (game.getCurrentHero())).useHeroPower(game.getCurrentHero().getField().get(index));
					powerM = false;
				} else if (attackM == false) {
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_Thinking.wav");
					targetAttack.setVisible(true);
					attackM = true;
					MinionToAttack = m;
				} else {
					game.validateAttack(MinionToAttack, m);
				}
			} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException | TauntBypassException
					| NotSummonedException | CannotAttackException | HeroPowerAlreadyUsedException | FullHandException
					| FullFieldException | CloneNotSupportedException e1) {
				powerM = false;
				castM = false;
				attackM = false;
				castH = false;
				attackH = false;
				error.setText(e1.getMessage());
				error.setVisible(true);
				if(e1 instanceof NotEnoughManaException) {
					if(game.getCurrentHero().getName().equals("Gul'dan") ||game.getCurrentHero().getName().equals("Anduin Wrynn") )
					       PlaySound("sounds/Gul'dan_Mana.wav");
						else
							PlaySound("sounds/" + game.getCurrentHero().getName() + "_Mana.wav");
				}
				else if (e1.getMessage().equals("This minion Cannot Attack")) {
					if(game.getCurrentHero() instanceof Mage)
						PlaySound("sounds/" + "Mage" + "_" + e1.getMessage()+ ".wav");
					else 
						PlaySound("sounds/" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof CannotAttackException && (game.getCurrentHero() instanceof Hunter || game.getCurrentHero() instanceof Warlock)) {
					PlaySound("sounds/" + "Anduin Wrynn" + "_" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof InvalidTargetException) {
					if(game.getCurrentHero().getName().equals("Anduin Wrynn") || game.getCurrentHero() instanceof Hunter) {
						PlaySound("sounds/" + "Gul'dan" + "_" + "InvalidTargetException.wav");
					}
					else
						PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + "InvalidTargetException.wav");
				}
				else if( e1 instanceof TauntBypassException || e1 instanceof NotSummonedException || e1 instanceof CannotAttackException || e1 instanceof FullHandException || e1 instanceof FullFieldException)
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + e1.getMessage()+ ".wav"); 
				else {	
					PlaySound("sounds/Computer Error.wav");}
			}
		} else if (B.equals(playerC)) {
			try {
				if (game.getCurrentHero() instanceof Mage || game.getCurrentHero() instanceof Priest) {
					if (powerH == false) {
						PlaySound("sounds/" + game.getCurrentHero().getName() + "_Thinking.wav");
						HeroPower.setVisible(true);
					}
					else {
						if (game.getCurrentHero() instanceof Mage) {
							PlaySound("sounds/power.wav");
							((Mage) (game.getCurrentHero())).useHeroPower(game.getCurrentHero());
						} else if (game.getCurrentHero() instanceof Priest) {
							((Priest) (game.getCurrentHero())).useHeroPower(game.getCurrentHero());
							PlaySound("sounds/power.wav");
						}
							powerH = false;
					}
				} else {
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_Power.wav");
					game.getCurrentHero().useHeroPower();
				}
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e1) {
				error.setText(e1.getMessage());
				error.setVisible(true);
				if(e1 instanceof NotEnoughManaException) {
					if(game.getCurrentHero().getName().equals("Gul'dan") ||game.getCurrentHero().getName().equals("Anduin Wrynn") )
					       PlaySound("sounds/Gul'dan_Mana.wav");
						else
							PlaySound("sounds/" + game.getCurrentHero().getName() + "_Mana.wav");
				}
				else if (e1.getMessage().equals("This minion Cannot Attack")) {
					if(game.getCurrentHero() instanceof Mage)
						PlaySound("sounds/" + "Mage" + "_" + e1.getMessage()+ ".wav");
					else 
						PlaySound("sounds/" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof CannotAttackException && (game.getCurrentHero() instanceof Hunter || game.getCurrentHero() instanceof Warlock)) {
					PlaySound("sounds/" + "Anduin Wrynn" + "_" + e1.getMessage()+ ".wav");
				}
				else if (e1 instanceof InvalidTargetException) {
					if(game.getCurrentHero().getName().equals("Anduin Wrynn") || game.getCurrentHero() instanceof Hunter) {
						PlaySound("sounds/" + "Gul'dan" + "_" + "InvalidTargetException.wav");
					}
					else
						PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + "InvalidTargetException.wav");
				}
				else if( e1 instanceof TauntBypassException || e1 instanceof NotSummonedException || e1 instanceof CannotAttackException || e1 instanceof FullHandException || e1 instanceof FullFieldException)
					PlaySound("sounds/" + game.getCurrentHero().getName() + "_" + e1.getMessage()+ ".wav"); 
				else {	
					PlaySound("sounds/Computer Error.wav");}
			}
		}
		this.Update();
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
	public void Update() {
		current_hand_images.clear();
		for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
			String string = game.getCurrentHero().getHand().get(i).getName();
			if (string.equals(("Shadow Word: Death")))
				string = "Shadow Word Death";
			string = "images/" + string + ".png";
			System.out.println(string);
			current_hand_images.add(string);
		}
		for (int i = 0; i < current_hand_images.size(); i++) {
			try {
				hand_current.get(i)
						.setIcon(new ImageIcon(ImageIO.read(new File(current_hand_images.get(i))).getScaledInstance(
								hand_current.get(i).getWidth(), hand_current.get(i).getHeight(),
								java.awt.Image.SCALE_SMOOTH)));
				hand_current.get(i).setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = current_hand_images.size(); i < hand_current.size(); i++) {
			hand_current.get(i).setVisible(false);
		}
		for (int i = 0; i < game.getOpponent().getHand().size(); i++) {
			hand_opponent.get(i).setIcon(new ImageIcon(back.getScaledInstance(hand_opponent.get(i).getWidth(),
					hand_opponent.get(i).getHeight(), java.awt.Image.SCALE_SMOOTH)));
			hand_opponent.get(i).setVisible(true);
		}
		for (int i = game.getOpponent().getHand().size(); i < hand_opponent.size(); i++) {
			hand_opponent.get(i).setVisible(false);
		}
		curr_field_images.clear();
		for (int i = 0; i < game.getCurrentHero().getField().size(); i++) {
			String string = game.getCurrentHero().getField().get(i).getName();
			if (string.equals(("Shadow Word: Death")))
				string = "Shadow Word Death";
			string = "images/" + string + ".png";
			System.out.println(string);
			curr_field_images.add(string);
		}
		for (int i = 0; i < curr_field_images.size(); i++) {
			try {
				field_current.get(i)
						.setIcon(new ImageIcon(ImageIO.read(new File(curr_field_images.get(i))).getScaledInstance(
								field_current.get(i).getWidth(), field_current.get(i).getHeight(),
								java.awt.Image.SCALE_SMOOTH)));
				field_current.get(i).setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = curr_field_images.size(); i < field_current.size(); i++) {
			field_current.get(i).setVisible(false);
		}
		opp_field_images.clear();
		for (int i = 0; i < game.getOpponent().getField().size(); i++) {
			String string = game.getOpponent().getField().get(i).getName();
			if (string.equals(("Shadow Word: Death")))
				string = "Shadow Word Death";
			string = "images/" + string + ".png";
			System.out.println(string);
			opp_field_images.add(string);
		}
		for (int i = 0; i < opp_field_images.size(); i++) {
			try {
				field_opponent.get(i)
						.setIcon(new ImageIcon(ImageIO.read(new File(opp_field_images.get(i))).getScaledInstance(
								field_opponent.get(i).getWidth(), field_opponent.get(i).getHeight(),
								java.awt.Image.SCALE_SMOOTH)));
				field_opponent.get(i).setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = opp_field_images.size(); i < field_opponent.size(); i++) {
			field_opponent.get(i).setVisible(false);
		}
		hero1_info.getCurrentHP().setText("HP : " + game.getCurrentHero().getCurrentHP() + "      ");
		hero1_info.getCurrentmana()
				.setText("Currnet ManaCrystals: " + game.getCurrentHero().getCurrentManaCrystals() + "    ");
		hero1_info.getTotalmana().setText("Total ManaCrystals: " + game.getCurrentHero().getTotalManaCrystals());
		hero2_info.getCurrentHP().setText("HP : " + game.getOpponent().getCurrentHP() + "      ");
		hero2_info.getCurrentmana()
				.setText("Currnet ManaCrystals: " + game.getOpponent().getCurrentManaCrystals() + "    ");
		hero2_info.getTotalmana().setText("Total ManaCrystals: " + game.getOpponent().getTotalManaCrystals());
		Deck1.setText("" + game.getCurrentHero().getDeck().size());
		Deck2.setText("" + game.getOpponent().getDeck().size());
		if (game.getCurrentHero().getDeck().size() == 0) {
			deck2.setVisible(false);
		} else {
			deck2.setVisible(true);
		}
		if (game.getOpponent().getDeck().size() == 0) {
			deck1.setVisible(false);
		} else {
			deck1.setVisible(true);
		}
		this.revalidate();
		this.repaint();
	}

	private class handlerClass implements MouseListener, MouseMotionListener {
		public void mouseClicked(MouseEvent event) {
			HowToPlay.setVisible(false);
		}

		public void mousePressed(MouseEvent event) {
			HowToPlay.setVisible(false);
		}

		public void mouseReleased(MouseEvent event) {
			HowToPlay.setVisible(false);
		}

		public void mouseEntered(MouseEvent event) {
			HowToPlay.setVisible(false);
		}

		public void mouseExited(MouseEvent event) {
			HowToPlay.setVisible(false);
		}

		public void mouseDragged(MouseEvent event) {
			HowToPlay.setVisible(false);
		}

		public void mouseMoved(MouseEvent event) {
			HowToPlay.setVisible(false);
			JButton button = (JButton) event.getSource();
			if (button.getIcon() != null) {
				if ((hand_current.contains(button))) {
					Image button_image;
					try {
						button_image = ImageIO.read(new File(current_hand_images.get(hand_current.indexOf(button))));
						button_image = button_image.getScaledInstance(display.getWidth(), display.getHeight(),
								java.awt.Image.SCALE_SMOOTH);
						display.setIcon(new ImageIcon(button_image));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Card c = game.getCurrentHero().getHand().get(hand_current.indexOf(button));
					minion.getHp().setText("Mana Cost :" + c.getManaCost());
					minion.getAttack().setText("");
					minion.getDivine().setText("");
					minion.getCharge().setText("");
					minion.getIsattack().setText("");
					minion.getRarity().setText("Rarity: " + c.getRarity());
					minion.getTaunt().setText("");
					Minion_Name.setText(c.getName());
				} else if (field_current.contains(button)
						&& field_current.indexOf(button) < game.getCurrentHero().getField().size()) {
					Minion m = game.getCurrentHero().getField().get(field_current.indexOf(button));
					minion.getHp().setText("HP: " + m.getCurrentHP());
					minion.getAttack().setText("Attck: " + m.getAttack());
					minion.getDivine().setText("Divine: " + m.isDivine());
					minion.getCharge().setText("Sleeping: " + m.isSleeping());
					minion.getIsattack().setText("Attcked: " + m.isAttacked());
					minion.getRarity().setText("Rarity: " + m.getRarity());
					minion.getTaunt().setText("Taunt: " + m.isTaunt());
					Minion_Name.setText(m.getName());
				} else if (field_opponent.contains(button)
						&& field_opponent.indexOf(button) < game.getOpponent().getField().size()) {
					Minion m = game.getOpponent().getField().get(field_opponent.indexOf(button));
					minion.getHp().setText("HP: " + m.getCurrentHP());
					minion.getAttack().setText("Attck: " + m.getAttack());
					minion.getDivine().setText("Divine: " + m.isDivine());
					minion.getCharge().setText("");
					minion.getIsattack().setText("Attcked: " + m.isAttacked());
					minion.getRarity().setText("Rarity: " + m.getRarity());
					minion.getTaunt().setText("Taunt: " + m.isTaunt());
					Minion_Name.setText(m.getName());
				} else if (button.equals(playerC)) {
					HowToPlay.setVisible(true);
				}
			} else {
				HowToPlay.setVisible(false);
			}
		}
	}

	public String getA() {
		return a;
	}

	public String getB() {
		return b;
	}

	private String b;

	public String getName3() {
		return name3;
	}

	public String getName4() {
		return name4;
	}

	public boolean isT2() {
		return t2;
	}
}
