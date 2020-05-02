package com.covid.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// Generated May 1, 2020, 8:54:03 PM by Hibernate Tools 5.2.12.Final
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PhoneDeviceApp generated by hbm2java
 */
@Entity
@Table(name = "phone_device_app", schema = "release1")
public class PhoneDeviceApp implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -68984553151551895L;
	private int deviceAppId;
	private int phoneNumberId;
	private String deviceImei;
	private String deviceMacId;
	private String deviceOs;
	private String deviceOsVersion;
	private String deviceManufacturer;
	private String deviceModel;
	private String devicePhoneNumber;
	private String devicePhoneServiceProvider;
	private String appVersion;
	private Date appInstalledDateTime;

	public PhoneDeviceApp() {
	}

	public PhoneDeviceApp(final int deviceAppId, final int phoneNumberId) {
		this.deviceAppId = deviceAppId;
		this.phoneNumberId = phoneNumberId;
	}

	public PhoneDeviceApp(final int deviceAppId, final int phoneNumberId, final String deviceImei,
			final String deviceMacId, final String deviceOs, final String deviceOsVersion,
			final String deviceManufacturer, final String deviceModel, final String devicePhoneNumber,
			final String devicePhoneServiceProvider, final String appVersion, final Date appInstalledDateTime) {
		this.deviceAppId = deviceAppId;
		this.phoneNumberId = phoneNumberId;
		this.deviceImei = deviceImei;
		this.deviceMacId = deviceMacId;
		this.deviceOs = deviceOs;
		this.deviceOsVersion = deviceOsVersion;
		this.deviceManufacturer = deviceManufacturer;
		this.deviceModel = deviceModel;
		this.devicePhoneNumber = devicePhoneNumber;
		this.devicePhoneServiceProvider = devicePhoneServiceProvider;
		this.appVersion = appVersion;
		this.appInstalledDateTime = appInstalledDateTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "device_app_id", unique = true, nullable = false)
	public int getDeviceAppId() {
		return this.deviceAppId;
	}

	public void setDeviceAppId(final int deviceAppId) {
		this.deviceAppId = deviceAppId;
	}

	@Column(name = "phone_number_id", nullable = false)
	public int getPhoneNumberId() {
		return this.phoneNumberId;
	}

	public void setPhoneNumberId(final int phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	@Column(name = "device_imei", length = 20)
	public String getDeviceImei() {
		return this.deviceImei;
	}

	public void setDeviceImei(final String deviceImei) {
		this.deviceImei = deviceImei;
	}

	@Column(name = "device_mac_id", length = 20)
	public String getDeviceMacId() {
		return this.deviceMacId;
	}

	public void setDeviceMacId(final String deviceMacId) {
		this.deviceMacId = deviceMacId;
	}

	@Column(name = "device_os", length = 10)
	public String getDeviceOs() {
		return this.deviceOs;
	}

	public void setDeviceOs(final String deviceOs) {
		this.deviceOs = deviceOs;
	}

	@Column(name = "device_os_version", length = 10)
	public String getDeviceOsVersion() {
		return this.deviceOsVersion;
	}

	public void setDeviceOsVersion(final String deviceOsVersion) {
		this.deviceOsVersion = deviceOsVersion;
	}

	@Column(name = "device_manufacturer", length = 16)
	public String getDeviceManufacturer() {
		return this.deviceManufacturer;
	}

	public void setDeviceManufacturer(final String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}

	@Column(name = "device_model", length = 16)
	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(final String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Column(name = "device_phone_number", length = 20)
	public String getDevicePhoneNumber() {
		return this.devicePhoneNumber;
	}

	public void setDevicePhoneNumber(final String devicePhoneNumber) {
		this.devicePhoneNumber = devicePhoneNumber;
	}

	@Column(name = "device_phone_service_provider", length = 20)
	public String getDevicePhoneServiceProvider() {
		return this.devicePhoneServiceProvider;
	}

	public void setDevicePhoneServiceProvider(final String devicePhoneServiceProvider) {
		this.devicePhoneServiceProvider = devicePhoneServiceProvider;
	}

	@Column(name = "app_version", length = 10)
	public String getAppVersion() {
		return this.appVersion;
	}

	public void setAppVersion(final String appVersion) {
		this.appVersion = appVersion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "app_installed_date_time", length = 29)
	public Date getAppInstalledDateTime() {
		return this.appInstalledDateTime;
	}

	public void setAppInstalledDateTime(final Date appInstalledDateTime) {
		this.appInstalledDateTime = appInstalledDateTime;
	}

}
