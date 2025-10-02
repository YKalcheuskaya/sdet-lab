package test;

import api.BaseApiTest;
import db.DbUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.UsersApiUtil;

public class ApiDbUserEmailTest extends BaseApiTest {

    @DataProvider
    public Object[][] userIds() {
        // подставь реальные id, которые есть в БД
        return new Object[][]{{1}, {2}, {3}};
    }

    @Test(dataProvider = "userIds")
    public void userEmail_inApiMatchesDatabase(int id) {
        // 1) API: получить email
        String apiEmail =
                UsersApiUtil.getUser(id)
                        .extract()
                        .body()
                        .jsonPath()
                        .getString("data.email");

        // 2) DB: взять email по тому же id
        String dbEmail = DbUtil.getUserEmailById(id);

        // 3) Мягкая защита от null-пустот
        Assert.assertNotNull(apiEmail, "API returned null email for id=" + id);
        Assert.assertNotNull(dbEmail, "DB returned null email for id=" + id);

        // 4) Сверка
        Assert.assertEquals(apiEmail.trim().toLowerCase(), dbEmail.trim().toLowerCase(),
                "Email mismatch for user id=" + id);
    }
}
