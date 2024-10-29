package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;


    @Override
    public void addAddress(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressBookMapper.addAddress(addressBook);
    }

    @Override
    public List<AddressBook> getAddressList() {
        return addressBookMapper.getAddressList(BaseContext.getCurrentId());
    }

    @Override
    public AddressBook getDefaultAddress() {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(1);
        return addressBookMapper.selectByCondition(addressBook);
    }

    @Override
    public void setDefault(AddressBook addressBook) {
        AddressBook reset = new AddressBook();
        reset.setUserId(BaseContext.getCurrentId());
        reset.setIsDefault(0);
        addressBookMapper.update(reset);
        addressBook.setIsDefault(1);
        addressBookMapper.update(addressBook);
    }

    @Override
    public AddressBook getAddressById(Long id) {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(id);
        return addressBookMapper.selectByCondition(addressBook);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressBookMapper.deleteById(id);
    }

    @Override
    public void updateAddress(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }
}
