package com.caochaojie;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetterAndSetterTest {

    @Getter
    private String getField;

    @Setter
    private String setField;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String otherField;

    @Getter
    @Setter
    private final String fs = null;


}
