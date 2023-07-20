package com.softwaremanager.schedulebuilder.repository;
import com.softwaremanager.schedulebuilder.Entity.ScheduleItem;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleItemRepository extends CrudRepository<ScheduleItem, Long>{
    
}
