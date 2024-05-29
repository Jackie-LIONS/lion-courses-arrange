package com.lion.coursesarrange.model.GA;


import com.lion.coursesarrange.model.pojo.Task;

//个体类  候选解
//负责存储 操作一条染色体
//两个构造方法  1.接受长度 随机初始化一条染色体  2.接受数组 作为染色体
public class Individual {
    //染色体
    private int[] chromsome;
    //适应度追踪
    private double fitness = -1;


    public Individual(int[] chromsome) {
        //创建个体染色体
        this.chromsome = chromsome;
    }

    //初始化   利用timetable对象来确定必须排课的班级数(染色体长度)
    //染色体本身由timetable随机取得教室，时段，教授生成
    public Individual(Bootstrap bootstrap){
        //班级数
        int numTables = bootstrap.getNumTables();

        //1位基因为房间   1位基因为时间段
        int chromosomeLength = numTables*2;
        //创建随机的染色体
        int[] newChromosome = new int[chromosomeLength];
        int chromosomeIndex = 0;
        //教学任务循环
        for (Task task : bootstrap.getTasksAsArray()){
                //加入教学任务

                //加入随机时间
                int timeslotId = bootstrap.getRandomTimeslot().getTimeId();
                newChromosome[chromosomeIndex] = timeslotId;
                chromosomeIndex++;

                //加入随机房间
                int roomId = bootstrap.getRandomRoom().getId();
                newChromosome[chromosomeIndex] = roomId;
                chromosomeIndex++;

        }
        this.chromsome = newChromosome;

    }


    public int[] getChromsome() {
        return chromsome;
    }

    public int getChromsomeLength() {
        return this.chromsome.length;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }


    //基因
    public int getGene(int offset) {
        return this.chromsome[offset];
    }

    public void setGene(int offset, int gene) {
        this.chromsome[offset] = gene;
    }

    //输出基因字符串
    @Override
    public String toString() {
        String output = "";
        for (int gene = 0; gene < this.chromsome.length; gene++) {
            output += this.chromsome[gene];
        }

        return output;
    }
}
