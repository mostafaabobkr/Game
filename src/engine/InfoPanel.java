package engine;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPanel extends JPanel {
	private JTextArea currentHP;
	private JTextArea currentmana;
	private JTextArea totalmana;

	public InfoPanel(int x, int y, int w, int h) {
		this.setLayout(new FlowLayout());
		this.setBounds(x, y, w, h);
		currentHP = new JTextArea("HP :        ");// 9
		currentHP.setSize(100, h);
		currentmana = new JTextArea("Currnet ManaCrystals:       ");// 7
		currentmana.setSize(100, h);
		totalmana = new JTextArea("Total ManaCrystals:   ");// 3
		totalmana.setSize(100, h);
		this.add(currentHP);
		this.add(currentmana);
		this.add(totalmana);
		this.setOpaque(false);
		Font f = new Font("Arial Black", 18, 18);
		currentHP.setFont(f);
		currentmana.setFont(f);
		totalmana.setFont(f);
		currentHP.setOpaque(false);
		currentHP.setForeground(Color.white);
		currentmana.setOpaque(false);
		currentmana.setForeground(Color.white);
		totalmana.setOpaque(false);
		totalmana.setForeground(Color.white);
		currentHP.setEditable(false);
		currentmana.setEditable(false);
		totalmana.setEditable(false);
	}

	public JTextArea getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(JTextArea currentHP) {
		this.currentHP = currentHP;
	}

	public JTextArea getCurrentmana() {
		return currentmana;
	}

	public void setCurrentmana(JTextArea currentmana) {
		this.currentmana = currentmana;
	}

	public JTextArea getTotalmana() {
		return totalmana;
	}

	public void setTotalmana(JTextArea totalmana) {
		this.totalmana = totalmana;
	}
}