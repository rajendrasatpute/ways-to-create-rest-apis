package io.github.rajendrasatpute.samplespringbootapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class City {
    @Id
    @Column(name = "city")
    private String cityName;
    @Column(name = "lat")
    private String latitude;
    @Column(name = "lng")
    private String longitude;
}
