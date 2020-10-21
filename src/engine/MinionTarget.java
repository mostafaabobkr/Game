package engine;

import java.awt.FlowLayout;

import javax.swing.*;

public class MinionTarget extends JPanel {
      JButton heroButton;
      JButton minionButton;
      public MinionTarget( int x ,int y ,int width,int height) {
    	  setBounds(x, y, width, height);
    	  this.setLayout(new FlowLayout());
    	  heroButton = new JButton("Hero");
    	  minionButton = new JButton("Minion");
    	  this.add(heroButton);
    	  this.add(minionButton);
    	  this.setOpaque(false);
      }
	public JButton getHeroButton() {
		return heroButton;
	}
	public void setHeroButton(JButton heroButton) {
		this.heroButton = heroButton;
	}
	public JButton getMinionButton() {
		return minionButton;
	}
	public void setMinionButton(JButton minionButton) {
		this.minionButton = minionButton;
	}
}
