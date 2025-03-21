package com.nano.cat.web.data.user;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:09
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVO {

    private long userId;

    private String nickname;

    private String avatar;

    private String email;

    private int gender;

    private String address;

    private int relationShipStatus;

}