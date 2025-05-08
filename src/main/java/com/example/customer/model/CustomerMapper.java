package com.example.customer.model;
import com.example.customer.dto.CustomerDto;
import com.example.customer.model.rdb.CustomerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToString")
    CustomerDto dao2Dto(CustomerInfo customerInfo);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "stringToLocalDateTime")
    CustomerInfo dto2Dao(CustomerDto customerDto);

    @Named("localDateTimeToString")
    static String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) :
                dateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    @Named("stringToLocalDateTime")
    static LocalDateTime stringToLocalDateTime(String dateTimeStr) {
        return (dateTimeStr != null && !dateTimeStr.isEmpty())
                ? LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : null;
    }
}
