package com.softwaremanager.schedulebuilder.Entity;



import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softwaremanager.schedulebuilder.Constant.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; 

    @NotBlank(message = "First Name cannot be blank!")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name cannot be blank!")
    @Column(name = "last_name")
    private String lastName;
    
    @Digits( integer = 2 ,fraction = 0, message ="Please enter a valid age number")
    @Column(name = "age")
    private int age;
    
    @DecimalMin(value = "7.0", inclusive = true, message="Please enter at least the minimum wage")
    @DecimalMax(value = "100.0", inclusive= false, message ="Please enter an hourly rate smaler than $ 100.0")
    @Column(name = "hourly_rate")
    private double hourlyRate;
    
    @NotBlank(message="Please enter a valid JobTitle e.i.: \"Manager\" ")
    @Column(name = "job_title")
    private String jobTitle;
    

    private int timeCardEmployeeId;

    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "you must specify a role (\"ADMIN\" or \"USER\")")
    @Column(name= "role")
    private Role role;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "employee_shift",
        joinColumns = @JoinColumn(name ="employee_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="shift_id", referencedColumnName = "id")
    )
    private List<Shift> shifts;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TimeCard> timeCards;


   
    public Employee(String firstName, String lastName, int i, Double hourlyRate, String jobTitle, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = i;
        this.hourlyRate = hourlyRate;
        this.jobTitle = jobTitle;
        this.role = role;
    }

    

    //this code set up a unique timeCard code for each employee

    public void setTimeCardEmployeeId(){
      this.timeCardEmployeeId = Constant.BASE_TIME_CARD_ID + this.id.intValue();  
    } 
    //-------------------------------------------------------


}