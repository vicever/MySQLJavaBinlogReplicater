package cn.ce.web.rest.impl;

import org.apache.commons.lang.StringUtils;

import cn.ce.binlog.mysql.conv.MySQLEventConsumer;
import cn.ce.binlog.mysql.parse.MysqlConnector;
import cn.ce.binlog.session.BinlogParseSession;
import cn.ce.binlog.session.BinlogParserManager;
import cn.ce.utils.common.ProFileUtil;
import cn.ce.web.rest.i.IFBinlogService;
import cn.ce.web.rest.vo.BinParseResultVO;
import cn.ce.web.rest.vo.TokenAuthRes;

public class BinlogServiceImpl implements IFBinlogService {

	public TokenAuthRes getToken(String slaveId, String tokenInput) {
		TokenAuthRes res = new TokenAuthRes();
		try {
			BinlogParserManager.getToken(new Long(slaveId), res);
		} catch (Throwable ex) {
			res = new TokenAuthRes();
			res.setMsgDetail("Error,msg:" + ex.getMessage() + ", slaveId:"
					+ slaveId);
			res.setResCode(TokenAuthRes.ERROR);
			ex.printStackTrace();
		}
		return res;
	}

	public BinParseResultVO getDump(Long slaveId, String binlogfilename,
			String binlogPosition, String serverhost, Integer serverPort,
			String username, String password, String tokenInput) {
		BinParseResultVO resVO = new BinParseResultVO();
		BinlogParseSession bps = new BinlogParseSession();
		MysqlConnector c = new MysqlConnector(serverhost, serverPort, username,
				password);
		try {
			BinlogParserManager.auth(slaveId, tokenInput);
			BinlogParserManager.startDumpToSession(slaveId, binlogfilename,
					binlogPosition, c, bps, resVO);
			boolean isNeedWait = true;
			BinlogParserManager.getVOFromSession(resVO, slaveId, isNeedWait);
			BinlogParserManager.saveCheckPoint(resVO, slaveId);
		} catch (Throwable ex) {
			StringBuilder sb = new StringBuilder();
			sb.append("Error,msg:");
			sb.append(ex.getMessage());
			sb.append(",slaveId:");
			sb.append(slaveId);
			// sb.append(",binlogfilename:");
			// sb.append(binlogfilename);
			// sb.append(",binlogPosition:");
			// sb.append(binlogPosition);
			// sb.append(",serverhost:");
			// sb.append(serverhost);
			// sb.append(",serverPort:");
			// sb.append(serverPort);
			// sb.append(",username:");
			// sb.append(username);
			// sb.append(",tokenInput:");
			// sb.append(tokenInput);
			resVO.addErrorMsg(sb.toString());
			resVO.setResCode(TokenAuthRes.ERROR);
			ex.printStackTrace();
		} finally {
			c.disconnect();
			BinlogParserManager.sessionMap.remove(slaveId);
		}
		return resVO;
	}
}