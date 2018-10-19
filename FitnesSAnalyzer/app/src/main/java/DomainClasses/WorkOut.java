package DomainClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkOut {

    @SerializedName("distance_km")
    @Expose
    private Double distanceKm;
    @SerializedName("peptalks")
    @Expose
    private Peptalks peptalks;
    @SerializedName("sport2")
    @Expose
    private Integer sport2;
    @SerializedName("heart_rate_bpm_avg")
    @Expose
    private Integer heartRateBpmAvg;
    @SerializedName("has_playlist")
    @Expose
    private Boolean hasPlaylist;
    @SerializedName("duration_sec")
    @Expose
    private Integer durationSec;
    @SerializedName("has_points")
    @Expose
    private Boolean hasPoints;
    @SerializedName("feed_id")
    @Expose
    private String feedId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("speed_kmh_avg")
    @Expose
    private Double speedKmhAvg;
    @SerializedName("heart_rate_bpm_max")
    @Expose
    private Integer heartRateBpmMax;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sport")
    @Expose
    private Integer sport;

    //Constructor
    public WorkOut(String startTime, Double distanceKm, Peptalks peptalks,
                   Integer sport2, Double speedKmhAvg, String name, Integer id,
                   Boolean hasPlaylist, Integer sport, Integer durationSec, Boolean hasPoints,
                   String feedId, Integer heartRateBpmAvg, Integer heartRateBpmMax) {
        this.startTime = startTime;
        this.distanceKm = distanceKm;
        this.peptalks = peptalks;
        this.sport2 = sport2;
        this.speedKmhAvg = speedKmhAvg;
        this.name = name;
        this.id = id;
        this.hasPlaylist = hasPlaylist;
        this.sport = sport;
        this.durationSec = durationSec;
        this.hasPoints = hasPoints;
        this.feedId = feedId;
        this.heartRateBpmAvg = heartRateBpmAvg;
        this.heartRateBpmMax = heartRateBpmMax;
    }

    public String getStartTime() {
        return startTime;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public Peptalks getPeptalks() {
        return peptalks;
    }

    public Integer getSport2() {
        return sport2;
    }

    public Double getSpeedKmhAvg() {
        return speedKmhAvg;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getHasPlaylist() {
        return hasPlaylist;
    }

    public Integer getSport() {
        return sport;
    }

    public Integer getDurationSec() {
        return durationSec;
    }

    public Boolean getHasPoints() {
        return hasPoints;
    }

    public String getFeedId() {
        return feedId;
    }

    public Integer getHeartRateBpmAvg() {
        return heartRateBpmAvg;
    }

    public Integer getHeartRateBpmMax() {
        return heartRateBpmMax;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public void setPeptalks(Peptalks peptalks) {
        this.peptalks = peptalks;
    }

    public void setSport2(Integer sport2) {
        this.sport2 = sport2;
    }

    public void setSpeedKmhAvg(Double speedKmhAvg) {
        this.speedKmhAvg = speedKmhAvg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHasPlaylist(Boolean hasPlaylist) {
        this.hasPlaylist = hasPlaylist;
    }

    public void setSport(Integer sport) {
        this.sport = sport;
    }

    public void setDurationSec(Integer durationSec) {
        this.durationSec = durationSec;
    }

    public void setHasPoints(Boolean hasPoints) {
        this.hasPoints = hasPoints;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public void setHeartRateBpmAvg(Integer heartRateBpmAvg) {
        this.heartRateBpmAvg = heartRateBpmAvg;
    }

    public void setHeartRateBpmMax(Integer heartRateBpmMax) {
        this.heartRateBpmMax = heartRateBpmMax;
    }
}