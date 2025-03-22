package com.nano.cat.logic;

import cn.hutool.json.JSONUtil;
import com.nano.cat.data.po.UserProfile;
import com.nano.cat.mapper.UserProfileMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserProfileTest {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Test
    public void test() {
        UserProfile user = UserProfile.builder()
                                      .appleId("xiaohua")
                                      .nickname("小华")
                                      .avatar("http://www.baidu.com")
                                      .email("xiaohua@geekidea")
                                      .gender(1)
                                      .address("北京市海淀区")
                                      .relationShipStatus(1)
                                      .status(1)
                                      .build();
        int insertFlag = userProfileMapper.insert(user);
        System.out.println(JSONUtil.toJsonStr(userProfileMapper.selectById(1)));;
    }

}