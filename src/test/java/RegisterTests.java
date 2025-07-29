import base.BaseMethods;
import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTests extends BaseMethods {
    RegisterPage registerPage = new RegisterPage();
    BaseTests baseTests = new BaseTests();

    // başarılı kullanıcı oluşturma testi
    @Test(description = "TC001 - Başarılı kullanıcı kaydı oluşturma test edilir.")
    public void SuccessRegister() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(driver.getCurrentUrl(), "https://qrsofra.com/dashboard");
    }
    // giriş yap butonunun ve password göz ikonunun aktifliği testi
    @Test(description = "TC002 - Register sayfasındaki Giriş Yap butonunun çalışması test edilir.")
    public void CheckLogin() {
        registerPage.clickLoginButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(driver.getCurrentUrl(), "https://qrsofra.com/auth/jwt/sign-in");
        baseTests.sleep(3000);
    }
    @Test(description = "TC003 - Şifre alanındaki göz ikonuna tıklanınca şifrenin görünürlüğünün değişip değişmediği test edilir.")
    public void PasswordVisibleIconCheck() {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        registerPage.clickPasswordEyeIcon();
        baseTests.sleep(2000);
        baseTests.assertEquals(passwordField.getAttribute("type"), "text");
        baseTests.sleep(2000);
        registerPage.clickPasswordEyeIcon();
        baseTests.assertEquals(passwordField.getAttribute("type"), "password");
        baseTests.sleep(2000);

    }
    // başarısız kullanıcı oluşturma testleri
    @Test(description = "TC004 - Varolan bir kullanıcı ile kayıt olma işlemi test edilir.")
    public void ExistingUserRegisterTest() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail("fatihnuripur@gmail.com")
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getExistingUserErrorMessage(), "Bu e-posta veya telefon numarası ile daha önce hesap oluşturulmuş!");
    }
    @Test(description = "TC005 - E-posta alanına emoji ve özel semboller girildiğinde sistemin geçersiz e-mail uyarısı vermesi test edilir.")
    public void EmailWithSymbolCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail("automation#@gmail.com")
                // emoji girildiğinde de hata alınıyor.
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getEmailErrorMessage(), "Email geçerli bir email adresi olmalıdır!");
    }
    @Test(description = "TC006 - E-posta alanı boş bırakıldığında hata vermesi test edilir.")
    public void EmptyEmailFieldCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail("")
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getEmailErrorMessage(), "Email alanı zorunludur!");
    }
    @Test(description = "TC007 - İsim alanı boş bırakıldığında hata vermesi test edilir.")
    public void EmptyNameFieldCheck() {
        registerPage.sendKeysRegisterName("")
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getNameErrorMessage(), "İsim alanı zorunludur!");
    }
    @Test(description = "TC008 - Soyisim alanı boş bırakıldığında hata vermesi test edilir.")
    public void EmptySurnameFieldCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname("")
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getSurnameErrorMessage(), "Soyisim alanı zorunludur!");
    }
    @Test(description = "TC009 - Telefon numarasının doğru formatta girilip girilmediği test edilir.")
    public void WrongFormatPhoneNumberCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone("1111111111")
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getPhoneNumberError(), "Geçersiz telefon numarası!");
    }
    @Test(description = "TC010 - Telefon numarasının boş bırakıldığında hata verip vermediği test edilir.")
    public void EmptyPhoneNumberCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone("")
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getPhoneNumberError(), "Telefon numarası alanı zorunludur!");
    }
    @Test(description = "TC011 - Şifrenin en az 6 karakter olma kuralı test edilir.")
    public void PasswordMin6CharsCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword("12345")
                .sendKeysRegisterRepeatPassword("12345")
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getPasswordError(), "Şifre en az 6 karakter olmalıdır!");
    }
    @Test(description = "TC012 - Şifre tekrar edilmediğinde alınan hata test edilir.")
    public void PasswordRepeatCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword("")
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getRepeatPasswordError(), "Şifreyi tekrar giriniz!");
    }
    @Test(description = "TC013 - Rıza metni ve hizmet şartları alanının seçilmemesi durumunda alınacak hata test edilir.")
    public void EmptyPoliciesCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword(registerPassword)
                .sendKeysRegisterRepeatPassword(registerRepeatPassword)
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getPoliciesError(), "Hizmet Şartlarını ve Gizlilik Politikasını kabul etmelisiniz.");
    }
    @Test(description = "TC014 - Bilgiler doldurulmadan Hesap Oluştur butonuna basınca uyarı çıkması test edilir..")
    public void EmptyFieldsErrorCheck() {
        registerPage.clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getPasswordError(), "Şifre en az 6 karakter olmalıdır!");
        baseTests.assertEquals(registerPage.getRepeatPasswordError(), "Şifreyi tekrar giriniz!");
        baseTests.assertEquals(registerPage.getPhoneNumberError(), "Telefon numarası alanı zorunludur!");
        baseTests.assertEquals(registerPage.getEmailErrorMessage(), "Email alanı zorunludur!");
        baseTests.assertEquals(registerPage.getNameErrorMessage(), "İsim alanı zorunludur!");
        baseTests.assertEquals(registerPage.getSurnameErrorMessage(), "Soyisim alanı zorunludur!");
        baseTests.assertEquals(registerPage.getPoliciesError(), "Hizmet Şartlarını ve Gizlilik Politikasını kabul etmelisiniz.");
    }
    @Test(description = "TC011 - Şifre tekrar girildiğinde input'un üstündeki labelda yazan text test edilir.")
    //alttaki test case'inde bug bulunmuştur. bu nedenle test fail vermektedir.
    public void EmptyPasswordRepeatInputErrorCheck() {
        registerPage.sendKeysRegisterName(registerName)
                .sendKeysRegisterSurname(registerSurname)
                .sendKeysRegisterEmail(registerEmail)
                .sendKeysRegisterPhone(registerPhone)
                .sendKeysRegisterPassword("12345")
                .sendKeysRegisterRepeatPassword("")
                .sendKeysRegisterAcceptConsent()
                .sendKeysRegisterAcceptPolicies()
                .clickRegisterButton();
        baseTests.sleep(3000);
        baseTests.assertEquals(registerPage.getInputLabelError(), "Şifre");
    }
}
