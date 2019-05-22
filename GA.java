
import java.util.ArrayList;
import java.util.Random;

public class GA {
	
	private Cidade [] caminho;
	private double fitness;
	
	//construtor
	public GA (ArrayList<Cidade> c) { setCidade(c); setFitness(); }
	public GA (Cidade [] c) { setCidade(c); setFitness(); }
	public GA (GA a) { setCidade(a.caminho); setFitness(); }
	
	
	/**
	 * Set path of GA
	 * 
	 * @param c
	 */
	private void setCidade(ArrayList<Cidade> c) { caminho = new Cidade [c.size()]; c.toArray(caminho); }
	
	
	/**
	 * Set path of GA
	 * 
	 * @param c
	 */
	private void setCidade(Cidade [] c) { 
		caminho = new Cidade [c.length];
		for(int i=0;i<c.length;i++)
			caminho[i] = c[i];
	}
	
	
	/**
	 * Set fitness of GA
	 */
	protected void setFitness() {
		fitness = 0;
		for(int i=0;i<caminho.length-1;i++)
			fitness += caminho[i].getP().dist(caminho[i+1].getP());
		fitness += caminho[caminho.length-1].getP().dist(caminho[0].getP());
	}
	
	
	/**
	 * Get path of GA
	 * 
	 * @return Path of GA
	 */
	public Cidade [] getCaminho() {
		return caminho;
	}
	
	
	/**
	 * Get fitness of GA
	 * 
	 * @return Fitness of GA
	 */
	public double getFitness() {
		return fitness;
	}
	
	
	/**
	 * Function that do crossover with 2 parentes and generate a child
	 * 
	 * @param a
	 * @param generator
	 * @return GA object
	 */
	protected GA crossOver(GA a, Random generator) {
		int start = 1 + (int) Math.round(generator.nextDouble() * (this.caminho.length-2));
		int finish = start + 1 + (int) Math.round(generator.nextDouble() * (this.caminho.length-1-start));
		ArrayList<Cidade> novoCaminho = new ArrayList<Cidade>();
		novoCaminho.add(this.caminho[0]);
		for(int i=start;i<finish;i++)
			novoCaminho.add(this.caminho[i]);
		for(int i=1;i<a.getCaminho().length;i++)
			if(!novoCaminho.contains(a.getCaminho()[i])) {
				novoCaminho.add(a.caminho[i]);
			}
		return new GA (novoCaminho);
	}
	
	
	/**
	 * Function that choose the best GA object (with less fitness)
	 * @param a
	 * @return GA object
	 */
	protected GA tournament(GA a) {
		if(this.fitness < a.fitness) return this;
		else return a;
	}	
	
	
	/**
	 * Function that make mutation of a GA element (with mutation probability)
	 * @param generator
	 * @param p
	 */
	protected void mutation(Random generator, double p){
		Cidade aux;
		for(int i=0;i<this.caminho.length;i++) {
			if(generator.nextDouble() < p) {
				int j = (int) (generator.nextDouble() * (this.caminho.length-1));
				int k = (j+1) % this.caminho.length;
				aux = this.caminho[k];
				this.caminho[k] = this.caminho[j];
				this.caminho[j] = aux;
			}
		}
		while(this.caminho[0].getId() != 0) {
			aux = this.caminho[0];
			for(int l=0;l<this.caminho.length-1;l++) 
				this.caminho[l] = this.caminho[l+1];
			this.caminho[this.caminho.length-1] = aux;
		}
	}
}