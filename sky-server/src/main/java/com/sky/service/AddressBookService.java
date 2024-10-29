package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    void addAddress(AddressBook addressBook);

    List<AddressBook> getAddressList();

    AddressBook getDefaultAddress();

    void setDefault(AddressBook addressBook);

    AddressBook getAddressById(Long id);

    void deleteAddressById(Long id);

    void updateAddress(AddressBook addressBook);
}
