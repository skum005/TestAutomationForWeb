package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class OrgDetails {

    private String tradingName;
    private String postcode;
    private String industryType;
    private String numOfWorkers;
    private String payeeRefNumber;
    private String emailAddress;
    private String howDidYouFindUs;
    private String stagingOrStartDate;

}