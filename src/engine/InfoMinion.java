package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class InfoMinion extends JPanel {
    private JButton hp;
    private JButton attack;
    private JButton divine;
    private JButton charge;
    private JButton isattack;
    private JButton rarity;
    private JButton taunt;
    public InfoMinion(int x , int y , int width , int height) {
    	this.setBounds(x, y, width, height);
    	this.setLayout(new GridLayout(4, 3));
    	hp = new JButton();
    	attack = new JButton();
    	divine = new JButton();
    	charge = new JButton();
    	isattack = new JButton();
    	rarity = new JButton();
    	taunt = new JButton();
    	hp.setSize(width/2, height/3);
    	attack.setSize(width/2, height/4);
    	divine.setSize(width/2, height/4);
    	charge.setSize(width/2, height/4);
    	isattack.setSize(width/2, height/4);
    	rarity.setSize(width/2, height/3);
    	taunt.setSize(width, height/4);
    	hp.setOpaque(false);hp.setBorderPainted(false);hp.setContentAreaFilled(false);
    	attack.setOpaque(false);attack.setBorderPainted(false);attack.setContentAreaFilled(false);
    	divine.setOpaque(false);divine.setBorderPainted(false);divine.setContentAreaFilled(false);
    	charge.setOpaque(false);charge.setBorderPainted(false);charge.setContentAreaFilled(false);
    	isattack.setOpaque(false);isattack.setBorderPainted(false);isattack.setContentAreaFilled(false);
    	rarity.setOpaque(false);rarity.setBorderPainted(false);rarity.setContentAreaFilled(false);
    	taunt.setOpaque(false);taunt.setBorderPainted(false);taunt.setContentAreaFilled(false);
    	Font f = new Font("Arial Black", 18, 18);
    	hp.setFont(f);
    	hp.setForeground(Color.white);
    	attack.setFont(f);
    	attack.setForeground(Color.white);
    	divine.setFont(f);
    	divine.setForeground(Color.white);
    	charge.setFont(f);
    	charge.setForeground(Color.white);
    	isattack.setFont(f);
    	isattack.setForeground(Color.white);
    	rarity.setFont(f);
    	rarity.setForeground(Color.white);
    	taunt.setFont(f);
    	taunt.setForeground(Color.white);
    	this.add(hp);
    	this.add(attack);
    	this.add(charge);
    	this.add(isattack);
    	this.add(rarity);
    	this.add(divine);
    	this.add(taunt);
    	this.setOpaque(false);
    }
	public JButton getHp() {
		return hp;
	}
	public void setHp(JButton hp) {
		this.hp = hp;
	}
	public JButton getAttack() {
		return attack;
	}
	public void setAttack(JButton attack) {
		this.attack = attack;
	}
	public JButton getDivine() {
		return divine;
	}
	public void setDivine(JButton divine) {
		this.divine = divine;
	}
	public JButton getCharge() {
		return charge;
	}
	public void setCharge(JButton charge) {
		this.charge = charge;
	}
	public JButton getIsattack() {
		return isattack;
	}
	public void setIsattack(JButton isattack) {
		this.isattack = isattack;
	}
	public JButton getRarity() {
		return rarity;
	}
	public void setRarity(JButton rarity) {
		this.rarity = rarity;
	}
	public JButton getTaunt() {
		return taunt;
	}
	public void setTaunt(JButton taunt) {
		this.taunt = taunt;
	}
}
