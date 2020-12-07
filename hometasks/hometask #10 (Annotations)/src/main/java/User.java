/**
 * 03.12.2020
 * 15.Annotations_SOURCE
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@HtmlForm(method = "post", action = "/users")
public class User {
    @HtmlInput(name = "nickname", placeholder = "Ваш ник")
    private String nickname;
    @HtmlInput(name = "email",type = "email", placeholder = "Ваша почта")
    private String email;
    @HtmlInput(name = "password", type = "password", placeholder = "Пароль")
    private String password;
}
