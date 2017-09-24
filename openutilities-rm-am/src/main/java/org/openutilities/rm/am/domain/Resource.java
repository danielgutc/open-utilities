package org.openutilities.rm.am.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

/**
 * Represents a resource.
 */
@Entity
@Table(name = "resources")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resource
{
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String resourceType;
    @Column (nullable = false)
    private String code;
}
