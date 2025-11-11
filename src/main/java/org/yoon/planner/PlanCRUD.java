package org.yoon.planner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class PlanCRUD implements ICRUD {

    ArrayList<Plan> list;
    Scanner s;
    final String fname = "Dictionary.txt";
    Connection conn;

    PlanCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
        conn = DBConnection.getConnection();
    }

    public void loadData() {
        list.clear();

        String selectall = "select * from Dictionary";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectall);
            while (true) {
                if (!rs.next()) break;
                int id = rs.getInt("id");
                int level = rs.getInt("level");
                int category = rs.getInt("category");
                int finish = rs.getInt("finish");
                String created_date = rs.getString("created_date");
                String contents = rs.getString("contents");
                list.add(new Plan(id, level, category, finish, created_date, contents));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object addPlan() {
        return null;
    }

    @Override
    public int updatePlan(Object obj) {
        return 0;
    }

    @Override
    public int deletePlan(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll(){
        loadData();
        System.out.println("---------------------------");
        for(int i=0; i<list.size();i++){
            System.out.print((i+1) +" ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("---------------------------");
    }

    public void listLevelPlan(){
        System.out.println("=> 중요도(1~3) 입력 : ");
        int level = s.nextInt();
        s.nextLine();

        ArrayList<Plan> temp = new ArrayList<>();
        for(int i=0; i<list.size();i++){
            if(list.get(i).getLevel() == level){
                temp.add(list.get(i));
            }
        }
        System.out.println("---------------------------");
        for(int i=0; i<temp.size();i++){
            System.out.print((i+1) +" ");
            System.out.println(temp.get(i).toString());
        }
        System.out.println("---------------------------");
    }

    public void listCategoryPlan(){
        System.out.println("=> 카테고리(1_학업 2_약속 3_개인) 입력 : ");
        int category = s.nextInt();
        s.nextLine();
        
        ArrayList<Plan> temp = new ArrayList<>();
        for(int i=0; i<list.size();i++){
            if(list.get(i).getCategory() == category){
                temp.add(list.get(i));
            }
        }
        System.out.println("---------------------------");
        for(int i=0; i<temp.size();i++){
            System.out.print((i+1) +" ");
            System.out.println(temp.get(i).toString());
        }
        System.out.println("---------------------------");
    }

    public void listSearchPlan(){
        System.out.println("=> 찾고 싶은 내용 : ");
        String keyword = s.next();
        s.nextLine();
        ArrayList<Plan> temp = new ArrayList<>();

        for(int i=0; i<list.size();i++){
            String contents = list.get(i).getContents();
            if(contents.contains(keyword)){
                temp.add(list.get(i));
            }
        }
        System.out.println("---------------------------");
        for(int i=0; i<temp.size();i++){
            System.out.print((i+1) +" ");
            System.out.println(temp.get(i).toString());
        }
        System.out.println("---------------------------");
    }
}
