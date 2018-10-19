package DomainClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkOutSet {

    @SerializedName("data")
    private List<WorkOut> data;
    @SerializedName("more")
    private Boolean more;

    public List<WorkOut> getworkOutList() {
        return data;
    }

    public void setData(List<WorkOut> data) {
        this.data = data;
    }

    public Boolean getMore() {
        return more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }
}