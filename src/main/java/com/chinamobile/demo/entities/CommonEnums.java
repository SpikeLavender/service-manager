package com.chinamobile.demo.entities;


/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class CommonEnums {

	public enum  ServiceLevelEnum implements BaseEnum {
		STANDARD(0, "standard", 10.0),
		GOLDEN(1, "golden", 20.0),
		DIAMOND(2, "diamond", 30.0);
		private int code;
		private String name;
		private double fee;

		ServiceLevelEnum(int code, String name, double fee) {
			this.code = code;
			this.name = name;
			this.fee = fee;
		}

		public double getFee() {
			return fee;
		}

		public void setFee(double fee) {
			this.fee = fee;
		}

		@Override
		public int getCode() {
			return code;
		}
	}

	public enum  SliceTypeEnum implements BaseEnum {
		EMBB("eMBB", 0, 1.0),
		URLLC("uRLLC", 1, 1.5),
		MIOT("mIoT", 2, 2.5);
		private int code;
		private String name;
		private double fee;

		SliceTypeEnum(String name, int code, double fee) {
			this.name = name;
			this.code = code;
			this.fee = fee;
		}

		public double getFee() {
			return fee;
		}

		public void setFee(double fee) {
			this.fee = fee;
		}

		@Override
		public int getCode() {
			return code;
		}
	}
	public enum  OrderStatusEnum implements BaseEnum {
		WAITTING("waiting", 0),
		//TODO: waiting
		SUCCESS("success", 5),
		ERROR("error", 10);

		private int code;
		private String name;

		OrderStatusEnum(String name, int code) {
			this.name = name;
			this.code = code;
		}

		@Override
		public int getCode() {
			return code;
		}
	}
}
