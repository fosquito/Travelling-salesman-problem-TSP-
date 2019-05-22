import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class GATest {


    private Cidade[] cidades;
    static Random generator = new Random();
    GA genes;
    private ArrayList<GA> populacao = new ArrayList<GA>();

    @org.junit.Before
    public void setUp() throws Exception {
    	cidades = new Cidade [10];
        cidades = Main.inicializePopulation(10);
        for(int i=0;i<10;i++) {
            populacao.add(new GA (Main.randomPermutation(cidades)));
        }
    }


    @org.junit.Test
    public void getFitness() {
        double dist=0;
        for(int i=0; i< populacao.get(0).getCaminho().length-1; i++){
            dist+= populacao.get(0).getCaminho()[i].getP().dist(populacao.get(0).getCaminho()[i+1].getP());
        }
        dist+=populacao.get(0).getCaminho()[0].getP().dist(populacao.get(0).getCaminho()[populacao.get(0).getCaminho().length-1].getP());
        int fit = (int) dist;
        assertEquals((int) populacao.get(0).getFitness(), fit);
    }

    @org.junit.Test
    public void crossOver() {
        GA parent1;
        GA parent2;
        GA child;
        for (int i = 0; i < populacao.size()-1; i++) {
            parent1 = populacao.get(i);
            parent2 = populacao.get(i+1);
            child = parent1.crossOver(parent2, generator);
            assertEquals(parent1.getCaminho().length, child.getCaminho().length);
            assertEquals(parent2.getCaminho().length, child.getCaminho().length);
        }
    }

    @org.junit.Test
    public void tournamentPopulation() {
        GA crom = populacao.get(1).tournament(populacao.get(0));
        assertNotNull(crom);
        if(populacao.get(0).getFitness() <= populacao.get(1).getFitness())
        	assertEquals(crom, populacao.get(0));
        else
        	assertEquals(crom, populacao.get(1));
    }

    @org.junit.Test
    public void randomPermutation() {
        int insucesso=0;
        Cidade[] permut = Main.randomPermutation(cidades);
        for (int i = 0; i < permut.length-1; i++) {
            for(int j=i+1; j<permut.length; j++){
                if(permut[i].equals(permut[j]))
                    insucesso=-1;
            }
        }
        assertEquals(0, insucesso);
        assertEquals(cidades.length, permut.length);

    }


    @org.junit.Test
    public void mutation() {
        GA parent1;
        GA temp;
        int sucesso=0;
        for (int i = 0; i < populacao.size()-1; i++) {
            parent1 = populacao.get(i);


            temp = new GA(parent1);
            parent1.mutation(generator, 0.8);

            assertEquals(parent1.getCaminho().length, temp.getCaminho().length);
            for (int j =0; j<parent1.getCaminho().length; j++){
                if(temp.getCaminho()[j].getP().getX()!=parent1.getCaminho()[j].getP().getX() || temp.getCaminho()[j].getP().getY()!=parent1.getCaminho()[j].getP().getY()){
                    sucesso=1;
                }
            }
            assertEquals(1, sucesso);
        }

    }
}