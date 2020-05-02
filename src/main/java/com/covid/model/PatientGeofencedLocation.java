package com.covid.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// Generated May 1, 2020, 8:54:03 PM by Hibernate Tools 5.2.12.Final
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PatientGeofencedLocation generated by hbm2java
 */
@Entity
@Table(name = "patient_geofenced_location", schema = "release1")
public class PatientGeofencedLocation implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6588657628030039407L;
	private int geofencedLocationId;
	private int patientId;
	private Integer addressId;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Double radiusMetres;
	private String geofenceStatus;

	public PatientGeofencedLocation() {
	}

	public PatientGeofencedLocation(final int geofencedLocationId, final int patientId) {
		this.geofencedLocationId = geofencedLocationId;
		this.patientId = patientId;
	}

	public PatientGeofencedLocation(final int geofencedLocationId, final int patientId, final Integer addressId,
			final BigDecimal latitude, final BigDecimal longitude, final Double radiusMetres,
			final String geofenceStatus) {
		this.geofencedLocationId = geofencedLocationId;
		this.patientId = patientId;
		this.addressId = addressId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.radiusMetres = radiusMetres;
		this.geofenceStatus = geofenceStatus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "geofenced_location_id", unique = true, nullable = false)
	public int getGeofencedLocationId() {
		return this.geofencedLocationId;
	}

	public void setGeofencedLocationId(final int geofencedLocationId) {
		this.geofencedLocationId = geofencedLocationId;
	}

	@Column(name = "patient_id", nullable = false)
	public int getPatientId() {
		return this.patientId;
	}

	public void setPatientId(final int patientId) {
		this.patientId = patientId;
	}

	@Column(name = "address_id")
	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(final Integer addressId) {
		this.addressId = addressId;
	}

	@Column(name = "latitude", precision = 10, scale = 8)
	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(final BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", precision = 11, scale = 8)
	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(final BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Column(name = "radius_metres", precision = 17, scale = 17)
	public Double getRadiusMetres() {
		return this.radiusMetres;
	}

	public void setRadiusMetres(final Double radiusMetres) {
		this.radiusMetres = radiusMetres;
	}

	@Column(name = "geofence_status", length = 32)
	public String getGeofenceStatus() {
		return this.geofenceStatus;
	}

	public void setGeofenceStatus(final String geofenceStatus) {
		this.geofenceStatus = geofenceStatus;
	}

}
