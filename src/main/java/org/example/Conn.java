package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conn {

    private WebDriver driver;
    private WebElement element;
    private String driverType = "webdriver.chrome.driver";
    private String driverPath = "C:\\webDriver\\chromedriver.exe";
    private String url = "http://hospital.onlinemed.co.kr/";
    private List<String> id;
    public String startDate;
    public String endDate;
    private String nowYear = Integer.toString(LocalDate.now().getYear());
    private String getUrl;

    public Conn() {
        id = new ArrayList<>();
        id.add("omca36");
        id.add("omca34");
        id.add("omca35");
        id.add("omch33");
        id.add("omcf09");
        id.add("omcb05");
        id.add("omcg23");
    }

    public void login(int center) throws Exception{
        System.setProperty(driverType, driverPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        element = driver.findElement(By.id("id"));
        element.sendKeys(id.get(center));
        element = driver.findElement(By.id("pw"));
        element.sendKeys(id.get(center));
        element = driver.findElement(By.xpath("//*[@id=\"mfrm\"]/table/tbody/tr[4]/td/table/tbody/tr[3]/td/table/tbody/tr/td[3]/input"));
        element.click();
        setCheck();
        Thread.sleep(2000);
        System.out.println("프로그램을 종료합니다");
        driver.close();
    }

    public void setCheck() {
        getUrl = url+"reserve/rsv_list.php?scom=&yearid="+nowYear+"&searchstart="+startDate+"&searchend="+endDate+"&pip_yn=&sort1=&sort2=&dtestand=regdte&hpcchk=N"; // regdte = "예약입력일", rsvdte = "예약일
        driver.get(getUrl);
        List<WebElement> elements = driver.findElements(By.name("hpcchk"));
        for (int i=0; i < elements.size(); i++) {
            if (!elements.get(i).isSelected()) {
                elements.get(i).click();
            }
        }
    }
}