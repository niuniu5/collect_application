package com.niu.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DistrictDto implements Serializable{

	private Integer id;
	private String name;
	private Integer parent;

	private List<DistrictDto> children;

}
