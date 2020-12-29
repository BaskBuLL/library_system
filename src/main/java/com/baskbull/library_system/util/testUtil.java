package com.baskbull.library_system.util;

import cn.hutool.crypto.SecureUtil;
import com.baskbull.library_system.entity.People;

import java.util.ArrayList;
import java.util.List;

/**
 * 临时
 * @author Dell
 */
public class testUtil {

    public static void main(String[] args) {
//        String password = "123";
//        System.out.println(SecureUtil.md5(password));
        List<People> a = new ArrayList<>();
        a.add(new People("name","难"));
        for(People x:a){
            System.out.println(x);
        }
    }
}
