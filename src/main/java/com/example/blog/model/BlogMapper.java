package com.example.blog.model;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.rdb.BlogInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface BlogMapper {

    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToString")
    @Mapping(target = "updateTime", source = "updateTime", qualifiedByName = "localDateTimeToString")
    BlogDto dao2Dto(BlogInfo blogInfo);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(target = "updateTime", source = "updateTime", qualifiedByName = "stringToLocalDateTime")
    BlogInfo dto2Dao(BlogDto blogDto);


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
