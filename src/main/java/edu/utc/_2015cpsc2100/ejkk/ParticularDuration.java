package edu.utc._2015cpsc2100.ejkk;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 * A duration with location
 *
 */
@Embeddable
public class ParticularDuration implements Serializable {

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date startTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date endTime;
	private static final long serialVersionUID = 1L;

	public ParticularDuration(Date startTime, Date endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	protected ParticularDuration(){}
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}   
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Duration getDuration()
	{
		return Duration.between(startTime.toInstant(), endTime.toInstant());
	}
   
}
