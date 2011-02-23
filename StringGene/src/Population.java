import java.util.ArrayList;


public class Population {
	private int MAX, generations;
	private float mutationRate;
	private DNA[] population;
	private float[] fitness;
	private ArrayList<DNA> darwin;
	private String phrase;
	private boolean finished;
	
	public Population(String p, float m, int num)
	{
		phrase = p;
		mutationRate = m;
		MAX = num;
		population = new DNA[MAX];
		fitness = new float[MAX];
		for(int i=0;i<population.length;i++)
		{
			population[i]=new DNA(phrase.length());
		}
		calcFitness();
		darwin = new ArrayList<DNA>();
		finished = false;
		generations = 0;
	}
	
	public void calcFitness()
	{
		for(int i=0;i<population.length;i++)
		{
			fitness[i]=population[i].fitness(phrase);
		}
	}
	public void naturalSelection()
	{
		darwin.clear();
		float totalFitness = getTotalFitness();
		for(int i=0;i<population.length;i++)
		{
			float fitnessNormal = fitness[i]/totalFitness;
			int n = (int)(fitnessNormal*10000.0f);
			for(int j=0;j<n;j++)
			{
				darwin.add(population[i]);
			}
		}
	}
	public void generate()
	{
		for(int i=0;i<population.length;i++)
		{
			int m=Randomizer.rand(0,darwin.size());
			int d=Randomizer.rand(0,darwin.size());
			DNA mom = (DNA) darwin.get(m);
			DNA dad = (DNA) darwin.get(d);
			DNA child = mom.mate(dad);
			child.mutate(mutationRate);
			population[i]=child;
		}
		generations++;
	}
	public String getBest()
	{
		float worldrecord = 0.0f;
		int index=0;
		for(int i=0;i<population.length;i++)
		{
			if(fitness[i]>worldrecord)
			{
				index=i;
				worldrecord=fitness[i];
			}
		}
		finished=(worldrecord==1.0f);
		return population[index].toString();
	}
	public boolean finished()
	{
		return finished;
	}
	public int getGenerations()
	{
		return generations;
	}
	public float getTotalFitness()
	{
		float total=0;
		for(int i=0;i<population.length;i++)
		{
			total+=fitness[i];
		}
		return total;
	}
	public float getAverageFitness()
	{
		float total=getTotalFitness();
		return total/(float)population.length;
	}
}
