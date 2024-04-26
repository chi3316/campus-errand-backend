package com.yolo.mapper;

import com.yolo.pojo.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long addressBookId);

    void insert(AddressBook addressBook);

    List<AddressBook> select(AddressBook addressBook);

    void update(AddressBook addressBook);

    @Delete("delete from address_book where id = #{id}")
    void delete(Long id);

    @Update("update address_book set is_default = 0 where user_id = #{userId}")
    void setIsDefaultByUserId(AddressBook addressBook);

}
