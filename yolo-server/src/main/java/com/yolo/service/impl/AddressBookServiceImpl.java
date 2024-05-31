package com.yolo.service.impl;

import com.yolo.context.BaseContext;
import com.yolo.mapper.AddressBookMapper;
import com.yolo.pojo.entity.AddressBook;
import com.yolo.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    // 默认地址：1
    private static final Integer IS_DEFAULT = 1;
    private static final Integer IS_NOT_DEFAULT = 0;
    @Autowired
    private AddressBookMapper addressBookMapper;
    /**
     * 新增地址
     */
    @Override
    public void save(AddressBook addressBook) {
        // 向表中一条地址记录
        addressBook.setUserId(BaseContext.getCurrentId());
        // 添加的时候普通的地址，需要在这里设置is_default字段为0
        addressBook.setIsDefault(IS_NOT_DEFAULT);
        addressBookMapper.insert(addressBook);
    }

    /**
     * 列出所有地址信息
     * @return
     */
    @Override
    public List<AddressBook> list() {
        Long userId = BaseContext.getCurrentId();
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(userId);
        // 封装成entity , mapper层更通用，提高扩展性
        return addressBookMapper.select(addressBook);
    }

    /**
     * 修改地址
     * @param addressBook
     */
    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 删除地址
     * @param id
     */
    @Override
    public void delete(Long id) {
        addressBookMapper.delete(id);
    }

    /**
     * 设置默认地址
     * @param addressBook
     */
    @Override
    public void setDefault(AddressBook addressBook) {
        // 默认地址只能有一个，先把所有的地址设置为非默认，再将该地址设置为默认
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(IS_NOT_DEFAULT);
        addressBookMapper.setIsDefaultByUserId(addressBook);

        // 将该地址设置为默认
        addressBook.setIsDefault(IS_DEFAULT);
        addressBookMapper.update(addressBook);
    }
}
