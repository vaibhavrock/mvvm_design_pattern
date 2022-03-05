package com.example.mvvm_design_pattern.network

object ApiConstants {
    const val BASE_URL = "https://api.foursquare.com"

    // region Default request parameter values
    const val CLIENT_ID_VALUE = "SZUXEOI25ZI1QL1XLCA0T5YS0SWVB4J5ZDZGB5PUWJ1L4WJX"
    const val CLIENT_SECRET_VALUE = "GIU3NUT4AG4JTEQNR0OERSW25MWDFHLYGSJO1NN3V3MSPKQE"
    const val VERSION_VALUE = 20190425
    const val RADIUS_VALUE = 1000
    const val LIMIT_VALUE = 10
    // endregion

    // region Request parameter names
    const val PARAM_CLIENT_ID = "client_id"
    const val PARAM_CLIENT_SECRET = "client_secret"
    const val PARAM_VERSION = "v"
    const val PARAM_NEAR = "near"
    const val PARAM_RADUIS = "radius"
    const val PARAM_LIMIT = "limit"
    //endregion

    const val CACHE_SIZE = 10L * 1024 * 1024
    const val CONNECTION_TIMEOUT_SECONDS = 10L
}