package com.practice.practice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
 * Please use
 * @Getter
 * @Setter
 * And avoid as much bolierplate as you can
 */
@Entity
@Table(name="practice")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="provider")
    private String provider;

    @Column(name="flow_name")
    private String flowName;

    @Column(name="down_from")
    private LocalDateTime downFrom;

    @Column(name="down_to") 
    private LocalDateTime downTo;

    public Practice() {
    }

    public Practice(String provider, String flowName,LocalDateTime downFrom,LocalDateTime downTo) {
        this.provider = provider;
        this.flowName = flowName;
        this.downFrom = downFrom;
        this.downTo = downTo;
//        System.out.println(this.downFrom);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }


    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }


    public LocalDateTime getDownFrom() {
        return downFrom;
    }

    public void setDownFrom(LocalDateTime downFrom) {
        this.downFrom = downFrom;
    }

    public LocalDateTime getDownTo() {
        return downTo;
    }

    public void setDownTo(LocalDateTime downTo) {
        this.downTo = downTo;
    }
}
