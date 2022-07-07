package com.practice.practice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private String downFrom;

    @Column(name="down_to") 
    private String downTo;

    public Practice() {
    }

    public Practice(String provider, String flowName, String downFrom, String downTo) {
        this.provider = provider;
        this.flowName = flowName;
        this.downFrom = downFrom;
        this.downTo = downTo;
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


    public String getDownFrom() {
        return downFrom;
    }

    public void setDownFrom(String downFrom) {
        this.downFrom = downFrom;
    }

    public String getDownTo() {
        return downTo;
    }

    public void setDownTo(String downTo) {
        this.downTo = downTo;
    }
}
