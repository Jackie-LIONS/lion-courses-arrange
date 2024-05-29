package com.lion.coursesarrange.model.GA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


//保存由个体构成的数组   种群的总体适应度
public class Population {
    private Individual population[];
    private double populationFitness = -1;

    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }


    //种群初始化
    public Population(int populationSize, Bootstrap bootstrap) {
        //初始化种群
        this.population = new Individual[populationSize];

        //循环种群大小
        for (int individualCount = 0;individualCount<populationSize;individualCount++){
            //创建染色体
            Individual individual = new Individual(bootstrap);
            //加入种群
            population[individualCount] = individual;

        }
    }

    //获取种群
    public Individual[] getPopulation() {
        return this.population;
    }

    //适应度排序
    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }

                return 0;
            }
        });
        return this.population[offset];
    }


    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    public double getPopulationFitness() {
        return this.populationFitness;
    }

    public int size() {
        return this.population.length;
    }

    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

    public Individual getIndividual(int offset) {
        return population[offset];
    }


    //洗牌
    public void shuffle() {
        Random r = new Random();
        for (int i = population.length -1; i > 0; i--) {
            int index = r.nextInt(i+1);
            Individual individual = population[index];
            population[index] = population[i];
            population[i] = individual;
        }
    }

}
