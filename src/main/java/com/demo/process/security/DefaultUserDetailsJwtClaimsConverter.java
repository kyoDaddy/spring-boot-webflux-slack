package com.demo.process.security;

import com.demo.process.security.impl.DefaultUserDetails;

import java.util.Map;

public interface DefaultUserDetailsJwtClaimsConverter {

    DefaultUserDetails convert(final Map<String, Object> claims);

    Map<String, Object> convert(final DefaultUserDetails userDetails);

}
