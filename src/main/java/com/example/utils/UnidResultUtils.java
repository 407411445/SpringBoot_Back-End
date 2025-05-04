package com.example.utils;

import com.example.utils.model.rdb.DailySeqRepository;
import com.example.utils.model.rdb.SeqInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public  class UnidResultUtils {

    @Autowired
    private DailySeqRepository dailySeqRepository;

    public String getUnid(String blogPrefix, String dateStr){
        StringBuffer sb = new StringBuffer();
        SeqInfo entity = dailySeqRepository.findById(dateStr).orElse(null);
        if (entity != null) {
            entity.setNumSeq(entity.getNumSeq() + 1);
        }
        else{
            entity = new SeqInfo(Long.parseLong(dateStr), 1);
        }
        dailySeqRepository.save(entity);
        String currentSeq = String.format("%02d", entity.getNumSeq());
        sb.append(blogPrefix).append(dateStr).append(currentSeq);
        return sb.toString();
    }

    public static class InsertResult {
        private final Integer objectid;
        private final String unid;
        public InsertResult(Integer objectid, String unid) {
            super();
            this.objectid = objectid;
            this.unid = unid;
        }
        public Integer getObjectid() {
            return objectid;
        }
        public String getUnid() {
            return unid;
        }
    }
}
