package com.softwaremanager.schedulebuilder.Entity;

import java.time.LocalTime;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shift")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;
    
    @ManyToMany(mappedBy = "shifts", cascade = CascadeType.ALL)
    private List<Employee> employees;

    
    @ManyToMany
    @JoinTable(
        name = "shifts_schedule_items",
        joinColumns = @JoinColumn(name = "shift_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="scheduleItem_id", referencedColumnName = "id")

    )
    private List<ScheduleItem> scheduleItems;

    public Shift(LocalTime start, LocalTime end){
        this.startTime = start;
        this.endTime = end;
    }

}