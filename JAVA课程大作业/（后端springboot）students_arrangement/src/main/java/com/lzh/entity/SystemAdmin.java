package com.lzh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LZH
 * @since 2022-11-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SystemAdmin implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value="id",type= IdType.AUTO)
      private Integer id;

    private String username;

    private String password;

    private String sysname;

    private String gender;

    private String telephone;


}
