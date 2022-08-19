package com.syes.syes_springboot.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author test
 * @since 2022-08-19
 */
@Getter
@Setter
@ApiModel(value = "Tablemenu对象", description = "")
public class Tablemenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("表格属性")
    private String field;

    @ApiModelProperty("表格对应中文名")
    private String label;

    @ApiModelProperty("所属表格")
    private String tablename;


}
