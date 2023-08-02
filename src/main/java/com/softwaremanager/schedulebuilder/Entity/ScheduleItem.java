package com.softwaremanager.schedulebuilder.Entity;


import java.time.LocalDate;
import java.util.List;


import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "schedule_item", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"date"})
})
public class ScheduleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "forcasted_sales")
    private Double forecastedSalesForTheDay;

    
    @ManyToMany(mappedBy = "scheduleItems", cascade = CascadeType.ALL)
    private List<Shift> shiftInTheScheduleItem;

    public ScheduleItem(LocalDate date, Double sales){
        this.date = date;
        this.forecastedSalesForTheDay = sales;
    }
}
