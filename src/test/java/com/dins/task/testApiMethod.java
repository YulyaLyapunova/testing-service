package com.dins.task;

import okhttp3.Response;
import org.apache.tools.ant.types.selectors.ReadableSelector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class testApiMethod {
    private ApiMethods method = new ApiMethods();

    @Test
    public void testShortFirstName() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("yu",
                "Lyapunova", "yulya.lyapunova@nordidy.ru").code());
    }

    @Test
    public void testLongFirstName() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("YulyaYulyaYulyaYulyaQ",
                "Lyapunova", "yulya.lyapunova@nordidy.ru").code());
    }

    @Test
    public void testShortLastName() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("Yulya",
                "Ly", "yulya.lyapunova@nordidy.ru").code());
    }

    @Test
    public void testLongLastName() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("Yulya",
                "LyapunovaLyapunovaaaa", "yulya.lyapunova@nordidy.ru").code());
    }

    @Test
    public void testEmailWithoutUsername() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("Yulya",
                "Lyapunova", "@nordidy.ru").code());
    }

    @Test
    public void testEmailWithoutSymbolAt() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("Yulya",
                "Lyapunova", "yulya.lyapunovanordidy.ru").code());
    }

    @Test
    public void testEmailWithoutDomain() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("Yulya",
                "Lyapunova", "yulya.lyapunova@.ru").code());
    }

    @Test
    public void testEmailWithoutDot() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("Yulya",
                "Lyapunova", "yulya.lyapunova@nordidyru").code());
    }

    @Test
    public void testEmailWithoutRootDomain() throws IOException {
        Assert.assertEquals(400, method.sendPostOfUsers("Yulya",
                "Lyapunova", "yulya.lyapunova@nordidy.").code());
    }

    @Test
    public void testLinkingPurchasesToUsers() throws IOException {
        String purchases = method.sendGetOfPurchases().body().string();
        String users = method.sendGetOfUsers().body().string();


        int flag = 0;
        JSONArray postsPurchases = new JSONArray(purchases);
        JSONArray postsUsers = new JSONArray(users);

        for (int i = 0; i < postsPurchases.length(); i++) {
            JSONObject postPurchases = postsPurchases.getJSONObject(i);
            for (int j = 0; j < postsUsers.length(); j++) {
                JSONObject postUsers = postsUsers.getJSONObject(j);
                if (postPurchases.getInt("userId") == postUsers.getInt("id")) {
                    flag = 1;
                    break;
                }
            }
            Assert.assertTrue(flag == 0, "A non-existing user is used for purchase");
            flag=0;
        }

    }


}
