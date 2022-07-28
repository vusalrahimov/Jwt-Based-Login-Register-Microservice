package io.desofme.cityservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumStatus {
    ENABLE(1),DISABLE(0);
    private int value;
}
