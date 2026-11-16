package Lesson9PojoDeSerialization;

import java.util.List;

public class Course {
    private List<Courseinfo> webAutomation;
    private List<Courseinfo> api;
    private List<Courseinfo> mobile;

    public List<Courseinfo> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<Courseinfo> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<Courseinfo> getApi() {
        return api;
    }

    public void setApi(List<Courseinfo> api) {
        this.api = api;
    }

    public List<Courseinfo> getMobile() {
        return mobile;
    }

    public void setMobile(List<Courseinfo> mobile) {
        this.mobile = mobile;
    }


}
