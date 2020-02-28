package com.dili.customer.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.dili.customer.validator.EnterpriseView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 由MyBatis Generator工具自动生成
 * <p>
 * This file was generated on 2020-01-02 16:18:39.
 */
@Getter
@Setter
@ToString
public class Contacts implements Serializable {

    private static final long serialVersionUID = -1896023012443783818L;
    /**
     * ID
     */
    private Long id;

    /**
     * 所属客户
     */
    private Long customerId;

    /**
     * 所属市场
     */
    private Long marketId;

    /**
     * 姓名
     */
    @NotBlank(message = "联系人姓名不能为空", groups = {EnterpriseView.class})
    @Size(max = 20, message = "联系人姓名请保持在20个字符以内", groups = {EnterpriseView.class})
    private String name;

    /**
     * 性别男，女
     */
    private Integer gender;

    /**
     * 电话
     */
    @NotBlank(message = "联系人电话不能为空", groups = {EnterpriseView.class})
    @Size(max = 20, message = "联系人电话请保持在20个字符以内", groups = {EnterpriseView.class})
    private String phone;

    /**
     * 民族
     */
    private Integer nation;

    /**
     * 地址
     */
    @Size(max = 250, message = "联系人地址请保持在250个字符以内")
    private String address;

    /**
     * 职务/关系
     */
    @Size(max = 100, message = "联系人职务请保持在100个字符以内")
    private String position;

    /**
     * 出生日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    /**
     * 备注
     */
    @Size(max = 250, message = "联系人备注请保持在250个字符以内")
    private String notes;

    /**
     * 是否默认联系人
     */
    @NotNull(message = "联系人是否默认不能为空", groups = {EnterpriseView.class})
    private Integer isDefault;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyTime;

    /**
     * 创建人
     */
    private Long creatorId;

    /**
     * 修改人
     */
    private Long modifierId;

}