package com.example.utils.model.rdb;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DailySeqRepository extends JpaRepository<SeqInfo,String> {

}
