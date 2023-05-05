package com.joshnieuwstad.fibapi.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seen_indices")
public class Index implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fib_index")
    private int fibIndex;

    public Index() {}

    public Index(int index) {
        this.fibIndex = index;
    }

    public int getIndex() {
        return this.fibIndex;
    }

    public void setIndex(int index) {
        this.fibIndex = index;
    }

    @Override
    public String toString() {
        return "Index{index: " + this.getIndex() + "}";
    }
}
