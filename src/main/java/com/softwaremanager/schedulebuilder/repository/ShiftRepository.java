package com.softwaremanager.schedulebuilder.repository;






import org.springframework.data.repository.CrudRepository;


import com.softwaremanager.schedulebuilder.Entity.Shift;





/**
 * {@code interface} name: ShiftRepository
 *  <p>  this is the Repository Class, which is responsable for
 *   all the CRUD operations(Create, Read, Update, Delete).
 *  </p>
 * 
 *  @author Lorenzo Piombini
 */

public interface ShiftRepository extends CrudRepository<Shift, Long>{
         
         
}