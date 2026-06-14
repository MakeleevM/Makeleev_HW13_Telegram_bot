package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement yearSelect = $(".react-datepicker__year-select");
    private final SelenideElement monthSelect = $(".react-datepicker__month-select");

    private SelenideElement dayCell(String day) {
        return $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)");
    }

    public void setDate(String day, String month, String year) {
        yearSelect.selectOption(year);
        monthSelect.selectOption(month);
        dayCell(day).click();
    }
}