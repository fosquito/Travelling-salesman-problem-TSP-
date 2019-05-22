
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
	
	//Global variables
	final static int MAXCO0RDENATE = 800;	//Coordenates of points 0 to MAXCO0RDENATE
	final static int POPULACAOSIZE = 1000;	//Size of population
	final static int GENERATIONS = 10000;	//Number of generations
	final static double MUTATION = 0.005;	//Probability of mutation
	final static double CROSSOVER = 0.9;	//Probability of crossover
	static Random generator = new Random();
	
	
	public static void main(String[] args){		//Main function
		
		Scanner sc = new Scanner(System.in);
		double d;	//variable to use to save random numbers
		
		int N = sc.nextInt();	//Number of cities (Points)
		Graphic g = new Graphic();	//Graphic to draw best path
		Cidade [] cidades = new Cidade [N];
		ArrayList<GA> populacao = new ArrayList<GA>();
		ArrayList<GA> newGeneration = new ArrayList<GA>();
		
		
		
		//for(int i=0;i<N;i++)
			//cidades[i] = new Cidade(i, sc.nextInt(), sc.nextInt());	//Put points manualy
		cidades = inicializePopulation(N);	//Put point randomly
		
		GA best = null;
		GA genBest = null;
		
		//create population by random permutation
		for(int i=0;i<POPULACAOSIZE;i++)
			populacao.add(new GA (randomPermutation(cidades)));
		
		
		
		//Start Generations
		for(int j=0;j<GENERATIONS;j++) {
			
			for(int i=0;i<POPULACAOSIZE;i++) {	//CrossOver
				d = generator.nextDouble();
				if(d < CROSSOVER) {
					d = Math.round(2 + generator.nextDouble() * 3);
					newGeneration.add(GARandom(populacao,(int) d).crossOver(GARandom(populacao,(int) d), generator));
					newGeneration.get(newGeneration.size()-1).setFitness();
				}
			}
			
			for(int i=newGeneration.size();i<POPULACAOSIZE;i++) {	//Complete population
				d = Math.round(2 + generator.nextDouble() * 250);
				newGeneration.add(GARandom(populacao, (int) d));
			}
			
			
			for(int i=0;i<POPULACAOSIZE;i++)	//Mutation
				newGeneration.get(i).mutation(generator, MUTATION);
			
			
			
			//New Generation
			populacao.clear();
			populacao = new ArrayList<GA>(newGeneration);
			newGeneration.clear();
			for(int i=0;i<POPULACAOSIZE;i++)
				populacao.get(i).setFitness();
			
			//Find best subject of generation
			genBest = populacao.get(0);
			for(int i=1;i<POPULACAOSIZE;i++)
				genBest = genBest.tournament(populacao.get(i));
			if(best == null || genBest.getFitness() <= best.getFitness())
				best = new GA (genBest);
			
			//Show on console best subject of generation
			for(Cidade x: genBest.getCaminho())
				System.out.print(x.getId()+" ");
			System.out.print(genBest.getCaminho()[0].getId());
			System.out.println("\tf:"+genBest.getFitness());
			
			
			//Paint the best path found
			g.repaint(g.getGraphics(), cidades, best);
		}
		
		//Show best path found
		System.out.println();
		for(Cidade x: best.getCaminho())
			System.out.print(x.getId()+" ");
		System.out.print(best.getCaminho()[0].getId());
		System.out.println(" "+best.getFitness());
		sc.close();
	}
	
	
	/**
	 * Function that generate a random number between a and b;
	 * 
	 * @param a
	 * @param b
	 * @return Double random number
	 */
	public static double numberGenerator(double a, double b) {
		return a + (generator.nextDouble() * (b-a));
	}
	
	
	/**
	 * Function that create Cities randomly
	 * 
	 * @param n
	 * @return Array of cities
	 */
	public static Cidade [] inicializePopulation(int n) {
		Cidade [] result = new Cidade [n];
		for(int i=0;i<n;i++)
			result[i] = new Cidade (i, (int) numberGenerator(0,MAXCO0RDENATE), (int) numberGenerator(0,MAXCO0RDENATE));
		return result;
	}
	
	
	/**
	 * Function that permute cities
	 * 
	 * @param cidades
	 * @return Array of cities permuted
	 */
	public static Cidade [] randomPermutation(Cidade [] cidades) {
		Cidade [] v = new Cidade [cidades.length];
		v = cidades.clone();
		int r;
		Cidade aux;
		for(int i=1;i<cidades.length-1;i++) {
			r = (int) Math.round(numberGenerator(i,cidades.length-1));
			aux = v[i];
			v[i] = v[r];
			v[r] = aux;
		}
		return v;
	}
	
	
	/**
	 * Function that generate a random GA with relative good fitness
	 * 
	 * @param population
	 * @param n
	 * @return GA object
	 */
	public static GA GARandom(ArrayList<GA> population, int n) {
		int [] a = new int [n];
		int d;
		GA best;
		if(population.size() < 2) return null;
		for(int i=0;i<n;i++) {
			d = (int) Math.round(numberGenerator(0,population.size()-1));
			a[i] = d;
		}
		best = population.get(a[0]);
		for(int i=1;i<n;i++) {
			best = best.tournament(population.get(a[i]));
		}
		return best;
	}
}