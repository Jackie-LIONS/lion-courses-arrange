package com.lion.coursesarrange.model.GA;


import com.lion.coursesarrange.model.pojo.CourseTable;
import com.lion.coursesarrange.model.pojo.Room;
import com.lion.coursesarrange.model.pojo.Task;
import com.lion.coursesarrange.model.pojo.Timeslot;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Bootstrap {
    private HashMap<Integer, Room> rooms;
    private HashMap<Integer, Timeslot> timeslots;
    //教学任务
    private HashMap<Integer, Task> tasks;
    //排课结果
    private CourseTable tables[];

    private int numTables = 0;

    //初始化新的时间表  创建对象


    public Bootstrap(HashMap<Integer,Room> rooms, HashMap<Integer,Timeslot> timeslots, HashMap<Integer,Task> tasks) {
        this.rooms = rooms;
        this.timeslots = timeslots;
        this.tasks = tasks;
    }

    public Bootstrap(Bootstrap cloneable ) {
        this.rooms = cloneable.getRooms();
        this.timeslots = cloneable.getTimeslots();
        this.tasks = cloneable.getTasks();
    }

    public Bootstrap() {
        this.rooms = new HashMap<Integer,Room>();
        this.tasks = new HashMap<Integer,Task>();
        this.timeslots = new HashMap<Integer,Timeslot>();
    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        for (Room room : rooms){
            this.rooms.put(room.getId(),room);
        }
    }

    public HashMap<Integer, Timeslot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<Timeslot> timeslots) {
        for (Timeslot timeslot : timeslots){
            //限制只有工作时间加入 排课的时间
            if ((timeslot.getTimeId()-1)%5 !=4 && timeslot.getTimeId()<26 ){
                this.timeslots.put(timeslot.getTimeId(),timeslot);
            }
        }
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    //获取所有的学习小组
    public Task[] getTasksAsArray(){
        return (Task[]) this.tasks.values().toArray(new Task[this.tasks.size()]);
    }

    public void setTasks(List<Task> tasks) {
        for (Task task :tasks){
            this.tasks.put(task.getId(),task);
        }
    }

    //获取随机房间
    public Room getRandomRoom(){
        Object[] roomsArray = this.rooms.values().toArray();
        Room room = (Room) roomsArray[(int) (Math.random() * roomsArray.length)];
        return room;
    }

    //获取指定id的房间
    public Room getRoom(int roomId){
        if(!this.rooms.containsKey(roomId)){
            System.out.println("Rooms doesn't contain key" +roomId);
        }
        return (Room) this.rooms.get(roomId);
    }

    //获取指定id的教学任务
    public Task getTask(int taskId){
        return (Task) this.tasks.get(taskId);
    }


    //获取随机的时间段
    public Timeslot getRandomTimeslot(){
        Object[] timeslotArray = timeslots.values().toArray();
        Timeslot timelot =(Timeslot) timeslotArray[(int) (Math.random() * timeslotArray.length)];
        return timelot;
    }

    // 获取教学任务数组
    public CourseTable[] getTables(){
        return this.tables;
    }

    //获取需要安排的教学任务数
    public  int getNumTables(){
        if(this.numTables >0){
            return numTables;
        }

        numTables = this.tasks.size();

        return this.numTables;
    }

    //计算冲突 返回冲突数
    public int calcClashes(){
        int clashes =0;

        for(CourseTable tableA : this.tables){
            //检查房间容量
            int roomCapacity = this.getRoom(tableA.getRoom().getId()).getCapacity();
            int groupSize = this.getTask(tableA.getTask().getId()).getCclasses().getSize();

            if (roomCapacity<groupSize){
                clashes++;
            }

            //检查房间是否已经被使用
            for (CourseTable tableB :this.tables){
                if (tableA.getRoom().getId().equals(tableB.getRoom().getId())
                        && tableA.getTimeslot().getTimeId().equals(tableB.getTimeslot().getTimeId())
                        && ! tableA.getTask().getId().equals(tableB.getTask().getId())){
                    clashes++;
                    break;
                }
            }

            //检查老师是否可用
            for(CourseTable tableB : this.tables){
                if(tableA.getTask().getTeacher().getId().equals(tableB.getTask().getTeacher().getId())
                        && tableA.getTimeslot().getTimeId().equals(tableB.getTimeslot().getTimeId())
                        && !tableA.getTask().getId().equals(tableB.getTask().getId())){
                    clashes++;
                    break;
                }
            }

            //检查班级是否可用
            for(CourseTable tableB : this.tables){
                boolean flag = false;
                String[] names = tableA.getTask().getCclasses().getClassesCourseName().split(",");
                String classesNameB = tableB.getTask().getCclasses().getClassesCourseName();
                for (String classesNameA :names){
                    //班级包含 即为单个班级相同
                    if (classesNameB.contains(classesNameA)){
                        flag = true;
                        break;
                    }
                }
                if( flag && tableA.getTimeslot().getTimeId().equals(tableB.getTimeslot().getTimeId())
                        && !tableA.getTask().getId().equals(tableB.getTask().getId())){
                    clashes++;
                    break;
                }
            }
        }
        return clashes;

    }




    //使用染色体编码  创建课表
    public void createTable(Individual individual){
        //初始化课表
        CourseTable[] tables = new CourseTable[this.getNumTables()];

        //获取个体的染色体
        int[] chromsome = individual.getChromsome();
        int chromesomePos = 0;
        int tablesIndex = 0;

        //初始化 随机赋值
        for (Task task : this.getTasksAsArray()) {

            tables[tablesIndex] = new CourseTable();
            //设置教学任务
            tables[tablesIndex].setTask(task);

            //设置时间段
            Timeslot timeslot = new Timeslot();
            timeslot.setTimeId(chromsome[chromesomePos]);
            tables[tablesIndex].setTimeslot(timeslot);
            chromesomePos++;

            //设置房间
            Room room = new Room();
            room.setId(chromsome[chromesomePos]);
            tables[tablesIndex].setRoom(room);
            chromesomePos++;

            tablesIndex++;
        }

        //赋值课程表
        this.tables = tables;
    }


}
