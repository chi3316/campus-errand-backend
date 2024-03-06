package com.yolo.service.impl;

import com.yolo.context.BaseContext;
import com.yolo.mapper.AddressBookMapper;
import com.yolo.pojo.entity.AddressBook;
import com.yolo.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    //默认地址：1
    private static final Integer IS_DEFAULT = 1;
    private static final Integer IS_NOT_DEFAULT = 0;
    @Autowired
    private AddressBookMapper addressBookMapper;
    /**
     * 新增地址
     */
    @Override
    public void save(AddressBook addressBook) {
        //向表中一条地址记录
        addressBook.setUserId(BaseContext.getCurrentId());
        //addressBook.setUserId(2L); 测试用
        //添加的时候普通的地址，需要在这里设置is_default字段为0
        addressBook.setIs_default(IS_NOT_DEFAULT);
        addressBookMapper.insert(addressBook);
    }
}
