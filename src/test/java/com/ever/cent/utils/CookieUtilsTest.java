package com.ever.cent.utils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtilsTest {

    //HttpServletRequest request = mock(HttpServletRequest.class);
    //HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void testAddCookie() {
        final HttpServletResponse response = mock(HttpServletResponse.class);
        assertAll("CookieUtils.addCookie", () -> {
            CookieUtils.addCookie(response, "teste", "teste", 9999);
            assertNotNull(response.toString().contains("teste"));
        });
    }

    @Test
    void testDeleteCookie() {
        assertAll("CookieUtils.deleteCookie", () -> {
            final HttpServletRequest request = mock(HttpServletRequest.class);
            final HttpServletResponse response = mock(HttpServletResponse.class);
            when(request.getCookies()).thenReturn(new Cookie[] { new Cookie("teste", "teste")});
            CookieUtils.deleteCookie(request, response, "teste");
            assertNull(response.getHeader("teste"));
        });
    }

    @Test
    void testDeleteCookieEmpty(){
        assertAll("CookieUtils.deleteCokie", ()->{
            final HttpServletRequest request = mock(HttpServletRequest.class);
            final HttpServletResponse response = mock(HttpServletResponse.class);
            CookieUtils.deleteCookie(request, response, "teste2");
            assertNull(response.getHeader("teste2"));
        });
    }

    @Test
    void testDeleteCookieFromList(){
        assertAll("CookieUtils.deleteCokie", ()->{
            final HttpServletRequest request = mock(HttpServletRequest.class);
            final HttpServletResponse response = mock(HttpServletResponse.class);
            when(request.getCookies()).thenReturn(new Cookie[] { new Cookie("teste", "teste")});
            when(response.getHeader("teste")).thenReturn("teste max-age=9999");

            CookieUtils.deleteCookie(request, response, "teste2");
            assertNull(response.getHeader("teste2"));
            assertNotNull(response.getHeader("teste"));
        });
    }

    @Test
    void testDeserialize() {
        String cookie = Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize("teste"));

        assertAll("CookieUtils.deserialize", () -> {
            assertNotNull(CookieUtils.deserialize(new Cookie(cookie, cookie), String.class));
        });

    }

    @Test
    void testGetCookie() {
        String cookie = "teste";
        assertAll("CookieUtils.getCookie", () -> {
            final HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getCookies()).thenReturn(new Cookie[] { new Cookie(cookie, cookie)});
            assertNotNull(CookieUtils.getCookie(request, cookie));
        });
    }

    @Test
    void testGetCookieFromListOfCookies() {
        String cookie = "teste";
        String cookie2 = "teste2";
        assertAll("CookieUtils.getCookie", () -> {
            final HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getCookies()).thenReturn(new Cookie[] { new Cookie(cookie, cookie), new Cookie(cookie2, cookie2)});
            assertNotNull(CookieUtils.getCookie(request, cookie2));
        });
    }

    @Test
    void testGetCookieEmpty() {
        String cookie = "teste";
        assertAll("CookieUtils.getCookie", () -> {
            final HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getCookies()).thenReturn(new Cookie[] {});
            assert(CookieUtils.getCookie(request, cookie).equals(Optional.empty()));
        });
    }

    @Test
    void testSerialize() {
        String cookie = "teste";
        assertAll("CookieUtils.serialize", () -> {
            assertNotNull(CookieUtils.serialize(cookie));
        });
    }
}
