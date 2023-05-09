package com.ever.cent.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;

import com.ever.cent.domain.dto.LocalUser;
import com.ever.cent.domain.dto.SocialProvider;
import com.ever.cent.domain.dto.UserInfo;
import com.ever.cent.domain.model.Role;
import com.ever.cent.domain.model.User;

public class GeneralUtilsTest {

    @Test
    void testBuildSimpleGrantedAuthoritiesAddOneAuthorite() {
        Role role = Role.builder()
                .name("ROLE_USER")
                .build();
        Set<Role> roles = Set.of(role);

        List<SimpleGrantedAuthority> result = GeneralUtils.buildSimpleGrantedAuthorities(roles);
        assert (!CollectionUtils.isEmpty(result));
    }

    @Test
    void testBuildUserInfo() {
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        Collection<? extends GrantedAuthority> authorities = List.of(grantedAuthority);

        User user = User.builder()
                .id(1L)
                .displayName("test")
                .email("test@test.com")
                .build();
        LocalUser localUser = new LocalUser("123", "123", true, false, false, false, authorities, user);
        UserInfo userInfo = GeneralUtils.buildUserInfo(localUser);
        assertNotNull(userInfo);
    }

    @Test
    void testToSocialProviderGoogle() {
        String providerId = "google";
        SocialProvider result = GeneralUtils.toSocialProvider(providerId);
        assert (result == SocialProvider.GOOGLE);
    }

    @Test
    void testToSocialProviderNoMapped() {
        String providerId = "bla";
        SocialProvider result = GeneralUtils.toSocialProvider(providerId);
        assert (result == SocialProvider.LOCAL);
    }
}
