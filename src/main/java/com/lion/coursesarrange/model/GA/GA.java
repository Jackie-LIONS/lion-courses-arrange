package com.lion.coursesarrange.model.GA;

import lombok.Data;

import java.io.Serializable;

//遗传算法本身操作所需的方法和变量
@Data
public class GA implements Serializable {
    //种群规模
    private final int populationSize;
    //变异率
    private final double mutationRate;
    //交叉率
    private final double crossoverRate;
    //精英成员数
    private final int elitismCount;
    //竞标赛大小？
    protected int tournamentSize;

    public GA(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int tournamentSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.tournamentSize = tournamentSize;
    }

    //初始化种群
    public Population initPopulation(Bootstrap bootstrap) {
        return new Population(populationSize, bootstrap);
    }

    //计算个体适应度
    public double calcIndividualFitness(Individual indiv, Bootstrap bootstrap) {
        //创建 新的引导类对象去使用 --从已存在的引导类复制
        Bootstrap threadBootstrap = new Bootstrap(bootstrap);
        threadBootstrap.createTable(indiv);
        //计算适应度
        int clashes = threadBootstrap.calcClashes();
        double fitness = 1 / (double) (clashes + 1);
        indiv.setFitness(fitness);
        return fitness;
    }

    //遍历种群 计算种群适应度 评价种群
    public void evalPopulationFitness(Population pop, Bootstrap bootstrap) {
        double populationFitness = 0;

        //循环计算个体适应度 累加
        for (Individual indiv : pop.getPopulation()) {
            populationFitness += this.calcIndividualFitness(indiv, bootstrap);
        }
        pop.setPopulationFitness(populationFitness);
    }

    //终止条件  到达最大世代后停止
    public boolean isEnd(Population pop) {
        return pop.getFittest(0).getFitness() == 1.0;
    }

    public boolean isEnd(int count, int maxCount) {
        return (count > maxCount);
    }

    //锦标赛
    public Individual getParent(Population pop) {
        //创建锦标赛
        Population tourPop = new Population(this.tournamentSize);

        //锦标赛中 加入随机个体
        pop.shuffle();
        for (int i = 0; i < this.tournamentSize; i++) {
            Individual tourIndiv = pop.getIndividual(i);
            tourPop.setIndividual(i, tourIndiv);
        }

        //返回最好的个体
        return tourPop.getFittest(0);
    }

    //均匀交叉
    public Population crossover(Population pop, Bootstrap bootstrap) {
        //创建新的种群
        Population newPop = new Population(pop.size());

        //循环当前种群 根据适应度
        for (int index = 0; index < pop.size(); index++) {
            Individual firstParent = pop.getFittest(index);

            //是否对个体应用交叉？
            if (this.crossoverRate > Math.random() && index > this.elitismCount) {
                //初始化后代
                Individual child = new Individual(bootstrap);

                //找到第二个亲代
                Individual SecondParent = getParent(pop);

                //循环基因组 交叉
                for (int geneIndex = 0; geneIndex < firstParent.getChromsomeLength(); geneIndex++) {
                    //均匀交叉
                    if (0.5 > Math.random()) {
                        child.setGene(geneIndex, firstParent.getGene(geneIndex));
                    } else {
                        child.setGene(geneIndex, SecondParent.getGene(geneIndex));
                    }
                }

                //将后代加入新的种群
                newPop.setIndividual(index, child);

            } else {
                //不交叉直接加入新种群
                newPop.setIndividual(index, firstParent);
            }

        }
        //返回新的种群
        return newPop;

    }

    //均匀变异
    public Population mutate(Population pop, Bootstrap bootstrap) {
        //初始化新的种群
        Population newPop = new Population(this.populationSize);

        //根据适应度循环当前的种群
        for (int index = 0; index < pop.size(); index++) {
            Individual indiv = pop.getFittest(index);
            //精英个体跳过种群变异
            if (index > this.elitismCount) {
                //创建随机个体来进行基因交换
                Individual randomIndiv = new Individual(bootstrap);
                //循环遍历当前个体的基因
                for (int geneIndex = 0; geneIndex < indiv.getChromsomeLength(); geneIndex++) {
                    //单个基因是否需要变异(交换)？
                    if (this.mutationRate > Math.random()) {
                        //交换基因
                        indiv.setGene(geneIndex, randomIndiv.getGene(geneIndex));
                    }

                }
            }
            //将新的个体加入种群
            newPop.setIndividual(index, indiv);

        }
        //返回变异后的种群
        return newPop;
    }


}
