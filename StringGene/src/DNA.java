
public class DNA {
	private char[] dna;
	
	public DNA(int num)
	{
		dna = new char[num];
		for(int i=0;i<dna.length;i++)
		{
			dna[i] = (char) Randomizer.rand(32, 128);
		}
	}
	
	public DNA(char[] newdna)
	{
		dna = (char[]) newdna.clone();
	}
	
	public float fitness(String goal)
	{
		int score = 0;
		for(int i=0;i<dna.length;i++)
		{
			if(dna[i]==goal.charAt(i)) score++;
		}
		float fitness = (float)score/(float)goal.length();
		return fitness;
	}
	public char getDNA(int index)
	{
		return dna[index];
	}
	public DNA mate(DNA partner)
	{
		char[] child = new char[dna.length];
		int crossover = Randomizer.rand(0, dna.length);
		for(int i=0;i<dna.length;i++)
		{
			if(i>crossover) child[i]=dna[i];
			else child[i]=partner.getDNA(i);
		}
		DNA newdna = new DNA(child);
		return newdna;
	}
	public void mutate(float m)
	{
		for(int i=0;i<dna.length;i++)
		{
			if(Math.random()<m) dna[i] = (char)Randomizer.rand(32,128);
		}
	}
	
	public String toString()
	{
		return new String(dna);
	}
}
