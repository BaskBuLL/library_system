package com.baskbull.library_system.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baskbull.library_system.common.lang.Result;
import com.baskbull.library_system.entity.Book;
import com.baskbull.library_system.entity.Reader;
import com.baskbull.library_system.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baskbull
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping("/list")
    public Result list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                       @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize) {
        QueryWrapper<Reader> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("rd_id");
        Page page = new Page(pageNo, pageSize);
        IPage readers = readerService.page(page, queryWrapper);
        return Result.ok(readers);
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable(name = "id") Integer id) {
        Reader reader = readerService.getById(id);
        return Result.ok(reader);
    }

    @PutMapping("/edit")
    public Result edit(@Validated @RequestBody Reader reader) {
        readerService.updateById(reader);
        return Result.ok("编辑成功!");
    }

    @PostMapping("/lose/{id}")
    public Result lose(@PathVariable(name = "id") Integer id){
        Reader reader = readerService.getById(id);
        reader.setRdStatus(2);
        readerService.updateById(reader);
        return Result.ok("挂失成功！");
    }

    @PostMapping("/free/{id}")
    public Result free(@PathVariable(name = "id") Integer id){
        Reader reader = readerService.getById(id);
        reader.setRdStatus(1);
        readerService.updateById(reader);
        return Result.ok("挂失成功！");
    }

    @PostMapping("/changePassword/{id}/{password}")
    public Result changePassword(@PathVariable(name = "id") Integer id,
                                 @PathVariable(name = "password") String password){
        Reader reader = readerService.getById(id);
        reader.setRdPwd(SecureUtil.md5(password));
        readerService.updateById(reader);
        return Result.ok("修改成功！");
    }

    @PostMapping("add")
    public Result add(@Validated @RequestBody Reader reader){
        Integer rdType = reader.getRdType();
        if(reader.getRdPhoto()==null || "".equals(reader.getRdPhoto())){
            reader.setRdPhoto("暂无");
        }
        if(rdType == 21){
            reader.setRdAdminRoles(25);
        }else if (rdType == 20){
            reader.setRdAdminRoles(99);
        }else if (rdType == 10){
            reader.setRdAdminRoles(99);
        }
        reader.setRdPwd(SecureUtil.md5("123"));
        reader.setRdBorrowQty(0);
        reader.setRdStatus(1);
        reader.setRdDateReg(LocalDateTime.now());
        readerService.save(reader);
        return Result.ok("添加成功");
    }
}
