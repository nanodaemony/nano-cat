package com.nano.cat.entity;

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
@TableName("user_profile")
public class UserProfile {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("appleId")
    private String appleId;

    @TableField("nickname")
    private String nickname;

    private String avatar;

    private String email;

    private Integer gender;

    private String address;

    @TableField("relationShipStatus")
    private Integer relationShipStatus;

    private Integer status;

    private Date dbctime;

    private Date dbutime;

}