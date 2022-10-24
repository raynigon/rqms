package com.raynigon.rqms.domain.offline.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OfflineMetricData {

    @Column(name = "metric_name")
    private String name;

    @Column(name = "metric_cutoff")
    private int cutoff;
}
