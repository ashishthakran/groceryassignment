package com.cgi.api.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractBaseDocument implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Field(name = "id")
    protected UUID id;
}
