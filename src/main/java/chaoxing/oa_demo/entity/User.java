package chaoxing.oa_demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName 用户表
 */
@Data
@TableName(value ="user")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 类型（10：管理员, 20:面试官）
     */
    private Integer type;

    /**
     * 是否删除标记
     */
    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}