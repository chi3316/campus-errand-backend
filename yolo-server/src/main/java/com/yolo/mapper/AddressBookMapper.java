package com.yolo.mapper;

import com.yolo.pojo.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressBookMapper {
    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long addressBookId);

    void insert(AddressBook addressBook);
}
