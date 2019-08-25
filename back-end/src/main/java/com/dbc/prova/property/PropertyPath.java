package com.dbc.prova.property;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class PropertyPath {

    protected static final String TEST_PROPERTIES = "test";

    protected static final String FIPE_PROPERTIES = TEST_PROPERTIES + "." + "fipe";

    protected static final String JWT_PROPERTIES = TEST_PROPERTIES + "." + "jwt";

}
