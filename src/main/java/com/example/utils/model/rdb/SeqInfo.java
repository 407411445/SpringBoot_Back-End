package com.example.utils.model.rdb;

import jakarta.persistence.*;

@Entity
@Table( name = "DAILYSEQ")
public class SeqInfo {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "DATESEQ")
    private Long dateSeq;

    @Column( name = "NUMSEQ")
    private int numSeq;

    public SeqInfo() {

    }

    public SeqInfo(Long dateSeq, int numSeq) {
        this.dateSeq = dateSeq;
        this.numSeq = numSeq;
    }

    public Long getDateSeq() {
        return dateSeq;
    }

    public void setDateSeq(Long dateSeq) {
        this.dateSeq = dateSeq;
    }

    public int getNumSeq() {
        return numSeq;
    }

    public void setNumSeq(int numSeq) {
        this.numSeq = numSeq;
    }
}
