import java.awt.*;

import javax.swing.JApplet;


public class GA_Main extends JApplet{
	private Font f,fb;
	private String phrase;
	private int popmax;
	private float mutationRate;
	Population pop;
	
	public void init()
	{
		setSize(450,200);
		fb = new Font("Courier", Font.PLAIN, 20);
		f=new Font("Arial", Font.PLAIN, 12);
		//f = Font.getFont("Arial");
		//fb=fb.deriveFont(32);
		//f=f.deriveFont(12);
		phrase="A watched pot never boils.";
		popmax=150;
		mutationRate=0.01f;
		pop=new Population(phrase,mutationRate,popmax);
	}
	public void paint(Graphics g)
	{
		pop.naturalSelection();
		pop.generate();
		pop.calcFitness();
		displayInfo(g);
		if(!pop.finished()) repaint();
	}
	public void displayInfo(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		String answer = pop.getBest();
		g.setColor(Color.GREEN);
		g.setFont(fb);
		g.drawString(answer, 20, 100);
		
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString("Generation: "+pop.getGenerations(),20,140);
		g.drawString("Average Fitness: "+pop.getAverageFitness(),20,155);
		g.drawString("Total Population: "+popmax, 20, 170);
		g.drawString("Mutation Rate: "+(int)(mutationRate*100)+"%",20,185);
	}
	
}
