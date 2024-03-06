package com.yolo.service;

import com.yolo.pojo.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    void save(AddressBook addressBook);

    List<AddressBook> list();
}
