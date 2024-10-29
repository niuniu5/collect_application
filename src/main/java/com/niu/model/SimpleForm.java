package com.niu.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleForm implements Serializable{

	private Integer id;
	private String name;

}
