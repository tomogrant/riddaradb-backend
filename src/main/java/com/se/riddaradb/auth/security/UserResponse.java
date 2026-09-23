package com.se.riddaradb.auth.security;

import java.util.List;

public record UserResponse (String username, List<String> authorities){
}
