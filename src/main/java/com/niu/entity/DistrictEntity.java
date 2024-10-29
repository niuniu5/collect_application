package com.niu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * 地区实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "acl_district")
public class DistrictEntity implements Serializable {

    /**ID，导入时写死**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**地区名称**/
    @Column(length = 16)
    private String name;

    /**地区代码**/
    @Column(length = 6)
    private String code;

    /**父ID，空表示1级**/
    private Integer parent;
}
