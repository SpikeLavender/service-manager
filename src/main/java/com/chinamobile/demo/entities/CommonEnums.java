package com.chinamobile.demo.entities;


/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class CommonEnums {

	public enum  ServiceLevelEnum implements BaseEnum {
		STANDARD(0, "standard"),
		GOLDEN(1, "golden"),
		DIAMOND(2, "diamond");
		private int code;
		private String name;

		ServiceLevelEnum(int code, String name) {
			this.code = code;
			this.name = name;
		}

		@Override
		public int getCode() {
			return code;
		}
	}

	public enum  SliceTypeEnum implements BaseEnum {
		EMBB("eMBB", 0),
		URLLC("uRLLC", 1),
		MIOT("mIoT", 2);
		private int code;
		private String name;

		SliceTypeEnum(String name, int code) {
			this.name = name;
			this.code = code;
		}

		@Override
		public int getCode() {
			return code;
		}
	}
	public enum  OrderStatusEnum implements BaseEnum {
		SUCCESS("success", 0),
		ERROR("error", 1),
		PAYING("is paying", 2);
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
