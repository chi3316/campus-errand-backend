package com.yolo.service.impl;

import com.yolo.context.BaseContext;
import com.yolo.mapper.AddressBookMapper;
import com.yolo.pojo.dto.AddressBookDTO;
import com.yolo.pojo.entity.AddressBook;
import com.yolo.pojo.vo.AddressVO;
import com.yolo.service.AddressBookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    private AddressBookMapper addressBookMapper;
    /**
     * 新增地址
     */
    @Override
    public void save(AddressBookDTO addressBookDTO) {
        // 向表中一条地址记录
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookMapper.insert(addressBook);
    }

    /**
     * 列出所有地址信息
     * @return
     */
    @Override
    public List<AddressVO> list() {
        Long userId = BaseContext.getCurrentId();
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(userId);
        // 封装成entity , mapper层更通用，提高扩展性
        List<AddressBook> addressBooks = addressBookMapper.select(addressBook);
        List<AddressVO> addressVOs = new ArrayList<AddressVO>();
        for(AddressBook address : addressBooks) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(address, addressVO);
            addressVOs.add(addressVO);
        }
        return addressVOs;
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
    /*
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
     */
}
