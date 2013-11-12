package cn.ce.web.rest.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlRootElement(name = "BinParseResultVO")
@XmlType(propOrder = { "errorMsg", "processMsg", "eventVOList",
		"binlogfilename", "binlogPosition", "resCode" }, name = "BinParseResultVOType")
@XmlAccessorType(value = XmlAccessType.NONE)
public class BinParseResultVO {
	private List<String> errorMsg = new ArrayList<String>();
	private List<String> processMsg = new ArrayList<String>();
	private List<EventVO> eventVOList = new ArrayList<EventVO>();
	private String binlogfilename;
	private Long binlogPosition;
	private String resCode;

	@XmlElementWrapper(name = "errorMsgList")
	@XmlElement(name = "errorMsg")
	public List<String> getErrorMsg() {
		return errorMsg;
	}

	public void addErrorMsg(String errorMsg) {
		this.errorMsg.add(errorMsg);
	}

	@XmlElement(name = "binlogfilename", type = String.class)
	public String getBinlogfilename() {
		return binlogfilename;
	}

	public void setBinlogfilename(String binlogfilename) {
		this.binlogfilename = binlogfilename;
	}

	@XmlElement(name = "binlogPosition", type = Long.class)
	public Long getBinlogPosition() {
		return binlogPosition;
	}

	public void setBinlogPosition(Long binlogPosition) {
		this.binlogPosition = binlogPosition;
	}

	public void setErrorMsg(List<String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	@XmlElementWrapper(name = "processMsgList")
	@XmlElement(name = "processMsg")
	public List<String> getProcessMsg() {
		return processMsg;
	}

	public void setProcessMsg(List<String> processMsg) {
		this.processMsg = processMsg;
	}

	public void addProcessMsg(String processMsg) {
		this.processMsg.add(processMsg);
	}

	@XmlElementWrapper(name = "eventVOList")
	@XmlElements({
			@XmlElement(name = "EventVO", type = EventVO.class),
			@XmlElement(name = "QueryLogEventVO", type = QueryLogEventVO.class),
			@XmlElement(name = "StopLogEventVO", type = StopLogEventVO.class),
			@XmlElement(name = "RotateLogEventVO", type = RotateLogEventVO.class),
			@XmlElement(name = "XidLogEventVO", type = XidLogEventVO.class),
			@XmlElement(name = "TableMapLogEventVO", type = TableMapLogEventVO.class),
			@XmlElement(name = "FormatDescriptionLogEventVO", type = FormatDescriptionLogEventVO.class),
			@XmlElement(name = "RowEventVO", type = RowEventVO.class) })
	public List<EventVO> getEventVOList() {
		return eventVOList;
	}

	public void setEventVOList(List<EventVO> eventVOList) {
		this.eventVOList = eventVOList;
	}

	public void addEventVOList(EventVO eventVO) {
		this.eventVOList.add(eventVO);
	}

	@XmlElement(name = "resCode", type = String.class)
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	@Override
	public String toString() {
		String s = ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
		return s;
	}
}
