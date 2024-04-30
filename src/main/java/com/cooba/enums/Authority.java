package com.cooba.enums;

import lombok.Getter;

@Getter
public enum Authority {
    CREATE,
    SELECT,
    UPDATE,
    DELETE,
    SPECIAL,
    ;
}
