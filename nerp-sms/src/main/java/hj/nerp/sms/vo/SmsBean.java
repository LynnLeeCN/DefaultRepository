package hj.nerp.sms.vo;

public class SmsBean {
	private String idSms = ""; // 单号
	private String idCom = ""; // 单位
	private String varContent = "";// 内容
	private String varMobile = "";// 手机号码
	private String idRecorder = "";// 录入人
	private String idSend = "";// 发件人名称
	private String dateSend = "";// 发送时间
	private String varMobilename = "";// 收件人名称

	public String getIdSms() {
		return idSms;
	}

	public void setIdSms(String idSms) {
		this.idSms = idSms;
	}

	public String getIdCom() {
		return idCom;
	}

	public void setIdCom(String idCom) {
		this.idCom = idCom;
	}

	public String getVarContent() {
		return varContent;
	}

	public void setVarContent(String varContent) {
		this.varContent = varContent;
	}

	public String getVarMobile() {
		return varMobile;
	}

	public void setVarMobile(String varMobile) {
		this.varMobile = varMobile;
	}

	public String getIdRecorder() {
		return idRecorder;
	}

	public void setIdRecorder(String idRecorder) {
		this.idRecorder = idRecorder;
	}

	public String getIdSend() {
		return idSend;
	}

	public void setIdSend(String idSend) {
		this.idSend = idSend;
	}

	public String getDateSend() {
		return dateSend;
	}

	public void setDateSend(String dateSend) {
		this.dateSend = dateSend;
	}

	public String getVarMobilename() {
		return varMobilename;
	}

	public void setVarMobilename(String varMobilename) {
		this.varMobilename = varMobilename;
	}

}
