package com.sky.controller.user;


import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userAddressBookController")
@Slf4j
@RequestMapping("/user/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping
    public Result<String> addAddress(@RequestBody AddressBook addressBook) {
        log.info("新增地址：{}", addressBook);
        addressBookService.addAddress(addressBook);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<AddressBook>> getAddressList() {
        log.info("查询地址");
        List<AddressBook> addressBookList = addressBookService.getAddressList();
        return Result.success(addressBookList);
    }

    @GetMapping("/default")
    public Result<AddressBook> getDefaultAddress() {
        log.info("查询默认地址");
        AddressBook addressBook = addressBookService.getDefaultAddress();
        return Result.success(addressBook);
    }

    @PutMapping("/default")
    public Result<String> setDefault(@RequestBody AddressBook addressBook) {
        log.info("设置默认地址：{}", addressBook);
        addressBookService.setDefault(addressBook);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<AddressBook> getAddressById(@PathVariable Long id) {
        log.info("根据id查询地址：{}", id);
        AddressBook addressBook = addressBookService.getAddressById(id);
        return Result.success(addressBook);
    }

    @DeleteMapping
    public Result<String> deleteAddressById(Long id) {
        log.info("根据id删除地址：{}", id);
        addressBookService.deleteAddressById(id);
        return Result.success();
    }

    @PutMapping
    public Result<String> updateAddress(@RequestBody AddressBook addressBook) {
        log.info("修改地址：{}", addressBook);
        addressBookService.updateAddress(addressBook);
        return Result.success();
    }

}
