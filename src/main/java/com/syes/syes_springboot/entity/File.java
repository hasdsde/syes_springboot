package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private String size;

    private String url;

    private String userid;

    private String md5;

    private String userid;

    private Boolean is_delete;

    private String enable;

    private LocalDateTime createtime;
}
