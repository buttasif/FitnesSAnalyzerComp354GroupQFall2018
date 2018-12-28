package View_Helpers;

public class ChallengeHelper {

	Integer challenger;
	Integer challengee;
	Integer challengeId;
	Integer chalengeStaus;
	
		
	public ChallengeHelper(Integer challenger, Integer chalengee, Integer challengeId, Integer chalengeStaus) {
		super();
		this.challenger = challenger;
		this.challengee = chalengee;
		this.challengeId = challengeId;
		this.chalengeStaus = chalengeStaus;
	}
	
	public Integer getChallenger() {
		return challenger;
	}
	public void setChallenger(Integer challenger) {
		this.challenger = challenger;
	}
	public Integer getChallengee() {
		return challengee;
	}
	public void setChallengee(Integer challengee) {
		this.challengee = challengee;
	}
	public Integer getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(Integer challengeId) {
		this.challengeId = challengeId;
	}
	public Integer getChalengeStaus() {
		return chalengeStaus;
	}
	public void setChalengeStaus(Integer chalengeStaus) {
		this.chalengeStaus = chalengeStaus;
	}
	
}
