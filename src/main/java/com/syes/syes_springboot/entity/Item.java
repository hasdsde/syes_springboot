package com.syes.syes_springboot.entity;

import cn.hutool.core.img.Img;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
@Getter
@Setter
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键自增，不行再改
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private LocalDateTime createtime;

    private String description;

    private Boolean onsale;

    private Double price;

    private String userid;

    @TableField(exist = false)
    private List<File> imgList;

    @TableField(exist = false)
    private User user;

    public void addImg(File file){
        if (this.imgList == null){
            this.imgList = new ArrayList<>();
        }
        imgList.add(file);
    }
}
