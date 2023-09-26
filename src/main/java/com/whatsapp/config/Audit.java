package com.whatsapp.config;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy","createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class Audit implements Serializable {
	
	
	@Column
    private String createdBy;

	@Column
    private String updatedBy;
    
    @CreatedDate
    private Instant createdAt=ZonedDateTime.now(TimeZone.getTimeZone("GMT").toZoneId()).toInstant();;

    @LastModifiedDate
    private Instant updatedAt=ZonedDateTime.now(TimeZone.getTimeZone("GMT").toZoneId()).toInstant();;
	
	@Column
	private boolean isDeleted = false;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
