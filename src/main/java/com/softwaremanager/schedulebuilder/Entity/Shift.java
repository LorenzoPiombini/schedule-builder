package com.softwaremanager.schedulebuilder.Entity;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Transient;
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

    @JsonIgnore
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

    @JsonIgnore
    @Transient
    private Double hourShift(){
        
        Duration shiftDuration = Duration.between(this.startTime, this.endTime);
        long hours = shiftDuration.toHours();
        long minutes = shiftDuration.toMinutesPart();

        Double shiftLasts = (double) hours + ((double)minutes / 60);
        return shiftLasts;
    }


    @Transient
    public Double getShiftDuration(){
        return hourShift();
    }

    

}