package org.yoon.planner;

public interface ICRUD {
    public Object addPlan();
    public int updatePlan(Object obj);
    public int deletePlan(Object obj);
    public void selectOne(int id);
}
