package com.yolo.service;

import com.yolo.pojo.dto.AddressBookDTO;
import com.yolo.pojo.entity.AddressBook;
import com.yolo.pojo.vo.AddressVO;

import java.util.List;

public interface AddressBookService {
    void save(AddressBookDTO addressBookDTO);

    List<AddressVO> list();

    void update(AddressBook addressBook);

    void delete(Long id);

    AddressVO getById(Long id);
}
