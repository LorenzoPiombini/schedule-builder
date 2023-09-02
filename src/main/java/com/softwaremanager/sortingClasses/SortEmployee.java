package com.softwaremanager.sortingClasses;

import java.util.Comparator;
import java.util.List;

import com.softwaremanager.schedulebuilder.Entity.Employee;
/*
 * this class sort the Employee entity
 */
public class SortEmployee implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }

    // sort by last name (default when you call the get Employees)
    public void sortByLastName(List<Employee> list){
        for (int i = 0; i < list.size() -1; i++) {
             if(compare(list.get(i), list.get(i+1)) > 0){
                Employee temp = list.get(i);
                list.set(i, list.get(i+1));
                list.set(i+1, temp);
             }
        }
    }
    
}
