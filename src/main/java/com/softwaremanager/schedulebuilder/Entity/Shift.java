package com.softwaremanager.schedulebuilder.Entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "date")
    private LocalDate date;
    
    @ManyToMany(mappedBy = "shifts", cascade = CascadeType.ALL)
    private List<Employee> employees;

    

    public Shift(LocalTime start, LocalTime end, LocalDate date){
        this.startTime = start;
        this.endTime = end;
        this.date = date;
    }

    
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